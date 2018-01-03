package com.wechat.entity.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;



public class ScanCodeInfo {
	@XmlElement
	private String ScanType;
	@XmlElement
	private String ScanResult;
	
	@XmlTransient
	public String getScanType() {
		return ScanType;
	}
	public void setScanType(String scanType) {
		ScanType = scanType;
	}
	
	@XmlTransient
	public String getScanResult() {
		return ScanResult;
	}
	public void setScanResult(String scanResult) {
		ScanResult = scanResult;
	}
	
	
}
