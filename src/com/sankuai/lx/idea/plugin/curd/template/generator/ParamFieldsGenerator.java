package com.sankuai.lx.idea.plugin.curd.template.generator;

import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.model.MapperInfo;
import com.sankuai.lx.idea.plugin.db.model.TableField;
import com.sankuai.lx.idea.plugin.utils.Constants;

/**
 * Description: ParamFieldsGenerator
 * Author: liangxiao03
 * Create: 2016-02-24 13:43
 */
public class ParamFieldsGenerator implements IMapperDataGenerator {

    private final String TEMPLATE = "#{%s}";

    private final String NULL = "null";


    @Override
    public String matcherName() {
        return "|paramFields|";
    }

    @Override
    public String genMapperData(CodeInfo codeInfo) {
        StringBuilder result = new StringBuilder();
        for (TableField tableField : codeInfo.getTableFields()) {
            if (tableField.isPrimaryKey()) {
                result.append(NULL).append(Constants.QUOTA);
            }  else {
                result.append(String.format(TEMPLATE, tableField.getPoFieldName())).append(Constants.QUOTA);
            }
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}
