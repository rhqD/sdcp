package devices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import beans.request;
import logger.logger;
import java.util.regex.*;
public class device implements Runnable{

	private Socket dev;
	private BufferedReader ins;
	private OutputStream ous;
	private boolean inited = false;
	private int minutes = 0;
	public device(Socket s){
		dev = s;	
		System.out.println("come on");
		try {
			dev.setReceiveBufferSize(4096);
			dev.setSendBufferSize(4096);
			dev.setSoTimeout(1000);
			ins = new BufferedReader(new InputStreamReader(s.getInputStream()));
			ous = s.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	private void init() throws IOException{
	  request req = new request(this.ins);
	  initWithRequest(req);
	}
	
	private void initWithRequest(request req){
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
