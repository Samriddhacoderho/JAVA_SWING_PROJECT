/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.view.MyBookings;

/**
 *
 * @author manoj
 */
public class MyBookingController {
   private final MyBookings bookingView;
   
   public MyBookingController(MyBookings bookingView){
       this.bookingView = bookingView;
       
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
}
