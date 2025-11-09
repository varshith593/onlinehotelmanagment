package com.hotel.main;

import com.hotel.database.DatabaseConnection;
import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("=== Testing Database Connection ===\n");

        try {
            System.out.println("Step 1: Attempting to connect...");
            Connection conn = DatabaseConnection.getConnection();

            if (conn != null) {
                System.out.println("✓ Connection successful!\n");

                System.out.println("Step 2: Checking for users table...");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM users");

                System.out.println("✓ Users table found!\n");
                System.out.println("Users in database:");
                System.out.println("==================");

                boolean hasUsers = false;
                while (rs.next()) {
                    hasUsers = true;
                    System.out.println("User ID: " + rs.getInt("user_id"));
                    System.out.println("Username: " + rs.getString("username"));
                    System.out.println("Password: " + rs.getString("password"));
                    System.out.println("Full Name: " + rs.getString("full_name"));
                    System.out.println("Role: " + rs.getString("role"));
                    System.out.println("------------------");
                }

                if (!hasUsers) {
                    System.out.println("⚠ WARNING: No users found in database!");
                    System.out.println("You need to add the admin user.");
                }

                rs.close();
                stmt.close();
                conn.close();

            } else {
                System.out.println("✗ Connection FAILED!");
                System.out.println("Check your DatabaseConnection.java settings:");
                System.out.println("- URL: jdbc:mysql://localhost:3306/hotel_management");
                System.out.println("- Username: root");
                System.out.println("- Password: [check if correct]");
            }

        } catch (SQLException e) {
            System.out.println("✗ SQL Error: " + e.getMessage());
            System.out.println("\nPossible issues:");
            if (e.getMessage().contains("Access denied")) {
                System.out.println("- Wrong MySQL username or password");
                System.out.println("- Check DatabaseConnection.java PASSWORD field");
            } else if (e.getMessage().contains("Unknown database")) {
                System.out.println("- Database 'hotel_management' does not exist");
                System.out.println("- Run: CREATE DATABASE hotel_management; in MySQL");
            } else if (e.getMessage().contains("Table") && e.getMessage().contains("doesn't exist")) {
                System.out.println("- Tables not created yet");
                System.out.println("- Run the CREATE TABLE commands in MySQL");
            }
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("✗ Unexpected Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
