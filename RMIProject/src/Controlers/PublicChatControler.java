package Controlers;

import GUIRMI.PublicChatGUI;
import Model.Facade;

public class PublicChatControler implements ISubscriber{
    private Facade facade;
    private PublicChatGUI view;

    public PublicChatControler(PublicChatGUI view) {
        this.view = view;
        facade = Facade.getInstance();
        facade.subscribe(this);
    }

    public void sendMessage(){
        String message = view.getTxtMessage().getText();
        facade.sendPublicMessage(message);
    }

    @Override
    public void reciveNotification(String notification) {
        view.getTxtMessagesHistory().setText(notification);
    }
}
