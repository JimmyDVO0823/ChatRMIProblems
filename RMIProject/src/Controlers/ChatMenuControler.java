/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlers;

import GUIRMI.MenuChats;
import GUIRMI.PrivateChatGUI;
import GUIRMI.PublicChatGUI;
import Model.Facade;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author LENOVO LOQ
 */
public class ChatMenuControler implements ISubscriber {
    private Facade facade;
    private MenuChats menu;
    ArrayList<String> model;

    public ChatMenuControler(MenuChats menu) {
        model = new ArrayList<>();
        System.out.println("construyendo el controlador del menu de chats");
        facade = Facade.getInstance();
        facade.subscribe(this);
        this.menu = menu;
        menu.getModel().addAll(model);
        // para que al cerrar notifique:
        menu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        menu.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    facade.getServer().unrevisterClient(facade.getUsername());
                } catch (RemoteException ex) {
                    // log
                }
                menu.dispose();
            }
        });
    }

    public void updateConectedUsers(ArrayList<String> users) {
        System.out.println("se intenta actualizar");
        menu.getModel().clear();
        menu.getModel().addAll(users);
    }

    public void startPrivateChat() {
        String reciver = menu.getLstConectedUsers().getSelectedValue();
        PrivateChatGUI privateChatGUI = PrivateChatGUI.getInstance();
        if (reciver.equals(facade.getReciver()) && privateChatGUI.isShowing() && privateChatGUI.isVisible()) {
            return;
        }
        facade.setReciver(reciver);
        privateChatGUI.getLblUser().setText(reciver);
        privateChatGUI.getTxtHistory().setText("");
        privateChatGUI.getTxtMessagePrivateArea().setText("");


        //Se cerea la ueva vista, si es que deberia
        if (privateChatGUI.isShowing() && privateChatGUI.isVisible()) {
            System.out.println("iguales");
            return;
        }

        privateChatGUI.setVisible(true);

        //PrivateChatGUI.main(new String[0]);
        System.out.println("Destinatario: " + reciver);
    }

    public void startPublicChat() {
        PublicChatGUI publicChatGUI = PublicChatGUI.getInstance();
        publicChatGUI.setVisible(true);
    }


    @Override
    public void reciveNotification(String notification) {

        System.out.println("Controlador de menu de chats suscrito");
    }
}
