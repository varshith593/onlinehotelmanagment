package com.hotel.gui;

import com.hotel.database.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, exitButton;

    public LoginFrame() {
        setTitle("Hotel Management System - Login");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Title
        JLabel titleLabel = new JLabel("Hotel Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(70, 20, 350, 30);
        titleLabel.setForeground(new Color(0, 102, 204));
        add(titleLabel);

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userLabel.setBounds(50, 80, 100, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(160, 80, 220, 30);
        add(usernameField);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passLabel.setBounds(50, 130, 100, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 130, 220, 30);
        add(passwordField);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(120, 190, 100, 35);
        loginButton.setBackground(new Color(0, 153, 76));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setOpaque(true);
        loginButton.addActionListener(e -> performLogin());
        add(loginButton);

// Exit Button
        exitButton = new JButton("Exit");
        exitButton.setBounds(240, 190, 100, 35);
        exitButton.setBackground(new Color(204, 0, 0));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        exitButton.setOpaque(true);
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);

        setVisible(true);
    }

    private void performLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        System.out.println("Attempting login with username: " + username); // DEBUG

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String query = "SELECT * FROM users WHERE username='" + username +
                    "' AND password='" + password + "'";
            System.out.println("Query: " + query); // DEBUG

            ResultSet rs = DatabaseConnection.executeQuery(query);

            if (rs != null && rs.next()) {
                String fullName = rs.getString("full_name");
                JOptionPane.showMessageDialog(this, "Welcome, " + fullName + "!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                new DashboardFrame().setVisible(true);
                this.dispose();
            } else {
                System.out.println("No matching user found"); // DEBUG
                JOptionPane.showMessageDialog(this, "Invalid username or password!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            System.out.println("Login error: " + ex.getMessage()); // DEBUG
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Login error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
