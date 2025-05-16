package Controlers;

import GUIRMI.MenuChats;
import GUIRMI.MenuGUI;
import Interface.IServer;
import Model.ClientCallBack;
import Model.ClientCallbackImpl;
import Model.Facade;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MenuControler {
    MenuGUI menu;
    private Facade facade;

    public MenuControler(MenuGUI menu) {
        this.menu = menu;
        facade = Facade.getInstance();
    }

    public void startConection() {
        try {
            String serverIp   = menu.getTxtServerIPMenu().getText();    // p.ej. 192.168.1.50
            int    port       = Integer.parseInt(menu.getTxtPort().getText()); // p.ej. 3232
            String serverName = menu.getTxtServerNameMenu().getText();  // p.ej. "SERVER"
            String username   = menu.getTxtUsernameMenu().getText();

            // Conexión al registro en la IP/puerto indicados
            Registry reg      = LocateRegistry.getRegistry(serverIp, port);
            IServer server    = (IServer) reg.lookup(serverName);
            facade.setServer(server);

            // Registro del callback
            ClientCallbackImpl cb = ClientCallbackImpl.getInstance();
            facade.registerClient(cb, username);

            // Avanza en la UI
            menu.setVisible(false);
            new MenuChats().setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con el servidor");
            e.printStackTrace();
        }
    }



}
