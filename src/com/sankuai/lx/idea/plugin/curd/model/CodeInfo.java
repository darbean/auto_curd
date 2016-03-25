package com.sankuai.lx.idea.plugin.curd.model;

import com.google.common.base.CaseFormat;
import com.sankuai.lx.idea.plugin.db.model.TableField;

import java.util.List;

/**
 * Description: CodeInfo
 * Author: liangxiao03
 * Create: 2016-03-25 15:16
 */
public class CodeInfo {

    private final String rootPackage;
    private String tableName;
    private final String uppercaseFirstTableName;
    private final String lowercaseFirstTableName;
    private final List<TableField> tableFields;
    private final TableField primaryField;


    public CodeInfo(String rootPackage, String tableName, List<TableField> tableFields) {
        this.rootPackage = rootPackage;
        this.tableName = tableName;
        this.uppercaseFirstTableName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
        this.lowercaseFirstTableName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName);
        this.tableFields = tableFields;
        this.primaryField = getPrimaryField(tableFields);
    }

    private TableField getPrimaryField(List<TableField> tableFields) {
        for (TableField tableField : tableFields) {
            if (tableField.isPrimaryKey()) {
                return tableField;
            }
        }
        throw new RuntimeException("table should have a primary key!");
    }

    public String getRootPackage() {
        return rootPackage;
    }

    public String getTableName() {
        return tableName;
    }

    public String getUppercaseFirstTableName() {
        return uppercaseFirstTableName;
    }

    public String getLowercaseFirstTableName() {
        return lowercaseFirstTableName;
    }

    public List<TableField> getTableFields() {
        return tableFields;
    }

    public TableField getPrimaryField() {
        return primaryField;
    }
}
