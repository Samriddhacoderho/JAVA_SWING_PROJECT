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
   private final BookVenueDAO dao=new BookVenueDAO();

   
   public MyBookingController(MyBookings bookingView,LoginModel loginModel){
       this.bookingView = bookingView;
       this.bookingView.cancelBookingListener(new CancelBooking());
       this.bookingView.backListener(new BackProfile());
       this.loginModel=loginModel;
       
   }
    
    public void open(){
        VenueDetailsFetchModel result=dao.getVenue_in_mybookingPage(loginModel.getEmail());
        if(result==null)
        {
            JOptionPane.showMessageDialog(bookingView, "Maybe you have no bookings at the moment!");
            DashboardView view=new DashboardView();
            DashboardController ctrl=new DashboardController(view, loginModel);
            ctrl.open();
            close();
        }
        else
        {
            this.bookingView.setVisible(true);  
            this.bookingView.getVenueName().setText(result.getVenue_name());
            this.bookingView.getVenueLocation().setText(result.getVenue_location());
            this.bookingView.getAdminEmail().setText(result.getVenue_email());
            this.bookingView.getAdminPhone().setText(result.getVenue_contactnum());
            this.bookingView.getGuestNumber().setText(Integer.toString(result.getEstimated_guests()));
            this.bookingView.getPrice().setText("Rs."+Long.toString(result.getTotal_price()));
        }
    }
    
    public void close(){
        this.bookingView.dispose();
    }
    
    class CancelBooking implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
        
    }
    
    class BackProfile implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            DashboardView view=new DashboardView();
            DashboardController ctrl=new DashboardController(view, loginModel);
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
}
