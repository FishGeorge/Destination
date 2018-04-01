package server;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Account {
private String name=new String();
private String passWord=new String();
private String mailBox=new String();
private String phoneNumber=new String();
public Account(String n,String p,String m,String phoneNum){
	name=n;
	passWord=p;
	mailBox=m;
	phoneNumber=phoneNum;
}
public String getName(){
	return name;
}
public String getPassWord(){
	return passWord;
}

public String getMailBox(){
	return mailBox;
}
public String getPhoneNumber(){
	return phoneNumber;
}
public void setName(String n){
	name=n;
}
public void setPassWord(String n){
	passWord=n;
}
public void setMailBox(String n){
	mailBox=n;
}
public void setPhoneNumber(String n){
	phoneNumber =n;
}

}
