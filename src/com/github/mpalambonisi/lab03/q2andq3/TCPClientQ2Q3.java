package com.github.mpalambonisi.lab03.q2andq3;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCPClientQ2Q3 {
	public static void main(String[] args) throws IOException {

		File file = new File("src/com/github/mpalambonisi/lab03/config.txt");
		Scanner sc = new Scanner(file);

		String serverIP = sc.nextLine();
		int serverPort = Integer.parseInt(sc.nextLine());

		InetAddress address = InetAddress.getByName(serverIP); // resolve server ip address
		System.out.println("Connecting to server at " + address + " on port : " + serverPort);

		Socket mysocket = new Socket(address, serverPort);
		try {
			System.out.println("Socket : " + mysocket);
			BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mysocket.getOutputStream())),true);
			for(int i = 0; i < 10; i ++) {
				out.println("Hi: " + i);
				String str = in.readLine();
				System.out.println(str);
			}
			out.println("END");
		} finally {
			System.out.println("Closing...");
			mysocket.close();
		}
	}
}