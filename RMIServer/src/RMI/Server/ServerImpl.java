package RMI.Server;

import Interface.IServer;
import Model.ClientCallBack;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerImpl extends UnicastRemoteObject implements IServer {
    private final Map<String, ClientCallBack> clients = new ConcurrentHashMap<>();
    private ArrayList<String> usersList;
    ActiveUsersThread activeUsersThread;

    public ServerImpl() throws RemoteException {
        super();  // Exporta el objeto
        usersList = new ArrayList<>();
        activeUsersThread = new ActiveUsersThread(this);
    }

    /**
     * Método para registrar un cliente, es decir, añadirlo a la lista de clientes
     * que maneja el servidor para poder tener acceso futuro. este método tambien
     * añade el username a la lista de nombres para los usuarios conectados y llama updateList()
     *
     * @param cb       Objeto remoto que representa al cliente
     * @param username la clave para guardar el objeto remoto
     */
    @Override
    public synchronized void registerClient(ClientCallBack cb, String username) {
        clients.put(username, cb);
        if (clients.size() == 1) {
            activeUsersThread.start();
        }
        //System.out.println("Cliente '" + username + "' registrado");
        usersList.add(username);
        try {
            updateList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Envia mensaje directo (privado) de un usuario a otro
     *
     * @param from remitente
     * @param to   destinatario
     * @param msg  mensaje a enviar
     * @throws RemoteException
     */
    @Override
    public synchronized void sendDirectMessage(String from, String to, String msg) throws RemoteException {
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

    /**
     * Le da la bienvenida al usuario cuando entra
     *
     * @param nombre nombre del usuario
     * @return retorna el saludo al usuario
     * @throws RemoteException
     */
    @Override
    public String darBienvenida(String nombre) throws RemoteException {
        String saludo = "Hola " + nombre + ". ¡Bienvenido al servidor!";
        System.out.println("Ejecutando darBienvenida para: " + nombre);
        return saludo;
    }

    //MÉTODOS

    /**
     * Manda a actualizar la lista de cada usuario por separado, enviandole la lista
     * de Strings con los nombres de cada uno
     */
    private void updateList() throws RemoteException {
        //System.out.println("lista de clientes:");
        for (Map.Entry<String, ClientCallBack> entry : clients.entrySet()) {
            ClientCallBack callback = entry.getValue();
            //System.out.println(entry.getKey());
            callback.reciveConectedUsers(usersList);
        }
        System.out.println("Lista de Usuarios:");
        System.out.println(usersList);
        //System.out.println("-----------------------");
    }

    public void runList() {
        ClientCallBack clientCallBackToDelete = null;
        String usernameToDelete = "";
        try {
            for (Map.Entry<String, ClientCallBack> entry : clients.entrySet()) {
                usernameToDelete = entry.getKey();
                clientCallBackToDelete = entry.getValue();
                clientCallBackToDelete.ping();
            }
        } catch (Exception e) {
            System.out.println("usuario eliminandose...");
            usersList.remove(usernameToDelete);
            clients.remove(usernameToDelete);
            try {
                updateList();
            } catch (RemoteException ex) {
                System.out.println("EXCEPCION AL ACTUALIZAR LA LISTA");
            }
        }
    }
}
