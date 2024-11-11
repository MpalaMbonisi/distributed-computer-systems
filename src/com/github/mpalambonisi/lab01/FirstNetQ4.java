package com.github.mpalambonisi.lab01;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Peter
 */
 
public class FirstNetQ4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		InetAddress node;

		// execution argument [remote host] - "microsoft.com"
		try {
			node = InetAddress.getByName(args[0]);
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
