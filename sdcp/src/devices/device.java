package devices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class device implements Runnable{

	private Socket dev;
	private BufferedReader ins;
	private OutputStream ous;
	public device(Socket s){
		dev = s;	
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
		
	}
}
