package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
//import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.catalina.User;

import server.Account;


public class DataProcess {
	private static  ArrayList<HashMap<String, Object>> executeSQL(String sql,boolean Query) {
		String dbURL="jdbc:mysql://localhost:3306/destination?verifyServerCertificate=false&useSSL=false";
		String user="root";
		String password="77789op";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("loading");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try{
				Connection conn=DriverManager.getConnection(dbURL,user,password);
				Statement smt=conn.createStatement();
				if(Query){		
					return  resultOfQuery(smt.executeQuery(sql));
				}
				else{
					smt.execute(sql);
				}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		ArrayList<HashMap<String, Object>>answer=new ArrayList<HashMap<String, Object>>();  
		return answer;
	}
	private static  ArrayList<HashMap<String, Object>> resultOfQuery(ResultSet s) throws Exception{
		ResultSetMetaData data = s.getMetaData();  
		  
        ArrayList<HashMap<String, Object>> al = new ArrayList<HashMap<String, Object>>();  
  
        while (s.next()) {  
            HashMap<String, Object> map = new HashMap<String, Object>();  
            for (int i = 1; i <= data.getColumnCount(); i++) {// 数据库里从 1 开始  
            	//convert every row to a arraylist unit
                String c = data.getColumnName(i);  
                String v = s.getString(c);  
                //System.out.println(c + ":" + v + "\t");  
                map.put(c, v);  
            }  
            al.add(map);          
        }         
        System.out.println(al);  
        s.close();   
        return al;
	}
	public static ArrayList<HashMap<String, Object>> parseMap(Map<String, Object> dataMap){
		Object order=dataMap.get("Order");
		dataMap.remove("Order");
		if(order.equals("101")){			
			addDataItem(dataMap);
		}
		if(order.equals("102")){
			updateDataItem(dataMap);		
		}
		if(order.equals("103")){
			deleteDataItem(dataMap);		
		}
		if(order.equals("104")){
			return getDataItem(dataMap);
		}
		ArrayList<HashMap<String, Object>>answer=new ArrayList<HashMap<String, Object>>();  
		return answer;
	}
	public static ArrayList<HashMap<String, Object>> addDataItem(Map<String, Object> dataMap){
		Object tablename=dataMap.get("table_name");
		if(tablename.equals("account"))
		{
			account a=new account(dataMap);
			return executeSQL(a.getCreateDataItemSql(),false);		
		}
		ArrayList<HashMap<String, Object>>answer=new ArrayList<HashMap<String, Object>>();  
		return answer;
	}
	public static ArrayList<HashMap<String, Object>> updateDataItem(Map<String, Object> dataMap){
		Object tablename=dataMap.get("table_name");
		dataMap.remove("table_name");
		if(tablename.equals("account"))
		{
			account a=new account(dataMap);
			return executeSQL(a.getUpdateDataItemSql(),false);		
		}
		ArrayList<HashMap<String, Object>>answer=new ArrayList<HashMap<String, Object>>();  
		return answer;
	}
	public static ArrayList<HashMap<String, Object>> deleteDataItem(Map<String, Object> dataMap){
		Object tablename=dataMap.get("table_name");
		dataMap.remove("table_name");
		if(tablename.equals("account"))
		{
			account a=new account(dataMap);
			return executeSQL(a.getDeleteDataItemSql(),false);		
		}
		ArrayList<HashMap<String, Object>>answer=new ArrayList<HashMap<String, Object>>();  
		return answer;
	}
	public static ArrayList<HashMap<String, Object>> getDataItem(Map<String, Object> dataMap){
		Object tablename=dataMap.get("table_name");
		dataMap.remove("table_name");
		if(tablename.equals("account"))
		{
			account a=new account(dataMap);
			return executeSQL(a.getReadDataItemSql(),true);		
		}
		ArrayList<HashMap<String, Object>>answer=new ArrayList<HashMap<String, Object>>();  
		return answer;
	}
}
