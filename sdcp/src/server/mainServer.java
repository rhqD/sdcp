package server;

import java.io.IOException;
import java.net.ServerSocket;
import config.config;
import logger.logger;
import supervisor.supervisor;
import centralSystem.centralSystem;

public class mainServer {
    public static void main(String args[]) {
    	try {
			ServerSocket server = new ServerSocket(config.port);
			System.out.println("服务器启动");
			logger.info("服务器启动");
			new supervisor();
			while(true){
				centralSystem.serve(server.accept());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
