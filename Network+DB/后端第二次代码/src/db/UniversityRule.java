package db;
/*Author:沈梦烨
 * Time:2018-4-5
 * */

import java.util.HashMap;
import java.util.Map;

public class UniversityRule {
	private String id;
	private String school_id;
	private String school_name;
	private String auther_id;
	private String period;
	private String time;
	private String run_time;
	private String pass_score;
	private String score_set;
	private String run_limit;
	private String standard;
	private String run_standard;
	private Map<String, Object> selfmap=new HashMap<String, Object>();
	
	public UniversityRule(Map<String,Object> map){
		selfmap=map;
		id=String.valueOf(map.get("id"));
		school_id=String.valueOf(map.get("school_id"));
		school_name=(String)map.get("school_name");
		auther_id=String.valueOf(map.get("auther_id"));
		period=(String)map.get("period");
		time=(String)map.get("time");
		run_time=(String)map.get("runtime");
		pass_score=(String.valueOf(map.get("pass_score")));
		score_set=(String)map.get("score_set");
		run_limit=(String)map.get("run_limit");
		standard=(String)map.get("standard");
		run_standard=(String)map.get("run_standard");
	}
	public String getCreateDataItemSql(){
		return "insert into UniversityRule values("+String.valueOf(id)+","+String.valueOf(school_id)+",'"+school_name+"',"+String.valueOf(auther_id)+",'"+period+"','"+time+"','"+run_time+"',"+String.valueOf(pass_score)+",'"+score_set+"','"+run_limit+"','"+standard+"','"+run_standard+"');";
	}
	public String getUpdateDataItemSql(){
		String  sqlString="update UniversityRule set school_id="+String.valueOf(school_id)+",school_name='"+school_name+"',author_id="+String.valueOf(auther_id)+",period='"+period+"',time='"+time+"',runtime='"+run_time+"',pass_score="+String.valueOf(pass_score)+",score_set='"+score_set+"',run_limit='"+run_limit+"',standard='"+standard+"',run_standard='"+run_standard+"'  where id="+String.valueOf(id)+";";
//		String idtemp=(String)selfmap.get("id");
//		selfmap.remove("id");
//		for(String key:selfmap.keySet()){
//			if(selfmap.containsKey(key)){
//				sqlString+=key+"='"+selfmap.get(key)+"',";
//			}
//		}
//		sqlString=sqlString.substring(0, sqlString.length()-1);
//		sqlString+=" where id='"+id+"';";
//		System.out.println(sqlString);
		return sqlString;
	}
	public String getDeleteDataItemSql(){
		//System.out.println( "delete from account where id='"+(String)selfmap.get("id")+"';");
		return "delete from UniversityRule where id="+String.valueOf(selfmap.get("id"))+";";
	}
	public String getReadDataItemSql(){
		return "select * from UniversityRule where id="+String.valueOf(selfmap.get("id"))+";";
	}

}
