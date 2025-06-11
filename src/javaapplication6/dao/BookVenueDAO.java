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
    

    
    
    public boolean cancelBooking(VenueDetailsFetchModel model)
    {
        String sqlQuery="DELETE FROM book_details WHERE venue_id=? and user_email=?";
        try (Connection conn=dbConn.connection_base()){
            PreparedStatement pstmt=conn.prepareStatement(sqlQuery);
            pstmt.setInt(1, model.getVenue_id());
            pstmt.setString(2, model.getUser_email());
            if(pstmt.executeUpdate()>0)
            {
                String body="Hello, your booking was successfully cancelled.\nVenue Name:"+model.getVenue_name()+"\nVenue Location:"+model.getVenue_location();
                boolean mailSent=smtpsMailSender.sendMail(model.getUser_email(), "Booking Cancellation", body);
                if(!mailSent)
                {
                    return false;
                }
                else
                {
                    String sqlQueryUpdate="UPDATE venue_table SET status='Unbooked' where id=?";
                    PreparedStatement pstmtUpdate=conn.prepareStatement(sqlQueryUpdate);
                    pstmtUpdate.setInt(1, model.getVenue_id());
                    if(pstmtUpdate.executeUpdate()>0)
                    {
                                        return true;
                    }
                }
                
                
            }
        } catch (Exception e) {
            return false;
        }
        return false;   
    }
}
