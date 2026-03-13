import java.util.LinkedList;
import java.util.Queue;

/**
 * Book My Stay App
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Demonstrates fair handling of booking requests using Queue (FIFO).
 *
 * @author Laxmi
 * @version 5.1
 */

// ---------------- Reservation Class ----------------

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

// ---------------- Booking Request Queue ----------------

class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add request to queue
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName());
    }

    // Display queue
    public void displayRequests() {

        System.out.println("\nCurrent Booking Requests (FIFO Order):");

        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
    }
}

// ---------------- Main Class ----------------

public class B1P13 {

    public static void main(String[] args) {

        System.out.println("Book My Stay App");
        System.out.println("Hotel Booking System v5.1");
        System.out.println("---------------------------");

        BookingRequestQueue queue = new BookingRequestQueue();

        // Guests submit booking requests
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Double Room"));
        queue.addRequest(new Reservation("Charlie", "Suite Room"));

        // Show queue
        queue.displayRequests();
    }
}