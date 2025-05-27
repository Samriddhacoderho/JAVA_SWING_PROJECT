package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaapplication6.dao.UserDAO;
import javax.swing.JOptionPane;
import javaapplication6.model.LoginModel;
import javaapplication6.view.HomeView;
import javaapplication6.view.LoginView;
import javaapplication6.view.RegistrationView;
// import javaapplication6.dao.UserDAO; // optional for DB
public class LoginController {

    private final LoginView loginView;
    private final UserDAO userDAO=new UserDAO();
    private Object view;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        loginView.LoginActionListener(new LoginUser());
        loginView.ShowActionListener(new ShowPassword());
        loginView.SignupActionListener(new SignupListener());
    }

    public void open() {
        loginView.setVisible(true);
    }

    public void close() {
        loginView.dispose();
    }

    private void addMouseListener(MouseListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    class LoginUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = loginView.getEmailTxt().getText();
            String password = new String(loginView.getPassPsf().getText());
            
            //validation part
            String validation = validate(email, password);
            
            
            if (!validation.equals("Logging in...")) {
                JOptionPane.showMessageDialog(loginView, validation);
            } else {
                try {
                    LoginModel loginModel=new LoginModel(email,password);
                    boolean result=userDAO.loginUser(loginModel);

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
    
    class ShowPassword implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginView.getShowBtn().getText()=="Show")
                {
                    loginView.getShowBtn().setText("Hide");
                    loginView.getPassPsf().setEchoChar((char) 0);
                }
                else
                {
                    loginView.getShowBtn().setText("Show");
                    loginView.getPassPsf().setEchoChar('*' );
                }
            }   
    }
    
    class SignupListener implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
             RegistrationView registrationView=new RegistrationView();
            RegistrationController registrationController=new RegistrationController(registrationView);
            registrationController.open();
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