package javaapplication6.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConn {
    private static final String serverUrl = "jdbc:mysql://localhost:3306/";
    private static final String dbName = "swing_db_test";
    private static final String dbUrl = serverUrl + dbName;
    private static final String username = "root";
    private static final String password = "coventry2019";

    public Connection connection_base() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(serverUrl, username, password);
            Statement stmt = conn.createStatement();

            //Creating database if it doesnt exits
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
            System.out.println("Database ensured!");

            //Connecting to the database
            conn = DriverManager.getConnection(dbUrl, username, password);

            //Creating table if not exists
            String sql = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100), "
                    + "email VARCHAR(100) UNIQUE, "
                    + "password VARCHAR(100))";

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Users table ensured!");

        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
        }

        return conn;
    }

    public PreparedStatement prepareStatement(String sql_query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
