import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Main {
    private HotelDAO hotelDAO;
    private RoomDAO roomDAO;
    private GuestDAO guestDAO;
    private ReservationDAO reservationDAO;

    public Main() {
        try {
            hotelDAO = new HotelDAO();
            roomDAO = new RoomDAO();
            guestDAO = new GuestDAO();
            reservationDAO = new ReservationDAO();
            createAndShowGUI();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error initializing DAOs: " + e.getMessage(),
                    "Initialization Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Hotel Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 1));

        // Add Hotel Button
        JButton addHotelButton = new JButton("Add Hotel");
        addHotelButton.addActionListener(e -> {
            try {
                Hotel hotel = new Hotel(1, "Hotel A", "City A");
                hotelDAO.addHotel(hotel);
                JOptionPane.showMessageDialog(frame, "Hotel added successfully.");
            } catch (Exception ex) {   // Catching Exception instead of SQLException
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error adding hotel: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        frame.add(addHotelButton);

        // Add Room Button
        JButton addRoomButton = new JButton("Add Room");
        addRoomButton.addActionListener(e -> {
            try {
                Room room = new Room(1, 1, 101, "Single", 100.00, "Available");
                roomDAO.addRoom(room);
                JOptionPane.showMessageDialog(frame, "Room added successfully.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error adding room: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        frame.add(addRoomButton);

        // Add Guest Button
        JButton addGuestButton = new JButton("Add Guest");
        addGuestButton.addActionListener(e -> {
            try {
                Guest guest = new Guest(1, "John Doe", "john@example.com", "1234567890");
                guestDAO.addGuest(guest);
                JOptionPane.showMessageDialog(frame, "Guest added successfully.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error adding guest: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        frame.add(addGuestButton);

        // Add Reservation Button
        JButton addReservationButton = new JButton("Add Reservation");
        addReservationButton.addActionListener(e -> {
            try {
                Reservation reservation = new Reservation(1, 1, 1, new Date(), new Date());
                reservationDAO.addReservation(reservation);
                JOptionPane.showMessageDialog(frame, "Reservation added successfully.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error adding reservation: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        frame.add(addReservationButton);

        // Retrieve Data Button
        JButton retrieveDataButton = new JButton("Retrieve Data");
        retrieveDataButton.addActionListener(e -> {
            try {
                Hotel retrievedHotel = hotelDAO.getHotelById(1);
                Room retrievedRoom = roomDAO.getRoomById(1);
                Guest retrievedGuest = guestDAO.getGuestById(1);
                Reservation retrievedReservation = reservationDAO.getReservationById(1);

                String data = "Retrieved Hotel: " + retrievedHotel + "\n"
                        + "Retrieved Room: " + retrievedRoom + "\n"
                        + "Retrieved Guest: " + retrievedGuest + "\n"
                        + "Retrieved Reservation: " + retrievedReservation;

                JOptionPane.showMessageDialog(frame, data);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error retrieving data: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        frame.add(retrieveDataButton);

        // Set frame properties
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Main();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Application error: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
