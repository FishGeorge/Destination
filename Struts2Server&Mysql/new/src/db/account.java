package db;

import java.util.HashMap;
import java.util.Map;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class account {
					private String id;
					private String tele;
					private String mail;
					private String name;
					private String password;
					private String sex;
					private String info_id;
					private String friend;
					private String visible_for_differentfriend;
					private String group_id;
					private String health_state;
					private String identity;
					private String sport_state;
					private Map<String, Object> selfmap=new HashMap<String, Object>();
					
					public account(Map<String, Object> map){
						selfmap=map;
						id=(String)map.get("id");
						tele=(String)map.get("tele");
						mail=(String)map.get("mail");
						name=(String)map.get("name");
						password=(String)map.get("password");
						sex=(String)map.get("sex");
						info_id=(String)map.get("info_id");
						friend=(String)map.get("friend");
						visible_for_differentfriend=(String)map.get("visible_for_differentfriend");
						group_id=(String)map.get("group_id");
						health_state=(String)map.get("health_state");
						identity=(String)map.get("identity");
						sport_state=(String)map.get("sport_state");
					}
					public String getCreateDataItemSql(){
						return "insert into account values('"+id+"','"+tele+"','"+mail+"','"+name+"','"+password+"','"+sex+"','"+info_id+"','"+friend+"','"+visible_for_differentfriend+
						"','"+group_id+"','"+health_state+"','"+identity+"','"+sport_state+"');";
					}
					public String getUpdateDataItemSql(){
						String  sqlString="update account set ";
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
