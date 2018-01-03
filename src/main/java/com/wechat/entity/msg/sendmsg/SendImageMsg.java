package com.wechat.entity.msg.sendmsg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class SendImageMsg extends SendMsg {
	/*@XmlElementWrapper
	@XmlElement(name="Image")*/
	private Image Image;

	
	public Image getImage() {
		return Image;
	}

	public void setImage(Image Image) {
		this.Image = Image;
	}
	
}
