<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.dao;

import javaapplication6.database.DBConn;
import javaapplication6.model.RegisterModel;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author suhritsatyal
 */
public class UserDAO {
    
    
    public boolean registerUser(RegisterModel registermodel)
    {
        DBConn conn=new DBConn();
       return true;
    }
    
    public boolean loginUser(LoginModel loginmodel)  //backend developer le controller ra model banayechi yo error jancha, for the time being there is error.
    {
        DBConn conn=new DBConn();
        String sql_query="SELECT * FROM users_table where email=? and password=?";
        try {
            PreparedStatement pstmt=conn.prepareStatement(sql_query);
            pstmt.setString(1, loginmodel.getEmail());
            pstmt.setString(2, loginmodel.getPassword());
            if(pstmt.executeQuery().next())
            {
                return true;
            }
            else
            {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
=======
package javaapplication6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javaapplication6.database.DBConn;
import javaapplication6.model.RegisterModel;
import javaapplication6.HashUtil.HashUtil;


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
>>>>>>> ishan
    }
}
