/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.dao;
import java.sql.*;
import java.util.ArrayList;
import javaapplication6.database.DBConn;
import javaapplication6.model.BookVenueModel;
import javaapplication6.model.VenueModel;

/**
 *
 * @author ishan-college
 */

public class BookVenueDAO {
    private final DBConn dbConn;

    public BookVenueDAO() {
        dbConn = new DBConn();
    }
    
    public VenueModel searchParticularVenue(VenueModel venueModel)
    {
        String sqlQuery="SELECT * FROM venue_table where name=? and location=?";
        try (Connection conn=dbConn.connection_base()){
            PreparedStatement pstmt=conn.prepareStatement(sqlQuery);
            pstmt.setString(1, venueModel.getName());
            pstmt.setString(2, venueModel.getLocation());
            var rs=pstmt.executeQuery();
            if(!rs.next())
            {
                return null;                
 
            }
            else
            {
                VenueModel result=new VenueModel(rs.getString("name"), rs.getString("location"),rs.getString("email"),rs.getString("contact_number"),rs.getDouble("price_per_plate"));
                return result;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public ArrayList<VenueModel> searchLocationVenue(VenueModel venueModel)
    {
        String sqlQuery="SELECT * FROM venue_table where location=?";
        try (Connection conn=dbConn.connection_base()){
            ArrayList<VenueModel> venuelist=new ArrayList<>();
            PreparedStatement pstmt=conn.prepareStatement(sqlQuery);
            pstmt.setString(1, venueModel.getLocation());
            var rs=pstmt.executeQuery();
            while(rs.next())
            {
                VenueModel result=new VenueModel(rs.getString("name"), rs.getString("location"),rs.getString("email"),rs.getString("contact_number"),rs.getDouble("price_per_plate"));
                venuelist.add(result);
            }
            return venuelist;
        } catch (Exception e) {
            return null;
        }
    }
    
    public ArrayList<VenueModel> sortVenues(String location,String type)
    {
        if(type.equalsIgnoreCase("Lowest to Highest"))
            {
                type="ASC";
            }
            else
            {
                type="DESC";
            }
        String sqlQuery="SELECT * FROM venue_table where location=? order by price_per_plate "+type;
        try (Connection conn=dbConn.connection_base()){
            
            ArrayList<VenueModel> venuelist=new ArrayList<>();
            PreparedStatement pstmt=conn.prepareStatement(sqlQuery);
            pstmt.setString(1, location);
            var rs=pstmt.executeQuery();
            while(rs.next())
            {
                VenueModel result=new VenueModel(rs.getString("name"), rs.getString("location"),rs.getString("email"),rs.getString("contact_number"),rs.getDouble("price_per_plate"));
                venuelist.add(result);
            }
            return venuelist;
        } catch (Exception e) {
            return null;
        }
    }
    
//    public boolean bookVenue(BookVenueModel model)
//    {
//        String sqlQuery="INSERT INTO book_details()"
//    }
}