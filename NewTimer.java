/*
 * ���ܣ���ʱ������ʱ��������������󣬻�ȡ���µķ������ճ̽���
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
	        calendar.set(Calendar.HOUR_OF_DAY, 12); // ����ʱ  
	        calendar.set(Calendar.MINUTE, 0);       // ���Ʒ�  
	        calendar.set(Calendar.SECOND, 0);       // ������  
	  
	        Date time = calendar.getTime();         // �ó�ִ�������ʱ��,�˴�Ϊ�����12��00��00  
	  
	        Timer timer = new Timer();  
	        timer.scheduleAtFixedRate(new TimerTask() {  
	            public void run() {  
	            	Map item=new HashMap();
	            	item.put("id", "score");
	            	score=new HttpClient().createObject(item);
	            }  
	        }, time, 1000 * 60 * 60 * 24);// �����趨����ʱÿ��̶�ִ��  
	    }  
	 
	}  

