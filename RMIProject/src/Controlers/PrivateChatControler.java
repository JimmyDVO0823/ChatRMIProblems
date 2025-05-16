package Controlers;

import GUIRMI.PrivateChatGUI;
import Model.Facade;

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
        String message = view.getTxtMessagePrivateArea().getText();
        facade.sendDirectMessage(message);
    }


    @Override
    public void reciveNotification(String notification) {
        String space = "";
        if (!view.getTxtHistory().getText().equals(""))space = "\n";
        view.getTxtHistory().append(space + notification);
    }
}
