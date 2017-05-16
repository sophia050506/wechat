package com.wei.wechat.core.bean.message.req;

public class ReqBaseMessage {

	//开发者微信号
	private String ToUserName;
	
	//发送方帐号（一个OpenID）
	private String FromUserName;
	
	//消息创建时间 （整型）
	private long CreateTime;
	
	//消息类型
	private String MsgType;

	//消息ID
	private long MsgId;

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

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}
	
	
}
