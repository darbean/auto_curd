package com.sankuai.lx.idea.plugin.curd.template;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.model.PojoInfo;
import com.sankuai.lx.idea.plugin.db.model.TableField;

import java.util.List;

/**
 * Description: PojoTemplate
 * Author: liangxiao03
 * Create: 2016-02-24 18:27
 */
public abstract class PojoTemplate {

    protected static final String TEMPLATE = "package %s;\n" +
            "\n" +
            "import java.sql.Timestamp;\n" +
            "\n" +
            "/**\n" +
            " * Description: %s\n" +
            " * Author: curd generator\n" +
            " * Create: 2016-02-18 11:06\n" +
            " */\n" +
            "public class %s {\n" +
            "%s" +
            "}";

    private static final String FIELD_TEMPLATE = "\tprivate %s %s;\n";
    private static final String GETTER_SETTER_TEMPLATE = "\tpublic %s get%s(){\n" +
            "        return this.%s;\n" +
            "    }\n" +
            "\n" +
            "    public void set%s(%s %s){\n" +
            "        this.%s = %s;\n" +
            "    }\n" +
            "\n";


    protected static String buildGetterAndSetter(List<TableField> tableFields) {
        StringBuilder sb = new StringBuilder();
        for (TableField tableField : tableFields) {
            List<Object> params = Lists.newArrayList();
            params.add(tableField.getType().getJavaType());
            String upper = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, tableField.getPoFieldName());
            params.add(upper);
            params.add(tableField.getPoFieldName());
            params.add(upper);
            params.add(tableField.getType().getJavaType());
            for (int i = 0; i < 3; i++) {
                params.add(tableField.getPoFieldName());
            }
            sb.append(String.format(GETTER_SETTER_TEMPLATE, params.toArray()));
        }
        return sb.toString();
    }

    protected static String buildField(List<TableField> tableFields) {
        StringBuilder sb = new StringBuilder();
        for (TableField tableField : tableFields) {
            sb.append(String.format(FIELD_TEMPLATE, tableField.getType().getJavaType(), tableField.getPoFieldName()));
        }
        return sb.toString();
    }


}
