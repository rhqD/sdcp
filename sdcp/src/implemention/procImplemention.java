package implemention;

import java.io.IOException;
import java.net.Socket;

import interfaces.procedure;
import logger.logger;
import centralSystem.centralSystem;

public class procImplemention implements procedure{
	
	public procImplemention(){
		
	}

	@Override
	public void process(Socket client) {
		// TODO Auto-generated method stub
		try {
			//logger.info("开始服务");
			centralSystem.clientCount++;
			Thread.sleep(1000);
			client.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
