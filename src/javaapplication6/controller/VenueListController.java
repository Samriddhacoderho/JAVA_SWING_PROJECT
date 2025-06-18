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
import javaapplication6.model.VenueModel;
import javaapplication6.view.BookingPageView;
import javaapplication6.view.VenueListView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author suhritsatyal
 */
public class VenueListController {

    private final VenueListView venuelistView;
    private final String location;
    private BookVenueDAO bookVenueDAO = new BookVenueDAO();
    private final LoginModel loginModel;

    public VenueListController(VenueListView venuelistView, String location, LoginModel loginModel) {
        this.venuelistView = venuelistView;
        this.location = location;
        this.loginModel = loginModel;
        this.venuelistView.jComboAction(new sortActionListner());
        this.venuelistView.BTNAction(new BTNAction());
    }

    public void open() {
        this.venuelistView.setVisible(true);
    }

    public void close() {
        this.venuelistView.dispose();
    }

    public void setTableContent(ArrayList<VenueModel> venuelist) {
        DefaultTableModel model = (DefaultTableModel) venuelistView.getjTable1().getModel();
        model.setRowCount(0);

        int sn = 1;
        for (VenueModel venueModel : venuelist) {
            model.addRow(new Object[]{sn++, venueModel.getName(), venueModel.getLocation(), venueModel.getPrice_per_plate(), venueModel.getStatus()});
        }
    }

    class sortActionListner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!venuelistView.getjComboBox1().getSelectedItem().equals("Sort Price:")) {
                ArrayList<VenueModel> result = bookVenueDAO.sortVenues(location, venuelistView.getjComboBox1().getSelectedItem().toString());
                if (!result.isEmpty()) {
                    setTableContent(result);
                }
            }
        }

    }

    class BTNAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (venuelistView.getjTable1().getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(venuelistView, "Please select a row first to proceed to booking page");
            } else {
                // Convert view row to model row if table is sortable
                int selectedRow = venuelistView.getjTable1().getSelectedRow();
                int modelRow = venuelistView.getjTable1().convertRowIndexToModel(selectedRow);
                String venueName = venuelistView.getjTable1().getModel().getValueAt(modelRow, 1).toString();
                String venueLocation = venuelistView.getjTable1().getModel().getValueAt(modelRow, 2).toString();
                VenueModel model = new VenueModel(venueName, venueLocation);
                VenueModel result = bookVenueDAO.searchParticularVenue(model);
                if (result.getStatus().equalsIgnoreCase("Booked")) {
                    JOptionPane.showMessageDialog(venuelistView, "This venue has already been booked. Please choose a different venue!");
                } else {
                    VenueDetailsFetchModel res1 = bookVenueDAO.getVenue_in_mybookingPage(loginModel.getEmail());
                    if (res1 != null && res1.getVenue_name().equalsIgnoreCase(result.getName())) {
                        JOptionPane.showMessageDialog(venuelistView, "You have already requested booking for this venue. Please choose a different venue!");
                    } else {
                        BookingPageView page = new BookingPageView();
                        BookingPageController pageController = new BookingPageController(page, loginModel, result);
                        pageController.open();
                        close();
                    }
                }
            }
        }

    }
}
