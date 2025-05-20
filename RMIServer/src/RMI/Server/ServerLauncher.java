package RMI.Server;

import Interface.IServer;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Interface.IServer;

public class ServerLauncher {

    public ServerLauncher() throws RemoteException {
    }

    public static void main(String[] args) throws Exception {
        IServer remoteObj = new ServerImpl();
        runServer(remoteObj);

    }

    public static void runServer(IServer server) throws RemoteException, AlreadyBoundException {
        int port = 3232;
        String ip = null;

        try {
            ip = InetAddress.getLocalHost().toString();
            Registry registry = LocateRegistry.createRegistry(3232); // (fixed port):contentReference[oaicite:1]{index=1}
            registry.bind("SERVER", server);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Servidor IP: " + ip + "Port :" + port);
        System.out.println("Servidor RMI listo en " + ip + ":3232");

    }

}