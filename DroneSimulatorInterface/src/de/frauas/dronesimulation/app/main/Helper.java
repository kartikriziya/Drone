package de.frauas.dronesimulation.app.main;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;
import java.io.IOException;
import java.util.List;
import de.frauas.dronesimulation.app.apiconnection.ApiHandler; // Import the apihandler class
import de.frauas.dronesimulation.app.dronedynamics.DroneDynamics;
import de.frauas.dronesimulation.app.dronelist.DroneList;

public class Helper {

	private static final Logger LOG = Logger.getGlobal();

	// Static block for setting up logger handlers
	static {
		Handler fileHandler;
		Handler consoleHandler;
		try {
			// File handler for logging to a file
			fileHandler = new FileHandler("./HelperLogFile.log");
			LOG.addHandler(fileHandler);
			Formatter xmlFormat = new XMLFormatter();
			fileHandler.setFormatter(xmlFormat);
			fileHandler.setLevel(Level.ALL);
		} catch (IOException e) {
			LOG.severe("An error occurred while setting up the logger handlers: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void getDroneDynamics(ApiHandler droneApiHandler, List<DroneList> listOfDrones, int minutesBefore,
			List<DroneDynamics> listOfDronesDynamicTimeStamp) {
		try {
			int offset = (minutesBefore * 25);
			if (offset >= 0) {
				droneApiHandler.fetchDroneDynamics(listOfDrones, offset, listOfDronesDynamicTimeStamp);
				listOfDronesDynamicTimeStamp.add(listOfDrones.get(0).getDroneDynamics());
			} else {
				System.out.println("Invalid minutesBefore value.");
			}
		} catch (Exception e) {
			LOG.warning("An error occurred while fetching dynamics info:  " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void printDroneDynamicsStatus(List<DroneList> listOfDrones, int selectedDroneIndex) {
		try {
			listOfDrones.get(selectedDroneIndex).getDroneDynamicsList().get(0).printStatus();
		} catch (Exception e) {
			LOG.warning("An error occurred while fetching dynamics info:  " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static int calculatePayloadPercentage(DroneList droneInstance) {
		try {
			int payloadPercentage = (droneInstance.getCarriageWeight() * 100)
					/ droneInstance.getDroneType().getMaxCarriage();
			System.out.println("PAYLOAD PERCENTAGE: " + payloadPercentage);
			return payloadPercentage;
		} catch (Exception e) {
			LOG.warning("An error occurred while fetching dynamics info:  " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}
}