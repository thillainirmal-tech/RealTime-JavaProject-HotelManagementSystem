import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestDAO {
    private Connection connection;

    public GuestDAO() {
        try {
            connection = DatabaseConnector.getConnection();
            if (connection == null) {
                throw new SQLException("Failed to establish connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGuest(Guest guest) {
        String query = "INSERT INTO Guest (name, email, phone) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, guest.getName());
            statement.setString(2, guest.getEmail());
            statement.setString(3, guest.getPhone());

            statement.executeUpdate();
            System.out.println("Guest added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Guest getGuestById(int id) {
        String query = "SELECT * FROM Guest WHERE guest_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            statement.setInt(1, id);

            if (resultSet.next()) {
                return new Guest(
                        resultSet.getInt("guest_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateGuest(Guest guest) {
        String query = "UPDATE Guest SET name = ?, email = ?, phone = ? WHERE guest_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, guest.getName());
            statement.setString(2, guest.getEmail());
            statement.setString(3, guest.getPhone());
            statement.setInt(4, guest.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Guest updated successfully.");
            } else {
                System.out.println("Guest not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGuest(int id) {
        String query = "DELETE FROM Guest WHERE guest_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Guest deleted successfully.");
            } else {
                System.out.println("Guest not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
