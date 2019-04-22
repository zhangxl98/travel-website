package com.zhangxl.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 9:39 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 数据源的工具类
 */
public class C3p0Util {

    /**
     * 创建私有静态数据源成员变量
     */
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    /**
     * 创建公有的得到数据源的方法
     *
     * @return
     */
    public static DataSource getDataSource() {

        return dataSource;
    }

    /**
     * 创建公有的得到连接对象的方法
     *
     * @return
     */
    public static Connection getConnection() {

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 创建公有的关闭资源的方法
     *
     * @param conn
     * @param stmt
     * @param rs
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {


        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建公有的关闭资源的方法
     *
     * @param conn
     * @param stmt
     */
    public static void close(Connection conn, Statement stmt) {

        close(conn, stmt, null);
    }
}
