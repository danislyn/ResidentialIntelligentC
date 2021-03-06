package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JScrollPane;

import client.Client;

import beans.Message;
import beans.MessageType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InstantChatFrame extends JFrame {

	private JPanel contentPane;
	private JTextPane chatPane;
	private JTextPane replyPane;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	
	private Client client;
	private List<Message> messageList = new ArrayList<Message>();
	
	/**
	 * Create the frame.
	 */
	public InstantChatFrame(Client client) {
		this.client = client;
		this.setTitle("实时对讲");
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //MainFrame也会一起关
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 464, 250);
		contentPane.add(scrollPane);
		
		chatPane = new JTextPane();
		chatPane.setFont(new Font("宋体", Font.PLAIN, 14));
		chatPane.setEditable(false);
		scrollPane.setViewportView(chatPane);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 270, 394, 82);
		contentPane.add(scrollPane_1);
		
		replyPane = new JTextPane();
		replyPane.setFont(new Font("宋体", Font.PLAIN, 14));
		scrollPane_1.setViewportView(replyPane);
		
		JButton button = new JButton("\u53D1\u9001");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Message msg = getThis().client.opChatMessage(getThis().replyPane.getText());
				getThis().replyPane.setText("");
				refreshContent(msg);
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 14));
		button.setBounds(414, 322, 60, 30);
		contentPane.add(button);
	}
	
	private InstantChatFrame getThis(){
		return this;
	}
	
	//=======================================
	public void refreshContent(Message msg){
		String curText = chatPane.getText();
		chatPane.setText(curText + msg.sourceUsername + "(房号" + msg.sourceResident + ")  [" + msg.getFormatTime() + "]:\n" + "  " + msg.content + "\n\n");
		chatPane.updateUI();
		
		//设置滚动条最下
//		scrollPane.doLayout();
//		JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
//		if(scrollBar != null){
//			scrollBar.setValue(scrollBar.getMaximum());
//		}
		repaint();
	}
	
}
