package server;

import java.util.List;
import java.util.Map;


import com.opensymphony.xwork2.ActionSupport;

public class Demo2Action extends ActionSupport{
	private List<String> list;
	private Map<String, String> map;
public String execute() throws Exception{
	System.out.println("Request arrived for demo2Action!");
	System.out.println("list:"+list);
	System.out.println("map:"+map);
	return "success";
}
public List<String> getList() {
	return list;
}

public void setList(List<String> list) {
	this.list = list;
}
public Map<String, String> getMap() {
	return map;
}
public void setMap(Map<String, String> map) {
	this.map = map;
}
}
