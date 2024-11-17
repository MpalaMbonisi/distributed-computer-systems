package com.github.mpalambonisi.lab06.q3;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserDatabaseInterface extends Remote {
    void addUser(User user) throws RemoteException;
    void deleteUser(String firstName, String lastName) throws RemoteException;
    User getUserDetails(String firstName, String lastName) throws RemoteException;
    List<User> listUsers() throws RemoteException;
    void modifyUser(String firstName, String lastName, User updatedUser) throws RemoteException;
}
