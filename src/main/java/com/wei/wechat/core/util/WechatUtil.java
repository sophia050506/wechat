package com.wei.wechat.core.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;

import com.wei.wechat.core.bean.Token;
import com.wei.wechat.core.bean.button.Menu;

import net.sf.json.JSONObject;


public class WechatUtil {

	public static Logger logger = Logger.getLogger(WechatUtil.class);
	
	//接口凭证地址
	private final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	//创建菜单（post）
	private final static String menu_create_url=" https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	//菜单查询（get）
	private final static String menu_get_url="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	
	//菜单删除（get）
	private final static String menu_delete_url="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	/**
	 * 发起https请求并获取结果 .
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {  
        JSONObject jsonObject = null;  
        StringBuffer buffer = new StringBuffer();  
        try {  
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化  
            TrustManager[] tm = { new MyX509TrustManager() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
            sslContext.init(null, tm, new java.security.SecureRandom()); 
            
            // 从上述SSLContext对象中得到SSLSocketFactory对象  
            SSLSocketFactory ssf = sslContext.getSocketFactory();  
  
            URL url = new URL(requestUrl);  
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
            httpUrlConn.setSSLSocketFactory(ssf);  
  
            httpUrlConn.setDoOutput(true);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false); 
            
            // 设置请求方式（GET/POST）  
            httpUrlConn.setRequestMethod(requestMethod);  
  
            if ("GET".equalsIgnoreCase(requestMethod))  
                httpUrlConn.connect();  
  
            // 当有数据需要提交时  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // 注意编码格式，防止中文乱码  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }  
  
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
            jsonObject = JSONObject.fromObject(buffer.toString());  
        } catch (ConnectException ce) {  
        	logger.error("微信链接超时.");  
        } catch (Exception e) {  
        	logger.error("https request error:{}", e);  
        }  
        return jsonObject;  
    }  
	

	/**
	 * 接口访问凭证.
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public static Token getToken(String appid,String appsecret){
		Token token =  null;
		String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl,"GET",null);
		if(null != jsonObject){
			try {
				token = new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (Exception e) {
				token = null;
				logger.error("获取token失败 errcode:{"+jsonObject.getInt("errcode")+"} errmsg:{"+jsonObject.getString("errmsg")+"}");
				e.printStackTrace();
			}
		}
		return token;
	}
	
	/**
	 * 创建菜单.
	 * @param menu
	 * @param accessToken
	 * @return
	 */
	public static boolean createMenu(Menu menu,String accessToken){
		boolean result = false;
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		String jsonMenu = JSONObject.fromObject(menu).toString();
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
		if(null != jsonMenu){
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if(0== errorCode){
				result=true;
			}else{
				result= false;
				logger.error("创建菜单失败：errcode：{"+errorCode+"}，errmsg：{"+errorMsg+"}");
			}
		}
		return result;
	}
	
	/**
	 * 查询菜单.
	 * @param accessToken
	 * @return
	 */
	public static String getMenu(String accessToken){
		String result = null;
		String url = menu_get_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = httpRequest(url, "GET", null);
		if(null != jsonObject){
			result = jsonObject.toString();
		}
		return result;
	}
	
	public static boolean deleteMenu(String accessToken){
		boolean result = false;
		String url = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = httpRequest(url, "GET", null);
		if(null != jsonObject){
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if(0== errorCode){
				result=true;
			}else{
				result= false;
				logger.error("删除菜单失败：errcode：{"+errorCode+"}，errmsg：{"+errorMsg+"}");
			}
		}
		return result;
	}
	
}
