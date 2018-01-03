package com.wechat.entity.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;


public class SendLocationInfo {
	@XmlElement
	private String Location_X;
	@XmlElement
	private String Location_Y;
	@XmlElement
	private String Scale;
	@XmlElement
	private String Label;
	@XmlElement
	private String Poiname;
	
	@XmlTransient
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	@XmlTransient
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	@XmlTransient
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	@XmlTransient
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	@XmlTransient
	public String getPoiname() {
		return Poiname;
	}
	public void setPoiname(String poiname) {
		Poiname = poiname;
	}
	
	
}
