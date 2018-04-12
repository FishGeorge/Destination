package db;
/*Author:Maugham(刘瑞琦)
 * Time:2018-4-3
 * */
import java.util.HashMap;
import java.util.Map;
public class account {
					private Map<String, Object> selfmap=new HashMap<String, Object>();					
					public account(Map<String, Object> map){
						selfmap=map;
					}
					public String getCreateDataItemSql(){
						return "insert into AccountInfo (a_type,a_id,a_name,a_password,nickname,user_type,user_id,gender,birthday,height,weight,scheduled_time) values("+String.valueOf(selfmap.get("a_type"))+","+String.valueOf(selfmap.get("a_id"))+",'"+selfmap.get("a_name")+"','"+selfmap.get("a_password")+"','"+selfmap.get("nickname")+"',"+String.valueOf(selfmap.get("user_type"))+","+String.valueOf(selfmap.get("user_id"))+","+String.valueOf(selfmap.get("gender"))+","+String.valueOf(selfmap.get("birthday"))+
						","+String.valueOf(selfmap.get("height"))+","+String.valueOf(selfmap.get("weight"))+",'"+selfmap.get("scheduled_time")+"' );";
					}
					public String getUpdateDataItemSql(){
						String  sqlString="update AccountInfo set ";
						String idtemp=String.valueOf(selfmap.get("a_id"));
						selfmap.remove("a_id");
								sqlString+="a_type="+String.valueOf(selfmap.get("a_type"))+","
								+"a_name='"+selfmap.get("a_name")+"',a_password='"+selfmap.get("a_password")+"',nickname='"+
								selfmap.get("nickname")+"',user_type="+String.valueOf(selfmap.get("user_type"))+",user_id="+
								String.valueOf(selfmap.get("user_id"))+",gender="+String.valueOf(selfmap.get("gender"))+",birthday="+
								String.valueOf(selfmap.get("height"))+",weight="+String.valueOf(selfmap.get("weight"))+",scheduled_time='"+selfmap.get("scheduled_time")+"' ";
						sqlString+=" where a_id="+idtemp+";";
						System.out.println(sqlString);
						return sqlString;
					}
					public String getDeleteDataItemSql(){
						return "delete from AccountInfo where a_id="+String.valueOf(selfmap.get("a_id"))+";";
					}
					public String getReadDataItemSql(){
						return "select * from AccountInfo where a_id="+String.valueOf(selfmap.get("a_id"))+";";
					}
}
