package com.sankuai.lx.idea.plugin.curd.template;

import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.model.PojoInfo;
import com.sankuai.lx.idea.plugin.utils.GlobelPathContainer;

/**
 * Description: PoTemplate
 * Author: liangxiao03
 * Create: 2016-02-24 17:57
 */
public class PoTemplate  extends PojoTemplate implements CodeTemplate{

    private static final String PACKAGE_SUFFIX = ".model.po";
    private static final String CLASS_SUFFIX = "PO";
    private String rootPackage;
    private String className;
    private CodeInfo codeInfo;

    public PoTemplate(CodeInfo codeInfo) {
        this.rootPackage = codeInfo.getRootPackage();
        this.className = buildClassName(codeInfo);
        this.codeInfo = codeInfo;
    }

    public String build() {

        return String.format(TEMPLATE, codeInfo.getRootPackage() + PACKAGE_SUFFIX, buildClassName(codeInfo)
                , className, buildField(codeInfo.getTableFields()) + "\n" + buildGetterAndSetter(codeInfo.getTableFields()));
    }

    private static String buildClassName(CodeInfo codeInfo) {
        return codeInfo.getUppercaseFirstTableName() + CLASS_SUFFIX;
    }



    @Override
    public String getFilePath() {
        return GlobelPathContainer.getInstance().getRealPath(rootPackage + PACKAGE_SUFFIX);
    }

    @Override
    public String getFileName() {
        return className + ".java";
    }
}
