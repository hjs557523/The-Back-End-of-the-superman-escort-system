package com.test.servlet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
//import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.HashMap;
//import java.sql.ResultSetMetaData;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.test.database.DBManager;
//import net.sf.json.JSON;



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
       
        String requestcode = request.getParameter("requestcode");
        switch(requestcode)
        {
        case "1":
        	String username1= request.getParameter("username");        	
        	String result1= Query1(username1);
        	dos.writeUTF(result1);   
        	break;
        	
        
        case"2":
        	 String username2 = request.getParameter("username");         
             String result2 =Query2(username2);
             dos.writeUTF(result2);
         	break;
        case"3":        	
        	String username3 = request.getParameter("username"); 
        	String week1 = request.getParameter("week1"); 
            String result3 =Query3(username3,week1);
            dos.writeUTF(result3);
        	break;
        	
        case"4":
        	String username4 = request.getParameter("username");  
        	String month4 = request.getParameter("month1"); 
            String result4 =Query4(username4,month4);
            dos.writeUTF(result4);
        	break;
        	
        case"5":
        	String username5 = request.getParameter("username");   
        	String year5 = request.getParameter("year1");   
     	
            String result5 =Query5(username5,year5);
            dos.writeUTF(result5);
        	break;
        	
        	
        case"6":
        	
        	String username6= request.getParameter("username"); 
        	String password6= request.getParameter("password"); 
        	String gender6= request.getParameter("gender");
        	String age6= request.getParameter("age");
        	String phone6= request.getParameter("phone"); 
        	String email6= request.getParameter("email"); 
        	String result6 = Query6(username6,password6,gender6,phone6,email6,age6);
        	dos.writeUTF(result6);
        	break;
        	
        	
        case"7":
        	String username7= request.getParameter("username"); 
        	String result7 = Query7(username7);
        	dos.writeUTF(result7);
        	break;
        default:
        	dos.writeUTF("请求码错误");
        	;
        
        }
        
        
    }
    
    //1
    private String Query1(String username) {

        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        String sql = "select * from " + DBManager.TABLE_NAME + " where  " + DBManager.COLUMN_USERNAME + " = " + "'" + username + "'"
        		+"order by datetime desc limit 1";
        DBManager db = new DBManager();
        ResultSet rst = db.query(sql);
        try {
        	    resultMap.put("result_code", 0);
                rst.next();                
               
                resultMap.put(DBManager.COLUMN_USERNAME, rst.getString(DBManager.COLUMN_USERNAME));           
                resultMap.put(DBManager.COLUMN_TEMPERATURE, rst.getString(DBManager.COLUMN_TEMPERATURE));
                resultMap.put(DBManager.COLUMN_WEIGHT, rst.getString(DBManager.COLUMN_WEIGHT));
                resultMap.put(DBManager.COLUMN_HEARTBEAT, rst.getString(DBManager.COLUMN_HEARTBEAT));
                resultMap.put(DBManager.COLUMN_SYSTOLICPRESSURE, rst.getString(DBManager.COLUMN_SYSTOLICPRESSURE));
                resultMap.put(DBManager.COLUMN_DIASTOLICPRESSURE, rst.getString(DBManager.COLUMN_DIASTOLICPRESSURE));
                resultMap.put(DBManager.COLUMN_BLOODFAT, rst.getString(DBManager.COLUMN_BLOODFAT));
                
           
        } 
        catch (SQLException e) {
            resultMap.put("result_code", 2);
            e.printStackTrace();
        }
        return (new Gson()).toJson(resultMap);
    }
       
    
    
 //2   
    
    private String Query2(String username) {
      String sql = "select DAY(datetime), hour(datetime),minute(datetime),second(datetime) ,username,temperature,weight,heartbeat,systolicPressure,diastolicPressure,bloodFat from "+ DBManager.TABLE_NAME+ " where " + DBManager.COLUMN_USERNAME + " = " + "'" + username + "'"
        		+"and"+ " datetime>=curdate()";
//  	  String sql = "select * from " + DBManager.TABLE_NAME+ " where " + DBManager.COLUMN_USERNAME + " = " + "'" + username+"'";
      //  String sql = "select * from " + DBManager.TABLE_NAME;
        DBManager db = new DBManager();
        ResultSet rst = db.query(sql);     
        HashMap<String, Object> map1 = new HashMap<>();
      
        try {
        	int n=0;
        	map1.put("result_code", 0);
        	
            while (rst.next()) {
                ResultSetMetaData resultSetMetaData = rst.getMetaData();
                int length = resultSetMetaData.getColumnCount();
                
                HashMap<String, Object> map = new HashMap<>();
          //    map.put(DBManager.COLUMN_USERNAME, rst.getString(DBManager.COLUMN_USERNAME));
                for (int i = 1; i <=length; i++) {
                	
                   map.put(resultSetMetaData.getColumnLabel(i), rst.getString(i));                  
                 //  map1.put(rst.getString("username"), map); 
                	
                } 
                
                
                         
                map1.put(""+n, map);
                n++;
               
            }         
            map1.put("number", n);
        }
        
        catch (SQLException e) {
            map1.put("result_code", 2);
            e.printStackTrace();
        }
    
        return (new Gson()).toJson(map1);
    }
    	
    	
