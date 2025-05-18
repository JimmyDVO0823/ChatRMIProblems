package Model.MessageSenders;

import Interface.IServer;

public class PublicMessagesThread extends Thread {
    IServer server;
    String message;
    String from;

    public PublicMessagesThread(String message, String from,IServer server) {
        this.server = server;
        this.message = message;
        this.from = from;
    }

    public void run() {
        try {
            server.sendPublicMessage(from, message);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
