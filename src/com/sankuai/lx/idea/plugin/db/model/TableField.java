package com.sankuai.lx.idea.plugin.db.model;

import com.sankuai.lx.idea.plugin.curd.model.FieldType;

/**
 * Description: TableField
 * Author: liangxiao03
 * Create: 2016-02-24 12:01
 */
public class TableField {

    private String dbFieldName;
    private String poFieldName;
    private FieldType type;
    private boolean isPrimaryKey;
    private boolean isTimestamp;

    public TableField(String dbFieldName, String poFieldName, FieldType type, boolean isPrimaryKey, boolean isTimestamp) {
        this.dbFieldName = dbFieldName;
        this.poFieldName = poFieldName;
        this.type = type;
        this.isPrimaryKey = isPrimaryKey;
        this.isTimestamp = isTimestamp;
    }

    public String getDbFieldName() {
        return dbFieldName;
    }

    public void setDbFieldName(String dbFieldName) {
        this.dbFieldName = dbFieldName;
    }

    public String getPoFieldName() {
        return poFieldName;
    }

    public void setPoFieldName(String poFieldName) {
        this.poFieldName = poFieldName;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public boolean isTimestamp() {
        return isTimestamp;
    }

    public void setTimestamp(boolean isTimestamp) {
        this.isTimestamp = isTimestamp;
    }

    @Override
    public String toString() {
        return "TableField{" +
                "dbFieldName='" + dbFieldName + '\'' +
                ", poFieldName='" + poFieldName + '\'' +
                ", type=" + type +
                ", isPrimaryKey=" + isPrimaryKey +
                ", isTimestamp=" + isTimestamp +
                '}';
    }
}
