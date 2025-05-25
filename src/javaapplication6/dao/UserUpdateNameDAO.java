///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package javaapplication6.dao;
import javaapplication6.model.RegisterModel;
import java.sql.PreparedStatement;
import javaapplication6.database.DBConn;
import java.sql.Connection;

//
///**
// *
// * @author suhritsatyal
// */

public class UserUpdateNameDAO {
    public boolean EditName(RegisterModel user) {
        DBConn dbConn = new DBConn();
        Connection conn = dbConn.connection_base();  // ✅ Get the actual SQL connection

        String sql_query = "UPDATE users SET name=? WHERE email=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql_query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());

            if (pstmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }
}

