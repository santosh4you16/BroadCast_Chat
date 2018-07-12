import java.util.*;
import java.io.*;
import java.net.*;
	
class MyServer{
	ArrayList al=new ArrayList();
	ServerSocket ss;
	Socket s;
	
	
	MyServer()throws Exception{
	ss=new ServerSocket(10);
	
	while(true){
	s=ss.accept();
	System.out.println(s);
	System.out.println("Client Connected");
	al.add(s);
	MyThread1 r=new MyThread1(s,al);
	Thread t=new Thread(r);
	t.start();
	}
	
	}
	
	public static void main(String... s)throws Exception{
	new MyServer();
	}
}

class MyThread1 implements Runnable{
	Socket s;
	ArrayList al;
	
	MyThread1(Socket s, ArrayList al){
	this.s=s;
	this.al=al;
	}

	public void run(){
	String s1;
	try{
	DataInputStream din=new DataInputStream(s.getInputStream());
	do{
		s1=din.readUTF();
		System.out.println(s1);
		if(!s1.equals("stop"))
			tellEveryOne(s1);
		else
		{
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
			dout.writeUTF(s1);
			dout.flush();
			al.remove(s);
		}
	}while(!s1.equals("stop"));
	}catch(Exception e){
	System.out.println(e);
	}
	}

	public void tellEveryOne(String ss1)throws Exception{
		Iterator i=al.iterator();
		while(i.hasNext()){
		Socket sc=(Socket)i.next();
		DataOutputStream dout=new DataOutputStream(sc.getOutputStream());
		dout.writeUTF(ss1);
		dout.flush();		
		}
		
	}
				
}