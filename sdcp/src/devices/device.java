package devices;

import java.net.Socket;

public class device implements Runnable{

	private Socket dev;
	public device(Socket s){
		this.dev = s;
		new Thread(this).start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
    
}
