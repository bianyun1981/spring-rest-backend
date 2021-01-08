/*
 * This file is generated by jOOQ.
 */
package com.silentcloud.spring.rest.jooq.gen.tables.records;


import com.silentcloud.spring.rest.jooq.gen.tables.SysApiPerm;

import org.jooq.Field;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SysApiPermRecord extends TableRecordImpl<SysApiPermRecord> implements Record4<Long, String, String, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>SYS_API_PERM.ID</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>SYS_API_PERM.ID</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>SYS_API_PERM.NAME</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>SYS_API_PERM.NAME</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>SYS_API_PERM.VALUE</code>.
     */
    public void setValue(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>SYS_API_PERM.VALUE</code>.
     */
    public String getValue() {
        return (String) get(2);
    }

    /**
     * Setter for <code>SYS_API_PERM.PARENT_ID</code>.
     */
    public void setParentId(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>SYS_API_PERM.PARENT_ID</code>.
     */
    public Long getParentId() {
        return (Long) get(3);
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, String, String, Long> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Long, String, String, Long> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return SysApiPerm.SYS_API_PERM.ID;
    }

    @Override
    public Field<String> field2() {
        return SysApiPerm.SYS_API_PERM.NAME;
    }

    @Override
    public Field<String> field3() {
        return SysApiPerm.SYS_API_PERM.VALUE;
    }

    @Override
    public Field<Long> field4() {
        return SysApiPerm.SYS_API_PERM.PARENT_ID;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getValue();
    }

    @Override
    public Long component4() {
        return getParentId();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getValue();
    }

    @Override
    public Long value4() {
        return getParentId();
    }

    @Override
    public SysApiPermRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public SysApiPermRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public SysApiPermRecord value3(String value) {
        setValue(value);
        return this;
    }

    @Override
    public SysApiPermRecord value4(Long value) {
        setParentId(value);
        return this;
    }

    @Override
    public SysApiPermRecord values(Long value1, String value2, String value3, Long value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SysApiPermRecord
     */
    public SysApiPermRecord() {
        super(SysApiPerm.SYS_API_PERM);
    }

    /**
     * Create a detached, initialised SysApiPermRecord
     */
    public SysApiPermRecord(Long id, String name, String value, Long parentId) {
        super(SysApiPerm.SYS_API_PERM);

        setId(id);
        setName(name);
        setValue(value);
        setParentId(parentId);
    }
}