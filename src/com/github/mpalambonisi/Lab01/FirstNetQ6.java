package com.github.mpalambonisi.Lab01;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Peter
 */
 
public class FirstNetQ5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		InetAddress node;

		// execution argument [remote host] - "192.168.1.1"
		try {
			// convert the String to byte
			String regex = "\\.";
			String[] stringAddress = args[0].split(regex);
			byte[] byteAddress = new byte[stringAddress.length];
			for(int i = 0; i < stringAddress.length; i++){
				int part = Integer.parseInt(stringAddress[i]); // Parse as an integer
				byteAddress[i] = (byte) part;
			}

			node = InetAddress.getByAddress(byteAddress);
			System.out.println("Local address: " + node.getHostAddress());
            System.out.println("Local hostname: " + node.getHostName());
			System.out.println("Canonical hostname: " + node.getCanonicalHostName());
			System.out.println("HashCode: " + node.hashCode());

			if(node.isMulticastAddress()) {
				System.out.println("This is a multi-cast address.");
			}
			else {
				System.out.println("This is not a multi-cast address.");
			}

			if (node.isLoopbackAddress()) {
				System.out.println("This is the loopback address");
			}
			else {
				System.out.println("This is not a loopback address");
			}
		}
		catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
    }
}
