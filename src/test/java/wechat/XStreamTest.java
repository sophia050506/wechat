package wechat;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.wei.wechat.core.bean.message.req.TextMessage;

public class XStreamTest {

	public static void main(String[] args) {
		TextMessage text = new TextMessage();
		text.setContent("你好！");
		text.setCreateTime(20170216);
		text.setFromUserName("wechat");
		text.setMsgId(1);
		text.setMsgType("text");
		text.setToUserName("sophia");
		System.out.println(javaObject2Xml(text));
		
		
		String xml = "<xml><ToUserName>toUser</ToUserName><FromUserName>fromUser</FromUserName><CreateTime>1348831860</CreateTime><MsgType>text</MsgType><Content>this is a test</Content><MsgId>1234567890123456</MsgId></xml>";
		TextMessage t2 = (TextMessage) xml2JavaObject(xml);
		System.out.println(t2.getFromUserName()+"----->"+t2.getToUserName());

	}
	
	/**
	 * xml转成java对象.
	 * @param xml
	 * @return
	 */
	public static Object xml2JavaObject(String xml){
		XStream xs = new XStream(new DomDriver());
		xs.alias("xml", TextMessage.class);
		TextMessage text = (TextMessage) xs.fromXML(xml);
		return text;
	}
	
	/**
	 * java对象 转换成xml.
	 * @param text
	 * @return
	 */
	public static String javaObject2Xml(TextMessage text){
		XStream xs = new XStream(new DomDriver());
		xs.alias("xml", TextMessage.class);
		return xs.toXML(text);
	}

}
