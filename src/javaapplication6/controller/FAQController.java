/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaapplication6.model.LoginModel;
import javaapplication6.view.DashboardView;
import javaapplication6.view.FAQView;

/**
 *
 * @author ishan-college
 */
public class FAQController {
    private final FAQView faqView;
    private final LoginModel loginModel;

    public FAQController(FAQView faqView,LoginModel loginModel) {
        this.faqView = faqView;
        loadFAQContent();
        this.loginModel=loginModel;
        this.faqView.BackListener(new Back());
    }
    
    public void open()
    {
        this.faqView.setVisible(true);
    }
    public void close()
    {
        this.faqView.dispose();
    }

    private void loadFAQContent() {
        String faqText = """
            1) Where are we?
            Kathmandu, Dillibazar.

            2) What is the capacity of the venue?
            Max up to 20,000.

            3) What is the price of the venue?
            According to the guest.

            4) What other services do you have?
            We have cameraman service and more.
        """;

        faqView.setFAQText(faqText);
    }
    
    class Back implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            DashboardView view=new DashboardView();
            DashboardController ctrl=new DashboardController(view, loginModel);
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