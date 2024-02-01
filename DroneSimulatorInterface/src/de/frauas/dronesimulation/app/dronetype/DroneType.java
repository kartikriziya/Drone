package de.frauas.dronesimulation.app.dronetype;

public class DroneType {
    private int typeId;
    private String manufacturer;
    private String typeName;
    private int weight;
    private int maxSpeed;
    private int batteryCapacity;
    private int controlRange;
    private int maxCarriage;
    String DroneTypeUri;

    public DroneType(int _typeId, String _manufacturer, String _typeName, int _weight, int _maxSpeed,
            int _batteryCapacity, int _controlRange, int _maxCarriage) {
        this.typeId = _typeId;
        this.manufacturer = _manufacturer;
        this.typeName = _typeName;
        this.weight = _weight;
        this.maxSpeed = _maxSpeed;
        this.batteryCapacity = _batteryCapacity;
        this.controlRange = _controlRange;
        this.maxCarriage = _maxCarriage;
        this.DroneTypeUri = "http://dronesim.facets-labs.com/api/dronetypes/" + _typeId + "/?format=json";
    }
    // Getters and setters for each field

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int _typeId) {
        this.typeId = _typeId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String _manufacturer) {
        this.manufacturer = _manufacturer;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String _typeName) {
        this.typeName = _typeName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int _weight) {
        this.weight = _weight;
    }

    public int getMax_speed() {
        return maxSpeed;
    }

    public void setMax_speed(int _maxSpeed) {
        this.maxSpeed = _maxSpeed;
    }

    public int getBatterycapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int _batteryCapacity) {
        this.batteryCapacity = _batteryCapacity;
    }

    public int getControlRange() {
        return controlRange;
    }

    public void setControlRange(int _controlRange) {
        this.controlRange = _controlRange;
    }

    public int getMaxCarriage() {
        return maxCarriage;
    }

    public void setMaxCarriage(int _maxCarriage) {
        this.maxCarriage = _maxCarriage;
    }

    public void setDroneTypeUri(String _DroneTypeUri) {
        this.DroneTypeUri = _DroneTypeUri;
    }

    public String getDroneTypeUri() {
        return DroneTypeUri;
    }

    public void printStatus() {
        System.out.println("Drone Type: ");
        System.out.println(
                "\n" + typeId + "\t" + manufacturer
                        + "\t" + typeName + "\t" + weight + "\t" + maxSpeed + "Km/h)" + "\t"
                        + batteryCapacity + "\t" + controlRange + "\t" +
                        maxCarriage
                        + "g" + "\n");
    }
}
