package RMI.Server;

import Interface.IServer;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import Model.ClientCallBack;

public class ServerLauncher  {


    public ServerLauncher() throws RemoteException {
        super();
    }

    public static void main(String[] args) throws Exception {
        // 1. Obtener IP local y fijar el hostname RMI
        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Servidor IP: " + ip);
        System.setProperty("java.rmi.server.hostname", ip);

//        // 2. (Opcional) configurar Security Manager si se necesitan políticas
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }

        // 3. Crear el registro RMI en el puerto 3232
        Registry registry = LocateRegistry.createRegistry(3232); // (fixed port):contentReference[oaicite:1]{index=1}

        // 4. Instanciar e exportar el objeto remoto (ej. ServerImpl implementa IServer)
        IServer remoteObj = new ServerImpl(); // debe implementar java.rmi.Remote e IServer
        registry.rebind("srv", remoteObj);

        System.out.println("Servidor RMI listo en " + ip + ":3232");
    }

}
