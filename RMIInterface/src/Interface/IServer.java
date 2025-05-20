/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;
import java.rmi.Remote;
import java.rmi.RemoteException;
import Interface.ClientCallBack;
        
        /**
 *
 * @author LENOVO LOQ
 */
public interface IServer extends Remote{
    public String darBienvenida(String nombre)throws RemoteException;
    public void sendPublicMessage(String sender,String message)throws RemoteException;
    void registerClient(ClientCallBack cb, String username) throws RemoteException;
    void sendDirectMessage(String from, String to, String msg) throws RemoteException;
    void unrevisterClient(String username) throws RemoteException;

}