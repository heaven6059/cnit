package com.cnit.yoyo.complain.model;
/**   
 * @Package com.cnit.yoyo.complain.model 
 * @Description: 投诉内容表
 * @author  余平 yuping@cnit.com 
 * @date 2015-4-29 下午2:58:31 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.io.Serializable;
import java.util.Date;

public class ComplainComments implements Serializable {
	private static final long serialVersionUID = -4883863747647702773L;
	private Long commentsId;
	private Long complainId;//投诉编号
	private String source;//留言方  default 'buyer'  '''buyer'' => (''买家''),''seller'' => (''卖家''),''platform'' => (''环球名牌'')',
	private Integer authorId;//发表ID
	private String author;//发表人
	private String imagePath;//图片路径
	
	private Date lastModified;//更新时间
	private String disabled;//更新时间
	private String comment;//失效 default 'false'  '（true：是；false：否）'

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append("commentsId=").append(commentsId);
		sb.append(", complainId=").append(complainId);
		sb.append(", source=").append(source);
		sb.append(", authorId=").append(authorId);
		sb.append(", author=").append(author);
		sb.append(", image=").append(imagePath);
		sb.append(", lastModified=").append(lastModified);
		sb.append(", disabled=").append(disabled);
		sb.append(", comment=").append(comment);
		return sb.toString();
	}

	public Long getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(Long commentsId) {
		this.commentsId = commentsId;
	}

	public Long getComplainId() {
		return complainId;
	}

	public void setComplainId(Long complainId) {
		this.complainId = complainId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}