/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.view.HomeView;
import javaapplication6.view.RegistrationView;

/**
 *
 * @author suhritsatyal
 */
public class HomeController {
    private final HomeView homeView;

    public HomeController(HomeView homeView) {
        this.homeView=homeView;
        homeView.GetStartedNavigationListener(new NavigationListener());
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
            RegistrationView registrationView=new RegistrationView();
            RegistrationController registrationController=new RegistrationController(registrationView);
            registrationController.close();
            close();
            //ok
            
        }
        
    }
    
    
    
    
    
}
