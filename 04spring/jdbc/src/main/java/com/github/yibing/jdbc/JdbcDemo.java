package com.github.yibing.jdbc;

import com.github.yibing.jdbc.domain.Student;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

public class JdbcDemo {
    private static Connection connection = null;

    public static void main(String[] args) {

    }

    // 查询
    @Test
    public void queryStudent() {
        String sql = "select * from student";
        connection = DBUtils.getConnection();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("id = " + resultSet.getString("id"));
                System.out.println("name = " + resultSet.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeConnection(connection, statement, resultSet);
        }
    }
    // 插入
    @Test
    public void insertOne() {
        Student student = new Student(3, "李华");
        Connection connection = DBUtils.getConnection();
        PreparedStatement psmt = null;
        try {
            psmt = connection.prepareStatement("insert into student values(?,?)");
            psmt.setInt(1, student.getId());
            psmt.setString(2, student.getName());
            int i = psmt.executeUpdate();
            if (i > 0) {
                System.out.println("成功----" + i);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeConnection(connection, psmt);
        }

    }
    // 删除一个
    @Test
    public void deleteOne() {
        Connection connection = DBUtils.getConnection();
        PreparedStatement psmt = null;
        try {
            psmt = connection.prepareStatement("delete from student where id = ?");
            psmt.setInt(1, 3);
            int i = psmt.executeUpdate();
            if (i > 0) {
                System.out.println("删除成功--" + i + "条");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeConnection(connection, psmt);
        }
    }
    // 修改
    @Test
    public void updateOne() {
        Student student = new Student(1, "小明");
        Connection connection = DBUtils.getConnection();
        PreparedStatement psmt = null;

        try {
            psmt = connection.prepareStatement("update student set name = ? where id = ?");
            int i = psmt.executeUpdate();
            if (i > 0) {
                System.out.println("已修改" + i + "条");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeConnection(connection, psmt);
        }

    }
    // 批量插入
    @Test
    public void batchInsert() {
        ArrayList<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Student("student" + i));
        }
        // 使用 JDBC
//        Connection connection = DBUtils.getConnection();
        // 使用 Hikari连接池
        Connection connection = DBUtils.getHikariConnection();
        String sql = "insert into student (name) values (?)";
        PreparedStatement psmt = null;
        try {
            psmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            for (int i = 0; i < list.size(); i++) {
                if (i == 5) {
                    psmt.setString(1, "sssssssssssssssssssssssssssssssssssssssssssssssssssssss");
                } else {
                    psmt.setString(1, list.get(i).getName());
                }
                psmt.executeUpdate();
            }
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DBUtils.closeConnection(connection, psmt);
        }
    }
}
