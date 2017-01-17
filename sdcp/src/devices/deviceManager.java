package devices;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import beans.request;
import config.config;

public class deviceManager implements Runnable{
	public static long deviceCount = 0;
    public static List<device> devs = new LinkedList<device>();
    
    public deviceManager(){
    	new Thread(this).start();
    }
    
    public static boolean checkPassword(String name, String password){
    	if (name.equals("lampA1") && password.equals("19940227")){
    		return true;
    	}
    	return false;
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
    		AsynchronousServerSocketChannel devServer = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(config.devicePort));
    		/*test code starts*/   		
//			FileInputStream fs = new FileInputStream("fakePacket.txt");
//			BufferedReader ins = new BufferedReader(new InputStreamReader(fs));
//			request req = new request(ins);
//			fs.close();
			/*test code ends*/
			devServer.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() { 
	  
	            @Override  
	            public void completed(AsynchronousSocketChannel result, Object attachment) {            	
	                new device(result);	               
	                devServer.accept(null, this);
	            }  
	  
	            @Override  
	            public void failed(Throwable exc, Object attachment) {  
	                System.out.print("Server failed...." + exc.getCause());  
	            }  
	        }); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   	
	}
    
}
