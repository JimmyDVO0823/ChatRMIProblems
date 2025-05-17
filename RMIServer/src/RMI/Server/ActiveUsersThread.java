package RMI.Server;

import Interface.IServer;
import Model.ClientCallBack;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActiveUsersThread extends Thread {
    ServerImpl server;
    ActiveUsersThread(ServerImpl server) {
        this.server = server;
    }

    public void run() {
        while (true) {
            System.out.println("corriendo");
            server.runList();
            try {
                sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
