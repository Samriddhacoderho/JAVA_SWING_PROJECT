/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaapplication6.model.InquiryModel;
import javaapplication6.view.BookingPageView;
import javaapplication6.view.InquiryView;
import javaapplication6.model.LoginModel;
import javaapplication6.model.VenueModel;
import javaapplication6.dao.UserDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author manoj
 * 
 */
public class InquiryController {
    
    private final InquiryView view;
    private final LoginModel loginModel;
    private final VenueModel venueModel;
    private final UserDAO dao =new UserDAO();
  
    public InquiryController(InquiryView view,LoginModel loginModel,VenueModel venueModel){
        this.view = view;
        this.loginModel=loginModel;
        this.venueModel = venueModel;
        this.view.BackListener(new BackListener());
        this.view.SendListener(new SendListener());
        
    }
    public void open(){
        this.view.setVisible(true);
    }
    public void close(){
        this.view.dispose();
    }
    
    class BackListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            
            BookingPageView view = new BookingPageView();
            BookingPageController controller = new BookingPageController(view,loginModel,venueModel);
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
        }
        
    }
    class SendListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameTxt().getText();
            String email = view.getEmailTxt().getText();
            String message = view.getMessageTxt().getText();
            String adminEmail = venueModel.getEmail();
            InquiryModel model = new InquiryModel(name,email,message,adminEmail);
            boolean result = dao.sendInquiry(model);
            if(!result){
                 JOptionPane.showMessageDialog(view, "There was an error sending the inquiry message");
            }
            else{
                JOptionPane.showMessageDialog(view, "Your inquiry was successfully sent to the admin of this venue.");
                BookingPageView view = new BookingPageView();
                BookingPageController controller = new BookingPageController(view,loginModel,venueModel);
                controller.open();
                close();
            }
            
        }
        
    }
}
