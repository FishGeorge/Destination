package package1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;



public class MainFrame extends JFrame {
	private JButton but;
	private JTextField jtf;
	private String mess;
	private Scanner sc;
	
	public MainFrame()
	{
		Container cp=getContentPane();
		cp.setLayout(new FlowLayout());
		
		but=new JButton("�ܲ���Ϣ����");
		cp.add(but);
		jtf=new JTextField("0",10);
		cp.add(jtf);
		
		
		but.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				JFrame newFrame=new JFrame("Destination-Report");
				JPanel pan1=new JPanel(new FlowLayout());
				JLabel la=new JLabel("�����뷢������");
				JTextField jtf1=new JTextField("0",10);
				
				//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				// try {
			     //       mess = br.readLine();
			    //    } catch (IOException e1) {
			   //         e1.printStackTrace();
			    //    }*/
			   jtf1.setText("���첻�ܲ�");
			   mess=jtf1.getText();
				 
				 JButton but1=new JButton("ȷ�Ϸ���");
				 but1.addActionListener(new ActionListener()
						 {
					 public void actionPerformed(ActionEvent e1)
					 {
						 if(mess==null)
							 JOptionPane.showMessageDialog(null, "��������Ϊ�գ�����������","Destination-Attention",JOptionPane.WARNING_MESSAGE);
						 else
							 jtf1.setText("0");
						 System.out.println(mess);
						 JOptionPane.showMessageDialog(null, "�����ѷ���","Destination-Message",JOptionPane.INFORMATION_MESSAGE);
						 Map item=new HashMap();
						 item.put("id", mess);
						 
						 jtf.setText("�����ѳɹ�����");
						// jtf.setText(new HttpClient().createObject(item));			 
					 }
						 });
				 
				 pan1.add(la);
				 pan1.add(jtf1);
				 newFrame.add(pan1,BorderLayout.CENTER);
				 newFrame.add(but1,BorderLayout.SOUTH);
				 newFrame.setSize(200, 400);
				 newFrame.setVisible(true);			
			}
				});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,400);
		setVisible(true);
		setTitle("Destination-Report");
		
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainFrame();

	}

}
