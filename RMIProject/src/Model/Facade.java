package Model;

import Controlers.ChatMenuControler;
import Controlers.ISubscriber;
import Interface.IServer;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Facade {
    private ArrayList<ISubscriber> subscribers;
    private IServer server;
    private Registry reg;
    private String username;
    private String reciver;
    private static Facade instance;

    private Facade() {
        subscribers = new ArrayList<>();
    }

    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    //para ver si sirve
    public static Facade getInstance(ISubscriber subscriber) {
        if (instance == null) {
            instance = new Facade();
        }
        instance.subscribe(subscriber);
        return instance;
    }

    public void registerClient(ClientCallBack callBack,String username) throws RemoteException {
        try {
            setUsername(username); //El usuario solo va a tener un username, entonces lo guardamos en la fachada
            server.registerClient(callBack,username); //Manda a registrar el callback
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el Cliente");
            throw new RuntimeException(e);
        }
    }

    public void sendDirectMessage(String message) {
        MessagesThread thread = new MessagesThread(username,reciver,message,server);
        thread.start();
    }

    public void notifyPrivate(String message){
        for (int i = 0; i < subscribers.size(); i++) {
            ISubscriber subscriber = subscribers.get(i);
            subscriber.reciveNotification(message);
        }
    }

    public void subscribe(ISubscriber subscriber){
        subscribers.add(subscriber);
    }

    public void updateConectedUsers(ArrayList<String> users){
        System.out.println("El nombre de usuario en la fachada es: " + username );
        users.remove(username);
        System.out.println("lista de usuarios sin el usuario propio:");
        System.out.println(users);
        for (ISubscriber subscriber : subscribers) {
            if (subscriber instanceof ChatMenuControler) {
                System.out.println("se encontró el controlador");
                ((ChatMenuControler) subscriber).updateConectedUsers(users);
            }
        }
    }

    //GETTERS SETTERS

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public Registry getReg() {
        return reg;
    }

    public void setReg(Registry reg) {
        this.reg = reg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public IServer getServer() {
        return server;
    }

    public void setServer(IServer server) {
        this.server = server;
    }
}
