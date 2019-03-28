package com.test.servlet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.test.database.DBManager;

public class NewAccount extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8" );
          request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/json");  /*设置字符集为’UTF-8’*/ 
     //     response.setCharacterEncoding("UTF-8");
//        response.setLocale(new java.util.Locale("zh", "CN"));
//        
        
        
        
        DataOutputStream dos = new DataOutputStream(response.getOutputStream());
       // getOutputStream().write(xxx.getBytes("UTF-8"));
        HashMap<String, Object> params = getParamsFromRequest(request);
        String result = handleNewUser(params);
        dos.writeUTF(result);
    }

    private String handleNewUser(HashMap<String, Object> params) throws UnsupportedEncodingException {
        /*
         *   result_code: 
         * 0 用户名不存在，可以正常注册
         * 1  用户名已存在
         * 2 数据库操作异常
         * */
        HashMap<String, Object> result = new HashMap<String, Object>();
        String username = (String) params.get(DBManager.COLUMN_USERNAME);
        if(isUsernameExsited(username)) {
            result.put("result_code", 1);
        } 
        else {
            DBManager db = new DBManager();
            String password = (String) params.get(DBManager.COLUMN_PASSWORD);
          String gender = (String) params.get(DBManager.COLUMN_GENDER);
         
            ///String gender = (String)(params.get(DBManager.COLUMN_GENDER).getBytes("ISO8859-1"),"UTF-8");
//			try {
//				gender = new String(gender.getBytes("ISO8859-1"), "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
            String age = (String) params.get(DBManager.COLUMN_AGE);
            String phone = (String) params.get(DBManager.COLUMN_PHONE);
            String email = (String) params.get(DBManager.COLUMN_EMAIL);
            String sql = "Insert into " + DBManager.TABLE_NAME + " values (" 
                    + "'" + username + "',"
                    + "'" + password + "',"
                    + "'" + gender + "',"
                     +  age + ","
                    + "'" + phone + "',"
                    + "'" + email + "')";
            System.out.println("sql = " + sql);
            int executeResult = db.update(sql);
            
            if(executeResult == 0) {
                result.put("result_code", 2);
            } else {
                result.put("result_code", 0);
            }
        }
        return (new Gson()).toJson(result);
    }

    HashMap<String, Object> getParamsFromRequest(HttpServletRequest request) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put(DBManager.COLUMN_USERNAME, request.getParameter(DBManager.COLUMN_USERNAME));
        params.put(DBManager.COLUMN_PASSWORD, request.getParameter(DBManager.COLUMN_PASSWORD));
        params.put(DBManager.COLUMN_GENDER, request.getParameter(DBManager.COLUMN_GENDER));
        params.put(DBManager.COLUMN_AGE, request.getParameter(DBManager.COLUMN_AGE));
        params.put(DBManager.COLUMN_PHONE, request.getParameter(DBManager.COLUMN_PHONE));
        params.put(DBManager.COLUMN_EMAIL, request.getParameter(DBManager.COLUMN_EMAIL));
        return params;
    }

    private boolean isUsernameExsited(String name) {
        boolean isExisted = true;
        DBManager db = new DBManager();
        String sql = "select * from " + DBManager.TABLE_NAME + " where " + DBManager.COLUMN_USERNAME + " = " + "'" + name + "'" ;
        ResultSet rst = db.query(sql);
        try {
            rst.next();
       
		@SuppressWarnings("unused")
		String username = rst.getString(DBManager.COLUMN_USERNAME);
            isExisted = true;
        } catch (SQLException e) {
            isExisted = false;
            e.printStackTrace();
        }
        return isExisted;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	resp.setContentType("text/html; charset=UTF-8" );
        req.setCharacterEncoding("UTF-8");
    	
        doGet(req, resp);
    }
}