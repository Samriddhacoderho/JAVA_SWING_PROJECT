///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package javaapplication6.controller;
//
//import javaapplication6.view.LoginView;
//import javaapplication6.view.RegistrationView;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import javaapplication6.dao.UserDAO;
//import javax.swing.JOptionPane;
//
//
//public class LoginController {
//    private final UserDAO userDao= new UserDAO();
//    private final LoginView loginView;
//    private boolean isPasswordVisible = false;
//    public LoginController( LoginView loginView){
//        this.loginView=loginView;
//        loginView.addLoginUserListener(new LoginUserListener());
//        loginView.addRegisterListener(new RegistrationListener());
//        loginView.showPasswordButtonListener(new ShowPasswordListener());
//
//    }
//    public void open(){
//        this.loginView.setVisible(true);
//    }
//    public void close(){
//        this.loginView.dispose();
//    }
//
//    private static class UserData {
//
//        public UserData() {
//        }
//    }
//    class RegistrationListener implements MouseListener{
//
//        @Override
//        public void mouseClicked(MouseEvent e) {
//            RegistrationView registrationView= new RegistrationView();
//            RegistrationController registrationController= new RegistrationController(registrationView);
//            registrationController.open();
//            close();
//        }
//
//        @Override
//        public void mousePressed(MouseEvent e) {
//        }
//
//        @Override
//        public void mouseReleased(MouseEvent e) {
//
//        }
//
//        @Override
//        public void mouseEntered(MouseEvent e) {
//
//        }
//
//        @Override
//        public void mouseExited(MouseEvent e) {
//        
//        }
//        
//    }
//    class ShowPasswordListener implements ActionListener {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        isPasswordVisible = !isPasswordVisible;
//        loginView.togglePasswordField(isPasswordVisible);
//    }
//    }
//    
//    class LoginUserListener implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//             String email= loginView.getEmailTextField().getText();
//            String password= String.valueOf(loginView.getPasswordField().getPassword());
//            if(email.isEmpty()||password.isEmpty()){
//                 
//                JOptionPane.showMessageDialog(loginView, 
//                        "Please fill in all the fields.", "Validation Error", 
//                        JOptionPane.ERROR_MESSAGE);
//                
//            }  else{
//                         JOptionPane.showMessageDialog(loginView, 
//                        "Login Failed");
//                         System.out.println("Insert Failed");
//                     }
//                     
//                
//            }
//        }
//        
//    }
//
