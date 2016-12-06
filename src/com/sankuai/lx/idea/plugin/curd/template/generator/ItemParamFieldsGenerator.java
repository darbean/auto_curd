package com.sankuai.lx.idea.plugin.curd.template.generator;

import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.db.model.TableField;
import com.sankuai.lx.idea.plugin.utils.Constants;

/**
 * Description: ItemParamFieldsGenerator
 * Author: liangxiao03
 * Create: 2016-12-06 15:52
 */
public class ItemParamFieldsGenerator implements IMapperDataGenerator {

    private final String TEMPLATE = "#{item.%s}";


    @Override
    public String matcherName() {
        return "|itemParamFields|";
    }

    @Override
    public String genMapperData(CodeInfo codeInfo) {
        StringBuilder result = new StringBuilder();
        for (TableField tableField : codeInfo.getTableFields()) {
            if (tableField.isPrimaryKey()) {
                result.append(Constants.NULL).append(Constants.QUOTA);
            }  else {
                result.append(String.format(TEMPLATE, tableField.getPoFieldName())).append(Constants.QUOTA);
            }
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}
