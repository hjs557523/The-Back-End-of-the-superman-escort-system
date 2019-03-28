package com.test.database;
//import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Object;
import com.mysql.jdbc.Connection;

public class DBManager {

    public final static String TABLE_NAME = "login_info";

    public final static String COLUMN_USERNAME = "username";
    public final static String COLUMN_PASSWORD = "password";
    public final static String COLUMN_GENDER = "gender";
    public final static String COLUMN_AGE = "age";
    public final static String COLUMN_PHONE = "phone";
    public final static String COLUMN_EMAIL = "email";

    public Statement getStatement() {
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/RUNOOB?useUnicode=true&characterEncoding=UTF-8", "root", "root");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return stmt;
    }

    public ArrayList<HashMap<String, Object>> getDatabaseContents() {
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = null;
        String sql = "select * from " + TABLE_NAME;
        Statement stmt = getStatement();
        ResultSet rst = null;
        try {
            rst = stmt.executeQuery(sql);
            if(rst != null) {
                while(rst.next()) {
                    map = new HashMap<String, Object>();
                    map.put(COLUMN_USERNAME, rst.getString(COLUMN_USERNAME));
                    map.put(COLUMN_PASSWORD, rst.getString(COLUMN_PASSWORD));
                    map.put(COLUMN_GENDER, rst.getString(COLUMN_GENDER));
                    map.put(COLUMN_AGE, rst.getInt(COLUMN_AGE));
                    map.put(COLUMN_PHONE, rst.getString(COLUMN_PHONE));
                    map.put(COLUMN_EMAIL, rst.getString(COLUMN_EMAIL));
                    list.add(map);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

    public ResultSet query(String sql) {
        ResultSet rst = null;
        Statement stmt = getStatement();
        System.out.println("stmt = " + stmt);
        try {
            rst = stmt.executeQuery(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rst;
    }

    public int update(String sql) {
        Statement stmt = getStatement();
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
