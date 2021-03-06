package com.silentcloud.springrest.service.impl.mapper.sys;

import com.silentcloud.springrest.model.entity.sys.ApiPerm;
import com.silentcloud.springrest.model.entity.sys.Menu;
import com.silentcloud.springrest.repository.sys.MenuRepository;
import com.silentcloud.springrest.service.api.dto.sys.MenuDto;
import com.silentcloud.springrest.service.impl.mapper.AbstractCycleAvoidingMappingContext;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * 菜单权限 Mapper
 *
 * @author bianyun
 */
@SuppressWarnings({
        "SpringJavaAutowiredMembersInspection",
        "RedundantSuppression",
        "AlibabaAbstractClassShouldStartWithAbstractNaming",
        "AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc"
})
@Mapper
public abstract class MenuMapper {

    @Autowired
    private MenuRepository menuRepository;

    @Named("entityToDto")
    public MenuDto entityToDto(Menu entity) {
        return entityToDto(entity, new MenuCycleAvoidingMappingContext());
    }

    @Named("dtoToEntity")
    public Menu dtoToEntity(MenuDto dto) {
        return dtoToEntity(dto, new MenuCycleAvoidingMappingContext());
    }

    @IterableMapping(qualifiedByName = "entityToDto")
    public abstract List<MenuDto> entityListToDtoList(List<Menu> entityList);

    @IterableMapping(qualifiedByName = "entityToDto")
    public abstract Set<MenuDto> entitySetToDtoSet(Set<Menu> entitySet);

    @Mapping(target = "parentMenuValue", source = "parent.value")
    @Mapping(target = "parentMenuName", source = "parent.name")
    @Mapping(target = "apiPermValues", ignore = true)
    @Mapping(target = "parent", ignore = true)
    abstract MenuDto entityToDto(Menu entity, @Context MenuCycleAvoidingMappingContext context);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "buttons", ignore = true)
    @Mapping(target = "apiPerms", ignore = true)
    abstract Menu dtoToEntity(MenuDto dto, @Context MenuCycleAvoidingMappingContext context);

    @AfterMapping
    public void establishRelations(MenuDto dto, @MappingTarget Menu entity) {
        MenuDto parentMenuDto = dto.getParent();
        if (parentMenuDto != null && parentMenuDto.getId() != null) {
            entity.setParent(menuRepository.getOne(parentMenuDto.getId()));
        }
    }

    @AfterMapping
    public void doExtraMappings(Menu entity, @MappingTarget MenuDto dto) {
        Set<String> apiPermValueSet = entity.getApiPerms().stream()
                .map(ApiPerm::getValue).collect(java.util.stream.Collectors.toSet());
        dto.setApiPermValues(apiPermValueSet);
    }

    static class MenuCycleAvoidingMappingContext extends AbstractCycleAvoidingMappingContext<Long, Menu, MenuDto> {
    }

}
