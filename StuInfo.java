package FunctionofStu;
import java.sql.*;
import java.util.*;
public class StuInfo {
	Connection conn=null;
	Scanner sc=new Scanner(System.in);
	/**
	 * ��������connect()
	 * ���ߣ������
	 * ��������
	 * ����ֵ��conn
	 * ���ܣ������������ݿ�
	 * @return
	 */
	public Connection connect() {
		try{
	        //���ؼ�ע��JDBC��������
	        Class.forName("com.mysql.jdbc.Driver");
	    }catch(ClassNotFoundException e1){
	    	System.out.println("�Ҳ������������࣬��������ʧ��");
	        e1.printStackTrace();
	    }
	    
	    String url="jdbc:mysql://localhost:3306/Mysql?useSSL=false";    //JDBC��URL    
	    //����DriverManager�����getConnection()���������һ��Connection����
	   
	    try {
	        conn = DriverManager.getConnection(url,"root","19970822zsq");
	        //����һ��Statement����
	        Statement stmt = conn.createStatement(); //����Statement����
	       // System.out.print("�ɹ����ӵ����ݿ⣡");
	       // stmt.close();
	       // conn.close();
	    } catch (SQLException e){
	    	System.out.println("���ݿ�����ʧ��");
	        e.printStackTrace();
	    }
	    return conn;
	}
	/**
	 * ��������InitialInfo()
	 * ���ߣ������
	 * ��������
	 * ����ֵ����
	 * ���ܣ����ڳ�ʼ��������Ϣ����ע��֮���Զ�����
	 * @return
	 */
	public void InitialInfo() {
		 Connection cnn=connect();   
		 String sql="insert into StudentInfo(id,stu_id,school_id,stu_name,stu_gender,phone_number,email) values(?,?,?,?,?,?,?)";
		    try{  
		        PreparedStatement preStmt =cnn.prepareStatement(sql); 
		        System.out.println("����������˺� ");
		        String a=sc.nextLine();
		        preStmt.setString(1,a);  
		        System .out.println("������һ��ͨ��");
		        int b=sc.nextInt();
		        preStmt.setInt(2,b);  
		        System .out.println("������ѧУ���");
		        int c=sc.nextInt();
		        preStmt.setInt(3,c);
		        System.out.println("������������� ");
		        String d=sc.nextLine();
		        preStmt.setString(4,d);  
		        System.out.println("����������Ա� ");
		        int e=sc.nextInt();
		        preStmt.setInt(5,e); 
		        System.out.println("��������ĵ绰���� ");
		        String f=sc.nextLine();
		        preStmt.setString(6,f);  
		        System.out.println("������������� ");
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
	 * ��������BookTime()
	 * ���ߣ������
	 * ��������
	 * ����ֵ����
	 * ���ܣ�����Ԥ���ܲ�ʱ��
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
	 * ��������BookTime()
	 * ���ߣ������
	 * ��������
	 * ����ֵ����
	 * ���ܣ����ڲ鿴���е�Ԥ��
	 * @return
	 */
	public void CheckBook() {
		//��ѯ���
	    String sql = "select * from StudentInfo"; 
	    //����
	    Connection conn = connect();
        PreparedStatement pst = null;
	    try {
         pst = (PreparedStatement) conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	        		System.out.println(rs.getInt("hour")+"��"+rs.getInt("minute")+"�֣�"+rs.getInt("month"));
	            }     
	        } 
	    catch (SQLException e) {
	        	 e.printStackTrace();  
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
				/*���ѷ�ʽ*/
					 }
				  }
				rs.close();
				conn.close();
			}
	    catch(SQLException e) {
				//���ݿ�����ʧ���쳣����
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
