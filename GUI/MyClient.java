import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
public class MyClient extends JFrame implements ActionListener {
	Socket s;
	DataInputStream din;
	DataOutputStream dout;
	JLabel jl;
	JTextField jt;
	JButton jb;
	JTextArea jta;
		
	MyClient()throws Exception{
		super("EasyChat");
		this.setSize(400,400);
		this.setVisible(true);
		this.setLayout(null);
		jta=new JTextArea();
		jta.setBounds(30,60,300,100);
		jta.setEditable(false);
		this.add(jta);
		jl=new JLabel("WECOME USER!");
		jl.setBounds(30, 10, 100, 30);
		this.add(jl);
		jt=new JTextField();
		jt.setBounds(30,200,300,30);
		this.add(jt);
		jb=new JButton("SEND");
		jb.addActionListener(this);
		jb.setBounds(200,300,100,20);
		this.add(jb);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		s=new Socket("localhost",10);
		System.out.println(s);
		
		din=new DataInputStream(s.getInputStream());
		dout=new DataOutputStream(s.getOutputStream());
		clientChat();		
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jb){
			
			String s1;
			try{
				//do{
					
					s1=jt.getText();	
				jta.append("YOUR MESS: "+s1+"\n");
				dout.writeUTF(s1);
				dout.flush();
				jt.setText(null);
				//}while(!s1.equals("stop"));
				}catch(Exception e1){
					System.out.println(e1);
				}

			
		}

		
	}
	
	public void clientChat(){
		
		MyThread m=new MyThread(din);
		Thread t1=new Thread(m);
		t1.start();
			}
	
	
	
	public static void main(String d[])throws Exception{
		new MyClient();
		
	}
	public class MyThread implements Runnable{
	
		//JTextArea jta;
		DataInputStream din;
		MyThread(DataInputStream din){
			this.din=din;
			//this.jta=jta;
			
		}
		public void run(){
		String s2="";
		do{
			try{
				s2=din.readUTF();
				if(s2.equalsIgnoreCase("stop")){
				
				new MyClient().dispose();
				}
				jta.append("CLIENT MESS: "+s2+"\n");
			}catch(Exception e){
				System.out.println(e);
				}
			}while(!s2.equals("stop"));
		}
		
	

}

}
