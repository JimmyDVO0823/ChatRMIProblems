package Model;

public class Facade {
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

    public void startChat(){

    }

    public void sendDirectMessage(String message){

    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }
}
