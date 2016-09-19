package centralSystem;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

import beans.worker;
import config.config;
import implemention.procImplemention;

public class centralSystem {
    private static Queue<worker> restRoom = new LinkedList<worker>();
    private static Queue<worker> workShop = new LinkedList<worker>();
    private static Queue<Socket> waitingRoom = new LinkedList<Socket>();
    public static void serve(Socket client){
    	System.out.println("客户进入服务流程");
    	synchronized(restRoom){
    		if (restRoom.isEmpty()) {//没有空闲线程，进行后续处理
    			System.out.println("没有空闲线程");
    			synchronized(workShop){
    				if (workShop.size() < config.maxWorkers ){//当前工作线程数量没有超过预设阀值，则分配新线程
    					System.out.println("当前工作线程数量没有超过预设阀值，分配新线程");
    					workShop.add(new worker(new procImplemention(), client));//返回点
    				} else {//当前工作线程超过预设阀值
    					System.out.println("当前工作线程超过预设阀值");
    					synchronized(waitingRoom){
    						if (waitingRoom.size() > config.maxPacksInWait){//当前等待客户数量超过预设阀值，拒绝服务
    							System.out.println("当前等待客户数量超过预设阀值，拒绝服务");
    							try {
									client.close();//返回点
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();//返回点
								}
    						} else {//当前等待客户数量小于预设阀值，进入等待线程
    							System.out.println("当前等待客户数量小于预设阀值，进入等待线程");
    							waitingRoom.add(client);//返回点
    						}
    					}
    				}
    			}
    		} else {//空闲线程池有可服务线程。直接进行服务
    			System.out.println("空闲线程池有可服务线程。直接进行服务");
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
    	restRoom.add(him);
    }
}
