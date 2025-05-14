/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author LENOVO LOQ
 */
public interface IServer extends Remote{
    public String darBienvenida(String nombre)throws RemoteException;
    public int calcularMayor(int num1, int num2)throws RemoteException;
}
