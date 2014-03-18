package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.Client;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	public Client client;

	/**
	 * Create the frame.
	 */
	public MainFrame(Client client) {
		this.client = client;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 800, 500);
		
		contentPane = new MainPane(this);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		customInit();
	}
	
	private void customInit(){
		//¼àÌý´°¿Ú¹Ø±Õ
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				client.close();
			}
		});
	}

}
