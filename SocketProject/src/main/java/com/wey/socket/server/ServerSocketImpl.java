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
			// 1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
			ServerSocket serverSocket = new ServerSocket(10086);// 1024-65535的某个端口
			// 2、调用accept()方法开始监听，等待客户端的连接
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
			writer.close(); //关闭Socket输出流
			in.close(); //关闭Socket输入流
			socket.close(); //关闭Socket
			serverSocket.close(); //关闭ServerSocket
			 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
