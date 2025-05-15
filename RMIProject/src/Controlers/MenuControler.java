package Controlers;

import GUIRMI.MenuChats;
import GUIRMI.MenuGUI;
import Interface.IServer;
import Invocator.ClientCallbackImpl;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MenuControler {
    final String SERVER_NAME = "DF-Andres-William";
    MenuGUI menu;

    public MenuControler(MenuGUI menu) {
        this.menu = menu;
    }

    //EL PÓRT DE PRUEBA ES 3232
    public void startConection() {

        int port = 0;
        String serverName = "";
        String username = "";

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


            //Se crea el objeto CallBack
            ClientCallbackImpl cb = ClientCallbackImpl.getInstance();
            menu.setVisible(false);
            MenuChats.main(new String[0]);
            //Abrimos el Scanner para elegir si somos el remitente

            /*
            System.out.println("Escriba \"Y\" si usted es el remitente");
            Scanner sc = new Scanner(System.in);
            String remitente = sc.nextLine();
            sc.close();
            //esto es un minitest

            if (remitente.toLowerCase().equals("y")) test1(cb, server,username);
             */

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con el servidor");
            System.out.println("error en conectar al servidor");
            //e.printStackTrace();
        }
    }

    public static void test1(ClientCallbackImpl cb, IServer server,String username) throws RemoteException {
        try {
            System.out.println("TEST");
            Scanner sc = new Scanner(System.in);

            //Se registra el cliente
            server.registerClient(cb, username);

            System.out.println("Usuario registrado\nIngrese su destinatario");
            String receiver = sc.nextLine();

            System.out.println("Ingrese su mensaje al destinatario " + receiver);
            String message = sc.nextLine();

            server.sendDirectMessage(username, receiver, message);
            sc.close();
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el server");
            throw new RuntimeException(e);
        }
    }

}
