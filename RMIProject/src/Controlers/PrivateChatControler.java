package Controlers;

import GUIRMI.PrivateChatGUI;
import Model.Facade;

import java.util.ArrayList;

public class PrivateChatControler implements ISubscriber {
    private PrivateChatGUI view;
    Facade facade;

    public PrivateChatControler(PrivateChatGUI view) {
        this.view = view;
        facade = Facade.getInstance(this);
        updateUser(facade.getReciver());
    }

    public void goBack() {
        view.getLblUser().setText(facade.getReciver());
    }

    private void updateUser(String user) {
        view.getLblUser().setText(user);
    }

    public void sendMessage() {
        String space = "\n";
        String message = view.getTxtMessagePrivateArea().getText();
        if (view.getTxtHistory().getText().isEmpty()) space = "";
        view.getTxtHistory().append(space + message);
        facade.sendDirectMessage("Tu: " + message);
    }


    @Override
    public void reciveNotification(String notification) {
        String space = "\n";
        if (view.getTxtHistory().getText().isEmpty())space = "";
        view.getTxtHistory().append(space + notification);
    }
}
