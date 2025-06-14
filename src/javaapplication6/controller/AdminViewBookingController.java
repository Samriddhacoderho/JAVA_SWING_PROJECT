package javaapplication6.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaapplication6.dao.RegisterVenueDAO;
import javax.swing.JOptionPane;
import javaapplication6.view.BookingDetailsView;
import javaapplication6.model.LoginModel;
import javaapplication6.model.VenueDetailsFetchModel;
import javaapplication6.view.AdminDashboardView;

public class AdminViewBookingController {

    private final BookingDetailsView detailView;
    private final LoginModel loginModel;
    private final RegisterVenueDAO bookingDAO = new RegisterVenueDAO();

    public AdminViewBookingController(BookingDetailsView detailView, LoginModel loginModel) {
        this.detailView = detailView;
        this.loginModel = loginModel;
        this.detailView.backListener(new BackProfileListener());
//        this.detailView.ApproveBookingListener(new ApproveListener());
//        this.detailView.RejectBookingListener(new RejectListener());

    }

    public void open() {
        VenueDetailsFetchModel result = bookingDAO.adminVenueView(loginModel.getEmail()); // This should join all three tables
        if (result == null) {
            JOptionPane.showMessageDialog(detailView, "Maybe this booking doesn't exist or has been removed!");
            AdminDashboardView dashView = new AdminDashboardView();
            AdminDashboardController dashController = new AdminDashboardController(dashView, loginModel);
            dashController.open();
            close();
        } else {
            this.detailView.setVisible(true);
            this.detailView.getVenueName().setText(result.getVenue_name());
            this.detailView.getVenueLocation().setText(result.getVenue_location());
            this.detailView.getAdminEmail().setText(result.getUser_email()); // This is venue owner's email later replace with user email
            this.detailView.getGuestNumber().setText(Integer.toString(result.getEstimated_guests()));
            this.detailView.getPrice().setText("Rs." + Long.toString(result.getTotal_price()));
        }
    }

    public void close() {
        this.detailView.dispose();
    }

//   class ApproveListener implements ActionListener {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        int confirm = JOptionPane.showConfirmDialog(
//            detailView,
//            "Are you sure you want to approve this booking request?",
//            "Confirm Approval",
//            JOptionPane.YES_NO_OPTION
//        );
//        
//        if (confirm == JOptionPane.YES_OPTION) {
//            try {
//                // Update venue status to 'Booked' in venue_table
//                boolean success = bookingDAO.updateVenueStatus(currentBooking.getVenueId(), "Booked");
//                
//                if (success) {
//                    JOptionPane.showMessageDialog(
//                        detailView,
//                        "Booking request has been approved successfully!",
//                        "Booking Approved",
//                        JOptionPane.INFORMATION_MESSAGE
//                    );
//                    navigateToAdminDashboard();
//                } else {
//                    JOptionPane.showMessageDialog(
//                        detailView,
//                        "Failed to approve booking. Please try again.",
//                        "Error",
//                        JOptionPane.ERROR_MESSAGE
//                    );
//                }
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(
//                    detailView,
//                    "Error approving booking: " + ex.getMessage(),
//                    "Error",
//                    JOptionPane.ERROR_MESSAGE
//                );
//            }
//        }
//    }
//}
//
//class RejectListener implements ActionListener {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        int confirm = JOptionPane.showConfirmDialog(
//            detailView,
//            "Are you sure you want to reject this booking request?",
//            "Confirm Rejection",
//            JOptionPane.YES_NO_OPTION
//        );
//        
//        if (confirm == JOptionPane.YES_OPTION) {
//            try {
//                // Delete the booking record and reset venue status to 'Unbooked'
//                boolean success = bookingDAO.deleteBookingAndResetVenue(bookingId, currentBooking.getVenueId());
//                
//                if (success) {
//                    JOptionPane.showMessageDialog(
//                        detailView,
//                        "Booking request has been rejected.",
//                        "Booking Rejected",
//                        JOptionPane.INFORMATION_MESSAGE
//                    );
//                    navigateToAdminDashboard();
//                } else {
//                    JOptionPane.showMessageDialog(
//                        detailView,
//                        "Failed to reject booking. Please try again.",
//                        "Error",
//                        JOptionPane.ERROR_MESSAGE
//                    );
//                }
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(
//                    detailView,
//                    "Error rejecting booking: " + ex.getMessage(),
//                    "Error",
//                    JOptionPane.ERROR_MESSAGE
//                );
//            }
//        }
//    }
//}
    class BackProfileListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            navigateToAdminDashboard();
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

    private void navigateToAdminDashboard() {
        AdminDashboardView dashView = new AdminDashboardView();
        AdminDashboardController dashController = new AdminDashboardController(dashView, loginModel);
        dashController.open();
        close();
    }
}
