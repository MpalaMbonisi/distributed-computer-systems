package com.github.mpalambonisi.lab05.q3;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;


public class ServerThread implements Runnable {
    // A reference to the client socket
    private final Socket clientSocket;
    private static final List<PrintWriter> clientWriters = new ArrayList<>();
  
    // A constructor to get the socket handler
    public ServerThread(Socket socket)
    {
        // Here we assign the client socket to the thread
        this.clientSocket = socket; 
        System.out.println("The Server Thread No. " + Thread.currentThread().threadId() + " for the client " + socket.getPort() + " has started!");
    } 

    @Override
    public void run() 
    { 
        try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)){

            synchronized (clientWriters){
                clientWriters.add(out);
            }

            // Read messages from this client and broadcast to all
            String line;
            while((line = in.readLine()) != null){
                System.out.println("Client " + clientSocket.getPort() + ": " + line);
                // broadcast the message to all clients
                synchronized (clientWriters){
                    for (PrintWriter writer: clientWriters){
                        writer.println("Client " + clientSocket.getPort() + ": " + line);
                    }
                }
            }
        } 
        catch (IOException e) {
            System.out.println("Connection closed for client " + clientSocket.getPort());
        }
        finally { 
            try { 
                clientSocket.close();
            } 
            catch (IOException e) {
                System.out.println("Error occurred: " + e.getMessage());
            } 
        } 
    } 
} 
