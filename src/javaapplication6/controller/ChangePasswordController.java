/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.view.ChangePassView;
import javax.swing.JOptionPane;
import javaapplication6.dao.UserDAO;
import javaapplication6.model.LoginModel;
import javaapplication6.view.DashboardView;

/**
 *
 * @author manoj
 */
public class ChangePasswordController {
    private final ChangePassView changePassView;
    private final UserDAO userDao = new UserDAO();
    private final LoginModel loginModel;
    
   
        
    
    public ChangePasswordController(ChangePassView changePassView,LoginModel loginModel){
        this.changePassView = changePassView;
        this.changePassView.PasswordChangeConfirm(new ChangePassListener());
        this.changePassView.ShowCurrentNav(new ShowCurrentPassword());
        this.changePassView.ShowNewNav(new ShowNewPassword());
        this.changePassView.ShowConfirmNav(new ShowConfirmPassword());
        this.changePassView.backListener(new GoBackListener());
        this.loginModel= loginModel;
        
    }
    public void open(){
        this.changePassView.setVisible(true);
    }
    public void close(){
        this.changePassView.dispose();
    }
    class ChangePassListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
//            
        String currentPass = changePassView.getjPasswordField3().getText();
        String newPass = changePassView.getjPasswordField1().getText();
        String confirmPass = changePassView.getjPasswordField2().getText();
        
        if (currentPass.equals(newPass)) {
            JOptionPane.showMessageDialog(changePassView, "New password cannot be the same as the current password");
            return;
        }
        
        if(currentPass.isEmpty()||newPass.isEmpty()||confirmPass.isEmpty()){
            JOptionPane.showMessageDialog(changePassView,"Please fill in all fields");
            return;
        }
        if(!newPass.equals(confirmPass)){
            JOptionPane.showMessageDialog(changePassView, "New Password and confirm Password do not match");
            return;
        }
        boolean isCorrect = userDao.checkCurrentPassword(loginModel,currentPass);
        if(!isCorrect){
            JOptionPane.showMessageDialog(changePassView,"Current Password is incorrect");
            return;
        }
        boolean updated = userDao.updatePassword(loginModel, newPass);
        if(updated){
            JOptionPane.showMessageDialog(changePassView, "Password successfully changed");
            DashboardView dashboardView=new DashboardView();
            DashboardController dashboardController=new DashboardController(dashboardView, loginModel);
            dashboardController.open();
            close();
        }else{
            JOptionPane.showMessageDialog(changePassView, "Failed to Update password , please try again!");
        }
        }
    }
    
    class ShowCurrentPassword implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (changePassView.getCurrentShowBTN().getText().equals("Show")) {
                changePassView.getCurrentShowBTN().setText("Hide");
                changePassView.getjPasswordField3().setEchoChar((char) 0);
            } else {
                changePassView.getCurrentShowBTN().setText("Show");
                changePassView.getjPasswordField3().setEchoChar('*');
            }
        }
    }

    class ShowNewPassword implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (changePassView.getNewPassBTN().getText().equals("Show")) {
                changePassView.getNewPassBTN().setText("Hide");
                changePassView.getjPasswordField1().setEchoChar((char) 0);
            } else {
                changePassView.getNewPassBTN().setText("Show");
                changePassView.getjPasswordField1().setEchoChar('*');
            }
        }
    }

    class ShowConfirmPassword implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (changePassView.getConfirmPassBTN().getText().equals("Show")) {
                changePassView.getConfirmPassBTN().setText("Hide");
                changePassView.getjPasswordField2().setEchoChar((char) 0);
            } else {
                changePassView.getConfirmPassBTN().setText("Show");
                changePassView.getjPasswordField2().setEchoChar('*');
            }
        }
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
    
    
   
}
