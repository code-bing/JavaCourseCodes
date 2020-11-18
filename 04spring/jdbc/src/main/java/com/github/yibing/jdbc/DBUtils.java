package com.github.yibing.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class DBUtils {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/student?&serverTimezone=Asia/Shanghai";
    private static final String username = "root";
    private static final String password = "123456";
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getHikariConnection() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        HikariDataSource dataSource = new HikariDataSource(config);
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection, Statement statement) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
