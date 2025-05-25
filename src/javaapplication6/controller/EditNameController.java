/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;
import javaapplication6.dao.UserUpdateNameDAO;
import javaapplication6.view.Dashboard;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.model.RegisterModel;

/**
 *
 * @author manoj
 */
public class EditNameController {
   final private Dashboard dashboardView;
   final private UserUpdateNameDAO userDAO;
//   private int userId;
   private String newName;
    public EditNameController(Dashboard view){
        this.dashboardView = view;
        this.userDAO = new UserUpdateNameDAO( );
        this.dashboardView.EditNameListener(new EditNameListener());
        
    }
    class EditNameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
//            int userId = dashboardView.getUserId();
//            String newName = dashboardView.getNewName();
            RegisterModel user = new RegisterModel(newName);
            boolean success = userDAO.EditName(user);
            if(success){
                JOptionPane.showMessageDialog(dashboardView, "Name updated Successfully");
            }
            else{
                JOptionPane.showMessageDialog(dashboardView,"Failed to update name");
            }
        }
    }
    
}
