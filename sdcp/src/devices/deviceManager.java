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
		
	}
    
}
