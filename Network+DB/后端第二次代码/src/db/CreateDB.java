package db;
/*Author:Maugham(刘瑞琦)
 * Time:2018-4-3
 * Tele:15764949052
 * QQ:3320089237
 * 
 * */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.catalina.User;

//import com.mysql.jdbc.Statement;

public class CreateDB {
	private static String destinationURL="jdbc:mysql://106.14.151.245:3306/destination?verifyServerCertificate=false&useSSL=false";
//	private static String manageinfoURL="jdbc:mysql://localhost:3306/manageinfo?verifyServerCertificate=false&useSSL=false";
//	private static String sport_recordURL="jdbc:mysql://localhost:3306/sport_records?verifyServerCertificate=false&useSSL=false";
	private static String user="root";
	private static String password="";
	public static void main(String[] args)throws Exception{
		CreateDB.createDBAuto();
	}
	private static void create(String url,String sql) throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("loading");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Connection conn=DriverManager.getConnection(url,user,password);
		Statement smt=conn.createStatement();
		smt.execute(sql);
	}
public static void createDBAuto() throws Exception{	
//	String createAccountTableSql="CREATE TABLE  account(id varchar(20) not null,tele varchar(15) not null,"
//		+"mail varchar(20) null,name varchar(10) not null,password varchar(30) not null,"
//		 +"sex varchar(10) not null,info_id varchar(20) not null,friend varchar(100) null,"
//		 +"visible_for_differentfriend varchar(100) null, group_id varchar(100) null,"
//		 +"health_state varchar(50) null,identity varchar(10) not null, sport_state varchar(10) null,"
//		 +"PRIMARY KEY(id));";
//	String createTeacherInfoTableSql="CREATE TABLE teacherinfo(id varchar(20) not null,real_name varchar(10) null,work_experience varchar(1000) null,study_experience varchar(1000) null,academic_title varchar(100) null,university varchar(10) null,employeenum varchar(20) null,PRIMARY KEY(id))ENGINE=InnoDB  DEFAULT CHARSET=utf8;";
//	String createRuleTableSql="CREATE TABLE rule(itemid int(6) unsigned not null auto_increment,		university varchar(10),sex varchar(10),scorestandard double(16,3) not null,timerequested timestamp not null,lengthrequested double(16,2) not null,health_state varchar(50),publisher_id varchar(20),term varchar(20),movementnumber_requested int(10) not null,	PRIMARY KEY(itemid))ENGINE=InnoDB  DEFAULT CHARSET=utf8;";
//	String createStudentInfoTableSql="CREATE TABLE studentinfo(id varchar(20) not null,real_name varchar(10) null,scores varchar(10) null,university varchar(10) null,studentNumber varchar(20) null,PRIMARY KEY(id))ENGINE=InnoDB  DEFAULT CHARSET=utf8;";
//	String createRecordTableSql="CREATE TABLE sport_record_of_2( itemid int(6) unsigned not null auto_increment,	single_score double(16,3) not null,	term varchar(20) not null,date date not null,start_time timestamp not null,end_time timestamp not null,area varchar(20) not null,length double(16,2) not null,steps int(10) not null,moment_time varchar(100) not null,speed  double(16,3) not null,calories double(16,3) not null,PRIMARY KEY(itemid))ENGINE=InnoDB  DEFAULT CHARSET=utf8;";
//	String createGroupTableSql="CREATE TABLE chatgroup(group_id varchar(20) not null,group_name varchar(10) not null,group_member varchar(100) not null, PRIMARY KEY(group_id))ENGINE=InnoDB  DEFAULT CHARSET=utf8;";
//	
//	create(destinationURL,createAccountTableSql);
//	create(destinationURL,createGroupTableSql);
//	create(destinationURL,createStudentInfoTableSql);
//	create(destinationURL,createTeacherInfoTableSql);
//	
//	create(manageinfoURL,createRuleTableSql);
//	
//	create(sport_recordURL,createRecordTableSql);
	
	String createAccount="create table AccountInfo(id int(30) not null auto_increment,a_type int(30),a_id int(30),a_name char,a_password char,nickname char,user_type int(30),user_id int(30),gender int(30),birthday int(30),height int(30),weight int(30),primary key (id));";
	String createAccountSportsRecord="create table AccountSportsRecord(id int(30) not null auto_increment,a_id int(30),date char,time char,location char,distance int(30),cost int(30),primary key (id));";
	String createFriendShip="create table Friendship(id int(30) not null auto_increment,a_id_1 int(30),a_id_2 int(30),primary key (id));";
	String createGroupShipString="create table Groupship(id int(30) not null auto_increment,group_id int(30),group_name char,member_id int(30),member_type int(30),primary key (id));";
	String createStudentInfoString="create table StudentInfo(id int(30) auto_increment,stu_id int(30),school_id int(30),stu_name char,stu_gender int(30),phone_number char,email char,score int(30),primary key (id) );";
	String createTeacherInfoString="create table TeacherInfo( id                   int(30) not null auto_increment,tea_id               int(30),school_id            int(30),tea_name             char, tea_type             int(30),phone_number         char,email                char,primary key (id));";
	String createStudentSportsRecordString="create table StudentSportsRecord(id int(30) not null auto_increment,school_id int(30),stu_id int(30),type int(30),date char,time char,primary key (id));";
	String createUniversityRule="create table UniversityRule(id int(30) not null auto_increment,school_id int(30),school_name char,author_id int(30),period char,time char,runtime char,pass_score int(30),score_set char,run_limit char,standard char,run_standard char,primary key (id));";
	String createPicture="create table picture(  id int(30) not null auto_increment,a_id int(30),img  longblob,caption varchar(100),primary key (id));";
//	create(destinationURL,createAccount);
//	create(destinationURL,createAccountSportsRecord);
//	create(destinationURL,createFriendShip);
//	create(destinationURL,createGroupShipString);
//	create(destinationURL,createStudentInfoString);
//	create(destinationURL,createStudentSportsRecordString);
//	create(destinationURL,createTeacherInfoString);
//	create(destinationURL,createUniversityRule);
	create(destinationURL,createPicture);
	System.out.println("create all successfully");
}
}
