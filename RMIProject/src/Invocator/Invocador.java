/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Invocator;
import Interface.IServer;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

/**
 *
 * @author LENOVO LOQ
 */
public class Invocador {
    public static void main(String[] args) {
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

    /**
     * Primera prueba del primer prototipo de envío de mensaje. En este caso se va a hacer todo a nivel de terminal.
     * Se va a registrar el usuario en el server, va a ingresar un destinatario, que va a ser quien recibe el
     * mensaje y se va a ingtresar el mensaje. Si todo sale bien, el destinatario deberia recibir el
     * mensaje en su respectva terminal
     * @param cb
     * @param server
     * @throws RemoteException
     */
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
