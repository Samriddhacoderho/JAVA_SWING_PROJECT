/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javaapplication6.HashUtil.HashUtil;
import javaapplication6.controller.Mail.SMTPSMailSender;
import javaapplication6.database.DBConn;
import javaapplication6.model.BookVenueModel;
import javaapplication6.model.LoginModel;
import javaapplication6.model.RegisterModel;
import javaapplication6.model.VenueModel;

/**
 *
 * @author manoj
 */
public class VenueManagerDAO {
    private final DBConn dbConn;
    private final SMTPSMailSender smtpsMailSender=new SMTPSMailSender();
    
    public VenueManagerDAO() {
        dbConn = new DBConn();
    }
    
    public boolean registerVM(RegisterModel model)
            
    {
        boolean result = false;
        String sql = "INSERT INTO admin (name, email, password) VALUES (?, ?, ?)";

        try (Connection conn = dbConn.connection_base();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, model.getName());
            stmt.setString(2, model.getEmail());
            String hashedPassword = HashUtil.hashPassword(model.getPassword());
            stmt.setString(3, hashedPassword);

            int rowsInserted = stmt.executeUpdate();
            result = rowsInserted > 0;
        } catch (Exception e) {
            System.out.println("Error in registerUser: " + e.getMessage());
        }
        return true;
        
    }
    public boolean approveRequest(LoginModel model,VenueModel modelVenue,BookVenueModel modelBook){
        boolean result = false;
        String sql = "INSERT INTO book_details(venue_id,user_email,estimated_guests,total_price) values(?,?,?,?)";
        try (Connection conn = dbConn.connection_base();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, modelVenue.getId());
            pstmt.setString(2, modelBook.getEmail());
            pstmt.setString(3, modelBook.getEstimated_guests());
            pstmt.setDouble(4, modelBook.getEstimated_price());
            int rowsInserted = pstmt.executeUpdate();
            if(pstmt.executeUpdate()>0)
            {
                String body="Hello, your booking was successfullly made.\nVenue Name:"+modelVenue.getName()+"\nVenue Location:"+modelVenue.getLocation();
                boolean mailSent=smtpsMailSender.sendMail(modelBook.getEmail(), "Booking Confirmation", body);
                if(mailSent)
                {
                    String sqlQueryUpdate="UPDATE venue_table SET status='Booked' where id=?";
                    PreparedStatement pstmtUpdate=conn.prepareStatement(sqlQueryUpdate);
                    pstmtUpdate.setInt(1, modelVenue.getId());
                    if(pstmtUpdate.executeUpdate()>0)
                    {
                                        return true;
                    }
                }
                else
                {
                    return false;
                }
                }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    public boolean rejectRequest(LoginModel model,VenueModel modelVenue,BookVenueModel modelBook){
        boolean result = false;
        try (Connection conn=dbConn.connection_base()){
              String sqlQueryUpdate="UPDATE venue_table SET status='Unbooked' where id=?";
                    PreparedStatement pstmtUpdate=conn.prepareStatement(sqlQueryUpdate);
                    pstmtUpdate.setInt(1, modelVenue.getId());
            if(pstmtUpdate.executeUpdate()>0)
            {
                String body="Hello, your booking request has been rejected. Please try another venue.\nVenue Name:"+modelVenue.getName()+"\nVenue Location:"+modelVenue.getLocation();
                boolean mailSent=smtpsMailSender.sendMail(modelBook.getEmail(), "Booking Rejection", body);
                if(mailSent)
                {
                    
                    if(pstmtUpdate.executeUpdate()>0)
                    {
                                        return true;
                    }
                }
                else
                {
                    return false;
                }
                }
        } catch (Exception e) {
            return false;
        }
        return false;
    
   }
     
    
    

    

    public boolean registerVenue(VenueModel model){
        boolean result= false;
        String sql = "insert into venue_table (name, location, email, contact_number,price_per_plate,status)values(?,?,?,?,?,?)";
        try(Connection conn = dbConn.connection_base();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,model.getName());
            stmt.setString(2, model.getLocation());
            stmt.setString(3, model.getEmail());
            stmt.setString(4,model.getContact_number());
            stmt.setDouble(5,model.getPrice_per_plate());
            stmt.setString(6, model.getStatus());
            int rowsInserted = stmt.executeUpdate();
            result = rowsInserted>0;
            
        } catch(Exception e){
            System.out.println("Error in registering Venue:"+e.getMessage());
        }
        
        
        return true;
    }
    
    public boolean loginManager(LoginModel loginmodel) {
    String sql_query = "SELECT password FROM admin WHERE email = ?";
    
    try (Connection conn = dbConn.connection_base();
         PreparedStatement pstmt = conn.prepareStatement(sql_query)) {

        pstmt.setString(1, loginmodel.getEmail());
        var rs = pstmt.executeQuery();

        if (rs.next()) {
            String storedHashedPassword = rs.getString("password");
            String inputHashedPassword = HashUtil.hashPassword(loginmodel.getPassword());

            return storedHashedPassword.equals(inputHashedPassword);
        }
    } catch (Exception e) {
        System.out.println("Error in loginManager: " + e.getMessage());
    }
    
    return false;
}

}
