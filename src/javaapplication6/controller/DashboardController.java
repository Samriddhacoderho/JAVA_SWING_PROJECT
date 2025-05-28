package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.model.LoginModel;
import javaapplication6.view.ChangePassView;
import javaapplication6.view.DashboardView;
import javaapplication6.view.EditNameView;
import javaapplication6.view.LoginView;
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
            ChangePasswordController changePasswordController=new ChangePasswordController(changePassView);
            changePasswordController.open();
            close();
        }
    }
    
    class LogoutListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showConfirmDialog(dashboardView, "Are you sure you want to log out?");
            JOptionPane.showMessageDialog(dashboardView, "You have been logged out successfully");
            LoginView loginView=new LoginView();
            LoginController loginController=new LoginController(loginView);
            loginController.open();
            close();
        }
    }
}
