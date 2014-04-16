package client;

import java.util.Date;

import beans.Message;
import beans.MessageType;
import beans.User;

public class ClientUtil {

	//���ڵ�¼֪ͨ
	public static Message getLoginMessage(User user){
		Message msg = new Message();
		//msg.id
		msg.sourceUsername = user.username;
		msg.sourceResident = user.resident;
		msg.destUsername = "ADMIN";
		msg.type = MessageType.LOGIN;
		msg.content = "��¼";
		msg.createTime = new Date();
		//msg.replyMsgId
		return msg;
	}
	
	//���ڱ���֪ͨ
	public static Message getAlarmMessage(User user){
		Message msg = new Message();
		//msg.id
		msg.sourceUsername = user.username;
		msg.sourceResident = user.resident;
		msg.destUsername = "ADMIN";
		msg.type = MessageType.ALARM;
		msg.content = "����";
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
		msg.content = "����";
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
