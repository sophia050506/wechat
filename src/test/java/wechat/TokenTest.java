package wechat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.wei.wechat.core.util.MyX509TrustManager;

public class TokenTest {

	public static void main(String[] args) throws Exception {
		//接口凭证地址
		String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx0b8bd06b060caedf&secret=4f151b2952bbe33b5229fc2bac061c62";
		
		//建立连接
		URL url = new URL(tokenUrl);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		
		//使用自定义的任务管理器
		TrustManager[] tm = {new MyX509TrustManager()};
		SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		conn.setSSLSocketFactory(ssf);
		conn.setDoInput(true);
		
		//设置请求方式
		conn.setRequestMethod("GET");
		
		//取得输入流
		InputStream inputStream = conn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		//读取响应内容
		StringBuffer buffer =  new StringBuffer();
		String str = null;
		while((str = bufferedReader.readLine()) != null){
			buffer.append(str);
		}
		
		//关闭释放资源
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		conn.disconnect();
		
		//输出结果
		System.out.println(buffer);
		
		
		
		
		
		
		
	}

}
