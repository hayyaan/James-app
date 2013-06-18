import java.net.*;

public class Network {
	public int listenPort;
	public InetAddress myIp;
	public int sendPort;
	
	public Thread receive;
	
	public Network(){
		listenPort = 9872;
		sendPort = 8601;
		try {
			myIp = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMsg(String message){
		
	}
	
	public void startReceive(){
		receive = new Thread(new Receiver(listenPort));
		receive.start();
	}	
	
}

class Receiver implements Runnable{
	int listenPort;
	public Receiver(int _port){
		listenPort = _port;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		byte[] recBytes = new byte[1024];
		while (true){
			try{
				DatagramPacket packet = new DatagramPacket(recBytes,recBytes.length);
				DatagramSocket socket = new DatagramSocket(listenPort);
				socket.receive(packet);
				socket.close();
				String mess = new String(packet.getData(),0,packet.getLength());
				mess.trim();
//				System.out.println("message received "+mess);
				Thread executeReceive = new Thread(new ExecuteReceived(mess));
				executeReceive.start();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
}

class ExecuteReceived implements Runnable{
	String message;
	
	public ExecuteReceived(String _ms){
		message = _ms;
	}
	
	public void run(){
		System.out.println(("Received message: "+message));
		Parse.getInstance().parse(message);
	}
	
}
