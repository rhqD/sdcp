package server;

import java.io.IOException;
import java.net.ServerSocket;
import config.config;
import centralSystem.centralSystem;

public class mainServer {
    public static void main(String args[]) {
    	try {
			ServerSocket server = new ServerSocket(config.port);
			while(true){
				centralSystem.serve(server.accept());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
