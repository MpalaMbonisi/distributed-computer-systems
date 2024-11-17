package com.github.mpalambonisi.lab06.q4;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDatabase extends UnicastRemoteObject implements UserDatabaseInterface {
    private List<User> users;
    public UserDatabase() throws RemoteException{
        users = new ArrayList<>();
    }
    @Override
    public void addUser(User user) throws RemoteException{
        users.add(user);
    }
    @Override
    public void deleteUser(String firstName, String lastName) throws RemoteException{
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();
            if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)){
                iterator.remove();
                return;
            }
        }
    }
    @Override
    public User getUserDetails(String firstName, String lastName) throws RemoteException{
        for (User user : users){
            if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)){
                return user;
            }
        }
        return null;
    }
    @Override
    public List<User> listUsers() throws RemoteException{
        return users;
    }

    @Override
    public void shutdown(){
        System.out.println("Server shutting down...");
        System.exit(0);
    }

    @Override
    public void modifyUser(String firstName, String lastName, User updatedUser) throws RemoteException{
        for (User user: users){
            if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)){
                user.setFirstName(updatedUser.getFirstName());
                user.setLastName(updatedUser.getLastName());
                user.setBirthDate(updatedUser.getBirthDate());
                user.setSalary(updatedUser.getSalary());
                user.setGender(updatedUser.getGender());
                user.setDivision(updatedUser.getDivision());
                user.setWorkPosition(updatedUser.getWorkPosition());
            }
        }
    }

}
