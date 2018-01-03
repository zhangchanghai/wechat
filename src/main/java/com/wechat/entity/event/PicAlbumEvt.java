package com.wechat.entity.event;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="xml")
public class PicAlbumEvt extends EventMsg {
	@XmlElement
	private SendPicsInfo SendPicsInfo;
	
	@XmlTransient
	public SendPicsInfo getSendPicsInfo() {
		return SendPicsInfo;
	}

	public void setSendPicsInfo(SendPicsInfo sendPicsInfo) {
		SendPicsInfo = sendPicsInfo;
	}
	
	
}
