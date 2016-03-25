package com.sankuai.lx.idea.plugin.curd.template;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.model.DaoInfo;
import com.sankuai.lx.idea.plugin.utils.GlobelPathContainer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Description: DaoTemplate
 * Author: liangxiao03
 * Create: 2016-02-24 11:08
 */
public class DaoTemplate implements CodeTemplate {

    private  final String TEMPLATE = "package %s.dao;\n" +
            "\n" +
            "import %s.model.po.%sPO;\n" +
            "import %s.model.query.%sQuery;\n" +
            "import org.springframework.stereotype.Repository;\n" +
            "\n" +
            "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * Description: %sDao\n" +
            " * Author: curd generator\n" +
            " * Create: 2016-02-18 11:45\n" +
            " */\n" +
            "@Repository\n" +
            "public interface %sDao {\n" +
            "\n" +
            "    int insert(%sPO po);\n" +
            "\n" +
            "    int update(%sPO po);\n" +
            "\n" +
            "    List<%sPO> query(%sQuery query);\n" +
            "\n" +
            "    long count(%sQuery query);\n" +
            "\n" +
            "}";

    private String rootPackage;
    private String upperTableName;
    private CodeInfo codeInfo;

    public DaoTemplate(CodeInfo codeInfo) {
        rootPackage = codeInfo.getRootPackage();
        upperTableName = codeInfo.getUppercaseFirstTableName();
        this.codeInfo = codeInfo;
    }

    public String build() {
        List<Object> params = Lists.newArrayListWithExpectedSize(9);

        for (int i = 0; i < 2; i++) {
            params.add(codeInfo.getRootPackage());
        }
        params.add(codeInfo.getUppercaseFirstTableName());
        params.add(codeInfo.getRootPackage());
        params.add(codeInfo.getUppercaseFirstTableName());
        for (int i = 0; i < 7; i++) {
            params.add(codeInfo.getUppercaseFirstTableName());
        }
        String code = String.format(TEMPLATE, params.toArray());
//        try {
//            String path = GlobelPathContainer.getInstance().getRealPath(getFilePath(codeInfo.getRootPackage()));
//            Files.createParentDirs(new File(path));
//            String filePath = path + "/" + getFileName(codeInfo.getUppercaseFirstTableName());
//            File file = new File(filePath);
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            Files.write(code, file, Charset.forName("utf8"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return code;
    }

    public String getFilePath() {
        return GlobelPathContainer.getInstance().getRealPath(rootPackage + ".dao");
    }

    public String getFileName() {
        return upperTableName + "Dao.java";

    }
}
