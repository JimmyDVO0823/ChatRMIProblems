/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI.Server;

import Interface.IServer;
import java.net.InetAddress;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author LENOVO LOQ
 */

/**
 * Clase Server: implementación remota de IServer usando Java RMI
 */
public class Server extends UnicastRemoteObject implements IServer {

    final String nombreServidor = "DF-Andres-William";
    // Constructor: exporta el objeto remoto para recibir llamadas RMI
    public Server() throws RemoteException {

    }

    // Punto de entrada de la aplicación
    public static void main(String[] args) throws Exception {
        Server s = new Server();    // Crea instancia del servidor
        s.iniciarServidor();        // Inicia el registro RMI
    }

    // Metodo para levantar el registry y hacer bind del servidor
    public void iniciarServidor() {
        try {
            int port = 3232;    // Puerto en el que escuchará el RMI Registry
            // Obtiene la IP local para mostrar dónde está escuchando
            String dirIP = (InetAddress.getLocalHost().toString());
            System.out.println("Escuchando en " + dirIP + port); //pa confirmar 👍

            // Crea un RMI Registry embebido en este proceso, en el puerto indicado
            Registry reg = LocateRegistry.createRegistry(port);
            // Registra (bind) este objeto bajo el nombre "rmiserver"
            reg.bind(nombreServidor, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //ESTO ES PARA IR PROBANDO, PROBABLEMENTE TERMINE SIENDO BORRADO (EL SEGUNDO SOBRE TODO)
    @Override
    public String darBienvenida(String nombre) throws RemoteException {
        System.out.println("Ejecutando el saludo");
        return "Hola " + nombre + ". Bienvenido!";
    }

    @Override
    public int calcularMayor(int i, int i1) throws RemoteException {
        System.out.println("Ejecutando el saludo");
        if (i > i1) {
            return i;
        } else {
            return i1;
        }
    }
}
