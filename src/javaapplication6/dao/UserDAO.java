///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package javaapplication6.dao;

import javaapplication6.database.DBConn;
import javaapplication6.model.RegisterModel;
import java.sql.*;
//
///**
// *
// * @author suhritsatyal
// */
public class UserDAO {
    
    
    public boolean registerUser(RegisterModel registermodel)
    {
        DBConn conn=new DBConn();
        try {
            
        } catch (Exception e) {
            
        }
    }
    
    public updateUser(int userId, String newName){
        String sql = "update users set name = ? where id = ?";
        try (Connection conn = DBConn.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1,newName);
            pstmt.setInt(2,userId);
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated;
            
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        
    }
}
