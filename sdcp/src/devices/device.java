package devices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.net.StandardSocketOptions;
import beans.request;
import logger.logger;
import java.util.regex.*;
public class device implements Runnable{

	private AsynchronousSocketChannel ASC;
	private boolean inited = false;
	private int minutes = 0;
	private String name;
	private byte state = 0;
	public static byte INIT = 0;
	public static byte WAKING_UP = 1;
	public static byte WAKED = 2;
	public static byte DEAD = 3;
	
	public device(){
		
	}
	
	public device(AsynchronousSocketChannel s){
		this.ASC = s;
		try {
			this.ASC.setOption(StandardSocketOptions.SO_RCVBUF, 4096);
			this.ASC.setOption(StandardSocketOptions.SO_SNDBUF, 4096);
			new Thread(this).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public boolean isInited(){
		return this.inited;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void wakeUp(){
		this.state = device.WAKING_UP;
		this.ASC.write(ByteBuffer.wrap("wake up\n".getBytes()), this, new CompletionHandler<Integer, device>() { 
			  
            @Override  
            public void completed(Integer result, device attachment) {
            	attachment.state = device.WAKED;         
            }  
  
			@Override
			public void failed(Throwable exc, device attachment) {
				// TODO Auto-generated method stub
				attachment.state = device.DEAD;
			}  
        });
	}
	
	public static device processDeviceWithRequest(device d, request req){
		if(req.type.equals('C')){
			return d;
		}
		if(req.deviceName == null){
			return d;
		}
		if(req.devicePassword == null){
			return d;
		}
		if (!deviceManager.checkPassword(req.deviceName, req.devicePassword)){
			return d;
		}
		d.name = req.deviceName;
		d.inited = true;
		return d;
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
	
	private void init() throws IOException{		
		  request req = new request(this.ASC, this);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//device 的初始化工作放在单独的一个线程中完成，考虑采用新的形式，类似于Promise的实现
		try {
			this.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
