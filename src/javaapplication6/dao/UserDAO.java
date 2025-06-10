package javaapplication6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javaapplication6.database.DBConn;
import javaapplication6.model.RegisterModel;
import javaapplication6.HashUtil.HashUtil;
import javaapplication6.model.EditNameModel;
import javaapplication6.model.EnterEmailModel;
import javaapplication6.model.LoginModel;
import javaapplication6.model.ResetModel;
import javaapplication6.model.VenueModel;


public class UserDAO {
    private final DBConn dbConn;

    public UserDAO() {
        dbConn = new DBConn();
    }

    public boolean registerUser(RegisterModel user) {
        boolean result = false;
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

        try (Connection conn = dbConn.connection_base();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            String hashedPassword = HashUtil.hashPassword(user.getPassword());
            stmt.setString(3, hashedPassword);

            int rowsInserted = stmt.executeUpdate();
            result = rowsInserted > 0;
        } catch (Exception e) {
            System.out.println("Error in registerUser: " + e.getMessage());
        }

        return result;
    }
    
   public boolean loginUser(LoginModel loginmodel) {
    String sql_query = "SELECT password FROM users WHERE email = ?";
    
    try (Connection conn = dbConn.connection_base();
         PreparedStatement pstmt = conn.prepareStatement(sql_query)) {

        pstmt.setString(1, loginmodel.getEmail());
        var rs = pstmt.executeQuery();

        if (rs.next()) {
            String storedHashedPassword = rs.getString("password");
            String inputHashedPassword = HashUtil.hashPassword(loginmodel.getPassword());

            return storedHashedPassword.equals(inputHashedPassword);
        }
    } catch (Exception e) {
        System.out.println("Error in loginUser: " + e.getMessage());
    }
    
    return false;
}
    public boolean checkCurrentPassword(LoginModel loginModel, String currentPass) {
    try (Connection conn = dbConn.connection_base()) {
        String sql = "SELECT password FROM users WHERE email = ?";
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
        String sql = "UPDATE users SET password = ? WHERE email = ?";
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
    
   public String editName(EditNameModel editNameModel, LoginModel loginModel) {
    String validationQuery = "SELECT name FROM users WHERE email = ?";
    String updateQuery = "UPDATE users SET name = ? WHERE email = ?";

    try (Connection conn = dbConn.connection_base()) {
        String currentName = null;
        try (PreparedStatement pstmtValidation = conn.prepareStatement(validationQuery)) {
            pstmtValidation.setString(1, loginModel.getEmail());
            try (var rs=pstmtValidation.executeQuery()){
                if(rs.next())
                {
                    currentName=rs.getString("name");
                }
                else
                {
                    System.out.println("User not found");
                }
            }
            catch(SQLException e)
            {
                System.out.println(e.getMessage()); 
            }
        }

        String newName = editNameModel.getNewName();
        if (currentName.equals(newName)) {
            return "New name cannot be the same as the current name.";
        }

        try (PreparedStatement pstmtUpdate = conn.prepareStatement(updateQuery)) {
            pstmtUpdate.setString(1, newName);
            pstmtUpdate.setString(2, loginModel.getEmail());
            int result = pstmtUpdate.executeUpdate();
            if(result>0)
            {
                return "Your name was successfully changed";
            }
            else
            {
                return "There were some error updating your name.";
            }
        }

    } catch (SQLException e) {
        return e.getMessage();
    } catch (Exception e) {
        System.err.println("General error: " + e.getMessage());
        return e.getMessage();
    }
}

   public boolean checkEmail(String email){
        String query="SELECT * FROM users where email=?";
        
        try(Connection conn = dbConn.connection_base()){
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1,email);
            ResultSet result= stmnt.executeQuery();
            if(result.next()){
                return true;
            } else{
                return false;
            }
        } catch(Exception e){
            return false;
        }
        
    }
    public boolean resetPassword(ResetModel resetModel){
        String query = "update users set password = ? where email = ?";
        
        try(Connection conn = dbConn.connection_base()){
            PreparedStatement stmnt = conn.prepareStatement(query );
            String newHashedPassword = HashUtil.hashPassword(resetModel.getPassword());
            stmnt.setString(1, newHashedPassword);
            stmnt.setString(2, resetModel.getEmail());
            int result = stmnt.executeUpdate();
            return result>0;
        }catch(Exception e){
           return false;
        }
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