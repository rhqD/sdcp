package visualDataSource;

import centralSystem.centralSystem;

public class eChartHelper implements Runnable{
	public eChartHelper(){
		new Thread(this).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
	        try {				
				Thread.sleep(10000);
				centralSystem.writeEchartLogger();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

}
