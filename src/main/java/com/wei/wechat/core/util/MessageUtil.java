package com.wei.wechat.core.util;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wei.wechat.core.bean.message.resp.ImageMessage;
import com.wei.wechat.core.bean.message.resp.MusicMessage;
import com.wei.wechat.core.bean.message.resp.NewsMessage;
import com.wei.wechat.core.bean.message.resp.TextMessage;
import com.wei.wechat.core.bean.message.resp.VideoMessage;
import com.wei.wechat.core.bean.message.resp.VoiceMessage;

public class MessageUtil {
	
	public static Logger logger = Logger.getLogger(MessageUtil.class);
	
	/*请求消息类型*/
	//文本
	public static final String REQ_MASSAGE_TYPE_TEXT = "text";
	
	//图片消息
	public static final String REQ_MASSAGE_TYPE_IMAGE = "image";
	
	//语音消息
	public static final String REQ_MASSAGE_TYPE_VOICE = "voice";
		
	//视频消息
	public static final String REQ_MASSAGE_TYPE_VIDEO = "video";
	
	//小视频消息
	public static final String REQ_MASSAGE_TYPE_SHORTVIDEO = "shortvideo";
	
	//地理位置消息
	public static final String REQ_MASSAGE_TYPE_LOCATION = "location";
	
	//链接消息
	public static final String REQ_MASSAGE_TYPE_LINK = "link";
	
	/*事件类型*/
	public static final String REQ_MASSAGE_TYPE_EVENT = "event";
	
	//订阅
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	
	//取消订阅
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	
	//扫描带参数的二维码
	public static final String EVENT_TYPE_SCAN = "SCAN";
	
	//上报地理位置
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	
	//点击
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
	/*响应消息类型*/
	//1 回复文本消息
	public static final String RESP_MASSAGE_TYPE_TEXT ="text";
	
	//2 回复图片消息
	public static final String RESP_MASSAGE_TYPE_IMAGE ="image";
	
	//3 回复语音消息
	public static final String RESP_MASSAGE_TYPE_VOICE ="voice";
	
	//4 回复视频消息
	public static final String RESP_MASSAGE_TYPE_VIDEO ="video";
	
	//	5 回复音乐消息
	public static final String RESP_MASSAGE_TYPE_MUSIC ="music";
	
	//	6 回复图文消息
	public static final String RESP_MASSAGE_TYPE_NEWS ="news";

	
	/**
	 * 解析微信发过来的请求(xml).
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> parseXml(HttpServletRequest request){
		//解析结果存储在HashMap中
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			//从request中获取输入流
			ServletInputStream inputStream = request.getInputStream();
			
			//读取输入流
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			
			//得到xml根元素
			Element root = document.getRootElement();
			
			//得到根元素的所有子节点
			List<Element> elementList = root.elements();
			
			//遍历所有子节点
			for(Element e: elementList){
				map.put(e.getName(), e.getText());
			}
			
			inputStream.close();
			inputStream= null;
			
		} catch (IOException e) {
			logger.info("request中获取输入流失败");
			e.printStackTrace();
		} catch (DocumentException e) {
			logger.info("读取输入流失败");
			e.printStackTrace();
		}		
		return map;
	}
	
	/**
	 * 扩展xstream，使其支持CDATA块 
	 */
	private static XStream xstream = new XStream(new XppDriver(){
		public HierarchicalStreamWriter createWriter(Writer out){
			return new PrettyPrintWriter(out){
				
				//对所有节点加CDATA标记
				boolean cdata = true;
				
				public void startNode(String name,Class clazz){
					super.startNode( name, clazz);
				}
				
				protected void WriteText(QuickWriter writer,String text){
					if(cdata){
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					}else{
						writer.write(text);
					}
				}
			};
		}
	});


	/**
	 * 文本消息转换为xml.
	 * @param message
	 * @return
	 */
	public static String messageToXml(TextMessage message){
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}
	
	/**
	 * 图片消息转换为xml.
	 * @param message
	 * @return
	 */
	public static String messageToXml(ImageMessage message){
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}
	
	/**
	 * 音乐消息转换为xml.
	 * @param message
	 * @return
	 */
	public static String messageToXml(MusicMessage message){
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}
	
	/**
	 * 语音消息转换为xml.
	 * @param message
	 * @return
	 */
	public static String messageToXml(VideoMessage message){
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}
	
	/**
	 * 视频消息转换为xml.
	 * @param message
	 * @return
	 */
	public static String messageToXml(VoiceMessage message){
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}
	
	/**
	 * 图文消息转换为xml.
	 * @param message
	 * @return
	 */
	public static String messageToXml(NewsMessage message) {  
        xstream.alias("xml", message.getClass());  
        xstream.alias("item", NewsMessage.Article.class);  
        return xstream.toXML(message);  
    } 
	
}
