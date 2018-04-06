package db;
/*Author:Maugham(刘瑞琦)
 * Time:2018-4-3
 * Tele:15764949052
 * QQ:3320089237
 * 
 * */
import java.util.HashMap;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class AccountSportsRecord {
private Map<String, Object> selfMap=new HashMap<String, Object>();
public AccountSportsRecord(Map<String, Object> map){
	selfMap=map;
}
public String getCreateDataItemSql(){
	return "insert into AccountSportsRecord (a_id,date,time,location,distance,cost) values ("+
	String.valueOf(selfMap.get("a_id"))+",'"+selfMap.get("date")+"','"+selfMap.get("time")+"','"+selfMap.get("location")+"',"
	+String.valueOf(selfMap.get("distance"))+","+String.valueOf(selfMap.get("cost"))+"  );";
}
public String getUpdateDataItemSql(){
	String  sqlString="update AccountSportsRecord set ";
	String idtemp=String.valueOf(selfMap.get("a_id"));
	selfMap.remove("a_id");
			sqlString+="date='"+selfMap.get("date")+"',"
			+"time='"+selfMap.get("time")+"',location='"+selfMap.get("location")+"',distance="+
			String.valueOf(selfMap.get("distance"))+",cost="+String.valueOf(selfMap.get("cost"))+" ";
	sqlString+=" where a_id="+idtemp+";";
	System.out.println(sqlString);
	return sqlString;
}
public String getDeleteDataItemSql(){
	return "delete from AccountSportsRecord where a_id="+String.valueOf(selfMap.get("a_id"))+";";
}
public String getReadDataItemSql(){
	return "select * from AccountSportsRecord where a_id="+String.valueOf(selfMap.get("a_id"))+";";
}
public String getReadDataItemWithFilterConditionSql(){
	return "select * from AccountInfo where a_id="+String.valueOf(selfMap.get("a_id"))+" and "+selfMap.get("Filtercondition")+";";
}
}
