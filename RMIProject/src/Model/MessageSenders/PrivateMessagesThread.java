package Model.MessageSenders;

import Interface.IServer;

import javax.swing.*;
import java.rmi.RemoteException;

public class PrivateMessagesThread extends Thread {
    private String from;
    private String to;
    private String message;
    private IServer server;

    public PrivateMessagesThread(String from, String to, String message, IServer server) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.server = server;
    }

    public void run() {
        try {

            server.sendDirectMessage(from, to, message);

        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el server");
            throw new RuntimeException(e);
        }
    }
}
