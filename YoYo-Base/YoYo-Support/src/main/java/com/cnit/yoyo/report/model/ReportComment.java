package com.cnit.yoyo.report.model;

import java.io.Serializable;
import java.util.Date;

public class ReportComment implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2881620607157244322L;
	private Long commentId;//编号
    private Long reportId;//举报编号
    private String source;//留言方
    private Integer authorId;//default 'buyer' comment '''buyer'' => (''买家''),''seller'' => (''卖家''),''platform'' => (''环球名牌'')',
    private String author;//发表ID
    private String imagePath;//发表人
    private Date lastModified;//更新时间
    private String disabled;//default 'false' comment '（true：是；false：否）',
    private String comment;//内容
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" commentId=").append(commentId);
        sb.append(", reportId=").append(reportId);
        sb.append(", source=").append(source);
        sb.append(", authorId=").append(authorId);
        sb.append(", author=").append(author);
        sb.append(", imagePath=").append(imagePath);
        sb.append(", lastModified=").append(lastModified);
        sb.append(", disabled=").append(disabled);
        sb.append(", comment=").append(comment);
        return sb.toString();
    }
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public Long getReportId() {
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
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