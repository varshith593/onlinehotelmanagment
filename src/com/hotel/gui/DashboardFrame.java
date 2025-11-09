package com.hotel.gui;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {
        setTitle("Hotel Management Dashboard");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();

        // Room Menu
        JMenu roomMenu = new JMenu("Rooms");
        JMenuItem addRoomItem = new JMenuItem("Add Room");
        JMenuItem viewRoomsItem = new JMenuItem("View Rooms");
        JMenuItem updateRoomItem = new JMenuItem("Update Room Status");

        addRoomItem.addActionListener(e -> new AddRoomFrame().setVisible(true));
        viewRoomsItem.addActionListener(e -> new ViewRoomsFrame().setVisible(true));

        roomMenu.add(addRoomItem);
        roomMenu.add(viewRoomsItem);
        roomMenu.add(updateRoomItem);

        // Customer Menu
        JMenu customerMenu = new JMenu("Customers");
        JMenuItem addCustomerItem = new JMenuItem("Add Customer");
        JMenuItem viewCustomersItem = new JMenuItem("View Customers");

        addCustomerItem.addActionListener(e -> new AddCustomerFrame().setVisible(true));
        viewCustomersItem.addActionListener(e -> new ViewCustomersFrame().setVisible(true));

        customerMenu.add(addCustomerItem);
        customerMenu.add(viewCustomersItem);

        // Booking Menu
        JMenu bookingMenu = new JMenu("Bookings");
        JMenuItem newBookingItem = new JMenuItem("New Booking");
        JMenuItem viewBookingsItem = new JMenuItem("View Bookings");

        newBookingItem.addActionListener(e -> new BookingFrame().setVisible(true));
        viewBookingsItem.addActionListener(e -> new ViewBookingsFrame().setVisible(true));

        bookingMenu.add(newBookingItem);
        bookingMenu.add(viewBookingsItem);

        // Exit Menu
        JMenu exitMenu = new JMenu("Exit");
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });
        exitMenu.add(logoutItem);

        menuBar.add(roomMenu);
        menuBar.add(customerMenu);
        menuBar.add(bookingMenu);
        menuBar.add(exitMenu);
        setJMenuBar(menuBar);

        // Welcome Panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(null);
        welcomePanel.setBackground(new Color(240, 248, 255));
        welcomePanel.setBounds(0, 0, 900, 650);

        JLabel welcomeLabel = new JLabel("Welcome to Hotel Management System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(0, 102, 204));
        welcomeLabel.setBounds(180, 150, 600, 40);
        welcomePanel.add(welcomeLabel);

        JLabel instructionLabel = new JLabel("Use the menu above to manage rooms, customers, and bookings");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionLabel.setBounds(180, 220, 550, 30);
        welcomePanel.add(instructionLabel);

        add(welcomePanel);
        setVisible(true);
    }
}
