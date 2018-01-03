package com.wechat.service;



import java.text.MessageFormat;

import javax.xml.bind.JAXBException;

import com.wechat.entity.event.BaseEvt;
import com.wechat.entity.event.EventMsg;
import com.wechat.entity.event.LocationSelectEvt;
import com.wechat.entity.event.PicAlbumEvt;
import com.wechat.entity.event.ScanCodePushEvt;
import com.wechat.entity.event.SubscribeMsg;
import com.wechat.entity.msg.sendmsg.ArticleItem;
import com.wechat.entity.msg.sendmsg.ArticleList;
import com.wechat.entity.msg.sendmsg.SendArticleMsg;
import com.wechat.entity.msg.sendmsg.SendTextMsg;
import com.wechat.util.DateUtil;
import com.wechat.util.XmlUtil;

/**
 * 处理包括点击菜单和进行某些操作之后，由微信服务器发过来的事件消息
 *
 */
public class EventService {
	public static String checkEventType(String strMsg){
		String strCheck="<Event><![CDATA[{0}]]></Event>";
		if(strMsg.contains(MessageFormat.format(strCheck, "CLICK"))){
			return "CLICK";
		}else if(strMsg.contains(MessageFormat.format(strCheck, "VIEW"))){
			return "VIEW";
		}else if(strMsg.contains(MessageFormat.format(strCheck, "scancode_push"))){
			return "scancode_push";
		}else if(strMsg.contains(MessageFormat.format(strCheck, "scancode_waitmsg"))){
			return "scancode_waitmsg";
		}else if(strMsg.contains(MessageFormat.format(strCheck, "pic_sysphoto"))){
			return "pic_sysphoto";
		}else if(strMsg.contains(MessageFormat.format(strCheck, "pic_photo_or_album"))){
			return "pic_photo_or_album";
		}else if(strMsg.contains(MessageFormat.format(strCheck, "pic_weixin"))){
			return "pic_weixin";
		}else if(strMsg.contains(MessageFormat.format(strCheck, "location_select"))){
			return "location_select";
		}else if(strMsg.contains(MessageFormat.format(strCheck, "subscribe"))){
			return "subscribe";
		}else{
			return "";
		}
	}
	/**
	 * 根据不同的事件类型去分发做不同的业务
	 * @param strRespContent
	 * @return
	 * @throws JAXBException
	 */
	public static String distributeEvent(String strRespContent) throws JAXBException{
		System.out.println("response content :"+strRespContent+"\n");
		String eventType=checkEventType(strRespContent);
		System.out.println("event type :"+eventType+"\n");
		String rtnXml="";
		if(eventType.equals("CLICK")){
			BaseEvt clickMsg=(BaseEvt)XmlUtil.xmlToObject(strRespContent, BaseEvt.class);
			rtnXml=clickEvent(clickMsg);
		}else if(eventType.equals("VIEW")){
			BaseEvt viewMsg=(BaseEvt)XmlUtil.xmlToObject(strRespContent, BaseEvt.class);
			//rtnXml=viewEvent(viewMsg);
		}else if(eventType.equals("scancode_waitmsg")){
			
			ScanCodePushEvt scanMsg=(ScanCodePushEvt)XmlUtil.xmlToObject(strRespContent, ScanCodePushEvt.class);
			rtnXml=scancodeEvent(scanMsg);
			
			
		}else if(eventType.equals("pic_photo_or_album")){
			PicAlbumEvt picMsg=(PicAlbumEvt)XmlUtil.xmlToObject(strRespContent, PicAlbumEvt.class);
			rtnXml=picAlbumEvent(picMsg);
		}else if(eventType.equals("location_select")){
			LocationSelectEvt locMsg=(LocationSelectEvt)XmlUtil.xmlToObject(strRespContent, LocationSelectEvt.class);
			rtnXml=locationEvent(locMsg);
		}else if(eventType.equals("subscribe")){
			SubscribeMsg subscribeMsg=(SubscribeMsg)XmlUtil.xmlToObject(strRespContent, SubscribeMsg.class);
			rtnXml=subscribeEvent(subscribeMsg);
		}
		return rtnXml;
	}
	
	/**
	 * 处理点击事件
	 * @param clickMsg
	 * @return
	 * @throws JAXBException
	 */
	public static String clickEvent(EventMsg clickMsg) throws JAXBException{
		SendTextMsg replyMsg=new SendTextMsg();
		replyMsg.setToUserName(clickMsg.getFromUserName());
		replyMsg.setFromUserName(clickMsg.getToUserName());
		replyMsg.setContent("别瞎点");
		replyMsg.setCreateTime(String.valueOf(DateUtil.getCurrentMilliSecond()));
		replyMsg.setMsgType("text");
		String rtn="";
		rtn=XmlUtil.objectToXml(replyMsg);
		return rtn;
	}
	
