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
	 * ��������InitialInfo()
	 * ���ߣ������
	 * ��������
	 * ����ֵ����
	 * ���ܣ����ڳ�ʼ��������Ϣ����ע��֮���Զ�����
	 * @return
	 */
	public void InitialInfo() {
		        HttpClient hc=new HttpClient();
		        Map<String,Object> oo=new HashMap();
		        System .out.println("������һ��ͨ��");
		        int a=sc.nextInt();
		        oo.put("stu_id", a);
		        System .out.println("������ѧУ���");
		        int b=sc.nextInt();
		        oo.put("school_id", b);
		        System.out.println("������������� ");
		        String c=sc.nextLine();
		        oo.put("stu_name", c); 
		        System.out.println("����������Ա� ");
		        int d=sc.nextInt();
		        oo.put("stu_gender", d);
		        System.out.println("��������ĵ绰���� ");
		        String e=sc.nextLine();
		        oo.put("phone_number", e);
		        System.out.println("������������� ");
		        String f=sc.nextLine();
		        oo.put("email", f);
		        hc.createObject(oo);
		    }  	
	/**
	 * ��������BookTime()
	 * ���ߣ������
	 * ��������
	 * ����ֵ����
	 * ���ܣ�����Ԥ���ܲ�ʱ��
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
	 * ��������CheckBook()
	 * ���ߣ������
	 * ��������
	 * ����ֵ����
	 * ���ܣ����ڲ鿴���е�Ԥ��
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
	 * ��������Monitor()
	 * ���ߣ������
	 * ��������
	 * ����ֵ����
	 * ���ܣ����������û������ܲ�
	 * @return
	 */
	public void moniter()  {
		 Calendar now=Calendar.getInstance();
		 int now_year=now.get(Calendar.YEAR);
		 int now_month=now.get(Calendar.MONTH)+1;
		 String now_day=Integer.toString(now.get(Calendar.DAY_OF_MONTH));// ��ȡ��������ڣ���ݣ��·�
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
				;//���ѷ�ʽ
			}
		 }
	}
	/**
	 * ��������ChangeInfo()
	 * ���ߣ������
	 * ��������
	 * ����ֵ����
	 * ���ܣ����ڸ��ĸ�����Ϣ
	 * @return
	 */
	public void ChangeInfo() {
		 HttpClient hc=new HttpClient();
		 Map<String,Object> oo=new HashMap();
		 int a=sc.nextInt();
		 String b=sc.nextLine();
		 oo.put("", "");//���ݵ���İ�ť����
		 hc.updateObject(oo);
	}
	/**
	 * ��������AddFriend()
	 * ���ߣ������
	 * ��������
	 * ����ֵ����
	 * ���ܣ�������Ӻ���
	 * @return
	 */
	public void AddFriend() {
		 int a=sc.nextInt();//����ĺ���id
		 HttpClient hc=new HttpClient();
		 Map<String,Object> oo=new HashMap();
		 oo.put("a_id_1","");//����id
		 oo.put("a_id_2, "");//����id
		//���˺�Ϊa���û�������������
		//ͬ���������
		 hc.createObject(oo);
	}
	/**
	 * ��������DeleteFriend()
	 * ���ߣ������
	 * ��������
	 * ����ֵ����
	 * ���ܣ�����ɾ������
	 * @return
	 */
	public void DeleteFriend() {
		 HttpClient hc=new HttpClient();
		 Map<String,Object> oo=new HashMap();
		 oo.put("a_id_1","");//����id
		 oo.put("a_id_1","");//����id
		 hc.deleteObject(oo);
	}
}
