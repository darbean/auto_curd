package com.sankuai.lx.idea.plugin.curd.template.generator;

import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.model.MapperInfo;
import com.sankuai.lx.idea.plugin.db.model.TableField;
import com.sankuai.lx.idea.plugin.utils.Constants;

/**
 * Description: TableFieldsGenerator
 * Author: liangxiao03
 * Create: 2016-02-24 13:46
 */
public class TableFieldsGenerator implements IMapperDataGenerator {


    @Override
    public String matcherName() {
        return "|tableFields|";
    }

    @Override
    public String genMapperData(CodeInfo codeInfo) {
        StringBuilder result = new StringBuilder();
        for (TableField tableField : codeInfo.getTableFields()) {
            result.append(tableField.getDbFieldName()).append(Constants.QUOTA);
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}
