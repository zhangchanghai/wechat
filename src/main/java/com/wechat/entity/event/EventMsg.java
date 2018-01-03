package com.wechat.entity.event;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;



public abstract class EventMsg {
	@XmlElement
	private String ToUserName;
	@XmlElement
	private String FromUserName;
	@XmlElement
	private String CreateTime;
	@XmlElement
	private String MsgType;
	@XmlElement
	private String Event;
	@XmlElement
	private String EventKey;
	
	
	@XmlTransient
	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}
	
	@XmlTransient
	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	@XmlTransient
	public String getToUserName() {
		return ToUserName;
	}
	
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	
	@XmlTransient
	public String getFromUserName() {
		return FromUserName;
	}
	
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	
	@XmlTransient
	public String getCreateTime() {
		return CreateTime;
	}
	
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	
	@XmlTransient
	public String getMsgType() {
		return MsgType;
	}
	
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
	
	
}
