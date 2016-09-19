package supervisor;

import java.util.Date;

import config.config;
import centralSystem.centralSystem;

public class supervisor implements Runnable{

	public supervisor(){
		System.out.println("监督线程启动");
		new Thread(this).start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
		    while(true){			    	
				Thread.sleep(config.supervisorCheckInterval * 1000);
				centralSystem.disposeRester();
		    }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
