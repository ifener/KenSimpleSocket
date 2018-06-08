package com.wey.socket.bytes.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	List<Socket> clients;
	ServerSocket serverSocket;
	
	public static void main(String[] args) {
		new Server();
	}
	
	public Server() {
		
		try {
			
			clients = new ArrayList<Socket>();
			serverSocket = new ServerSocket(10086);
			while(true) {
				// 阻塞等待，每接收到一个请求就创建一个新的连接实例
				Socket socket = serverSocket.accept();
				clients.add(socket);
				new SocketThread(socket).start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class SocketThread extends Thread {
		Socket socket;
		BufferedReader br;
		PrintWriter pw;
		String msg;
		
		public SocketThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				 byte[] bytes = new byte[1]; // 一次读取一个byte
				 String ret ="";
				while(dis.read(bytes)!=-1) {
					ret += new String(bytes,"UTF-8");
					
					if(dis.available()==0)
					{
						System.out.println(ret);
						ret="";
					}
				}
				
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public String bytesToHexString(byte[] src) {
	        StringBuilder stringBuilder = new StringBuilder("");
	        if (src == null || src.length <= 0) {
	            return null;
	        }
	        for (int i = 0; i < src.length; i++) {
	            int v = src[i] & 0xFF;
	            String hv = Integer.toHexString(v);
	            if (hv.length() < 2) {
	                stringBuilder.append(0);
	            }
	            stringBuilder.append(hv);
	        }
	        return stringBuilder.toString();
	    }
		
		public void sendMsg() {
			for(int i=0;i<clients.size();i++)
			{
				try {
					pw = new PrintWriter(clients.get(i).getOutputStream(),true);
					pw.println(msg);
					//pw.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		
	}

}
