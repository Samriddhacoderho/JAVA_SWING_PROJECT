package javaapplication6.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConn {
    private static final String baseUrl = "jdbc:mysql://localhost:3306/";
    private static final String dbName = "swing_db_test";
    private static final String username = "root";
    private static final String password = "Coventry2019@";

<<<<<<< HEAD
    public Connection connection_base() {
        Connection conn = null;
=======

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
>>>>>>> manoj
        try {
            Connection initialConn = DriverManager.getConnection(baseUrl, username, password);
            Statement stmt = initialConn.createStatement();

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
            System.out.println("Database ensured!");

            conn = DriverManager.getConnection(baseUrl + dbName, username, password);

            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100), "
                    + "email VARCHAR(100) UNIQUE, "
                    + "password VARCHAR(100))";
            stmt.executeUpdate(sql);
            System.out.println("Users table ensured!");

        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
        }

        return conn;
    }
}
