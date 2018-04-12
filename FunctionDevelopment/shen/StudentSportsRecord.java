package db;

import java.util.HashMap;
/*
 * shen
 * 数据库表StudentSportsRecord
 */
public class StudentSportsRecord {
	private String id;
	private String school_id;
	private String stu_id;
	private String type;
	private String date;
	private String time;
	private Map<String, Object> selfmap=new HashMap<String, Object>();
	
	public StudentSportsRecord(Map<String,Obeject> map){
		selfmap=map;
		id=(String)map.get("id");
		school_id=(String)map.get("school_id");
		stu_id=(String)map.get("stu_id");
		type=(String)map.get("type");
		date=(String)map.get("date");
		time=(String)map.get("time");
		
	}
	public String getCreateDataItemSql(){
		return "insert into StudentSportsRecord values('"+id+"','"+school_id+"','"+stu_id+"','"+type+"','"+date+"','"+time+"');";
	}
	public String getAverageScoreSql(){
		return "select avg(score) from StudentInfo;";
	}
	public String getUpdateDataItemSql(){
		String  sqlString="update StudentSportsRecord set ";
		String idtemp=(String)selfmap.get("id");
		selfmap.remove("id");
		for(String key:selfmap.keySet()){
			if(selfmap.containsKey(key)){
				sqlString+=key+"='"+selfmap.get(key)+"',";
			}
		}
		sqlString=sqlString.substring(0, sqlString.length()-1);
		sqlString+=" where id='"+id+"';";
		System.out.println(sqlString);
		return sqlString;
	}
	public String getDeleteDataItemSql(){
		return "delete from StudentSportsRecord where id='"+(String)selfmap.get("id")+"';";
	}
	public String getReadDataItemSql(){
		return "select * from StudentSportsRecord where id='"+selfmap.get("id")+"';";
	}

}
