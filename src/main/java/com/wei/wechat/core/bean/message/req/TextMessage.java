package com.wei.wechat.core.bean.message.req;

/**
 * 文本消息.
 * @author sophia
 *
 */
public class TextMessage extends ReqBaseMessage{

	//text消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
