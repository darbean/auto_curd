package com.sankuai.lx.idea.plugin.curd.template;

import com.google.common.collect.Lists;
import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;

import java.util.List;

/**
 * Description: CodeTemplateFactory
 * Author: liangxiao03
 * Create: 2016-03-25 15:48
 */
public class CodeTemplateFactory {

    public static List<CodeTemplate> getCodeTemplates(CodeInfo codeInfo) {
        List<CodeTemplate> codeTemplates = Lists.newArrayList();
        codeTemplates.add(new DaoTemplate(codeInfo));
        codeTemplates.add(new MapperTemplate(codeInfo));
        codeTemplates.add(new PoTemplate(codeInfo));
        codeTemplates.add(new QueryTemplate(codeInfo));
        return codeTemplates;
    }

}
