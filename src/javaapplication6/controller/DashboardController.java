package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javaapplication6.model.LoginModel;
import javaapplication6.view.AhomeView;
import javaapplication6.view.ChangePassView;
import javaapplication6.view.DashboardView;
import javaapplication6.view.EditNameView;
import javaapplication6.view.FAQView;
import javaapplication6.view.LoginView;
import javaapplication6.view.ReportView;
import javax.swing.JOptionPane;
import javaapplication6.dao.BookVenueDAO;
import javaapplication6.model.VenueDetailsFetchModel;
import javaapplication6.view.CurrentBookingView;
import javaapplication6.view.PastBookingView;

/**
 *
 * @author suhritsatyal
 */
public class DashboardController {

    private final DashboardView dashboardView;
    private final LoginModel loginModel;
    private final BookVenueDAO dao = new BookVenueDAO();

    public DashboardController(DashboardView dashboardView, LoginModel loginModel) {
        this.dashboardView = dashboardView;
        dashboardView.EditNameListener(new EditNameListener());
        dashboardView.ChangePasswordListener(new ChangePassListener());
        dashboardView.LogoutListener(new LogoutListener());
        dashboardView.ReportListener(new ReportProblemListener());
        dashboardView.ViewFAQListener(new ViewFAQListener());

        dashboardView.BackButton(new BackButtonListener());
        dashboardView.ViewPastBookingListener(new ViewPast());
        dashboardView.ViewCurrentBookingListener(new ViewCurrent());
        this.loginModel = loginModel;
    }

    public void open() {
        dashboardView.setVisible(true);
    }

    public void close() {
        dashboardView.dispose();
    }

    class EditNameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            EditNameView editNameView = new EditNameView();
            EditNameController editNameController = new EditNameController(editNameView, loginModel);
            editNameController.open();
            close();
        }

    }

    class ChangePassListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ChangePassView changePassView = new ChangePassView();
            ChangePasswordController changePasswordController = new ChangePasswordController(changePassView, loginModel);
            changePasswordController.open();
            close();
        }
    }

    class ViewFAQListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FAQView faqView = new FAQView();
            FAQController faqController = new FAQController(faqView);
            faqController.open();
            close();
        }

    }

    class ReportProblemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ReportView reportView = new ReportView();
            ReportController reportController = new ReportController(reportView, loginModel);
            reportController.open();
            close();
        }

    }

    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (JOptionPane.showConfirmDialog(dashboardView, "Are you sure you want to log out?") == 0) {
                JOptionPane.showMessageDialog(dashboardView, "You have been logged out successfully");
                LoginView loginView = new LoginView();
                LoginController loginController = new LoginController(loginView);
                loginController.open();
                close();
            }
        }
    }

    class BackButtonListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            AhomeView home = new AhomeView();
            AHomeController controller = new AHomeController(home, loginModel);
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

    class ViewPast implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<VenueDetailsFetchModel> result = dao.getVenues_in_mybookingPastPage(loginModel.getEmail());
            if (result==null || result.isEmpty()) {
                JOptionPane.showMessageDialog(dashboardView, "You do not have any past bookings!");
            } else {
                PastBookingView view = new PastBookingView();
                PastBookingController ctrl = new PastBookingController(view, loginModel);
                ctrl.setTableContent(result);
                ctrl.open();
                close();
            }

        }
    }

    class ViewCurrent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<VenueDetailsFetchModel> result = dao.getVenues_in_mybookingCurrentPage(loginModel.getEmail());
            if (result==null || result.isEmpty()) {
                JOptionPane.showMessageDialog(dashboardView, "You do not have any current bookings at the moment!");
            } else {
                CurrentBookingView view = new CurrentBookingView();
                CurrentBookingController ctrl = new CurrentBookingController(view, loginModel);
                ctrl.setTableContent(result);
                ctrl.open();
                close();
            }

        }

    }

}
