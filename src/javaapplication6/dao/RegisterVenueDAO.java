/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javaapplication6.database.DBConn;
import javaapplication6.model.BookVenueModel;
import javaapplication6.model.LoginModel;
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

    public ArrayList<VenueDetailsFetchModel> adminVenuesView(String email) {
        String sqlQuery = "select * from book_details join venue_table on venue_table.id=book_details.venue_id where venue_table.email=? and book_details.completed='no'";
        try (Connection conn = dbConn.connection_base()) {
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, email);
            ArrayList<VenueDetailsFetchModel> venuelist = new ArrayList<>();
            var rs = pstmt.executeQuery();
            while (rs.next()) {
                VenueDetailsFetchModel result = new VenueDetailsFetchModel(rs.getInt("venue_id"), rs.getString("user_email"), rs.getString("name"), rs.getString("location"), email, rs.getString("contact_number"), rs.getInt("estimated_guests"), rs.getString("status"), rs.getDouble("price_per_plate"), rs.getLong("total_price"), rs.getString("payment"), rs.getString("completed"), rs.getString("status_detail"));
                venuelist.add(result);
            }
            return venuelist;
        } catch (Exception e) {
            return null;
        }
    }

    public VenueDetailsFetchModel getVenue_in_myAdminbookingPage(String user_email, String email) {
        String sqlQuery = "select * from book_details join venue_table on venue_table.id=book_details.venue_id where book_details.user_email=? and venue_table.email=?";
        try (Connection conn = dbConn.connection_base()) {
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, user_email);
            pstmt.setString(2, email);
            var rs = pstmt.executeQuery();
            if (rs.next()) {
                VenueDetailsFetchModel result = new VenueDetailsFetchModel(rs.getInt("venue_id"), rs.getString("user_email"), rs.getString("name"), rs.getString("location"), email, rs.getString("contact_number"), rs.getInt("estimated_guests"), rs.getString("status"), rs.getDouble("price_per_plate"), rs.getLong("total_price"), rs.getString("payment"), rs.getString("completed"), rs.getString("status_detail"));
                return result;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public boolean registerVenue(VenueModel model) {
        boolean result = false;
        String sql = "insert into venue_table (name, location, email, contact_number,price_per_plate)values(?,?,?,?,?)";
        try (Connection conn = dbConn.connection_base(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, model.getName());
            stmt.setString(2, model.getLocation());
            stmt.setString(3, model.getEmail());
            stmt.setString(4, model.getContact_number());
            stmt.setDouble(5, model.getPrice_per_plate());
            int rowsInserted = stmt.executeUpdate();
            result = rowsInserted > 0;
            if (result) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error in registering Venue:" + e.getMessage());
        }

        return false;
    }

    public boolean updateVenue(VenueModel model) {
        String sql = "UPDATE venue_table SET name=?, location=?, email=?, contact_number=?, price_per_plate=?, image=? WHERE id=?";

        try (Connection conn = dbConn.connection_base(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, model.getName());
            stmt.setString(2, model.getLocation());
            stmt.setString(3, model.getEmail());
            stmt.setString(4, model.getContact_number());
            stmt.setDouble(5, model.getPrice_per_plate());
            stmt.setBytes(6, model.getImage());
            stmt.setInt(7, model.getId());

            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (Exception e) {
            System.out.println("Error in registering Venue:" + e.getMessage());
        }
        return false;
    }

    public VenueModel adminVenueViewFetch(String email) {
        String sqlQuery = "SELECT * FROM venue_table WHERE email = ?";
        try (Connection conn = dbConn.connection_base()) {
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, email);
            var rs = pstmt.executeQuery();
            if (rs.next()) {
                VenueModel result = new VenueModel(rs.getInt("id"), rs.getString("name"), rs.getString("location"), rs.getString("email"), rs.getString("contact_number"), rs.getDouble("price_per_plate"), rs.getString("status"));
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean markComplete(LoginModel loginModel, BookVenueModel modelBook) {
        String sqlQuery = "UPDATE book_details JOIN venue_table ON book_details.venue_id=venue_table.id SET book_details.completed='yes' WHERE venue_table.email=? and book_details.user_email=?";
        String sqlQueryUP;
        if (adminVenuesView(loginModel.getEmail()) == null || adminVenuesView(loginModel.getEmail()).isEmpty()) {
            sqlQueryUP = "UPDATE venue_table SET status='Unbooked' WHERE email=?";
        } else {
            sqlQueryUP = "UPDATE venue_table SET status='Pending' WHERE email=?";
        }
        try (Connection conn = dbConn.connection_base()) {
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            PreparedStatement pstmtUP = conn.prepareStatement(sqlQueryUP);
            pstmt.setString(1, loginModel.getEmail());
            pstmt.setString(2, modelBook.getEmail());
            pstmtUP.setString(1, loginModel.getEmail());
            int result = pstmt.executeUpdate();
            int resultUP = pstmtUP.executeUpdate();
            return result > 0 && resultUP > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean payStatus(LoginModel loginModel, BookVenueModel modelBook) {
        String sqlQuery = "UPDATE book_details JOIN venue_table ON book_details.venue_id=venue_table.id SET book_details.payment='payed' WHERE venue_table.email=? and book_details.user_email=?";
        try (Connection conn = dbConn.connection_base()) {
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, loginModel.getEmail());
            pstmt.setString(2, modelBook.getEmail());
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public VenueModel fetchVenueBasicInfo(String email) {
        String sqlQuery = "SELECT name, email, image FROM venue_table WHERE email = ?";
        try (Connection conn = dbConn.connection_base()) {
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, email);
            var rs = pstmt.executeQuery();
            if (rs.next()) {
                VenueModel venue = new VenueModel();
                venue.setName(rs.getString("name"));
                venue.setEmail(rs.getString("email"));
                venue.setImage(rs.getBytes("image"));
                return venue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
