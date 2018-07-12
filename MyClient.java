import java.io.*;
import java.net.*;

class MyClient{
	
	Socket s;
	DataInputStream din;
	DataOutputStream dout;
	
	MyClient()throws IOException{
	
	s=new Socket("localhost",10);
	System.out.println(s);
	
	din=new DataInputStream(s.getInputStream());
	dout=new DataOutputStream(s.getOutputStream());
	clientChat();
	}

	public void clientChat()throws IOException{
	My m=new My(din);
	Thread t1=new Thread(m);
	t1.start();
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	String s1;
	do{
	s1=br.readLine();
	dout.writeUTF(s1);
	dout.flush();	
	}while(!s1.equals("stop"));
	}

	public static void main(String... s)throws IOException
	{
		new MyClient();
	}
}
class My implements Runnable{
	DataInputStream din;
	My(DataInputStream din){
		this.din=din;
	}
	public void run(){
	String s2="";
	do{
		try{
			s2=din.readUTF();
			System.out.println(s2);
		}catch(Exception e){
			System.out.println(e);
			}
		}while(!s2.equals("stop"));
	}
	
}