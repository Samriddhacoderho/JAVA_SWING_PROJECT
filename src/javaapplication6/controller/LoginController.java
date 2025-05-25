package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javaapplication6.model.LoginModel;
import javaapplication6.view.LoginView;
// import javaapplication6.dao.UserDAO; // optional for DB

public class LoginController {

    private final LoginView loginView;
    // private final UserDAO userDAO = new UserDAO(); // if using DB

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        loginView.LoginUserListener(new LoginUser());
        loginView.showPasswordListener(new ShowPassword());
    }

    public void open() {
        loginView.setVisible(true);
    }

    public void close() {
        loginView.dispose();
    }

    class ShowPassword implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (loginView.getShowPasswordBtn().getText().equals("Show")) {
                loginView.getShowPasswordBtn().setText("Hide");
                loginView.getPasswordField().setEchoChar((char) 0);
            } else {
                loginView.getShowPasswordBtn().setText("Show");
                loginView.getPasswordField().setEchoChar('*');
            }
        }
    }

    class LoginUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = loginView.getEmailTxt().getText();
            String password = new String(loginView.getPasswordField().getPassword());

            String validation = validate(email, password);
            if (!validation.equals("Logging in...")) {
                JOptionPane.showMessageDialog(loginView, validation);
            } else {
                try {
                    LoginModel loginModel = new LoginModel(email, password);

                    // Mock logic:
                    if (email.equals("admin@example.com") && password.equals("admin123")) {
                        JOptionPane.showMessageDialog(loginView, "Login Successful");
                    } else {
                        JOptionPane.showMessageDialog(loginView, "Invalid credentials");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(loginView, "Error: " + ex.getMessage());
                }
            }
        }

        public String validate(String email, String password) {
            if (email.isEmpty() || password.isEmpty()) {
                return "Please enter both email and password";
            }
            return "Logging in...";
        }
    }
}
