package com.hotel.gui;

import com.hotel.database.DatabaseConnection;
import javax.swing.*;
import java.awt.*;

public class AddRoomFrame extends JFrame {
    private JTextField roomNumberField, priceField;
    private JComboBox<String> roomTypeCombo, bedTypeCombo;

    public AddRoomFrame() {
        setTitle("Add New Room");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Add New Room");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(150, 20, 200, 30);
        add(titleLabel);

        JLabel roomNoLabel = new JLabel("Room Number:");
        roomNoLabel.setBounds(50, 70, 120, 25);
        add(roomNoLabel);

        roomNumberField = new JTextField();
        roomNumberField.setBounds(180, 70, 200, 30);
        add(roomNumberField);

        JLabel typeLabel = new JLabel("Room Type:");
        typeLabel.setBounds(50, 120, 120, 25);
        add(typeLabel);

        String[] roomTypes = {"Single", "Double", "Suite", "Deluxe"};
        roomTypeCombo = new JComboBox<>(roomTypes);
        roomTypeCombo.setBounds(180, 120, 200, 30);
        add(roomTypeCombo);

        JLabel bedLabel = new JLabel("Bed Type:");
        bedLabel.setBounds(50, 170, 120, 25);
        add(bedLabel);

        String[] bedTypes = {"Single", "Double", "Queen", "King"};
        bedTypeCombo = new JComboBox<>(bedTypes);
        bedTypeCombo.setBounds(180, 170, 200, 30);
        add(bedTypeCombo);

        JLabel priceLabel = new JLabel("Price/Night:");
        priceLabel.setBounds(50, 220, 120, 25);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(180, 220, 200, 30);
        add(priceField);

        JButton addButton = new JButton("Add Room");
        addButton.setBounds(80, 290, 120, 35);
        addButton.setBackground(new Color(0, 153, 76));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);
        addButton.setOpaque(true);
        addButton.addActionListener(e -> addRoom());
        add(addButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(220, 290, 120, 35);
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

    private void addRoom() {
        String roomNo = roomNumberField.getText().trim();
        String roomType = (String) roomTypeCombo.getSelectedItem();
        String bedType = (String) bedTypeCombo.getSelectedItem();
        String price = priceField.getText().trim();

        if (roomNo.isEmpty() || price.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Double.parseDouble(price); // Validate price
            String query = "INSERT INTO rooms (room_number, room_type, bed_type, price_per_night, status) " +
                    "VALUES ('" + roomNo + "', '" + roomType + "', '" + bedType + "', " + price + ", 'available')";
            DatabaseConnection.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Room added successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Price must be a number!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
