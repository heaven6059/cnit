package com.cnit.yoyo.report.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ReportDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2188244304160947659L;
	private Long reportId;// 举报编号
	private Integer catId;// 举报类型id
	private Integer fromMemberId;// 举报方
	private String fromUname;// 举报人
	private Integer toMemberId;// 被举报方
	private String toUname;// 店主名
	private String source;// 举报方来源 default 'buyer' buyer => (''买家''),''seller''
							// => (''卖家'')'
	private String mobile;// 手机
	private Long storeId;// 店铺Id
	private Long companyId;
	private String storeName;// 店铺名
	private String status;// 举报状态 default 'intervene' comment '''intervene'' =>
							// (''平台介入''),''voucher'' => (''取证中''),
	// ''success'' => (''举报成立''),''error'' => (''举报不成立''),''cancel'' =>
	// (''举报撤销''),''finish'' => (''完成''),',
	private Date createtime;// 申请时间
	private Date lastModified;// 更新时间
	private String disabled;// 失效 default 'false' comment '（true：是；false：否）'
	private String reason;// 举报原因 false 虚假信息 unconformity 图片不符

	private Long commentId;// 编号
	// private Long reportId;//举报编号
	private String commentSource;// 留言方
	private Integer authorId;// default 'buyer' comment '''buyer'' =>
								// (''买家''),''seller'' => (''卖家''),''platform''
								// => (''环球名牌'')',
	private String author;// 发表ID
	private String imagePath;// 发表人
	private Date commentLastModified;// 更新时间
	// private String disabled;//default 'false' comment '（true：是；false：否）',
	private String comment;// 内容
	private String memo;

	private List<ReportComment> reportComments;

	// product
	private Integer productId;
	private Integer goodsId;
	private String name;
	private String picturePath;
	private BigDecimal price;

	public Date getCommentLastModified() {
		return commentLastModified;
	}

	public void setCommentLastModified(Date commentLastModified) {
		this.commentLastModified = commentLastModified;
	}

	public String getCommentSource() {
		return commentSource;
	}

	public void setCommentSource(String commentSource) {
		this.commentSource = commentSource;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<ReportComment> getReportComments() {
		return reportComments;
	}

	public void setReportComments(List<ReportComment> reportComments) {
		this.reportComments = reportComments;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public Integer getFromMemberId() {
		return fromMemberId;
	}

	public void setFromMemberId(Integer fromMemberId) {
		this.fromMemberId = fromMemberId;
	}

	public String getFromUname() {
		return fromUname;
	}

	public void setFromUname(String fromUname) {
		this.fromUname = fromUname;
	}

	public Integer getToMemberId() {
		return toMemberId;
	}

	public void setToMemberId(Integer toMemberId) {
		this.toMemberId = toMemberId;
	}

	public String getToUname() {
		return toUname;
	}

	public void setToUname(String toUname) {
		this.toUname = toUname;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	
}
