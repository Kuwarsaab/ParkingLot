import java.util.*;

public class CostStrategy {
    private Map<Vehicle.VehicleType, Double> hourlyRates;
    private String currency;

    public CostStrategy(Map<Vehicle.VehicleType, Double> rates, String currency) {
        this.hourlyRates = rates;
        this.currency = currency;
    }

    public double calculateCost(Vehicle.VehicleType type, long hours) {
        return hourlyRates.getOrDefault(type, 0.0) * hours;
    }

    public String getCurrency() {
        return currency;
    }
}
