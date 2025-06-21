package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaapplication6.dao.UserDAO;
import javax.swing.JOptionPane;
import javaapplication6.model.LoginModel;
import javaapplication6.view.AhomeView;
import javaapplication6.view.DashboardView;
import javaapplication6.view.EnterEmailView;
import javaapplication6.view.EntryView;
import javaapplication6.view.LoginView;
import javaapplication6.view.UserRegisterView;
 import javaapplication6.dao.UserDAO; // optional for DB
public class LoginController {

    private final LoginView loginView;
    private final UserDAO userDAO=new UserDAO();

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        loginView.LoginActionListener(new LoginUser());
        loginView.ShowActionListener(new ShowPassword());
        loginView.SignupActionListener(new SignupListener());
        loginView.ForgetActionListener(new ForgetActionListener());
        loginView.EntryViewListener(new EntryViewListener());
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
            String password = loginView.getPassPsf().getText();
            
            //validation part
            String validation = validate(email, password);
            
            
            if (!validation.equals("Logging in...")) {
                JOptionPane.showMessageDialog(loginView, validation);
            } else {
                
                try {
                    LoginModel loginModel=new LoginModel(email,password);
                    boolean result=userDAO.loginUser(loginModel);
                    if(result)
                    {
                        
                        
                        AhomeView ahomeView=new AhomeView();
                        AHomeController ahomeController=new AHomeController(ahomeView,loginModel);
                        ahomeController.open();
                        close();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(loginView, "Please enter correct credentials!");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(loginView, "Error: " + ex.getMessage());
                }
                
            }
        }  
        
         public String validate(String email, String password) {
            if (email.isEmpty() || password.isEmpty()) {
                return "Please enter both email and passwords";
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
             UserRegisterView registrationView=new UserRegisterView();
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
    class EntryViewListener implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            EntryView view=new EntryView();
            EntryController controller=new EntryController(view);
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
    class ForgetActionListener implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            EnterEmailView enterEmailView=new EnterEmailView();
            EnterEmailController enterEmailController=new EnterEmailController(enterEmailView);
            enterEmailController.open();
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