/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataBaseConection;

import DTOs.User;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author LENOVO LOQ
 */
public interface IDAO {
    public void createTableUsers() throws SQLException;
    public void addUser(int id, String nombre, int edad, boolean prioritario) throws SQLException;
    public void deleteUser(int id) throws SQLException;
    public User searchUser(int id) throws SQLException;
    public ArrayList<User> getUsers() throws SQLException;
    public void uploadUser(String dto, int id)throws SQLException;
}
