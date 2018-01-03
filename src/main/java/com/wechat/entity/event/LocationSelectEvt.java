package com.wechat.entity.event;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="xml")
public class LocationSelectEvt extends EventMsg {
	@XmlElement
	private SendLocationInfo SendLocationInfo;
	
	@XmlTransient
	public SendLocationInfo getSendLocationInfo() {
		return SendLocationInfo;
	}

	public void setSendLocationInfo(SendLocationInfo sendLocationInfo) {
		SendLocationInfo = sendLocationInfo;
	}
	
	
}
