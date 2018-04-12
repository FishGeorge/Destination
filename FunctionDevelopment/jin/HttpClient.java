package package1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject; 

public class HttpClient {
	 private static String serverURL="http://106.14.151.245:8080/dest/JsonAction";
	  /*
	   * 101--创建一条数据对象
	   * 102--更新一条数据对象
	   * 103--删除一条数据对象
	   * 104--读取一条数据对象
	   * 105--更新一条数据对象中的部分字段值
	   * map中应当有如下几项:table_name,ID
	   * */
	  //存数据对象用的
	  //使用者必须传入对应表格的所有字段值，即使没有，也应当写一点填充值
	  public static String createObject(Map<String,Object> map)
	  {
		  map.put("Order", "101"); 	  
		  return sendPost(map);
	  }
	  public static String updateObject(Map<String,Object> map)
	  {
		  map.put("Order", "102");
		  return sendPost(map);
	  }
	  public static String deleteObject(Map<String,Object> map)
	  {
		  map.put("Order", "103");
		  return sendPost(map);
	  }
	  public static String readObject(Map<String,Object> map)
	  {
		  //这里默认返回item的所有数据，如果查询指定一个字段的也可以，以后再开发
		  map.put("Order", "104");
		  return sendPost(map);
	  }
	/*
	 * This method return a single json object
	 * to convert json object to map
	 * 使用这样的方法即可：
	 */
	  public static ArrayList<Map<String, Object>> getAnswerObjects(String answer) {
		  JSONObject tmp=JSONObject.parseObject(answer);  
		  JSONArray arr=tmp.getJSONArray("answer");
		  int l=arr.size();
		  ArrayList<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		  for(int i=0;i<l;i++) {
			  result.add((Map<String, Object>)arr.getJSONObject(i));
		  }
		  return result;
	  }
	  public static Map<String, Object> getSingleAnswerObject(String answer) {
		  JSONObject tmp=JSONObject.parseObject(answer);  
		  JSONArray arr=tmp.getJSONArray("answer");
		  int l=arr.size();
		  return (Map<String, Object>)arr.getJSONObject(0);
	  }
	  private static String sendPost(Map<String,Object> param)
		    {
		        String result = "";
		        try
		        {
		            URL realUrl = new URL(serverURL);
		            //构造一个到指定URL的连接
		            URLConnection conn = realUrl.openConnection();
		             
		            conn.setRequestProperty("accept", "*/*");
		            conn.setRequestProperty("connection", "Keep-Alive");
		            conn.setRequestProperty("user-agent",
		            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		            conn.setRequestProperty("username", "helloworld"); 
		            conn.setDoOutput(true);
		            conn.setDoInput(true);
		            try {	            	 
		                JSONObject user = new JSONObject(param);  
		                //将JSON数据添加到输出流中
		                OutputStream ot = conn.getOutputStream();
		                ot.write(user.toString().getBytes());  
		            	//ot.write(t.getBytes());
		                //ot.write(param.getBytes());
		                PrintWriter out = new PrintWriter(ot);	             
		                out.flush();
		            } catch (Exception e) {
		                 
		            }
		            try(
		                //接收服务器端发回的响应
		                BufferedReader in = new BufferedReader(new InputStreamReader
		                    (conn.getInputStream() , "utf-8")))
		            {
		                String line;
		                while ((line = in.readLine())!= null)
		                {
		                    result += "\n" + line;
		                }
		            }
		        }
		        catch(Exception e)
		        {
		            System.out.println("POST异常" + e);
		            e.printStackTrace();
		        }
		        return result;
		    }
		     
		    public static void main(String args[])
		    {
		    	Map<String,Object> oo=new HashMap();
		    	oo.put("id", "3");
		    	oo.put("tele","15754732221");
		    	oo.put("name","龚呈");
		    	oo.put("mail", "15764949052@163.com");
		    	oo.put("password","gongcheng-is-a-genus");
		    	oo.put("sex","male");
		    	oo.put("info_id","00001");
		    	oo.put("friend","金杰");
		    	oo.put("visible_for_differentfriend","1");
		    	oo.put("group_id","5555");
		    	oo.put("health_state","healthy");
		    	oo.put("identity","student");
		    	oo.put("sport_state","running");
		    	oo.put("table_name", "account");
		    	
		    	//Test for add data item
		        //System.out.println(createObject(oo));
		    	
		    	//Test for update data item
		        //oo.put("mail","10086@gmail.com");	        
		        //System.out.println(updateObject(oo));
		    	
		    	//Test for delete data item
		    	//System.out.println(deleteObject(oo));
		    	
		    	//Test for read data item
		    	System.out.println(getSingleAnswerObject(readObject(oo)));
		    }

}
