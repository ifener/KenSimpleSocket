package com.wey.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketImpl {

	public static void main(String[] args) {
		try {
			// 1������һ����������Socket����ServerSocket��ָ���󶨵Ķ˿ڣ��������˶˿�
			ServerSocket serverSocket = new ServerSocket(10086);// 1024-65535��ĳ���˿�
			// 2������accept()������ʼ�������ȴ��ͻ��˵�����
			Socket socket = serverSocket.accept();
			String line;
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer=new PrintWriter(socket.getOutputStream());
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			line=br.readLine();
			while(!line.equals("end")){
				 writer.println(line);
				 writer.flush();
				 System.out.println("Server:"+line);
				 System.out.println("Client:"+in.readLine());
				 line=br.readLine();
			}
			writer.close(); //�ر�Socket�����
			in.close(); //�ر�Socket������
			socket.close(); //�ر�Socket
			serverSocket.close(); //�ر�ServerSocket
			 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
