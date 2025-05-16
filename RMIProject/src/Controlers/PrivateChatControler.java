package Controlers;

import GUIRMI.PrivateChatGUI;
import Model.Facade;

public class PrivateChatControler {
    private PrivateChatGUI view;
    Facade facade;
    public PrivateChatControler(PrivateChatGUI view) {
        this.view = view;
        facade = Facade.getInstance();
    }
}
