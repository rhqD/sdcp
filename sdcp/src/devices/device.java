package devices;

import java.net.Socket;

public class device implements Runnable{

	private Socket dev;
	public device(Socket s){
		this.dev = s;
		new Thread(this).start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//为了将心跳包数据和用户数据区分开，在发送时，对socket的inputStream进行互斥访问
	}
    
}
