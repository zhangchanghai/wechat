package com.wechat.entity.msg.sendmsg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class SendArticleMsg extends SendMsg {
	@XmlElement
	private String ArticleCount;
	@XmlElement(name="Articles")
	private ArticleList articles;
	
	@XmlTransient
	public String getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}
	
	@XmlTransient
	public ArticleList getArticles() {
		return articles;
	}
	public void setArticles(ArticleList articles) {
		this.articles = articles;
	}
	
	
	
	
}
