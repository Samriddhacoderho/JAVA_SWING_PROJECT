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
import javaapplication6.model.VenueDetailsFetchModel;
import javaapplication6.view.DashboardView;
import javaapplication6.view.MyBookings;
import javaapplication6.view.PastBookingView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author suhritsatyal
 */
public class PastBookingController {

    private final PastBookingView pastBookingView;
    private final LoginModel loginModel;
    private final BookVenueDAO dao = new BookVenueDAO();

    public PastBookingController(PastBookingView pastBookingView, LoginModel loginModel) {
        this.pastBookingView = pastBookingView;
        this.loginModel = loginModel;
        this.pastBookingView.BackDashboard(new Back());
    }

    public void open() {
        this.pastBookingView.setVisible(true);

    }

    public void close() {
        this.pastBookingView.dispose();
    }

    public void setTableContent(ArrayList<VenueDetailsFetchModel> venueLists) {
        DefaultTableModel model = (DefaultTableModel) pastBookingView.getjTable1().getModel();
        model.setRowCount(0);

        for (VenueDetailsFetchModel venueList : venueLists) {
            model.addRow(new Object[]{venueList.getVenue_id(), venueList.getVenue_name(), venueList.getVenue_location(), venueList.getPrice_per_plate(), venueList.getVenue_email(), venueList.getVenue_contactnum(), venueList.getPayed()});
        }
    }

    class Back implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            DashboardView view=new DashboardView();
            DashboardController ctrl=new DashboardController(view, loginModel);
            ctrl.open();
            close();
        }

    }

}
