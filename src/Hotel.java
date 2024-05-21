import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> searchAvailableRooms(String category, Date startDate, Date endDate) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getCategory().equals(category) && room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Reservation makeReservation(Room room, String customerName, Date startDate, Date endDate) {
        if (room.isAvailable()) {
            Reservation reservation = new Reservation(room, customerName, startDate, endDate);
            reservations.add(reservation);
            room.setAvailable(false);
            return reservation;
        }
        return null;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Reservation> getReservationsByCustomer(String customerName) {
        List<Reservation> customerReservations = new ArrayList<>();
        for (Reservation res : reservations) {
            if (res.getCustomerName().equals(customerName)) {
                customerReservations.add(res);
            }
        }
        return customerReservations;
    }

    public Reservation getReservationById(String reservationId) {
        for (Reservation res : reservations) {
            if (res.getId().equals(reservationId)) {
                return res;
            }
        }
        return null;
    }
}
