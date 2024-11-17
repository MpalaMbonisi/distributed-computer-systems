package com.github.mpalambonisi.lab06.q4;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Hello extends UnicastRemoteObject implements UserDatabaseInterface {
    private UserDatabase userDatabase;

    public Hello() throws RemoteException{
        userDatabase = new UserDatabase();
    }

    @Override
    public void addUser(User user) throws RemoteException {
        userDatabase.addUser(user);
    }

    @Override
    public void deleteUser(String firstName, String lastName) throws RemoteException {
        userDatabase.deleteUser(firstName, lastName);
    }

    @Override
    public User getUserDetails(String firstName, String lastName) throws RemoteException {
        return userDatabase.getUserDetails(firstName, lastName);
    }

    @Override
    public List<User> listUsers() throws RemoteException {
        return userDatabase.listUsers();
    }

    @Override
    public void modifyUser(String firstName, String lastName, User updatedUser) throws RemoteException {
        userDatabase.modifyUser(firstName, lastName, updatedUser);
    }

    @Override
    public void shutdown(){
        System.out.println("Server shutting down...");
        System.exit(0);
    }

}
