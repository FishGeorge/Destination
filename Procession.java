/*����������������ܴ���/δ�ܴ������Ѿ���������/ʣ�����ڣ�
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
		
		la1=new JLabel("�ѻ�÷���");
		la2=new JLabel("������");
		la3=new JLabel("�Ѿ����ճ�");
		la4=new JLabel("���ճ�");
		
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
		jtf1.setText(score);//��ȡ�ѻ�÷���
		jtf2.setText("total");//Ŀ�����
		jtf3.setText("now-start");//�ѹ��ճ�
		jtf4.setText("end-start");//���ճ�
		
		
		
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
