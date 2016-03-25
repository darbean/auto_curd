package com.sankuai.lx.idea.plugin.curd.template.generator;

import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.model.MapperInfo;

/**
 * Description: IMapperDataGenerator
 * Author: liangxiao03
 * Create: 2016-02-24 12:38
 */
public interface IMapperDataGenerator {

    String matcherName();

    String genMapperData(CodeInfo codeInfo);

}
