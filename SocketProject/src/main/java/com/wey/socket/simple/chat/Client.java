package com.wey.socket.simple.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	Socket socket;
	
	public static void main(String[] args) {
		new Client();
	}
	
	public Client() {
		try {
			socket = new Socket("localhost", 10086);
			new ClientThread().start();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg;
			while((msg=br.readLine())!=null) {
				System.out.println(msg);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	class ClientThread extends Thread {

		@Override
		public void run() {
		   
		    try {
		    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
				String msg;
				while(true) {
					msg = bf.readLine();
					//聊天内容
					//pw.println(msg);
					//pw.flush();\
					
					//Socket传递一个对象
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(new User("1",msg));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
