package com.silentcloud.springrest.service.impl.module;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.wenhao.jpa.PredicateBuilder;
import com.github.wenhao.jpa.Specifications;
import com.silentcloud.springrest.model.entity.Activatable;
import com.silentcloud.springrest.model.entity.LogicallyDeletable;
import com.silentcloud.springrest.repository.BaseRepository;
import com.silentcloud.springrest.service.api.EntityDeleteFailureException;
import com.silentcloud.springrest.service.api.LogicallyDeletedEntityActivateFailureException;
import com.silentcloud.springrest.service.api.UniqueConstraintViolationException;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.dto.Unique;
import com.silentcloud.springrest.service.api.module.BaseService;
import com.silentcloud.springrest.service.impl.mapper.BaseMapper;
import com.silentcloud.springrest.service.impl.meta.EntityMetaData;
import com.silentcloud.springrest.service.impl.meta.EntityMetaDataMap;
import com.silentcloud.springrest.service.impl.query.flat.FlatQueryConditionExprParser;
import com.silentcloud.springrest.service.impl.util.JooqUtil;
import com.silentcloud.springrest.service.impl.util.JpaUtil;
import com.silentcloud.springrest.util.LabelUtil;
import com.silentcloud.springrest.util.MiscUtil;
import lombok.NonNull;
import lombok.Value;
import org.hibernate.Hibernate;
import org.jooq.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static com.silentcloud.springrest.model.entity.LogicallyDeletable.DELETED_PROPERTY_NAME;
import static com.silentcloud.springrest.util.MiscUtil.illegalArgumentException;


