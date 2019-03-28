package com.test.servlet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
//import java.sql.ResultSet;
//import java.sql.SQLException;
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
  
        
        
        
        DataOutputStream dos = new DataOutputStream(response.getOutputStream());
       // getOutputStream().write(xxx.getBytes("UTF-8"));
        HashMap<String, Object> params = getParamsFromRequest(request);
        String result = handleNewUser(params);
        dos.writeUTF(result);
    }

    private String handleNewUser(HashMap<String, Object> params) throws UnsupportedEncodingException {
      
    	
    	
    	
    	
        HashMap<String, Object> result = new HashMap<String, Object>();
          
            
            String username = (String) params.get(DBManager.COLUMN_USERNAME);   	
           
            DBManager db = new DBManager();
           
            String temperature = (String) params.get(DBManager.COLUMN_TEMPERATURE);
            String weight = (String) params.get(DBManager.COLUMN_WEIGHT);
            String heartbeat = (String) params.get(DBManager.COLUMN_HEARTBEAT);
            String systolicPressure= (String) params.get(DBManager.COLUMN_SYSTOLICPRESSURE);
            String diastolicPressure= (String) params.get(DBManager.COLUMN_DIASTOLICPRESSURE);  
            String bloodFat = (String) params.get(DBManager.COLUMN_BLOODFAT);
           
            
         
            
            String sql = "Insert into " + DBManager.TABLE_NAME + " (username, temperature,weight,heartbeat,systolicPressure,diastolicPressure,bloodFat) "+" values (" 
                    + "'" + username + "',"
                    
                    + "'" + temperature + "',"
                    + "'" + weight + "',"+
                      "'" +  heartbeat + "',"
                    + "'" + systolicPressure + "',"
                    + "'" + diastolicPressure + "',"
                    + "'" + bloodFat + "')" ;
            
            
            System.out.println("sql = " + sql);
            int executeResult = db.update(sql);
            
            if(executeResult == 0) {
                result.put("result_code", 2);
            } else {
                result.put("result_code", 0);
            }
        
        return (new Gson()).toJson(result);
    }


    HashMap<String, Object> getParamsFromRequest(HttpServletRequest request) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put(DBManager.COLUMN_USERNAME, request.getParameter(DBManager.COLUMN_USERNAME));
        params.put(DBManager.COLUMN_TEMPERATURE, request.getParameter(DBManager.COLUMN_TEMPERATURE));
        params.put(DBManager.COLUMN_WEIGHT, request.getParameter(DBManager.COLUMN_WEIGHT));
        params.put(DBManager.COLUMN_HEARTBEAT, request.getParameter(DBManager.COLUMN_HEARTBEAT));
        params.put(DBManager.COLUMN_SYSTOLICPRESSURE, request.getParameter(DBManager.COLUMN_SYSTOLICPRESSURE));
        params.put(DBManager.COLUMN_DIASTOLICPRESSURE, request.getParameter(DBManager.COLUMN_DIASTOLICPRESSURE));
        params.put(DBManager.COLUMN_BLOODFAT, request.getParameter(DBManager.COLUMN_BLOODFAT));
        return params;
    }

//    private boolean isUsernameExsited(String name) {
//        boolean isExisted = true;
//        DBManager db = new DBManager();
//        String sql = "select * from " + DBManager.TABLE_NAME + " where " + DBManager.COLUMN_USERNAME + " = " + "'" + name + "'" ;
//        ResultSet rst = db.query(sql);
//        try {
//            rst.next();
//       
//		@SuppressWarnings("unused")
//		String username = rst.getString(DBManager.COLUMN_USERNAME);
//            isExisted = true;
//        } catch (SQLException e) {
//            isExisted = false;
//            e.printStackTrace();
//        }
//        return isExisted;
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	resp.setContentType("text/html; charset=UTF-8" );
        req.setCharacterEncoding("UTF-8");
    	
        doGet(req, resp);
    }
}