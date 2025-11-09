package com.hotel.gui;

import com.hotel.database.DatabaseConnection;
import javax.swing.*;
import java.awt.*;

public class AddCustomerFrame extends JFrame {
    private JTextField nameField, phoneField, emailField, idProofField;
    private JTextArea addressArea;

    public AddCustomerFrame() {
        setTitle("Add New Customer");
        setSize(450, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Add New Customer");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(130, 20, 250, 30);
        add(titleLabel);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(50, 70, 120, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(180, 70, 200, 30);
        add(nameField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 120, 120, 25);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(180, 120, 200, 30);
        add(phoneField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 170, 120, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(180, 170, 200, 30);
        add(emailField);

        JLabel idLabel = new JLabel("ID Proof:");
        idLabel.setBounds(50, 220, 120, 25);
        add(idLabel);

        idProofField = new JTextField();
        idProofField.setBounds(180, 220, 200, 30);
        add(idProofField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 270, 120, 25);
        add(addressLabel);

        addressArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(addressArea);
        scrollPane.setBounds(180, 270, 200, 60);
        add(scrollPane);

        JButton addButton = new JButton("Add Customer");
        addButton.setBounds(100, 360, 130, 35);
        addButton.setBackground(new Color(0, 153, 76));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);
        addButton.setOpaque(true);
        addButton.addActionListener(e -> addCustomer());
        add(addButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(250, 360, 130, 35);
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

    private void addCustomer() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        String idProof = idProofField.getText().trim();
        String address = addressArea.getText().trim();

        if (name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and Phone are required!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String query = "INSERT INTO customers (full_name, phone, email, id_proof, address) " +
                    "VALUES ('" + name + "', '" + phone + "', '" + email + "', '" +
                    idProof + "', '" + address + "')";
            DatabaseConnection.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Customer added successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
