package Controlers;

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
        String nombreServidor = menu.getTxtServerNameMenu().getText();
        String username = menu.getTxtUsernameMenu().getText();
        int port = Integer.parseInt(menu.getTxtIPServerMenu().getText());
        try {
            //Se da un nombre
            Scanner sc = new Scanner(System.in);


            //Se conecta al servidor
            Registry reg = LocateRegistry.getRegistry("localhost", port);
            IServer server = (IServer) reg.lookup(nombreServidor);

            //Se crea el objeto CallBack
            ClientCallbackImpl cb = new ClientCallbackImpl();

            //Abrimos el Scanner para elegir el remitente y destinatario
            //Scanner sc = new Scanner(System.in);
            System.out.println("Escriba \"Y\" si usted es el remitente");
            String remitente = sc.nextLine();

            if (remitente.toLowerCase().equals("y")) test1(cb, server,username);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test1(ClientCallbackImpl cb, IServer server,String username) throws RemoteException {
        try {
            System.out.println("TEST");
            Scanner sc = new Scanner(System.in);
            //System.out.println("Ingresar nombre de usuario");
            //String username = sc.nextLine();
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
