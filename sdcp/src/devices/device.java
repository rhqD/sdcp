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
	private String name;
	
	public boolean isInited(){
		return this.inited;
	}
	
	public device(Socket s){
		dev = s;	
		System.out.println("come on");
		try {
			dev.setReceiveBufferSize(4096);
			dev.setSendBufferSize(4096);
			dev.setSoTimeout(1000);
			ins = new BufferedReader(new InputStreamReader(s.getInputStream()));
			ous = s.getOutputStream();
			new Thread(this).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	private void init() throws IOException{
	  request req = new request(this.ins);
	  initWithRequest(req);
	  if (!this.inited){
		  this.dev.close();
		  this.ins = null;
		  this.ous = null;
	  }
	}
	
	private void initWithRequest(request req){
		if(req.type.equals('C')){
			return;
		}
		if(req.deviceName == null){
			return;
		}
		if(req.devicePassword == null){
			return;
		}
		if (!deviceManager.checkPassword(req.deviceName, req.devicePassword)){
			return;
		}
		this.name = req.deviceName;
		this.inited = true;
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
