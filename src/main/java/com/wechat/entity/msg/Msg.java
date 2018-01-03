package com.wechat.entity.msg;

import javax.xml.bind.annotation.XmlElement;



public class Msg {
	@XmlElement
	private String ToUserName;
	@XmlElement
	private String FromUserName;
	@XmlElement
	private String CreateTime;
	@XmlElement
	private String MsgType;
	@XmlElement
	private String MsgId;
	
	public enum messageType{
		text,image,voice,video,shortvideo,location,link;
	}
	
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
	public String getCreateTime() {
		return CreateTime;
	}
	
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getMsgId() {
		return MsgId;
	}
	
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	
	
}
