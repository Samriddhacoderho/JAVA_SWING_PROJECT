/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.controller.Mail.SMTPSMailSender;
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
    private SMTPSMailSender smtpsMailSender;
    public EnterOTPController(EnterOTPView enterOTPView,String email)
    {
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
            int random_otp=(int) (Math.random()*(9999-1000))+1000;
            String body="The otp to reset your password is:"+random_otp;
            boolean mailSent=smtpsMailSender.sendMail(email, "Reset Password Verification", body);
            if(!mailSent)
            {
                       JOptionPane.showMessageDialog(enterOTPView, "Faialed to send OTP. Please try again later!");
            }
            else
            {
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
                               ResetModel resetModel=new ResetModel(email, password);
                               //dao wala le banayechi ill call
                           }
                }
            }
        }
        
    }
    
    
}
