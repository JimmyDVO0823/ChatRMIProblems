package Model;

import GUIRMI.MenuChats;
import GUIRMI.MenuGUI;
import Interface.IServer;
import Model.ClientCallBack;
import Model.ClientCallbackImpl;
import Model.Facade;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Facade {
    private IServer server;
    private Registry reg;
    private String username;
    private String reciver;
    private static Facade instance;

    private Facade() {
    }

    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    public void registerClient(ClientCallBack callBack,String username) throws RemoteException {
        try {
            server.registerClient(callBack,username);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el Cliente");
            throw new RuntimeException(e);
        }
    }

    public void sendDirectMessage(String message) {
        MessagesThread thread = new MessagesThread(username,reciver,message,server);
        thread.start();
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
