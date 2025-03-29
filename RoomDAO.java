import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDAO {
    private Connection connection;

    public RoomDAO() {
        try {
            connection = DatabaseConnector.getConnection();
            if (connection == null) {
                throw new SQLException("Failed to establish connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addRoom(Room room) {
        String query = "INSERT INTO Room (hotel_id, room_number, type, price, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, room.getHotelId());
            statement.setInt(2, room.getRoomNumber());
            statement.setString(3, room.getType());
            statement.setDouble(4, room.getPrice());
            statement.setString(5, room.getStatus());
            statement.executeUpdate();
            System.out.println("Room added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Room getRoomById(int id) {
        String query = "SELECT * FROM Room WHERE room_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            statement.setInt(1, id);

            if (resultSet.next()) {
                return new Room(resultSet.getInt("room_id"),
                        resultSet.getInt("hotel_id"),
                        resultSet.getInt("room_number"),
                        resultSet.getString("type"),
                        resultSet.getDouble("price"),
                        resultSet.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateRoom(Room room) {
        String query = "UPDATE Room SET hotel_id = ?, room_number = ?, type = ?, price = ?, status = ? WHERE room_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, room.getHotelId());
            statement.setInt(2, room.getRoomNumber());
            statement.setString(3, room.getType());
            statement.setDouble(4, room.getPrice());
            statement.setString(5, room.getStatus());
            statement.setInt(6, room.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Room updated successfully.");
            } else {
                System.out.println("Room not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoom(int id) {
        String query = "DELETE FROM Room WHERE room_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Room deleted successfully.");
            } else {
                System.out.println("Room not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
