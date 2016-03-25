package com.sankuai.lx.idea.plugin.curd.model;

import com.sankuai.lx.idea.plugin.db.model.TableField;

import java.util.List;

/**
 * Description: PoInfo
 * Author: liangxiao03
 * Create: 2016-02-24 18:05
 */
public class PojoInfo extends DaoInfo {

    private final List<TableField> tableFields;

    public PojoInfo(String rootPackage, String tableName, List<TableField> tableFields) {
        super(rootPackage, tableName);
        this.tableFields = tableFields;
    }

    public List<TableField> getTableFields() {
        return tableFields;
    }
}
