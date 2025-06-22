package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javaapplication6.dao.RegisterVenueDAO;
import javaapplication6.model.LoginModel;
import javaapplication6.model.VenueModel;
import javaapplication6.view.AdminDashboardView;
import javaapplication6.view.DashboardView;
import javaapplication6.view.UpdateVenueDetailsView;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class UpdateVenueDetailsController {
    
    private UpdateVenueDetailsView view;
    private LoginModel model;
    private RegisterVenueDAO dao;
    private VenueModel venueModel;

    public UpdateVenueDetailsController(UpdateVenueDetailsView view,LoginModel model,VenueModel venueModel) {
        this.view = view;
        this.view.addUploadImageListener(new addUploadImageListener()); 
        this.model=model;
        this.dao = new RegisterVenueDAO();
        this.venueModel=venueModel;
        this.view.UpdateListener(new UpdateListener());
        this.view.getNameTxt().setText(venueModel.getName());
        this.view.getLocationTxt().setText(venueModel.getLocation());
        this.view.getEmailTxt().setText(venueModel.getEmail());
        this.view.getNumberTxt().setText(venueModel.getContact_number());
        this.view.getPriceTxt().setText(String.valueOf(venueModel.getPrice_per_plate())); 
        this.view.BackListener(new Back());
    }

    public void open()
    {
        this.view.setVisible(true);
    }
    
    public void close()
    {
        this.view.dispose();
    }
    
    class addUploadImageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (file.exists() && file.isFile()) {
                    view.setSelectedFile(file);
                } else {
                    JOptionPane.showMessageDialog(view,
                            "Invalid file selected.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    class UpdateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameTxt().getText();
            String location = view.getLocationTxt().getText();
            String email = view.getEmailTxt().getText();
            String number = view.getNumberTxt().getText();
            String priceStr = view.getPriceTxt().getText();

            // Input validation
            if (name.isEmpty() || location.isEmpty() || email.isEmpty() || number.isEmpty() || priceStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "All fields must be filled.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            float price;
            try {
                price = Float.parseFloat(priceStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid price format.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            venueModel.setName(name);
            venueModel.setLocation(location);
            venueModel.setEmail(email);
            venueModel.setContact_number(number);
            venueModel.setPrice_per_plate(price);

            File selectedFile = view.getSelectedFile();
            if (selectedFile != null) {
                try {
                    byte[] imageBytes = java.nio.file.Files.readAllBytes(selectedFile.toPath());
                    venueModel.setImage(imageBytes);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Failed to read image file.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // DAO Update
            RegisterVenueDAO dao = new RegisterVenueDAO();
            boolean success = dao.updateVenue(venueModel);

            if (success) {
                JOptionPane.showMessageDialog(view, "Venue updated successfully.");
                AdminDashboardView admindashboardView=new AdminDashboardView();
                AdminDashboardController admindashboardController=new AdminDashboardController(admindashboardView, model);
                admindashboardController.open();
                close();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to update venue.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class Back implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            AdminDashboardView view=new AdminDashboardView();
            AdminDashboardController ctrl=new AdminDashboardController(view, model);
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