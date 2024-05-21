import java.util.UUID;

public class Room {
    private String id;
    private String category;
    private double price;
    private boolean isAvailable;

    public Room(String category, double price) {
        this.id = UUID.randomUUID().toString();
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
