package com.wechat.entity.msg.sendmsg;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="xml")
public class SendTextMsg extends SendMsg{
	@XmlElement
	private String Content;
	
	@XmlTransient
	public String getContent() {
		return Content;
	}
	
	public void setContent(String content) {
		Content = content;
	}
	
	
	
	
	
}
