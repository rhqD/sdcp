package beans;

import java.net.Socket;
import java.util.Date;

import interfaces.procedure;
import logger.logger;
import centralSystem.centralSystem;

public class worker implements Runnable{
    public Object holder = new Object();
    private Socket client;
    private procedure proc;
    public Date restTime;
    public worker(procedure p, Socket s){
    	this.proc = p;
    	this.client = s;
    	new Thread(this).start();
    }
    
	@Override
	public void run() {
		// TODO Auto-generated method stub		
		try {
			while(true){
				if (client == null){
					synchronized(holder){
						restTime = new Date();
						//logger.info("进入restRoom");
						centralSystem.rest(this);
						holder.wait();
					}				
				} else {
					this.proc.process(client);
                    client = centralSystem.fetchOne();	
                    if (client != null){
                    	//logger.info("领取新任务");
                    }                
				}				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setClient(Socket newClient){
		this.client = newClient;
	}
}
