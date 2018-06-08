package com.wey.socket.simple.chat;

import java.io.BufferedReader;
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
				// �����ȴ���ÿ���յ�һ������ʹ���һ���µ�����ʵ��
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
				//������
				/*br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				msg ="��ӭ["+socket.getInetAddress()+"]���������ң���ǰ��������:"+clients.size()+"�ˣ�";
				sendMsg();
				while ((msg = br.readLine()) != null) {

                    msg = "��" + socket.getInetAddress() + "��˵��" + msg;
                    sendMsg();

                }*/
				
				//Socket����һ������
				try {
					ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
					User user = (User)objectInputStream.readObject();
					System.out.println(user);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
