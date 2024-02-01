package de.frauas.dronesimulation.app.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

public class uiHandler extends JFrame {

	public uiHandler() {
		setTitle("Drone Simulation Interface");
		setSize(1300, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		JLabel label = new JLabel("Hello, Drone!");
		JButton button = new JButton("Refresh");

		panel.add(label);
		panel.add(button);

		add(panel);
	}

}
