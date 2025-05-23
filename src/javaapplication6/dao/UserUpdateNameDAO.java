/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.dao;

import java.sql.PreparedStatement;
import javaapplication6.database.DBConn;

/**
 *
 * @author suhritsatyal
 */
public class UserUpdateNameDAO {
    public boolean EditName(UserNameUpdateModel userNameUpdateModel){  //ya error aako cha because usernameupdatemodel bhanne model banya chaina, backend developer le banauna paryo
        DBConn conn=new DBConn();
        String sql_query="UPDATE users_table SET name=? where email=?";
         try {
            PreparedStatement pstmt=conn.prepareStatement(sql_query);
            pstmt.setString(1, userNameUpdateModel.getName());
            pstmt.setString(2, userNameUpdateModel.getEmail());
            if(pstmt.executeUpdate()>0)
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
    }
}
