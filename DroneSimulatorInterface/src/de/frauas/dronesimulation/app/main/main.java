package de.frauas.dronesimulation.app.main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;
import java.io.IOException;

import de.frauas.dronesimulation.app.apiconnection.ApiHandler;
import de.frauas.dronesimulation.app.dronedynamics.DroneDynamics;
import de.frauas.dronesimulation.app.dronelist.DroneList;
import de.frauas.dronesimulation.app.dronetype.DroneType;
import de.frauas.dronesimulation.app.uiux.dashboard.Dashboard;

public class main {
	// Logger for logging information and errors
	private static final Logger LOG = Logger.getGlobal();

	// Static block for setting up logger handlers
	static {
		Handler fileHandler;
		Handler consoleHandler;
		try {
			// File handler for logging to a file
			fileHandler = new FileHandler("./mainLogFile.log");
			LOG.addHandler(fileHandler);
			Formatter xmlFormat = new XMLFormatter();
			fileHandler.setFormatter(xmlFormat);
			fileHandler.setLevel(Level.ALL);
		} catch (IOException e) {
			LOG.severe("An error occurred while setting up the logger handlers: " + e.getMessage());
			e.printStackTrace();
		}

		// Console handler for logging to the console
		consoleHandler = new ConsoleHandler();
		LOG.addHandler(consoleHandler);
		consoleHandler.setLevel(Level.WARNING);
		Formatter consoleFormat = new SimpleFormatter();
		consoleHandler.setFormatter(consoleFormat);
	}

	public static void main(String[] args) {
		try {
			// Initialize API handler and lists for drones, drone types, and drone dynamics
			ApiHandler droneApiHandler = new ApiHandler();
			List<DroneList> listOfDrones = new ArrayList<>();
			List<DroneType> listOfDroneTypes = new ArrayList<>();
			List<DroneDynamics> listOfDronesDynamicTimeStamp = new ArrayList<>();

			// Populate drone list and create drone type objects
			populateDroneList(droneApiHandler, listOfDrones);
			createDroneTypeObj(droneApiHandler, listOfDrones, listOfDroneTypes);

			int minutesBefore = 0; // 0 means current time and 1440 means last data offset 24 hours

			// Get drone dynamics
			Helper.getDroneDynamics(droneApiHandler, listOfDrones, minutesBefore, listOfDronesDynamicTimeStamp);

			// Print status of the first drone
			listOfDrones.get(0).getDroneDynamics().printStatus();

			// Log the size of drone list and drone type list
			LOG.info("Size of drone list: " + listOfDrones.size());
			LOG.info("Size of drone type list: " + listOfDroneTypes.size());

			LOG.info("Done.");
			
			
			// Create obj for uiux and display it
			Dashboard dashboardInstance = new Dashboard(droneApiHandler, listOfDrones, listOfDroneTypes, listOfDronesDynamicTimeStamp);
			dashboardInstance.setVisible(true);
			
		} catch (Exception e) {
			// Log any exceptions that occur
			LOG.severe("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Method to populate drone list
	private static void populateDroneList(ApiHandler droneApiHandler, List<DroneList> listOfDrones) {
		try {
			droneApiHandler.fetchDroneList(0, 30, listOfDrones);
		} catch (Exception e) {
			LOG.severe("An error occurred while Calling and creating the drone list: " + e.getMessage());
			e.printStackTrace();
			System.exit(1); // stop the application
		}
	}

	// Method to create drone type objects
	private static void createDroneTypeObj(ApiHandler droneApiHandler, List<DroneList> listOfDrones,
			List<DroneType> listOfDroneTypes) {
		try {
			for (DroneList drone : listOfDrones) {
				boolean matchFound = false;

				// Check if drone type already exists in the list
				for (DroneType droneType : listOfDroneTypes) {
					if (drone.getDronetypeUri().equals(droneType.getDroneTypeUri())) {
						drone.setDroneType(droneType);
						matchFound = true;
						break;
					}
				}

				// If drone type does not exist, fetch it and add to the list
				if (!matchFound) {
					droneApiHandler.fetchDroneType(drone);
					listOfDroneTypes.add(drone.getDroneType());
				}
			}
		} catch (Exception e) {
			LOG.severe("An error occurred while creating drone type objects: " + e.getMessage());
			e.printStackTrace();
			System.exit(1); // stop the application
		}
	}
	
	


	public static void refreshData(ApiHandler droneApiHandler, List<DroneList> listOfDrones,
			List<DroneType> listOfDroneTypes, List<DroneDynamics> listOfDronesDynamicTimeStamp, int minutesBefore) {
		// Clear the existing lists
		listOfDrones.clear();
		listOfDroneTypes.clear();

		// Fetch and create the objects again
		populateDroneList(droneApiHandler, listOfDrones);
		createDroneTypeObj(droneApiHandler, listOfDrones, listOfDroneTypes);
		Helper.getDroneDynamics(droneApiHandler, listOfDrones, minutesBefore, listOfDronesDynamicTimeStamp);
		System.out.println("Refreshed the data!");
	}
}