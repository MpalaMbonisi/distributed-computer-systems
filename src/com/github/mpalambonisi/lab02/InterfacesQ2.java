package com.github.mpalambonisi.lab02;

import java.net.*;
import java.util.List;

/**
 * Network Interface Query Program
 * Displays details of a specified network interface.
 */
public class InterfacesQ2 {

    /**
     * Main method to retrieve network interface details
     *
     * @param args The command-line argument should be the name of the network interface.
     */
    public static void main(String[] args) { // argument - loopback_0
        // Check that only one argument is provided
        if (args.length > 1) {
            System.out.println("Error: too many execution arguments passed...");
            return;
        }

        try {
            // Retrieve the specified network interface by name
            NetworkInterface networkInterface = NetworkInterface.getByName(args[0]);
            if (networkInterface == null) {
                System.out.println("Error: Network interface not found.");
                return;
            }

            // Display the network interface's name
            System.out.println("NetworkInterface name: " + networkInterface.getName());

            // Get and display the MAC address if available
            byte[] hardwareAddressByte = networkInterface.getHardwareAddress();
            if (hardwareAddressByte != null) {
                StringBuilder hardwareAddress = new StringBuilder();
                for (byte b : hardwareAddressByte) {
                    // Format each byte to two hexadecimal characters, separated by colons
                    hardwareAddress.append(String.format("%02X:", b));
                }
                // Remove the trailing colon for cleaner output
                if (!hardwareAddress.isEmpty()) {
                    hardwareAddress.setLength(hardwareAddress.length() - 1);
                }
                System.out.println("Hardware Address: " + hardwareAddress);
            } else {
                System.out.println("Hardware Address: Not available");
            }

            // Retrieve and display all IP addresses and hostnames associated with the interface
            List<InterfaceAddress> networkInterfaces = networkInterface.getInterfaceAddresses();
            for (InterfaceAddress interAdd : networkInterfaces) {
                System.out.println("Interface Address name : " + interAdd.getAddress().getHostName());
                System.out.println("Interface Address address : " + interAdd.getAddress().getHostAddress());
                System.out.println("---------------------------------------");
            }

        } catch (SocketException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
