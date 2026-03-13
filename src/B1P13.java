import java.util.*;

/**
 * Book My Stay App
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Demonstrates room allocation, uniqueness enforcement,
 * and inventory synchronization.
 *
 * @author Laxmi
 * @version 6.1
 */


/* ---------------- Reservation ---------------- */

class Reservation {

    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}


/* ---------------- Booking Queue ---------------- */

class BookingRequestQueue {

    Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(String guestName, String roomType) {
        queue.offer(new Reservation(guestName, roomType));
        System.out.println("Booking request added for " + guestName);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}


/* ---------------- Inventory Service ---------------- */

class InventoryService {

    Map<String, Integer> inventory = new HashMap<>();

    public InventoryService() {

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decreaseRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}


/* ---------------- Booking Service ---------------- */

class BookingService {

    InventoryService inventory;

    Set<String> allocatedRoomIds = new HashSet<>();

    Map<String, Set<String>> roomTypeMapping = new HashMap<>();


    public BookingService(InventoryService inventory) {
        this.inventory = inventory;
    }


    public void processBooking(Reservation reservation) {

        String roomType = reservation.roomType;

        if (inventory.getAvailability(roomType) > 0) {

            String roomId = generateRoomId(roomType);

            allocatedRoomIds.add(roomId);

            roomTypeMapping.putIfAbsent(roomType, new HashSet<>());
            roomTypeMapping.get(roomType).add(roomId);

            inventory.decreaseRoom(roomType);

            System.out.println("Reservation Confirmed");
            System.out.println("Guest: " + reservation.guestName);
            System.out.println("Room Type: " + roomType);
            System.out.println("Assigned Room ID: " + roomId);
            System.out.println("-----------------------------");

        } else {

            System.out.println("Sorry " + reservation.guestName +
                    ", No " + roomType + " available.");
            System.out.println("-----------------------------");
        }
    }


    private String generateRoomId(String roomType) {

        String prefix = roomType.substring(0, 2).toUpperCase();
        String roomId;

        do {
            roomId = prefix + (int)(Math.random() * 1000);
        } while (allocatedRoomIds.contains(roomId));

        return roomId;
    }
}


/* ---------------- Main Class ---------------- */

public class B1P13 {

    public static void main(String[] args) {

        System.out.println("Book My Stay App");
        System.out.println("Hotel Booking System v6.1");
        System.out.println("--------------------------------");

        BookingRequestQueue queue = new BookingRequestQueue();
        InventoryService inventory = new InventoryService();
        BookingService bookingService = new BookingService(inventory);


        // Guests submit requests
        queue.addRequest("Alice", "Single Room");
        queue.addRequest("Bob", "Double Room");
        queue.addRequest("Charlie", "Suite Room");
        queue.addRequest("David", "Single Room");


        System.out.println("\nProcessing Booking Requests...\n");


        while (!queue.isEmpty()) {

            Reservation r = queue.getNextRequest();

            bookingService.processBooking(r);
        }
    }
}