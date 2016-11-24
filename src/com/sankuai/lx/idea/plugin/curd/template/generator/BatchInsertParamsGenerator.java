package com.sankuai.lx.idea.plugin.curd.template.generator;

import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.model.MapperInfo;
import com.sankuai.lx.idea.plugin.db.model.TableField;
import com.sankuai.lx.idea.plugin.utils.Constants;

/**
 * Description: BatchInsertParamsGenerator
 * Author: liangxiao03
 * Create: 2016-02-24 13:56
 */
public class BatchInsertParamsGenerator implements IMapperDataGenerator {

    private final String TEMPLATE = "%s=VALUES(%s)";

    @Override
    public String matcherName() {
        return "|batchInsertParams|";
    }

    @Override
    public String genMapperData(CodeInfo codeInfo) {
        StringBuilder result = new StringBuilder();
        for (TableField tableField : codeInfo.getTableFields()) {
            result.append(String.format(TEMPLATE, tableField.getDbFieldName(), tableField.getDbFieldName())).append(Constants.QUOTA);
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}
