package com.github.mpalambonisi.lab01;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Peter
 */
 
public class FirstNetQ2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

		// execution argument - "localhost"
		InetAddress node;
		try {
			node = InetAddress.getByName(args[0]);
			System.out.println("Local address: " + node.getHostAddress());
            System.out.println("Local hostname: " + node.getHostName());
			if (node.isLoopbackAddress()) {
				System.out.println("This is the loopback address");
			}
		}
		catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
    }
}
