package com.github.mpalambonisi.lab04.q3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient extends Thread {
    private String clientName;
    private DatagramSocket mySocket;
    private InetAddress serverAddress;
    private int serverPort = 6666;
    private byte[] inbuf = new byte[1000];
    private byte[] outbuf;

    public UDPClient(String clientName) {
        this.clientName = clientName; // Fixed assignment
    }

    @Override
    public void run() {
        try {
            // Initialize the socket and server address
            mySocket = new DatagramSocket();
            serverAddress = InetAddress.getByName("localhost");

            // Send a connection message
            sendMessage("CONNECT:" + clientName);
            System.out.println(clientName + " sent connection request.");

            // Thread to receive messages
            Thread receiver = new Thread(this::receiveMessages);
            receiver.start();

            // Sending messages
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print(clientName + " (Type message): ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("END")) {
                    System.out.println(clientName + " is exiting the connection.");
                    sendMessage(message);
                    break;
                }

                sendMessage(clientName + ": " + message);
            }

            // Wait for receiver thread to finish
            receiver.join();
            mySocket.close();
            System.out.println(clientName + " disconnected.");

        } catch (Exception e) {
            System.out.println("Error while connecting " + clientName + ".\n" + e.getMessage());
        }
    }

    private void receiveMessages() {
        try {
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(inbuf, inbuf.length);
                mySocket.receive(receivePacket);
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("\n" + receivedMessage);

                System.out.print(clientName + " (Type message): ");

                if (receivedMessage.equalsIgnoreCase("END")) {
                    System.out.println("Connection closed by the server.");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error in receiving message for " + clientName + ": " + e.getMessage());
        }
    }

    private void sendMessage(String message) {
        try {
            outbuf = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(outbuf, outbuf.length, serverAddress, serverPort);
            mySocket.send(sendPacket);
        } catch (Exception e) {
            System.out.println("Error in sending message for " + clientName + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length == 1){

            UDPClient client = new UDPClient(args[0]);

            client.start();
        }
    }
}
