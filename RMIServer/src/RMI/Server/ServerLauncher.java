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

public class ServerLauncher extends UnicastRemoteObject implements IServer {

    private static final String SERVICE_NAME = "SERVER";
    private final Map<String,ClientCallBack> clients = new ConcurrentHashMap<>();

    public ServerLauncher() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            // 1) Obtener la IP LAN del servidor (p. ej. "192.168.1.50")
            String serverIp = InetAddress.getLocalHost().getHostAddress();
            System.out.println(serverIp);
            // 2) Decirle a RMI qué hostname/IP usar en los stubs
            System.setProperty("java.rmi.server.hostname", serverIp);

            // 3) Elegir puerto (por defecto 3232 o desde args)
            int port = 3232;
            if (args.length >= 1) {
                port = Integer.parseInt(args[0]);
            }

            // 4) Crear instancia y arrancar registry
            ServerLauncher server = new ServerLauncher();
            Registry registry = LocateRegistry.createRegistry(port);

            // 5) Registrar (o re‑registrar) el servicio
            registry.rebind(SERVICE_NAME, server);

            System.out.printf("Servidor RMI '%s' listo en %s:%d%n",
                    SERVICE_NAME, serverIp, port);

        } catch (Exception e) {
            System.err.println("Error arrancando el servidor:");
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void registerClient(ClientCallBack cb, String username) {
        System.out.println("Cliente '" + username + "' registrado");
        clients.put(username, cb);
    }

    @Override
    public synchronized void sendDirectMessage(String from, String to, String msg)
            throws RemoteException {
        ClientCallBack target = clients.get(to);
        if (target != null) {
            try {
                target.receiveMessage(from, msg);
            } catch (RemoteException e) {
                // Cliente desconectado: limpiar
                clients.remove(to);
                System.out.println("Cliente '" + to + "' removido por desconexión");
            }
        } else {
            System.out.println("Usuario destino '" + to + "' no encontrado");
        }
    }

    @Override
    public String darBienvenida(String nombre) throws RemoteException {
        System.out.println("Ejecutando darBienvenida para: " + nombre);
        return "Hola " + nombre + ". ¡Bienvenido al servidor!";
    }

}
