package com.sankuai.lx.idea.plugin.db.model;

import java.util.List;

/**
 * Description: TableInfo
 * Author: liangxiao03
 * Create: 2016-02-24 16:01
 */
public class TableInfo {

    private String tableName;
    private List<TableField> tableFields;

    public TableInfo(String tableName, List<TableField> tableFields) {
        this.tableName = tableName;
        this.tableFields = tableFields;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<TableField> getTableFields() {
        return tableFields;
    }

    public void setTableFields(List<TableField> tableFields) {
        this.tableFields = tableFields;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "tableName='" + tableName + '\'' +
                ", tableFields=" + tableFields +
                '}';
    }
}
