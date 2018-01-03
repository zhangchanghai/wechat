package com.wechat.entity.event;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;


public class PicItem {
	@XmlElement
	private String PicMd5Sum;
	
	@XmlTransient
	public String getPicMd5Sum() {
		return PicMd5Sum;
	}

	public void setPicMd5Sum(String picMd5Sum) {
		PicMd5Sum = picMd5Sum;
	}
	
	
	
}
