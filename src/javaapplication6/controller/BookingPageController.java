/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.dao.BookVenueDAO;
import javaapplication6.model.BookVenueModel;
import javaapplication6.model.LoginModel;
import javaapplication6.model.VenueModel;
import javaapplication6.view.BookingPageView;
import javax.swing.JOptionPane;

/**
 *
 * @author suhritsatyal
 */
public class BookingPageController {
    private final BookingPageView page;
    private final LoginModel loginModel;
    private VenueModel venueModel;
    private BookVenueDAO bookVenueDAO=new BookVenueDAO();
    
    public BookingPageController(BookingPageView page,LoginModel loginModel,VenueModel venueModel)
    {
        this.page=page;
        this.loginModel=loginModel;
        this.venueModel=venueModel;
        this.page.getjLabel2().setText(venueModel.getName());
        this.page.getjTextField2().setText(venueModel.getEmail());
        this.page.getjTextField1().setText(venueModel.getContact_number());
        this.page.ProceedAction(new ProceedAction());
    }
    
    public void open()
    {
        this.page.setVisible(true);
    }
    public void close()
    {
        this.page.dispose();
    }
    
    class ProceedAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String estimated_guests=page.getjTextField3().getText();
            
            //validation
            if(estimated_guests.isEmpty())
            {
                JOptionPane.showMessageDialog(page, "Estimated number of guest cannot be empty!");
            }
            else if(!estimated_guests.matches("\\d+"))
            {
                JOptionPane.showMessageDialog(page, "Please enter only numeric digits for estimated guests!");
            }
            else if(Long.parseLong(estimated_guests)<=200 || Long.parseLong(estimated_guests)>=2000)
            {
                JOptionPane.showMessageDialog(page, "Please enter guests between 200 and 2000 or directly contact the venue manager!");
            }
            else
            {
                String confirmString="Total Price is:"+(Long.parseLong(estimated_guests)*venueModel.getPrice_per_plate())+"\nAre you sure you want to book this venue?";
                if(JOptionPane.showConfirmDialog(page, confirmString)==0)
                {
                 double estimated_price=Long.parseLong(estimated_guests)*venueModel.getPrice_per_plate();
                    BookVenueModel model=new BookVenueModel(loginModel.getEmail(), estimated_guests, estimated_price);
//                    boolean result=bookVenueDAO.bookVenue(model);
//                    if(!result)
//                    {
//                        JOptionPane.showMessageDialog(page, "There was an error booking the venue");
//                    }
//                    else
//                    {
//                        JOptionPane.showMessageDialog(page, "Your booking was successfully made");
//                    }
                        //todo
                }
            }
        }
        
    }
    
    
    
}
