<<<<<<< HEAD
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package javaapplication6.controller;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JOptionPane;
//
///**
// *
// * @author Dell
// */
//public class LoginController {
//    private LoginView loginview = new LoginView();
////    code here for controlling opening and closing of view
//
//    
//    
////    code for validation of user during login
//   
//    class userLogin implements ActionListener{
//        @Override
//        public void actionPerformed(ActionEvent e){
//            String email = loginView.getEmailTextField.getText();
//            String password = String.valueOf(loginView.getPasswordField()).getPassword();
//            if(email.isEmpty()||password.isEmpty()){
//                JOptionPane.showMessageDialog(loginView,"Fields cannot be empty!");
//            }
//            else{
//                JOptionPane.showMessageDialog(loginView, "Details are validated");
//            }
//            
//        }
//        
//    }
//    
//    
//}
//
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class LoginController {
    private LoginView loginview = new LoginView();
//    code here for controlling opening and closing of view
//    create loginView in view package

    
    
//    code for validation of user during login
   
    class userLogin implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String email = loginView.getEmailTextField.getText();
            String password = String.valueOf(loginView.getPasswordField()).getPassword();
            if(email.isEmpty()||password.isEmpty()){
                JOptionPane.showMessageDialog(loginView,"Fields cannot be empty!");
            }
            else{
                JOptionPane.showMessageDialog(loginView, "Details are validated");
            }
            
        }
        
    }
    
    
}

>>>>>>> manoj
