package RMI.Server;

import Interface.IServer;
import Model.ClientCallBack;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerImpl extends UnicastRemoteObject implements IServer {
    private final Map<String, ClientCallBack> clients = new ConcurrentHashMap<>();

    public ServerImpl() throws RemoteException {
        super();  // Exporta el objeto
    }

    @Override
    public synchronized void registerClient(ClientCallBack cb, String username) {
        clients.put(username, cb);
        System.out.println("Cliente '" + username + "' registrado");
    }

    @Override
    public synchronized void sendDirectMessage(String from, String to, String msg)
            throws RemoteException {
        ClientCallBack target = clients.get(to);
        if (target != null) {
            try {
                target.receiveMessage(from, msg);
            } catch (RemoteException e) {
                clients.remove(to);
                System.out.println("Cliente '" + to + "' removido por desconexión");
            }
        } else {
            System.out.println("Usuario destino '" + to + "' no encontrado");
        }
    }

    @Override
    public String darBienvenida(String nombre) throws RemoteException {
        String saludo = "Hola " + nombre + ". ¡Bienvenido al servidor!";
        System.out.println("Ejecutando darBienvenida para: " + nombre);
        return saludo;
    }
}
