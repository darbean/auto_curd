package com.sankuai.lx.idea.plugin.curd.template.generator;

import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.model.MapperInfo;
import com.sankuai.lx.idea.plugin.db.model.TableField;

/**
 * Description: UpdateFieldsGenerator
 * Author: liangxiao03
 * Create: 2016-02-24 14:26
 */
public class UpdateFieldsGenerator implements IMapperDataGenerator {

    private final String TEMPLATE = "\t\t\t<if test=\"%s!=null\">\n" +
            "                %s=#{%s},\n" +
            "            </if>\n";

    @Override
    public String matcherName() {
        return "|updateFields|";
    }

    @Override
    public String genMapperData(CodeInfo codeInfo) {
        StringBuilder result = new StringBuilder();
        for (TableField tableField : codeInfo.getTableFields()) {
            if (!tableField.isPrimaryKey() && !tableField.isTimestamp()) {
                result.append(String.format(TEMPLATE, tableField.getPoFieldName(), tableField.getDbFieldName(), tableField.getPoFieldName()));
            }
        }
        return result.toString();
    }
}
