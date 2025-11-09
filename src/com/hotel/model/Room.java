package com.hotel.model;

public class Room {
    private int roomId;
    private String roomNumber;
    private String roomType;
    private String bedType;
    private double pricePerNight;
    private String status;

    public Room(int roomId, String roomNumber, String roomType, String bedType,
                double pricePerNight, String status) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.bedType = bedType;
        this.pricePerNight = pricePerNight;
        this.status = status;
    }

    // Getters and Setters
    public int getRoomId() { return roomId; }
    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public String getBedType() { return bedType; }
    public double getPricePerNight() { return pricePerNight; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
