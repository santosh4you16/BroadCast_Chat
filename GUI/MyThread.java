import java.io.*;

import javax.swing.JTextArea;


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
				jta.append("CLIENT MESS: "+s2);
			}catch(Exception e){
				System.out.println(e);
				}
			}while(!s2.equals("stop"));
		}
		
	

}
