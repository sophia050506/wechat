package com.wei.wechat.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.wei.wechat.core.bean.message.resp.NewsMessage;
import com.wei.wechat.core.bean.message.resp.NewsMessage.Article;
import com.wei.wechat.core.bean.message.resp.TextMessage;
import com.wei.wechat.core.util.MessageUtil;

public class CoreService {
	public static Logger logger = Logger.getLogger(CoreService.class);

	public static String processRequest(HttpServletRequest request) {
		
		logger.info("--processRequest--");
		//XML格式的消息数据
		String respXml = null;
		
		//默认返回的文本消息内容
		String respContent = "未知的消息类型！";
		
		//调用parseXml解析请求消息
		Map<String, String> requestMap = MessageUtil.parseXml(request);
		
		String fromUserName = requestMap.get("FromUserName");
		String toUserName = requestMap.get("ToUserName");
		String msgType = requestMap.get("MsgType");
		
		logger.info("fromUserName="+fromUserName+";toUserName="+toUserName+";msgType="+msgType);
		
		TextMessage text  =  new TextMessage();
		text.setToUserName(fromUserName);
		text.setFromUserName(toUserName);
		text.setCreateTime(new Date().getTime());
		text.setMsgType(MessageUtil.RESP_MASSAGE_TYPE_TEXT);
		
		if(msgType.equals(MessageUtil.REQ_MASSAGE_TYPE_TEXT)){
			logger.info("文本");
			respContent = "文本消息";
			text.setContent(respContent);
			respXml= MessageUtil.messageToXml(text);
			logger.info("--"+respXml+"--");
		}else if(msgType.equals(MessageUtil.REQ_MASSAGE_TYPE_IMAGE)){
			respContent = "图片消息";
			text.setContent(respContent);
			respXml= MessageUtil.messageToXml(text);
			logger.info("--"+respXml+"--");
		}else if(msgType.equals(MessageUtil.REQ_MASSAGE_TYPE_VOICE)){
			respContent = "语音消息";
			text.setContent(respContent);
			respXml= MessageUtil.messageToXml(text);
			logger.info("--"+respXml+"--");
		}else if(msgType.equals(MessageUtil.REQ_MASSAGE_TYPE_VIDEO)){
			respContent = "视频消息";
			text.setContent(respContent);
			respXml= MessageUtil.messageToXml(text);
			logger.info("--"+respXml+"--");
		}else if(msgType.equals(MessageUtil.REQ_MASSAGE_TYPE_SHORTVIDEO)){
			respContent = "小视频消息";
			text.setContent(respContent);
			respXml= MessageUtil.messageToXml(text);
			logger.info("--"+respXml+"--");
		}else if(msgType.equals(MessageUtil.REQ_MASSAGE_TYPE_LOCATION)){
			respContent = "地理位置消息";
			text.setContent(respContent);
			respXml= MessageUtil.messageToXml(text);
			logger.info("--"+respXml+"--");
		}else if(msgType.equals(MessageUtil.REQ_MASSAGE_TYPE_LINK)){
			respContent = "链接消息";
			text.setContent(respContent);
			respXml= MessageUtil.messageToXml(text);
			logger.info("--"+respXml+"--");
		}else if(msgType.equals(MessageUtil.REQ_MASSAGE_TYPE_EVENT)){
			//事件类型
			String eventType = requestMap.get("event");
			if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
				logger.info("关注");
				respContent = "感谢您的关注";
				text.setContent(respContent);
				respXml= MessageUtil.messageToXml(text);
				logger.info("--"+respXml+"--");
			}else if(eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){
				//TODO
			}else if(eventType.equals(MessageUtil.EVENT_TYPE_SCAN)){
				//TODO
			}else if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)){
				//TODO
			}else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
				String eventKey = requestMap.get("EventKey");
				if(eventKey.equals("oschina")){
					Article article =new NewsMessage.Article();
					article.setTitle("开源中国");
					article.setDescription("分享");
					article.setPicUrl("");
					article.setUrl("http://m.oschaina.com");
					List<Article> list = new ArrayList<Article>();
					list.add(article);
					
					NewsMessage news = new NewsMessage();
					news.setToUserName(fromUserName);
					news.setFromUserName(toUserName);
					news.setCreateTime(new Date().getTime());
					news.setArticleCount(list.size());
					news.setArticles(list);
					
					respXml=MessageUtil.messageToXml(news);
					
				}else if(eventKey.equals("iteye")){
					text.setContent("讨论");
					respXml=MessageUtil.messageToXml(text);
				}
			}
			
		}
		return respXml;
	}

}
