/*
 * 功能：定时器，定时向服务器发送请求，获取最新的分数和日程进度
 */
package package1;
import java.util.Calendar;  
import java.util.Date;  
import java.util.Timer;  
import java.util.TimerTask;  
import java.util.Map;
import java.util.HashMap;
  

public class NewTimer {
	private static String score;
	
	public String getScore()
	{
		return score;
	}
	  public static void timer4() {  
	        Calendar calendar = Calendar.getInstance();  
	        calendar.set(Calendar.HOUR_OF_DAY, 12); // 控制时  
	        calendar.set(Calendar.MINUTE, 0);       // 控制分  
	        calendar.set(Calendar.SECOND, 0);       // 控制秒  
	  
	        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的12：00：00  
	  
	        Timer timer = new Timer();  
	        timer.scheduleAtFixedRate(new TimerTask() {  
	            public void run() {  
	            	Map item=new HashMap();
	            	item.put("id", "score");
	            	score=new HttpClient().createObject(item);
	            }  
	        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行  
	    }  
	 
	}  

