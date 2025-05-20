package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientCallBack extends Remote {
    /**
     * Recibe el mensaje previa y remotamente enviado por otro usuario
     * @param from remitente del mensaje
     * @param message mensaje
     * @throws RemoteException
     */
    void receiveMessage(String from, String message) throws RemoteException;
    void sendMessage(String to, String message) throws RemoteException;
    void reciveConectedUsers(ArrayList<String> users) throws RemoteException;
    void ping() throws RemoteException;
    void recivePublicMessage(String from, String message) throws RemoteException;
}