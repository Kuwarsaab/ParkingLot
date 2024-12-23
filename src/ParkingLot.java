import java.time.Duration;
import java.util.*;
import java.time.LocalDateTime;

public class ParkingLot {
    private List<Floor> floors;
    private CostStrategy costStrategy;
    private Map<String, Ticket> tickets;

    public ParkingLot(int numberOfFloors, Map<Vehicle.VehicleType, Integer> capacities) {
        floors = new ArrayList<>();
        for (int i = 0; i < numberOfFloors; i++) {
            floors.add(new Floor(i + 1, capacities));
        }
        tickets = new HashMap<>();
    }

    public void configureCostStrategy(Map<Vehicle.VehicleType, Double> rates, String currency) {
        this.costStrategy = new CostStrategy(rates, currency);
    }

    public String addVehicle(Vehicle vehicle) {
        for (Floor floor : floors) {
            Optional<VehicleSpace> space = floor.parkVehicle(vehicle);
            if (space.isPresent()) {
                Ticket ticket = new Ticket(vehicle.getRegistrationNumber(), floor.getFloorNumber(), space.get().getType(), LocalDateTime.now());
                tickets.put(ticket.getTokenId(), ticket);
                return ticket.getTokenId();
            }
        }
        throw new IllegalStateException("Parking Lot is full for vehicle type: " + vehicle.getType());
    }

    public void removeVehicle(String tokenId) {
        if (!tickets.containsKey(tokenId)) {
            throw new IllegalArgumentException("Invalid token id: " + tokenId);
        }
        Ticket ticket = tickets.remove(tokenId);
        Vehicle removedVehicle = floors.get(ticket.getFloorNumber() - 1).removeVehicle(ticket.getRegistrationNumber()).orElseThrow();
        long hoursParked = Duration.between(ticket.getEntryTime(), LocalDateTime.now()).toHours();
        if(hoursParked<1) hoursParked = 1;
        double cost = costStrategy.calculateCost(ticket.getVehicleType(), hoursParked);
        System.out.println("Vehicle Removed: " + removedVehicle + " | Cost: " + cost + " " + costStrategy.getCurrency());
    }

    public void displayStatus() {
        for (Floor floor : floors) {
            System.out.println("Floor " + floor.getFloorNumber() + ": " + floor.checkAvailability());
        }
    }
}
