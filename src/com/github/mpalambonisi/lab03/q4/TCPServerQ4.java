package com.github.mpalambonisi.lab03.q4;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TCPServerQ4 {
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
				handleClient(mysocket);
			} finally {
				System.out.println("Closing...");
				mysocket.close();
			}
		} finally {
			s.close();
		}
	}

	public static void handleClient(Socket clientSocket){
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())),true);
			String command;
			while((command = in.readLine()) != null){
				String[] commandParts = command.split(" ", 2); // split command and parent (if any)
				if (commandParts[0].equals("STATS") && commandParts.length ==2){
					String text = commandParts[1];
					String stats = getTextStats(text);
					out.println(stats);
				}
				else if (commandParts[0].equals("ANAGRAM") && commandParts.length == 2){
					String text = commandParts[1];
					String anagram = getAnagram(text);
					out.println(anagram);
				}
				else if (commandParts[0].equals("DROP")){
					out.println("Connection closed by server.");
					break;
				}
				else{
					out.println("ERROR: Invalid command.");
				}
			}

		}catch (IOException e){
			System.out.println("Something went wrong..." + e.getMessage());
		}
	}

	private static String getTextStats(String text){
		int lowercase = 0, uppercase = 0, digits = 0, others = 0;
		for (char c: text.toCharArray()){
			if (Character.isLowerCase(c)) lowercase++;
			else if (Character.isUpperCase(c)) uppercase++;
			else if(Character.isDigit(c)) digits++;
			else others++;
		}

		return String.format("Lowercase: %d, Uppercase: %d, Digits: %d, Others: %d", lowercase, uppercase, digits, others);
	}

	private static String getAnagram(String text){
		List<Character> characterList = new ArrayList<>();
		for (char c : text.toCharArray()){
			characterList.add(c);
		}
		Collections.shuffle(characterList); // shuffle to generate an anagram
		StringBuilder anagram = new StringBuilder();
		for (char c: characterList){
			anagram.append(c);
		}
		return anagram.toString();
	}
}