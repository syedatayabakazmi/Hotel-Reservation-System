import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {

    private static Hotel hotel = new Hotel();
    private static PaymentProcessor paymentProcessor = new PaymentProcessor();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pre-load some rooms for testing purposes
        hotel.addRoom(new Room("Single", 100.0));
        hotel.addRoom(new Room("Double", 150.0));
        hotel.addRoom(new Room("Suite", 250.0));

        while (true) {
            System.out.println("1. Search available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View reservations by customer name");
            System.out.println("4. View reservation details by ID");
            System.out.println("5. Add a room (Administrator)");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        searchAvailableRooms(scanner);
                        break;
                    case 2:
                        makeReservation(scanner);
                        break;
                    case 3:
                        viewReservationsByCustomer(scanner);
                        break;
                    case 4:
                        viewReservationDetailsById(scanner);
                        break;
                    case 5:
                        addRoom(scanner);
                        break;
                    case 6:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format");
            }
        }
    }

    private static void searchAvailableRooms(Scanner scanner) throws ParseException {
        System.out.print("Enter room category (Single/Double/Suite): ");
        String category = scanner.nextLine();
        System.out.print("Enter start date (yyyy-MM-dd): ");
        Date startDate = dateFormat.parse(scanner.nextLine());
        System.out.print("Enter end date (yyyy-MM-dd): ");
        Date endDate = dateFormat.parse(scanner.nextLine());
        List<Room> availableRooms = hotel.searchAvailableRooms(category, startDate, endDate);
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available in the selected category and date range.");
        } else {
            System.out.println("Available rooms:");
            for (Room room : availableRooms) {
                System.out.println(room);
            }
        }
    }

    private static void makeReservation(Scanner scanner) throws ParseException {
        System.out.print("Enter room category (Single/Double/Suite): ");
        String category = scanner.nextLine();
        System.out.print("Enter start date (yyyy-MM-dd): ");
        Date startDate = dateFormat.parse(scanner.nextLine());
        System.out.print("Enter end date (yyyy-MM-dd): ");
        Date endDate = dateFormat.parse(scanner.nextLine());
        List<Room> availableRooms = hotel.searchAvailableRooms(category, startDate, endDate);
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available in the selected category and date range.");
            return;
        }
        System.out.println("Available rooms:");
        for (Room room : availableRooms) {
            System.out.println(room);
        }
        System.out.print("Enter room ID to book: ");
        String roomId = scanner.nextLine();
        Room roomToBook = null;
        for (Room room : availableRooms) {
            if (room.getId().equals(roomId)) {
                roomToBook = room;
                break;
            }
        }
        if (roomToBook == null) {
            System.out.println("Invalid room ID");
            return;
        }
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter credit card number: ");
        String creditCardNumber = scanner.nextLine();
        if (paymentProcessor.processPayment(creditCardNumber, roomToBook.getPrice())) {
            Reservation reservation = hotel.makeReservation(roomToBook, customerName, startDate, endDate);
            System.out.println("Reservation made: " + reservation);
        } else {
            System.out.println("Payment failed");
        }
    }

    private static void viewReservationsByCustomer(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        List<Reservation> reservations = hotel.getReservationsByCustomer(customerName);
        if (reservations.isEmpty()) {
            System.out.println("No reservations found for this customer.");
        } else {
            for (Reservation res : reservations) {
                System.out.println(res);
            }
        }
    }

    private static void viewReservationDetailsById(Scanner scanner) {
        System.out.print("Enter reservation ID: ");
        String reservationId = scanner.nextLine();
        Reservation reservation = hotel.getReservationById(reservationId);
        if (reservation != null) {
            System.out.println(reservation);
        } else {
            System.out.println("Reservation not found");
        }
    }

    private static void addRoom(Scanner scanner) {
        System.out.print("Enter room category: ");
        String category = scanner.nextLine();
        System.out.print("Enter room price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        Room room = new Room(category, price);
        hotel.addRoom(room);
        System.out.println("Room added: " + room);
    }
}
