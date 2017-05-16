package com.wei.wechat.core.bean.message.resp;

/**
 * 响应消息基类（公众号-->用户）.
 * @author sophia
 *
 */
public class RespBaseMassage {

	//接收方帐号（收到的OpenID）
	private String ToUserName;
	
	//开发者微信号
	private String FromUserName;
	
	//消息创建时间 （整型）
	private long CreateTime;
	
	//消息类型
	private String MsgType;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	
	
}
