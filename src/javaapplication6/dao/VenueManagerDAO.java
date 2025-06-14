/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javaapplication6.HashUtil.HashUtil;
import javaapplication6.database.DBConn;
import javaapplication6.model.AdminRegisterModel;

/**
 *
 * @author manoj
 */
public class VenueManagerDAO {
    private final DBConn dbConn;
    
    public VenueManagerDAO() {
        dbConn = new DBConn();
    }
    
    public boolean registerVM(AdminRegisterModel model)
            
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
}
