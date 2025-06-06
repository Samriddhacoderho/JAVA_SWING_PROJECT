/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.dao;
import java.sql.*;
import javaapplication6.database.DBConn;
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
                VenueModel result=new VenueModel(rs.getString("name"), rs.getString("location"));
                return result;
            }
        } catch (Exception e) {
            return null;
        }
    }
}