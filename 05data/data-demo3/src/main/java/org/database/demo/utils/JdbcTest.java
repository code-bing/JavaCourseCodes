package org.database.demo.utils;

import java.sql.*;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.IntStream;

public class JdbcTest {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3307/sharding_db?useUnicode=true&characterEncoding=utf-8&useServerPrepStmts=true&cachePrepStmts=true";
    private static String username = "root";
    private static String password = "root";

    public static void insertUsePrepare() throws SQLException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String sql = "insert into t_order (user_id,commodities,status,deliver_status,total_price,create_time,update_time)" +
                    "values (";
            String[] strings = UUID.randomUUID().toString().split("-");
            sql = sql+(i)+",'"+strings[0]+"',"+(i+1)+",'"+strings[2]+"',"+(i+2)+","+(i+3)+","+(i+4)+")";
            Objects.requireNonNull(getConn()).createStatement().execute(sql);
        }
        long end = System.currentTimeMillis();
        System.out.println("方式一耗时：" + (end-start));
    }

    public static void insertUsePrepareBatch() throws SQLException {
        long start = System.currentTimeMillis();
        String sql = "insert into orders (user_id,commodities,status,deliver_status,total_price,create_time,update_time)" +
                "values (?,?,?,?,?,?,?)";

        PreparedStatement prepareStatement = Objects.requireNonNull(getConn()).prepareStatement(sql);
        for (int i = 0; i < 1000000; i++) {
            String[] strings = UUID.randomUUID().toString().split("-");
            prepareStatement.setInt(1,i);
            prepareStatement.setString(2,strings[0]);
            prepareStatement.setInt(3,i+1);
            prepareStatement.setString(4,strings[0]);
            prepareStatement.setInt(5,i+2);
            prepareStatement.setInt(6,i+3);
            prepareStatement.setInt(7,i+4);
            prepareStatement.addBatch();
        }
        prepareStatement.execute();
        long end = System.currentTimeMillis();
        System.out.println("方式一耗时：" + (end-start));
    }

    private static Connection getConn() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws SQLException {
        insertUsePrepare();
    }
}
