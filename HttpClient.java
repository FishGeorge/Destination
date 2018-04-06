package Test;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.stream.FileImageOutputStream;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject; 


public class HttpClient {
  private static String serverURL="http://localhost:8080/new/JsonAction";
	//private static String serverURL="http://106.14.151.245:8080/dest/JsonAction";
  /*
   * 101--����һ�����ݶ���
   * 102--����һ�����ݶ���
   * 103--ɾ��һ�����ݶ���
   * 104--��ȡһ�����ݶ���
   * 105--ʹ�ù�������ɸѡ����
   * map��Ӧ�������¼���:table_name,ID
   * */
  //�����ݶ����õ�
  //ʹ���߱��봫���Ӧ���������ֶ�ֵ����ʹû�У�ҲӦ��дһ�����ֵ
  public static String createObject(Map<String,Object> map) throws Exception
  {
	  map.put("Order", "101"); 
	  
	  if((map.get("table_name")).equals("picture")) {
		  File pic=new File((String)map.get("PictureUrl"));
		  map.remove("PictureUrl");
		  FileInputStream fis = new FileInputStream(pic);
		  
		  String p="";
		  byte[] b=new byte[fis.available()];
		  int length=0;
		  while((length=fis.read(b))!=-1) {
			  p+=b.toString();
		  }
		  fis.close();
	      //byte[] read = new byte[fis.available()]; 
	      //System.out.println(read.toString());
//	      fis.read(read);
//	      String p=read.toString();
	      map.put("picture",p);
	  }
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
	  //����Ĭ�Ϸ���item���������ݣ������ѯָ��һ���ֶε�Ҳ���ԣ��Ժ��ٿ���
	  map.put("Order", "104");
	  return sendPost(map);
  }
  public static String readObjectWithFilterConditions(Map<String,Object> map)
  {
	  //����Ĭ�Ϸ���item���������ݣ������ѯָ��һ���ֶε�Ҳ���ԣ��Ժ��ٿ���
	  map.put("Order", "105");
	  return sendPost(map);
  }
  public static String averageObject(Map<String,Object> map)
  {
	  map.put("Order", "106");
	  return sendPost(map);
  }
/*
 * This method return a single json object
 * to convert json object to map
 * ʹ�������ķ������ɣ�
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
	  System.out.println(answer);
	  JSONObject tmp=JSONObject.parseObject(answer);  
	  JSONArray arr=tmp.getJSONArray("answer");
	  int l=arr.size();
	  return (Map<String, Object>)arr.getJSONObject(0);
  }
  
  public static Map<String, Object> getPicture(Map<String,Object> map) throws Exception{
	  Map<String, Object> result=getSingleAnswerObject(readObject(map));
	  String s=(String)result.get("img");
	  InputStream in=new ByteArrayInputStream(s.getBytes());   
	  byte[] b=new byte[10240];
	  int len=0;
	  String pos="./"+String.valueOf(map.get("a_id"))+"."+map.get("caption");
     // OutputStream out = new ByteArrayOutputStream(); 
      FileOutputStream imageOutput = new FileOutputStream(new File(pos));//��������
      while((len=in.read(b))!=-1) {
    	  imageOutput.write(b,0,len);
      }
      imageOutput.write(b, 0, b.length);//��byteд��Ӳ��
      imageOutput.flush();
      imageOutput.close();
      in.close();
//      InputStream is = new ByteArrayInputStream(b); 
//      byte[] buff = new byte[1024];  
//      int len = 0;  
//      while((len=is.read(buff))!=-1){  
//          out.write(buff, 0, len);  
//      }  
//      is.close();  
//      out.close();
      result.put("PictureUrl",pos);
      result.remove("img");
      return result;
  }
  private static String sendPost(Map<String,Object> param)
	    {
	        String result = "";
	        try
	        {
	            URL realUrl = new URL(serverURL);
	            //����һ����ָ��URL������
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
	                //��JSON������ӵ��������
	                OutputStream ot = conn.getOutputStream();
	                ot.write(user.toString().getBytes());  
	            	//ot.write(t.getBytes());
	                //ot.write(param.getBytes());
	                PrintWriter out = new PrintWriter(ot);	             
	                out.flush();
	            } catch (Exception e) {
	                 
	            }
	            try(
	                //���շ������˷��ص���Ӧ
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
	            System.out.println("POST�쳣" + e);
	            e.printStackTrace();
	        }
	        return result;
	    }
}
