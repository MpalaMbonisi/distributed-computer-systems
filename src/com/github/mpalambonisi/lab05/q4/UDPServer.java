package com.github.mpalambonisi.lab05.q4;

import java.io.IOException;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

public class UDPServer {
    private static final int PORT = 9876;
    private static final int BUFFER_SIZE = 1024;
    private static final Set<InetSocketAddress> clients = new HashSet<>();

    public static void main(String[] args) {
        try(DatagramSocket socket = new DatagramSocket(PORT)){
            System.out.println("UDP Server started on port : " + PORT + ".");

            while(true){
                // receive a message from a client
                byte[] buffer = new byte[BUFFER_SIZE];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(packet.getData(), 0, packet.getLength());
                InetSocketAddress clientAddress = new InetSocketAddress(packet.getAddress(), packet.getPort());

                // add a client to the list
                synchronized (clients){
                    clients.add(clientAddress);
                }

                System.out.println("Received from " + clientAddress + ": " + message);

                // broadcast the message to all clients
                synchronized (clients){
                    for (InetSocketAddress client: clients){
                        if (!client.equals(clientAddress)){
                            byte[] sendBuffer = message.getBytes();
                            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length,
                                    client.getAddress(), client.getPort());
                            socket.send(sendPacket);
                        }
                    }
                }
            }
        }catch(IOException e){
            System.out.println("Error occurred during connection : " + e.getMessage());
        }
    }
}
