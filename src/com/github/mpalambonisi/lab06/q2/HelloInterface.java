package com.github.mpalambonisi.lab06.q2;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author pbilski
 */
public interface HelloInterface extends Remote {
	// The list of operations performed by the server
    public String say() throws RemoteException;
    public int addNumbers(int a, int b) throws RemoteException;
    public int calculateVolume(MyObject o) throws RemoteException;
    public void clientConnected() throws RemoteException;
}