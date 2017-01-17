package devices;

public class waker implements Runnable{
   
	public waker(){
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("waking " + deviceManager.devs.size() + " devices");
			for(int i = 0; i < deviceManager.devs.size();i++){
				deviceManager.devs.get(i).wakeUp();
				System.out.println("waking device " + deviceManager.devs.get(i).getName());
			}			
		}
	}

}
