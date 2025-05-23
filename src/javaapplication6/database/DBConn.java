/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.database;

import java.sql.*;

/**
 *
 * @author suhritsatyal
 */
public class DBConn {
    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author suhritsatyal
 */
    private static final String url = "jdbc:mysql://localhost:3306/swingdb";
    private static final String username="root";
    private static final String password="@@Forget@@321";
    
    public Connection connection_base()
    {
        Connection conn=null;
        try {
             conn=DriverManager.getConnection(url,username,password);
           
                System.out.println("Connection Successful");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
