/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javaapplication6.database.DBConn;
import javaapplication6.model.ReportProblemModel;


/**
 *
 * @author Dell
 */
public class ReportProblemDAO {
    private final DBConn dbConn;
    
    public ReportProblemDAO() {
        dbConn = new DBConn();
    }
    public boolean addReport(ReportProblemModel report){
         
        String query = "insert into problems(email,subject,description) values(?,?,?,?)";
        try (Connection conn = dbConn.connection_base();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1,report.getEmail() );
            stmt.setString(2, report.getSubject() );
            stmt.setString(3, report.getDescription());
            return stmt.executeUpdate() > 0;
            
        } catch (Exception e) {
            System.out.println("Error in report problem: " + e.getMessage());
            
        }
        return false;
        
        
    }
}
