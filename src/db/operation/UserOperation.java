package db.operation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import beans.User;

import db.connection.ConnectionStatement;
import db.connection.PublicConnection;

public class UserOperation{

	//�����ݿ�ȡ�����ӣ���ʼ��statement
	private void open(ConnectionStatement connectionStatement){
		try {
			//�����ݿ�ȡ�����ӣ��������ȴ�ʱ��Ϊ1000����
			connectionStatement.connection = PublicConnection.getConnection();
			
			//��ʼ��statement
			if(connectionStatement.connection != null){
				connectionStatement.statement = connectionStatement.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			}
			else{
				System.out.println("no available connection in ConnectionPool, wait......");
			}
		}catch(SQLException e){			
			System.out.println("Exception when open().....");
			e.printStackTrace();
		}
			
	}
	
	//�黹Connection
	private void close(ConnectionStatement connectionStatement){
		if(connectionStatement.statement != null)
			try {
				connectionStatement.statement.close();
			} catch (SQLException e) {
				System.out.println("Exception when close().....");
				e.printStackTrace();			
			}
		
		if(connectionStatement.connection != null)
			PublicConnection.freeConnection(connectionStatement.connection);
	}
	
	

	public User loginCheck(String username, String password) {
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
					user.resident = user.username;
					
					//TODO
					//��������״̬
					
//					java.util.Date date = new Date();
//					java.sql.Timestamp time = new Timestamp(date.getTime());
//					
//					sqlString = "insert into " + onlineUserTable + " (username, location) values('" + username + "', " + location + ")";
//					connStmt.statement.executeUpdate(sqlString);
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

	
	public boolean logout(String username) {
		ConnectionStatement connStmt = new ConnectionStatement();
		open(connStmt);
		
		boolean result = false;
		
		if(connStmt.connection != null){
			String sqlString = "";
			
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
