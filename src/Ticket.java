import java.time.LocalDateTime;
import java.util.*;


public class Ticket {
    private String tokenId;
    private String registrationNumber;
    private int floorNumber;
    private Vehicle.VehicleType vehicleType;
    private LocalDateTime entryTime;

    public Ticket(String registrationNumber, int floorNumber, Vehicle.VehicleType vehicleType, LocalDateTime entryTime) {
        this.tokenId = UUID.randomUUID().toString();
        this.registrationNumber = registrationNumber;
        this.floorNumber = floorNumber;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
    }

    public String getTokenId() {
        return tokenId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Vehicle.VehicleType getVehicleType() {
        return vehicleType;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}
