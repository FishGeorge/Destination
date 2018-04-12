package db;

import java.util.HashMap;
import java.util.Map;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class friendship {

	private String id;
	private int a_id_1;
	private int a_id_2;
	private Map<String, Object> selfmap=new HashMap<String, Object>();
	
	public friendship(Map<String, Object> map){
		selfmap=map;
		id=(String)map.get("id");
		a_id_1=Integer.decode((String)map.get("a_id_1"));
		a_id_2=Integer.decode((String)map.get("a_id_2"));
	}
	public String getCreateDataItemSql(){
		return "insert into account values('"+id+"','"+a_id_1+"','"+a_id_2+"');";
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
