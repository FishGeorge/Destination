package Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import db.HashMap;
import db.Map;
import db.Object;
import db.String;

/*smy
 * 
 */
public class Manager {
	
	/*
	 * shen
	 * �����ܲٹ���
	 * ���룺map
	 * �����boolean
	 */
	public boolean setStandard(java.util.Map<String,Object> map)
	{
		try{
			HttpClient.createObject(map);//����HttpClient�ĺ���
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/*
	 * shen
	 * ��ѯȫ���ۺ���Ϣ
	 * ���룺��
	 * �����ȫ���¼��ƽ��
	 */
	public String CheckAllinfo()
	{
		String res="";
		java.util.Map<String, Object> selfmap=new HashMap<String, Object>();
		try{
			selfmap.put("table_name","StudentInfo");
			res=HttpClient.readObject(selfmap);//����HttpClient�ĺ���
			res+=HttpClient.averageObject(selfmap);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return res;
	}

	/*
	 * shen
	 * ��ѯѧ�������˻���Ϣ
	 * ���룺stu_id
	 * �����ѧ�������˻���Ϣ
	 */
	public String CheckStudentInfo(String stuid)
	{
		String res="";
		java.util.Map<String, Object> selfmap=new HashMap<String, Object>();
		try{
			selfmap.put("table_name","StudentInfo");
			selfmap.put("stu_id", stuid);
			res=HttpClient.readObject(selfmap);//����HttpClient�ĺ���
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	/*
	 * shen
	 * ��ѯѧ�������ܲټ�¼
	 * ���룺�˻�id
	 * �����ѧ�������ܲټ�¼
	 */
	public String CheckStudentRecord(String aid)
	{
		String res="";
		java.util.Map<String, Object> selfmap=new HashMap<String, Object>();
		try{
			selfmap.put("table_name","AccountSportsRecord");
			selfmap.put("a_id", aid);
			res=HttpClient.readObject(selfmap);//����HttpClient�ĺ���
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * shen
	 * ��ѯѧ���༶�ܲټ�¼
	 * ���룺�༶
	 * �����ѧ���༶�ܲټ�¼
	 */
	public String CheckClass(String banji)
	{
		String res="";
		java.util.Map<String, Object> selfmap=new HashMap<String, Object>();
		try{
			selfmap.put("table_name","StudentInfo");
			selfmap.put("banji", banji);
			res=HttpClient.readObject(selfmap);//����HttpClient�ĺ���
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * shen
	 * ��ѯѧ���꼶�ܲټ�¼
	 * ���룺�꼶
	 * �����ѧ���꼶�ܲټ�¼
	 */
	public String CheckGrade(String grade)
	{
		String res="";
		java.util.Map<String, Object> selfmap=new HashMap<String, Object>();
		try{
			selfmap.put("table_name","StudentInfo");
			selfmap.put("grade", grade);
			res=HttpClient.readObject(selfmap);//����HttpClient�ĺ���
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	/*
	 * shen
	 * ��ѯѧ��ѧԺ�ܲټ�¼
	 * ���룺ѧԺ
	 * �����ѧ��ѧԺ�ܲټ�¼
	 */
	public String CheckInstitution(String institution)
	{
		String res="";
		java.util.Map<String, Object> selfmap=new HashMap<String, Object>();
		try{
			selfmap.put("table_name","StudentInfo");
			selfmap.put("institution", institution);
			res=HttpClient.readObject(selfmap);//����HttpClient�ĺ���
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;	
	}

}
