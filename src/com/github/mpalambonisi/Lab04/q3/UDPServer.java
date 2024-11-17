package com.github.mpalambonisi.lab04.q3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class UDPServer {

    private static class ClientInfo {
        private final InetAddress address;
        private final int port;

        public ClientInfo(InetAddress address, int port) {
            this.address = address;
            this.port = port;
        }

        public InetAddress getAddress() {
            return address;
        }

        public int getPort() {
            return port;
        }
    }

    static final int INPORT = 6666;
    private byte[] inbuf = new byte[1000];
    private byte[] outbuf;
    private DatagramPacket dp = new DatagramPacket(inbuf, inbuf.length);
    private HashMap<String, ClientInfo> clients = new HashMap<>();
    private DatagramSocket mysocket;

    public UDPServer() {
        try {
            mysocket = new DatagramSocket(INPORT);
            System.out.println("The server is up!");

            while (true) {
                mysocket.receive(dp);
                String message = new String(dp.getData(), 0, dp.getLength());

                InetAddress clientAddress = dp.getAddress();
                int clientPort = dp.getPort();

                System.out.println("Received: \"" + message + "\", from " + clientAddress + " : " + clientPort);

                // Handle new client registration
                if (message.startsWith("CONNECT:")) {
                    String clientName = message.split(":")[1];
                    registerClient(clientName, clientAddress, clientPort);
                    continue;
                }

                // Relay messages if 2 clients are connected
                if (clients.size() == 2) {
                    relayMessage(message, clientAddress, clientPort);
                } else {
                    String waitingMessage = "Waiting for another client to connect...";
                    sendMessage(waitingMessage, clientAddress, clientPort);
                }

                // Handle termination
                if (message.equalsIgnoreCase("END")) {
                    System.out.println("Terminating communication....");
                    broadcast("END");
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Communication error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (mysocket != null && !mysocket.isClosed()) {
                mysocket.close();
            }
            System.out.println("Server shut down.");
        }
    }

    private void registerClient(String clientName, InetAddress address, int port) throws IOException {
        if (!clients.containsKey(clientName)) {
            clients.put(clientName, new ClientInfo(address, port));
            System.out.println("Client registered: " + clientName + " (" + address + ":" + port + ")");
            if (clients.size() == 2) {
                System.out.println("Two clients connected. Communication can start.");
                broadcast("Both clients are connected. You may now communicate.");
            }
        }
    }

    private void relayMessage(String message, InetAddress senderAddress, int senderPort) throws IOException {
        for (ClientInfo client : clients.values()) {
            if (!(client.getAddress().equals(senderAddress) && client.getPort() == senderPort)) {
                sendMessage(message, client.getAddress(), client.getPort());
            }
        }
    }

    private void sendMessage(String message, InetAddress address, int port) throws IOException {
        outbuf = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(outbuf, outbuf.length, address, port);
        mysocket.send(sendPacket);
        System.out.println("Sent: \"" + message + "\" to " + address + ":" + port);
    }

    private void broadcast(String message) throws IOException {
        for (ClientInfo client : clients.values()) {
            sendMessage(message, client.getAddress(), client.getPort());
        }
    }


    public static void main(String[] args) {
        new UDPServer();
    }


}
