/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import javaapplication6.view.RegisterVenueView;

/**
 *
 * @author suhritsatyal
 */
public class RegisterVenueController {
    private final RegisterVenueView view;
    
    public RegisterVenueController(RegisterVenueView view)
    {
        this.view=view;
    }
    
    public void open()
    {
        this.view.setVisible(true);
    }
    
    public void close()
    {
        this.view.dispose();
    }
    
}
