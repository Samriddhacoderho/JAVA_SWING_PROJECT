/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;
import javaapplication6.dao.UserDAO;
import javaapplication6.view.Dashboard;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author manoj
 */
public class EditName {
    private Dashboard dashboardView;
    private UserDAO userDAO;
    public EditName(Dashboard view){
        this.dashboardView = view;
        this.userDAO = new UserDAO();
        this.dashboardView.addEditNameListener(new EditNameListener());
        
    }
    class EditNameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int userId = dashboardView.getUserId();
            String newName = dashboardView.getNewName();
            boolean success = userDAO.updateUserName(userId,newName);
            if(success){
                JOptionPane.showMessageDialog(dashboardView, "Name updated Successfully");
            }
            else{
                JOptionPane.showMessageDialog(dashboardView,"Failed to update name");
            }
        }
    }
    
}
