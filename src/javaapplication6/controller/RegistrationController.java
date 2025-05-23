/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.dao.UserDAO;
import javaapplication6.model.RegisterModel;
import javaapplication6.view.RegistrationView;
import javax.swing.JOptionPane;

/**
 *
 * @author suhritsatyal
 */
public class RegistrationController {
    
    private final RegistrationView registrationView;
    private final UserDAO userDAO=new UserDAO();
    public RegistrationController(RegistrationView registrationView) {
        this.registrationView=registrationView;
        registrationView.RegisterUserListener(new RegisterUser());
        registrationView.showPasswordListener(new showPassword());
        registrationView.showConfirmPasswordListener(new showConfirmPassword());
    }
    
    
    public void open()
    {
        this.registrationView.setVisible(true);
    }
    
    public void close()
    {
        this.registrationView.dispose();
    }
    
     class showPassword implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(registrationView.getPassShowbtn().getText()=="Show")
                {
                    registrationView.getPassShowbtn().setText("Hide");
                    registrationView.getPassPsf().setEchoChar((char) 0);
                }
                else
                {
                    registrationView.getPassShowbtn().setText("Show");
                    registrationView.getPassPsf().setEchoChar('*');
                }
            }
            
        }
     
     class showConfirmPassword implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(registrationView.getConfirmPassshowbtn().getText()=="Show")
                {
                    registrationView.getConfirmPassshowbtn().setText("Hide");
                    registrationView.getConfirmPsf().setEchoChar((char) 0);
                }
                else
                {
                    registrationView.getConfirmPassshowbtn().setText("Show");
                    registrationView.getConfirmPsf().setEchoChar('*');
                }
            }
            
        }
     class RegisterUser implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name=registrationView.getNameTxt().getText();
            String email=registrationView.getEmailTxt().getText();
            String password=registrationView.getPassPsf().getText();
            String confirmPassword=registrationView.getConfirmPsf().getText();
            
            //validation part:
            String validate_result=validate(name, email, password, confirmPassword);
            if(!validate_result.equals("Registering User..."))
            {
                JOptionPane.showMessageDialog(registrationView, validate_result);
            }
            else
            {
                JOptionPane.showMessageDialog(registrationView, validate_result);
                try {
                    RegisterModel registerModel=new RegisterModel(name, email, password, confirmPassword);
                    boolean result=userDAO.registerUser(registerModel);
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            }
            
        }
        
        public static String validate(String name,String email,String password,String confirmPassword)
        {
            if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
            {
                return "You are required to fill all the fields";
            }
            else if(!password.equals(confirmPassword))
            {
                return "Passwords do not match each other";
            }
            else if(password.length()<=8 || password.length()>=16)
            {
                return "Password is either too short or too long";
            }
            else
            {
                return "Registering User...";
            }
        }
        
    }
}
