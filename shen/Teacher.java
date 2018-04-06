package Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Teacher {
	
	/*
	 * shen
	 * 查询学生个人账户信息
	 * 输入：stu_id
	 * 输出：学生个人账户信息
	 */
	public String CheckStudentInfo(String stuid)
	{
		String res="";
		java.util.Map<String, Object> selfmap=new HashMap<String, Object>();
		try{
			selfmap.put("table_name","StudentInfo");
			selfmap.put("stu_id", stuid);
			res=HttpClient.readObject(selfmap);//调用HttpClient的函数
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	/*
	 * shen
	 * 查询学生个人跑操记录
	 * 输入：账户id
	 * 输出：学生个人跑操记录
	 */
	public String CheckStudentRecord(String aid)
	{
		String res="";
		java.util.Map<String, Object> selfmap=new HashMap<String, Object>();
		try{
			selfmap.put("table_name","AccountSportsRecord");
			selfmap.put("a_id", aid);
			res=HttpClient.readObject(selfmap);//调用HttpClient的函数
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * shen
	 * 查询学生班级跑操记录
	 * 输入：班级
	 * 输出：学生班级跑操记录
	 */
	public String CheckClass(String banji)
	{
		String res="";
		java.util.Map<String, Object> selfmap=new HashMap<String, Object>();
		try{
			selfmap.put("table_name","StudentInfo");
			selfmap.put("banji", banji);
			res=HttpClient.readObject(selfmap);//调用HttpClient的函数
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * shen
	 * 查询学生年级跑操记录
	 * 输入：年级
	 * 输出：学生年级跑操记录
	 */
	public String CheckGrade(String grade)
	{
		String res="";
		java.util.Map<String, Object> selfmap=new HashMap<String, Object>();
		try{
			selfmap.put("table_name","StudentInfo");
			selfmap.put("grade", grade);
			res=HttpClient.readObject(selfmap);//调用HttpClient的函数
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

}
