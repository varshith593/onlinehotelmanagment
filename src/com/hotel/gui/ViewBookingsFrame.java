package com.hotel.gui;

import com.hotel.database.DatabaseConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewBookingsFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public ViewBookingsFrame() {
        setTitle("View All Bookings");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columns = {"Booking ID", "Customer", "Room", "Check-In", "Check-Out", "Amount", "Status"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setRowHeight(25);
        table.getTableHeader().setBackground(new Color(0, 102, 204));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        loadBookings();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadBookings());
        add(refreshButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadBookings() {
        model.setRowCount(0);

        try {
            String query = "SELECT b.booking_id, c.full_name, r.room_number, " +
                    "b.check_in_date, b.check_out_date, b.total_amount, b.booking_status " +
                    "FROM bookings b " +
                    "JOIN customers c ON b.customer_id = c.customer_id " +
                    "JOIN rooms r ON b.room_id = r.room_id " +
                    "ORDER BY b.booking_id DESC";
            ResultSet rs = DatabaseConnection.executeQuery(query);

            while (rs != null && rs.next()) {
                Object[] row = {
                        rs.getInt("booking_id"),
                        rs.getString("full_name"),
                        rs.getString("room_number"),
                        rs.getDate("check_in_date"),
                        rs.getDate("check_out_date"),
                        String.format("₹%.2f", rs.getDouble("total_amount")),
                        rs.getString("booking_status")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading bookings: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
