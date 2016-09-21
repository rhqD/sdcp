package config;

public class config {
	//后期将从配置文件中读取，以下为没有配置文件情况下的默认值
    public static int maxWorkers = 100;//最多有多少个服务线程
    public static int maxClientsInWait = 200;//最多有多少客户同时等待服务
    public static int port = 5428;//用户服务端口
    public static int devicePort = 5427;//设备和服务器建立长连接的TCP端口
    public static int maxDevices = 100;//最多有多少台设备与服务器建立长连接
    public static int waitTime = 2;//线程在线程池中的最长等待时间
    public static int supervisorCheckInterval = 60;//监督现成每隔多长时间进行一次检查
    public static void init(String fileName){
    	//读取配置文件
    }
}
