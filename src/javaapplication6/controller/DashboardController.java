/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.view.ChangePassView;
import javaapplication6.view.DashboardView;

/**
 *
 * @author Dell
 */
public class DashboardController {
    private final DashboardView dashView;
    
    
    public DashboardController(DashboardView dashView){
        this.dashView = dashView;
        dashView.ChangePasswordListener(new ChangePassListener());
        
    }
     public void open()
    {
        this.dashView.setVisible(true);
    }
    
    public void close()
    {
        this.dashView.dispose();
    }
    class ChangePassListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ChangePassView changePass = new ChangePassView();
            ChangePasswordController changePassController = new ChangePasswordController(changePass);
            changePassController.open();
            close();
            
            
        }
    }
    
}
