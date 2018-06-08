package com.wey.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {

	public static void main(String[] args) {
		try {
			// 1、创建客户端Socket，指定服务器地址和端口
			Socket socket = new Socket("localhost", 10086);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter write = new PrintWriter(socket.getOutputStream());

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String readline;
			readline = br.readLine(); // 从系统标准输入读入一字符串
			while (!readline.equals("end")) {
				write.println(readline);
				write.flush();
				System.out.println("Client:" + readline);
				System.out.println("Server:" + in.readLine());
				readline = br.readLine(); // 从系统标准输入读入一字符串
			}
			write.close(); // 关闭Socket输出流
			in.close(); // 关闭Socket输入流
			socket.close(); // 关闭Socket

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
