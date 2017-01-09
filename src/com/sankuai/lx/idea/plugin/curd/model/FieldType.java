package com.sankuai.lx.idea.plugin.curd.model;

/**
 * Description: FieldType
 * Author: liangxiao03
 * Create: 2016-02-24 14:05
 */
public enum FieldType {
    INT("INTEGER", "Integer"),
    SMALLINT("INTEGER", "Integer"),
    TINYINT("INTEGER", "Integer"),
    BIGINT("BIGINT", "Long"),
    VARCHAR("VARCHAR", "String"),
    CHAR("CHAR", "String"),
    TEXT("LONGVARCHAR", "String"),
    TIMESTAMP("TIMESTAMP", "Timestamp"),
    DATETIME("DATETIME", "Timestamp"),
    BLOB("BLOB", "String"),
    ;

    private final String jdbcType;
    private final String javaType;


    FieldType(String jdbcType, String javaType) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
    }

    public static FieldType fromDBType(String dbType) {
        for (FieldType fieldType : FieldType.values()) {
            if (dbType.toUpperCase().startsWith(fieldType.name())) {
                return fieldType;
            }
        }
        throw new RuntimeException("invalid db field: " + dbType);
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public String getJavaType() {
        return javaType;
    }
}
