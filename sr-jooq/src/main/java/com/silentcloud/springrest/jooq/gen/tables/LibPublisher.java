/*
 * This file is generated by jOOQ.
 */
package com.silentcloud.springrest.jooq.gen.tables;


import com.silentcloud.springrest.jooq.gen.DefaultSchema;
import com.silentcloud.springrest.jooq.gen.tables.records.LibPublisherRecord;
import com.silentcloud.springrest.model.enums.Country;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LibPublisher extends TableImpl<LibPublisherRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>LIB_PUBLISHER</code>
     */
    public static final LibPublisher LIB_PUBLISHER = new LibPublisher();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<LibPublisherRecord> getRecordType() {
        return LibPublisherRecord.class;
    }

    /**
     * The column <code>LIB_PUBLISHER.ID</code>.
     */
    public final TableField<LibPublisherRecord, Long> ID = createField(DSL.name("ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>LIB_PUBLISHER.CREATED_TIME</code>.
     */
    public final TableField<LibPublisherRecord, LocalDateTime> CREATED_TIME = createField(DSL.name("CREATED_TIME"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>LIB_PUBLISHER.LAST_MODIFIED_TIME</code>.
     */
    public final TableField<LibPublisherRecord, LocalDateTime> LAST_MODIFIED_TIME = createField(DSL.name("LAST_MODIFIED_TIME"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>LIB_PUBLISHER.ADDRESS</code>.
     */
    public final TableField<LibPublisherRecord, String> ADDRESS = createField(DSL.name("ADDRESS"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>LIB_PUBLISHER.COUNTRY</code>.
     */
    public final TableField<LibPublisherRecord, Country> COUNTRY = createField(DSL.name("COUNTRY"), SQLDataType.INTEGER, this, "", org.jooq.Converter.of(Integer.class, Country.class, i -> Country.HELPER.byId(i), Country::getId));

    /**
     * The column <code>LIB_PUBLISHER.NAME</code>.
     */
    public final TableField<LibPublisherRecord, String> NAME = createField(DSL.name("NAME"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>LIB_PUBLISHER.ZIP_CODE</code>.
     */
    public final TableField<LibPublisherRecord, String> ZIP_CODE = createField(DSL.name("ZIP_CODE"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>LIB_PUBLISHER.CREATED_BY_ID</code>.
     */
    public final TableField<LibPublisherRecord, Long> CREATED_BY_ID = createField(DSL.name("CREATED_BY_ID"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>LIB_PUBLISHER.LAST_MODIFIED_BY_ID</code>.
     */
    public final TableField<LibPublisherRecord, Long> LAST_MODIFIED_BY_ID = createField(DSL.name("LAST_MODIFIED_BY_ID"), SQLDataType.BIGINT, this, "");

    private LibPublisher(Name alias, Table<LibPublisherRecord> aliased) {
        this(alias, aliased, null);
    }

    private LibPublisher(Name alias, Table<LibPublisherRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>LIB_PUBLISHER</code> table reference
     */
    public LibPublisher(String alias) {
        this(DSL.name(alias), LIB_PUBLISHER);
    }

    /**
     * Create an aliased <code>LIB_PUBLISHER</code> table reference
     */
    public LibPublisher(Name alias) {
        this(alias, LIB_PUBLISHER);
    }

    /**
     * Create a <code>LIB_PUBLISHER</code> table reference
     */
    public LibPublisher() {
        this(DSL.name("LIB_PUBLISHER"), null);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public LibPublisher as(String alias) {
        return new LibPublisher(DSL.name(alias), this);
    }

    @Override
    public LibPublisher as(Name alias) {
        return new LibPublisher(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public LibPublisher rename(String name) {
        return new LibPublisher(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public LibPublisher rename(Name name) {
        return new LibPublisher(name, null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<Long, LocalDateTime, LocalDateTime, String, Country, String, String, Long, Long> fieldsRow() {
        return (Row9) super.fieldsRow();
    }
}
