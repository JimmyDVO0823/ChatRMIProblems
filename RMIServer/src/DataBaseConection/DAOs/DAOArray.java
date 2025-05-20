package DataBaseConection.DAOs;

import DataBaseConection.DTOs.User;

import java.util.ArrayList;

public class DAOArray {

    public ArrayList<User> createUsers()  {
        ArrayList<User> users = new ArrayList<>();
        return users;
    }

    public void addUser(ArrayList<User> users,User user)  {
        if (!users.contains(user)) users.add(user);
    }

    public void deleteUser(ArrayList<User> users, User user)  {
        users.remove(user);
    }

    public User searchUser(ArrayList<User> users, User user)  {
        return users.get(users.indexOf(user));
    }

//    public ArrayList<User> getUsers()  {
//        return null;
//    }

    public void uploadUser(User oldUser, User newUser)  {
        oldUser.setIp(newUser.getIp());
        oldUser.setPort(newUser.getPort());
        oldUser.setUsername(newUser.getUsername());
    }
}
