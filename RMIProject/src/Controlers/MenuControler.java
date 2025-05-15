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
    MenuGUI menu;
    public MenuControler(MenuGUI menu) {
        this.menu = menu;
    }

    public void startConection(){
        final String nombreServidor = "DF-Andres-William";
        try {
            //Se da un nombre
            Scanner sc = new Scanner(System.in);
            System.out.println("Escriba el nombre");
            String nombre = sc.nextLine();
            //Se conecta al servidor
            Registry reg = LocateRegistry.getRegistry("localhost",3232);
            IServer server = (IServer) reg.lookup(nombreServidor);

            //Se da la bienvenida, es una miniprueba
            String saludo = server.darBienvenida(nombre);
            System.out.println(saludo);

            //Se crea el objeto CallBack
            ClientCallbackImpl cb = new ClientCallbackImpl();

            //Abrimos el Scanner para elegir el remitente y destinatario
            //Scanner sc = new Scanner(System.in);
            System.out.println("Escriba \"Y\" si usted es el remitente");
            String remitente = sc.nextLine();

            if (remitente.equals("Y")) test1(cb, server);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void test1(ClientCallbackImpl cb, IServer server) throws RemoteException {
        try {
            System.out.println("TEST");
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingresar nombre de usuario");
            String username = sc.nextLine();
            server.registerClient(cb,username);

            System.out.println("Usuario registrado\nIngrese su destinatario");
            String receiver = sc.nextLine();

            System.out.println("Usuario recebido\nIngrese su mensaje");
            String message = sc.nextLine();

            server.sendDirectMessage(username,receiver,message);
            sc.close();
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el server");
            throw new RuntimeException(e);
        }
    }

}
