package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import client.Client;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel menuPanel;
	private JPanel mainPanel;
	
	private Client client;


	/**
	 * Create the frame.
	 */
	public MainFrame(Client client) {
		this.client = client;
		this.setTitle("智能小区住户服务");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menuPanel = new JPanel();
		menuPanel.setBounds(10, 10, 145, 441);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);
		
		JButton alarmBtn = new JButton("\u62A5\u8B66");
		alarmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getClient().opAlarm();
			}
		});
		alarmBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		alarmBtn.setBounds(10, 10, 120, 30);
		menuPanel.add(alarmBtn);
		
		JButton chatBtn = new JButton("\u5B9E\u65F6\u5BF9\u8BB2");
		chatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		chatBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		chatBtn.setBounds(10, 61, 120, 30);
		menuPanel.add(chatBtn);
		
		JButton announcementBtn = new JButton("\u67E5\u770B\u901A\u77E5");
		announcementBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		announcementBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		announcementBtn.setBounds(10, 113, 120, 30);
		menuPanel.add(announcementBtn);
		
		mainPanel = new JPanel();
		mainPanel.setBounds(165, 10, 559, 441);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		customInit();
	}
	
	private void customInit(){
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				client.close();
			}
		});
	}
	
	private Client getClient(){
		return this.client;
	}

}
