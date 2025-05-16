package Model;

public class messagesThread extends Thread {
    private String from;
    private String to;
    private String message;

    public messagesThread(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public void run() {
        
    }
}
