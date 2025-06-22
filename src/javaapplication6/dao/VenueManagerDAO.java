/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javaapplication6.HashUtil.HashUtil;
import javaapplication6.database.DBConn;
import javaapplication6.model.LoginModel;
import javaapplication6.model.RegisterModel;
import javaapplication6.model.VenueModel;
import javaapplication6.controller.Mail.SMTPSMailSender;
import javaapplication6.model.BookVenueModel;
import javaapplication6.model.InquiryModel;
import javaapplication6.model.VenueDetailsFetchModel;

/**
 *
 * @author manoj
 */
public class VenueManagerDAO {

    private final DBConn dbConn;
    private SMTPSMailSender smtpsMailSender = new SMTPSMailSender();
    private final RegisterVenueDAO dao = new RegisterVenueDAO();
    //test

    public VenueManagerDAO() {
        dbConn = new DBConn();
    }

    public boolean registerVM(RegisterModel model) {
        boolean result = false;
        String sql = "INSERT INTO admin (name, email, password) VALUES (?, ?, ?)";

        try (Connection conn = dbConn.connection_base(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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

    public boolean registerVenue(VenueModel model) {
        boolean result = false;
        String sql = "insert into venue_table (name, location, email, contact_number,price_per_plate,status)values(?,?,?,?,?,?)";
        try (Connection conn = dbConn.connection_base(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, model.getName());
            stmt.setString(2, model.getLocation());
            stmt.setString(3, model.getEmail());
            stmt.setString(4, model.getContact_number());
            stmt.setDouble(5, model.getPrice_per_plate());
            stmt.setString(6, model.getStatus());
            int rowsInserted = stmt.executeUpdate();
            result = rowsInserted > 0;

        } catch (Exception e) {
            System.out.println("Error in registering Venue:" + e.getMessage());
        }

        return true;
    }
 public ArrayList<InquiryModel> getInquiries(LoginModel loginModel) {
        String sqlQuery = "SELECT * FROM inquiry_table where adminEmail=?";
        try (Connection conn = dbConn.connection_base()) {
            ArrayList<InquiryModel> inquiryList = new ArrayList<>();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, loginModel.getEmail());
            var rs = pstmt.executeQuery();
            while (rs.next()) {
                InquiryModel result = new InquiryModel(rs.getString("name"), rs.getString("email"), rs.getString("message"),rs.getString("adminEmail"));
                inquiryList.add(result);
            }
            return inquiryList;
        } catch (Exception e) {
            return null;
        }
    }


    public boolean approveRequest(LoginModel model, BookVenueModel modelBook, VenueModel modelVenue, VenueDetailsFetchModel result) {

        try (Connection conn = dbConn.connection_base()) {
            String sqlQueryUpdate = "UPDATE venue_table SET status='Booked' where email=?";
            String sqlQueryUP = "UPDATE book_details SET status_detail='approved' where user_email=? and venue_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sqlQueryUpdate);
            PreparedStatement pstmtDel = conn.prepareStatement(sqlQueryUP);
            pstmt.setString(1, model.getEmail());
            pstmtDel.setString(1, modelBook.getEmail());
            pstmtDel.setInt(2, result.getVenue_id());
            int rowsInserted = pstmt.executeUpdate();
            int rowsInsertedDel = pstmtDel.executeUpdate();
            if (rowsInserted > 0 && rowsInsertedDel > 0) {
                String body = "Hello, your booking was successfullly made.\nVenue Name:" + modelVenue.getName() + "\nVenue Location:" + modelVenue.getLocation();
                boolean mailSent = smtpsMailSender.sendMail(modelBook.getEmail(), "Booking Confirmation", body);
                if (mailSent) {

                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public boolean rejectRequest(LoginModel model, BookVenueModel modelBook, VenueModel modelVenue, VenueDetailsFetchModel result) {

        try (Connection conn = dbConn.connection_base()) {
            String sqlQueryUpdate;
            if (dao.adminVenuesView(model.getEmail()) == null || dao.adminVenuesView(model.getEmail()).isEmpty()) {
                sqlQueryUpdate = "UPDATE venue_table SET status='Unbooked' where email=?";
            } else {
                sqlQueryUpdate = "UPDATE venue_table SET status='Pending' where email=?";

            }

            String sqlQueryDelete = "DELETE FROM book_details WHERE user_email=? and venue_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sqlQueryUpdate);
            PreparedStatement pstmtDel = conn.prepareStatement(sqlQueryDelete);
            pstmt.setString(1, model.getEmail());
            pstmtDel.setString(1, modelBook.getEmail());
            pstmtDel.setInt(2, result.getVenue_id());
            int rowsInserted = pstmt.executeUpdate();
            int rowsInsertedDel = pstmtDel.executeUpdate();

            if (rowsInserted > 0 && rowsInsertedDel > 0) {
                String body = "Hello, your booking request was rejected by the venue manager.\nVenue Name:" + modelVenue.getName() + "\nVenue Location:" + modelVenue.getLocation();
                boolean mailSent = smtpsMailSender.sendMail(modelBook.getEmail(), "Booking Rejection", body);
                if (mailSent) {

                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public boolean loginManager(LoginModel loginmodel) {
        String sql_query = "SELECT password FROM admin WHERE email = ?";

        try (Connection conn = dbConn.connection_base(); PreparedStatement pstmt = conn.prepareStatement(sql_query)) {

            pstmt.setString(1, loginmodel.getEmail());
            var rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                String inputHashedPassword = HashUtil.hashPassword(loginmodel.getPassword());

                return storedHashedPassword.equals(inputHashedPassword);
            }
        } catch (Exception e) {
            System.out.println("Error in loginManager: " + e.getMessage());
        }

        return false;
    }
    public boolean checkCurrentPassword(LoginModel loginModel, String currentPass) {
    try (Connection conn = dbConn.connection_base()) {
        String sql = "SELECT password FROM admin WHERE email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, loginModel.getEmail());
        var rs = stmt.executeQuery();

        if (rs.next()) {
            String storedHashedPassword = rs.getString("password");
            String inputHashedPassword = HashUtil.hashPassword(currentPass);

            return storedHashedPassword.equals(inputHashedPassword);
        }
    } catch (Exception e) {
        System.out.println("Error in checkCurrentPassword: " + e.getMessage());
    }
    return false;
}
    public boolean updatePassword(LoginModel loginModel, String newPass) {
    try (Connection conn = dbConn.connection_base()) {
        String sql = "UPDATE admin SET password = ? WHERE email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        String hashedPassword = HashUtil.hashPassword(newPass);
        stmt.setString(1, hashedPassword);
        stmt.setString(2,loginModel.getEmail() );

        int updated = stmt.executeUpdate();
        return updated > 0;
    } catch (Exception e) {
        System.out.println("Error in updatePassword: " + e.getMessage());
    }
    return false;
}
}
