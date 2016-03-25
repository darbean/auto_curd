package com.sankuai.lx.idea.plugin.curd.model;

import com.google.common.base.CaseFormat;
import com.sankuai.lx.idea.plugin.db.model.TableField;

import java.util.List;

/**
 * Description: MapperInfo
 * Author: liangxiao03
 * Create: 2016-02-24 12:05
 */
public class MapperInfo {

    private String rootPackage;
    private String tableName;
    private String uppercaseFirstTableName;
    private List<TableField> tableFields;
    private TableField primaryField;

    public MapperInfo(String rootPackage, String tableName, List<TableField> tableFields) {
        this.rootPackage = rootPackage;
        this.tableName = tableName.toLowerCase();
        this.uppercaseFirstTableName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, this.tableName);
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

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUppercaseFirstTableName() {
        return uppercaseFirstTableName;
    }

    public void setUppercaseFirstTableName(String uppercaseFirstTableName) {
        this.uppercaseFirstTableName = uppercaseFirstTableName;
    }

    public List<TableField> getTableFields() {
        return tableFields;
    }

    public void setTableFields(List<TableField> tableFields) {
        this.tableFields = tableFields;
    }

    public TableField getPrimaryField() {
        return primaryField;
    }

    public void setPrimaryField(TableField primaryField) {
        this.primaryField = primaryField;
    }
}
