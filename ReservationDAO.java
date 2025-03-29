import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    private Connection connection;

    public ReservationDAO() {
        try {
            connection = DatabaseConnector.getConnection();
            if (connection == null) {
                throw new SQLException("Failed to establish connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addReservation(Reservation reservation) {
        String query = "INSERT INTO Reservation (room_id, guest_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservation.getRoomId());
            statement.setInt(2, reservation.getGuestId());
            statement.setDate(3, new java.sql.Date(reservation.getCheckInDate().getTime()));
            statement.setDate(4, new java.sql.Date(reservation.getCheckOutDate().getTime()));

            statement.executeUpdate();
            System.out.println("Reservation added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Reservation getReservationById(int id) {
        String query = "SELECT * FROM Reservation WHERE reservation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            statement.setInt(1, id);

            if (resultSet.next()) {
                return new Reservation(
                        resultSet.getInt("reservation_id"),
                        resultSet.getInt("room_id"),
                        resultSet.getInt("guest_id"),
                        resultSet.getDate("check_in_date"),
                        resultSet.getDate("check_out_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservation";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                reservations.add(new Reservation(
                        resultSet.getInt("reservation_id"),
                        resultSet.getInt("room_id"),
                        resultSet.getInt("guest_id"),
                        resultSet.getDate("check_in_date"),
                        resultSet.getDate("check_out_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public void updateReservation(Reservation reservation) {
        String query = "UPDATE Reservation SET room_id = ?, guest_id = ?, check_in_date = ?, check_out_date = ? WHERE reservation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservation.getRoomId());
            statement.setInt(2, reservation.getGuestId());
            statement.setDate(3, new java.sql.Date(reservation.getCheckInDate().getTime()));
            statement.setDate(4, new java.sql.Date(reservation.getCheckOutDate().getTime()));
            statement.setInt(5, reservation.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Reservation updated successfully.");
            } else {
                System.out.println("Reservation not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReservation(int id) {
        String query = "DELETE FROM Reservation WHERE reservation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Reservation deleted successfully.");
            } else {
                System.out.println("Reservation not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
