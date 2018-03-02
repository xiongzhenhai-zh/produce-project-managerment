package com.carejava.core.build.utils;

import com.carejava.core.build.model.Model;
import com.carejava.core.build.model.ModelAttribute;
import com.carejava.core.build.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 熊振海    zhenhai.xiong@mi-me.com
 * @version [V1.0,  2017年11月03日]
 */
public class InitModelFromDBUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(InitModelFromDBUtil.class);

    public static void init(Project project, List<String> tableNames) {
        DBContentFacatory dbContentFacatory = new DBContentFacatory();
        String url = project.getDataSource().getUrl();
        String db = getDbFromUrl(url);
        project.setDatabaseName(db);
        Connection connection = dbContentFacatory.getConnection(url, project.getDataSource().getUsername(), project.getDataSource().getPassword());
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String model = "SELECT TABLE_NAME,TABLE_COMMENT FROM information_schema.`TABLES` WHERE TABLE_SCHEMA='" + db + "'";
            if (tableNames != null && tableNames.size() > 0) {
                StringBuilder buff = new StringBuilder();
                for (int i = 0; i < tableNames.size(); i++) {
                    if (i == tableNames.size() - 1) {
                        buff.append("'" + tableNames.get(i) + "'");
                    } else {
                        buff.append("'" + tableNames.get(i) + "',");
                    }
                }
                model += " AND TABLE_NAME in (" + buff.toString() + ")";
            }
            model += ";";    //要执行的SQL
            ResultSet rs = stmt.executeQuery(model);//创建数据对象
            List<Model> models = covertModel(rs, project);
            String attribute = "select TABLE_NAME,COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT,COLUMN_KEY from information_schema.COLUMNS where table_schema = '" + db + "';";    //要执行的SQL
            Statement stmt2 = connection.createStatement();
            ResultSet attributes = stmt2.executeQuery(attribute);//创建数据对象
            models = covertAttribure(models, attributes);
            project.setModels(models);
            stmt.close();
            stmt2.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("数据库错误e={}", e);
            throw new RuntimeException("数据库错误");
        }
    }

    private static String getDbFromUrl(String url) {
        String db = url.substring(0, url.indexOf('?'));
        return db.substring(db.lastIndexOf("/") + 1, db.length());
    }

    private static List<Model> covertAttribure(List<Model> models, ResultSet attributes) throws SQLException {
        while (attributes.next()) {
            for (Model model : models) {
                if (model.getDbName().equalsIgnoreCase(attributes.getString(1))) {
                    String type = Util.covertDbTypeToJava(attributes.getString(3));
                    model.addAttribute(new ModelAttribute(Util.getId(), Util.toLowerCase(attributes.getString(2)), attributes.getString(4), type, attributes.getString(2), "PRI".equals(attributes.getString(5))));
                }
            }
        }
        return models;
    }

    private static List<Model> covertModel(ResultSet rs, Project project) throws SQLException {
        List<Model> models = new ArrayList<Model>();
        while (rs.next()) {
            Model model = new Model(Util.getId(), Util.toUpperCaseFirstOne(Util.toLowerCase(rs.getString(1))), rs.getString(2), "uuid", rs.getString(1));
            models.add(model);
        }
        return models;
    }
}
