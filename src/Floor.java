import java.util.*;

public class Floor {
    private int floorNumber;
    private Map<Vehicle.VehicleType, List<VehicleSpace>> spacesByType;

    public Floor(int floorNumber, Map<Vehicle.VehicleType, Integer> capacities) {
        this.floorNumber = floorNumber;
        spacesByType = new HashMap<>();
        for (Map.Entry<Vehicle.VehicleType, Integer> entry : capacities.entrySet()) {
            Vehicle.VehicleType type = entry.getKey();
            int capacity = entry.getValue();
            List<VehicleSpace> spaces = new ArrayList<>();
            for (int i = 0; i < capacity; i++) {
                spaces.add(new VehicleSpace(i + 1, type));
            }
            spacesByType.put(type, spaces);
        }
    }

    public Optional<VehicleSpace> parkVehicle(Vehicle vehicle) {
        List<VehicleSpace> spaces = spacesByType.get(vehicle.getType());
        for (VehicleSpace space : spaces) {
            if (space.isAvailable()) {
                space.parkVehicle(vehicle);
                return Optional.of(space);
            }
        }
        return Optional.empty();
    }

    public Optional<Vehicle> removeVehicle(String registrationNumber) {
        for (List<VehicleSpace> spaces : spacesByType.values()) {
            for (VehicleSpace space : spaces) {
                if (!space.isAvailable() && space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    return Optional.of(space.removeVehicle());
                }
            }
        }
        return Optional.empty();
    }

    public Map<Vehicle.VehicleType, Long> checkAvailability() {
        Map<Vehicle.VehicleType, Long> availability = new HashMap<>();
        for (Map.Entry<Vehicle.VehicleType, List<VehicleSpace>> entry : spacesByType.entrySet()) {
            long availableSpaces = entry.getValue().stream().filter(VehicleSpace::isAvailable).count();
            availability.put(entry.getKey(), availableSpaces);
        }
        return availability;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
