package db.operation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.Announcement;
import beans.User;

import db.connection.ConnectionStatement;

public class AnnouncementOperation extends DBOperation {

	public static List<Announcement> getAnnouncements() {
		ConnectionStatement connStmt = new ConnectionStatement();
		ResultSet rs = null;
		open(connStmt);
		
		List<Announcement> result = new ArrayList<Announcement>();
		
		if(connStmt.connection != null){
			String sqlString = "select * from announcement order by create_time desc";
			
			try {
				rs = connStmt.statement.executeQuery(sqlString);
			
				while(rs.next()){
					Announcement announcement = new Announcement();
					announcement.id = rs.getInt("id");
					announcement.title = rs.getString("title");
					announcement.content = rs.getString("content");
					
					Timestamp ts = rs.getTimestamp("create_time");
					Date d = new Date();
					d.setTime(ts.getTime());
					announcement.createTime = d;
					
					result.add(announcement);
				}		
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			close(connStmt);
		}
		return result;
	}
	
}
