package com.github.mpalambonisi.lab05.q3;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPMultithreading {

    public static final int PORT = 7777;
    
    public static void main(String[] args) {
        // First, we prepare the socket
        ServerSocket myServer = null;
  
        try {
            // We open the socket
            myServer = new ServerSocket(PORT);
            // This is to maintain the connection open even if there is no client
            myServer.setReuseAddress(true);
            // Infinite loop for incoming requests
            System.out.println("The server is up!");
            while (true) {
                // Here we accept the connection of the client
                Socket clientsocket = myServer.accept();
                // Here we inform about establishing the connection 
                System.out.println("New client connected " + clientsocket.getInetAddress().getHostAddress());
  
                // Here we create the new thread to process the client - we must pass the socket!
                System.out.println(clientsocket);
                ServerThread myThread = new ServerThread(clientsocket);
                // ... and start it
                new Thread(myThread).start();
            } 
        } 
        catch (IOException e) {
            System.out.println("Error with connection : " + e.getMessage());
        } 
        finally { 
            if (myServer != null) {
                try { 
                    myServer.close();
                } 
                catch (IOException e) {
                    System.out.println("Error with connection : " + e.getMessage());
                } 
            } 
        } 
    }
    
}
