/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javaapplication6.model.InquiryModel;
import javaapplication6.model.LoginModel;
import javaapplication6.model.VenueDetailsFetchModel;
import javaapplication6.view.AdminDashboardView;
import javaapplication6.view.AdminInquiryView;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class AdminInquiryController {
    private final AdminInquiryView view;
    private final LoginModel loginModel;
    
    public AdminInquiryController(AdminInquiryView view,LoginModel loginModel){
        this.view = view;
        this.loginModel = loginModel;
        this.view.BackListener(new BackListener());
    }
    public void open(){
        this.view.setVisible(true);
    }
    public void close(){
        this.view.dispose();
    }
    public void setTableContent(ArrayList<InquiryModel> inquiries) {
        DefaultTableModel model = (DefaultTableModel) view.getjTable1().getModel();
        model.setRowCount(0);
        
        
        for (InquiryModel inquiry : inquiries) {
            model.addRow(new Object[]{ inquiry.getEmail(),inquiry.getMessage()});
        }
    }
    class BackListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            AdminDashboardView view = new AdminDashboardView();
            AdminDashboardController controller = new AdminDashboardController(view,loginModel);
            controller.open();
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
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        
    }
}
