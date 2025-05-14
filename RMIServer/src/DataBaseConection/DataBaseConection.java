/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBaseConection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseConection {
    private static String jdbcURL = "jdbc:h2:~/test";
    private static String jdbcUsername = "sa";
    private static String jdbcPassword = "";
    private static String jdbcDrive = "org.h2.Driver";

    public static Connection GetConexionDBH2() throws SQLException{
        Connection connection = null;
        try{
            Class.forName(jdbcDrive);
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("Conexion exitosa");
        }catch(ClassNotFoundException  exception){
            exception.printStackTrace();
            System.out.println("Error conexion a la base de datos");
        }
        return connection;
    }
}