package de.frauas.dronesimulation.app.dronedynamics;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DroneDynamics {
    private String drone;
    private LocalDateTime timestamp;
    private int speed;
    private String alignRoll;
    private String alignPitch;
    private String alignYaw;
    private String longitude;
    private String latitude;
    private int batteryStatus;
    private int batteryPercentage;
    private LocalDateTime lastSeen;
    private String status;

    // Constructor for DroneDynamics class
    public DroneDynamics(String _drone, String _timestamp, int _speed, String _alignRoll, String _alignPitch,
            String _alignYaw, String _longitude, String _latitude, int _batteryStatus, String _lastSeen,
            String _status, int _batteryPercentage) {
        // Initialize drone URI
        this.drone = _drone;
        // Initialize and format timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(_timestamp.toString(), formatter);
        this.timestamp = zonedDateTime.toLocalDateTime();
        this.speed = _speed;
        this.alignRoll = _alignRoll;
        this.alignPitch = _alignPitch;
        this.alignYaw = _alignYaw;
        this.longitude = _longitude;
        this.latitude = _latitude;
        this.batteryStatus = _batteryStatus;
        // Initialize and format last seen timestamp
        zonedDateTime = ZonedDateTime.parse(_lastSeen.toString(), formatter);
        this.lastSeen = zonedDateTime.toLocalDateTime();
        this.status = _status;
        this.batteryPercentage = _batteryPercentage;
    }

    public String getDrone() {
        return drone;
    }

    public void setDrone(String drone) {
        this.drone = drone;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getAlignRoll() {
        return alignRoll;
    }

    public void setAlignRoll(String alignRoll) {
        this.alignRoll = alignRoll;
    }

    public String getAlignPitch() {
        return alignPitch;
    }

    public void setAlignPitch(String alignPitch) {
        this.alignPitch = alignPitch;
    }

    public String getAlignYaw() {
        return alignYaw;
    }

    public void setAlignYaw(String alignYaw) {
        this.alignYaw = alignYaw;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(int batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBatteryPercentage(int batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public int getBatteryPercentage() {
        return batteryPercentage;
    }

    public void printStatus() {
        System.out.println("Drone Dynamics: ");
        System.out.println(
                "\n" + drone + "\t" + status + "\t" + timestamp + "\t" + speed + "\t" +
                        alignRoll
                        + "\t" + alignPitch + "\t" + alignYaw + "\t" + longitude + "\t" + latitude + "\t"
                        + batteryStatus + "\t" + lastSeen + "\n" + " Battery Percentage:  " + batteryPercentage + "\n");
    }

}
