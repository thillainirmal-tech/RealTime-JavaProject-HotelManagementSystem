import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class HotelDAO {

    private Connection connection;

    // ✅ Declare SQLException in the constructor signature
    public HotelDAO() throws SQLException {
        connection = DatabaseConnector.getConnection();
    }

    // ✅ Add hotel
    public void addHotel(Hotel hotel) {
        String query = "INSERT INTO hotels (name, location) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getLocation());
            statement.executeUpdate();
            System.out.println("✅ Hotel added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Get hotel by ID
    public Hotel getHotelById(int id) {
        String query = "SELECT * FROM hotels WHERE hotel_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Hotel(
                        resultSet.getInt("hotel_id"),
                        resultSet.getString("name"),
                        resultSet.getString("location")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Update hotel
    public void updateHotel(Hotel hotel) {
        String query = "UPDATE hotels SET name = ?, location = ? WHERE hotel_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getLocation());
            statement.setInt(3, hotel.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Hotel updated successfully.");
            } else {
                System.out.println("❌ Hotel not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Delete hotel
    public void deleteHotel(int id) {
        String query = "DELETE FROM hotels WHERE hotel_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Hotel deleted successfully.");
            } else {
                System.out.println("❌ Hotel not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
