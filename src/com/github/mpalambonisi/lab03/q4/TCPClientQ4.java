package com.github.mpalambonisi.lab03.q4;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCPClientQ4 {
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
			// Example: Send "STATS Hello World"
			out.println("STATS Hello World");
			System.out.println("Server response: " + in.readLine());

			// Example: Send "ANAGRAM Hello"
			out.println("ANAGRAM Hello");
			System.out.println("Server response: " + in.readLine());

			// Example: Send "DROP" to close connection
			out.println("DROP");
			System.out.println("Server response: " + in.readLine());

			// Example: Send "OTHER Mbonisi mpala"
			out.println("OTHER Mbonisi mpala");
			System.out.println("Server response: " + in.readLine());

		} finally {
			System.out.println("Closing...");
			mysocket.close();
		}
	}
}