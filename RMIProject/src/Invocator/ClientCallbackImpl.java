package Invocator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientCallbackImpl extends UnicastRemoteObject implements ClientCallBack {
    public ClientCallbackImpl() throws RemoteException { super(); }
    @Override
    public void receiveMessage(String from, String message) {
        System.out.println(from + ": " + message);
    }
}