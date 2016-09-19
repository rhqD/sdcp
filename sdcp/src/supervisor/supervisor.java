package supervisor;

public class supervisor implements Runnable{

	public supervisor(){
		new Thread(this).start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
