package test;

import implemention.procFactoryImplemention;
import server.Server;

public class testServer {
    public static void main(String args[]){
    	new Server(new procFactoryImplemention(),"").start();
    }
}
