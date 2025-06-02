/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaapplication6.view.EnterEmailView;
import javaapplication6.view.LoginView;

/**
 *
 * @author suhritsatyal
 */
public class EnterEmailController {
    
    private final EnterEmailView enterEmailView;
    public EnterEmailController(EnterEmailView enterEmailView)
    {
        this.enterEmailView=enterEmailView;
        this.enterEmailView.LoginActionListener(new LoginActionListener());
    }
    
    public void open()
    {
        this.enterEmailView.setVisible(true);
    }
    
    public void close()
    {
        this.enterEmailView.dispose();
    }
    
    class LoginActionListener implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            LoginView loginView=new LoginView();
            LoginController loginController=new LoginController(loginView);
            loginController.open();
            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        
    }
}
