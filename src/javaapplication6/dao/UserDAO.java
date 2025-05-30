package javaapplication6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javaapplication6.database.DBConn;
import javaapplication6.model.RegisterModel;
import javaapplication6.HashUtil.HashUtil;
import javaapplication6.model.EditNameModel;
import javaapplication6.model.LoginModel;


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


}