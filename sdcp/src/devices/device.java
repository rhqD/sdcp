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
public class device{

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
	
	public boolean isInited(){
		return this.inited;
	}
	
	public String getName(){
		return this.name;
	}

}
