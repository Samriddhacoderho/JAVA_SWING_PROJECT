/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.dao.RegisterVenueDAO;
import javaapplication6.model.LoginModel;
import javaapplication6.model.VenueModel;
import javaapplication6.view.AdminDashboardView;
import javaapplication6.view.RegisterVenueView;
import javax.swing.JOptionPane;

/**
 *
 * @author suhritsatyal
 */
public class RegisterVenueController {
    private final RegisterVenueView view;
    private final LoginModel loginModel;
    private final RegisterVenueDAO dao=new RegisterVenueDAO();
    
    public RegisterVenueController(RegisterVenueView view,LoginModel loginModel)
    {
        this.view=view;
        this.view.RegisterVenueListener(new RegisterVenueListener());
        this.loginModel=loginModel;
    }
    
    public void open()
    {
        this.view.setVisible(true);
    }
    
    public void close()
    {
        this.view.dispose();
    }
    
    class RegisterVenueListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name=view.getNameTxt().getText().strip();
            String location=view.getLocationTxt().getText().strip();
            String contact_number=view.getNumberTxt().getText().strip();
            Double price_per_plate=Double.valueOf(view.getPriceTxt().getText().strip());
            
            //validation ya haala:
            //ya halaa
            //

            VenueModel model=new VenueModel(name, location,loginModel.getEmail(),contact_number, price_per_plate, "Unbooked");
            boolean result=dao.registerVenue(model);
            if(result)
            {
                JOptionPane.showMessageDialog(view, "Your venue has been successfully registered.");
                AdminDashboardView view=new AdminDashboardView();
                AdminDashboardController ctrl=new AdminDashboardController(view, loginModel);
                ctrl.open();
                close();
            }
            
        }
        
    }
    
}
