/*功能描述：获得已跑次数/未跑次数；已经过过日期/剩余日期；
 * 
 */
package package1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Procession extends JFrame {
	private JLabel la1,la2,la3,la4;
	private JTextField jtf1,jtf2,jtf3,jtf4;
	private String score;

	public Procession()
	{
		Container cp=getContentPane();
		cp.setLayout(new FlowLayout());
		
		la1=new JLabel("已获得分数");
		la2=new JLabel("达标分数");
		la3=new JLabel("已经过日程");
		la4=new JLabel("总日程");
		
		jtf1=new JTextField("0",10);
		jtf2=new JTextField("0",10);
		jtf3=new JTextField("0",10);
		jtf4=new JTextField("0",10);
		
		cp.add(la1);
		cp.add(jtf1);
		cp.add(la2);
		cp.add(jtf2);
		cp.add(la3);
		cp.add(jtf3);
		cp.add(la4);
		cp.add(jtf4);
		
		
		score=new NewTimer().getScore();
		int total;
		String start,end;
		String now;
		jtf1.setText(score);//获取已获得分数
		jtf2.setText("total");//目标分数
		jtf3.setText("now-start");//已过日程
		jtf4.setText("end-start");//总日程
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(150,280);
		setVisible(true);
		setTitle("Destination-Information");
	}
	 public static void main(String[] args) 
	  {
		  new Procession();
	  }
	
}
