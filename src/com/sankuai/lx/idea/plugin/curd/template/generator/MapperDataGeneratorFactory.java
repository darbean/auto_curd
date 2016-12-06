package com.sankuai.lx.idea.plugin.curd.template.generator;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Description: MapperDataGeneratorFactory
 * Author: liangxiao03
 * Create: 2016-02-24 15:06
 */
public class MapperDataGeneratorFactory {

    private static final List<IMapperDataGenerator> mapperDataGenerators = Lists.newArrayList();

    static {
        mapperDataGenerators.add(new BatchInsertParamsGenerator());
        mapperDataGenerators.add(new ParamFieldsGenerator());
        mapperDataGenerators.add(new ItemParamFieldsGenerator());
        mapperDataGenerators.add(new ResultMapFieldsGenerator());
        mapperDataGenerators.add(new RootPackageGenerator());
        mapperDataGenerators.add(new TableFieldsGenerator());
        mapperDataGenerators.add(new TableNameGenerator());
        mapperDataGenerators.add(new UpdateFieldsGenerator());
        mapperDataGenerators.add(new UpdateWhereFieldsGenerator());
        mapperDataGenerators.add(new UppercaseTableNameGenerator());
        mapperDataGenerators.add(new WhereParamGenerator());
    }

    public static List<IMapperDataGenerator> getMapperDataGenerators() {
        return Lists.newArrayList(mapperDataGenerators);
    }

}
