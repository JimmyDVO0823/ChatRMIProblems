package Model;

import Interface.ClientCallBack;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ClientCallbackImpl extends UnicastRemoteObject implements ClientCallBack {
    //Uso de singleton, pues solo vamos a tener un usuario (nosotros mismos)
    Facade facade;
    private static ClientCallbackImpl instance;
    private ClientCallbackImpl() throws RemoteException {
        super();
        facade = Facade.getInstance();
    }

    public static ClientCallbackImpl getInstance() throws RemoteException {
        if (instance == null) {
            instance = new ClientCallbackImpl();
        }
        return instance;
    }

    @Override
    public void receiveMessage(String from, String message) {
        System.out.println(from + ": " + message);
        String messageNotify = from + ": " + message;
        facade.notifyPrivate(messageNotify);
    }

    @Override
    public void sendMessage(String to, String message) throws RemoteException {

    }

    @Override
    public void reciveConectedUsers(ArrayList<String> users) throws RemoteException {
        //System.out.println("se recibir la lista de usuarios en el callback");
        //System.out.println(users);
        facade.updateConectedUsers(users);
    }

    @Override
    public void ping() throws RemoteException {
        System.out.println("ping");
    }

    @Override
    public void recivePublicMessage(String from, String message) throws RemoteException {
        facade.notifyPublicMessage(from,message);
    }
}