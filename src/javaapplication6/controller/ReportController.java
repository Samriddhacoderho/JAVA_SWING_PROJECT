/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.dao.ReportProblemDAO;
import javaapplication6.model.LoginModel;
import javaapplication6.model.ReportProblemModel;
import javaapplication6.view.ReportView;
import javax.swing.JOptionPane;

/**
 *
 * @author suhritsatyal
 */
public class ReportController {
    
    private final ReportView reportView;
        private final LoginModel loginModel;

    public ReportController(ReportView reportView,LoginModel loginModel)
    {
        this.reportView=reportView;
        reportView.ReportListener(new ReportListener());
        this.loginModel=loginModel;
    }
    
    public void open()
    {
        this.reportView.setVisible(true);
        this.reportView.toFront();
this.reportView.requestFocus();
    }
    
    public void close()
    {
        this.reportView.dispose();
    }
    
    class ReportListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String email=loginModel.getEmail();
            String subject=(String) reportView.getReportList().getSelectedItem();
            String description=reportView.getIssuesTxt().getText();
            ReportProblemModel reportprobModel=new ReportProblemModel(email, subject, description);
            ReportProblemDAO reportProblemDAO=new ReportProblemDAO();
            boolean result=reportProblemDAO.addReport(reportprobModel);
            if(result)
            {
                
            }
            else
            {
                System.out.println("There were some error caused!");
            }
        }
        
    }
}


