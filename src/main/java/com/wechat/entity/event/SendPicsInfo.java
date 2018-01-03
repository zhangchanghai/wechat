package com.wechat.entity.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;


public class SendPicsInfo {
	@XmlElement
	private String Count;
	@XmlElement
	private PicList PicList;
	
	@XmlTransient
	public String getCount() {
		return Count;
	}
	public void setCount(String count) {
		Count = count;
	}
	
	@XmlTransient
	public PicList getPicList() {
		return PicList;
	}
	public void setPicList(PicList picList) {
		PicList = picList;
	}
	
	
	
}
