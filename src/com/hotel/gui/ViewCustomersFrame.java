package com.hotel.gui;

import com.hotel.database.DatabaseConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewCustomersFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public ViewCustomersFrame() {
        setTitle("View All Customers");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columns = {"Customer ID", "Name", "Phone", "Email", "ID Proof", "Address"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setRowHeight(25);
        table.getTableHeader().setBackground(new Color(0, 102, 204));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        loadCustomers();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(0, 102, 204));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("Arial", Font.BOLD, 14));
        refreshButton.setFocusPainted(false);
        refreshButton.setBorderPainted(false);
        refreshButton.setOpaque(true);
        refreshButton.addActionListener(e -> loadCustomers()); // or loadCustomers() or loadBookings()
        add(refreshButton, BorderLayout.SOUTH);



        setVisible(true);
    }

    private void loadCustomers() {
        model.setRowCount(0);

        try {
            String query = "SELECT * FROM customers ORDER BY customer_id DESC";
            ResultSet rs = DatabaseConnection.executeQuery(query);

            while (rs != null && rs.next()) {
                Object[] row = {
                        rs.getInt("customer_id"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("id_proof"),
                        rs.getString("address")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading customers: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
