package com.hust.soict.tuanvh175995.client_server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;
import com.hust.soict.tuanvh175995.helper.*;



public class Server {
	
	private static class Sorter extends Thread {
		private Socket socket;
		private int clientNumber;
		
		public Sorter(Socket socket, int clientNumber) {
			this.socket = socket;
			this.clientNumber = clientNumber;
			System.out.println("new client #"+clientNumber + " connected at "+ socket);
		}
		
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.println("hello, you are client# " + clientNumber);
				while(true) {
					
					//input
					String input = in.readLine();
					//check if message is null or has only " "
					if(input.isEmpty() || input == null) {
						break;
					} 
					//create an array to store input
					String[] nums = input.split(" ");
					
					//convert to int
					int[] intarr = new int[nums.length];
					int i = 0;
					for(String textValue : nums) {
						intarr[i] = Integer.parseInt(textValue);
						i++;
					 	}
					
					new SelectionSort().sort(intarr);
					 //back to String
					
					
					String strArray[] = Arrays.stream(intarr).mapToObj(String::valueOf).toArray(String[]::new);
					out.println(Arrays.toString(strArray));
					 }

			} catch (IOException e){
				System.out.println("error handling client #"+clientNumber);
				
			} finally {
				try {socket.close();} catch (IOException e){}
				System.out.println("connection with client #"+clientNumber + " closed");
			}
		}
	}
	
	
	
	public static void main() throws IOException {
		System.out.println("server is running");
		int clientNumber = 0;
		try (ServerSocket listener = new ServerSocket(80)){
			while(true) {
				new Sorter(listener.accept(), clientNumber++).start();
			}
		}
	}
}
