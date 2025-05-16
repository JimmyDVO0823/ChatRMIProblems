package Controlers;

import GUIRMI.MenuChats;
import GUIRMI.MenuGUI;
import Interface.IServer;
import Model.ClientCallbackImpl;
import Model.Facade;

import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MenuControler {
    MenuGUI menu;
    private Facade facade;

    public MenuControler(MenuGUI menu) {
        this.menu = menu;
        facade = Facade.getInstance();
    }

    public void conectToServer() {
        try {
            String serverIp   = menu.getTxtServerIPMenu().getText();    // p.ej. 192.168.1.50
            int    port       = Integer.parseInt(menu.getTxtPort().getText()); // p.ej. 3232
            String serverName = menu.getTxtServerNameMenu().getText();  // p.ej. "SERVER"
            String username   = menu.getTxtUsernameMenu().getText();

            System.out.println("Server IP: " + serverIp);
            System.out.println("Server Name: " + serverName);
            System.out.println("Username: " + username);
            System.out.println("Port: " + port);
            // Conexión al registro en la IP/puerto indicados
            Registry registry = LocateRegistry.getRegistry(serverIp, port);
            IServer server = (IServer) registry.lookup(serverName);
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
