/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.dao;
import java.sql.*;

/**
 *
 * @author ishan-college
 */

public class BookVenueDAO {
    private static final String DB_URL = "jdbc:sqlite:venues.db";

    public BookVenueDAO() {
    }

    public boolean markVenueAsBooked(int venueId) {
        String sql = "UPDATE venues SET status = 'Booked' WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, venueId);
            int affected = pstmt.executeUpdate();
            return affected > 0; 
        } catch (SQLException e) {
            System.out.println("Error booking venue (ID: " + venueId + "): " + e.getMessage());
            return false;
        }
    }
}