//3
    private String Query3(String username,String week) {
//        String sql = "select * from "+ DBManager.TABLE_NAME+ " where " + DBManager.COLUMN_USERNAME + " = " + "'" + username + "'"
//          		+"and"+ " DATE_SUB(CURDATE(), INTERVAL 7 DAY) <=curdaate() ";
    	String sql ="select year(datetime), MONTH(datetime),WEEK(datetime),DAY(datetime), "
    			+ "AVG(temperature),AVG(weight), AVG(heartbeat),AVG(systolicPressure), AVG(diastolicPressure),AVG(bloodFat) "
    			+ "from physical "
    			+ "group by year(datetime), MONTH(datetime),WEEK(datetime),DAY(datetime),username "
    			+ "HAVING username='"+username+"'"+" AND "+"`WEEK(datetime)`='"+week+"'";
        
        
//    	  String sql = "select * from " + DBManager.TABLE_NAME+ " where " + DBManager.COLUMN_USERNAME + " = " + "'" + username+"'";
        //  String sql = "select * from " + DBManager.TABLE_NAME;
          DBManager db = new DBManager();
          ResultSet rst = db.query(sql);     
          HashMap<String, Object> map1 = new HashMap<>();
          
          try {
          	int n=0;
          	map1.put("result_code", 3);
              while (rst.next()) {
                  ResultSetMetaData resultSetMetaData = rst.getMetaData();
                  int length = resultSetMetaData.getColumnCount();
                  HashMap<String, Object> map = new HashMap<>();
          //       map.put(DBManager.COLUMN_USERNAME, rst.getString(DBManager.COLUMN_USERNAME));
                  for (int i = 1; i <=length; i++) {
                  	
                     map.put(resultSetMetaData.getColumnLabel(i), rst.getString(i));                  
                   //  map1.put(rst.getString("username"), map); 
                  	
                  } 
                  map1.put(""+n, map);
                  n++;
              }
              
              map1.put("number", n);
          }
          
          catch (SQLException e) {
              map1.put("result_code", 2);
              e.printStackTrace();
          }
      
          return (new Gson()).toJson(map1);
      }
      	
      
    
    //4
    private String Query4(String username,String month) {
//        String sql = "select * from "+ DBManager.TABLE_NAME+ " where " + DBManager.COLUMN_USERNAME + " = " + "'" + username + "'"
//          		+"and"+ " DATE_SUB(CURDATE(), INTERVAL INTERVAL 1 MONTH) <=curdate() ";
//    	  String sql = "select * from " + DBManager.TABLE_NAME+ " where " + DBManager.COLUMN_USERNAME + " = " + "'" + username+"'";
        //  String sql = "select * from " + DBManager.TABLE_NAME;
    	
    	
    	String sql =
    		    "select year(datetime), MONTH(datetime),DAY(datetime), "
    			+ "AVG(temperature),AVG(weight), AVG(heartbeat),AVG(systolicPressure), AVG(diastolicPressure),AVG(bloodFat)"
    			+ "from physical "
    			+ "group by year(datetime), MONTH(datetime),DAY(datetime),username "
    			+ "HAVING username='"+username+"'";
    	
    	
    	DBManager db = new DBManager();
        ResultSet rst = db.query(sql);     
        HashMap<String, Object> map1 = new HashMap<>();
        
        try {
        	int n=0;
        	map1.put("result_code", 4);
            while (rst.next()) {
                ResultSetMetaData resultSetMetaData = rst.getMetaData();
                int length = resultSetMetaData.getColumnCount();
                HashMap<String, Object> map = new HashMap<>();
        //       map.put(DBManager.COLUMN_USERNAME, rst.getString(DBManager.COLUMN_USERNAME));
                for (int i = 1; i <=length; i++) {
                	
                   map.put(resultSetMetaData.getColumnLabel(i), rst.getString(i));                  
                 //  map1.put(rst.getString("username"), map); 
                	
                } 
                map1.put(""+n, map);
                n++;
            }
            
            map1.put("number", n);
        }
        
        catch (SQLException e) {
            map1.put("result_code", 2);
            e.printStackTrace();
        }
    
        return (new Gson()).toJson(map1);
    }
   //5
    private String Query5(String username,String year) {
//        String sql = "select * from "+ DBManager.TABLE_NAME+ " where " + DBManager.COLUMN_USERNAME + " = " + "'" + username + "'"
//          		+"and"+ " DATE_SUB(CURDATE(), INTERVAL INTERVAL 12 MONTH) <=curdate() ";
//    	  String sql = "select * from " + DBManager.TABLE_NAME+ " where " + DBManager.COLUMN_USERNAME + " = " + "'" + username+"'";
        //  String sql = "select * from " + DBManager.TABLE_NAME;
    	String sql ="select year(datetime),MONTH(datetime), "
    			+ "AVG(temperature),AVG(weight), AVG(heartbeat),AVG(systolicPressure), AVG(diastolicPressure),AVG(bloodFat) "
    			+ "from physical "
    			+ "group by year(datetime),MONTH(datetime), username "
    			+ "HAVING username='"+username+"'"+"AND"+"`year(datetime)`='"+year+"'";
          DBManager db = new DBManager();
          ResultSet rst = db.query(sql);     
          HashMap<String, Object> map1 = new HashMap<>();
          
          try {
          	int n=0;
          	map1.put("result_code", 5);
              while (rst.next()) {
                  ResultSetMetaData resultSetMetaData = rst.getMetaData();
                  int length = resultSetMetaData.getColumnCount();
                  HashMap<String, Object> map = new HashMap<>();
             //   map.put(DBManager.COLUMN_USERNAME, rst.getString(DBManager.COLUMN_USERNAME));
                  for (int i = 1; i <=length; i++) {
                  	
                     map.put(resultSetMetaData.getColumnLabel(i), rst.getString(i));                  
                   //  map1.put(rst.getString("username"), map); 
                  	
                  } 
                  map1.put(""+n, map);
                  n++;
              }
              
              map1.put("number", n);
          }
          
          catch (SQLException e) {
              map1.put("result_code", 2);
              e.printStackTrace();
          }
      
          return (new Gson()).toJson(map1);
      }
    
 //6  
    
    private String Query6(String username,String password,String gender,String phone,String email,String age ) {
      String sql = "update " + DBManager.TABLE_NAME1+ "  set  password="+"'"+password + "',"
    		  + " gender="+"'"+gender + "',"
    		  + " phone="+"'"+phone + "',"
    		  + " email="+"'"+email +"', age="+"'"+age + "'  where "+DBManager.COLUMN_USERNAME+" = '"+username+"'";
        		
        DBManager db = new DBManager();
      int executeResult = db.update(sql); 
      HashMap<String, Object> result = new HashMap<>();
      
      if(executeResult == 0) {
          result.put("result_code", 2);
      } else {
          result.put("result_code", 0);
      }
  
  return (new Gson()).toJson(result);
    } 
   
  //7
  private String Query7(String username) {
    
      HashMap<String, Object> resultMap = new HashMap<String, Object>();
      String sql = "select * from " + DBManager.TABLE_NAME1 + " where " + DBManager.COLUMN_USERNAME + " = " + "'" + username + "'" ;
      

      System.out.println("url = " + sql);
      DBManager db = new DBManager();
      ResultSet rst = db.query(sql);
      try {
    	      resultMap.put("result_code", 0);
              rst.next();    
              resultMap.put("result_code", 0);
              resultMap.put(DBManager.COLUMN_USERNAME, rst.getString(DBManager.COLUMN_USERNAME));   
              resultMap.put(DBManager.COLUMN_PASSWORD, rst.getString(DBManager.COLUMN_PASSWORD)); 
              resultMap.put(DBManager.COLUMN_GENDER, rst.getString(DBManager.COLUMN_GENDER));
              resultMap.put(DBManager.COLUMN_AGE, rst.getString(DBManager.COLUMN_AGE));
              resultMap.put(DBManager.COLUMN_PHONE, rst.getString(DBManager.COLUMN_PHONE));
              resultMap.put(DBManager.COLUMN_EMAIL, rst.getString(DBManager.COLUMN_EMAIL));
                
          
      } 
  catch (SQLException e) {
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

