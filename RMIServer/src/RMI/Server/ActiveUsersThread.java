package RMI.Server;

import java.rmi.RemoteException;

public class ActiveUsersThread extends Thread {
    ServerImpl server;
    ActiveUsersThread(ServerImpl server) {
        this.server = server;
    }

    public void run() {
        while (true) {
            System.out.println("corriendo");
            try {
                server.runList();
            } catch (RemoteException e) {
                System.out.println("notificado");
                throw new RuntimeException(e);
            }
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
