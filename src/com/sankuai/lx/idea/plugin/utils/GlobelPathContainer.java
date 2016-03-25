package com.sankuai.lx.idea.plugin.utils;

import com.google.common.base.Splitter;

import java.util.List;

/**
 * Description: GlobelContainer
 * Author: liangxiao03
 * Create: 2016-03-22 14:04
 */
public class GlobelPathContainer {

    private String baseProjectPath;
    private String rootPackage;
    private static GlobelPathContainer instance = new GlobelPathContainer();

    private GlobelPathContainer() {

    }

    public static GlobelPathContainer getInstance() {
        return instance;
    }


    private final Splitter SPLITTER = Splitter.on(".");


    public String getBaseProjectPath() {
        return baseProjectPath;
    }

    public String getRootPackage() {
        return rootPackage;
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }

    public void setBaseProjectPath(String baseProjectPath) {
        this.baseProjectPath = baseProjectPath;
    }

    public String getCodePath() {
        return baseProjectPath + "/src/main/java/";
    }

    public String getMapperPath() {
        return baseProjectPath + "/src/main/resources/mapper";
    }

    public String getRealPath(String packageName) {
        List<String> dirs = SPLITTER.splitToList(packageName);

        StringBuilder sb = new StringBuilder(getCodePath());
        for (String dir : dirs) {
            sb.append(dir).append("/");
        }
        return sb.toString();
    }

}
