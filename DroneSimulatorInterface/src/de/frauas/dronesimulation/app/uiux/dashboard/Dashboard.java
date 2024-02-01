package de.frauas.dronesimulation.app.uiux.dashboard;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import de.frauas.dronesimulation.app.apiconnection.ApiHandler;
import de.frauas.dronesimulation.app.dronedynamics.DroneDynamics;
import de.frauas.dronesimulation.app.dronelist.DroneList;
import de.frauas.dronesimulation.app.dronetype.DroneType;
import de.frauas.dronesimulation.app.main.Helper;
import de.frauas.dronesimulation.app.main.main;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Dashboard extends JFrame {
	
	// Logger for logging information and errors
	private static final Logger LOG = Logger.getGlobal();

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private static JTable table;
	static DefaultTableModel model;
	
	static JLabel droneID;
	
	static JLabel droneInfos_lbl1;
	static JLabel droneInfos_lbl2;
	static JLabel droneInfos_lbl3;
	static JLabel droneInfos_lbl4;
	static JLabel droneInfos_lbl5;
	static JLabel droneInfos_lbl6;
	static JLabel droneInfos_lbl7;
	static JLabel droneInfos_lbl8;
	
	static JLabel dynamicInfos_lbl1;
	static JLabel dynamicInfos_lbl2;
	static JLabel dynamicInfos_lbl3;
	static JLabel dynamicInfos_lbl4;
	static JLabel dynamicInfos_lbl5;
	static JLabel dynamicInfos_lbl6;
	static JLabel dynamicInfos_lbl7;
	static JLabel dynamicInfos_lbl8;
	
	static JLabel drone_Icon;
	static JLabel drone_Icon_Extra;
	static JLabel droneBattery_Icon;
	static JLabel droneSpeed_Icon;
	static JLabel droneStatus_Icon;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard(null, null, null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public Dashboard(ApiHandler droneApiHandler, List<DroneList> listOfDrones, List<DroneType> listOfDroneTypes, List<DroneDynamics> listOfDronesDynamicTimeStamp) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 1186, 149);
		headerPanel.setBackground(new Color(0, 128, 128));
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Drone Simulation");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel.setBounds(28, 10, 294, 78);
		headerPanel.add(lblNewLabel);
		
		JLabel dashboard = new JLabel("Dashboard");
		dashboard.setHorizontalAlignment(SwingConstants.CENTER);
		dashboard.setForeground(Color.WHITE);
		dashboard.setFont(new Font("Tahoma", Font.BOLD, 18));
		dashboard.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/1.png")));
		dashboard.setBounds(200, 87, 195, 54);
		headerPanel.add(dashboard);
		
		JLabel manufactures = new JLabel("Manufactures");
		manufactures.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/3.png")));
		manufactures.setHorizontalAlignment(SwingConstants.CENTER);
		manufactures.setForeground(Color.WHITE);
		manufactures.setFont(new Font("Tahoma", Font.BOLD, 18));
		manufactures.setBounds(405, 87, 195, 54);
		headerPanel.add(manufactures);
		
		JLabel team = new JLabel("Our Team");
		team.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/4.png")));
		team.setHorizontalAlignment(SwingConstants.CENTER);
		team.setForeground(Color.WHITE);
		team.setFont(new Font("Tahoma", Font.BOLD, 18));
		team.setBounds(815, 87, 195, 54);
		headerPanel.add(team);
		
		JLabel refresh_lbl = new JLabel("");
		refresh_lbl.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/4.png")));
		refresh_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		refresh_lbl.setForeground(Color.WHITE);
		refresh_lbl.setFont(new Font("Tahoma", Font.BOLD, 27));
		refresh_lbl.setBounds(1098, 10, 78, 78);
		headerPanel.add(refresh_lbl);
		
		JLabel about = new JLabel("About");
		about.setBounds(610, 87, 195, 54);
		headerPanel.add(about);
		about.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/2.png")));
		about.setHorizontalAlignment(SwingConstants.CENTER);
		about.setForeground(Color.WHITE);
		about.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(0, 128, 0));
		contentPanel.setBounds(48, 196, 1092, 488);
		contentPane.add(contentPanel);
		contentPanel.setLayout(new CardLayout(0, 0));
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBackground(new Color(0, 128, 128));
		contentPanel.add(tablePanel, "name_276719349104700");
		tablePanel.setLayout(null);
		
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setBounds(10, 78, 1072, 400);
		tablePanel.add(scrollPanel);
		
		table = new JTable();
		model=new DefaultTableModel();
		Object[] column = {"ID","Manufacturer","Typename","Serialnumber","Status"};
		final Object[] row = new Object[5];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPanel.setViewportView(table);
		
		JLabel droneOverview_lbl = new JLabel("Drone Overview Table");
		droneOverview_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		droneOverview_lbl.setForeground(Color.WHITE);
		droneOverview_lbl.setFont(new Font("Tahoma", Font.BOLD, 21));
		droneOverview_lbl.setBounds(10, 10, 294, 48);
		tablePanel.add(droneOverview_lbl);
		
		JPanel cardPanel = new JPanel();
		cardPanel.setBackground(new Color(0, 128, 128));
		contentPanel.add(cardPanel, "name_249565888064300");
		cardPanel.setLayout(null);
		
		JPanel droneInfos = new JPanel();
		droneInfos.setBackground(new Color(192, 192, 192));
		droneInfos.setBounds(10, 78, 330, 400);
		cardPanel.add(droneInfos);
		droneInfos.setLayout(null);
		
		droneInfos_lbl1 = new JLabel("Model: ");
		droneInfos_lbl1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		droneInfos_lbl1.setBounds(10, 10, 274, 29);
		droneInfos.add(droneInfos_lbl1);
		
		droneInfos_lbl2 = new JLabel("Serial Number:");
		droneInfos_lbl2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		droneInfos_lbl2.setBounds(10, 49, 274, 29);
		droneInfos.add(droneInfos_lbl2);
		
		droneInfos_lbl3 = new JLabel("Weight: ");
		droneInfos_lbl3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		droneInfos_lbl3.setBounds(10, 88, 274, 29);
		droneInfos.add(droneInfos_lbl3);
		
		droneInfos_lbl4 = new JLabel("Max Speed: ");
		droneInfos_lbl4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		droneInfos_lbl4.setBounds(10, 127, 274, 29);
		droneInfos.add(droneInfos_lbl4);
		
		droneInfos_lbl5 = new JLabel("Carriage type: ");
		droneInfos_lbl5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		droneInfos_lbl5.setBounds(10, 166, 274, 29);
		droneInfos.add(droneInfos_lbl5);
		
		droneInfos_lbl6 = new JLabel("Battery Capacity: ");
		droneInfos_lbl6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		droneInfos_lbl6.setBounds(10, 205, 274, 29);
		droneInfos.add(droneInfos_lbl6);
		
		droneInfos_lbl7 = new JLabel("Carriage weight: ");
		droneInfos_lbl7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		droneInfos_lbl7.setBounds(10, 244, 274, 29);
		droneInfos.add(droneInfos_lbl7);
		
		droneInfos_lbl8 = new JLabel("Max Carriage weight: ");
		droneInfos_lbl8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		droneInfos_lbl8.setBounds(10, 283, 274, 29);
		droneInfos.add(droneInfos_lbl8);
		
		JLabel lblNewLabel_2_1 = new JLabel("Drone Infos");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_2_1.setBounds(10, 342, 310, 48);
		droneInfos.add(lblNewLabel_2_1);
		
		JPanel droneIcons = new JPanel();
		droneIcons.setBackground(new Color(192, 192, 192));
		droneIcons.setBounds(788, 78, 294, 400);
		cardPanel.add(droneIcons);
		droneIcons.setLayout(null);
		
		drone_Icon = new JLabel("");
		drone_Icon.setHorizontalAlignment(SwingConstants.CENTER);
		drone_Icon.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/5.png")));
		drone_Icon.setBounds(10, 25, 110, 110);
		droneIcons.add(drone_Icon);
		
		droneSpeed_Icon = new JLabel("");
		droneSpeed_Icon.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/5.png")));
		droneSpeed_Icon.setHorizontalAlignment(SwingConstants.CENTER);
		droneSpeed_Icon.setBounds(10, 267, 130, 110);
		droneIcons.add(droneSpeed_Icon);
		
		droneBattery_Icon = new JLabel("");
		droneBattery_Icon.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/5.png")));
		droneBattery_Icon.setHorizontalAlignment(SwingConstants.CENTER);
		droneBattery_Icon.setBounds(50, 147, 200, 110);
		droneIcons.add(droneBattery_Icon);
		
		droneStatus_Icon = new JLabel("");
		droneStatus_Icon.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/5.png")));
		droneStatus_Icon.setHorizontalAlignment(SwingConstants.CENTER);
		droneStatus_Icon.setBounds(154, 267, 130, 110);
		droneIcons.add(droneStatus_Icon);
		
		drone_Icon_Extra = new JLabel("");
		drone_Icon_Extra.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/5.png")));
		drone_Icon_Extra.setHorizontalAlignment(SwingConstants.CENTER);
		drone_Icon_Extra.setBounds(174, 25, 110, 110);
		droneIcons.add(drone_Icon_Extra);
		
		droneID = new JLabel("");
		droneID.setToolTipText("");
		droneID.setHorizontalAlignment(SwingConstants.CENTER);
		droneID.setForeground(Color.WHITE);
		droneID.setFont(new Font("Tahoma", Font.BOLD, 21));
		droneID.setBounds(10, 10, 294, 48);
		cardPanel.add(droneID);
		
		JPanel dynamicInfos = new JPanel();
		dynamicInfos.setLayout(null);
		dynamicInfos.setBackground(Color.LIGHT_GRAY);
		dynamicInfos.setBounds(351, 78, 427, 400);
		cardPanel.add(dynamicInfos);
		
		dynamicInfos_lbl1 = new JLabel("Speed: ");
		dynamicInfos_lbl1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dynamicInfos_lbl1.setBounds(10, 10, 407, 29);
		dynamicInfos.add(dynamicInfos_lbl1);
		
		dynamicInfos_lbl2 = new JLabel("Serial Number: ");
		dynamicInfos_lbl2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dynamicInfos_lbl2.setBounds(10, 49, 407, 29);
		dynamicInfos.add(dynamicInfos_lbl2);
		
		dynamicInfos_lbl3 = new JLabel("Align Roll: ");
		dynamicInfos_lbl3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dynamicInfos_lbl3.setBounds(10, 88, 407, 29);
		dynamicInfos.add(dynamicInfos_lbl3);
		
		dynamicInfos_lbl4 = new JLabel("Align Pitch: ");
		dynamicInfos_lbl4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dynamicInfos_lbl4.setBounds(10, 127, 407, 29);
		dynamicInfos.add(dynamicInfos_lbl4);
		
		dynamicInfos_lbl5 = new JLabel("Align Yaw: ");
		dynamicInfos_lbl5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dynamicInfos_lbl5.setBounds(10, 166, 407, 29);
		dynamicInfos.add(dynamicInfos_lbl5);
		
		dynamicInfos_lbl6 = new JLabel("Longitude: ");
		dynamicInfos_lbl6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dynamicInfos_lbl6.setBounds(10, 205, 407, 29);
		dynamicInfos.add(dynamicInfos_lbl6);
		
		dynamicInfos_lbl7 = new JLabel("Last Seen: ");
		dynamicInfos_lbl7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dynamicInfos_lbl7.setBounds(10, 244, 407, 29);
		dynamicInfos.add(dynamicInfos_lbl7);
		
		dynamicInfos_lbl8 = new JLabel("Status: ");
		dynamicInfos_lbl8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dynamicInfos_lbl8.setBounds(10, 283, 407, 29);
		dynamicInfos.add(dynamicInfos_lbl8);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Dynamic Infos");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_2_1_1.setBounds(10, 342, 407, 48);
		dynamicInfos.add(lblNewLabel_2_1_1);
		
		JLabel back_lbl = new JLabel("");
		back_lbl.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/7.png")));
		back_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		back_lbl.setBounds(954, 10, 128, 61);
		cardPanel.add(back_lbl);
		
		JComboBox<String> drone_DatePicker = new JComboBox<String>();
		drone_DatePicker.setBounds(351, 23, 250, 35);
		cardPanel.add(drone_DatePicker);
		
		JComboBox<String> drone_TimePicker = new JComboBox<String>();
		drone_TimePicker.setBounds(611, 23, 167, 35);
		cardPanel.add(drone_TimePicker);
		
		JPanel manufacturesPanel = new JPanel();
		manufacturesPanel.setBackground(new Color(0, 128, 128));
		contentPanel.add(manufacturesPanel, "name_287063045570700");
		manufacturesPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("More than 10+ Brands");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(10, 10, 294, 48);
		manufacturesPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/8.png")));
		lblNewLabel_3.setBounds(20, 68, 120, 110);
		manufacturesPanel.add(lblNewLabel_3);
		
		JPanel aboutPanel = new JPanel();
		aboutPanel.setBackground(new Color(0, 128, 128));
		contentPanel.add(aboutPanel, "name_287472189737400");
		aboutPanel.setLayout(null);
		
		JPanel teamPanel = new JPanel();
		teamPanel.setBackground(new Color(0, 128, 128));
		contentPanel.add(teamPanel, "name_287528452812400");
		teamPanel.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Image 1");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(10, 10, 150, 180);
		teamPanel.add(lblNewLabel_6);
		
		
		
		
		
		
		


		dashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dashboard.setForeground(Color.LIGHT_GRAY);
				manufactures.setForeground(Color.WHITE);
				about.setForeground(Color.WHITE);
				team.setForeground(Color.WHITE);
				
				tablePanel.setVisible(true);
				cardPanel.setVisible(false);
				manufacturesPanel.setVisible(false);
				aboutPanel.setVisible(false);
				teamPanel.setVisible(false);
			}
		});
		manufactures.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dashboard.setForeground(Color.WHITE);
				manufactures.setForeground(Color.LIGHT_GRAY);
				about.setForeground(Color.WHITE);
				team.setForeground(Color.WHITE);
				
				tablePanel.setVisible(false);
				cardPanel.setVisible(false);
				manufacturesPanel.setVisible(true);
				aboutPanel.setVisible(false);
				teamPanel.setVisible(false);
			}
		});
		about.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dashboard.setForeground(Color.WHITE);
				manufactures.setForeground(Color.WHITE);
				about.setForeground(Color.LIGHT_GRAY);
				team.setForeground(Color.WHITE);
				
				tablePanel.setVisible(false);
				cardPanel.setVisible(false);
				manufacturesPanel.setVisible(false);
				aboutPanel.setVisible(true);
				teamPanel.setVisible(false);
			}
		});
		team.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dashboard.setForeground(Color.WHITE);
				manufactures.setForeground(Color.WHITE);
				about.setForeground(Color.WHITE);
				team.setForeground(Color.LIGHT_GRAY);
				
				tablePanel.setVisible(false);
				cardPanel.setVisible(false);
				manufacturesPanel.setVisible(false);
				aboutPanel.setVisible(false);
				teamPanel.setVisible(true);
			}
		});
		
		dashboard.setForeground(Color.LIGHT_GRAY);
		
		// call showDroneOverviewTable() to display all Drones in Drone Overview Table
		showDroneOverviewTable(droneApiHandler, listOfDrones, listOfDroneTypes);

		// on any row click in Drone Overview Table, all the Details will be shown in proper manner
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
								
				int index=table.getSelectedRow();
				//JOptionPane.showMessageDialog(null, "Hello... ID = " + model.getValueAt(index, 0).toString());
								
				for (DroneList drone : listOfDrones) {
					if (model.getValueAt(index, 0).equals(drone.getId())) {
						
						fetchDroneDetails( index, drone);
						
						//JOptionPane.showMessageDialog(null, "ID = " + model.getValueAt(index, 0).toString() + ", Speed = " + drone.getDroneDynamics().getSpeed());
						
						tablePanel.setVisible(false);
						cardPanel.setVisible(true);
					}
		        }
			}
		});
		
		
		
		


		
		int hour = 00;
		int minute = 00;
		
		// Fill drone_TimePicker
		// Create a list to hold all time values
		List<String> times = new ArrayList<>();
		// Generate all possible time values
		for (int i = hour; i < 24; i++) {
			for (int j = minute; j < 60; j++) {
				times.add(String.format("%02d:%02d", i, j));

			}
			minute = 0;
		}
		drone_TimePicker.setModel(new DefaultComboBoxModel<String>(times.toArray(new String[0])));
		
		// Fill drone_DatePicker
		// Create a list to hold all date values
		List<String> dates1 = new ArrayList<>();
		// Iterate over the first two elements of the list
		for (int i = 0; i < 2; i++) {
			// Get the timestamp
			LocalDateTime timestamp = listOfDronesDynamicTimeStamp.get(i).getTimestamp();

			// Format the date
			String date = String.format("%02d/%02d/%d",
					timestamp.getDayOfMonth(),
					timestamp.getMonthValue(),
					timestamp.getYear());

			dates1.add(date);

		}
		drone_DatePicker.setModel(new DefaultComboBoxModel<String>(dates1.toArray(new String[0])));
		
		
		// Set the selected item to the current time
		drone_TimePicker.setSelectedItem(String.format("%02d:%02d", hour, minute));
		drone_DatePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				offsetSelectedDateTime(drone_DatePicker, drone_TimePicker, listOfDronesDynamicTimeStamp,
						droneApiHandler, listOfDrones);
				
				if (!droneID.getToolTipText().equals("")) {
					fetchDroneDetailsOnInstance(listOfDrones);
				}
			}
		});
		drone_TimePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				offsetSelectedDateTime(drone_DatePicker, drone_TimePicker, listOfDronesDynamicTimeStamp,
						droneApiHandler, listOfDrones);
				
				if (!droneID.getToolTipText().equals("")) {
					fetchDroneDetailsOnInstance(listOfDrones);
				}
			}
		});
		drone_TimePicker.setSelectedItem(
				String.format("%02d:%02d", listOfDronesDynamicTimeStamp.get(0).getTimestamp().getHour(),
						listOfDronesDynamicTimeStamp.get(0).getTimestamp().getMinute()));
		
		
		

		
		
		// back button to show Drone Overview Table again
		back_lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				drone_TimePicker.setSelectedItem(
						String.format("%02d:%02d", listOfDronesDynamicTimeStamp.get(0).getTimestamp().getHour(),
								listOfDronesDynamicTimeStamp.get(0).getTimestamp().getMinute()));
				
				tablePanel.setVisible(true);
				cardPanel.setVisible(false);
			}
		});
		
		
		// refresh button to reset Drone Overview Table
		refresh_lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				emptyDroneOverviewTable();
				drone_TimePicker.setSelectedItem(
						String.format("%02d:%02d", listOfDronesDynamicTimeStamp.get(0).getTimestamp().getHour(),
								listOfDronesDynamicTimeStamp.get(0).getTimestamp().getMinute()));
				
				tablePanel.setVisible(true);
				cardPanel.setVisible(false);
				
				callRefreshData(droneApiHandler, listOfDrones, listOfDroneTypes, 12, listOfDronesDynamicTimeStamp);
				
				// call showDroneOverviewTable() to display all Drones in Drone Overview Table again
				showDroneOverviewTable(droneApiHandler, listOfDrones, listOfDroneTypes);
			}
		});
		
		
		
	}
	
	
	
	
	
	
	

	/**************************************************************************************************/
	/* 										Start of Methods                                          */
	/**************************************************************************************************/
	
	
	///////////////////////////////////////////////////////
	// Method to show all Drones in a Drone Overview Table
	///////////////////////////////////////////////////////
	private static void showDroneOverviewTable(ApiHandler droneApiHandler, List<DroneList> listOfDrones,
			List<DroneType> listOfDroneTypes) {
		try {
				for (DroneList drone : listOfDrones) {
					model.addRow(new Object[] { drone.getId(), drone.getDroneType().getManufacturer(),
					drone.getDroneType().getTypeName(), drone.getSerialnumber(), drone.getDroneDynamics().getStatus() });
				}
		} catch (Exception e) {
			LOG.severe("An error occurred while showing Drones in a Drone Overview Table: " + e.getMessage());
			e.printStackTrace();
			System.exit(1); // stop the application
		}
	}
	
	
	////////////////////////////////////////////////////////////////////////////
	// Method to fetch Drone Details, based on which Drone row is being clicked
	////////////////////////////////////////////////////////////////////////////
	private static void fetchDroneDetails( int i, DroneList drone) {
		try {
			
			int batteryPercentage = (drone.getDroneDynamics()
					.getBatteryStatus() * 100)
					/ drone.getDroneType().getBatterycapacity();
			
			//JOptionPane.showMessageDialog(null, "Hello... ID = " + drone.getId());
			
			//droneID.setText((String) "# " + drone.getDroneType().getManufacturer() + " ID: " + model.getValueAt(i, 0).toString());
			droneID.setText((String) "# " + drone.getDroneType().getManufacturer() + " ID: " + drone.getId());
			droneID.setToolTipText(String.valueOf(drone.getId()));
			
			droneInfos_lbl1.setText((String) "Typename: " + drone.getDroneType().getTypeName());
			droneInfos_lbl2.setText((String) "Serial Number: " + drone.getSerialnumber());
			droneInfos_lbl3.setText((String) "Weight: " + drone.getDroneType().getWeight());
			droneInfos_lbl4.setText((String) "Max Speed: " + drone.getDroneType().getMax_speed());
			droneInfos_lbl5.setText((String) "Carriage type: " + drone.getCarriageType());
			droneInfos_lbl6.setText((String) "Battery Capacity: " + String.valueOf(drone.getDroneType().getBatterycapacity()));
			//droneInfos_lbl6.setText((String) "Battery Capacity: " + batteryPercentage + " %");
			droneInfos_lbl7.setText((String) "Carriage weight: " + drone.getCarriageWeight());
			droneInfos_lbl8.setText((String) "Max Carriage weight: " + drone.getDroneType().getMaxCarriage());
			
			dynamicInfos_lbl1.setText((String) "Speed: " + drone.getDroneDynamics().getSpeed());
			dynamicInfos_lbl2.setText((String) "Serial Number: " + drone.getSerialnumber());
			dynamicInfos_lbl3.setText((String) "Align Roll: " + drone.getDroneDynamics().getAlignRoll());
			dynamicInfos_lbl4.setText((String) "Align Pitch: " + drone.getDroneDynamics().getAlignPitch());
			dynamicInfos_lbl5.setText((String) "Align Yaw: " + drone.getDroneDynamics().getAlignYaw());
			dynamicInfos_lbl6.setText((String) "Longitude: " + drone.getDroneDynamics().getLongitude());
			dynamicInfos_lbl7.setText((String) "Last Seen: " + drone.getDroneDynamics().getLastSeen());
			dynamicInfos_lbl8.setText((String) "Status: " + drone.getDroneDynamics().getStatus());
			

			fetchDroneIcons(drone, batteryPercentage);
			
			
		} catch (Exception e) {
			LOG.severe("An error occurred while fetching Drone Details. " + e.getMessage());
			e.printStackTrace();
			System.exit(1); // stop the application
		}
		
	}
	private static void fetchDroneDetailsOnInstance(List<DroneList> listOfDrones) {
		try {
			//JOptionPane.showMessageDialog(null, "ID = " + droneID.getToolTipText());
			for (DroneList drone : listOfDrones) {
				if (Integer.valueOf(droneID.getToolTipText()).equals(drone.getId())) {
					
					int batteryPercentage = (drone.getDroneDynamics()
							.getBatteryStatus() * 100)
							/ drone.getDroneType().getBatterycapacity();
					
					droneID.setText((String) "# " + drone.getDroneType().getManufacturer() + " ID: " + drone.getId());
					
					droneInfos_lbl1.setText((String) "Typename: " + drone.getDroneType().getTypeName());
					droneInfos_lbl2.setText((String) "Serial Number: " + drone.getSerialnumber());
					droneInfos_lbl3.setText((String) "Weight: " + drone.getDroneType().getWeight());
					droneInfos_lbl4.setText((String) "Max Speed: " + drone.getDroneType().getMax_speed());
					droneInfos_lbl5.setText((String) "Carriage type: " + drone.getCarriageType());
					droneInfos_lbl6.setText((String) "Battery Capacity: " + String.valueOf(drone.getDroneType().getBatterycapacity()));
					//droneInfos_lbl6.setText((String) "Battery Capacity: " + batteryPercentage + " %");
					droneInfos_lbl7.setText((String) "Carriage weight: " + drone.getCarriageWeight());
					droneInfos_lbl8.setText((String) "Max Carriage weight: " + drone.getDroneType().getMaxCarriage());
					
					dynamicInfos_lbl1.setText((String) "Speed: " + drone.getDroneDynamics().getSpeed());
					dynamicInfos_lbl2.setText((String) "Serial Number: " + drone.getSerialnumber());
					dynamicInfos_lbl3.setText((String) "Align Roll: " + drone.getDroneDynamics().getAlignRoll());
					dynamicInfos_lbl4.setText((String) "Align Pitch: " + drone.getDroneDynamics().getAlignPitch());
					dynamicInfos_lbl5.setText((String) "Align Yaw: " + drone.getDroneDynamics().getAlignYaw());
					dynamicInfos_lbl6.setText((String) "Longitude: " + drone.getDroneDynamics().getLongitude());
					dynamicInfos_lbl7.setText((String) "Last Seen: " + drone.getDroneDynamics().getLastSeen());
					dynamicInfos_lbl8.setText((String) "Status: " + drone.getDroneDynamics().getStatus());
					
		
					fetchDroneIcons(drone, batteryPercentage);
				}
			}
		} catch (Exception e) {
			LOG.severe("An error occurred while fetching Drone Details. " + e.getMessage());
			e.printStackTrace();
			System.exit(1); // stop the application
		}
		
	}
	
	private static void fetchDroneIcons(DroneList drone, int batteryPercentage) {

		// icons for the card panel
		String iconPath = "/de/frauas/dronesimulation/app/ui/icon/";
	
		String iconBattery0 = iconPath + "battery0.png";
		String iconBattery25 = iconPath + "battery25.png";
		String iconBattery50 = iconPath + "battery50.png";
		String iconBattery75 = iconPath + "battery75.png";
		String iconBattery100 = iconPath + "battery100.png";
		
		String speed1 = iconPath + "speed1.png";
		String speed2 = iconPath + "speed2.png";
		String speed3 = iconPath + "speed3.png";
		String speed4 = iconPath + "speed4.png";
		String speed5 = iconPath + "speed5.png";
		String speed6 = iconPath + "speed6.png";
		
		String iconSwitchOn = iconPath + "switch-on.png";
		String iconSwitchOff = iconPath + "switch-off.png";
		
		// For Drone
		if (drone.getDroneType().getTypeName()
				.equals("AA108")) {
			drone_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "AA108" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("Chroma Camera Drone")) {
			drone_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "Chroma Camera Drone" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("D80")) {
			drone_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "D80" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("Evo II")) {
			drone_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "Evo II" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("F24 Pro")) {
			drone_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "F24 Pro" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("HS100")) {
			drone_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "HS100" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("Karma")) {
			drone_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "Karma" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("PowerEgg X")) {
			drone_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "PowerEgg X" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("S5C")) {
			drone_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "S5C" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("Skydio 2")) {
			drone_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "Skydio 2" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("Tello")) {
			drone_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "Tello" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("Typhoon H Pro")) {
			drone_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "Typhoon H Pro" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("Voyager 4")) {
			drone_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "Voyager 4" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("X4 H107D")) {
			drone_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "X4 H107D" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("X5C")) {
			drone_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "X5C" + ".png")));
		}
		
		if (drone.getCarriageType().equals("NOT")) {
			drone_Icon_Extra.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "droneNOT" + ".png")));
		} else if (drone.getCarriageType().equals("ACT")) {
			drone_Icon_Extra.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "droneACT" + ".png")));
		} else if (drone.getCarriageType().equals("SEN")) {
			drone_Icon_Extra.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "droneSEN" + ".png")));
		}
		
		
		
		// For Battery
		if (batteryPercentage == 0) {
			droneBattery_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconBattery0)));
		} else if (batteryPercentage > 0 && batteryPercentage <= 35) {
			droneBattery_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconBattery25)));
		} else if (batteryPercentage > 35 && batteryPercentage <= 60) {
			droneBattery_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconBattery50)));
		} else if (batteryPercentage > 60 && batteryPercentage <= 99) {
			droneBattery_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconBattery75)));
		} else if (batteryPercentage == 100) {
			droneBattery_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconBattery100)));
		}
		
		
		// For Speedometer
		if (Integer.valueOf(drone.getDroneDynamics().getSpeed()) <= 10) {
			droneSpeed_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(speed1)));
		} else if (Integer.valueOf(drone.getDroneDynamics().getSpeed()) <= 20) {
			droneSpeed_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(speed2)));
		} else if (Integer.valueOf(drone.getDroneDynamics().getSpeed()) <= 30) {
			droneSpeed_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(speed3)));
		} else if (Integer.valueOf(drone.getDroneDynamics().getSpeed()) <= 40) {
			droneSpeed_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(speed4)));
		} else if (Integer.valueOf(drone.getDroneDynamics().getSpeed()) <= 50) {
			droneSpeed_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(speed5)));
		} else {
			droneSpeed_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(speed6)));
		}
		
		
		// For Switch
		if (drone.getDroneDynamics().getStatus().equals("ON")) {
			droneStatus_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconSwitchOn)));
		} else {
			droneStatus_Icon.setIcon(new javax.swing.ImageIcon(Dashboard.class.getResource(iconSwitchOff)));
		}
		
	}

	
	///////////////////////////////////////////////////////
	// Method to refresh all Drones in a Drone Overview Table
	///////////////////////////////////////////////////////
	public static void callRefreshData(ApiHandler droneApiHandler, List<DroneList> listOfDrones,
			List<DroneType> listOfDroneTypes, int minutesBefore, List<DroneDynamics> listOfDronesDynamicTimeStamp) {

		main.refreshData(droneApiHandler, listOfDrones, listOfDroneTypes, listOfDronesDynamicTimeStamp,
				minutesBefore);
	}
	
	
	
	///////////////////////////////////////////////////////
	// Clear all Drones from Drone Overview Table
	///////////////////////////////////////////////////////
	public static void emptyDroneOverviewTable() {
		
		while (model.getRowCount()>0)
        {
			model.removeRow(0);
        }

    }
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	// Checks the current Status of Drone availability, functionality on selected Date and Time
	////////////////////////////////////////////////////////////////////////////////////////////
	public int offsetSelectedDateTime(JComboBox<String> drone_DatePicker, JComboBox<String> drone_TimePicker,
			List<DroneDynamics> listOfDronesDynamicTimeStamp, ApiHandler droneApiHandler,
			List<DroneList> listOfDrones) {
		String selectedDate = (String) drone_DatePicker.getSelectedItem();
		String selectedTime = (String) drone_TimePicker.getSelectedItem();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String dateTimeString = selectedDate + " " + selectedTime;
		LocalDateTime selecteDateTime = LocalDateTime.parse(dateTimeString, formatter);

		if (drone_DatePicker.getSelectedIndex() == 0) {
			if ((selecteDateTime.getHour() == listOfDronesDynamicTimeStamp.get(0).getTimestamp().getHour()
					&& selecteDateTime.getMinute() >= listOfDronesDynamicTimeStamp.get(0).getTimestamp().getMinute())
					|| selecteDateTime.getHour() > listOfDronesDynamicTimeStamp.get(0).getTimestamp().getHour()) {
				System.out.println("Available");
			} else {
				JOptionPane.showMessageDialog(null,
						"There is no Dynamic Data in this Timestamp" + "\n" + "Please choose another time" + "\n"
								+ "Dynamic data on selected day starts from:"
								+ listOfDronesDynamicTimeStamp.get(0).getTimestamp().getHour() + ":"
								+ listOfDronesDynamicTimeStamp.get(0).getTimestamp().getMinute() + "\n" + "");

				System.out.println("Not available yet");
			}
		}
		if (drone_DatePicker.getSelectedIndex() == 1) {
			System.out.println("Selected date Hour: " + selecteDateTime.getHour());
			System.out.println("Selected date Minute: " + selecteDateTime.getMinute());
			System.out.println("SelectLI date Hour: " + listOfDronesDynamicTimeStamp.get(1).getTimestamp().getHour());
			System.out
					.println("SelectLI date Minute: " + listOfDronesDynamicTimeStamp.get(1).getTimestamp().getMinute());

			if ((selecteDateTime.getHour() == listOfDronesDynamicTimeStamp.get(1).getTimestamp().getHour()
					&& selecteDateTime.getMinute() <= listOfDronesDynamicTimeStamp.get(1).getTimestamp().getMinute())
					|| selecteDateTime.getHour() < listOfDronesDynamicTimeStamp.get(1).getTimestamp().getHour()) {
				System.out.println("Available");

			} else {
				JOptionPane.showMessageDialog(null,
						"There is no Dynamic Data in this Timestamp" + "\n" + "Please choose another time" + "\n"
								+ "Dynamic data on selected day ends at: "
								+ listOfDronesDynamicTimeStamp.get(1).getTimestamp().getHour() + ":"
								+ listOfDronesDynamicTimeStamp.get(1).getTimestamp().getMinute() + "\n" + "");
				System.out.println("Not available yet");
			}
		}

		System.out.println("Selected date: " + selectedDate);
		System.out.println("Selected time: " + selectedTime);
		int hour = selecteDateTime.getHour();
		int minute = selecteDateTime.getMinute();

		// Calculate the total number of minutes
		int totalMinutes = 0;

		if (drone_DatePicker.getSelectedIndex() == 0) {
			totalMinutes = (hour - listOfDronesDynamicTimeStamp.get(0).getTimestamp().getHour()) * 60
					+ (minute - listOfDronesDynamicTimeStamp.get(0).getTimestamp().getMinute());
		} else {
			totalMinutes = (hour) * 60
					+ (minute) + 893;
		}

		System.out.println("Total minutes from 00:00: " + totalMinutes);
		if (0 <= totalMinutes && totalMinutes <= 1440) {
			Helper.getDroneDynamics(droneApiHandler, listOfDrones, totalMinutes, listOfDronesDynamicTimeStamp);
		}

		return totalMinutes;
	}

	
	

	
		
	/**************************************************************************************************/
	/* 										 End of Methods                                           */
	/**************************************************************************************************/
}
