/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;
import javaapplication6.view.DashboardView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.dao.UserDAO;
import javaapplication6.model.RegisterModel;
import javaapplication6.view.EditNameView;

/**
 *
 * @author manoj
 */


public class EditNameController {
    final private EditNameView editNameView;
   final private UserDAO userDAO=new UserDAO();   
   
    public EditNameController(EditNameView view){
        this.editNameView = view;
        this.editNameView.UpdateNameListener(new EditNameListener());
        
    }
    
    public void open() {
        editNameView.setVisible(true);
    }

    public void close() {
        editNameView.dispose();
    }
    
    class EditNameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String newName=editNameView.getjTextField1().getText();
            //manoj le ya bhitra kam garna baki cha
//            boolean success = userDAO.EditName(user);
//            if(success){
//                JOptionPane.showMessageDialog(editNameView, "Name updated Successfully");
//            }
//            else{
//                JOptionPane.showMessageDialog(editNameView,"Failed to update name");
//            }
        }
    }
    
}
