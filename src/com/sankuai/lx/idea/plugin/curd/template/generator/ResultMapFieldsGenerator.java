package com.sankuai.lx.idea.plugin.curd.template.generator;

import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.model.MapperInfo;
import com.sankuai.lx.idea.plugin.db.model.TableField;

/**
 * Description: ResultMapFieldsGenerator
 * Author: liangxiao03
 * Create: 2016-02-24 12:44
 */
public class ResultMapFieldsGenerator implements IMapperDataGenerator {

    private final String ID_TEMP = "\t\t<id column=\"%s\" property=\"%s\" jdbcType=\"BIGINT\"/>";
    private final String FIELD_TEMP = "\t\t<result column=\"%s\" property=\"%s\" jdbcType=\"%s\"/>";
    private final String LINE_SEPERATOR = "\n";

    @Override
    public String matcherName() {
        return "|resultMapFields|";
    }

    @Override
    public String genMapperData(CodeInfo codeInfo) {
        StringBuilder result = new StringBuilder();
        TableField primaryField = codeInfo.getPrimaryField();
        result.append(String.format(ID_TEMP, primaryField.getDbFieldName(), primaryField.getPoFieldName())).append(LINE_SEPERATOR);
        for (TableField tableField : codeInfo.getTableFields()) {
            if (!tableField.isPrimaryKey()) {
                result.append(String.format(FIELD_TEMP, tableField.getDbFieldName(), tableField.getPoFieldName(), tableField.getType().getJdbcType()))
                      .append(LINE_SEPERATOR);
            }
        }

        return result.toString();
    }
}
