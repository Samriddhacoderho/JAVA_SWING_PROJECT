/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javaapplication6.database.DBConn;
import javaapplication6.model.VenueModel;

/**
 *
 * @author ishan-college
 */
public class RegisterVenueDAO {
    private final DBConn dbConn;
    
    public RegisterVenueDAO() {
        dbConn = new DBConn();
    }
    
        public boolean updateVenueDetails(VenueModel venue) {
        String sqlQuery = "UPDATE venue_table SET name = ?, location = ?, email = ?, contact_number = ?, price_per_plate = ?, status = ? WHERE id = ?";
        try (Connection conn = dbConn.connection_base()) {
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, venue.getName());
            pstmt.setString(2, venue.getLocation());
            pstmt.setString(3, venue.getEmail());
            pstmt.setString(4, venue.getContact_number());
            pstmt.setDouble(5, venue.getPrice_per_plate());
            pstmt.setString(6, venue.getStatus());
            pstmt.setInt(7, venue.getId());

            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addAdditionalVenueDetails(VenueModel venue) {
    String sqlQuery = "UPDATE venue_table " +
                      "SET name = COALESCE(NULLIF(name, ''), ?), " +
                      "    location = COALESCE(NULLIF(location, ''), ?), " +
                      "    email = COALESCE(NULLIF(email, ''), ?), " +
                      "    contact_number = COALESCE(NULLIF(contact_number, ''), ?), " +
                      "    price_per_plate = COALESCE(price_per_plate, ?), " +
                      "    status = COALESCE(NULLIF(status, ''), ?) " +
                      "WHERE id = ?";

    try (Connection conn = dbConn.connection_base()) {
        PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
        pstmt.setString(1, venue.getName());
        pstmt.setString(2, venue.getLocation());
        pstmt.setString(3, venue.getEmail());
        pstmt.setString(4, venue.getContact_number());
        pstmt.setDouble(5, venue.getPrice_per_plate());
        pstmt.setString(6, venue.getStatus());
        pstmt.setInt(7, venue.getId());

        return pstmt.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

        
}


