package centralSystem;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

import beans.worker;
import config.config;
import implemention.procImplemention;
import interfaces.procedureFactory;
import logger.logger;
import visualDataSource.eChartDataLogger;

public class centralSystem {
    private static Queue<worker> restRoom = new ConcurrentLinkedQueue<worker>();
    private static Queue<worker> workShop = new LinkedList<worker>();
    private static Queue<Socket> waitingRoom = new LinkedList<Socket>();
    private static long threadsCreated = 0;
    private static long threadsDestoryed = 0;
    public static long clientCount = 0;
    private static long refusedClientCount = 0;
    public static void serve(Socket client, procedureFactory factory){
    	synchronized(restRoom){
    		if (restRoom.isEmpty()) {//没有空闲线程，进行后续处理
    			//logger.info("没有空闲线程");
    			synchronized(workShop){
    				if (workShop.size() < config.maxWorkers ){//当前工作线程数量没有超过预设阀值，则分配新线程
    					logger.info("分配新线程");
    					threadsCreated++;
    					workShop.add(new worker(factory.createProcedure(), client));//返回点
    				} else {//当前工作线程超过预设阀值
    					synchronized(waitingRoom){
    						if (waitingRoom.size() > config.maxClientsInWait){//当前等待客户数量超过预设阀值，拒绝服务
    							logger.info("拒绝服务");
    							refusedClientCount++;
    							try {
									client.close();//返回点
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();//返回点
								}
    						} else {//当前等待客户数量小于预设阀值，进入等待线程
    							//logger.info("客户进入等待线程");
    							waitingRoom.add(client);//返回点
    						}
    					}
    				}
    			}
    		} else {//空闲线程池有可服务线程。直接进行服务
    			logger.info("空闲线程进行服务");
    			worker him = restRoom.poll();
    			workShop.add(him);   	
    			him.setClient(client);
    			synchronized(him.holder){
    			    him.holder.notify();//返回点
    			}
    		}
    	}
    }
    public static Socket fetchOne(){
    	return waitingRoom.poll();
    }
    public static void rest(worker him){
    	workShop.remove(him);
    	restRoom.add(him);
    }
    public static void writeEchartLogger() throws IOException{
    	eChartDataLogger.log(workShop.size(), restRoom.size(), waitingRoom.size(), threadsCreated, threadsDestoryed, clientCount, refusedClientCount);
    }
    public static void disposeRester(){
    	Date now = new Date();
    	Iterator it = restRoom.iterator();
    	try{
        	while(it.hasNext()){
        		worker him = (worker)it.next();
        		if (now.getTime() - him.restTime.getTime() > config.waitTime * 1000){
        			logger.info("超时回收");
        			threadsDestoryed++;
        			it.remove();
        		}
        	}
    	}catch(Exception e){
    		e.printStackTrace(logger.writer);
    	}

        
    }
}
