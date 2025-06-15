/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.model.LoginModel;
import javaapplication6.view.AdminDashboardView;
import javaapplication6.view.RegisterVenueView;

/**
 *
 * @author suhritsatyal
 */
public class AdminDashboardController {
    private final AdminDashboardView view;
    private final LoginModel loginModel;
    
    public AdminDashboardController(AdminDashboardView view,LoginModel loginModel)
    {
        this.view=view;
        this.view.RegisteVenueUserListener(new VenueRegister());
        this.loginModel=loginModel;
    }
    
    public void open()
    {
        this.view.setVisible(true);
    }
    
    public void close()
    {
        this.view.dispose();
    }
    
    
    class VenueRegister implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            RegisterVenueView registerVenueView=new RegisterVenueView();
            RegisterVenueController registerVenueController=new RegisterVenueController(registerVenueView,loginModel);
            registerVenueController.open();
            close();
        }
        
    }
    
}
