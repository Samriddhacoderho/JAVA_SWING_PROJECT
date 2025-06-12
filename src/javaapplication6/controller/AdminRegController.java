/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaapplication6.dao.UserDAO;
import javaapplication6.dao.VenueManagerDAO;
import javaapplication6.model.RegisterModel;
import javaapplication6.view.AdminRegisterView;
import javaapplication6.view.LoginView;
import javaapplication6.view.UserRegisterView;
import javax.swing.JOptionPane;


public class AdminRegController {
    
    private final AdminRegisterView view;
    private final VenueManagerDAO dao=new VenueManagerDAO();
    public AdminRegController(AdminRegisterView view) {
        this.view=view;
        view.RegisterUserListener(new RegisterUser());
        view.showPasswordListener(new showPassword());
        view.showConfirmPasswordListener(new showConfirmPassword());
        view.LoginNavigate(new LoginNavigationListener());
    }
    
    
    public void open()
    {
        this.view.setVisible(true);
    }
    
    public void close()
    {
        this.view.dispose();
    }
    
     class showPassword implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(view.getPassShowbtn().getText()=="Show")
                {
                    view.getPassShowbtn().setText("Hide");
                    view.getPassPsf().setEchoChar((char) 0);
                }
                else
                {
                    view.getPassShowbtn().setText("Show");
                    view.getPassPsf().setEchoChar('*');
                }
            }
            
        }
     
     class showConfirmPassword implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(view.getConfirmPassshowbtn().getText()=="Show")
                {
                    view.getConfirmPassshowbtn().setText("Hide");
                    view.getConfirmPsf().setEchoChar((char) 0);
                }
                else
                {
                    view.getConfirmPassshowbtn().setText("Show");
                    view.getConfirmPsf().setEchoChar('*');
                }
            }
            
        }
     class RegisterUser implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name=view.getNameTxt().getText();
            String email=view.getEmailTxt().getText();
            String password=view.getPassPsf().getText();
            String confirmPassword=view.getConfirmPsf().getText();
            
            //validation part:
            String validate_result=validate(name, email, password, confirmPassword);
            if(!validate_result.equals("Registering Venue Manager..."))
            {
                JOptionPane.showMessageDialog(view, validate_result);
            }
            else
            {
                if(JOptionPane.showConfirmDialog(view, "Are you sure you want to register?")==0)
                {
                try {
                    RegisterModel registerModel=new RegisterModel(name, email, password, confirmPassword);
                    boolean result=dao.registerVM(registerModel);
                    if(result)
                    {
                        JOptionPane.showMessageDialog(view, "Registration Successful");
                        LoginView loginView=new LoginView();
                        LoginController loginController=new LoginController(loginView);
                        loginController.open();
                        close();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(view, "Registration Failed");
                    }
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
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
     
     class LoginNavigationListener implements MouseListener
     {

        @Override
        public void mouseClicked(MouseEvent e) {
            LoginView loginView=new LoginView();
            LoginController loginController=new LoginController(loginView);
            loginController.open();
            close();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
         
     }
}
