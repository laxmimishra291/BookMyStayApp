import java.util.*;

// 🔹 Add-On Service Class
class Service {
    String name;
    double cost;

    Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

// 🔹 Service Manager Class
class AddOnServiceManager {

    // Map: Reservation ID → List of Services
    private Map<String, List<Service>> serviceMap = new HashMap<>();

    // Add service to a reservation
    public void addService(String reservationId, Service service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    // Display services for a reservation
    public void displayServices(String reservationId) {
        List<Service> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        System.out.println("Services for Reservation " + reservationId + ":");
        for (Service s : services) {
            System.out.println(s.name + " -> " + s.cost);
        }
    }

    // Calculate total cost
    public double calculateTotalCost(String reservationId) {
        double total = 0;
        List<Service> services = serviceMap.get(reservationId);

        if (services != null) {
            for (Service s : services) {
                total += s.cost;
            }
        }
        return total;
    }
}

// 🔹 Main Class
public class main {

    public static void main(String[] args) {

        System.out.println("=== Add-On Service Selection ===");

        AddOnServiceManager manager = new AddOnServiceManager();

        // Sample Reservation ID
        String reservationId = "RES101";

        // Guest selects services
        manager.addService(reservationId, new Service("Breakfast", 200));
        manager.addService(reservationId, new Service("Airport Pickup", 500));
        manager.addService(reservationId, new Service("Spa", 800));

        // Display services
        manager.displayServices(reservationId);

        // Total cost
        double total = manager.calculateTotalCost(reservationId);
        System.out.println("Total Add-On Cost: " + total);

        System.out.println("Booking and room allocation remain unchanged.");
    }
}