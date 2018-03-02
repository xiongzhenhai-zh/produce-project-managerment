package com.carejava.core.build.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 熊振海    zhenhai.xiong@mi-me.com
 * @version [V1.0,  2017年11月03日]
 */
public class DBContentFacatory {

    private final static Logger LOGGER = LoggerFactory.getLogger(DBContentFacatory.class);

    private Connection conn;

    Connection getConnection(String url, String username, String password){
        if (conn == null){
            createContent(url,username,password);
        }
        return conn;
    }

    private void createContent(String url , String username, String password){
        try {
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            LOGGER.info("成功加载MySQL驱动！");
        } catch ( ClassNotFoundException e1) {
            LOGGER.error("找不到MySQL驱动!");
            e1.printStackTrace();
        }
        //调用DriverManager对象的getConnection()方法，获得一个Connection对象
        try {
            conn = DriverManager.getConnection(url, username, password);
            LOGGER.info("成功连接到数据库！");
        } catch (SQLException e) {
            LOGGER.info("连接数据库失败");
            e.printStackTrace();
        }
    }

}
