package db.operation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import beans.User;

import db.connection.ConnectionStatement;
import db.connection.PublicConnection;

public class UserOperation extends DBOperation{

	public static User loginCheck(String username, String password) {
		ConnectionStatement connStmt = new ConnectionStatement();
		ResultSet rs = null;
		open(connStmt);
		
		User user = null;
		
		if(connStmt.connection != null){
			String sqlString = "select * from user where username='" + username + "' and password='" + password +"'";
			
			try {
				rs = connStmt.statement.executeQuery(sqlString);
			
				if(rs.next()){
					user = new User();
					user.id = rs.getInt("id");
					user.username = rs.getString("username");
					user.password = rs.getString("password");
					user.resident = user.username;  //暂时和username相同
					
					Date now = new Date();
					user.online = 1;
					user.lastLogin = now;
					
					//更新在线状态
					Timestamp ts = new Timestamp(now.getTime());
					sqlString = "update user set online=1, last_login='" + ts + "' where id=" + user.id;
					connStmt.statement.executeUpdate(sqlString);
				}		
			} catch (SQLException e) {
				user = null;
				System.out.println("Exception when handling loginCheck......");
				e.printStackTrace();
			}
		
			close(connStmt);
		}
		return user;
	}

	
	public static boolean logout(String username) {
		ConnectionStatement connStmt = new ConnectionStatement();
		open(connStmt);
		
		boolean result = false;
		
		if(connStmt.connection != null){
			String sqlString = "update user set online=0 where username='" + username + "'";
			
			try {
				connStmt.statement.executeUpdate(sqlString);
				result = true;
				
			} catch (SQLException e) {
				result = false;
				System.out.println("Exception when handling logout......");
				e.printStackTrace();
			}
			
			close(connStmt);
		}
		return result;
	}

}
