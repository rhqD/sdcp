package visualDataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class eChartDataLogger {
    public static FileWriter writer;
    static{
    	try {
			writer = new FileWriter("./data.txt", true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("logger 生成失败");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("logger 生成失败");
		}
    }
    public static void log(int a, int b, int c, long d, long e, long f, long g) throws IOException{
    	if (writer != null){
    		writer.write(a+ " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + "\r\n");
    		writer.flush();  		
    	}
    }
}
