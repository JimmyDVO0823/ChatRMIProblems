package Controlers;

import GUIRMI.PublicChatGUI;
import Model.Facade;

public class PublicChatControler {
    private Facade facade;
    private PublicChatGUI view;

    public PublicChatControler(PublicChatGUI view) {
        this.view = view;
        facade = Facade.getInstance();
    }
}