	/**
	 * 处理点击事件
	 * @param clickMsg
	 * @return
	 * @throws JAXBException
	 */
	public static String subscribeEvent(SubscribeMsg clickMsg) throws JAXBException{
		SendArticleMsg sendarticleMsg=new SendArticleMsg();
		ArticleItem item=new ArticleItem();
		item.setTitle("5道关卡，考验不凡眼力");
		item.setDescription("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		item.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/PVHdHLVguQTNpkjc1UHiaicxEUNpjLK6dZHqasHIGmaU95NBRpB2wevQnulDCGChj48vjibeuoadUCO5Rvtd5xjSQ/0?wx_fmt=jpeg");
		item.setUrl("http://mp.weixin.qq.com/s?__biz=MzIxMDE0Mjc2OQ==&mid=405350138&idx=1&sn=81ac3199ce80c7b3eace0db35879f963&scene=0#wechat_redirect");
		ArticleList list=new ArticleList();
		list.addArticle(item);
		sendarticleMsg.setArticleCount(String.valueOf(1));
		sendarticleMsg.setArticles(list);
		sendarticleMsg.setToUserName(clickMsg.getFromUserName());
		sendarticleMsg.setFromUserName(clickMsg.getToUserName());
		sendarticleMsg.setMsgType("news");
		sendarticleMsg.setCreateTime(String.valueOf(DateUtil.getCurrentMilliSecond()));
		String rtn="";
		rtn=XmlUtil.objectToXml(sendarticleMsg);
		return rtn;
	}
	
	/**
	 * 处理链接事件
	 * @param viewMsg
	 * @return
	 * @throws JAXBException
	 */
	public static String viewEvent(EventMsg viewMsg) throws JAXBException{
		SendTextMsg replyMsg=new SendTextMsg();
		replyMsg.setToUserName(viewMsg.getFromUserName());
		replyMsg.setFromUserName(viewMsg.getToUserName());
		replyMsg.setContent("您访问了一次网站");
		replyMsg.setCreateTime(String.valueOf(DateUtil.getCurrentMilliSecond()));
		replyMsg.setMsgType("text");
		String rtn="";
		rtn=XmlUtil.objectToXml(replyMsg);
		return rtn;
	}
	
	/**
	 * 处理扫描二维码事件
	 * @param scanMsg
	 * @return
	 * @throws JAXBException
	 */
	public static String scancodeEvent(ScanCodePushEvt scanMsg) throws JAXBException{
		SendTextMsg replyMsg=new SendTextMsg();
		replyMsg.setToUserName(scanMsg.getFromUserName());
		replyMsg.setFromUserName(scanMsg.getToUserName());
		replyMsg.setContent("扫描的结果为:"+scanMsg.getScanCodeInfo().getScanResult());
		replyMsg.setCreateTime(String.valueOf(DateUtil.getCurrentMilliSecond()));
		replyMsg.setMsgType("text");
		String rtn="";
		rtn=XmlUtil.objectToXml(replyMsg);
		return rtn;
	}
	
	/**
	 * 处理打开手机相册事件
	 * @param picMsg
	 * @return
	 * @throws JAXBException
	 */
	public static String picAlbumEvent(PicAlbumEvt picMsg) throws JAXBException{
		SendTextMsg replyMsg=new SendTextMsg();
		replyMsg.setToUserName(picMsg.getFromUserName());
		replyMsg.setFromUserName(picMsg.getToUserName());
		replyMsg.setContent("图片:"+picMsg.getSendPicsInfo().getCount());
		replyMsg.setCreateTime(String.valueOf(DateUtil.getCurrentMilliSecond()));
		replyMsg.setMsgType("text");
		String rtn="";
		rtn=XmlUtil.objectToXml(replyMsg);
		return rtn;
	}
	
	/**
	 * 处理发送定位事件
	 * @param locMsg
	 * @return
	 * @throws JAXBException
	 */
	public static String locationEvent(LocationSelectEvt locMsg) throws JAXBException{
		SendTextMsg replyMsg=new SendTextMsg();
		replyMsg.setToUserName(locMsg.getFromUserName());
		replyMsg.setFromUserName(locMsg.getToUserName());
		replyMsg.setContent("地理位置为:"+locMsg.getSendLocationInfo().getLabel());
		replyMsg.setCreateTime(String.valueOf(DateUtil.getCurrentMilliSecond()));
		replyMsg.setMsgType("text");
		String rtn="";
		rtn=XmlUtil.objectToXml(replyMsg);
		return rtn;
	}
}
