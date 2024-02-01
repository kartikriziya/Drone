package de.frauas.dronesimulation.app.dronedynamics;

import java.util.logging.*;
import java.io.IOException;
import org.json.JSONObject;
import de.frauas.dronesimulation.app.dronelist.DroneList;
import org.json.JSONArray;
import java.util.List;

public class ParseDroneDynamics {
    private static final Logger LOG = Logger.getLogger(ParseDroneDynamics.class.getName());

    static {
        Handler fileHandler;
        Handler consoleHandler;
        try {
            // Creating a file handler to write log to a file
            fileHandler = new FileHandler("./ParseDynamicsLogFile.log");
            LOG.addHandler(fileHandler);
            Formatter xmlFormat = new XMLFormatter();
            fileHandler.setFormatter(xmlFormat);
            fileHandler.setLevel(Level.ALL);
        } catch (IOException e) {
            // Log any IO exceptions
            LOG.log(Level.WARNING, "Error occur in FileHandler.", e);
        }

        // Creating a console handler to write log to the console
        consoleHandler = new ConsoleHandler();
        LOG.addHandler(consoleHandler);
        consoleHandler.setLevel(Level.WARNING);
        Formatter consoleFormat = new SimpleFormatter();
        consoleHandler.setFormatter(consoleFormat);
    }

    public static void parseJsonResponse(String input, List<DroneList> listOfDrones,
            List<DroneDynamics> listOfDronesDynamicTimeStamp) {
        try {
            // Convert the input string to a JSON object
            JSONObject wholeFile = new JSONObject(input);

            // Loop through each drone in the list
            for (DroneList drone : listOfDrones) {
                int DroneId = drone.getId();
                String DroneUri = "http://dronesim.facets-labs.com/api/drones/" + DroneId + "/?format=json";

                // Get the "results" array from the JSON object
                JSONArray jsonFileDynamic = wholeFile.getJSONArray("results");

                // Loop through each object in the "results" array
                for (int i = 0; i < jsonFileDynamic.length(); i++) {
                    JSONObject object = jsonFileDynamic.getJSONObject(i);

                    // If the object has a "speed" field and its "drone" field matches the current
                    // drone's URI
                    if (object.has("speed") && object.getString("drone").equals(DroneUri)) {

                        // Extract the data from the object
                        String droneLink = object.getString("drone");
                        String timestamp = object.getString("timestamp");
                        int speed = object.getInt("speed");
                        String alignRoll = object.getString("align_roll");
                        String alignPitch = object.getString("align_pitch");
                        String alignYaw = object.getString("align_roll");
                        String longitude = object.getString("longitude");
                        String latitude = object.getString("latitude");
                        int batteryStatus = object.getInt("battery_status");
                        String lastSeen = object.getString("last_seen");
                        String status = object.getString("status");

                        // Calculate the battery percentage
                        int batteryPercentage = (batteryStatus * 100) / drone.getDroneType().getBatterycapacity();

                        // Create a new DroneDynamics object with the extracted data
                        DroneDynamics droneDynamics = new DroneDynamics(droneLink, timestamp, speed, alignRoll,
                                alignPitch,
                                alignYaw, longitude, latitude, batteryStatus, lastSeen, status, batteryPercentage);

                        // Set the drone's dynamics to the new DroneDynamics object
                        drone.setDroneDynamics(droneDynamics);
                    }
                }
            }

        } catch (Exception e) {
            // Log any exceptions
            LOG.log(Level.WARNING, "An error occurred while parsing the drone dynamics: " + e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public static void parseJsonResponseForDate(String input, List<DroneDynamics> listOfDronesDynamicTimeStamp) {
        try {
            // Convert the input string to a JSON object
            JSONObject wholeFile = new JSONObject(input);

            // Get the "results" array from the JSON object
            JSONArray jsonFileDynamic = wholeFile.getJSONArray("results");

            // Get the first object from the "results" array
            JSONObject object = jsonFileDynamic.getJSONObject(0);

            // Extract the data from the object
            String droneLink = object.getString("drone");
            String timestamp = object.getString("timestamp");
            int speed = object.getInt("speed");
            String alignRoll = object.getString("align_roll");
            String alignPitch = object.getString("align_pitch");
            String alignYaw = object.getString("align_roll");
            String longitude = object.getString("longitude");
            String latitude = object.getString("latitude");
            int batteryStatus = object.getInt("battery_status");
            String lastSeen = object.getString("last_seen");
            String status = object.getString("status");

            // Create a new DroneDynamics object with the extracted data
            DroneDynamics droneDynamics = new DroneDynamics(droneLink, timestamp, speed, alignRoll,
                    alignPitch,
                    alignYaw, longitude, latitude, batteryStatus, lastSeen, status, 0);

            // Add the new DroneDynamics object to the list
            listOfDronesDynamicTimeStamp.add(droneDynamics);

        } catch (Exception e) {
            // Log any exceptions
            LOG.log(Level.WARNING, "An error occurred while parsing the drone dynamics: " + e.getMessage(), e);
            e.printStackTrace();
        }
    }
}