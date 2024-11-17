package com.github.mpalambonisi.lab05.q4;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    private static final int SERVER_PORT = 9876;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try(DatagramSocket socket = new DatagramSocket()){
            InetAddress serverAddress = InetAddress.getByName("localhost");
            System.out.println("UPD Client started. Type your messages:");

            // Thread to receive messages
            Thread receiveThread = new Thread(()->{
                try {
                    byte[] buffer = new byte[BUFFER_SIZE];
                    while(true){
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        socket.receive(packet);
                        String message = new String(packet.getData(), 0, packet.getLength());
                        System.out.println("Received: " + message);
                    }
                }catch(IOException e){
                    System.out.println("Connection closed");
                }
            });

            Thread sendThread = new Thread(()->{
               try(Scanner scanner = new Scanner(System.in)){
                   while(true){
                       String message = scanner.nextLine();
                       byte[] buffer = message.getBytes();
                       DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, SERVER_PORT);
                       socket.send(packet);
                       System.out.println("You: " + message);
                   }
               }catch(IOException e){
                   System.out.println("Connection error : " + e.getMessage());
               }
            });

            receiveThread.start();
            sendThread.start();

            receiveThread.join();
            sendThread.join();
        }catch(IOException | InterruptedException e){
            System.out.println("Error occurred : " + e.getMessage());
        }
    }
}
