package com.github.mpalambonisi.lab03.q2andq3;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerQ2Q3 {
	public static void main(String[] args) throws IOException {

		if (args.length < 1){
			System.out.println("Please provide a port number.");
			return;
		}

		int port = Integer.parseInt(args[0]);
		ServerSocket s = new ServerSocket(port);
		InetAddress serverAddress = InetAddress.getLocalHost();
		System.out.println("Server started on IP: " + serverAddress + " and port = " + port);

		System.out.println("Working: " + s);
		try {
			Socket mysocket = s.accept();
			try {
				System.out.println("Connection accepted: "+ mysocket);
				BufferedReader in = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mysocket.getOutputStream())),true);
				while (true) {
					String str = in.readLine();
					if (str.equals("END")) break;
					System.out.println("Received: " + str);
					out.println(str);
				}			
			} finally {
				System.out.println("Closing...");
				mysocket.close();
			}
		} finally {
			s.close();
		}
	}
}