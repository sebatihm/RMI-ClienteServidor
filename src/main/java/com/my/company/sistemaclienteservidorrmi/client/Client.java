package com.my.company.sistemaclienteservidorrmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IRemoteService;

public class Client {
    public static IRemoteService remoteService;

    public static IRemoteService getRemoteService() {
        if (remoteService == null) {
            try {
                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                remoteService = (IRemoteService) registry.lookup("RemoteService");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return remoteService;
    }

    public static void main(String[] args) {
        IRemoteService service = getRemoteService();
        if (service != null) {
            System.out.println("Connected to remote service.");

            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        } else {
            System.out.println("Failed to connect to remote service.");
        }
    }


    
}
