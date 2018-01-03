package com.wechat.entity.event;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;


public class PicList {
	@XmlElement
	private List<PicItem> PicList;
	@XmlTransient
	public List<PicItem> getPicList() {
		return PicList;
	}

	public void setPicList(PicItem pi) {
		PicList.add(pi);
	}

	public PicList(){
		PicList=new ArrayList<PicItem>();
	}
}
