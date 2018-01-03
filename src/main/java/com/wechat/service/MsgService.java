package com.wechat.service;



import java.net.MalformedURLException;
import java.text.MessageFormat;

import javax.xml.bind.JAXBException;

import com.wechat.entity.msg.PicMsg;
import com.wechat.entity.msg.TextMsg;
import com.wechat.entity.msg.sendmsg.ArticleItem;
import com.wechat.entity.msg.sendmsg.ArticleList;
import com.wechat.entity.msg.sendmsg.Image;
import com.wechat.entity.msg.sendmsg.SendArticleMsg;
import com.wechat.entity.msg.sendmsg.SendImageMsg;
import com.wechat.entity.msg.sendmsg.SendMsg;
import com.wechat.entity.msg.sendmsg.SendTextMsg;
import com.wechat.util.DateUtil;
import com.wechat.util.XmlUtil;

/**
 *消息处理服务
 */
public class MsgService {
	
	//判断发过来的消息类型
	public static String checkMsgType(String strMsg){
		String strCheck="<MsgType><![CDATA[{0}]]></MsgType>";
		if(strMsg.contains(MessageFormat.format(strCheck, "text"))){
			return "text";
		}else if(strMsg.contains(MessageFormat.format(strCheck, "image"))){
			return "image";
		}else if(strMsg.contains(MessageFormat.format(strCheck, "voice"))){
			return "voice";
		}else if(strMsg.contains(MessageFormat.format(strCheck, "video"))){
			return "video";
		}else if(strMsg.contains(MessageFormat.format(strCheck, "shortvideo"))){
			return "shortvideo";
		}else if(strMsg.contains(MessageFormat.format(strCheck, "location"))){
			return "location";
		}else if(strMsg.contains(MessageFormat.format(strCheck, "link"))){
			return "link";
		}else if(strMsg.contains(MessageFormat.format(strCheck, "event"))){
			return "event";
		}else if(strMsg.contains(MessageFormat.format(strCheck, "subscribe"))){
			return "subscribe";
		}
		else{
			return "";
		}
	}
	
	/**
	 * 处理图片消息
	 * @param picMsg
	 * @return
	 * @throws JAXBException
	 */
	public static String processImageMsg(PicMsg picMsg) throws JAXBException{
		String rtnXml="";
		SendImageMsg sendImageMsg=new SendImageMsg();
		Image image=new Image();
		image.setMediaId(picMsg.getMediaId());
		sendImageMsg.setToUserName(picMsg.getFromUserName());
		sendImageMsg.setFromUserName(picMsg.getToUserName());
		sendImageMsg.setMsgType(picMsg.getMsgType());
		sendImageMsg.setImage(image);
		sendImageMsg.setCreateTime(String.valueOf(DateUtil.getCurrentMilliSecond()));
		rtnXml=XmlUtil.objectToXml(sendImageMsg);
		return rtnXml;
	}
	
	public static String processTextMsg(TextMsg textMsg) throws JAXBException{
		String rtnXml="";
		SendTextMsg sendTextMsg=new SendTextMsg();
		sendTextMsg.setToUserName(textMsg.getFromUserName());
		sendTextMsg.setFromUserName(textMsg.getToUserName());
		sendTextMsg.setContent("您的留言已收到，谢谢!");
		sendTextMsg.setMsgType(textMsg.getMsgType());
		sendTextMsg.setCreateTime(String.valueOf(DateUtil.getCurrentMilliSecond()));
		rtnXml=XmlUtil.objectToXml(sendTextMsg);
		
		//rtnXml=rtnXml.replace("image>", "Image>");
		//rtnXml=rtnXml.replace("</image", "</Image");
		return rtnXml;
	}
	
	//返回图文消息
	public static String processTextMsgWithArticle(TextMsg textMsg) throws JAXBException{
		String rtnXml="";
		SendArticleMsg articleMsg=new SendArticleMsg();
		ArticleList articles=new ArticleList();
		ArticleItem a1=new ArticleItem();
		a1.setTitle("a1");
		a1.setDescription("a1 test");
		a1.setPicUrl("http://www.quanjing.com/imginfo/374403700.html");
		a1.setUrl("www.baidu.com");
		
		ArticleItem a2=new ArticleItem();
		a2.setTitle("a2");
		a2.setDescription("a2 test");
		a2.setPicUrl("http://rapher.tuchong.com/256832/256832/");
		a2.setUrl("www.baidu.com");
		
		articles.addArticle(a1);
		articles.addArticle(a2);
		
		articleMsg.setToUserName(textMsg.getFromUserName());
		articleMsg.setFromUserName(textMsg.getToUserName());
		articleMsg.setArticleCount(String.valueOf(articles.articleCount()));
		articleMsg.setMsgType("news");
		articleMsg.setCreateTime(String.valueOf(DateUtil.getCurrentMilliSecond()));
		articleMsg.setArticles(articles);
		rtnXml=XmlUtil.objectToXml(articleMsg);
		return rtnXml;
		
	}
	
}
