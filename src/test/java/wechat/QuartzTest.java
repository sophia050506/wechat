package wechat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuartzTest {

	public static void main(String[] args) {
		 SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
         Date d = new Date();  
         String returnstr = DateFormat.format(d);          
          
         TestJob job = new TestJob();  
         String job_name ="11";  

	}

}
