/*
 * This file is generated by jOOQ.
 */
package com.silentcloud.springrest.jooq.gen.tables;


import com.silentcloud.springrest.jooq.gen.DefaultSchema;
import com.silentcloud.springrest.jooq.gen.tables.records.LibBookRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.time.LocalDateTime;
import java.time.YearMonth;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LibBook extends TableImpl<LibBookRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>LIB_BOOK</code>
     */
    public static final LibBook LIB_BOOK = new LibBook();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<LibBookRecord> getRecordType() {
        return LibBookRecord.class;
    }

    /**
     * The column <code>LIB_BOOK.ID</code>.
     */
    public final TableField<LibBookRecord, Long> ID = createField(DSL.name("ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>LIB_BOOK.CREATED_TIME</code>.
     */
    public final TableField<LibBookRecord, LocalDateTime> CREATED_TIME = createField(DSL.name("CREATED_TIME"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>LIB_BOOK.LAST_MODIFIED_TIME</code>.
     */
    public final TableField<LibBookRecord, LocalDateTime> LAST_MODIFIED_TIME = createField(DSL.name("LAST_MODIFIED_TIME"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>LIB_BOOK.ISBN</code>.
     */
    public final TableField<LibBookRecord, String> ISBN = createField(DSL.name("ISBN"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>LIB_BOOK.PUBLISHED_ON</code>.
     */
    public final TableField<LibBookRecord, YearMonth> PUBLISHED_ON = createField(DSL.name("PUBLISHED_ON"), SQLDataType.INTEGER, this, "", new org.jooq.impl.JPAConverter(com.silentcloud.springrest.model.converter.YearMonthIntegerAttributeConverter.class));

    /**
     * The column <code>LIB_BOOK.TITLE</code>.
     */
    public final TableField<LibBookRecord, String> TITLE = createField(DSL.name("TITLE"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>LIB_BOOK.TRANSLATERS</code>.
     */
    public final TableField<LibBookRecord, String> TRANSLATERS = createField(DSL.name("TRANSLATERS"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>LIB_BOOK.UNIT_PRICE</code>.
     */
    public final TableField<LibBookRecord, Long> UNIT_PRICE = createField(DSL.name("UNIT_PRICE"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>LIB_BOOK.WORD_COUNT</code>.
     */
    public final TableField<LibBookRecord, Long> WORD_COUNT = createField(DSL.name("WORD_COUNT"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>LIB_BOOK.CREATED_BY_ID</code>.
     */
    public final TableField<LibBookRecord, Long> CREATED_BY_ID = createField(DSL.name("CREATED_BY_ID"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>LIB_BOOK.LAST_MODIFIED_BY_ID</code>.
     */
    public final TableField<LibBookRecord, Long> LAST_MODIFIED_BY_ID = createField(DSL.name("LAST_MODIFIED_BY_ID"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>LIB_BOOK.PUBLISHER_ID</code>.
     */
    public final TableField<LibBookRecord, Long> PUBLISHER_ID = createField(DSL.name("PUBLISHER_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    private LibBook(Name alias, Table<LibBookRecord> aliased) {
        this(alias, aliased, null);
    }

    private LibBook(Name alias, Table<LibBookRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>LIB_BOOK</code> table reference
     */
    public LibBook(String alias) {
        this(DSL.name(alias), LIB_BOOK);
    }

    /**
     * Create an aliased <code>LIB_BOOK</code> table reference
     */
    public LibBook(Name alias) {
        this(alias, LIB_BOOK);
    }

    /**
     * Create a <code>LIB_BOOK</code> table reference
     */
    public LibBook() {
        this(DSL.name("LIB_BOOK"), null);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public LibBook as(String alias) {
        return new LibBook(DSL.name(alias), this);
    }

    @Override
    public LibBook as(Name alias) {
        return new LibBook(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public LibBook rename(String name) {
        return new LibBook(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public LibBook rename(Name name) {
        return new LibBook(name, null);
    }

    // -------------------------------------------------------------------------
    // Row12 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row12<Long, LocalDateTime, LocalDateTime, String, YearMonth, String, String, Long, Long, Long, Long, Long> fieldsRow() {
        return (Row12) super.fieldsRow();
    }
}
