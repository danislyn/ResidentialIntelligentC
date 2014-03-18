package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import beans.Message;
import beans.User;

public class Client {
	
	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 8001;

	private User user;
	
	private Socket socket;
	private ObjectInputStream objInput;
	private ObjectOutputStream objOutput;
	
	private Thread listeningThread;
	
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void sendRequest(Message msg){
		try {
			objOutput.writeObject(msg);
			objOutput.flush();
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
						System.out.println(msg.toString());
						//TODO
						
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	//=====================================================
	//客户端操作
	public void opAlarm(){
		Message msg = ClientUtil.getAlarmMessage(user);
		sendRequest(msg);
	}
	
	
	
	

	
}
