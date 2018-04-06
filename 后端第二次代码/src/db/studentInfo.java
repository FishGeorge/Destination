package db;

import java.util.HashMap;
import java.util.Map;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class studentInfo {

	private String stu_id;
	private String school_id;
	private String stu_name;
	private String stu_gender;
	private String phone_number;
	private String email;
	private String institution;
	private String grade;
	private String className;
	private String score;
	
	public studentInfo(Map<String, Object> map){
		stu_id=String.valueOf(map.get("stu_id"));
		school_id=String.valueOf(map.get("school_id"));
		stu_name=(String)map.get("stu_name");
		stu_gender=String.valueOf(map.get("stu_gender"));
		phone_number=(String)map.get("phone_number");
		email=(String)map.get("email");
		score=String.valueOf(map.get("score"));
		institution=(String)map.get("institution");
		grade=(String)map.get("grade");
		className=(String)map.get("class");
	}
	public String getCreateDataItemSql(){
		return  "insert into  StudentInfo  (stu_id,school_id,stu_name,stu_gender,phone_number,email,score,institution,grade,class) values("+stu_id+","+school_id+",'"+stu_name+"',"+stu_gender+",'"+phone_number+"','"+email+"',"+score+",'"+institution+"','"+grade+"','"+className+"');";
	}
	public String getUpdateDataItemSql(){
		String  sqlString="update StudentInfo set ";
		sqlString+=" school_id="+String.valueOf(school_id)+",stu_name='"+stu_name+"',stu_gender="+String.valueOf(stu_gender)+",phone_number='"+phone_number+"',email='"+email+"',score="+String.valueOf(score)+",institution='"+institution+"',grade='"+grade+"',class='"+className+"' where stu_id="+String.valueOf(stu_id)+";";
		System.out.println(sqlString);
		return sqlString;
	}
	public String getDeleteDataItemSql(){
		//System.out.println( "delete from account where id='"+(String)selfmap.get("id")+"';");
		return "delete from StudentInfo where stu_id="+stu_id+";";
	}
	public String getReadDataItemSql(){
		return "select * from StudentInfo where stu_id="+stu_id+";";
	}
	public String getAverageScoreSql(){
		return "select avg(score) from StudentInfo;";
	}


}
