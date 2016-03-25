package com.sankuai.lx.idea.plugin.curd.template.generator;

import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.model.MapperInfo;

/**
 * Description: UppercaseTableNameGenerator
 * Author: liangxiao03
 * Create: 2016-02-24 12:41
 */
public class UppercaseTableNameGenerator implements IMapperDataGenerator {
    @Override
    public String matcherName() {
        return "|uppercaseTableName|";
    }

    @Override
    public String genMapperData(CodeInfo codeInfo) {
        return codeInfo.getUppercaseFirstTableName();
    }
}
