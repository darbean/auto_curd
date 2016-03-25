package com.sankuai.lx.idea.plugin.curd.template;

import com.google.common.collect.Lists;
import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.model.FieldType;
import com.sankuai.lx.idea.plugin.curd.model.PojoInfo;
import com.sankuai.lx.idea.plugin.db.model.TableField;
import com.sankuai.lx.idea.plugin.utils.GlobelPathContainer;

import java.util.List;

/**
 * Description: PoTemplate
 * Author: liangxiao03
 * Create: 2016-02-24 17:57
 */
public class QueryTemplate extends PojoTemplate implements CodeTemplate {

    private static final String PACKAGE_SUFFIX = ".model.query";
    private static final String CLASS_SUFFIX = "Query";
    private String rootPackage;
    private String className;
    private CodeInfo codeInfo;

    public QueryTemplate(CodeInfo codeInfo) {
        this.rootPackage = codeInfo.getRootPackage();
        this.className = buildClassName(codeInfo);
        this.codeInfo = codeInfo;
    }

    public  String build() {
        List<TableField> tableFields = Lists.newArrayList(codeInfo.getTableFields());
        TableField offsetField = new TableField("offset", "offset", FieldType.BIGINT, false, false);
        TableField limitField = new TableField("limit", "limit", FieldType.INT, false, false);
        tableFields.add(offsetField);
        tableFields.add(limitField);
        return String.format(TEMPLATE, codeInfo.getRootPackage() + PACKAGE_SUFFIX, buildClassName(codeInfo)
                , buildClassName(codeInfo), buildField(tableFields) + "\n" + buildGetterAndSetter(tableFields));
    }

    private  String buildClassName(CodeInfo codeInfo) {
        return codeInfo.getUppercaseFirstTableName() + CLASS_SUFFIX;
    }

    @Override
    public String getFilePath() {
        return GlobelPathContainer.getInstance().getRealPath(rootPackage + PACKAGE_SUFFIX);
    }

    @Override
    public String getFileName() {
        return className + ".java";
    }
}
