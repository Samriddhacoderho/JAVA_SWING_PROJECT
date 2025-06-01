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
import javaapplication6.model.EditNameModel;
import javaapplication6.model.LoginModel;
import javaapplication6.model.RegisterModel;
import javaapplication6.view.EditNameView;

/**
 *
 * @author manoj
 */


public class EditNameController {
    final private EditNameView editNameView;
   final private UserDAO userDAO=new UserDAO();   
    private final LoginModel loginModel;
   
    public EditNameController(EditNameView view,LoginModel loginModel){
        this.editNameView = view;
        this.editNameView.UpdateNameListener(new EditNameListener());
        this.editNameView.backListener(new GoBackListener());
        this.loginModel=loginModel;
        
    }
    
    public void open() {
        editNameView.setVisible(true);
    }

    public void close() {
        editNameView.dispose();
    }
    
    class GoBackListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            DashboardView dashboardView=new DashboardView();
            DashboardController dashboardController=new DashboardController(dashboardView, loginModel);
            dashboardController.open();
            close();
        }
        
    }
    
    class EditNameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String newName=editNameView.getjTextField1().getText();
            
            //validation part
            String validation=validate(newName);
            if(!validation.equals("Are you sure you want to update your name?"))
            {
                JOptionPane.showMessageDialog(editNameView, validation);
            }
            else
            {
                if(JOptionPane.showConfirmDialog(editNameView, validation)==0)
                {
                try {
                    EditNameModel editNameModel=new EditNameModel(newName);
                    String result=userDAO.editName(editNameModel,loginModel);
                    JOptionPane.showMessageDialog(editNameView, result);
                    if(result.equals("Your name was successfully changed"))
                    {
                        DashboardView dashboardView=new DashboardView();
                        DashboardController dashboardController=new DashboardController(dashboardView, loginModel);
                        dashboardController.open();
                        close();
                    }
                    
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(editNameView, error.getMessage());
                }
                }
            }
            
        }
        
        public static String validate(String newName)
        {
            if(newName.isEmpty())
            {
                return "Name cannot be empty";
            }
            return "Are you sure you want to update your name?";
        }
    }
    
}
