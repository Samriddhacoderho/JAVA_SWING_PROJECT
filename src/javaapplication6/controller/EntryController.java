/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.view.AdminLoginView;
import javaapplication6.view.EntryView;
import javaapplication6.view.LoginView;

/**
 *
 * @author suhritsatyal
 */
public class EntryController {
    private final EntryView homeView;

    public EntryController(EntryView homeView) {
        this.homeView=homeView;
        homeView.GetStartedNavigationListener(new NavigationListener());
        this.homeView.GetStartedVMNavigationListener(new NavVMListener());
    }
    public void open()
    {
        this.homeView.setVisible(true);
    }
    
    public void close()
    {
        this.homeView.dispose();
    }
    
    class NavigationListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginView loginView = new LoginView();
            LoginController loginController =new LoginController(loginView);
            loginController.open();
            close();
            //ok
            
        }

        
        
    }
    
    class NavVMListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            AdminLoginView view=new AdminLoginView();
            AdminLoginController ctrl=new AdminLoginController(view);
            ctrl.open();
            close();
        }
        
    }
    
    
    
    
    
}
