package visualDataSource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class eChartDataLogger {
    public static PrintWriter writer;
    private static FileOutputStream fs;
    static{
    	try {
    		fs = new FileOutputStream("./data.txt");
			writer = new PrintWriter(fs);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("logger 生成失败");
		}
    }
    public static void log(int a, int b, int c) throws IOException{
    	if (writer != null){
    		writer.println(a+ " " + b + " " + c);
    		fs.flush();
    	}
    }
}
