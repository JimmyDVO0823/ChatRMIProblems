/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataBaseConection.DAOs;

import DataBaseConection.DTOs.User;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author LENOVO LOQ
 */
public interface IDAO {
    public void createUsers() throws SQLException;
    public void addUser(int port, String username, String password) throws SQLException;
    public void deleteUser(int port) throws SQLException;
    public User searchUser(int port) throws SQLException;
    public ArrayList<User> getUsers() throws SQLException;
    public void uploadUser(String username, User user)throws SQLException;
}
