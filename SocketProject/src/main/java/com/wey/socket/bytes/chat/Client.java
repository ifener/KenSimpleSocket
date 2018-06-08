package com.wey.socket.bytes.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
		    	InputStream is = new DataInputStream(System.in);
		    	byte [] b = new byte[1];
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
	            while(is.read(b)!=-1) {
	            	dos.write(b);
	            }  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
