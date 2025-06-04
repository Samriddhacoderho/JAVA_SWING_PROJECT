/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.controller.Mail.SMTPSMailSender;
import javaapplication6.dao.UserDAO;
import javaapplication6.model.ResetModel;
import javaapplication6.view.EnterOTPView;
import javax.swing.JOptionPane;

/**
 *
 * @author suhritsatyal
 */

public class EnterOTPController {
    private final EnterOTPView enterOTPView;
    private String email;
    private UserDAO userDAO=new UserDAO();
    private int random_otp;

    public EnterOTPController(EnterOTPView enterOTPView,String email,int random_otp)
    {
        this.random_otp=random_otp;
        this.enterOTPView=enterOTPView;
        this.email=email;
        this.enterOTPView.VerifyActionListener(new VerifyListener());
    }
    
    public void open()
    {
        this.enterOTPView.setVisible(true);
    }
    
    public void close()
    {
        this.enterOTPView.dispose();
    }
    
    class VerifyListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            
           
                String user_otp=enterOTPView.getOtpTxt().getText();
                if(!user_otp.equals(String.valueOf(random_otp)))
                {
                    JOptionPane.showMessageDialog(enterOTPView, "Incorrect OTP Code, please try again");
                }
                else
                {
                    String password=JOptionPane.showInputDialog(email,"Enter your new password:");
                           if(password.trim().isEmpty())
                           {
                               JOptionPane.showMessageDialog(enterOTPView, "Password cannot be empty!");
                           }
                           else
                           {
                               if(JOptionPane.showConfirmDialog(enterOTPView, "Are you sure you want to reset your password?")==0)
                               {
                               ResetModel resetModel=new ResetModel(email, password);
                               boolean result=userDAO.resetPassword(resetModel);
                               if(!result)
                               {
                                   JOptionPane.showMessageDialog(enterOTPView, "There were some error occured");
                               }
                               else
                               {
                                   JOptionPane.showMessageDialog(enterOTPView, "Your password has been reset successfully");
                               }
                               }
                           
                }
            }
        }
        
    }
}
