package db;
/*Author:Maugham(刘瑞琦)，张治隆，沈梦烨
 * Time:2018-4-3
 * 
 * */
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


public class DataProcess {
	private static  ArrayList<HashMap<String, Object>> executeSQL(String sql,boolean Query) {
		String dbURL="jdbc:mysql://106.14.151.245:3306/destination?verifyServerCertificate=false&useSSL=false";
		String user="destination";
		String password="admin";
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
		if(order.equals("105")){
			return executeSQL(getReadDataItemWithFilterConditionSql(dataMap),true);
		}
		if(order.equals("106")){
			return getAverageData(dataMap);
		}
		ArrayList<HashMap<String, Object>>answer=new ArrayList<HashMap<String, Object>>();  
		return answer;
	}
	public static String getReadDataItemWithFilterConditionSql(Map<String, Object> map){
		return "select * from "+map.get("table_name")+" where "+map.get("Filtercondition")+";";
	}
	
	
	
	
	public static ArrayList<HashMap<String, Object>> addDataItem(Map<String, Object> dataMap){
		Object tablename=dataMap.get("table_name");
		if(tablename.equals("AccountInfo"))
		{
			account a=new account(dataMap);
			return executeSQL(a.getCreateDataItemSql(),false);		
		}
		if(tablename.equals("AccountSportsRecord")){
			AccountSportsRecord accountSportsRecord=new AccountSportsRecord(dataMap);
			return executeSQL(accountSportsRecord.getCreateDataItemSql(),false);
		}
		if(tablename.equals("picture")){
			picture a=new picture(dataMap);
			return a.savePicture();
		}
		if(tablename.equals("Friendship"))
		{
			friendship a = new friendship(dataMap);
			return executeSQL(a.getCreateDataItemSql(),false);		
		}
		if(tablename.equals("Groupship"))
		{
			groupship a=new groupship(dataMap);
			return executeSQL(a.getCreateDataItemSql(),false);		
		}
		if(tablename.equals("StudentInfo"))
		{
			studentInfo a=new studentInfo(dataMap);
			return executeSQL(a.getCreateDataItemSql(),false);		
		}
		if(tablename.equals("TeacherInfo"))
		{
			teacherInfo a=new teacherInfo(dataMap);
			return executeSQL(a.getCreateDataItemSql(),false);		
		}
		if(tablename.equals("UniversityRule"))
		{
			UniversityRule rule=new UniversityRule(dataMap);
			return executeSQL(rule.getCreateDataItemSql(),false);		
		}
		if(tablename.equals("StudentSportsRecord"))
		{
			StudentSportsRecord rule=new StudentSportsRecord(dataMap);
			return executeSQL(rule.getCreateDataItemSql(),false);		
		}
		ArrayList<HashMap<String, Object>>answer=new ArrayList<HashMap<String, Object>>();  
		return answer;
	}
	public static ArrayList<HashMap<String, Object>> updateDataItem(Map<String, Object> dataMap){
		Object tablename=dataMap.get("table_name");
		dataMap.remove("table_name");
		if(tablename.equals("AccountInfo"))
		{
			account a=new account(dataMap);
			return executeSQL(a.getUpdateDataItemSql(),false);		
		}
		if(tablename.equals("AccountSportsRecord")){
			AccountSportsRecord accountSportsRecord=new AccountSportsRecord(dataMap);
			return executeSQL(accountSportsRecord.getUpdateDataItemSql(),false);
		}
		if(tablename.equals("Friendship"))
		{
			friendship a=new friendship(dataMap);
			return executeSQL(a.getUpdateDataItemSql(),false);		
		}
		if(tablename.equals("Groupship"))
		{
			groupship a=new groupship(dataMap);
			return executeSQL(a.getUpdateDataItemSql(),false);		
		}
		if(tablename.equals("StudentInfo"))
		{
			studentInfo a=new studentInfo(dataMap);
			return executeSQL(a.getUpdateDataItemSql(),false);		
		}
		if(tablename.equals("TeacherInfo"))
		{
			teacherInfo a=new teacherInfo(dataMap);
			return executeSQL(a.getUpdateDataItemSql(),false);		
		}
		if(tablename.equals("UniversityRule"))
		{
			UniversityRule rule=new UniversityRule(dataMap);
			return executeSQL(rule.getUpdateDataItemSql(),false);		
		}
		if(tablename.equals("StudentSportsRecord"))
		{
			StudentSportsRecord rule=new StudentSportsRecord(dataMap);
			return executeSQL(rule.getUpdateDataItemSql(),false);		
		}
		ArrayList<HashMap<String, Object>>answer=new ArrayList<HashMap<String, Object>>();  
		return answer;
	}
	public static ArrayList<HashMap<String, Object>> deleteDataItem(Map<String, Object> dataMap){
		Object tablename=dataMap.get("table_name");
		dataMap.remove("table_name");
		if(tablename.equals("AccountInfo"))
		{
			account a=new account(dataMap);
			return executeSQL(a.getDeleteDataItemSql(),false);		
		}
		if(tablename.equals("AccountSportsRecord")){
			AccountSportsRecord accountSportsRecord=new AccountSportsRecord(dataMap);
			return executeSQL(accountSportsRecord.getDeleteDataItemSql(),false);
		}
		if(tablename.equals("Friendship"))
		{
			friendship a=new friendship(dataMap);
			return executeSQL(a.getDeleteDataItemSql(),false);		
		}
		if(tablename.equals("Groupship"))
		{
			groupship a=new groupship(dataMap);
			return executeSQL(a.getDeleteDataItemSql(),false);		
		}
		if(tablename.equals("StudentInfo"))
		{
			studentInfo a=new studentInfo(dataMap);
			return executeSQL(a.getDeleteDataItemSql(),false);		
		}
		if(tablename.equals("TeacherInfo"))
		{
			teacherInfo a=new teacherInfo(dataMap);
			return executeSQL(a.getDeleteDataItemSql(),false);		
		}
		if(tablename.equals("UniversityRule"))
		{
			UniversityRule rule=new UniversityRule(dataMap);
			return executeSQL(rule.getDeleteDataItemSql(),false);		
		}
		if(tablename.equals("StudentSportsRecord"))
		{
			StudentSportsRecord rule=new StudentSportsRecord(dataMap);
			return executeSQL(rule.getDeleteDataItemSql(),false);		
		}
		ArrayList<HashMap<String, Object>>answer=new ArrayList<HashMap<String, Object>>();  
		return answer;
	}
	public static ArrayList<HashMap<String, Object>> getDataItem(Map<String, Object> dataMap){
		Object tablename=dataMap.get("table_name");
		dataMap.remove("table_name");
		if(tablename.equals("AccountInfo"))
		{
			account a=new account(dataMap);
			return executeSQL(a.getReadDataItemSql(),true);		
		}
		if(tablename.equals("AccountSportsRecord")){
			AccountSportsRecord accountSportsRecord=new AccountSportsRecord(dataMap);
			return executeSQL(accountSportsRecord.getReadDataItemSql(),true);
		}
		if(tablename.equals("picture")){
			picture p=new picture(dataMap);
			return p.getPicture();
		}
		if(tablename.equals("Friendship"))
		{
			friendship a=new friendship(dataMap);
			return executeSQL(a.getReadDataItemSql(),true);		
		}
		if(tablename.equals("Groupship"))
		{
			groupship a=new groupship(dataMap);
			return executeSQL(a.getReadDataItemSql(),true);		
		}
		if(tablename.equals("StudentInfo"))
		{
			studentInfo a=new studentInfo(dataMap);
			return executeSQL(a.getReadDataItemSql(),true);		
		}
		if(tablename.equals("TeacherInfo"))
		{
			teacherInfo a=new teacherInfo(dataMap);
			return executeSQL(a.getReadDataItemSql(),true);		
		}
		if(tablename.equals("UniversityRule"))
		{
			UniversityRule rule=new UniversityRule(dataMap);
			return executeSQL(rule.getReadDataItemSql(),true);		
		}
		if(tablename.equals("StudentSportsRecord"))
		{
			StudentSportsRecord rule=new StudentSportsRecord(dataMap);
			return executeSQL(rule.getReadDataItemSql(),true);		
		}
		ArrayList<HashMap<String, Object>>answer=new ArrayList<HashMap<String, Object>>();  
		return answer;
	}
	public static ArrayList<HashMap<String, Object>> getAverageData(Map<String, Object> dataMap){
		Object tablename=dataMap.get("table_name");
		dataMap.remove("table_name");
		if(tablename.equals("StudentInfo")){
			studentInfo s=new studentInfo(dataMap);
			return executeSQL(s.getAverageScoreSql(),true);
			}
		ArrayList<HashMap<String, Object>>answer=new ArrayList<HashMap<String, Object>>();  
		return answer;
	}
}
