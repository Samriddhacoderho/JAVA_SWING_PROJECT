/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javaapplication6.dao.RegisterVenueDAO;
import javaapplication6.dao.VenueManagerDAO;
import javaapplication6.model.InquiryModel;
import javaapplication6.model.LoginModel;
import javaapplication6.model.VenueDetailsFetchModel;
import javaapplication6.model.VenueModel;
import javaapplication6.view.AdminDashboardView;
import javaapplication6.view.AdminInquiryView;
import javaapplication6.view.AdminLoginView;
import javaapplication6.view.AdminViewBooks;
import javaapplication6.view.BookingDetailsView;
import javaapplication6.view.ChangePassView;
import javaapplication6.view.PastBookingView;
import javaapplication6.view.RegisterVenueView;
import javaapplication6.view.UpdateVenueDetailsView;
import javax.swing.JOptionPane;

/**
 *
 * @author suhritsatyal
 */
public class AdminDashboardController {

    private final AdminDashboardView view;
    private final LoginModel loginModel;
    private VenueModel venueModel;
    RegisterVenueDAO dao = new RegisterVenueDAO();
    VenueManagerDAO vdao = new VenueManagerDAO();

    public AdminDashboardController(AdminDashboardView view, LoginModel loginModel) {
        this.view = view;
        this.view.RegisteVenueUserListener(new VenueRegister());
        this.view.ViewPreviousBookingListener(new BookView());
        this.loginModel = loginModel;
        this.view.UpdeteDetailsUserListener(new UpdateVenueDetails());
        this.view.LogOutUserListener(new LogoutListener());
        this.view.ChangepassUserListener(new ChangePassListener());
        this.view.ViewInquiry(new ViewInquiry());
        
        this.view.RegisterButtonAtCentre(new VenueRegister());
        this.view.UpdateButtonAtCentre(new UpdateVenueDetails());
        this.view.ViewBookButtonAtCentre(new BookView());
        this.view.ViewInquiryAtCentre(new ViewInquiry());
        
        RegisterVenueDAO dao = new RegisterVenueDAO();
        this.venueModel = dao.fetchVenueBasicInfo(loginModel.getEmail());

        if (venueModel != null) {
            view.setVenueName(venueModel.getName());
            view.setVenueEmail(venueModel.getEmail());
            view.setVenueImage(venueModel.getImage());
        } else {
            JOptionPane.showMessageDialog(view, "No venue found for your account.");
        }

    }

    public void open() {
        this.view.setVisible(true);
    }

    public void close() {
        this.view.dispose();
    }

    class VenueRegister implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            VenueModel fetchedModel = dao.adminVenueViewFetch(loginModel.getEmail());
            if (fetchedModel != null) {
                JOptionPane.showMessageDialog(view, "You already have a registered venue. You cannot register another venue!");
            } else {
                RegisterVenueView registerVenueView = new RegisterVenueView();
                RegisterVenueController registerVenueController = new RegisterVenueController(registerVenueView, loginModel);
                registerVenueController.open();
                close();
            }
        }

    }
    class ViewInquiry implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            ArrayList<InquiryModel> result = vdao.getInquiries(loginModel);
            if (result ==null ||result.isEmpty()){
                JOptionPane.showMessageDialog(view, "You do not have any inquiries.");
            }
            else{
            AdminInquiryView inquiryView = new AdminInquiryView();
            AdminInquiryController controller = new AdminInquiryController(inquiryView,loginModel);
            controller.setTableContent(result);
            controller.open();
            close();
                
            }
            
        }
        
    }

    class BookView implements ActionListener {

        @Override
       public void actionPerformed(ActionEvent e) {
            ArrayList<VenueDetailsFetchModel> result = dao.adminVenuesView(loginModel.getEmail());
            if (result==null || result.isEmpty()) {
                JOptionPane.showMessageDialog(view, "There are no bookings yet done!");
            } else {
                AdminViewBooks view = new AdminViewBooks();
                AdminViewBooksController ctrl = new AdminViewBooksController(view, loginModel);
                ctrl.setTableContent(result);
                ctrl.open();
                close();
            }

    }
    }

    class UpdateVenueDetails implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String email = loginModel.getEmail();
            VenueModel fetchedModel = dao.adminVenueViewFetch(email);

            if (fetchedModel != null) {
                VenueModel venueModel = new VenueModel();
                venueModel.setId(fetchedModel.getId());
                venueModel.setName(fetchedModel.getName());
                venueModel.setLocation(fetchedModel.getLocation());
                venueModel.setEmail(fetchedModel.getEmail());
                venueModel.setContact_number(fetchedModel.getContact_number());
                venueModel.setPrice_per_plate((float) fetchedModel.getPrice_per_plate());
                venueModel.setStatus(fetchedModel.getStatus());

                UpdateVenueDetailsView updateView = new UpdateVenueDetailsView();
                UpdateVenueDetailsController updateController = new UpdateVenueDetailsController(updateView, loginModel, venueModel);
                updateController.open();
                close();
            } else {
                JOptionPane.showMessageDialog(view, "No registered venue found for your account.");
            }
        }
    }
    class ChangePassword implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            
        }
    }

    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (JOptionPane.showConfirmDialog(view, "Are you sure you want to log out?") == 0) {
                JOptionPane.showMessageDialog(view, "You have been logged out successfully");
                AdminLoginView loginView = new AdminLoginView();
                AdminLoginController loginController = new AdminLoginController(loginView);
                loginController.open();
                close();
            }
        }

    }
    
    class ChangePassListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ChangePassView changePassView = new ChangePassView();
            AdminChangePasswordController changePasswordController = new AdminChangePasswordController(changePassView, loginModel);
            changePasswordController.open();
            close();
        }
    }
}
