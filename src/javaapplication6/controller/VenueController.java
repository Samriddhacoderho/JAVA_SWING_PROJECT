/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javaapplication6.database.DBConn;
import javaapplication6.model.VenueModel;


/**
 *
 * @author ishan-college
 */
public class VenueController {
    private final DBConn dbConn;

    public VenueController() {
        dbConn = new DBConn();
    }

    public ArrayList<VenueModel> getAvailableVenuesByDate(String desiredDate) {
        ArrayList<VenueModel> venues = new ArrayList<>();
        String query = "SELECT * FROM venues WHERE status = 'Available' AND id NOT IN (SELECT venue_id FROM bookings WHERE date = ?)";

        try (Connection conn = dbConn.connection_base();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, desiredDate);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                VenueModel venue = new VenueModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getInt("capacity"),
                    rs.getString("status")
                );
                venues.add(venue);
            }

        } catch (Exception e) {
            System.out.println("Error fetching available venues: " + e.getMessage());
        }

        return venues;
    }
}