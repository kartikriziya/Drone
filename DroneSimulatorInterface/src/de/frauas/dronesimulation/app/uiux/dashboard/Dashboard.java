package de.frauas.dronesimulation.app.uiux.dashboard;
////Last one
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import de.frauas.dronesimulation.app.apiconnection.ApiHandler;
import de.frauas.dronesimulation.app.dronedynamics.DroneDynamics;
import de.frauas.dronesimulation.app.dronelist.DroneList;
import de.frauas.dronesimulation.app.dronetype.DroneType;
import de.frauas.dronesimulation.app.main.Helper;
import de.frauas.dronesimulation.app.main.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Dashboard extends JFrame {
	
	// Logger for logging information and errors
	private static final Logger LOG = Logger.getGlobal();

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private static JTable table;
	static DefaultTableModel model;
	
	static JLabel droneID;
	
	static JLabel droneModel;
	static JLabel droneSerial;
	static JLabel droneWeight;
	static JLabel droneMaxSpeed;
	static JLabel droneType;
	static JLabel droneBattery;
	static JLabel droneCarriageWeight;
	static JLabel droneMaxCarriage;
	
	static JLabel dynamicSpeed;
	static JLabel dynamicSerial;
	static JLabel dynamicRoll;
	static JLabel dynamicPitch;
	static JLabel dynamicYaw;
	static JLabel dynamicLongitude;
	static JLabel dynamicLastSeen;
	static JLabel dynamicStatus;
	
	static JLabel iconDrone;
	static JLabel iconDroneExtra;
	static JLabel iconDroneBattery;
	static JLabel iconDroneSpeed;
	static JLabel iconDroneStatus;
	
	

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
		contentPane.setBackground(new Color(25, 131, 168));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 1200, 150);
		headerPanel.setBackground(new Color(19, 102, 131));
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblDroneSimulation = new JLabel("Drone Simulation");
		lblDroneSimulation.setBackground(Color.WHITE);
		lblDroneSimulation.setHorizontalAlignment(SwingConstants.CENTER);
		lblDroneSimulation.setForeground(new Color(255, 255, 255));
		lblDroneSimulation.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblDroneSimulation.setBounds(28, 10, 294, 78);
		headerPanel.add(lblDroneSimulation);
		
		JLabel dashboard = new JLabel("Dashboard");
		dashboard.setBackground(Color.WHITE);
		dashboard.setHorizontalAlignment(SwingConstants.CENTER);
		dashboard.setForeground(Color.WHITE);
		dashboard.setFont(new Font("Tahoma", Font.BOLD, 18));
		dashboard.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/5.png")));
		dashboard.setBounds(200, 87, 195, 54);
		headerPanel.add(dashboard);
		
		JLabel manufactures = new JLabel("Manufactures");
		manufactures.setBackground(Color.WHITE);
		manufactures.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/6.png")));
		manufactures.setHorizontalAlignment(SwingConstants.CENTER);
		manufactures.setForeground(Color.WHITE);
		manufactures.setFont(new Font("Tahoma", Font.BOLD, 18));
		manufactures.setBounds(405, 87, 195, 54);
		headerPanel.add(manufactures);
		
		JLabel about = new JLabel("About");
		about.setBackground(Color.WHITE);
		about.setBounds(610, 87, 195, 54);
		headerPanel.add(about);
		about.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/about1.png")));
		about.setHorizontalAlignment(SwingConstants.CENTER);
		about.setForeground(Color.WHITE);
		about.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel team = new JLabel("Our Team");
		team.setBackground(Color.WHITE);
		team.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/8.png")));
		team.setHorizontalAlignment(SwingConstants.CENTER);
		team.setForeground(Color.WHITE);
		team.setFont(new Font("Tahoma", Font.BOLD, 18));
		team.setBounds(815, 87, 195, 54);
		headerPanel.add(team);
		
		JLabel lblRefresh = new JLabel("");
		lblRefresh.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/refresh1.png")));
		lblRefresh.setHorizontalAlignment(SwingConstants.CENTER);
		lblRefresh.setBounds(1098, 10, 78, 78);
		headerPanel.add(lblRefresh);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(0, 128, 0));
		contentPanel.setBounds(48, 196, 1092, 488);
		contentPane.add(contentPanel);
		contentPanel.setLayout(new CardLayout(0, 0));
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBackground(new Color(25, 131, 168));
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
		
		JLabel lblDroneOverview = new JLabel("Drone Overview Table");
		lblDroneOverview.setBackground(Color.WHITE);
		lblDroneOverview.setHorizontalAlignment(SwingConstants.CENTER);
		lblDroneOverview.setForeground(Color.WHITE);
		lblDroneOverview.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblDroneOverview.setBounds(10, 10, 294, 48);
		tablePanel.add(lblDroneOverview);
		
		JPanel cardPanel = new JPanel();
		cardPanel.setBackground(new Color(25, 131, 168));
		contentPanel.add(cardPanel, "name_249565888064300");
		cardPanel.setLayout(null);
		
		JPanel droneInfos = new JPanel();
		droneInfos.setBackground(Color.WHITE);
		droneInfos.setBounds(10, 78, 330, 400);
		cardPanel.add(droneInfos);
		droneInfos.setLayout(null);
		
		droneModel = new JLabel("Model: ");
		droneModel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		droneModel.setBounds(10, 10, 274, 29);
		droneInfos.add(droneModel);
		
		droneSerial = new JLabel("Serial Number:");
		droneSerial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		droneSerial.setBounds(10, 49, 274, 29);
		droneInfos.add(droneSerial);
		
		droneWeight = new JLabel("Weight: ");
		droneWeight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		droneWeight.setBounds(10, 88, 274, 29);
		droneInfos.add(droneWeight);
		
		droneMaxSpeed = new JLabel("Max Speed: ");
		droneMaxSpeed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		droneMaxSpeed.setBounds(10, 127, 274, 29);
		droneInfos.add(droneMaxSpeed);
		
		droneType = new JLabel("Carriage type: ");
		droneType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		droneType.setBounds(10, 166, 274, 29);
		droneInfos.add(droneType);
		
		droneBattery = new JLabel("Battery Capacity: ");
		droneBattery.setFont(new Font("Tahoma", Font.PLAIN, 16));
		droneBattery.setBounds(10, 205, 274, 29);
		droneInfos.add(droneBattery);
		
		droneCarriageWeight = new JLabel("Carriage weight: ");
		droneCarriageWeight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		droneCarriageWeight.setBounds(10, 244, 274, 29);
		droneInfos.add(droneCarriageWeight);
		
		droneMaxCarriage = new JLabel("Max Carriage weight: ");
		droneMaxCarriage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		droneMaxCarriage.setBounds(10, 283, 274, 29);
		droneInfos.add(droneMaxCarriage);
		
		JLabel lblDroneInfos = new JLabel("Drone Infos");
		lblDroneInfos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDroneInfos.setForeground(new Color(25, 131, 168));
		lblDroneInfos.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblDroneInfos.setBounds(10, 342, 310, 48);
		droneInfos.add(lblDroneInfos);
		
		JPanel droneIcons = new JPanel();
		droneIcons.setBackground(Color.WHITE);
		droneIcons.setBounds(788, 78, 294, 400);
		cardPanel.add(droneIcons);
		droneIcons.setLayout(null);
		
		iconDrone = new JLabel("");
		iconDrone.setHorizontalAlignment(SwingConstants.CENTER);
		iconDrone.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/5.png")));
		iconDrone.setBounds(10, 25, 110, 110);
		droneIcons.add(iconDrone);
		
		iconDroneSpeed = new JLabel("");
		iconDroneSpeed.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/5.png")));
		iconDroneSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		iconDroneSpeed.setBounds(10, 267, 130, 110);
		droneIcons.add(iconDroneSpeed);
		
		iconDroneBattery = new JLabel("");
		iconDroneBattery.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/5.png")));
		iconDroneBattery.setHorizontalAlignment(SwingConstants.CENTER);
		iconDroneBattery.setBounds(50, 147, 200, 110);
		droneIcons.add(iconDroneBattery);
		
		iconDroneStatus = new JLabel("");
		iconDroneStatus.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/5.png")));
		iconDroneStatus.setHorizontalAlignment(SwingConstants.CENTER);
		iconDroneStatus.setBounds(154, 267, 130, 110);
		droneIcons.add(iconDroneStatus);
		
		iconDroneExtra = new JLabel("");
		iconDroneExtra.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/5.png")));
		iconDroneExtra.setHorizontalAlignment(SwingConstants.CENTER);
		iconDroneExtra.setBounds(174, 25, 110, 110);
		droneIcons.add(iconDroneExtra);
		
		droneID = new JLabel("");
		droneID.setToolTipText("");
		droneID.setHorizontalAlignment(SwingConstants.CENTER);
		droneID.setForeground(Color.WHITE);
		droneID.setFont(new Font("Tahoma", Font.BOLD, 21));
		droneID.setBounds(10, 10, 294, 48);
		cardPanel.add(droneID);
		
		JPanel dynamicInfos = new JPanel();
		dynamicInfos.setLayout(null);
		dynamicInfos.setBackground(Color.WHITE);
		dynamicInfos.setBounds(351, 78, 427, 400);
		cardPanel.add(dynamicInfos);
		
		dynamicSpeed = new JLabel("Speed: ");
		dynamicSpeed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dynamicSpeed.setBounds(10, 10, 407, 29);
		dynamicInfos.add(dynamicSpeed);
		
		dynamicSerial = new JLabel("Serial Number: ");
		dynamicSerial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dynamicSerial.setBounds(10, 49, 407, 29);
		dynamicInfos.add(dynamicSerial);
		
		dynamicRoll = new JLabel("Align Roll: ");
		dynamicRoll.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dynamicRoll.setBounds(10, 88, 407, 29);
		dynamicInfos.add(dynamicRoll);
		
		dynamicPitch = new JLabel("Align Pitch: ");
		dynamicPitch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dynamicPitch.setBounds(10, 127, 407, 29);
		dynamicInfos.add(dynamicPitch);
		
		dynamicYaw = new JLabel("Align Yaw: ");
		dynamicYaw.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dynamicYaw.setBounds(10, 166, 407, 29);
		dynamicInfos.add(dynamicYaw);
		
		dynamicLongitude = new JLabel("Longitude: ");
		dynamicLongitude.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dynamicLongitude.setBounds(10, 205, 407, 29);
		dynamicInfos.add(dynamicLongitude);
		
		dynamicLastSeen = new JLabel("Last Seen: ");
		dynamicLastSeen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dynamicLastSeen.setBounds(10, 244, 407, 29);
		dynamicInfos.add(dynamicLastSeen);
		
		dynamicStatus = new JLabel("Status: ");
		dynamicStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dynamicStatus.setBounds(10, 283, 407, 29);
		dynamicInfos.add(dynamicStatus);
		
		JLabel lblDynamicInfos = new JLabel("Dynamic Infos");
		lblDynamicInfos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDynamicInfos.setForeground(new Color(25, 131, 168));
		lblDynamicInfos.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblDynamicInfos.setBounds(10, 342, 407, 48);
		dynamicInfos.add(lblDynamicInfos);
		
		JLabel lblBack = new JLabel("");
		lblBack.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/return0.png")));
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setBounds(954, 10, 128, 61);
		cardPanel.add(lblBack);
		
		JComboBox<String> drone_DatePicker = new JComboBox<String>();
		drone_DatePicker.setBounds(351, 23, 250, 35);
		cardPanel.add(drone_DatePicker);
		
		JComboBox<String> drone_TimePicker = new JComboBox<String>();
		drone_TimePicker.setBounds(611, 23, 167, 35);
		cardPanel.add(drone_TimePicker);
		
		JPanel manufacturesPanel = new JPanel();
		manufacturesPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		manufacturesPanel.setBackground(new Color(25, 131, 168));
		contentPanel.add(manufacturesPanel, "name_287063045570700");
		manufacturesPanel.setLayout(null);
		
		JLabel txtManufacture = new JLabel("More than 10+ Brands");
		txtManufacture.setHorizontalAlignment(SwingConstants.CENTER);
		txtManufacture.setFont(new Font("Tahoma", Font.BOLD, 21));
		txtManufacture.setForeground(new Color(255, 255, 255));
		txtManufacture.setBounds(10, 10, 294, 48);
		manufacturesPanel.add(txtManufacture);
		
		JLabel iconSkydio = new JLabel("");
		iconSkydio.setHorizontalAlignment(SwingConstants.CENTER);
		iconSkydio.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/ Skydio.png")));
		iconSkydio.setBounds(50, 70, 120, 110);
		manufacturesPanel.add(iconSkydio);
		
		JLabel iconYuneec = new JLabel("");
		iconYuneec.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/ Yuneec.png")));
		iconYuneec.setHorizontalAlignment(SwingConstants.CENTER);
		iconYuneec.setBounds(250, 70, 120, 110);
		manufacturesPanel.add(iconYuneec);
		
		JLabel iconAutelRobotics = new JLabel("");
		iconAutelRobotics.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/AutelRobotics.png")));
		iconAutelRobotics.setHorizontalAlignment(SwingConstants.CENTER);
		iconAutelRobotics.setBounds(450, 70, 120, 110);
		manufacturesPanel.add(iconAutelRobotics);
		
		JLabel iconContixo = new JLabel("");
		iconContixo.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/ Contixo.png")));
		iconContixo.setHorizontalAlignment(SwingConstants.CENTER);
		iconContixo.setBounds(650, 70, 120, 110);
		manufacturesPanel.add(iconContixo);
		
		JLabel iconAltairAerial = new JLabel("");
		iconAltairAerial.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/Altair.jpeg")));
		iconAltairAerial.setHorizontalAlignment(SwingConstants.CENTER);
		iconAltairAerial.setBounds(50, 200, 120, 110);
		manufacturesPanel.add(iconAltairAerial);
		
		JLabel iconWalkera = new JLabel("");
		iconWalkera.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/ Walkera.png")));
		iconWalkera.setHorizontalAlignment(SwingConstants.CENTER);
		iconWalkera.setBounds(50, 330, 120, 110);
		manufacturesPanel.add(iconWalkera);
		
		JLabel iconHolyStone = new JLabel("");
		iconHolyStone.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/HolyStone.png")));
		iconHolyStone.setHorizontalAlignment(SwingConstants.CENTER);
		iconHolyStone.setBounds(250, 200, 120, 110);
		manufacturesPanel.add(iconHolyStone);
		
		JLabel iconPotensic = new JLabel("");
		iconPotensic.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/Potensic.png")));
		iconPotensic.setHorizontalAlignment(SwingConstants.CENTER);
		iconPotensic.setBounds(250, 330, 120, 110);
		manufacturesPanel.add(iconPotensic);
		
		JLabel iconHubsan = new JLabel("");
		iconHubsan.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/Hubsan.png")));
		iconHubsan.setHorizontalAlignment(SwingConstants.CENTER);
		iconHubsan.setBounds(450, 200, 120, 110);
		manufacturesPanel.add(iconHubsan);
		
		JLabel iconPowerVision = new JLabel("");
		iconPowerVision.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/PowerVision.png")));
		iconPowerVision.setHorizontalAlignment(SwingConstants.CENTER);
		iconPowerVision.setBounds(450, 330, 120, 110);
		manufacturesPanel.add(iconPowerVision);
		
		JLabel iconSyma = new JLabel("");
		iconSyma.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/symas.png")));
		iconSyma.setHorizontalAlignment(SwingConstants.CENTER);
		iconSyma.setBounds(650, 200, 120, 110);
		manufacturesPanel.add(iconSyma);
		
		JLabel iconRyze = new JLabel("");
		iconRyze.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/Ryze.png")));
		iconRyze.setHorizontalAlignment(SwingConstants.CENTER);
		iconRyze.setBounds(650, 330, 120, 110);
		manufacturesPanel.add(iconRyze);
		
		JLabel iconGopro = new JLabel("");
		iconGopro.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/gopro_Logo.jpg")));
		iconGopro.setHorizontalAlignment(SwingConstants.CENTER);
		iconGopro.setBounds(850, 70, 120, 110);
		manufacturesPanel.add(iconGopro);
		
		JLabel iconBlade = new JLabel("");
		iconBlade.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/Blade.png")));
		iconBlade.setHorizontalAlignment(SwingConstants.CENTER);
		iconBlade.setBounds(850, 200, 120, 110);
		manufacturesPanel.add(iconBlade);
		
		JLabel iconSnaptain = new JLabel("");
		iconSnaptain.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/Snaptain.png")));
		iconSnaptain.setHorizontalAlignment(SwingConstants.CENTER);
		iconSnaptain.setBounds(850, 330, 120, 110);
		manufacturesPanel.add(iconSnaptain);
		
		JPanel aboutPanel = new JPanel();
		aboutPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(19, 102, 131)));
		aboutPanel.setBackground(new Color(25, 131, 168));
		contentPanel.add(aboutPanel, "name_287472189737400");
		aboutPanel.setLayout(null);
		
		JLabel txtAbout = new JLabel("About Us");
		txtAbout.setVerticalAlignment(SwingConstants.TOP);
		txtAbout.setHorizontalAlignment(SwingConstants.LEFT);
		txtAbout.setForeground(Color.WHITE);
		txtAbout.setFont(new Font("Tahoma", Font.BOLD, 21));
		txtAbout.setBounds(30, 30, 200, 40);
		aboutPanel.add(txtAbout);
		
		JTextArea txtrWelcomeToOur = new JTextArea();
		txtrWelcomeToOur.setEditable(false);
		txtrWelcomeToOur.setForeground(Color.WHITE);
		txtrWelcomeToOur.setBackground(new Color(25, 131, 168));
		txtrWelcomeToOur.setText("\n  Welcome to our exciting project! \n\n  We're working on a Java application that connects with a cool drone simulation system. \n  This system uses a web-based RESTful API to give us all the details about a bunch of simulated drones – \n  everything from the basic essentials to the intricate specifics of cargo and real-time positional information. \n\n  Our goal is to make interacting with the drone simulator super easy and fun. \n  How? By building a sleek Graphical User Interface (GUI) using Java. \n  This GUI won't just fetch the data; it'll present it in a way that's easy to understand and looks great.\n\n  Our team was able to design a GUI that went beyond expectations. \n  It gives users a smooth and awesome experience with the drone simulation. \n\n  Join us in making drone simulation cooler and more accessible with the power of Java! ");
		txtrWelcomeToOur.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 17));
		txtrWelcomeToOur.setBounds(30, 100, 1030, 350);
		aboutPanel.add(txtrWelcomeToOur);
		
		JPanel teamPanel = new JPanel();
		teamPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(19, 102, 131), new Color(19, 102, 131), new Color(19, 102, 131), new Color(19, 102, 131)));
		teamPanel.setBackground(new Color(25, 131, 168));
		contentPanel.add(teamPanel, "name_287528452812400");
		teamPanel.setLayout(null);
		
		JLabel iconBackend = new JLabel("");
		iconBackend.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/backend1.png")));
		iconBackend.setHorizontalAlignment(SwingConstants.CENTER);
		iconBackend.setBounds(50, 80, 150, 150);
		teamPanel.add(iconBackend);
		
		JLabel lblOurTeam = new JLabel("Our Team");
		lblOurTeam.setHorizontalAlignment(SwingConstants.LEFT);
		lblOurTeam.setForeground(Color.WHITE);
		lblOurTeam.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblOurTeam.setBounds(20, 30, 200, 50);
		teamPanel.add(lblOurTeam);
		
		JLabel lblBackend = new JLabel("Backend Developer");
		lblBackend.setHorizontalAlignment(SwingConstants.LEFT);
		lblBackend.setForeground(Color.WHITE);
		lblBackend.setFont(new Font("Tahoma", Font.ITALIC, 21));
		lblBackend.setBounds(50, 240, 200, 40);
		teamPanel.add(lblBackend);
		
		JLabel lblKoosha = new JLabel("Koosha Olad");
		lblKoosha.setHorizontalAlignment(SwingConstants.LEFT);
		lblKoosha.setForeground(Color.WHITE);
		lblKoosha.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblKoosha.setBounds(50, 300, 200, 40);
		teamPanel.add(lblKoosha);
		
		JLabel iconFrontend = new JLabel("");
		iconFrontend.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/frontend.png")));
		iconFrontend.setHorizontalAlignment(SwingConstants.CENTER);
		iconFrontend.setBounds(400, 80, 150, 150);
		teamPanel.add(iconFrontend);
		
		JLabel lblFrontend = new JLabel("Frontend Developers");
		lblFrontend.setHorizontalAlignment(SwingConstants.LEFT);
		lblFrontend.setForeground(Color.WHITE);
		lblFrontend.setFont(new Font("Tahoma", Font.ITALIC, 21));
		lblFrontend.setBounds(400, 240, 200, 40);
		teamPanel.add(lblFrontend);
		
		JLabel lblKartik = new JLabel("Kartik Riziya");
		lblKartik.setHorizontalAlignment(SwingConstants.LEFT);
		lblKartik.setForeground(Color.WHITE);
		lblKartik.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblKartik.setBounds(400, 300, 200, 40);
		teamPanel.add(lblKartik);

		JLabel lblHana = new JLabel("Hana Eltalawy");
		lblHana.setHorizontalAlignment(SwingConstants.LEFT);
		lblHana.setForeground(Color.WHITE);
		lblHana.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblHana.setBounds(400, 346, 200, 40);
		teamPanel.add(lblHana);
		
		JLabel iconDocumentation = new JLabel("");
		iconDocumentation.setIcon(new ImageIcon(Dashboard.class.getResource("/de/frauas/dronesimulation/app/ui/icon/droneGallery/documentation.png")));
		iconDocumentation.setHorizontalAlignment(SwingConstants.CENTER);
		iconDocumentation.setBounds(750, 80, 150, 150);
		teamPanel.add(iconDocumentation);
		
		JLabel lblDocumentationDevelopers = new JLabel("Documentation Developers");
		lblDocumentationDevelopers.setHorizontalAlignment(SwingConstants.LEFT);
		lblDocumentationDevelopers.setForeground(Color.WHITE);
		lblDocumentationDevelopers.setFont(new Font("Tahoma", Font.ITALIC, 21));
		lblDocumentationDevelopers.setBounds(750, 240, 300, 40);
		teamPanel.add(lblDocumentationDevelopers);
		
		JLabel lblTara = new JLabel("Tara Khoramnia");
		lblTara.setHorizontalAlignment(SwingConstants.LEFT);
		lblTara.setForeground(Color.WHITE);
		lblTara.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblTara.setBounds(50, 350, 200, 40);
		teamPanel.add(lblTara);
		
		JLabel lblReubin = new JLabel("Reubin Sam Thomas");
		lblReubin.setHorizontalAlignment(SwingConstants.LEFT);
		lblReubin.setForeground(Color.WHITE);
		lblReubin.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblReubin.setBounds(750, 300, 300, 40);
		teamPanel.add(lblReubin);
		
		
		
		
		
		
		


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
		lblBack.addMouseListener(new MouseAdapter() {
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
		lblRefresh.addMouseListener(new MouseAdapter() {
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
			
			droneModel.setText((String) "Typename: " + drone.getDroneType().getTypeName());
			droneSerial.setText((String) "Serial Number: " + drone.getSerialnumber());
			droneWeight.setText((String) "Weight: " + drone.getDroneType().getWeight());
			droneMaxSpeed.setText((String) "Max Speed: " + drone.getDroneType().getMax_speed());
			droneType.setText((String) "Carriage type: " + drone.getCarriageType());
			droneBattery.setText((String) "Battery Capacity: " + String.valueOf(drone.getDroneType().getBatterycapacity()));
			//droneBatteryCapacity.setText((String) "Battery Capacity: " + batteryPercentage + " %");
			droneCarriageWeight.setText((String) "Carriage weight: " + drone.getCarriageWeight());
			droneMaxCarriage.setText((String) "Max Carriage weight: " + drone.getDroneType().getMaxCarriage());
			
			dynamicSpeed.setText((String) "Speed: " + drone.getDroneDynamics().getSpeed());
			dynamicSerial.setText((String) "Serial Number: " + drone.getSerialnumber());
			dynamicRoll.setText((String) "Align Roll: " + drone.getDroneDynamics().getAlignRoll());
			dynamicPitch.setText((String) "Align Pitch: " + drone.getDroneDynamics().getAlignPitch());
			dynamicYaw.setText((String) "Align Yaw: " + drone.getDroneDynamics().getAlignYaw());
			dynamicLongitude.setText((String) "Longitude: " + drone.getDroneDynamics().getLongitude());
			dynamicLastSeen.setText((String) "Last Seen: " + drone.getDroneDynamics().getLastSeen());
			dynamicStatus.setText((String) "Status: " + drone.getDroneDynamics().getStatus());
			

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
					
					droneModel.setText((String) "Typename: " + drone.getDroneType().getTypeName());
					droneSerial.setText((String) "Serial Number: " + drone.getSerialnumber());
					droneWeight.setText((String) "Weight: " + drone.getDroneType().getWeight());
					droneMaxSpeed.setText((String) "Max Speed: " + drone.getDroneType().getMax_speed());
					droneType.setText((String) "Carriage type: " + drone.getCarriageType());
					droneBattery.setText((String) "Battery Capacity: " + String.valueOf(drone.getDroneType().getBatterycapacity()));
					//droneBatteryCapacity.setText((String) "Battery Capacity: " + batteryPercentage + " %");
					droneCarriageWeight.setText((String) "Carriage weight: " + drone.getCarriageWeight());
					droneMaxCarriage.setText((String) "Max Carriage weight: " + drone.getDroneType().getMaxCarriage());
					
					dynamicSpeed.setText((String) "Speed: " + drone.getDroneDynamics().getSpeed());
					dynamicSerial.setText((String) "Serial Number: " + drone.getSerialnumber());
					dynamicRoll.setText((String) "Align Roll: " + drone.getDroneDynamics().getAlignRoll());
					dynamicPitch.setText((String) "Align Pitch: " + drone.getDroneDynamics().getAlignPitch());
					dynamicYaw.setText((String) "Align Yaw: " + drone.getDroneDynamics().getAlignYaw());
					dynamicLongitude.setText((String) "Longitude: " + drone.getDroneDynamics().getLongitude());
					dynamicLastSeen.setText((String) "Last Seen: " + drone.getDroneDynamics().getLastSeen());
					dynamicStatus.setText((String) "Status: " + drone.getDroneDynamics().getStatus());
					
		
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
			iconDrone.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "AA108" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("Chroma Camera Drone")) {
			iconDrone.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "Chroma Camera Drone" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("D80")) {
			iconDrone.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "D80" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("Evo II")) {
			iconDrone.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "Evo II" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("F24 Pro")) {
			iconDrone.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "F24 Pro" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("HS100")) {
			iconDrone.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "HS100" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("Karma")) {
			iconDrone.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "Karma" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("PowerEgg X")) {
			iconDrone.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "PowerEgg X" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("S5C")) {
			iconDrone.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "S5C" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("Skydio 2")) {
			iconDrone.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "Skydio 2" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("Tello")) {
			iconDrone.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "Tello" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("Typhoon H Pro")) {
			iconDrone.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "Typhoon H Pro" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("Voyager 4")) {
			iconDrone.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "Voyager 4" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("X4 H107D")) {
			iconDrone.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "X4 H107D" + ".png")));
		} else if (drone.getDroneType().getTypeName()
				.equals("X5C")) {
			iconDrone.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "X5C" + ".png")));
		}
		
		if (drone.getCarriageType().equals("NOT")) {
			iconDroneExtra.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "droneNOT" + ".png")));
		} else if (drone.getCarriageType().equals("ACT")) {
			iconDroneExtra.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "droneACT" + ".png")));
		} else if (drone.getCarriageType().equals("SEN")) {
			iconDroneExtra.setIcon(new ImageIcon(Dashboard.class.getResource(iconPath + "droneGallery/" + "droneSEN" + ".png")));
		}
		
		
		
		// For Battery
		if (batteryPercentage == 0) {
			iconDroneBattery.setIcon(new ImageIcon(Dashboard.class.getResource(iconBattery0)));
		} else if (batteryPercentage > 0 && batteryPercentage <= 35) {
			iconDroneBattery.setIcon(new ImageIcon(Dashboard.class.getResource(iconBattery25)));
		} else if (batteryPercentage > 35 && batteryPercentage <= 60) {
			iconDroneBattery.setIcon(new ImageIcon(Dashboard.class.getResource(iconBattery50)));
		} else if (batteryPercentage > 60 && batteryPercentage <= 99) {
			iconDroneBattery.setIcon(new ImageIcon(Dashboard.class.getResource(iconBattery75)));
		} else if (batteryPercentage == 100) {
			iconDroneBattery.setIcon(new ImageIcon(Dashboard.class.getResource(iconBattery100)));
		}
		
		
		// For Speedometer
		if (Integer.valueOf(drone.getDroneDynamics().getSpeed()) <= 10) {
			iconDroneSpeed.setIcon(new ImageIcon(Dashboard.class.getResource(speed1)));
		} else if (Integer.valueOf(drone.getDroneDynamics().getSpeed()) <= 20) {
			iconDroneSpeed.setIcon(new ImageIcon(Dashboard.class.getResource(speed2)));
		} else if (Integer.valueOf(drone.getDroneDynamics().getSpeed()) <= 30) {
			iconDroneSpeed.setIcon(new ImageIcon(Dashboard.class.getResource(speed3)));
		} else if (Integer.valueOf(drone.getDroneDynamics().getSpeed()) <= 40) {
			iconDroneSpeed.setIcon(new ImageIcon(Dashboard.class.getResource(speed4)));
		} else if (Integer.valueOf(drone.getDroneDynamics().getSpeed()) <= 50) {
			iconDroneSpeed.setIcon(new ImageIcon(Dashboard.class.getResource(speed5)));
		} else {
			iconDroneSpeed.setIcon(new ImageIcon(Dashboard.class.getResource(speed6)));
		}
		
		
		// For Switch
		if (drone.getDroneDynamics().getStatus().equals("ON")) {
			iconDroneStatus.setIcon(new ImageIcon(Dashboard.class.getResource(iconSwitchOn)));
		} else {
			iconDroneStatus.setIcon(new ImageIcon(Dashboard.class.getResource(iconSwitchOff)));
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
}
