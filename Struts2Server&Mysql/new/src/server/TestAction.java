package server;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
//import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
public class TestAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
//	private String name;
//	private int pass;
	private Map<String, Object> jsonData;
	public String getJsonList(){
		System.out.println("Request arrived.");
		jsonData=new HashMap<String,Object>();
		Account user=new Account("maugham", "helloworld", "15764949052@163.com", "15764949052");
		jsonData.put("account", user);
		jsonData.put("success", true);
		return ActionSupport.SUCCESS;
	}
	public Map<String, Object> getJsonData(){
		return jsonData;
	}
	public void setJsonData(Map<String, Object> jsonData){
		this.jsonData=jsonData;
	}
}
