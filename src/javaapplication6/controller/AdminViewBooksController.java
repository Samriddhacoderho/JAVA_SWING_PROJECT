/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javaapplication6.dao.RegisterVenueDAO;
import javaapplication6.model.LoginModel;
import javaapplication6.model.VenueDetailsFetchModel;
import javaapplication6.view.AdminDashboardView;
import javaapplication6.view.AdminViewBooks;
import javaapplication6.view.BookingDetailsView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author suhritsatyal
 */
public class AdminViewBooksController {

    private final AdminViewBooks view;
    private final LoginModel loginModel;
    private final RegisterVenueDAO dao = new RegisterVenueDAO();

    public AdminViewBooksController(AdminViewBooks view,LoginModel loginModel) {
        this.view = view;
        this.loginModel = loginModel;
        this.view.ViewBook(new BTNAction());
        this.view.BackDashboard(new Back());

    }

    public void open() {
        this.view.setVisible(true);

    }

    public void close() {
        this.view.dispose();
    }

    public void setTableContent(ArrayList<VenueDetailsFetchModel> venueLists) {
        DefaultTableModel model = (DefaultTableModel) view.getjTable1().getModel();
        model.setRowCount(0);
        
        int sn=1;
        for (VenueDetailsFetchModel venueList : venueLists) {
            model.addRow(new Object[]{sn++, venueList.getUser_email(), venueList.getTotal_price(),  venueList.getPayed(),venueList.getCompleted()});
        }
    }

    class BTNAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getjTable1().getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(view, "Please select a row first to proceed to view booking page");
            } else {
                int selectedRow = view.getjTable1().getSelectedRow();
                int modelRow = view.getjTable1().convertRowIndexToModel(selectedRow);
                String user_email =  view.getjTable1().getModel().getValueAt(modelRow, 1).toString();
                VenueDetailsFetchModel result = dao.getVenue_in_myAdminbookingPage(user_email, loginModel.getEmail());
                BookingDetailsView bookings = new BookingDetailsView();
                AdminViewBookingController ctrl = new AdminViewBookingController(bookings, loginModel,result);
                ctrl.open();
                close();
            }
        }

    }
    
    class Back implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            AdminDashboardView view=new AdminDashboardView();
            AdminDashboardController ctrl=new AdminDashboardController(view, loginModel);
            ctrl.open();
            close();
        }
        
    }

}
