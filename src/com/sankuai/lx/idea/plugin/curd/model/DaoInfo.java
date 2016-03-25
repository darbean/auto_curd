package com.sankuai.lx.idea.plugin.curd.model;

import com.google.common.base.CaseFormat;

/**
 * Description: DaoInfo
 * Author: liangxiao03
 * Create: 2016-02-24 11:21
 */
public class DaoInfo {

    private String rootPackage;
    private String uppercaseFirstTableName;
    private String lowercaseFirstTableName;

    public DaoInfo(String rootPackage, String tableName) {
        this.rootPackage = rootPackage;
        tableName = tableName.toLowerCase();
        this.uppercaseFirstTableName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
        this.lowercaseFirstTableName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName);
    }

    public String getRootPackage() {
        return rootPackage;
    }

    public String getUppercaseFirstTableName() {
        return uppercaseFirstTableName;
    }

    public String getLowercaseFirstTableName() {
        return lowercaseFirstTableName;
    }
}
