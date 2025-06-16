/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javaapplication6.HashUtil.HashUtil;
import javaapplication6.database.DBConn;
import javaapplication6.model.LoginModel;
import javaapplication6.model.RegisterModel;
import javaapplication6.model.VenueModel;

/**
 *
 * @author manoj
 */
public class VenueManagerDAO {
    private final DBConn dbConn;
    
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
