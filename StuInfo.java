package FunctionofStu;
import java.sql.*;
import java.util.*;
public class StuInfo {
	Connection conn=null;
	Scanner sc=new Scanner(System.in);
	/**
	 * 函数名：connect()
	 * 作者：曲正锟
	 * 参数：无
	 * 返回值：conn
	 * 功能：用于连接数据库
	 * @return
	 */
	public Connection connect() {
		try{
	        //加载及注册JDBC驱动程序
	        Class.forName("com.mysql.jdbc.Driver");
	    }catch(ClassNotFoundException e1){
	    	System.out.println("找不到驱动程序类，加载驱动失败");
	        e1.printStackTrace();
	    }
	    
	    String url="jdbc:mysql://localhost:3306/Mysql?useSSL=false";    //JDBC的URL    
	    //调用DriverManager对象的getConnection()方法，获得一个Connection对象
	   
	    try {
	        conn = DriverManager.getConnection(url,"root","19970822zsq");
	        //创建一个Statement对象
	        Statement stmt = conn.createStatement(); //创建Statement对象
	       // System.out.print("成功连接到数据库！");
	       // stmt.close();
	       // conn.close();
	    } catch (SQLException e){
	    	System.out.println("数据库连接失败");
	        e.printStackTrace();
	    }
	    return conn;
	}
	/**
	 * 函数名：InitialInfo()
	 * 作者：曲正锟
	 * 参数：无
	 * 返回值：无
	 * 功能：用于初始化个人信息，在注册之后自动调用
	 * @return
	 */
	public void InitialInfo() {
		 Connection cnn=connect();   
		 String sql="insert into StudentInfo(id,stu_id,school_id,stu_name,stu_gender,phone_number,email) values(?,?,?,?,?,?,?)";
		    try{  
		        PreparedStatement preStmt =cnn.prepareStatement(sql); 
		        System.out.println("请输入你的账号 ");
		        String a=sc.nextLine();
		        preStmt.setString(1,a);  
		        System .out.println("请输入一卡通号");
		        int b=sc.nextInt();
		        preStmt.setInt(2,b);  
		        System .out.println("请输入学校编号");
		        int c=sc.nextInt();
		        preStmt.setInt(3,c);
		        System.out.println("请输入你的姓名 ");
		        String d=sc.nextLine();
		        preStmt.setString(4,d);  
		        System.out.println("请输入你的性别 ");
		        int e=sc.nextInt();
		        preStmt.setInt(5,e); 
		        System.out.println("请输入你的电话号码 ");
		        String f=sc.nextLine();
		        preStmt.setString(6,f);  
		        System.out.println("请输入你的邮箱 ");
		        String g=sc.nextLine();
		        preStmt.setString(7,g);  
		        preStmt.executeUpdate();  
		    }  
		    catch (SQLException e)  
		    {  
		        e.printStackTrace();  
		    }  
		    
		}
	/**
	 * 函数名：BookTime()
	 * 作者：曲正锟
	 * 参数：无
	 * 返回值：无
	 * 功能：用于预定跑操时间
	 * @return
	 */
	public void Booktime() {
		 Connection cnn=connect();   
		 String sql="insert into StudentInfo(hour,minute,day,month,year) values(?,?,?,?,?)";
		    try{  
		        PreparedStatement preStmt =cnn.prepareStatement(sql); 
		        System.out.println("Please put into the hour: ");
		        int a=sc.nextInt();
		        preStmt.setInt(1,a);  
		        System .out.println("PLease put into the minute: ");
		        int b=sc.nextInt();
		        preStmt.setInt(2,b); 
		        System .out.println("PLease put into the day: ");
		        int c=sc.nextInt();
		        preStmt.setInt(3,c);
		        System .out.println("PLease put into the month: ");
		        int d=sc.nextInt();
		        preStmt.setInt(4,d);
		        System .out.println("PLease put into the year: ");
		        int e=sc.nextInt();
		        preStmt.setInt(5,e);
		        preStmt.executeUpdate();  
		    }  
		    catch (SQLException e)  
		    {  
		        e.printStackTrace();  
		    }
	}
	/**
	 * 函数名：BookTime()
	 * 作者：曲正锟
	 * 参数：无
	 * 返回值：无
	 * 功能：用于查看已有的预定
	 * @return
	 */
	public void CheckBook() {
		//查询语句
	    String sql = "select * from StudentInfo"; 
	    //连接
	    Connection conn = connect();
        PreparedStatement pst = null;
	    try {
         pst = (PreparedStatement) conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	        		System.out.println(rs.getInt("hour")+"点"+rs.getInt("minute")+"分："+rs.getInt("month"));
	            }     
	        } 
	    catch (SQLException e) {
	        	 e.printStackTrace();  
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
		int da=Integer.parseInt(now_day);
		int now_hour=now.get(Calendar.HOUR_OF_DAY);
		int now_minute=now.get(Calendar.MINUTE);
		int now_second=now.get(Calendar.SECOND);
		String sql = "select * from StudentInfo"; 
		Connection conn = connect();
		PreparedStatement pst = null;
		try {
				pst = (PreparedStatement) conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery(sql);
				while(rs.next()){	
					if(now_year==rs.getInt("year")&&now_month==rs.getInt("month")&&da==rs.getInt("day")&&now_hour==rs.getInt("hour")&&now_minute==rs.getInt("minute")) { 
				/*提醒方式*/
					 }
				  }
				rs.close();
				conn.close();
			}
	    catch(SQLException e) {
				//数据库连接失败异常处理
				e.printStackTrace();  
				}
		catch (Exception e) {
				 e.printStackTrace();
				}
		finally{
				;
				}
	 }
}
