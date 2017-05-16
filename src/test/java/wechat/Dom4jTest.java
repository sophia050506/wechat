package wechat;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Dom4jTest {
	
	public static void main(String[] args) throws Exception {
		StringBuffer bf = new StringBuffer();
		bf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bf.append("<xml>");
		bf.append("<ToUserName>sophia</ToUserName>");
		bf.append("<FromUserName>anna</FromUserName>");
		bf.append("<CreateTime>12345678</CreateTime>");
		bf.append("<MsgType>text</MsgType>");
		bf.append("<Content>你好!</Content>");
		bf.append("</xml>");

		//通过解析XML创建Document对象
		Document doc = DocumentHelper.parseText(bf.toString());
		//得到XML的根元素
		Element root = doc.getRootElement();
		//得到根元素上的所有子节点
		List<Element> elements = root.elements();
		//遍历所有子节点
		for(Element e:elements){
			//输出所有子节点的名称和值
			System.out.println(e.getName()+"--->"+e.getText());
		}
		
	}
}
