package client;

import beans.Message;
import beans.User;

public class ClientUtil {

	public static Message getAlarmMessage(User user){
		Message msg = new Message();
//		msg.sourceId
		msg.source = user.resident;
		msg.content = "±¨¾¯";
		
		return msg;
	}
	
}
