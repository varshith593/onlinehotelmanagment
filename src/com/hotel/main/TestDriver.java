package com.hotel.main;

public class TestDriver {
    public static void main(String[] args) {
        System.out.println("Testing driver availability...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✓✓✓ SUCCESS! MySQL Driver found!");
        } catch (ClassNotFoundException e) {
            System.out.println("✗✗✗ FAILED! Driver NOT found!");
            System.out.println("JAR is not properly added to classpath");
            e.printStackTrace();
        }
    }
}
