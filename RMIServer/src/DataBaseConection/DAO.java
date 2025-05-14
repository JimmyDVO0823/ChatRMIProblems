/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBaseConection;

import DTOs.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author LENOVO LOQ
 */
public class DAO implements IDAO{

    private String SENTENCIA_CREAR_TABLA = "CREATE TABLE IF NOT EXISTS USUARIOS (USERNAME VARCHAR(30) PRIMARY KEY, PASSWORD VARCHAR(30) NOT NULL)";
    private String SENTENCIA_ELIMINAR_USUARIO = "DELETE FROM USUARIOS WHERE USERNAME = ?";
    private String SENTENCIA_OBTENER_USUARIO = "SELECT USERNAME, PASSWORD FROM USUARIOS";
    private String SENTENCIA_AGREGAR_USUARIO = "INSERT INTO USUARIOS (USERNAME, PASSWORD) VALUES (?, ?)";
    private String SENTENCIA_ACTUALIZAR_USUARIO = "UPDATE USUARIOS SET USERNAME = ?, PASSWORD = ? WHERE USERNAME = ?;";


    @Override
    public void createTableUsers() throws SQLException {

    }

    @Override
    public void addUser(int id, String nombre, int edad, boolean prioritario) throws SQLException {

    }

    @Override
    public void deleteUser(int id) throws SQLException {

    }

    @Override
    public User searchUser(int id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<User> getUsers() throws SQLException {
        return null;
    }

    @Override
    public void uploadUser(String dto, int id) throws SQLException {

    }
}
