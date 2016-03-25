package com.sankuai.lx.idea.plugin.db;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.sankuai.lx.idea.plugin.curd.model.*;
import com.sankuai.lx.idea.plugin.curd.template.DaoTemplate;
import com.sankuai.lx.idea.plugin.curd.template.MapperTemplate;
import com.sankuai.lx.idea.plugin.curd.template.PoTemplate;
import com.sankuai.lx.idea.plugin.curd.template.QueryTemplate;
import com.sankuai.lx.idea.plugin.db.model.TableField;
import com.sankuai.lx.idea.plugin.db.model.TableInfo;

import java.sql.*;
import java.util.List;

/**
 * Description: DBService
 * Author: liangxiao03
 * Create: 2016-02-24 15:50
 */
public class DBService {

    private static Connection connection;
    private static volatile DBService instance;
    private static final String URL_TEMPLATE = "jdbc:mysql://%s:%s/%s";


    private DBService(String host, int port, String username, String pwd, String db) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(String.format(URL_TEMPLATE, host, port, db), username, pwd);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DBService getInstance(String host, int port, String username, String pwd, String db) {
        if (instance == null) {
            synchronized (DBService.class) {
                if (instance == null) {
                    instance = new DBService(host, port, username, pwd, db);
                }
            }
        }
        return instance;
    }

    public static DBService getInstance() {
        if (instance == null) {
            throw new RuntimeException("dbservice has not been instance!");
        }
        return instance;
    }

    public List<String> getTables() throws SQLException {
        List<String> tables = Lists.newArrayList();

        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(null, "%", "%", new String[]{"TABLE"});
        while (resultSet.next()) {
            tables.add(resultSet.getString("TABLE_NAME"));
        }
        return tables;
    }

    public List<TableInfo> getTableInfo(List<String> tables) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        String catalog = connection.getCatalog();
        List<TableInfo> tableInfos = Lists.newArrayListWithExpectedSize(tables.size());
        for (String table : tables) {
            List<TableField> tableFields = Lists.newArrayList();

            ResultSet primaryKeyResultSet = metaData.getPrimaryKeys(catalog, null, table);
            String primaryKeyColumnName = "";
            while (primaryKeyResultSet.next()) {
                primaryKeyColumnName = primaryKeyResultSet.getString("COLUMN_NAME");
            }
            ResultSet colRet = metaData.getColumns(null, "%", table, "%");
            while (colRet.next()) {
                String columnName = colRet.getString("COLUMN_NAME");
                String columnType = colRet.getString("TYPE_NAME");
                FieldType type = FieldType.fromDBType(columnType);
                tableFields.add(new TableField(columnName, toLowercaseCamel(columnName),
                        type, primaryKeyColumnName.equalsIgnoreCase(columnName), isTimestamp(type)));
            }
            tableInfos.add(new TableInfo(table, tableFields));
        }
        return tableInfos;
    }

    private String toLowercaseCamel(String s) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, s.toLowerCase());
    }

    private boolean isTimestamp(FieldType type) {
        return type == FieldType.TIMESTAMP;
    }

    public static void main(String[] args) {
        DBService dbService = DBService.getInstance("mysqldev.vip.sankuai.com", 5002, "web", "yT!O4LkfPT$3", "sc_yue_pickup");

        try {
            List<String> tables = dbService.getTables();
            List<TableInfo> tableInfo = dbService.getTableInfo(tables);
            String rootPackage = "com.sankuai.sc.yue.pickup";
            PojoInfo pojoInfo = new PojoInfo(rootPackage, tables.get(2), tableInfo.get(2).getTableFields());
            CodeInfo codeInfo = new CodeInfo(rootPackage, tables.get(0), tableInfo.get(0).getTableFields());
            PoTemplate poTemplate = new PoTemplate(codeInfo);
            QueryTemplate queryTemplate = new QueryTemplate(codeInfo);
            DaoTemplate daoTemplate = new DaoTemplate(codeInfo);
            MapperTemplate mapperTemplate = new MapperTemplate(codeInfo);
//            System.out.println(poTemplate.build(pojoInfo));
            System.out.println(queryTemplate.build());
//            System.out.println(daoTemplate.build(new DaoInfo(rootPackage, tables.get(2))));
//            System.out.println(mapperTemplate.build(new MapperInfo(rootPackage, tableInfo.get(2).getTableName(), tableInfo.get(2).getTableFields())));
            StringBuilder sb = new StringBuilder();
//            for (TableField tableField : tableInfo.get(0).getTableFields()) {
//                sb.append("@RequestParam ").append(tableField.getType().getJavaType()).append(" ").append(tableField.getDbFieldName()).append(",");
//            }
//            for (TableField tableField : tableInfo.get(0).getTableFields()) {
//                sb.append("po.set").append(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, tableField.getPoFieldName()))
//                        .append("(").append(tableField.getDbFieldName()).append(")").append(";\n");
//            }
            System.out.println(sb.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
