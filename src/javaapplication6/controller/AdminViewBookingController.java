package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javaapplication6.dao.VenueManagerDAO;
//import javaapplication6.dao.BookingDAO; // Placeholder DAO
import javaapplication6.view.BookingDetailsView;
import javaapplication6.model.VenueModel;
import javaapplication6.model.BookingModel; // Assuming you have this
import javaapplication6.model.LoginModel;
import javaapplication6.view.AdminDashboardView;

public class AdminViewBookingController {
    private final BookingDetailsView detailView;
    private final VenueManagerDAO venueDAO = new VenueManagerDAO();
//    private final BookingDAO bookingDAO = new BookingDAO(); // Placeholder
    private BookingModel currentBooking;
    private int bookingId;
    
    public AdminViewBookingController(BookingDetailsView detailView, int bookingId){
        this.detailView = detailView;
        this.bookingId = bookingId;
        this.detailView.backListener(new BackProfileListener());
        this.detailView.ApproveBookingListener(new ApproveListener());
        this.detailView.RejectBookingListener(new RejectListener());
        
        
    }
    
    public void open() {
    BookingModel result = bookingDAO.getBookingById(bookingId); // Placeholder for  DAO method
    if (result == null) {
        JOptionPane.showMessageDialog(detailView, "Maybe you have no bookings at the moment!");
        AdminDashboardView dashView = new AdminDashboardView();
        AdminDashboardController dashController = new AdminDashboardController(dashView);
        dashController.open();
        close();
    } else {
        this.detailView.setVisible(true);
        this.detailView.getVenueName().setText(result.getVenueName());
        this.detailView.getVenueLocation().setText(result.getVenueLocation());
        this.detailView.getAdminEmail().setText(result.getUserEmail());
        this.detailView.getAdminPhone().setText(result.getUserPhone());
        this.detailView.getPrice().setText("Rs." + Long.toString((long)result.getTotalPrice()));
    }
}

    
    public void close(){
        this.detailView.dispose();
    }
    
    class ApproveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(
                detailView,
                "Are you sure you want to approve this booking request?",
                "Confirm Approval",
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    // Update booking status to approved (placeholder DAO call)
                    boolean success = bookingDAO.updateBookingStatus(bookingId, "booked");
                    
                    if (success) {
                        
                        JOptionPane.showMessageDialog(
                            detailView,
                            "Booking request has been approved successfully!",
                            "Booking Approved",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                        
                        // Navigate back to admin dashboard
                        navigateToAdminDashboard();
                    } else {
                        JOptionPane.showMessageDialog(
                            detailView,
                            "Failed to approve booking. Please try again.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                        detailView,
                        "Error approving booking: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }
    
    class RejectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Show dialog to get rejection reason
            String reason = JOptionPane.showInputDialog(
                detailView,
                "Please provide a reason for rejection (optional):",
                "Rejection Reason",
                JOptionPane.QUESTION_MESSAGE
            );
            if (reason == null) {
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(
                detailView,
                "Are you sure you want to reject this booking request?",
                "Confirm Rejection",
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    // Update booking status to rejected (placeholder DAO call)
                    boolean success = bookingDAO.updateBookingStatus(bookingId, "REJECTED");
                    
                    if (success) {
                        // Save rejection reason if provided (placeholder)
                        if (reason != null && !reason.trim().isEmpty()) {
                            bookingDAO.saveRejectionReason(bookingId, reason);
                        }
                        
                        // Optional: Send rejection email to user (placeholder)
                        // emailService.sendRejectionEmail(currentBooking.getUserEmail(), currentBooking, reason);
                        
                        JOptionPane.showMessageDialog(
                            detailView,
                            "Booking request has been rejected.",
                            "Booking Rejected",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                        
                        // Navigate back to admin dashboard
                        navigateToAdminDashboard();
                    } else {
                        JOptionPane.showMessageDialog(
                            detailView,
                            "Failed to reject booking. Please try again.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                        detailView,
                        "Error rejecting booking: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }
    
    class BackProfileListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            navigateToAdminDashboard();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
    
    private void navigateToAdminDashboard() {
//        AdminDashboardView dashView = new AdminDashboardView();
//        AdminDashboardController dashController = new AdminDashboardController(dashView);
//        dashController.open();
//        close();
    }
}
