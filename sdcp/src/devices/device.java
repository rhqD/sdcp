package devices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import logger.logger;

public class device implements Runnable{

	private Socket dev;
	private BufferedReader ins;
	private OutputStream ous;
	private int minutes = 0;
	public device(Socket s){
		dev = s;	
		System.out.println("come on");
		try {
			dev.setReceiveBufferSize(4096);
			dev.setSendBufferSize(4096);
			dev.setSoTimeout(2000);
			ins = new BufferedReader(new InputStreamReader(s.getInputStream()));
			ous = s.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				synchronized(this.ous){
					this.minutes++;
					this.ous.write(20);
					this.ous.flush();
				}
				Thread.sleep(60000);
			}
		} catch (IOException e){
			logger.error("connection with device has been shutdown and it lasts " + this.minutes + " minutes");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
