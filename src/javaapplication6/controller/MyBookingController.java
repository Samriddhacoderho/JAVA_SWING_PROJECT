/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import javaapplication6.dao.BookVenueDAO;
import javaapplication6.model.LoginModel;
import javaapplication6.model.VenueDetailsFetchModel;
import javaapplication6.model.VenueModel;
import javaapplication6.view.AhomeView;

import javaapplication6.view.DashboardView;
import javaapplication6.view.MyBookings;
import javax.swing.JOptionPane;

/**
 *
 * @author manoj
 */
public class MyBookingController {

    private final MyBookings bookingView;
    private final LoginModel loginModel;
    private VenueDetailsFetchModel result;

    private final BookVenueDAO dao = new BookVenueDAO();

    public MyBookingController(MyBookings bookingView, LoginModel loginModel,VenueDetailsFetchModel result) {
        this.bookingView = bookingView;
        this.bookingView.cancelBookingListener(new CancelBooking());
        this.bookingView.backListener(new BackProfile());
        this.loginModel = loginModel;
        this.result=result;

    }

    public void open() {

        this.bookingView.setVisible(true);
        result.getVenue_id();
        this.bookingView.getVenueName().setText(result.getVenue_name());
        this.bookingView.getVenueLocation().setText(result.getVenue_location());
        this.bookingView.getAdminEmail().setText(result.getVenue_email());
        this.bookingView.getAdminPhone().setText(result.getVenue_contactnum());
        this.bookingView.getGuestNumber().setText(Integer.toString(result.getEstimated_guests()));
        this.bookingView.getPrice().setText("Rs." + Long.toString(result.getTotal_price()));
    }

    public void close() {
        this.bookingView.dispose();
    }

    class CancelBooking implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(result.getPayed().equalsIgnoreCase("payed"))
            {
                JOptionPane.showMessageDialog(bookingView, "You cannot cancel this booking since the payment is already done! Contact the venue manager");
            }
            else
            {
            if (JOptionPane.showConfirmDialog(bookingView, "Are you sure you want to cancel this booking?") == 0) {
                int id=result.getVenue_id();
                boolean result = dao.cancelBooking(dao.getVenue_in_mybookingPage(id, loginModel.getEmail()));
                if (!result) {
                    JOptionPane.showMessageDialog(bookingView, "There was some error cancelling your booking at the moment!");
                } else {
                    JOptionPane.showMessageDialog(bookingView, "Your booking was successfully cancelled");
                    AhomeView view = new AhomeView();
                    AHomeController ctrl = new AHomeController(view, loginModel);
                    ctrl.open();
                    close();
                }

            }
            }
        }

    }

    class BackProfile implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            DashboardView view = new DashboardView();
            DashboardController ctrl = new DashboardController(view, loginModel);
            ctrl.open();
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

    class BacktoProfile implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            DashboardView dash = new DashboardView();
            DashboardController dashController = new DashboardController(dash, loginModel);
            dashController.open();
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
}
