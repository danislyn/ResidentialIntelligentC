package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import client.Client;

import beans.User;

import db.operation.UserOperation;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class LoginPane extends JPanel {
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	private JFrame currentFrame;

	/**
	 * Create the panel.
	 */
	public LoginPane(JFrame parent) {
		this.currentFrame = parent;
		setLayout(null);
		
		JLabel usernameLabel = new JLabel("\u623F\u53F7");
		usernameLabel.setFont(new Font("����", Font.PLAIN, 16));
		usernameLabel.setBounds(62, 88, 54, 24);
		add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(126, 88, 106, 26);
		add(usernameField);
		usernameField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("\u5BC6\u7801");
		passwordLabel.setFont(new Font("����", Font.PLAIN, 16));
		passwordLabel.setBounds(62, 137, 54, 24);
		add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(126, 137, 106, 26);
		add(passwordField);
		
		JButton loginButton = new JButton("\u767B\u5F55");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username = usernameField.getText();
				String password = passwordField.getText();
				
				User loginUser = UserOperation.loginCheck(username, password);
				
				if(loginUser != null){
					//���ӷ�����
					Client client = new Client(loginUser);
					client.start();
					
					//�رյ�ǰ
					setVisible(false);
					currentFrame.dispose();
					
					//����������
					MainFrame mainFrame = new MainFrame(client);
					mainFrame.setVisible(true);
					
					//����LOGIN֪ͨ��Ϣ
					client.sendLoginMessage();
				}
				else{
					JOptionPane.showMessageDialog(null, "�����������������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					usernameField.setText("");
					passwordField.setText("");
				}
				
			}
		});
		loginButton.setBounds(126, 186, 93, 23);
		add(loginButton);
		
		JLabel label = new JLabel("\u667A\u80FD\u5C0F\u533A\u4F4F\u6237\u767B\u5F55");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("����", Font.PLAIN, 24));
		label.setBounds(33, 22, 228, 36);
		add(label);

	}
}
