/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaapplication6.controller.Mail.SMTPSMailSender;
import javaapplication6.dao.UserDAO;
import javaapplication6.model.EnterEmailModel;
import javaapplication6.view.EnterEmailView;
import javaapplication6.view.EnterOTPView;
import javaapplication6.view.LoginView;
import javax.swing.JOptionPane;

/**
 *
 * @author suhritsatyal
 */
public class EnterEmailController {
    
    private final EnterEmailView enterEmailView;
        private SMTPSMailSender smtpsMailSender=new SMTPSMailSender();
    private UserDAO userDao=new UserDAO();
    public EnterEmailController(EnterEmailView enterEmailView)
    {
        this.enterEmailView=enterEmailView;
        this.enterEmailView.LoginActionListener(new LoginActionListener());
        this.enterEmailView.OTPActionListener(new SendOTPListener());
    }
    
    public void open()
    {
        this.enterEmailView.setVisible(true);
    }
    
    public void close()
    {
        this.enterEmailView.dispose();
    }
    
    class LoginActionListener implements MouseListener
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
    
    class SendOTPListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String email=enterEmailView.getEmailTxt().getText();
            EnterEmailModel enterEmailModel=new EnterEmailModel(email);
            if(!userDao.checkEmail(email))
            {
                JOptionPane.showMessageDialog(enterEmailView, "This email is not registered under our application");
                LoginView loginView=new LoginView();
                LoginController loginController=new LoginController(loginView);
                loginController.open();
                close();
            }
            else
            {
             int random_otp=(int) (Math.random()*(9999-1000))+1000;
             String body="The otp to reset your password is:"+random_otp;
            boolean mailSent=smtpsMailSender.sendMail(email, "Reset Password Verification", body);
             EnterOTPView enterOTPView=new EnterOTPView();
            EnterOTPController enterOTPController=new EnterOTPController(enterOTPView,email,random_otp);
            
            enterOTPController.open();
            close(); 
            }
        }
        
    }
}
