package com.wechat.entity.msg.sendmsg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Articles")
public class ArticleList {
	@XmlElement(name="item")
	private List<ArticleItem> Articles;
	
	public ArticleList(){
		Articles=new ArrayList<ArticleItem>();
	}
	
	public void addArticle(ArticleItem item){
		Articles.add(item);
	}
	
	public void removeArticle(ArticleItem item){
		Articles.remove(item);
	}
	
	public int articleCount(){
		return Articles.size();
	}
}
