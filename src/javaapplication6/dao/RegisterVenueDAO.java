/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javaapplication6.database.DBConn;
import javaapplication6.model.VenueDetailsFetchModel;
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
   
    
    public VenueDetailsFetchModel adminVenueView(String email)
    {
        String sqlQuery="select * from book_details join venue_table on venue_table.id=book_details.venue_id where venue_table.email=?";
        try (Connection conn=dbConn.connection_base()){
            PreparedStatement pstmt=conn.prepareStatement(sqlQuery);
            pstmt.setString(1, email);
            var rs=pstmt.executeQuery();
            if(rs.next())
            {
                VenueDetailsFetchModel result=new VenueDetailsFetchModel(rs.getInt("venue_id"), rs.getString("user_email"), rs.getString("name"), rs.getString("location"),email, rs.getString("contact_number"), rs.getInt("estimated_guests"), rs.getString("status"), rs.getDouble("price_per_plate"), rs.getLong("total_price"));
                return result;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
    
    public boolean registerVenue(VenueModel model){
        boolean result= false;
        String sql = "insert into venue_table (name, location, email, contact_number,price_per_plate)values(?,?,?,?,?)";
        try(Connection conn = dbConn.connection_base();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,model.getName());
            stmt.setString(2, model.getLocation());
            stmt.setString(3, model.getEmail());
            stmt.setString(4,model.getContact_number());
            stmt.setDouble(5,model.getPrice_per_plate());
            int rowsInserted = stmt.executeUpdate();
            result = rowsInserted>0;
            if(result)
            {
                return true;
            }
            
        } catch(Exception e){
            System.out.println("Error in registering Venue:"+e.getMessage());
        }
        
        
        return false;
    }

        
}


