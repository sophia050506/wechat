package wechat;

import com.wei.wechat.core.util.SignUtil;

public class SignUtilTest {

	//CoreServlet?signature=&echostr=2909560007788854895&timestamp=&nonce= 200 0.010 0.010 0 
	
	public static void main(String[] args) {
		String signature = "8628ced5d2c00269ea16f1475442acbe28dbd609";
		String timestamp = "1487906415";
		String nonce = "1525092677";
		boolean a = SignUtil.checkSignature(signature, timestamp, nonce);
		System.out.println(a);
	}

}
