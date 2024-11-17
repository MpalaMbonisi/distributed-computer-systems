package com.github.mpalambonisi.lab04.q2;

import java.net.*;

public class UDPClient extends Thread {
    // Here are object properties
    private String clientName;
    private DatagramSocket mysocket;
    private InetAddress hostAddress;
    private byte[] inbuf = new byte[1000];
    private byte[] outbuf;
    private DatagramPacket dp = new DatagramPacket(inbuf, inbuf.length);

    public UDPClient(String clientName) {
        this.clientName = this.clientName;
    }

    @Override
    public void run(){
        try{
            System.out.println(clientName + " is connected.");
        }catch(Exception e){
            System.out.println("Error whilst trying to connect " + clientName + ".\n" + e.getMessage());;
        }
    }


    public static void main(String[] args) {
        // Here we start the 4 clients - object constructor
        UDPClient client01 = new UDPClient("Client01");
        UDPClient client02 = new UDPClient("Client02");
        UDPClient client03 = new UDPClient("Client03");
        UDPClient client04 = new UDPClient("Client04");

        client01.start();
        client02.start();
        client03.start();
        client04.start();
    }
}

