package server;

import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class Demo3Action extends ActionSupport {
	private Map<String, String> account;
public Map<String, String> getAccount() {
		return account;
	}
	public void setAccount(Map<String, String> account) {
		this.account = account;
	}
public String execute() throws Exception{
	for(String key:account.keySet())
	{
		System.out.println(key+":"+account.get(key));
	}
	return SUCCESS;
}
}
