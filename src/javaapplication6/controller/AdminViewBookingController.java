package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaapplication6.dao.RegisterVenueDAO;
import javaapplication6.dao.VenueManagerDAO;
import javaapplication6.model.BookVenueModel;
import javax.swing.JOptionPane;
import javaapplication6.view.BookingDetailsView;
import javaapplication6.model.LoginModel;
import javaapplication6.model.VenueDetailsFetchModel;
import javaapplication6.model.VenueModel;
import javaapplication6.view.AdminDashboardView;

public class AdminViewBookingController {

    private final BookingDetailsView detailView;
    private final LoginModel loginModel;
    private final RegisterVenueDAO bookingDAO = new RegisterVenueDAO();
    private final VenueManagerDAO dao = new VenueManagerDAO();
    private String status;
    private VenueDetailsFetchModel result;

    public AdminViewBookingController(BookingDetailsView detailView, LoginModel loginModel, VenueDetailsFetchModel result) {
        this.detailView = detailView;
        this.loginModel = loginModel;
        this.result = result;
        this.detailView.backListener(new BackProfileListener());
        this.detailView.ApproveBookingListener(new ApproveListener());
        this.detailView.RejectBookingListener(new RejectListener());
        this.detailView.MarkComplete(new MarkComplete());
        this.detailView.PaymentDone(new PaymentDone());

    }

    public void open() {

        this.detailView.setVisible(true);
        this.detailView.getVenueName().setText(result.getVenue_name());
        this.detailView.getVenueLocation().setText(result.getVenue_location());
        this.detailView.getAdminEmail().setText(result.getUser_email()); // This is venue owner's email later replace with user email
        this.detailView.getGuestNumber().setText(Integer.toString(result.getEstimated_guests()));
        this.detailView.getPrice().setText("Rs." + Long.toString(result.getTotal_price()));
        this.status = result.getStatus_detail();
    }

    public void close() {
        this.detailView.dispose();
    }

    class ApproveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (status.equalsIgnoreCase("approved")) {
                JOptionPane.showMessageDialog(detailView, "You have already approved this booking!");
            } else if (result.getStatus().equalsIgnoreCase("Booked")) {
                JOptionPane.showMessageDialog(detailView, "You cannot approve another booking whilst one has been approved!");

            } else {
                int confirm = JOptionPane.showConfirmDialog(
                        detailView,
                        "Are you sure you want to approve this booking request?",
                        "Confirm Approval",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        String venue_name = detailView.getVenueName().getText();
                        String venue_location = detailView.getVenueLocation().getText();
                        String user_email = detailView.getAdminEmail().getText();
                        String estimated_guests = detailView.getGuestNumber().getText();
                        double estimated_price = Long.parseLong(detailView.getPrice().getText().substring(3));
                        BookVenueModel modelBook = new BookVenueModel(user_email, estimated_guests, estimated_price);
                        VenueModel modelVenue = new VenueModel(venue_name, venue_location);

                        boolean success = dao.approveRequest(loginModel, modelBook, modelVenue, result);

                        if (success) {
                            JOptionPane.showMessageDialog(
                                    detailView,
                                    "Booking request has been approved successfully!",
                                    "Booking Approved",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
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
    }

    class RejectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(result.getPayed());
            if (result.getPayed().equalsIgnoreCase("payed")) {
                JOptionPane.showMessageDialog(detailView, "Once the payment is done, you cannot reject the booking!");
            } else {
                int confirm = JOptionPane.showConfirmDialog(
                        detailView,
                        "Are you sure you want to reject this booking request?",
                        "Confirm Rejection",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        String venue_name = detailView.getVenueName().getText();
                        String venue_location = detailView.getVenueLocation().getText();
                        String user_email = detailView.getAdminEmail().getText();
                        String estimated_guests = detailView.getGuestNumber().getText();
                        double estimated_price = Long.parseLong(detailView.getPrice().getText().substring(3));
                        BookVenueModel modelBook = new BookVenueModel(user_email, estimated_guests, estimated_price);
                        VenueModel modelVenue = new VenueModel(venue_name, venue_location);

                        boolean success = dao.rejectRequest(loginModel, modelBook, modelVenue, result);

                        if (success) {
                            JOptionPane.showMessageDialog(
                                    detailView,
                                    "Booking request has been rejected successfully!",
                                    "Booking Approved",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
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
    }

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

    class MarkComplete implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (bookingDAO.getVenue_in_myAdminbookingPage(result.getUser_email(), loginModel.getEmail()).getPayed().equalsIgnoreCase("unpayed")) {
                JOptionPane.showMessageDialog(detailView, "You cannot mark this booking as completed until the payment is done!");
            } else {
                if (JOptionPane.showConfirmDialog(detailView, "Are you sure you want to mark this booking as completed?You cannot revert this action!") == 0) {
                    String user_email = detailView.getAdminEmail().getText();
                    String estimated_guests = detailView.getGuestNumber().getText();
                    double estimated_price = Long.parseLong(detailView.getPrice().getText().substring(3));
                    BookVenueModel modelBook = new BookVenueModel(user_email, estimated_guests, estimated_price);
                    boolean success = bookingDAO.markComplete(loginModel, modelBook);
                    if (!success) {
                        JOptionPane.showMessageDialog(detailView, "There was an error performing this action. Please try again later");
                    } else {
                        JOptionPane.showMessageDialog(detailView, "This booking is successfully marked as completed! You can no longer view it in view bookings page");
                        AdminDashboardView view=new AdminDashboardView();
                        AdminDashboardController ctrl=new AdminDashboardController(view, loginModel);
                        ctrl.open();
                        close();
                    }
                }
            }
        }

    }

    class PaymentDone implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (bookingDAO.getVenue_in_myAdminbookingPage(result.getUser_email(), loginModel.getEmail()).getStatus_detail().equalsIgnoreCase("no")) {
                JOptionPane.showMessageDialog(detailView, "You cannot perform this action before approving the venue!");

            } else if (bookingDAO.getVenue_in_myAdminbookingPage(result.getUser_email(), loginModel.getEmail()).getPayed().equalsIgnoreCase("payed")) {
                JOptionPane.showMessageDialog(detailView, "The status of this venue is already payed!");

            } else {
                if (JOptionPane.showConfirmDialog(detailView, "Are you sure you want to change the payment status to payed?You cannot revert this action!") == 0) {
                    String user_email = detailView.getAdminEmail().getText();
                    String estimated_guests = detailView.getGuestNumber().getText();
                    double estimated_price = Long.parseLong(detailView.getPrice().getText().substring(3));
                    BookVenueModel modelBook = new BookVenueModel(user_email, estimated_guests, estimated_price);
                    boolean success = bookingDAO.payStatus(loginModel, modelBook);
                    if (!success) {
                        JOptionPane.showMessageDialog(detailView, "There was an error performing this action. Please try again later");
                    } else {
                        JOptionPane.showMessageDialog(detailView, "This booking is successfully marked as payed! You can no longer reject the booking now!");
                    }
                }

            }
        }
    }
}
