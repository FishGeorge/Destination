package FunctionofStu;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.crypto.Data;
public class StuInfo {
	Scanner sc=new Scanner(System.in);
	/**
	 * 函数名：InitialInfo()
	 * 作者：曲正锟
	 * 参数：无
	 * 返回值：无
	 * 功能：用于初始化个人信息，在注册之后自动调用
	 * @return
	 */
	public void InitialInfo() {
		        HttpClient hc=new HttpClient();
		        Map<String,Object> oo=new HashMap();
		        System .out.println("请输入一卡通号");
		        int a=sc.nextInt();
		        oo.put("stu_id", a);
		        System .out.println("请输入学校编号");
		        int b=sc.nextInt();
		        oo.put("school_id", b);
		        System.out.println("请输入你的姓名 ");
		        String c=sc.nextLine();
		        oo.put("stu_name", c); 
		        System.out.println("请输入你的性别 ");
		        int d=sc.nextInt();
		        oo.put("stu_gender", d);
		        System.out.println("请输入你的电话号码 ");
		        String e=sc.nextLine();
		        oo.put("phone_number", e);
		        System.out.println("请输入你的邮箱 ");
		        String f=sc.nextLine();
		        oo.put("email", f);
		        hc.createObject(oo);
		    }  	
	/**
	 * 函数名：BookTime()
	 * 作者：曲正锟
	 * 参数：无
	 * 返回值：无
	 * 功能：用于预定跑操时间
	 * @return
	 * @throws ParseException 
	 */
	public void Booktime() throws ParseException {
		HttpClient hc=new HttpClient();
		SimpleDateFormat simFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss"); 
		String a=sc.nextLine();
		Date dtBeg = simFormat.parse(a);
	    Map<String,Object> oo=new HashMap();
	    oo.put("scheduled_time", a);
	    hc.createObject(oo);
	}
	/**
	 * 函数名：CheckBook()
	 * 作者：曲正锟
	 * 参数：无
	 * 返回值：无
	 * 功能：用于查看已有的预定
	 * @return
	 */
	public void CheckBook() {
		 HttpClient hc=new HttpClient();
		 Map<String,Object> oo=new HashMap();
		 oo.put("Filtercondition","id>0" );
		 ArrayList a=hc.getAnswerObjects(hc.readObjectWithFilterConditions(oo));
		 for(int i=0;i<a.size();i++) {
			 Date c=(Date) ((Map<String, Object>) a.get(i)).get("scheduled_id");
			 System.out.println(c);
		 }
	}
	/**
	 * 函数名：Monitor()
	 * 作者：曲正锟
	 * 参数：无
	 * 返回值：无
	 * 功能：用于提醒用户进行跑操
	 * @return
	 */
	public void moniter()  {
		 Calendar now=Calendar.getInstance();
		 int now_year=now.get(Calendar.YEAR);
		 int now_month=now.get(Calendar.MONTH)+1;
		 String now_day=Integer.toString(now.get(Calendar.DAY_OF_MONTH));// 获取今天的日期，年份，月份
		 int now_da=Integer.parseInt(now_day);
		 int now_hour=now.get(Calendar.HOUR_OF_DAY);
		 int now_minute=now.get(Calendar.MINUTE);
		 HttpClient hc=new HttpClient();
		 Map<String,Object> oo=new HashMap();
		 oo.put("Filtercondition","id>0" );
		 ArrayList a=hc.getAnswerObjects(hc.readObjectWithFilterConditions(oo));
		 for(int i=0;i<a.size();i++) {
			 Date c=(Date) ((Map<String, Object>) a.get(i)).get("scheduled_id");
			if(now_year==c.getYear()&&now_month==c.getMonth()&&now_da==c.getDay()&&now_hour==c.getHours()
					&&now_minute==c.getMinutes()) {
				;//提醒方式
			}
		 }
	}
	/**
	 * 函数名：ChangeInfo()
	 * 作者：曲正锟
	 * 参数：无
	 * 返回值：无
	 * 功能：用于更改个人信息
	 * @return
	 */
	public void ChangeInfo() {
		 HttpClient hc=new HttpClient();
		 Map<String,Object> oo=new HashMap();
		 int a=sc.nextInt();
		 String b=sc.nextLine();
		 oo.put("", "");//根据点击的按钮决定
		 hc.updateObject(oo);
	}
	/**
	 * 函数名：AddFriend()
	 * 作者：曲正锟
	 * 参数：无
	 * 返回值：无
	 * 功能：用于添加好友
	 * @return
	 */
	public void AddFriend() {
		 int a=sc.nextInt();//输入的好友id
		 HttpClient hc=new HttpClient();
		 Map<String,Object> oo=new HashMap();
		 oo.put("a_id_1","");//个人id
		 oo.put("a_id_2, "");//好友id
		//向账号为a的用户发送请求推送
		//同意后进行添加
		 hc.createObject(oo);
	}
	/**
	 * 函数名：DeleteFriend()
	 * 作者：曲正锟
	 * 参数：无
	 * 返回值：无
	 * 功能：用于删除好友
	 * @return
	 */
	public void DeleteFriend() {
		 HttpClient hc=new HttpClient();
		 Map<String,Object> oo=new HashMap();
		 oo.put("a_id_1","");//个人id
		 oo.put("a_id_1","");//好友id
		 hc.deleteObject(oo);
	}
}
