package com.cnit.yoyo.weixin.tencent.pojo;

import java.util.List;

public class NewsMsg extends BaseMsg {

	private Integer articleCount;
	private List<Article> articles;
	
	public Integer getArticleCount(){
		return articleCount;
	}
	
	public void setArticleCount(Integer articleCount){
		this.articleCount = articleCount;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
