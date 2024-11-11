package com.github.mpalambonisi.lab02;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 *
 * @author Mbonisi Mpala
 */
public class Interfaces {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            InetAddress localHost = Inet4Address.getLocalHost();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface thisInterface;
            while (networkInterfaces.hasMoreElements())
            {
                NetworkInterface iface = networkInterfaces.nextElement();
                System.out.println("Interface name: " + iface.getName());
            }

        }
        catch(SocketException | UnknownHostException e) {
            
        }
    }
    
}
