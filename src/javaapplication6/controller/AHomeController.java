/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javaapplication6.dao.BookVenueDAO;
import javaapplication6.model.LoginModel;
import javaapplication6.model.VenueModel;
import javaapplication6.view.AhomeView;
import javaapplication6.view.DashboardView;
import javaapplication6.view.VenueListView;
import javax.swing.JOptionPane;


/**
 *
 * @author ishan-college
 */
public class AHomeController {
    private final AhomeView ahomeView;
    private final LoginModel loginModel;
    private final BookVenueDAO bookVenueDAO=new BookVenueDAO();
    
    public AHomeController(AhomeView ahomeView,LoginModel loginModel) {
        this.ahomeView=ahomeView;
        this.loginModel=loginModel;
        this.ahomeView.ProfileListener(new ProfileActionListener());
        this.ahomeView.SearchListener(new SearchActionListener());
        this.ahomeView.BrowsektmListener(new BrowseListener(this.ahomeView.getKtmLBL().getText()));
        this.ahomeView.BrowsepkrListener(new BrowseListener(this.ahomeView.getPkrLBL().getText()));
        this.ahomeView.BrowsebktListener(new BrowseListener(this.ahomeView.getBkpLBL().getText()));
        this.ahomeView.BrowseptnListener(new BrowseListener(this.ahomeView.getPtnLBL().getText()));
        this.ahomeView.BrowselmbListener(new BrowseListener(this.ahomeView.getLmbLBL().getText()));
        this.ahomeView.BrowsechiListener(new BrowseListener(this.ahomeView.getChiLBL().getText()));
        
    }
    public void open()
    {
        this.ahomeView.setVisible(true);
    }
    
    public void close()
    {
        this.ahomeView.dispose();
    }
    
    
    class ProfileActionListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            DashboardView dashboardView=new DashboardView();
            DashboardController dashboardController=new DashboardController(dashboardView, loginModel);
            dashboardController.open();
            close();
        }
        
    }
    
    class SearchActionListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
             String venueName=ahomeView.getVenue().getText();
             String venueLocation=ahomeView.getLocationField().getText();
             
             //validation-part
             boolean validation=validate(venueName,venueLocation);
             
             if(!validation)
             {
                 JOptionPane.showMessageDialog(ahomeView, "Please enter both fields correctly");
             }
             else
             {
                 VenueModel model=new VenueModel(venueName,venueLocation);
                 VenueModel result=bookVenueDAO.searchParticularVenue(model);
                 if(result==null)
                 {
                     JOptionPane.showMessageDialog(ahomeView, "The searched venue is not available in our application!");
                 }
                 else
                 {
                     ArrayList<VenueModel> venuelist=new ArrayList<VenueModel>();
                     venuelist.add(result);
                     VenueListView venuelistView=new VenueListView();
//                     VenueListController venueListController=new VenueListController(venuelistView);
//                     venueListController.setTableContent(venuelist);
//                     venueListController.open();
//                     close();
                 }
                
             }
        }
        
        public static boolean validate(String vN,String vL)
        {
            if(vN.isEmpty() || vL.isEmpty())
            {
                return false;
            }
            else
            {
                return true;
            }
        }
    }
    
    class BrowseListener implements ActionListener
    {
        private String location;
        
        BrowseListener(String location)
        {
            this.location=location;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            VenueModel model=new VenueModel(location);
            ArrayList<VenueModel> result=bookVenueDAO.searchLocationVenue(model);
            if(result.size()==0)
            {
                JOptionPane.showMessageDialog(ahomeView, "We currently have no venue parties registered in our application from "+this.location);
            }
            else
            {
                VenueListView venueListView=new VenueListView();
                VenueListController venueListController=new VenueListController(venueListView,this.location);
                venueListController.setTableContent(result);
                venueListController.open();
                close();
            }
        }
        
    }
}
