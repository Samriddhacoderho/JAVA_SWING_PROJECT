/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.dao;
import java.sql.*;
import java.util.ArrayList;
import javaapplication6.controller.Mail.SMTPSMailSender;
import javaapplication6.database.DBConn;
import javaapplication6.model.BookVenueModel;
import javaapplication6.model.VenueDetailsFetchModel;
import javaapplication6.model.VenueModel;

/**
 *
 * @author ishan-college
 */

public class BookVenueDAO {
    private final DBConn dbConn;
            private SMTPSMailSender smtpsMailSender=new SMTPSMailSender();


    public BookVenueDAO() {
        dbConn = new DBConn();
    }
    
    public boolean insertVenue(VenueModel venue) {
    String sqlQuery = "INSERT INTO venue_table (name, location, email, contact_number, price_per_plate, status) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = dbConn.connection_base()) {
        PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
        pstmt.setString(1, venue.getName());
        pstmt.setString(2, venue.getLocation());
        pstmt.setString(3, venue.getEmail());
        pstmt.setString(4, venue.getContact_number());
        pstmt.setDouble(5, venue.getPrice_per_plate());
        pstmt.setString(6, venue.getStatus());

        return pstmt.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
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
                VenueModel result=new VenueModel(rs.getInt("id"),rs.getString("name"), rs.getString("location"),rs.getString("email"),rs.getString("contact_number"),rs.getDouble("price_per_plate"),rs.getString("status"));
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
                VenueModel result=new VenueModel(rs.getInt("id"),rs.getString("name"), rs.getString("location"),rs.getString("email"),rs.getString("contact_number"),rs.getDouble("price_per_plate"),rs.getString("status"));
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
                VenueModel result=new VenueModel(rs.getInt("id"),rs.getString("name"), rs.getString("location"),rs.getString("email"),rs.getString("contact_number"),rs.getDouble("price_per_plate"),rs.getString("status"));
                venuelist.add(result);
            }
            return venuelist;
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean bookVenue(BookVenueModel modelBook,VenueModel modelVenue)
    {
        String sqlQuery="INSERT INTO book_details(venue_id,user_email,estimated_guests,total_price) values(?,?,?,?)";
        try (Connection conn=dbConn.connection_base()){
            PreparedStatement pstmt=conn.prepareStatement(sqlQuery);
            pstmt.setInt(1, modelVenue.getId());
            pstmt.setString(2, modelBook.getEmail());
            pstmt.setString(3, modelBook.getEstimated_guests());
            pstmt.setDouble(4, modelBook.getEstimated_price());
            if(pstmt.executeUpdate()>0)
            {
                String body="Hello, your booking was successfullly made.\nVenue Name:"+modelVenue.getName()+"\nVenue Location:"+modelVenue.getLocation();
                boolean mailSent=smtpsMailSender.sendMail(modelBook.getEmail(), "Booking Confirmation", body);
                if(mailSent)
                {
                    System.out.println(modelVenue.getId());
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
    
    public VenueDetailsFetchModel getVenue_in_mybookingPage(String email)
    {
        String sqlQuery="select * from book_details join venue_table on venue_table.id=book_details.venue_id where book_details.user_email=?";
        try (Connection conn=dbConn.connection_base()){
            PreparedStatement pstmt=conn.prepareStatement(sqlQuery);
            pstmt.setString(1, email);
            var rs=pstmt.executeQuery();
            if(rs.next())
            {
                VenueDetailsFetchModel result=new VenueDetailsFetchModel(rs.getInt("venue_id"), email, rs.getString("name"), rs.getString("location"), rs.getString("email"), rs.getString("contact_number"), rs.getInt("estimated_guests"), rs.getString("status"), rs.getDouble("price_per_plate"), rs.getLong("total_price"));
                return result;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
    public void insertSampleVenues() {
        String sqlQuery = "INSERT INTO venue_table (name, location, email, contact_number, price_per_plate, status) VALUES (?, ?, ?, ?, ?, ?)";
        String[][] data = {
            {"Grand Horizon Hall", "Kathmandu", "contact@grandhorizon.com", "9801234567", "1200.50", "Unbooked"},
            {"Everest View Banquet", "Pokhara", "info@everestview.com", "9812345678", "1350.00", "Unbooked"},
            {"Lalitpur Palace Venue", "Kathmandu", "events@lalitpurpalace.com", "9823456789", "1500.75", "Booked"},
            {"Royal Banquet", "Bhaktapur", "royal@banquetnp.com", "9809876543", "1100.00", "Unbooked"},
            {"Moonlight Garden", "Kathmandu", "moonlight@events.com", "9843219876", "1250.25", "Booked"},
            {"Himalayan Fiesta", "Pokhara", "contact@himalayanfiesta.com", "9811122233", "1400.00", "Unbooked"},
            {"Sunset View Hall", "Kathmandu", "sunsetview@hall.com", "9803344556", "1300.00", "Unbooked"},
            {"Peaceful Valley Venue", "Bhaktapur", "peace@valleyvenue.com", "9833344455", "1450.00", "Booked"},
            {"Evergreen Event Center", "Pokhara", "evergreen@eventcenter.com", "9801122334", "1150.00", "Unbooked"},
            {"Blue Sky Banquet", "Bhaktapur", "bluesky@banquets.com", "9845566778", "1380.00", "Booked"}
        };

        try (Connection conn = dbConn.connection_base()) {
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            for (String[] row : data) {
                pstmt.setString(1, row[0]);
                pstmt.setString(2, row[1]);
                pstmt.setString(3, row[2]);
                pstmt.setString(4, row[3]);
                pstmt.setDouble(5, Double.parseDouble(row[4]));
                pstmt.setString(6, row[5]);
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error in insertSampleVenues: " + e.getMessage());
        }
    }
}
    
