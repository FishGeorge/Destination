package db;

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
public static void main(String[] args) throws Exception{
	String dbURL="jdbc:mysql://localhost:3306/destination?verifyServerCertificate=false&useSSL=false";
	//String dbURL="jdbc:mysql://localhost:3306/manageinfo?verifyServerCertificate=false&useSSL=false";
	//String dbURL="jdbc:mysql://localhost:3306/sport_records?verifyServerCertificate=false&useSSL=false";
	String user="root";
	String password="77789op";
	
	String createAccountTableSql="CREATE TABLE  account(id varchar(20) not null,tele varchar(15) not null,"
		+"mail varchar(20) null,name varchar(10) not null,password varchar(30) not null,"
		 +"sex varchar(10) not null,info_id varchar(20) not null,friend varchar(100) null,"
		 +"visible_for_differentfriend varchar(100) null, group_id varchar(100) null,"
		 +"health_state varchar(50) null,identity varchar(10) not null, sport_state varchar(10) null,"
		 +"PRIMARY KEY(id));";
	String createTeacherInfoTableSql="CREATE TABLE teacherinfo(id varchar(20) not null,real_name varchar(10) null,work_experience varchar(1000) null,study_experience varchar(1000) null,academic_title varchar(100) null,university varchar(10) null,employeenum varchar(20) null,PRIMARY KEY(id))ENGINE=InnoDB  DEFAULT CHARSET=utf8;";
	String createRuleTableSql="CREATE TABLE rule(itemid int(6) unsigned not null auto_increment,		university varchar(10),sex varchar(10),scorestandard double(16,3) not null,timerequested timestamp not null,lengthrequested double(16,2) not null,health_state varchar(50),publisher_id varchar(20),term varchar(20),movementnumber_requested int(10) not null,	PRIMARY KEY(itemid))ENGINE=InnoDB  DEFAULT CHARSET=utf8;";
	String createStudentInfoTableSql="CREATE TABLE studentinfo(id varchar(20) not null,real_name varchar(10) null,scores varchar(10) null,university varchar(10) null,studentNumber varchar(20) null,PRIMARY KEY(id))ENGINE=InnoDB  DEFAULT CHARSET=utf8;";
	String createRecordTableSql="CREATE TABLE sport_record_of_2( itemid int(6) unsigned not null auto_increment,	single_score double(16,3) not null,	term varchar(20) not null,date date not null,start_time timestamp not null,end_time timestamp not null,area varchar(20) not null,length double(16,2) not null,steps int(10) not null,moment_time varchar(100) not null,speed  double(16,3) not null,calories double(16,3) not null,PRIMARY KEY(itemid))ENGINE=InnoDB  DEFAULT CHARSET=utf8;";
	String createGroupTableSql="CREATE TABLE chatgroup(group_id varchar(20) not null,group_name varchar(10) not null,group_member varchar(100) not null, PRIMARY KEY(group_id))ENGINE=InnoDB  DEFAULT CHARSET=utf8;";
	try{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("loading");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	Connection conn=DriverManager.getConnection(dbURL,user,password);
	Statement smt=conn.createStatement();
	//DB:destination
	smt.execute(createTeacherInfoTableSql);
	smt.execute(createGroupTableSql);
	smt.execute(createStudentInfoTableSql);	
	smt.execute(createAccountTableSql);
	
	//DB:manageinfo
	//smt.execute(createRuleTableSql);
	
	//DB:sport_record
	smt.execute(createRecordTableSql);
}
}
