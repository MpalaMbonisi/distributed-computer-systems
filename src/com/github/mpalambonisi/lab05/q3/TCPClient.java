package com.github.mpalambonisi.lab05.q3;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class TCPClient {
    public static void main(String[] args)
    {
        try{
            InetAddress address = InetAddress.getByName("localhost");
            try (Socket socket = new Socket(address, TCPMultithreading.PORT);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true))
            {
                System.out.println("Client with the socket " + socket.getLocalPort() + " has started!");

                Thread readThread = new Thread( ()->{
                    try{
                        String response;
                        while((response = in.readLine()) != null){
                            System.out.println(response);
                        }
                    }catch(IOException e){
                        System.out.println("Connection closed by server.");
                    }
                });

                Thread writeThread = new Thread(()->{
                    try(Scanner scanner = new Scanner(System.in)){
                        String message;
                        while(!(message = scanner.nextLine()).equalsIgnoreCase("END")){
                            out.println(message);
                        }
                    }catch(Exception e){
                        System.out.println("Error sending message to server.");
                    }
                });

                readThread.start();
                writeThread.start();

                readThread.join();
                writeThread.join();
            }
            catch (IOException | InterruptedException e) {
                System.out.println("Error with connection : " + e.getMessage());
            }
        }catch(UnknownHostException e){
            System.out.println("Error with connection : " + e.getMessage());
        }
    }
}
