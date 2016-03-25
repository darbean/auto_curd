package com.sankuai.lx.idea.plugin.curd.template;

import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.template.generator.IMapperDataGenerator;
import com.sankuai.lx.idea.plugin.curd.template.generator.MapperDataGeneratorFactory;
import com.sankuai.lx.idea.plugin.utils.GlobelPathContainer;

/**
 * Description: MapperTemplate
 * Author: liangxiao03
 * Create: 2016-02-24 11:52
 */
public class MapperTemplate implements CodeTemplate {

    private static final String TEMPLATE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
            "<mapper namespace=\"|rootPackage|.dao.|uppercaseTableName|Dao\">\n" +
            "\n" +
            "    <resultMap id=\"BaseResultMap\" type=\"|rootPackage|.model.po.|uppercaseTableName|PO\">\n" +
            "|resultMapFields|" +
            "    </resultMap>\n" +
            "\n" +
            "    <insert id=\"insert\">\n" +
            "        INSERT INTO |tableName|(|tableFields|)\n" +
            "        VALUES(|paramFields|)\n" +
            "    </insert>\n" +
            "    <insert id=\"batchInsert\" parameterType=\"list\">\n" +
            "        INSERT INTO |tableName|(|tableFields|)\n" +
            "        VALUES\n" +
            "        <foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">\n" +
            "            (|paramFields|)\n" +
            "        </foreach>\n" +
            "        ON DUPLICATE KEY UPDATE\n" +
            "        |batchInsertParams|\n" +
            "    </insert>\n" +
            "    <update id=\"update\">\n" +
            "        UPDATE |tableName|\n" +
            "        <trim prefix=\"SET\" suffixOverrides=\",\">\n" +
            "|updateFields|" +
            "        </trim>\n" +
            "        WHERE |updateWhereFields|\n" +
            "    </update>\n" +
            "\n" +
            "    <sql id=\"SELECT_FIELD\">\n" +
            "        |tableFields|\n" +
            "    </sql>\n" +
            "\n" +
            "    <sql id=\"QUERY_SQL\">\n" +
            "        from |tableName|\n" +
            "        <where>\n" +
            "|whereParams|\n" +
            "        </where>\n" +
            "    </sql>\n" +
            "\n" +
            "    <select id=\"query\" resultMap=\"BaseResultMap\">\n" +
            "        select <include refid=\"SELECT_FIELD\"/>\n" +
            "        <include refid=\"QUERY_SQL\"/>\n" +
            "        <if test=\"limit!=null and limit!=-1 \">\n" +
            "            <if test=\"offset!=null and offset!=-1\">\n" +
            "                limit #{offset},#{limit}\n" +
            "            </if>\n" +
            "        </if>\n" +
            "    </select>\n" +
            "\n" +
            "    <select id=\"count\" resultType=\"long\">\n" +
            "        select count(*)\n" +
            "        <include refid=\"QUERY_SQL\"/>\n" +
            "    </select>\n" +
            "\n" +
            "</mapper>";

    private String rootPackage;
    private String lowerTableName;
    private CodeInfo codeInfo;

    public MapperTemplate(CodeInfo codeInfo) {
        this.rootPackage = codeInfo.getRootPackage();
        this.lowerTableName = codeInfo.getLowercaseFirstTableName();
        this.codeInfo = codeInfo;
    }

    public String build() {

        String t = new String(TEMPLATE);
        for (IMapperDataGenerator mapperDataGenerator : MapperDataGeneratorFactory.getMapperDataGenerators()) {
            t = t.replace(mapperDataGenerator.matcherName(), mapperDataGenerator.genMapperData(codeInfo));
        }
        return t;
    }

    @Override
    public String getFilePath() {
        return GlobelPathContainer.getInstance().getMapperPath();
    }

    @Override
    public String getFileName() {
        return lowerTableName + ".xml";
    }


}
