package beans;

import java.net.Socket;

import interfaces.procedure;

public class worker implements Runnable{
    public Object holder = new Object();
    private Socket client;
    private procedure proc;
    public worker(procedure p, Socket s){
    	this.proc = p;
    	this.client = s;
    	new Thread(this).start();
    }
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}