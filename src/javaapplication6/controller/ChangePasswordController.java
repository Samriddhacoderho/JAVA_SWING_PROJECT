/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.view.ChangePassView;


/**
 *
 * @author Dell
 */
public class ChangePasswordController {
    private final ChangePassView changePass;
    
    public ChangePasswordController(ChangePassView changePass){
        this.changePass = changePass;
        changePass.PasswordChangeConfirm(new ChangePassListener());
        
    }
    public void open(){
        this.changePass.setVisible(true);
    }
    public void close(){
        this.changePass.dispose();
    }
    
    class ChangePassListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){    
            
            
        }
    }
    
    //manoj le changePassView ko show buttons haru ko lai ni action banauna paryo hai...
}
