/*
 * This file is generated by jOOQ.
 */
package com.silentcloud.springrest.jooq.gen.tables;


import com.silentcloud.springrest.jooq.gen.DefaultSchema;
import com.silentcloud.springrest.jooq.gen.tables.records.SysMenuApiPermRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SysMenuApiPerm extends TableImpl<SysMenuApiPermRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>SYS_MENU_API_PERM</code>
     */
    public static final SysMenuApiPerm SYS_MENU_API_PERM = new SysMenuApiPerm();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SysMenuApiPermRecord> getRecordType() {
        return SysMenuApiPermRecord.class;
    }

    /**
     * The column <code>SYS_MENU_API_PERM.MENU_ID</code>.
     */
    public final TableField<SysMenuApiPermRecord, Long> MENU_ID = createField(DSL.name("MENU_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>SYS_MENU_API_PERM.API_PERM_ID</code>.
     */
    public final TableField<SysMenuApiPermRecord, Long> API_PERM_ID = createField(DSL.name("API_PERM_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    private SysMenuApiPerm(Name alias, Table<SysMenuApiPermRecord> aliased) {
        this(alias, aliased, null);
    }

    private SysMenuApiPerm(Name alias, Table<SysMenuApiPermRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>SYS_MENU_API_PERM</code> table reference
     */
    public SysMenuApiPerm(String alias) {
        this(DSL.name(alias), SYS_MENU_API_PERM);
    }

    /**
     * Create an aliased <code>SYS_MENU_API_PERM</code> table reference
     */
    public SysMenuApiPerm(Name alias) {
        this(alias, SYS_MENU_API_PERM);
    }

    /**
     * Create a <code>SYS_MENU_API_PERM</code> table reference
     */
    public SysMenuApiPerm() {
        this(DSL.name("SYS_MENU_API_PERM"), null);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public SysMenuApiPerm as(String alias) {
        return new SysMenuApiPerm(DSL.name(alias), this);
    }

    @Override
    public SysMenuApiPerm as(Name alias) {
        return new SysMenuApiPerm(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SysMenuApiPerm rename(String name) {
        return new SysMenuApiPerm(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SysMenuApiPerm rename(Name name) {
        return new SysMenuApiPerm(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
