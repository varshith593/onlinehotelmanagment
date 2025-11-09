package com.hotel.gui;

import com.hotel.database.DatabaseConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewRoomsFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public ViewRoomsFrame() {
        setTitle("View All Rooms");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columns = {"Room ID", "Room No", "Type", "Bed Type", "Price/Night", "Status"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setRowHeight(25);
        table.getTableHeader().setBackground(new Color(0, 102, 204));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        loadRooms();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(0, 102, 204));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("Arial", Font.BOLD, 14));
        refreshButton.setFocusPainted(false);
        refreshButton.setBorderPainted(false);
        refreshButton.setOpaque(true);
        refreshButton.addActionListener(e -> loadRooms()); // or loadCustomers() or loadBookings()
        add(refreshButton, BorderLayout.SOUTH);


        setVisible(true);
    }

    private void loadRooms() {
        model.setRowCount(0); // Clear existing rows

        try {
            String query = "SELECT * FROM rooms ORDER BY room_number";
            ResultSet rs = DatabaseConnection.executeQuery(query);

            while (rs != null && rs.next()) {
                Object[] row = {
                        rs.getInt("room_id"),
                        rs.getString("room_number"),
                        rs.getString("room_type"),
                        rs.getString("bed_type"),
                        String.format("₹%.2f", rs.getDouble("price_per_night")),
                        rs.getString("status")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading rooms: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
