package client;

import java.util.Date;

import beans.Message;
import beans.MessageType;
import beans.User;

public class ClientUtil {

	public static Message getAlarmMessage(User user){
		Message msg = new Message();
		//msg.id
		msg.sourceUsername = user.username;
		msg.sourceResident = user.resident;
		msg.destUsername = "ADMIN";
		msg.type = MessageType.ALARM;
		msg.content = "±¨¾¯";
		msg.createTime = new Date();
		//msg.replyMsgId
		return msg;
	}
	
}
