package server;

import com.opensymphony.xwork2.ActionSupport;

public class Demo1Action extends ActionSupport{
	private String name="";
	private String passWord="";
	private String mailBox="";
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getMailBox() {
		return mailBox;
	}
	public void setMailBox(String mailBox) {
		this.mailBox = mailBox;
	}
	
	public String execute() throws Exception{
	System.out.println("name:"+name+"  passwd:"+passWord+"  mailbox:"+mailBox);
	return SUCCESS;
}
}
