package com.sankuai.lx.idea.plugin.curd.template;

import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;

/**
 * Description: CodeTemplate
 * Author: liangxiao03
 * Create: 2016-03-25 15:08
 */
public interface CodeTemplate {

    String build();

    String getFilePath();

    String getFileName();

}
