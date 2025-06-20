/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.dao.RegisterVenueDAO;
import javaapplication6.model.LoginModel;
import javaapplication6.model.VenueDetailsFetchModel;
import javaapplication6.model.VenueModel;
import javaapplication6.view.AdminDashboardView;
import javaapplication6.view.AdminLoginView;
import javaapplication6.view.BookingDetailsView;
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

    public AdminDashboardController(AdminDashboardView view, LoginModel loginModel) {
        this.view = view;
        this.view.RegisteVenueUserListener(new VenueRegister());
        this.view.ViewPreviousBookingListener(new BookView());
        this.loginModel = loginModel;
        this.view.UpdeteDetailsUserListener(new UpdateVenueDetails());
        this.view.LogOutUserListener(new LogoutListener());
        this.view.setUserName(loginModel.getName());
        this.view.setUserEmail(loginModel.getEmail());
        this.view.setUserImage(loginModel.getImage());
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
            RegisterVenueView registerVenueView = new RegisterVenueView();
            RegisterVenueController registerVenueController = new RegisterVenueController(registerVenueView, loginModel);
            registerVenueController.open();
            close();
        }

    }

    class BookView implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BookingDetailsView view = new BookingDetailsView();
            AdminViewBookingController ctrl = new AdminViewBookingController(view, loginModel);
            ctrl.open();
            close();

        }

    }

    class UpdateVenueDetails implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String email = loginModel.getEmail();
            RegisterVenueDAO dao = new RegisterVenueDAO();
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

}