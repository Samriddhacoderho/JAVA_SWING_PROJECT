/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import javaapplication6.view.EnterOTPView;

/**
 *
 * @author suhritsatyal
 */

public class EnterOTPController {
    private final EnterOTPView enterOTPView;
    public EnterOTPController(EnterOTPView enterOTPView)
    {
        this.enterOTPView=enterOTPView;
    }
    
    public void open()
    {
        this.enterOTPView.setVisible(true);
    }
    
    public void close()
    {
        this.enterOTPView.dispose();
    }
}
