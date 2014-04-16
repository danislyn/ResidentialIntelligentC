package client;

import gui.InstantChatFrame;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import db.operation.MessageOperation;
import db.operation.UserOperation;

import beans.Message;
import beans.MessageType;
import beans.User;

public class Client {
	
	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 8001;

	private User user;
	
	private Socket socket;
	private ObjectInputStream objInput;
	private ObjectOutputStream objOutput;
	
	private Thread listeningThread;
	
	private InstantChatFrame chatFrame;
	
	public Client(User user){
		this.user = user;
	}
	
	public void start(){
		try {
			socket = new Socket(SERVER_IP, SERVER_PORT);
			
			objOutput = new ObjectOutputStream(socket.getOutputStream());
			objInput = new ObjectInputStream(socket.getInputStream());
			System.out.println("连接成功");
			
			//启动监听线程
			listeningThread = new Thread(new ListeningThread());
			listeningThread.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			listeningThread.stop();
			
			objOutput.close();
			objInput.close();
			socket.close();
			
			//logout
			UserOperation.logout(user.username);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void sendRequest(Message msg){
		try {
			objOutput.writeObject(msg);
			objOutput.flush();
			
			//save message
			if(msg.type != MessageType.LOGIN){
				MessageOperation.saveMessage(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//内部类，用来实时监听服务端的responding message
	class ListeningThread implements Runnable{

		@Override
		public void run() {
			try {
				Message msg = null;
				
				while(true){
					msg = (Message) objInput.readObject();
					if(msg != null){
						InstantChatFrame chatFrame = startP2P();
						chatFrame.refreshContent(msg);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	//=====================================================
	public void sendLoginMessage(){
		Message msg = ClientUtil.getLoginMessage(user);
		sendRequest(msg);
	}
	
	public InstantChatFrame startP2P(){
		if(chatFrame == null){
			chatFrame = new InstantChatFrame(this);
		}
		chatFrame.setVisible(true);
		return chatFrame;
	}
	
	
	//=====================================================
	//客户端操作
	public void opAlarm(){
		Message msg = ClientUtil.getAlarmMessage(user);
		sendRequest(msg);
	}
	
	public Message opChatMessage(String content){
		Message msg = ClientUtil.getChatMessage(user, content);
		sendRequest(msg);
		return msg;
	}
	

	
}
