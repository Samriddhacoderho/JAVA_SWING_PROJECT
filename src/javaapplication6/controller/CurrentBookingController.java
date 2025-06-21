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
import javaapplication6.view.CurrentBookingView;
import javaapplication6.view.DashboardView;
import javaapplication6.view.MyBookings;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author suhritsatyal
 */
public class CurrentBookingController {

    private final CurrentBookingView currentBookingView;
    private final LoginModel loginModel;
    private final BookVenueDAO dao = new BookVenueDAO();

    public CurrentBookingController(CurrentBookingView currentBookingView, LoginModel loginModel) {
        this.currentBookingView = currentBookingView;
        this.loginModel = loginModel;
        this.currentBookingView.ViewBook(new BTNAction());
        this.currentBookingView.BackDashboard(new BackDashboard());

    }

    public void open() {
        this.currentBookingView.setVisible(true);

    }

    public void close() {
        this.currentBookingView.dispose();
    }

    public void setTableContent(ArrayList<VenueDetailsFetchModel> venueLists) {
        DefaultTableModel model = (DefaultTableModel) currentBookingView.getjTable1().getModel();
        model.setRowCount(0);

        for (VenueDetailsFetchModel venueList : venueLists) {
            model.addRow(new Object[]{venueList.getVenue_id(), venueList.getVenue_name(), venueList.getVenue_location(), venueList.getTotal_price(), venueList.getVenue_email(), venueList.getVenue_contactnum(), venueList.getPayed()});
        }
    }

    class BTNAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentBookingView.getjTable1().getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(currentBookingView, "Please select a row first to proceed to booking page");
            } else {
                // Convert view row to model row if table is sortable
                int selectedRow = currentBookingView.getjTable1().getSelectedRow();
                int modelRow = currentBookingView.getjTable1().convertRowIndexToModel(selectedRow);
                int venueID = (int) currentBookingView.getjTable1().getModel().getValueAt(modelRow, 0);
                VenueDetailsFetchModel result = dao.getVenue_in_mybookingPage(venueID, loginModel.getEmail());
                MyBookings bookings = new MyBookings();
                MyBookingController ctrl = new MyBookingController(bookings, loginModel,result);
                ctrl.open();
                close();
            }
        }

    }
    
    class BackDashboard implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            DashboardView view=new DashboardView();
            DashboardController ctrl=new DashboardController(view, loginModel);
            ctrl.open();
            close();
        }
        
    }

}
