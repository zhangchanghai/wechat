package com.wechat.entity.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name="xml")
public class ScanCodePushEvt extends EventMsg  {
	
	@XmlElement(name="ScanCodeInfo")
	private ScanCodeInfo ScanCodeInfo;
	
	@XmlTransient
	public ScanCodeInfo getScanCodeInfo() {
		return ScanCodeInfo;
	}

	public void setScanCodeInfo(ScanCodeInfo scanCodeInfo) {
		ScanCodeInfo = scanCodeInfo;
	}
	
}
