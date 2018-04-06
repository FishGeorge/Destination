package Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class testPrograms {
	public static void main(String args[]) throws Exception
    {
		testPrograms t1=new testPrograms();
		t1.testForStudentInfo();
		//t1.testForUniversityRule();
		//t1.testForStudentSportsRecord();
    	//t1.testForPictureUsing();		
		//t1.testForAccountInfoUsing();
		//t1.testForAccountSportsRecordUsing();

    }
	public void testForStudentInfo()throws Exception{
		Map<String,Object> oo=new HashMap();
		oo.put("table_name", "StudentInfo");
		oo.put("stu_id",71116412);
		oo.put("school_id", 101);
		oo.put("stu_name","gongcheng");
		oo.put("stu_gender",1);
		oo.put("phone_number","15764949052");
		oo.put("score",99999);
		oo.put("institution","software");
		oo.put("grade","16");
		oo.put("class","11");
		//HttpClient.createObject(oo);
		//HttpClient.updateObject(oo);
		//System.out.println(HttpClient.readObject(oo));
		System.out.println(HttpClient.getAnswerObjects(HttpClient.averageObject(oo)));
	}
	public void testForUniversityRule() throws Exception{
		Map<String,Object> oo=new HashMap();
		oo.put("table_name", "UniversityRule");
		oo.put("id",1);
		oo.put("school_id", 101);
		oo.put("school_name","seu");
		oo.put("auther_id",888888 );
		oo.put("period","ai,写的好苦啊");
		oo.put("time","我不想写了");
		oo.put("runtime","wuwuwu，概率论作业还没写呢...");
		oo.put("pass_score",0);
		oo.put("score_set","搞不懂这个字段干啥的");
		oo.put("run_limit","whatever");
		oo.put("standard","aiaiaiaiai");
		oo.put("run_standard","888");
		//HttpClient.createObject(oo);
		//HttpClient.updateObject(oo);
		//HttpClient.deleteObject(oo);
		System.out.println(HttpClient.getAnswerObjects(HttpClient.readObject(oo)));
	}
	public void testForStudentSportsRecord() throws Exception{
		Map<String,Object> oo=new HashMap();
		oo.put("table_name", "StudentSportsRecord");
		oo.put("school_id", 112);
		oo.put("stu_id",71116410);
		oo.put("type", 8);
		oo.put("date","2018-4-6");
		oo.put("time", "06:06:06");
		//HttpClient.createObject(oo);
		//HttpClient.updateObject(oo);
		oo.put("id",6);
		//HttpClient.deleteObject(oo);
		//System.out.println(HttpClient.getAnswerObjects(HttpClient.readObject(oo)));
		
		oo.put("Filtercondition"," id>0");
		System.out.println(HttpClient.getAnswerObjects(HttpClient.readObjectWithFilterConditions(oo)));
	}
	public void testForAccountSportsRecordUsing() throws Exception{
		Map<String,Object> oo=new HashMap();
		oo.put("table_name", "AccountSportsRecord");
		oo.put("a_id",13);
		oo.put("date","2018-2-5");
		oo.put("time", "06:06:06");
		oo.put("location","taoyuan");
		oo.put("distance",1200);
		oo.put("cost",3333333);
		//HttpClient.createObject(oo);
		//HttpClient.updateObject(oo);
		//HttpClient.deleteObject(oo);
		//System.out.println(HttpClient.getAnswerObjects(HttpClient.readObject(oo)));
		oo.put("Filtercondition","a_id > 11");
		System.out.println(HttpClient.getAnswerObjects(HttpClient.readObjectWithFilterConditions(oo)));
	}
	public void testForAccountInfoUsing() throws Exception{
		Map<String,Object> oo=new HashMap();
		oo.put("table_name", "AccountInfo");
		oo.put("a_id",10);
		oo.put("a_type",1);
		oo.put("a_name", "maugham");
		oo.put("a_password","654321");
		oo.put("nickname", "gergoe");
		oo.put("user_type",1);
		oo.put("user_id", 6666);
		oo.put("gender",12);
		oo.put("birthday", 2018666);
		oo.put("height",178);
		oo.put("weight", 1066);
		Date date = new Date();    
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);//将时间格式转换成符合Timestamp要求的格式.  
		oo.put("scheduled_time",nowTime);
		//HttpClient.createObject(oo);
		//HttpClient.updateObject(oo);
		//HttpClient.deleteObject(oo);
		//System.out.println(HttpClient.getAnswerObjects(HttpClient.readObject(oo)));
		oo.put("Filtercondition","a_id < 20");
		System.out.println(HttpClient.getAnswerObjects(HttpClient.readObjectWithFilterConditions(oo)));
	}
    public void testForPictureUsing()throws Exception {
    	Map<String,Object> oo=new HashMap();
    	oo.put("PictureUrl", "C:\\Users\\Maugham\\Desktop\\qianxun.jpg");
    	oo.put("table_name","picture");
    	oo.put("a_id",39);
    	oo.put("caption","jpg");
    	HttpClient.createObject(oo);    	
    	System.out.println(HttpClient.getPicture(oo));
    }
}
