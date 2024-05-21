import java.util.Date;
import java.util.UUID;

public class Reservation {
    private String id;
    private Room room;
    private String customerName;
    private Date startDate;
    private Date endDate;

    public Reservation(Room room, String customerName, Date startDate, Date endDate) {
        this.id = UUID.randomUUID().toString();
        this.room = room;
        this.customerName = customerName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", room=" + room +
                ", customerName='" + customerName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
