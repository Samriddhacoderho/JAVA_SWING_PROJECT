/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaapplication6.model.LoginModel;
import javaapplication6.view.DashboardView;
import javaapplication6.view.MyBookings;

/**
 *
 * @author manoj
 */
public class MyBookingController {
   private final MyBookings bookingView;
   private final LoginModel loginModel;
   
   
   public MyBookingController(MyBookings bookingView,LoginModel loginModel){
       this.bookingView = bookingView;
       this.loginModel=loginModel;
      bookingView.ProfileListener(new BacktoProfile());
     bookingView.ViewBookingListener(new CancelBooking());
       
   }
    
    public void open(){
        this.bookingView.setVisible(true);
    }
    public void close(){
        this.bookingView.dispose();
    }
    class CancelBooking implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
        
    }
    class BacktoProfile implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            DashboardView dash = new DashboardView();
            DashboardController dashController = new DashboardController(dash,loginModel);
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
