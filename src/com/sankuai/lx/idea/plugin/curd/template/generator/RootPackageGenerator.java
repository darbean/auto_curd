package com.sankuai.lx.idea.plugin.curd.template.generator;

import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.model.MapperInfo;

/**
 * Description: RootPackageGenerator
 * Author: liangxiao03
 * Create: 2016-02-24 12:40
 */
public class RootPackageGenerator implements IMapperDataGenerator {
    @Override
    public String matcherName() {
        return "|rootPackage|";
    }

    @Override
    public String genMapperData(CodeInfo codeInfo) {
        return codeInfo.getRootPackage();
    }
}
