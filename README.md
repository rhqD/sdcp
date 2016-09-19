#sdcp
一套用于控制简单联网设备的协议设计和实现

##基本设计：
服务器的线程池模型大致为实现以下几点需求：
- 1.在访问量低的情况下尽可能地让每一个用户独享一个线程
- 2.在访问量较多的情况下，控制同时为用户提供服务的线程的数量，防止线程过多导致线程间切换的花费过多。
- 3.对服务完的线程进行阻塞操作，防止继续占用资源，但同时避免频繁地进行线程的创建和释放，提高线程的重用率。
- 4.防止过多线程在线程池中等待过长时间。

##centralSystem 部分代码
```java
    public class centralSystem {
    public static Queue<worker> restRoom = new LinkedList<worker>();
    public static Queue<worker> workShop = new LinkedList<worker>();
    public static Queue<Socket> waitingRoom = new LinkedList<Socket>();
    public static void serve(Socket client){
    	synchronized(restRoom){
    		if (restRoom.isEmpty()) {//没有空闲线程，进行后续处理
    			synchronized(workShop){
    				if (workShop.size() < config.maxWorkers ){//当前工作线程数量没有超过预设阀值，则分配新线程
    					workShop.add(new worker(new procImplemention(), client));//返回点
    				} else {//当前工作线程超过预设阀值
    					synchronized(waitingRoom){
    						if (waitingRoom.size() > config.maxPacksInWait){//当前等待客户数量超过预设阀值，拒绝服务
    							try {
									client.close();//返回点
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();//返回点
								}
    						} else {//当前等待客户数量小于预设阀值，进入等待线程
    							waitingRoom.add(client);//返回点
    						}
    					}
    				}
    			}
    		} else {//空闲线程池有可服务线程。直接进行服务
    			worker him = restRoom.poll();
    			workShop.add(him);   			
    			him.holder.notify();//返回点
    		}
    	}
    }
}```
