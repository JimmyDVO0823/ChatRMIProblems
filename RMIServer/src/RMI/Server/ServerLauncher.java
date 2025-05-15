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
import Interface.IServer;
import Invocator.ClientCallBack;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author LENOVO LOQ
 */

/**
 * Clase ServerLauncher: implementación remota de IServer usando Java RMI
 */
public class ServerLauncher extends UnicastRemoteObject implements IServer {

    final String nombreServidor = "DF-Andres-William";
    // Constructor: exporta el objeto remoto para recibir llamadas RMI
    public ServerLauncher() throws RemoteException {

    }

    // Punto de entrada de la aplicación
    public static void main(String[] args) throws Exception {
        ServerLauncher s = new ServerLauncher();    // Crea instancia del servidor
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
            // Registra (bind) este objeto bajo el nombre "DF-Andres-William"
            reg.bind(nombreServidor, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final Map<String,ClientCallBack> clients = new ConcurrentHashMap<>();
    @Override
    public synchronized void registerClient(ClientCallBack cb, String username) {
        clients.put(username, cb);
    }

    /**
     * El remitente envia un mensaje directo al destinatario
     * @param from Remitente
     * @param to Destinatario
     * @param msg Mensaje
     * @throws RemoteException
     */
    @Override
    public synchronized void sendDirectMessage(String from, String to, String msg) throws RemoteException {
        ClientCallBack target = clients.get(to);
        if (target != null) {
            try {
                target.receiveMessage(from, msg);
            } catch (RemoteException e) {
                clients.remove(to); // limpia stub inválido
            }
        }
    }


    //ESTO ES PARA IR PROBANDO, PROBABLEMENTE TERMINE SIENDO BORRADO (EL SEGUNDO SOBRE TODO)
    @Override
    public String darBienvenida(String nombre) throws RemoteException {
        System.out.println("Ejecutando el saludo");
        return "Hola " + nombre + ". Bienvenido!";
    }
}
