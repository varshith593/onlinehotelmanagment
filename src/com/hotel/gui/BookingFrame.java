package com.hotel.gui;

import com.hotel.database.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookingFrame extends JFrame {
    private JComboBox<String> customerCombo, roomCombo;
    private JTextField checkInField, checkOutField;

    public BookingFrame() {
        setTitle("New Booking");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Create New Booking");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(140, 20, 250, 30);
        add(titleLabel);

        JLabel customerLabel = new JLabel("Customer:");
        customerLabel.setBounds(50, 80, 120, 25);
        add(customerLabel);

        customerCombo = new JComboBox<>();
        customerCombo.setBounds(180, 80, 250, 30);
        loadCustomers();
        add(customerCombo);

        JLabel roomLabel = new JLabel("Room:");
        roomLabel.setBounds(50, 130, 120, 25);
        add(roomLabel);

        roomCombo = new JComboBox<>();
        roomCombo.setBounds(180, 130, 250, 30);
        loadAvailableRooms();
        add(roomCombo);

        JLabel checkInLabel = new JLabel("Check-In (YYYY-MM-DD):");
        checkInLabel.setBounds(50, 180, 160, 25);
        add(checkInLabel);

        checkInField = new JTextField(LocalDate.now().toString());
        checkInField.setBounds(220, 180, 210, 30);
        add(checkInField);

        JLabel checkOutLabel = new JLabel("Check-Out (YYYY-MM-DD):");
        checkOutLabel.setBounds(50, 230, 160, 25);
        add(checkOutLabel);

        checkOutField = new JTextField(LocalDate.now().plusDays(1).toString());
        checkOutField.setBounds(220, 230, 210, 30);
        add(checkOutField);

        JButton bookButton = new JButton("Create Booking");
        bookButton.setBounds(120, 300, 140, 35);
        bookButton.setBackground(new Color(0, 153, 76));
        bookButton.setForeground(Color.WHITE);
        bookButton.setFont(new Font("Arial", Font.BOLD, 14));
        bookButton.setFocusPainted(false);
        bookButton.setBorderPainted(false);
        bookButton.setOpaque(true);
        bookButton.addActionListener(e -> createBooking());
        add(bookButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(280, 300, 140, 35);
        cancelButton.setBackground(new Color(204, 0, 0));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setFocusPainted(false);
        cancelButton.setBorderPainted(false);
        cancelButton.setOpaque(true);
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);


        setVisible(true);
    }

    private void loadCustomers() {
        try {
            String query = "SELECT customer_id, full_name FROM customers";
            ResultSet rs = DatabaseConnection.executeQuery(query);

            while (rs != null && rs.next()) {
                customerCombo.addItem(rs.getInt("customer_id") + " - " + rs.getString("full_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAvailableRooms() {
        try {
            String query = "SELECT room_id, room_number, room_type, price_per_night FROM rooms WHERE status='available'";
            ResultSet rs = DatabaseConnection.executeQuery(query);

            while (rs != null && rs.next()) {
                roomCombo.addItem(rs.getInt("room_id") + " - Room " + rs.getString("room_number") +
                        " (" + rs.getString("room_type") + ") - ₹" + rs.getDouble("price_per_night"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createBooking() {
        if (customerCombo.getSelectedItem() == null || roomCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please select customer and room!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String customerStr = customerCombo.getSelectedItem().toString();
        String roomStr = roomCombo.getSelectedItem().toString();
        int customerId = Integer.parseInt(customerStr.split(" - ")[0]);
        int roomId = Integer.parseInt(roomStr.split(" - ")[0]);

        String checkIn = checkInField.getText().trim();
        String checkOut = checkOutField.getText().trim();

        try {
            // Calculate total amount
            LocalDate checkInDate = LocalDate.parse(checkIn);
            LocalDate checkOutDate = LocalDate.parse(checkOut);
            long days = ChronoUnit.DAYS.between(checkInDate, checkOutDate);

            if (days <= 0) {
                JOptionPane.showMessageDialog(this, "Check-out must be after check-in!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Get room price
            String priceQuery = "SELECT price_per_night FROM rooms WHERE room_id=" + roomId;
            ResultSet rs = DatabaseConnection.executeQuery(priceQuery);
            double pricePerNight = 0;
            if (rs != null && rs.next()) {
                pricePerNight = rs.getDouble("price_per_night");
            }

            double totalAmount = days * pricePerNight;

            // Create booking
            String query = "INSERT INTO bookings (customer_id, room_id, check_in_date, check_out_date, total_amount, booking_status) " +
                    "VALUES (" + customerId + ", " + roomId + ", '" + checkIn + "', '" + checkOut + "', " +
                    totalAmount + ", 'active')";
            DatabaseConnection.executeUpdate(query);

            // Update room status
            String updateRoom = "UPDATE rooms SET status='occupied' WHERE room_id=" + roomId;
            DatabaseConnection.executeUpdate(updateRoom);

            JOptionPane.showMessageDialog(this,
                    "Booking created successfully!\nTotal Amount: ₹" + String.format("%.2f", totalAmount) +
                            "\nDuration: " + days + " days",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
