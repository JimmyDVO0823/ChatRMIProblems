/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBaseConection.DTOs;

/**
 *
 * @author LENOVO LOQ
 */
public class User {
    private String username;
    private String ip;
    private int port;

    public User(String username, String ip, int port) {
        this.username = username;
        this.ip = ip;
        this.port = port;
    }

    //Constructor por defecto
    public User() {}


    //GETTERS SETTERS
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
