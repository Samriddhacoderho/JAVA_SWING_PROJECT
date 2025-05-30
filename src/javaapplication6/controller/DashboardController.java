package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.model.LoginModel;
import javaapplication6.view.ChangePassView;
import javaapplication6.view.DashboardView;
import javaapplication6.view.EditNameView;
import javaapplication6.view.FAQView;
import javaapplication6.view.LoginView;
import javaapplication6.view.ReportView;
import javax.swing.JOptionPane;

/**
 *
 * @author suhritsatyal
 */
public class DashboardController {
    private final DashboardView dashboardView;
    private final LoginModel loginModel;
    
    public DashboardController(DashboardView dashboardView,LoginModel loginModel)
    {
        this.dashboardView=dashboardView;
        dashboardView.EditNameListener(new EditNameListener());
        dashboardView.ChangePasswordListener(new ChangePassListener());
        dashboardView.LogoutListener(new LogoutListener());
        dashboardView.ReportListener(new ReportProblemListener());
        dashboardView.ViewFAQListener(new ViewFAQListener());
        this.loginModel=loginModel;
    }
    
     public void open() {
        dashboardView.setVisible(true);
    }

    public void close() {
        dashboardView.dispose();
    }
    
    class EditNameListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            EditNameView editNameView=new EditNameView();
            EditNameController editNameController=new EditNameController(editNameView,loginModel);
            editNameController.open();
            close();
        }
        
    }
    
    class ChangePassListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            ChangePassView changePassView=new ChangePassView();
            ChangePasswordController changePasswordController=new ChangePasswordController(changePassView,loginModel);
            changePasswordController.open();
            close();
        }
    }
    
    class ViewFAQListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            FAQView faqView=new FAQView();
            faqView.setVisible(true);
        }
        
    }
    
    class ReportProblemListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            ReportView reportView=new ReportView();
            ReportController reportController=new ReportController(reportView,loginModel);
            reportController.open();
        }
        
    }
    
    class LogoutListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(JOptionPane.showConfirmDialog(dashboardView, "Are you sure you want to log out?")==0)
            {
            JOptionPane.showMessageDialog(dashboardView, "You have been logged out successfully");
            LoginView loginView=new LoginView();
            LoginController loginController=new LoginController(loginView);
            loginController.open();
            close();
            }
            }
    }
}
