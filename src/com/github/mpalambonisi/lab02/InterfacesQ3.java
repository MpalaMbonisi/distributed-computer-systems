package com.github.mpalambonisi.lab02;

import java.net.*;
import java.util.Enumeration;
import java.util.List;

/**
 * Network Interface Query Program
 * Displays details of a specified network interface.
 */
public class InterfacesQ3 {

    /**
     * Main method to retrieve network interface details
     *
     * @param args The command-line argument should be the name of the network interface.
     */
    public static void main(String[] args) {
        try {
            // get the list of all network interfaces
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null){
                System.out.println("No ( non-virtual )network interfaces found.");
                return;
            }

            // iterate through all network interfaces
            while(networkInterfaces.hasMoreElements()){
                NetworkInterface networkInterface = networkInterfaces.nextElement();

                // skip virtual interfaces ( eg. lo0 )
                if (networkInterface.isVirtual()){
                    continue;
                }

                // get the interface name and MTU
                String interfaceName = networkInterface.getName();
                int mtu = networkInterface.getMTU();
                boolean isLoopBack = interfaceName.equals("lo") || interfaceName.equals("loopback_0");

                // check if the interface is the loopback interface

                System.out.println("Interface Name: " + interfaceName);
                System.out.println("MTU : " + mtu);
                System.out.println("Is Loopback : " + isLoopBack);
                System.out.println("--------------------------------------");

                // get the list of interface addresses (IP, subnet mask, broadcast)
                List<InterfaceAddress> interfaceAddresses = networkInterface.getInterfaceAddresses();
                for(InterfaceAddress address: interfaceAddresses){
                    InetAddress inetAddress = address.getAddress();
                    InetAddress broadcast = address.getBroadcast();
                    int networkPrefixLength = address.getNetworkPrefixLength();

                    System.out.println("IP Address: " + inetAddress.getHostAddress());
                    System.out.println("Network Prefix Length : " + networkPrefixLength);
                    if (broadcast != null) {
                        System.out.println("Broadcast Address : " + broadcast.getHostAddress());
                    }else{
                        System.out.println("Broadcast Address: Not Available.");
                    }
                }
                System.out.println();
            }


        } catch (SocketException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
