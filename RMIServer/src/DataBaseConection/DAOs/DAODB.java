/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBaseConection.DAOs;

import DataBaseConection.DTOs.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author LENOVO LOQ
 */
public class DAODB implements IDAO{

    private String SENTENCIA_CREAR_TABLA = "CREATE TABLE IF NOT EXISTS USUARIOS (USERNAME VARCHAR(30) PRIMARY KEY,IP INTEGER, PASSWORD VARCHAR(30) NOT NULL)";
    private String SENTENCIA_ELIMINAR_USUARIO = "DELETE FROM USUARIOS WHERE USERNAME = ?";
    private String SENTENCIA_OBTENER_USUARIO = "SELECT USERNAME, IP, PASSWORD FROM USUARIOS";
    private String SENTENCIA_AGREGAR_USUARIO = "INSERT INTO USUARIOS (USERNAME, IP, PASSWORD) VALUES (?, ?, ?)";
    private String SENTENCIA_ACTUALIZAR_USUARIO = "UPDATE USUARIOS SET USERNAME = ?, IP = ?, PASSWORD = ? WHERE USERNAME = ?;";


    @Override
    public void createUsers() throws SQLException {

    }

    @Override
    public void addUser(int port, String username, String password) throws SQLException {

    }

    @Override
    public void deleteUser(int port) throws SQLException {

    }

    @Override
    public User searchUser(int port) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<User> getUsers() throws SQLException {
        return null;
    }

    @Override
    public void uploadUser(String port, User user) throws SQLException {

    }
}
