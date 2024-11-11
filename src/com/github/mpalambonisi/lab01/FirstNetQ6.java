package com.github.mpalambonisi.lab01;

import java.io.IOException;
import java.net.InetAddress;

/**
 *
 * @author Peter
 */
 
public class FirstNetQ6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

		if(args.length != 2) {
			System.out.println("Error: invalid number of execution arguments passed...");
			return;
		}

		// test arguments - 127.0.0.0 or 192.168.1.0

		String baseIp = args[0];
		int range = Integer.parseInt(args[1]); // Example "10" to check the first 10 addresses

		// convert the String to byte
		String[] parts = baseIp.split("\\.");
		if(parts.length != 4){
			System.out.println("Invalid base IP address format...");
			return;
		}

		try {

			int addressPart01 = Integer.parseInt(parts[0]);
			int addressPart02 = Integer.parseInt(parts[1]);
			int addressPart03 = Integer.parseInt(parts[2]);
			int addressPart04 = Integer.parseInt(parts[3]);

			for(int i = 0; i < range; i++){
				// only increment the last part of the IP address
				int currentPart04 = addressPart04 + i;
				if (currentPart04 > 255) break; // prevent going out-of-range

				byte[] ipAddress = new byte[]{
						(byte) addressPart01,
						(byte) addressPart02,
						(byte) addressPart03,
						(byte) currentPart04
				};
				InetAddress address = InetAddress.getByAddress(ipAddress);
				System.out.println("Checking " + address.getHostAddress() + " ..... ");

				if (address.isReachable(1000)){ // timeout is set to 1000ms
					System.out.println("Host is reachable...");
				}else{
					System.out.println("Host is not reachable...");
				}
			}

		}
		catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
