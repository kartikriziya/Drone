package de.frauas.dronesimulation.app.dronelist;

import org.json.JSONObject;
import org.json.JSONArray;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.logging.*;

public class ParseDroneList {
    private static final Logger LOG = Logger.getLogger(ParseDroneList.class.getName());

    static {
        Handler fileHandler;
        Handler consoleHandler;
        try {
            // File handler for logging to a file
            fileHandler = new FileHandler("./ParseDroneListLogFile.log");
            LOG.addHandler(fileHandler);
            Formatter xmlFormat = new XMLFormatter();
            fileHandler.setFormatter(xmlFormat);
            fileHandler.setLevel(Level.ALL);
        } catch (Exception e) {
            // Exception handling
            LOG.log(Level.WARNING, "Error occur in FileHandler.", e);
        }

        // Console handler for logging to the console
        consoleHandler = new ConsoleHandler();
        LOG.addHandler(consoleHandler);
        consoleHandler.setLevel(Level.WARNING);
        Formatter consoleFormat = new SimpleFormatter();
        consoleHandler.setFormatter(consoleFormat);
    }

    public static void parseJsonResponse(String input, List<DroneList> droneInstanceList) {
        try {
            // Create a JSONObject with the response
            JSONObject wholeFile = new JSONObject(input);

            // Get the "results" array from the JSONObject
            JSONArray jsonFile = wholeFile.getJSONArray("results");
            // Loop through the array
            for (int i = 0; i < jsonFile.length(); i++) {
                // Get each object in the array
                JSONObject o = jsonFile.getJSONObject(i);
                // If the object has "carriage_type" and "carriage_weight"
                if (o.has("carriage_type") && o.has("carriage_weight")) {
                    // Get the values of "carriage_type" and "carriage_weight"
                    String carriageType = o.getString("carriage_type");
                    int carriageWeight = o.getInt("carriage_weight");
                    int id = o.getInt("id");
                    String created = o.getString("created");
                    String serialNumber = o.getString("serialnumber");
                    String droneTypeUri = o.getString("dronetype");

                    DroneList drone = new DroneList(id, droneTypeUri, created, serialNumber, carriageWeight,
                            carriageType);
                    droneInstanceList.add(drone);
                }
            }
        } catch (Exception e) {
            LOG.log(Level.WARNING, "An error occurred while parsing the drone list: " + e.getMessage(), e);
        }
    }
}