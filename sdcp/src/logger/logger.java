package logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;

public class logger {
    public static PrintWriter writer;
    static{
    	try {
			writer = new PrintWriter(new FileOutputStream("./log.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("logger 生成失败");
		}
    }
    public static void info(String mes){
    	if (writer != null){
    		writer.println("[INFO] "+ mes);
    		writer.flush();
    	}
    }
    public static void error(String mes){
    	if (writer != null){
    		writer.println("[ERROR] "+ mes);
    		writer.flush();
    	}
    }
    public static void warn(String mes){
    	if (writer != null){
    		writer.println("[WARN] "+ mes);
    		writer.flush();
    	}
    }
}
