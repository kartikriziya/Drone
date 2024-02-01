package de.frauas.dronesimulation.app.apiconnection;

import de.frauas.dronesimulation.app.dronedynamics.DroneDynamics;
import de.frauas.dronesimulation.app.dronedynamics.ParseDroneDynamics;
import de.frauas.dronesimulation.app.dronelist.DroneList;
import de.frauas.dronesimulation.app.dronelist.ParseDroneList;
import de.frauas.dronesimulation.app.dronetype.ParseDroneType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;

public class ApiHandler {

    private static final Logger LOG = Logger.getLogger(ApiHandler.class.getName());
    private static final String USER_AGENT = "MOzilla FIrefox Awesome version";
    private static final String ENDPOINT_URL_DRONE_LIST = "https://dronesim.facets-labs.com/api/drones/?format=json";
    private static final String ENDPOINT_URL_DRONE_DYNAMICS = "https://dronesim.facets-labs.com/api/dronedynamics/?format=json";
    private static final String TOKEN = "Token 2ace84830f9ad2a039c6a6dda7b529bac48a71cd";
    ///////////

    // Static block to initialize logging handlers
    static {
        Handler fileHandler;
        Handler consoleHandler;
        try {
            // File handler for logging to a file
            fileHandler = new FileHandler("./APILogFile.log");
            // Add the file handler to the logger
            LOG.addHandler(fileHandler);
            // Set the formatter for the file handler to XML
            Formatter xmlFormat = new XMLFormatter();
            fileHandler.setFormatter(xmlFormat);
            // Set the level of the file handler to ALL
            fileHandler.setLevel(Level.ALL);
        } catch (IOException e) {
            // Handle any IO exceptions
        }

        // Console handler for logging to the console
        consoleHandler = new ConsoleHandler();
        // Add the console handler to the logger
        LOG.addHandler(consoleHandler);
        // Set the level of the console handler to WARNING
        consoleHandler.setLevel(Level.WARNING);
        // Set the formatter for the console handler to Simple
        Formatter consoleFormat = new SimpleFormatter();
        consoleHandler.setFormatter(consoleFormat);
    }

    // Method to fetch the drone list from the API
    public static void fetchDroneList(int offset, int limit, List<DroneList> listOfDrones) {
        try {
            // Create a URL object with the API endpoint
            URL url = new URL(ENDPOINT_URL_DRONE_LIST + "&offset=" + offset + "&limit=" + limit);
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set the request properties
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            // Log the response code
            LOG.log(Level.INFO, "Response Code : " + responseCode);

            // If the response code is OK
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                // Read the response line by line
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                // Close the BufferedReader
                in.close();
                // Parse the JSON response
                ParseDroneList.parseJsonResponse(response.toString(), listOfDrones);
            } else {
                // Log a warning if the GET request did not work
                LOG.log(Level.WARNING, "GET request not worked");
            }
        } catch (Exception e) {
            // Log any exceptions that occur
            LOG.log(Level.WARNING, "An error occurred while fetching the drone list: " + e.getMessage(), e);
            // Stop the application
            System.exit(1);
        }
    }

    // Method to fetch the drone type from the API
    public static void fetchDroneType(DroneList drone) {
        try {
            // Create a URL object with the drone type URI
            URL url = new URL(drone.getDronetypeUri());
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set the request properties
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            // Log the response code
            LOG.log(Level.INFO, "Response Code : " + responseCode);

            // If the response code is OK
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                // Read the response line by line
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                // Close the BufferedReader
                in.close();

                // Parse the JSON response
                ParseDroneType.parseJsonResponse(response.toString(), drone);
            }
        } catch (Exception e) {
            // Log any exceptions that occur
            LOG.log(Level.WARNING, "An error occurred while fetching the drone type: " + e.getMessage(), e);
            // Stop the application
            System.exit(1);
        }
    }

    // Method to fetch the drone dynamics from the API
    public void fetchDroneDynamics(List<DroneList> listOfDrones, int offset,
            List<DroneDynamics> listOfDronesDynamicTimeStamp) {
        try {
            // Create a URL object with the API endpoint and the specified limit and offset
            URL url = new URL(ENDPOINT_URL_DRONE_DYNAMICS + "&limit=" + 25 + "&offset=" + offset);
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set the request properties
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            // Log the response code
            LOG.log(Level.INFO, "Response Code : " + responseCode);

            // If the response code is OK
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                // Read the response line by line
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                // Close the BufferedReader
                in.close();

                // Parse the JSON response
                ParseDroneDynamics.parseJsonResponse(response.toString(), listOfDrones, listOfDronesDynamicTimeStamp);
            } else {
                // Log a warning if the GET request did not work
                LOG.log(Level.WARNING, "GET request not worked");
            }

            // Create a new URL object with the API endpoint and a limit of 25 and an offset
            // of 0
            url = new URL(ENDPOINT_URL_DRONE_DYNAMICS + "&limit=" + 25 + "&offset=" + 0);
            // Open a new connection to the URL
            HttpURLConnection connection1 = (HttpURLConnection) url.openConnection();
            // Set the request properties
            connection1.setRequestProperty("Authorization", TOKEN);
            connection1.setRequestProperty("User-Agent", USER_AGENT);
            connection1.setRequestMethod("GET");

            // Get the response code
            responseCode = connection1.getResponseCode();

            // If the response code is OK
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                // Read the response line by line
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                // Close the BufferedReader
                in.close();

                // Parse the JSON response for the date
                ParseDroneDynamics.parseJsonResponseForDate(response.toString(), listOfDronesDynamicTimeStamp);
            } else {
                // Log a warning if the GET request did not work
                LOG.log(Level.WARNING, "GET request not worked");
            }
        } catch (Exception e) {
            // Log any exceptions that occur
            LOG.log(Level.WARNING, "An error occurred while fetching the drone dynamics: " + e.getMessage(), e);
            // Stop the application
            System.exit(1);
        }
    }
}