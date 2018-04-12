package db;

import java.util.HashMap;
import java.util.Map;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class groupship {

	private String id;
	private int group_id;
	private String group_name;
	private int member_id;
	private int member_type;
	private Map<String, Object> selfmap=new HashMap<String, Object>();
	
	public groupship(Map<String, Object> map){
		selfmap=map;
		id=(String)map.get("id");
		group_id=Integer.decode((String)map.get("a_id"));
		group_name=(String)map.get("date");
		member_id=Integer.decode((String)map.get("distance"));
		member_type=Integer.decode((String)map.get("cost"));
	}
	public String getCreateDataItemSql(){
		return "insert into account values('"+id+"','"+group_id+"','"+group_name+"','"+member_id+"','"+member_type+"');";
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
