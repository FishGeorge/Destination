package db;
/*Author:Maugham(刘瑞琦)
 * Time:2018-4-3
 * Tele:15764949052
 * QQ:3320089237
 * 
 * */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Decoder.BinaryStream;

//import com.mysql.jdbc.Blob;

//import com.sun.java.util.jar.pack.Package.File;

public class picture {
	private Map<String, Object> selfmap=new HashMap<String, Object>();
	public picture(Map<String, Object> map){
		selfmap=map;
	}
	public ArrayList<HashMap<String, Object>>  savePicture(){
			String dbURL="jdbc:mysql://106.14.151.245:3306/destination?verifyServerCertificate=false&useSSL=false";
			String user="destination";
			String password="admin";
			try{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("loading");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try{
					Connection conn=DriverManager.getConnection(dbURL,user,password);
					PreparedStatement smt=conn.prepareStatement("insert into picture (a_id,img,caption) values(?,?,?);");				
					String s=String.valueOf(selfmap.get("a_id"));
					smt.setInt(1,Integer.parseInt(s));
//					byte[] b=new byte[((String)selfmap.get("picture")).getBytes().length];
//					b=((String)selfmap.get("picture")).getBytes();
//					InputStream in=new ByteArrayInputStream(b);
					InputStream in=new   ByteArrayInputStream(((String)selfmap.get("picture")).getBytes());
					OutputStream fout=new FileOutputStream("C:\\Users\\Maugham\\Desktop\\cat.jpg");
					byte[] b=new byte[in.available()];
					int len=0;
					while((len=in.read(b))!=-1){
						fout.write(b,0,len);
					}
					smt.setBinaryStream(2,in);
					
					smt.setString(3, (String)selfmap.get("caption"));
					
					smt.executeUpdate();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			ArrayList<HashMap<String, Object>>answer=new ArrayList<HashMap<String, Object>>();  
			return answer;
		}
	public ArrayList<HashMap<String, Object>>  getPicture(){
		String dbURL="jdbc:mysql://106.14.151.245:3306/destination?verifyServerCertificate=false&useSSL=false";
		String user="destination";
		String password="admin";
		ArrayList<HashMap<String, Object>> al = new ArrayList<HashMap<String, Object>>();  
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("loading");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try{
				Connection conn=DriverManager.getConnection(dbURL,user,password);
				PreparedStatement smt=conn.prepareStatement(getPictureDataItemSql());				
				
				ResultSet set=smt.executeQuery();
				ResultSetMetaData data = smt.getMetaData();  
				InputStream iStream=null; 
			    while (set.next()) {  
			                HashMap<String, Object> map = new HashMap<String, Object>();  			              
			            	//convert every row to a arraylist unit
			            	map.put("id", set.getInt(1));
			            	map.put("a_id", set.getInt(2));
			            	map.put("caption",(String)set.getString(4));
			            	Blob blob=set.getBlob("img");
			            	iStream=blob.getBinaryStream();
			            	System.out.println("thsi is jpg 1th.");
			            	OutputStream out = new FileOutputStream("C:\\Users\\Maugham\\Desktop\\cat.jpg");  
		                    byte[] buffer = new byte[1024];  
		                    int len = 0;  
		                    while((len = iStream.read(buffer)) != -1){  
		                           out.write(buffer, 0, len);  
		                    }  
		                    System.out.println("thsi is jpg 2th");			              
			                al.add(map);   
			                iStream.close();
			                out.close();
			        }         
			        System.out.println(al);  
			        set.close();   
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return al;
	}
public String getCreateDataItemSql(){
	String s="insert into picture values(1,'"+(String)selfmap.get("a_id")+"',";
	
//	File f=(File)selfmap.get("picture");
      //  byte[] pic=new byte[(int)f.length()];
//	try{
//		InputStream is=new FileInputStream(f);
//		ByteArrayOutputStream bytestream=new ByteArrayOutputStream();
//		byte[] b=new byte[((byte[])selfmap.get("picture")).length];
//	    b=(byte[])selfmap.get("picture");
//		int ch;
//		ch=is.read(b);
//		while(ch!=-1){
//			bytestream.write(b,0,ch);
//			ch=is.read(b);
//		}
//		pic=bytestream.toByteArray();
//	}
//	catch(Exception e)
//	{
//		e.printStackTrace();
//	}
//	String tmp=b.toString();
//	s+=tmp;
	s+=selfmap.get("picture");
	s+=",'"+selfmap.get("caption")+"');";
	return s;
}
public String getPictureDataItemSql() {
	String string="select * from picture where a_id=";
	string+=String.valueOf(selfmap.get("a_id"));
	string+=";";
	return string;	
}
//convert InputStream object to String
private   static   String   inputStream2String(InputStream   is)   throws   IOException{ 
    ByteArrayOutputStream   baos   =   new   ByteArrayOutputStream(); 
  //  int   i=-1; 
    byte[] b=new byte[10240];
	  int length=0;
	  String p="";
	  while((length=is.read(b))!=-1) {
		  p+=b.toString();
	  }
	  return p;
//    while((i=is.read())!=-1){ 
//    	baos.write(i); 
//    } 
 //  return   baos.toString(); 
}
}