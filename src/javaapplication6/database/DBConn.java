package javaapplication6.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DBConn {

    private static final String baseUrl = "jdbc:mysql://localhost:3306/";
    private static final String dbName = "swing_db_test";
    private static final String username = "root";
    private static final String password = "Coventry2019@";

    public Connection connection_base() {
        Connection conn = null;

        try {
            Connection initialConn = DriverManager.getConnection(baseUrl, username, password);
            Statement stmt = initialConn.createStatement();

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);

            conn = DriverManager.getConnection(baseUrl + dbName, username, password);

            stmt = conn.createStatement();

            // Create users table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100), "
                    + "email VARCHAR(100) UNIQUE, "
                    + "password VARCHAR(100))");

            // Create report_table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS report_table ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "email VARCHAR(200), "
                    + "subject VARCHAR(200), "
                    + "description VARCHAR(200))");
            
            // Create venue_table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS venue_table ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(200), "
                    + "location VARCHAR(200), "
                    + "email VARCHAR(200), "
                    + "contact_number VARCHAR(200), "
                    + "price_per_plate FLOAT, "
                    + "status VARCHAR(200) DEFAULT 'Unbooked', "
                    + "image BLOB DEFAULT NULL"
                    + ")");

                    
            // Create book_details table
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS book_details ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "venue_id INT, "
                    + "user_email VARCHAR(100), "
                    + "estimated_guests VARCHAR(200), "
                    + "total_price FLOAT, "
                    + "FOREIGN KEY (venue_id) REFERENCES venue_table(id)"
                    + ")"
            );
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS admin ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100), "
                    + "email VARCHAR(100) UNIQUE, "
                    + "password VARCHAR(100))");

            

        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
        }

        return conn;
    }

    public PreparedStatement prepareStatement(String sql_query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
