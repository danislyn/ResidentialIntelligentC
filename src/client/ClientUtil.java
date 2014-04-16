package client;

import java.util.Date;

import beans.Message;
import beans.MessageType;
import beans.User;

public class ClientUtil {

	//用于登录通知
	public static Message getLoginMessage(User user){
		Message msg = new Message();
		//msg.id
		msg.sourceUsername = user.username;
		msg.sourceResident = user.resident;
		msg.destUsername = "ADMIN";
		msg.type = MessageType.LOGIN;
		msg.content = "登录";
		msg.createTime = new Date();
		//msg.replyMsgId
		return msg;
	}
	
	//用于报警通知
	public static Message getAlarmMessage(User user){
		Message msg = new Message();
		//msg.id
		msg.sourceUsername = user.username;
		msg.sourceResident = user.resident;
		msg.destUsername = "ADMIN";
		msg.type = MessageType.ALARM;
		msg.content = "报警";
		msg.createTime = new Date();
		//msg.replyMsgId
		return msg;
	}
	
/*	public static Message getDefaultChatMessage(User user){
		Message msg = new Message();
		//msg.id
		msg.sourceUsername = user.username;
		msg.sourceResident = user.resident;
		msg.destUsername = "ADMIN";
		msg.type = MessageType.CHAT;
		msg.content = "在吗？";
		msg.createTime = new Date();
		//msg.replyMsgId
		return msg;
	}*/
	
	public static Message getChatMessage(User user, String content){
		Message msg = new Message();
		//msg.id
		msg.sourceUsername = user.username;
		msg.sourceResident = user.resident;
		msg.destUsername = "ADMIN";
		msg.type = MessageType.CHAT;
		msg.content = content;
		msg.createTime = new Date();
		//msg.replyMsgId
		return msg;
	}
	
}