@Transactional(readOnly = true)
public abstract class AbstractBaseService<ID extends Serializable, Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>>
        implements BaseService<ID, Entity, DTO> {
    private static final Collection<Class<?>> UNIQUE_CHECK_ATTR_ALLOWED_VALUE_TYPES = Arrays.asList(
            String.class, Integer.class, Long.class, int.class, long.class);

    protected final DSLContext dsl;
    private final BaseRepository<ID, Entity> repository;
    private final BaseMapper<ID, Entity, DTO> mapper;

    private final String label;
    private final Class<Entity> entityClass;
    private final boolean isEntityLogicallyDeletable;
    private final boolean isEntityActivatable;
    private final Specification<Entity> specForNotLogicallyDeleted;

    {
        Class<DTO> dtoClass = MiscUtil.getDtoGenericParameterClass(getClass());
        entityClass = MiscUtil.getEntityGenericParameterClass(getClass());

        EntityMetaData<ID, DTO, Entity> entityMetaData = EntityMetaData.of(dtoClass, entityClass);
        EntityMetaDataMap.put(dtoClass, entityMetaData);
        EntityMetaDataMap.put(entityClass, entityMetaData);

        label = entityMetaData.getLabel();
        isEntityLogicallyDeletable = entityMetaData.isEntityLogicallyDeletable();
        isEntityActivatable = entityMetaData.isEntityActivatable();
        specForNotLogicallyDeleted = Specifications.<Entity>and().eq(isEntityLogicallyDeletable, DELETED_PROPERTY_NAME, false).build();
    }

    protected AbstractBaseService(DSLContext dsl,
                                  BaseRepository<ID, Entity> repository,
                                  BaseMapper<ID, Entity, DTO> mapper) {
        this.dsl = dsl;
        this.repository = repository;
        this.mapper = mapper;
    }

    @PostConstruct
    private void buildNestedTable() {
        SelectConditionStep<? extends Record> selectConditionStep =
                buildSelectConditionStep(flteredBuiltSelectPartSql(), buildJoinedTable());
        TableLike<? extends Record> nestedTable = buildRemainingPartSql(selectConditionStep).asTable("nested");
        Set<String> legalFlatQueryOuterFieldNames = JooqUtil.getLegalOuterFieldNames(nestedTable);

        EntityMetaData<ID, DTO, Entity> entityMetaData = EntityMetaDataMap.get(entityClass);
        entityMetaData.setFlatQueryConditionExprParser(new FlatQueryConditionExprParser(legalFlatQueryOuterFieldNames));
        entityMetaData.setJooqNestedTable(nestedTable);
        entityMetaData.setMapper(mapper);
    }

    protected SelectSelectStep<? extends Record> buildSelectPartSql() {
        return dsl.select();
    }

    protected abstract Table<? extends Record> buildJoinedTable();

    protected SelectFinalStep<? extends Record> buildRemainingPartSql(SelectConditionStep<? extends Record> selectConditionStep) {
        return selectConditionStep;
    }


    private Specification<Entity> buildFindByIdSpec(ID id) {
        return JpaUtil.buildFindByIdSpec(entityClass, id);
    }

    @Override
    public boolean existsById(@NonNull ID id) {
        return findById(id) != null;
    }

    @Override
    public DTO findById(@NonNull ID id) {
        Entity entity = repository.findOne(buildFindByIdSpec(id)).orElse(null);
        return mapper.entityToDto(entity);
    }

    @Override
    public List<DTO> findAll() {
        List<Entity> entities = repository.findAll(specForNotLogicallyDeleted);
        return mapper.entityListToDtoList(entities);
    }

    @Transactional
    @Override
    public DTO create(@NonNull DTO dto) {
        checkUniqueConstraints(dto);
        Entity entity = mapper.dtoToEntity(dto);
        Entity savedEntity = repository.save(entity);
        return mapper.entityToDto(savedEntity);
    }

    @Transactional
    @Override
    public DTO updateById(@NonNull ID id, @NonNull DTO dto) {
        if (!Objects.equals(id, Objects.requireNonNull(dto.getId()))) {
            String dtoClassName = dto.getClass().getSimpleName();
            throw illegalArgumentException("路径变量ID 必须跟请求参数对象{}的ID 保持一致", dtoClassName);
        }

        checkUniqueConstraints(dto, id);
        Entity entity = repository.getOne(id);
        mapper.updateEntityFromDto(dto, entity);
        Entity updatedEntity = repository.save(entity);
        return mapper.entityToDto(updatedEntity);
    }

    @Transactional
    @Override
    public void deleteById(@NonNull ID id) {
        Entity entity = repository.getOne(id);
        if (isEntityLogicallyDeletable) {
            LogicallyDeletable deletableEntity = ((LogicallyDeletable) entity);
            if (deletableEntity.isDeleted()) {
                return;
            }
            ((LogicallyDeletable) entity).logicallyDelete();

            if (isEntityActivatable) {
                Activatable activatableEntity = (Activatable) entity;
                if (activatableEntity.isActive()) {
                    activatableEntity.setActive(false);
                }
            }
            repository.save(entity);
        } else {
            checkReferentialConstraintsBeforeDelete(label, Hibernate.unproxy(entity, entityClass));
            repository.delete(entity);
        }
    }

    @Transactional
    @Override
    public void activateById(@NonNull ID id) {
        doActivatableOperation(id, true);
    }

    @Transactional
    @Override
    public void deactivateById(@NonNull ID id) {
        doActivatableOperation(id, false);
    }

    private void doActivatableOperation(ID id, boolean targetActive) {
        Entity entity = repository.getOne(id);

        if (isEntityActivatable) {
            if (isEntityLogicallyDeletable && ((LogicallyDeletable) entity).isDeleted()) {
                throw new LogicallyDeletedEntityActivateFailureException(label, id, targetActive ? "启用" : "停用");
            }

            Activatable activatableEntity = (Activatable) entity;
            if (activatableEntity.isActive() ^ targetActive) {
                activatableEntity.setActive(targetActive);
                repository.save(entity);
            }
        } else {
            throw new UnsupportedOperationException("entity not activatable, activatable operation not supported.");
        }
    }

    private SelectSelectStep<? extends Record> flteredBuiltSelectPartSql() {
        SelectSelectStep<? extends Record> selectPartSql = buildSelectPartSql();
        org.jooq.Field<?>[] allFields = selectPartSql.getSelect().toArray(new org.jooq.Field<?>[0]);
        if (isEntityLogicallyDeletable) {
            org.jooq.Field<?>[] filteredFields = ArrayUtil.filter(allFields,
                    (Filter<org.jooq.Field<?>>) field -> !JooqUtil.buildFieldAlias(field).endsWith(
                            JooqUtil.DELIMETER_BETWEEN_TABLE_AND_COLUMN + "deleted"));
            return dsl.select(filteredFields);
        } else {
            return dsl.select(allFields);
        }
    }

    private SelectConditionStep<? extends Record> buildSelectConditionStep(SelectSelectStep<? extends Record> selectPartSql,
                                                                           Table<? extends Record> joinedTable) {
        List<org.jooq.Field<?>> aliasedFields;
        if (selectPartSql.getSQL().equals(dsl.select().getSQL())) {
            aliasedFields = JooqUtil.addAliasForTableFields(joinedTable.fields());
        } else {
            aliasedFields = JooqUtil.addAliasForTableFields(selectPartSql.getSelect());
        }

        SelectConditionStep<? extends Record> result = dsl.select(aliasedFields).from(joinedTable).where();
        if (isEntityLogicallyDeletable) {
            org.jooq.Field<Boolean> field = joinedTable.field("DELETED", Boolean.class);
            Condition notLogicallyDeleted = Objects.requireNonNull(field).eq(false);
            result = result.and(notLogicallyDeleted);
        }
        return result;
    }

    private void checkUniqueConstraints(DTO dto) {
        checkUniqueConstraints(dto, null);
    }

    private void checkUniqueConstraints(DTO dto, ID id) {
        List<UniqueCheckingAttribute> attrList = findAllUniqueCheckingAttributes(dto);
        attrList.forEach(attr -> doCheckUniqueConstraints(dto, attr.getName(), attr.getFullLabel(), attr.getValue(),
                attr.getWithInScopeAttribute(), id));
    }

    private List<UniqueCheckingAttribute> findAllUniqueCheckingAttributes(DTO dto) {
        List<Field> fieldList = getUniqueCheckedFieldListBySpecifiedValueTypes(dto.getClass());

        return parseUniqueCheckingAttributes(dto, fieldList);
    }

    private List<UniqueCheckingAttribute> parseUniqueCheckingAttributes(DTO dto, List<Field> fieldList) {
        List<UniqueCheckingAttribute> resultList = new ArrayList<>();
        for (Field field : fieldList) {
            Unique uniqueAnnotation = AnnotationUtil.getAnnotation(field, Unique.class);
            if (uniqueAnnotation != null) {
                Object fieldValue = ReflectUtil.getFieldValue(dto, field);
                if (fieldValue == null) {
                    continue;
                }

                String entityAttribute = uniqueAnnotation.entityAttribute();
                String withInScopeAttribute = uniqueAnnotation.scope();
                String attrName = StrUtil.isBlank(entityAttribute) ? field.getName() : entityAttribute;
                String attrFullLabel = LabelUtil.getFieldFullLabel(dto.getClass(), field);

                resultList.add(UniqueCheckingAttribute.of(attrName, attrFullLabel, fieldValue, withInScopeAttribute));
            }
        }
        return resultList;
    }

    private List<Field> getUniqueCheckedFieldListBySpecifiedValueTypes(Class<?> clasz) {
        List<Field> fieldList = MiscUtil.getFieldsListWithAnnotation(clasz, Unique.class);
        return fieldList.stream().filter(field -> UNIQUE_CHECK_ATTR_ALLOWED_VALUE_TYPES.contains(field.getType()))
                .collect(Collectors.toList());
    }

    private void doCheckUniqueConstraints(DTO dto, String attrName, String attrFullLabel, Object attrValue,
                                          String withInScopeAttribute, ID id) {
        PredicateBuilder<Entity> predicateBuilder = Specifications.<Entity>and().eq(attrName, attrValue);
        if (StrUtil.isNotBlank(withInScopeAttribute)) {
            Field withInScopeField = ReflectUtil.getField(dto.getClass(), withInScopeAttribute);
            Assert.notNull(withInScopeField, "attribute[{}] not exists in {}", withInScopeAttribute, dto.getClass());

            Object withInScopeFieldValue = ReflectUtil.getFieldValue(dto, withInScopeField);
            if (withInScopeFieldValue instanceof BaseDto) {
                Object withInScopeObjId = ((BaseDto<?, ?>) withInScopeFieldValue).getId();
                predicateBuilder.eq(withInScopeAttribute + ".id", withInScopeObjId);
            } else {
                predicateBuilder.eq(withInScopeAttribute, withInScopeFieldValue);
            }
        }

        Specification<Entity> specification = predicateBuilder.build();
        repository.findOne(specification).ifPresent(entity -> {
            if (id == null || !id.equals(entity.getId())) {
                throw new UniqueConstraintViolationException(attrFullLabel, attrValue);
            }
        });
    }

    private static <ID extends Serializable, Entity extends Persistable<ID>>
    void checkReferentialConstraintsBeforeDelete(String label, @NonNull Entity entity) {
        checkReferentialConstraintsBeforeDelete(label, entity, ManyToMany.class);
        checkReferentialConstraintsBeforeDelete(label, entity, OneToMany.class);
        checkReferentialConstraintsBeforeDelete(label, entity, OneToOne.class);
    }

    private static <ID extends Serializable, Entity extends Persistable<ID>>
    void checkReferentialConstraintsBeforeDelete(String label, @NonNull Entity entity, Class<? extends Annotation> annotationType) {
        List<Field> annotatedFields = JpaUtil.getAnnotatedFieldsByAnnotationType(entity.getClass(), annotationType);

        for (Field field : annotatedFields) {
            String mappedByPropValue = AnnotationUtil.getAnnotationValue(field, annotationType, "mappedBy");
            if (StrUtil.isNotBlank(mappedByPropValue)) {
                if (annotationType.equals(OneToOne.class)) {
                    Object referencedObj = ReflectUtil.getFieldValue(entity, field);
                    if (referencedObj != null) {
                        Class<?> clazz = referencedObj.getClass();
                        String relatedObjLabel = EntityMetaDataMap.get(clazz).getLabel();
                        throw new EntityDeleteFailureException(label, entity.getId(), 1, relatedObjLabel);
                    }
                } else if (annotationType.equals(ManyToMany.class) || annotationType.equals(OneToMany.class)) {
                    Collection<?> coll = (Collection<?>) ReflectUtil.getFieldValue(entity, field);
                    if (coll != null && !coll.isEmpty()) {
                        Class<?> firestElementClass = coll.iterator().next().getClass();
                        String relatedObjLabel = EntityMetaDataMap.get(firestElementClass).getLabel();
                        throw new EntityDeleteFailureException(label, entity.getId(), coll.size(), relatedObjLabel);
                    }
                }
            }
        }
    }

    @Value(staticConstructor = "of")
    private static class UniqueCheckingAttribute {
        String name;
        String fullLabel;
        Object value;
        String withInScopeAttribute;
    }
}
