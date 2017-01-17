package devices;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import beans.request;
import config.config;

public class deviceManager {
	public static long deviceCount = 0;
    public static List<device> devs = new LinkedList<device>();
    public static void start(){
    	try {
    		/*test code starts*/
			ServerSocket devServer = new ServerSocket(config.devicePort);
			FileInputStream fs = new FileInputStream("fakePacket.txt");
			BufferedReader ins = new BufferedReader(new InputStreamReader(fs));
			request req = new request(ins);
			fs.close();
			/*test code ends*/
			while(true){
	    		if (deviceCount < config.maxDevices){ 		    
	    			devs.add(new device(devServer.accept()));
	    		}
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   	
    }
    
}
