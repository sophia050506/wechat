package com.wei.wechat.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 微信请求校验.
 * @author sophia
 *
 */
public class SignUtil {

	//与开发模式接口配置信息中的Token保持一致
	private static String token="wechatToken";
	 
	/**
	 * 校验签名.
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		//对token，timestamp，nonce进行字典排序
		String[] paramArr = new String[]{token,timestamp,nonce};
		Arrays.sort(paramArr);
		
		//排序后的数组拼接成一个字符串
		String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
		
		
		String ciphertext = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			//对拼接后的字符串进行sha-1加密
			byte[] digest = md.digest(content.toString().getBytes());
			ciphertext= byteToStr(digest);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		content = null;  
		return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 将字节数组转换成16进制字符串.
	 * @param digest
	 * @return
	 */
	private static String byteToStr(byte[] digest) {
		String strDigest = "";
		for(int i=0;i<digest.length;i++){
			strDigest+=byteToHexStr(digest[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换成16进制字符串.
	 * @param b
	 * @return
	 */
	private static String byteToHexStr(byte b) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(b >>> 4) & 0X0F];  
        tempArr[1] = Digit[b & 0X0F];  
  
        String s = new String(tempArr);  
        return s; 
		
	}
	
}
