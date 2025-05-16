package Model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallBack extends Remote {
    /**
     * Recibe el mensaje previa y remotamente enviado por otro usuario
     * @param from remitente del mensaje
     * @param message mensaje
     * @throws RemoteException
     */
    void receiveMessage(String from, String message) throws RemoteException;
    void sendMessage(String to, String message) throws RemoteException;
}