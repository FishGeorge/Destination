package db;

import java.util.HashMap;
import java.util.Map;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class teacherInfo {

	private String id;
	private int tea_id;
	private int school_id;
	private String tea_name;
	private int tea_type;
	private String phone_number;
	private String email;
	private Map<String, Object> selfmap=new HashMap<String, Object>();
	
	public teacherInfo(Map<String, Object> map){
		selfmap=map;
		id=(String)map.get("id");
		tea_id=Integer.decode((String)map.get("tea_id"));
		school_id=Integer.decode((String)map.get("school_id"));
		tea_name=(String)map.get("tea_name");
		tea_type=Integer.decode((String)map.get("tea_type"));
		phone_number=(String)map.get("phone_number");
		email=(String)map.get("email");
	}
	public String getCreateDataItemSql(){
		return "insert into account values('"+id+"','"+tea_id+"','"+school_id+"','"+tea_name+"','"+tea_type+"','"+phone_number+"','"+email+"');";
	}
	public String getUpdateDataItemSql(){
		String  sqlString="update accountSportsRecord set ";
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
		//System.out.println( "delete from account where id='"+(String)selfmap.get("id")+"';");
		return "delete from account where id='"+(String)selfmap.get("id")+"';";
	}
	public String getReadDataItemSql(){
		return "select * from account where id='"+selfmap.get("id")+"';";
	}
}
