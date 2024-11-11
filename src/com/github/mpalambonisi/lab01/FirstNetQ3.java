package com.github.mpalambonisi.lab01;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Peter
 */
 
public class FirstNetQ3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		InetAddress node;

		// execution argument [remote host] - "google.com"
		try {
			node = InetAddress.getByName(args[0]);
			System.out.println("Local address: " + node.getHostAddress());
            System.out.println("Local hostname: " + node.getHostName());
			if (node.isLoopbackAddress()) {
				System.out.println("This is the loopback address");
			}
			else{
				System.out.println("This is not a loopback address");
			}
		}
		catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
    }
}
