package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPane extends JPanel {
	
	private MainFrame mainFrame;

	/**
	 * Create the panel.
	 */
	public MainPane(MainFrame parent) {
		mainFrame = parent;
		setLayout(null);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(10, 10, 129, 456);
		add(menuPanel);
		menuPanel.setLayout(null);
		
		JButton alarmButton = new JButton("\u62A5\u8B66");
		alarmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.client.opAlarm();
			}
		});
		alarmButton.setBounds(10, 10, 109, 32);
		menuPanel.add(alarmButton);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(149, 10, 584, 456);
		add(contentPanel);
		contentPanel.setLayout(null);

	}
}
