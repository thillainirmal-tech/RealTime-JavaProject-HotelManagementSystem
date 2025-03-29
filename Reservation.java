import java.util.Date;

public class Reservation {
    private int id;
    private int roomId;
    private int guestId;
    private Date checkInDate;
    private Date checkOutDate;
    public Reservation(int id, int roomId, int guestId, Date checkInDate, Date
            checkOutDate) {
        this.id = id;
        this.roomId = roomId;
        this.guestId = guestId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    public int getGuestId() {
        return guestId;
    }
    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
    public Date getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }
    public Date getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}

