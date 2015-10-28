package com.cnit.yoyo.model.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cnit.yoyo.model.order.OrderComment;

public class OrderCommentDTO extends OrderComment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 525258050558516997L;
	private String productName;
	private BigDecimal price;
	private Date createtime;
	private String picturePath;
	private Integer replyCount;
	private String loginName;
	private String memberName;
	private String mobile;
	private String storeName;

	private List<OrderCommentDTO> replyComment;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public List<OrderCommentDTO> getReplyComment() {
		return replyComment;
	}

	public void setReplyComment(List<OrderCommentDTO> replyComment) {
		this.replyComment = replyComment;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}