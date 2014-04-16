package gui;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

import db.operation.AnnouncementOperation;

import beans.Announcement;

public class AnnouncementPanel extends JPanel {
	
	private JTextPane textPane;

	/**
	 * Create the panel.
	 */
	public AnnouncementPanel() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 535, 420);
		add(scrollPane);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("ËÎÌו", Font.PLAIN, 14));
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);

	}
	
	public void updateContent(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String contentText = "";
		
		List<Announcement> list = AnnouncementOperation.getAnnouncements();
		for(Announcement a : list){
			contentText += "                    " + a.title + "\n";
			contentText += "                [" + sdf.format(a.createTime) + "]\n";
			contentText += "  " + a.content + "\n\n\n";
		}
		
		textPane.setText(contentText);
		textPane.updateUI();
		repaint();
	}
	
}
