package db;
/*Author:沈梦烨
 * Time:2018-4-5
 * */
import java.util.HashMap;
import java.util.Map;

public class StudentSportsRecord {
	private String id;
	private String school_id;
	private String stu_id;
	private String type;
	private String date;
	private String time;
	private Map<String, Object> selfmap=new HashMap<String, Object>();
	
	public StudentSportsRecord(Map<String,Object> map){
		selfmap=map;
		id=String.valueOf(map.get("id"));
		school_id=String.valueOf(map.get("school_id"));
		stu_id=String.valueOf(map.get("stu_id"));
		type=String.valueOf(map.get("type"));
		date=(String)map.get("date");
		time=(String)map.get("time");
		
	}
	public String getCreateDataItemSql(){
		return "insert into StudentSportsRecord (school_id,stu_id,type,date,time) values("+school_id+","+stu_id+","+type+",'"+date+"','"+time+"');";
	}
	public String getUpdateDataItemSql(){
		String  sqlString="update StudentSportsRecord set ";
		String idtemp=String.valueOf(selfmap.get("stu_id"));
		selfmap.remove("stu_id");

		sqlString+=" school_id="+school_id+",type="+type+",date='"+date+"',time='"+time+"'";
		sqlString+="  where stu_id="+idtemp+";";
		System.out.println(sqlString);
		return sqlString;
	}
	public String getDeleteDataItemSql(){
		return "delete from StudentSportsRecord where id= "+id+";";
	}
	public String getReadDataItemSql(){
		return "select * from StudentSportsRecord where stu_id= "+stu_id+";";
	}

}
