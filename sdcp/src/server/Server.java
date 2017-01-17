package server;

import java.io.IOException;
import java.net.ServerSocket;
import config.config;
import devices.deviceManager;
import devices.waker;
import interfaces.procedure;
import interfaces.procedureFactory;
import logger.logger;
import supervisor.supervisor;
import visualDataSource.eChartHelper;
import centralSystem.centralSystem;

public class Server{
	private String configFile;
	private procedureFactory factory;
	public Server(procedureFactory f, String fileName){
		configFile = fileName;
		factory = f;
	}
    public void start() {
    	config.init(configFile);
    	try {
			ServerSocket server = new ServerSocket(config.port);		
			deviceManager dm = new deviceManager();
			waker wk = new waker();
			System.out.println("服务器启动");
			System.out.println("设备服务器启动");
			logger.info("服务器启动");
			logger.info("设备服务器启动");			
			new supervisor();
			new eChartHelper();
			while(true){
				centralSystem.serve(server.accept(), factory);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
