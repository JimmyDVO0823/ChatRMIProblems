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

    //EL PÓRT DE PRUEBA ES 3232
    public void startConection() {

        int port;
        String serverName;
        String username;

        try {
            serverName = menu.getTxtServerNameMenu().getText();
            username = menu.getTxtUsernameMenu().getText();
            port = Integer.parseInt(menu.getTxtIPServerMenu().getText());


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese los datos del usuario");
            return;
            //e.printStackTrace();
        }

        try {
            //Se conecta al servidor
            Registry reg = LocateRegistry.getRegistry("localhost", port);
            IServer server = (IServer) reg.lookup(serverName);
            facade.setServer(server);

            //Se crea el objeto CallBack
            ClientCallbackImpl cb = ClientCallbackImpl.getInstance();
            facade.registerClient(cb, username);

            //Pa probar si queda bien asi
            menu.setVisible(false);
            MenuChats menuChats = new MenuChats();
            menuChats.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con el servidor");
            System.out.println("error en conectar al servidor");
            //e.printStackTrace();
        }
    }


}
