package server;
import db.DataProcess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject; 
import org.apache.catalina.User;
import org.apache.struts2.json.annotations.JSON;  

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class JsonAction extends ActionSupport{
	private Map<String, Object> dataMap=new HashMap<String,Object>(); 
	private ArrayList<HashMap<String, Object>>answer=new ArrayList<HashMap<String, Object>>();  
//	private String returndata;
//	public String getReturndata() {
//		return returndata;
//	}
//	public void setReturndata(String returndata) {
//		this.returndata = returndata;
//	}
	
public ArrayList<HashMap<String, Object>> getAnswer() {
		return answer;
	}

	public void setAnswer(ArrayList<HashMap<String, Object>> answer) {
		this.answer = answer;
	}

public String execute() throws Exception{
	ActionContext actionContext=ActionContext.getContext();
	Map<String, Object> reqMap=actionContext.getParameters();
	for(String key:reqMap.keySet()){
		JSONObject jsonObject=JSONObject.parseObject(key);
		System.out.println(jsonObject);
		dataMap=(Map<String, Object>)jsonObject;
		answer=DataProcess.parseMap(dataMap);

		
		
		
		//these code are for test
//		System.out.println(key);
//		String[] valueStrings=(String[])reqMap.get(key);
//		System.out.println(valueStrings);
	}
	System.out.println("Page arrived.");
	return SUCCESS;
}
}
