/*
 * This file is generated by jOOQ.
 */
package com.silentcloud.springrest.jooq.gen.tables;


import com.silentcloud.springrest.jooq.gen.DefaultSchema;
import com.silentcloud.springrest.jooq.gen.tables.records.SysRoleButtonRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SysRoleButton extends TableImpl<SysRoleButtonRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>SYS_ROLE_BUTTON</code>
     */
    public static final SysRoleButton SYS_ROLE_BUTTON = new SysRoleButton();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SysRoleButtonRecord> getRecordType() {
        return SysRoleButtonRecord.class;
    }

    /**
     * The column <code>SYS_ROLE_BUTTON.ROLE_ID</code>.
     */
    public final TableField<SysRoleButtonRecord, Long> ROLE_ID = createField(DSL.name("ROLE_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>SYS_ROLE_BUTTON.BUTTON_ID</code>.
     */
    public final TableField<SysRoleButtonRecord, Long> BUTTON_ID = createField(DSL.name("BUTTON_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    private SysRoleButton(Name alias, Table<SysRoleButtonRecord> aliased) {
        this(alias, aliased, null);
    }

    private SysRoleButton(Name alias, Table<SysRoleButtonRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>SYS_ROLE_BUTTON</code> table reference
     */
    public SysRoleButton(String alias) {
        this(DSL.name(alias), SYS_ROLE_BUTTON);
    }

    /**
     * Create an aliased <code>SYS_ROLE_BUTTON</code> table reference
     */
    public SysRoleButton(Name alias) {
        this(alias, SYS_ROLE_BUTTON);
    }

    /**
     * Create a <code>SYS_ROLE_BUTTON</code> table reference
     */
    public SysRoleButton() {
        this(DSL.name("SYS_ROLE_BUTTON"), null);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public SysRoleButton as(String alias) {
        return new SysRoleButton(DSL.name(alias), this);
    }

    @Override
    public SysRoleButton as(Name alias) {
        return new SysRoleButton(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SysRoleButton rename(String name) {
        return new SysRoleButton(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SysRoleButton rename(Name name) {
        return new SysRoleButton(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
