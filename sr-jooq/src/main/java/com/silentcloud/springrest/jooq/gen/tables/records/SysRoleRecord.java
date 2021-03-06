/*
 * This file is generated by jOOQ.
 */
package com.silentcloud.springrest.jooq.gen.tables.records;


import com.silentcloud.springrest.jooq.gen.tables.SysRole;
import org.jooq.Field;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.TableRecordImpl;

import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SysRoleRecord extends TableRecordImpl<SysRoleRecord> implements Record8<Long, LocalDateTime, LocalDateTime, String, String, String, Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>SYS_ROLE.ID</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>SYS_ROLE.ID</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>SYS_ROLE.CREATED_TIME</code>.
     */
    public void setCreatedTime(LocalDateTime value) {
        set(1, value);
    }

    /**
     * Getter for <code>SYS_ROLE.CREATED_TIME</code>.
     */
    public LocalDateTime getCreatedTime() {
        return (LocalDateTime) get(1);
    }

    /**
     * Setter for <code>SYS_ROLE.LAST_MODIFIED_TIME</code>.
     */
    public void setLastModifiedTime(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>SYS_ROLE.LAST_MODIFIED_TIME</code>.
     */
    public LocalDateTime getLastModifiedTime() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>SYS_ROLE.DESCRIPTION</code>.
     */
    public void setDescription(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>SYS_ROLE.DESCRIPTION</code>.
     */
    public String getDescription() {
        return (String) get(3);
    }

    /**
     * Setter for <code>SYS_ROLE.NAME</code>.
     */
    public void setName(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>SYS_ROLE.NAME</code>.
     */
    public String getName() {
        return (String) get(4);
    }

    /**
     * Setter for <code>SYS_ROLE.VALUE</code>.
     */
    public void setValue(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>SYS_ROLE.VALUE</code>.
     */
    public String getValue() {
        return (String) get(5);
    }

    /**
     * Setter for <code>SYS_ROLE.CREATED_BY_ID</code>.
     */
    public void setCreatedById(Long value) {
        set(6, value);
    }

    /**
     * Getter for <code>SYS_ROLE.CREATED_BY_ID</code>.
     */
    public Long getCreatedById() {
        return (Long) get(6);
    }

    /**
     * Setter for <code>SYS_ROLE.LAST_MODIFIED_BY_ID</code>.
     */
    public void setLastModifiedById(Long value) {
        set(7, value);
    }

    /**
     * Getter for <code>SYS_ROLE.LAST_MODIFIED_BY_ID</code>.
     */
    public Long getLastModifiedById() {
        return (Long) get(7);
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<Long, LocalDateTime, LocalDateTime, String, String, String, Long, Long> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<Long, LocalDateTime, LocalDateTime, String, String, String, Long, Long> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return SysRole.SYS_ROLE.ID;
    }

    @Override
    public Field<LocalDateTime> field2() {
        return SysRole.SYS_ROLE.CREATED_TIME;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return SysRole.SYS_ROLE.LAST_MODIFIED_TIME;
    }

    @Override
    public Field<String> field4() {
        return SysRole.SYS_ROLE.DESCRIPTION;
    }

    @Override
    public Field<String> field5() {
        return SysRole.SYS_ROLE.NAME;
    }

    @Override
    public Field<String> field6() {
        return SysRole.SYS_ROLE.VALUE;
    }

    @Override
    public Field<Long> field7() {
        return SysRole.SYS_ROLE.CREATED_BY_ID;
    }

    @Override
    public Field<Long> field8() {
        return SysRole.SYS_ROLE.LAST_MODIFIED_BY_ID;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public LocalDateTime component2() {
        return getCreatedTime();
    }

    @Override
    public LocalDateTime component3() {
        return getLastModifiedTime();
    }

    @Override
    public String component4() {
        return getDescription();
    }

    @Override
    public String component5() {
        return getName();
    }

    @Override
    public String component6() {
        return getValue();
    }

    @Override
    public Long component7() {
        return getCreatedById();
    }

    @Override
    public Long component8() {
        return getLastModifiedById();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public LocalDateTime value2() {
        return getCreatedTime();
    }

    @Override
    public LocalDateTime value3() {
        return getLastModifiedTime();
    }

    @Override
    public String value4() {
        return getDescription();
    }

    @Override
    public String value5() {
        return getName();
    }

    @Override
    public String value6() {
        return getValue();
    }

    @Override
    public Long value7() {
        return getCreatedById();
    }

    @Override
    public Long value8() {
        return getLastModifiedById();
    }

    @Override
    public SysRoleRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public SysRoleRecord value2(LocalDateTime value) {
        setCreatedTime(value);
        return this;
    }

    @Override
    public SysRoleRecord value3(LocalDateTime value) {
        setLastModifiedTime(value);
        return this;
    }

    @Override
    public SysRoleRecord value4(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public SysRoleRecord value5(String value) {
        setName(value);
        return this;
    }

    @Override
    public SysRoleRecord value6(String value) {
        setValue(value);
        return this;
    }

    @Override
    public SysRoleRecord value7(Long value) {
        setCreatedById(value);
        return this;
    }

    @Override
    public SysRoleRecord value8(Long value) {
        setLastModifiedById(value);
        return this;
    }

    @Override
    public SysRoleRecord values(Long value1, LocalDateTime value2, LocalDateTime value3, String value4, String value5, String value6, Long value7, Long value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SysRoleRecord
     */
    public SysRoleRecord() {
        super(SysRole.SYS_ROLE);
    }

    /**
     * Create a detached, initialised SysRoleRecord
     */
    public SysRoleRecord(Long id, LocalDateTime createdTime, LocalDateTime lastModifiedTime, String description, String name, String value, Long createdById, Long lastModifiedById) {
        super(SysRole.SYS_ROLE);

        setId(id);
        setCreatedTime(createdTime);
        setLastModifiedTime(lastModifiedTime);
        setDescription(description);
        setName(name);
        setValue(value);
        setCreatedById(createdById);
        setLastModifiedById(lastModifiedById);
    }
}
