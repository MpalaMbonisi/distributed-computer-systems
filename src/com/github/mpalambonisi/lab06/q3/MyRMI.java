package com.github.mpalambonisi.lab06.q3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MyRMI {
    public static void main(String[] args) {
        try{
            Registry r = LocateRegistry.createRegistry(5001);
            r.rebind("UserDatabase", new Hello());
            System.out.println("RMI server is up!");
        } catch(Exception e){
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
