# Hospitality Management System

## 📄 Project Overview
The **Hospitality Management System** is a Java-based application designed to manage hotel operations efficiently. It provides functionalities for managing hotels, rooms, guests, and reservations. The project uses **MySQL** for data storage and **Swing GUI** for user interaction.

## 🚀 Features
### Hotel Management
- Add/Edit/Delete hotels
- View hotel details

### Room Management
- Add/Edit/Delete rooms
- View room details

### Guest Management
- Add/Edit/Delete guests
- View guest details

### Reservation Management
- Make new reservations
- View reservation details
- Check room availability

## 🛠️ Tech Stack
- **Language:** Java (Core)
- **Database:** MySQL
- **GUI:** Swing
- **IDE:** IntelliJ IDEA

## 💾 Database Schema
- `Hotels`: Stores hotel details
- `Rooms`: Contains room information
- `Guests`: Holds guest information
- `Reservations`: Tracks guest reservations

## ⚙️ Installation and Setup
1. **Clone the repository:**
```bash
git clone thillainirmal-tech/RealTime-JavaProject-HotelManagementSystem.git
```
2. **Import the project into IntelliJ IDEA**
3. **Install MySQL and create the database:**
```sql
CREATE DATABASE hospitalitydb;
```
4. **Run the SQL script to create tables**
5. **Update database connection settings** in `DatabaseConnector.java`:
```java
private static final String URL = "jdbc:mysql://localhost:3306/hospitalitydb";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```
6. **Run the project**

## 🚦 Usage
- Launch the application
- Use the GUI to manage hotels, rooms, guests, and reservations

## 📝 Future Enhancements
- Add authentication for admin users
- Include reports and analytics
- Improve the GUI design

## 👥 Contributors
Thillai Nirmal K
(https://github.com/thillainirmal-tech)

## 📜 License
This project is licensed under the MIT License.
