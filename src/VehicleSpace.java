public class VehicleSpace {
    private int spaceNumber;
    private boolean isAvailable;
    private Vehicle.VehicleType type;
    private Vehicle vehicle;

    public VehicleSpace(int spaceNumber, Vehicle.VehicleType type) {
        this.spaceNumber = spaceNumber;
        this.type = type;
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isAvailable = false;
    }

    public Vehicle removeVehicle() {
        Vehicle temp = this.vehicle;
        this.vehicle = null;
        this.isAvailable = true;
        return temp;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Vehicle.VehicleType getType() {
        return type;
    }
}
