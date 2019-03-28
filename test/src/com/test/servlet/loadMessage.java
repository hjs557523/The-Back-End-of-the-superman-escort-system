package com.test.servlet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;

import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.test.database.DBManager;

public class loadMessage extends HttpServlet {
    private final static long serialVersionUID = 1L;
    public loadMessage() {
        super();
    }
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8" );
        DataOutputStream dos = new DataOutputStream(response.getOutputStream());
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String result = doLogin(username, password);
        System.out.println("result = " + result);
        dos.writeUTF(result);
    }
    private String doLogin(String username,
            String password) {
        /*
         * login_result:
         * -1£ºµÇÂ½Ê§°Ü£¬Î´Öª´íÎó£¡
         * 0: µÇÂ½³É¹¦£¡
         * 1£ºµÇÂ½Ê§°Ü£¬ÓÃ»§Ãû»òÃÜÂë´íÎó£¡
         * 2£ºµÇÂ½Ê§°Ü£¬ÓÃ»§Ãû²»´æÔÚ£¡
         * */
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        String sql = "select * from " + DBManager.TABLE_NAME + " where " + DBManager.COLUMN_USERNAME + " = " + "'" + username + "'" ;
        

        System.out.println("url = " + sql);
        DBManager db = new DBManager();
        ResultSet rst = db.query(sql);
        try {
            rst.next();
            String pwd = rst.getString(DBManager.COLUMN_PASSWORD);
            if(!password.equals(pwd)) {
                resultMap.put("result_code", 1);
            } else {
                resultMap.put("result_code", 0);
                resultMap.put(DBManager.COLUMN_USERNAME, rst.getString(DBManager.COLUMN_USERNAME));
                
                resultMap.put(DBManager.COLUMN_GENDER, rst.getString(DBManager.COLUMN_GENDER));
                resultMap.put(DBManager.COLUMN_AGE, rst.getInt(DBManager.COLUMN_AGE));
                resultMap.put(DBManager.COLUMN_PHONE, rst.getString(DBManager.COLUMN_PHONE));
                resultMap.put(DBManager.COLUMN_EMAIL, rst.getString(DBManager.COLUMN_EMAIL));
            }
        } catch (SQLException e) {
            resultMap.put("result_code", 2);
            e.printStackTrace();
        }
        return (new Gson()).toJson(resultMap);
    }
       
    
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);

    }
    public void init() throws ServletException {
        // Put your code here
    }

}


//public ArrayList<HashMap<String, Object>> getDatabaseContents() {
//    ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
//    HashMap<String, Object> map = null;
//    String sql = "select * from " + TABLE_NAME;
//    Statement stmt = getStatement();
//    ResultSet rst = null;
//    try {
//        rst = stmt.executeQuery(sql);
//        if(rst != null) {
//            while(rst.next()) {
//                map = new HashMap<String, Object>();
//                map.put(COLUMN_USERNAME, rst.getString(COLUMN_USERNAME));
//                map.put(COLUMN_PASSWORD, rst.getString(COLUMN_PASSWORD));
//                map.put(COLUMN_GENDER, rst.getString(COLUMN_GENDER));
//                map.put(COLUMN_AGE, rst.getInt(COLUMN_AGE));
//                map.put(COLUMN_PHONE, rst.getString(COLUMN_PHONE));
//                map.put(COLUMN_EMAIL, rst.getString(COLUMN_EMAIL));
//                list.add(map);
//            }
//        }
//    } catch (SQLException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//    }
//
//    return list;
//}

