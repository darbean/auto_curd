package com.sankuai.lx.idea.plugin.curd.template.generator;

import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.model.MapperInfo;

/**
 * Description: TableNameGenerator
 * Author: liangxiao03
 * Create: 2016-02-24 12:41
 */
public class TableNameGenerator implements IMapperDataGenerator {
    @Override
    public String matcherName() {
        return "|tableName|";
    }

    @Override
    public String genMapperData(CodeInfo codeInfo) {
        return codeInfo.getTableName();
    }
}
