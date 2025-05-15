package Invocator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientCallbackImpl extends UnicastRemoteObject implements ClientCallBack {
    //Uso de singleton, pues solo vamos a tener un usuario (nosotros mismos)
    private static ClientCallbackImpl instance;
    private ClientCallbackImpl() throws RemoteException { super(); }

    public static ClientCallbackImpl getInstance() throws RemoteException {
        if (instance == null) {
            instance = new ClientCallbackImpl();
        }
        return instance;
    }

    @Override
    public void receiveMessage(String from, String message) {
        System.out.println(from + ": " + message);
    }

    @Override
    public void sendMessage(String to, String message) throws RemoteException {

    }
}