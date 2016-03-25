package com.sankuai.lx.idea.plugin.curd.template.generator;

import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.model.MapperInfo;

/**
 * Description: UpdateWhereFieldsGenerator
 * Author: liangxiao03
 * Create: 2016-02-24 14:31
 */
public class UpdateWhereFieldsGenerator implements IMapperDataGenerator {

    private final String TEMPLATE = "%s=#{%s}";

    @Override
    public String matcherName() {
        return "|updateWhereFields|";
    }

    @Override
    public String genMapperData(CodeInfo codeInfo) {
        return String.format(TEMPLATE, codeInfo.getPrimaryField().getDbFieldName(), codeInfo.getPrimaryField().getPoFieldName());
    }
}
