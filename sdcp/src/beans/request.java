package beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class request {
  public String version;
  public String type;
  public String userName;
  public String permissionToken;
  public String password;
  public String deviceName;
  public String devicePassword;
  public String content;
  public String divider;
  public String server;
  public static String[] suportedType = {"A", "B", "C", "D", "E"};
  
  public request(BufferedReader ins){
	  try {
		this.init(ins);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public void init(BufferedReader ins) throws IOException{
	  Pattern p1 = Pattern.compile("^SDCP/(\\d.\\d) ([ABCDE]{1})$");
	  Pattern p2 = Pattern.compile("^user:([0-9a-zA-Z]{3,20}){0,1} {0,1}([0-9a-zA-Z]{6,12}){0,1}$");
	  Pattern p3 = Pattern.compile("^server: {0,1}(.*)$");
	  Pattern p4 = Pattern.compile("^device: {0,1}([0-9a-zA-Z]{6,20}){0,1}$");
	  Pattern p5 = Pattern.compile("^divider: {0,1}([a-zA-Z]{8,8}){0,1}$");
	  Matcher matcher;
	  String line = ins.readLine();
	  matcher = p1.matcher(line);  
	  if (matcher.matches()){
		  this.version = matcher.group(1);
		  this.type = matcher.group(2);
		  line = ins.readLine();
		  matcher = p2.matcher(line);
		  if (matcher.matches()){
			  this.userName = matcher.group(1);
			  this.password = matcher.group(2);
			  line = ins.readLine();
			  matcher = p3.matcher(line);
			  if (matcher.matches()){
				  this.server = matcher.group(1);
				  line = ins.readLine();
				  matcher = p4.matcher(line);
				  if (matcher.matches()){
					  this.deviceName = matcher.group(1);
					  line = ins.readLine();
					  matcher = p5.matcher(line);
					  if (matcher.matches()){
						  String divider = matcher.group(1);
						  if(divider == null){
							  return;
						  } else {
							  this.divider = divider;
							  line = ins.readLine();
							  Pattern p6 = Pattern.compile("^content: {0,1}");
							  matcher = p6.matcher(line);
							  if (matcher.matches() && ins.readLine().equals("/-----" + this.divider + "-----/")){
								  line = ins.readLine();
								  while(!line.equals("/-----" + this.divider + "-----/")){									  
									  if (this.content == null){
										  this.content = line;
									  } else {
										  this.content += line;
									  }
									  this.content += "\n";
									  line = ins.readLine();
								  }
							  }
						  }
					  }
				  }
			  }
		  }
	  }
  }
}
