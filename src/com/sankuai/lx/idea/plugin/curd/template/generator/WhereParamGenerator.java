package com.sankuai.lx.idea.plugin.curd.template.generator;

import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.model.FieldType;
import com.sankuai.lx.idea.plugin.curd.model.MapperInfo;
import com.sankuai.lx.idea.plugin.db.model.TableField;

/**
 * Description: WhereParamGenerator
 * Author: liangxiao03
 * Create: 2016-02-24 14:34
 */
public class WhereParamGenerator implements IMapperDataGenerator {

    private final String TEMPLATE = "\t\t\t<if test=\"%s!=null%s\">\n" +
            "               AND %s=#{%s}\n" +
            "            </if>\n";
    private final String EXTRA_TEMPLATE = " and %s!=-1";

    private final String INTEGER_TYPE = "Integer";
    private final String BIGINT_TYPE = "Long";

    @Override
    public String matcherName() {
        return "|whereParams|";
    }

    @Override
    public String genMapperData(CodeInfo codeInfo) {
        StringBuilder result = new StringBuilder();
        for (TableField tableField : codeInfo.getTableFields()) {
            String extra = "";
            if (isNumber(tableField.getType())) {
                extra = String.format(EXTRA_TEMPLATE, tableField.getPoFieldName());
            }
            result.append(String.format(TEMPLATE, tableField.getPoFieldName(), extra,
                    tableField.getDbFieldName(), tableField.getPoFieldName()));
        }
        return result.toString();
    }

    private boolean isNumber(FieldType type) {
        return INTEGER_TYPE.equalsIgnoreCase(type.getJavaType()) || BIGINT_TYPE.equalsIgnoreCase(type.getJavaType());
    }
}
