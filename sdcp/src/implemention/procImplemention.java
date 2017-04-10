package implemention;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

import beans.request;
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
			logger.info("开始服务");
			centralSystem.clientCount++;
			request req = new request(client);			
			Class[] cArg = new Class[1];
			cArg[0] = request.class;
			Method method = procImplemention.class.getMethod("dealWithType" + req.type, cArg);
			method.invoke(null, req);
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void dealWithTypeA(request req){
	}
	
	public static void dealWithTypeB(request req){
		
	}

	public static void dealWithTypeD(request req){
	    
    }
    
	public static void dealWithTypeE(request req){
    	
    }

}
