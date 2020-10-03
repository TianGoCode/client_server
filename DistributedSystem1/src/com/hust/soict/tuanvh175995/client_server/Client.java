package com.hust.soict.tuanvh175995.client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 80);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println(in.readLine());
			Scanner scanner = new Scanner(System.in);
			// cau 6
			while(true) {
				//input
				String message = scanner.nextLine();
				//check if message is null or has only " "
				if(message.isBlank()) {
					break;
				} 
				out.println("your message from client: " + message);
				 }

			//
			socket.close();
			scanner.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
