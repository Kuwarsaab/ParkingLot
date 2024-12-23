import java.util.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Map<Vehicle.VehicleType, Integer> capacities = Map.of(
                Vehicle.VehicleType.BIKE, 10,
                Vehicle.VehicleType.CAR, 10,
                Vehicle.VehicleType.SPORTSCAR, 10,
                Vehicle.VehicleType.TRUCK, 5,
                Vehicle.VehicleType.BUS, 5
        );

        ParkingLot parkingLot = new ParkingLot(2, capacities);
        parkingLot.configureCostStrategy(Map.of(
                Vehicle.VehicleType.BIKE, 10.0,
                Vehicle.VehicleType.CAR, 20.0,
                Vehicle.VehicleType.SPORTSCAR, 20.0,
                Vehicle.VehicleType.BUS, 30.0,
                Vehicle.VehicleType.TRUCK, 30.0
        ), "INR");

        String token1 = parkingLot.addVehicle(new Vehicle("KA-01-AA-1111", "Red", Vehicle.VehicleType.CAR));
        String token2 = parkingLot.addVehicle(new Vehicle("KA-01-BB-2222", "Blue", Vehicle.VehicleType.CAR));

        parkingLot.displayStatus();

        parkingLot.removeVehicle(token2);
        parkingLot.displayStatus();
    }
}