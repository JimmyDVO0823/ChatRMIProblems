package Controlers;

import GUIRMI.PrivateChatGUI;
import Model.Facade;

public class PrivateChatControler {
    private PrivateChatGUI view;
    Facade facade;

    public PrivateChatControler(PrivateChatGUI view) {
        this.view = view;
        facade = Facade.getInstance();
        updateUser(facade.getReciver());
    }

    public void goBack(){
        view.getLblUser().setText(facade.getReciver());
    }

    private void updateUser(String user){
        view.getLblUser().setText(user);
    }

    private void sendPrivateMessage(String message){
        facade.
    }

}
