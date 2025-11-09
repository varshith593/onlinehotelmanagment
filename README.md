# Online Hotel Management System

A desktop application built with **Java Swing** and **MySQL** database for managing hotel operations including rooms, customers, and bookings.

---

## Features

- **User Authentication**: Secure login system for hotel staff
- **Room Management**: Add, view, and update room details
- **Customer Management**: Store and manage customer information
- **Booking System**: Create and manage room reservations
- **Real-time Status**: View room availability and booking status
- **Automatic Billing**: Calculate charges based on stay duration

---

## Technologies Used

- **Java 11+** (Programming Language)
- **Java Swing** (GUI Framework)
- **MySQL 8.0+** (Database)
- **JDBC** (Database Connectivity)
- **IntelliJ IDEA** (IDE)

---

## Required Installations

### Must Have:
1. **Java Development Kit (JDK)** - Version 11 or higher
2. **MySQL Server** - Version 8.0 or higher
3. **IntelliJ IDEA** - Community Edition (free) or Ultimate
4. **MySQL Connector/J** - Version 9.x (JDBC driver JAR file)

### Optional:
- **Homebrew** (for macOS only) - Package manager to install MySQL easily

---

## Setup Instructions

### 1. Install MySQL Connector/J

**Option 1: Direct Download**
- Visit: https://dev.mysql.com/downloads/connector/j/
- Select **"Platform Independent"** from dropdown
- Download the **ZIP Archive**
- Extract and locate `mysql-connector-j-9.5.0.jar`

**Option 2: Maven (In IntelliJ IDEA)**
- After cloning the project, open in IntelliJ IDEA
- Go to **Project Structure → Libraries**
- Click **Add (+) → From Maven**
- Search for: `com.mysql:mysql-connector-j:9.5.0`
- Click OK to download

> **Note**: This JAR file allows the Java application to connect to MySQL database

### 2. Configure Database Connection

- Open `src/com/hotel/database/DatabaseConnection.java`
- Update the MySQL password:
- search for this line and replace 123456789 to your sql password;
- private static final String PASSWORD = "123456789";
- in code there is 123456789 chnage it to your sql password

### 3. Run the Application

- Open `src/com/hotel/main/Main.java`
- Right-click and select **Run 'Main.main()'**

### 4. Login Credentials

**Default Admin Account:**
- **Username**: `admin`
- **Password**: `admin123`

---

## Project Structure

HotelManagementSystem/
├── src/
│   └── com/
│       └── hotel/
│           ├── database/
│           │   └── DatabaseConnection.java
│           ├── model/
│           │   └── Room.java
│           ├── gui/
│           │   ├── LoginFrame.java
│           │   ├── DashboardFrame.java
│           │   ├── AddRoomFrame.java
│           │   ├── ViewRoomsFrame.java
│           │   ├── AddCustomerFrame.java
│           │   ├── ViewCustomersFrame.java
│           │   ├── BookingFrame.java
│           │   └── ViewBookingsFrame.java
│           └── main/
│               └── Main.java
├── lib/
│   └── mysql-connector-j-9.5.0.jar
└── README.md
---

## Database Schema

The application uses four main tables:
- **users** - Store admin and staff credentials
- **rooms** - Room details and availability
- **customers** - Customer information
- **bookings** - Reservation records

---
B.VARSHITH NAIK(IIT2024151),MANOJ KUMAR(IIT2024148)
---
---















