package devices;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

import config.config;

public class deviceManager {
	public static long deviceCount = 0;
    public static List<device> devs = new LinkedList<device>();
    public static void start(){
    	try {
			ServerSocket devServer = new ServerSocket(config.devicePort);
			while(true){
	    		if (deviceCount < config.maxDevices){
	    			new device(devServer.accept());
	    		}
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   	
    }
    
}
