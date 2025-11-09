package com.hotel.main;

import com.hotel.gui.LoginFrame;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Launch login screen
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}
