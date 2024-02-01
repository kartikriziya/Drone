package de.frauas.dronesimulation.app.dronetype;

import org.json.JSONObject;
import de.frauas.dronesimulation.app.dronelist.DroneList;
import java.util.logging.*;

public class ParseDroneType {
    // Logger for this class
    private static final Logger LOG = Logger.getLogger(ParseDroneType.class.getName());

    static {
        Handler fileHandler;
        Handler consoleHandler;
        try {
            // File handler for logging to a file
            fileHandler = new FileHandler("./ParseDroneTypeLogFile.log");
            // Add the file handler to the logger
            LOG.addHandler(fileHandler);
            // Set the formatter for the file handler to XML
            Formatter xmlFormat = new XMLFormatter();
            fileHandler.setFormatter(xmlFormat);
            // Log all levels to the file
            fileHandler.setLevel(Level.ALL);
        } catch (Exception e) {
            // Log any exceptions that occur with the file handler
            LOG.log(Level.WARNING, "Error occur in FileHandler.", e);
        }

        // Console handler for logging to the console
        consoleHandler = new ConsoleHandler();
        // Add the console handler to the logger
        LOG.addHandler(consoleHandler);
        // Only log warnings and above to the console
        consoleHandler.setLevel(Level.WARNING);
        // Set the formatter for the console handler to simple text
        Formatter consoleFormat = new SimpleFormatter();
        consoleHandler.setFormatter(consoleFormat);
    }

    public static void parseJsonResponse(String input, DroneList drone) {
        try {
            // Create a JSONObject from the input string
            JSONObject o = new JSONObject(input);
            // Extract the drone type information from the JSONObject
            int typeId = o.getInt("id");
            String manufacturer = o.getString("manufacturer");
            String typeName = o.getString("typename");
            int weight = o.getInt("weight");
            int maxSpeed = o.getInt("max_speed");
            int batteryCapacity = o.getInt("battery_capacity");
            int controlRange = o.getInt("control_range");
            int maxCarriage = o.getInt("max_carriage");

            // Create a new DroneType object with the extracted information
            DroneType dronetype = new DroneType(typeId, manufacturer, typeName, weight, maxSpeed,
                    batteryCapacity, controlRange, maxCarriage);
            // Set the drone type of the drone
            drone.setDroneType(dronetype);
        } catch (Exception e) {
            // Log any exceptions that occur while parsing the drone type
            LOG.log(Level.WARNING, "An error occurred while parsing the drone type: " + e.getMessage(), e);
        }
    }
}