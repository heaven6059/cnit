package com.cnit.yoyo.model.member.dto;

import java.util.Date;
import java.util.List;

import com.cnit.yoyo.model.goods.Picture;
import com.cnit.yoyo.model.goods.dto.PictureDTO;
import com.cnit.yoyo.model.member.MemberCommentWithBLOBs;
import com.cnit.yoyo.util.domain.ResultPage;

public class MemberCommentDetailDTO extends MemberCommentWithBLOBs{

	private static final long serialVersionUID = 8851591180222764235L;

	private Integer goodsPoint;//商品评分
	
	private String memberLvName;//用户等级
	
	private String loginName;//用户登录用户名
	
	private String commentDate;//评论时间
	
	private Date orderCreateTime;//订单生成时间
	
	private String orderCreateDate;//订单生成时间
	
//	private ResultPage<Picture> picPageList;//晒图
//	private ResultPage<PictureDTO> picPageList;//晒图
	private List<PictureDTO> picPageList;//晒图
	
	private Integer replyCommentId;//回复对象id
	
	private String replyAuthor;//回复者用户名
	
	private String replyComment;//回复内容
	
	private Date replyTime;//回复时间
	
	private String replyDate;//回复时间
	
	private Double positiveRate;//好评度
	
	private Double neutralRate;//中评度
	
	private Double negativeRate;//差评度
	
	private Integer replyCount;//回复数量
	
	private List<TagsDTO> tagsList;//标签列表
	
	public Integer getGoodsPoint() {
		return goodsPoint;
	}

	public void setGoodsPoint(Integer goodsPoint) {
		this.goodsPoint = goodsPoint;
	}

	public String getMemberLvName() {
		return memberLvName;
	}

	public void setMemberLvName(String memberLvName) {
		this.memberLvName = memberLvName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Date getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getOrderCreateDate() {
		return orderCreateDate;
	}

	public void setOrderCreateDate(String orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
	}

	public String getReplyAuthor() {
		return replyAuthor;
	}

	public void setReplyAuthor(String replyAuthor) {
		this.replyAuthor = replyAuthor;
	}

	public String getReplyComment() {
		return replyComment;
	}

	public void setReplyComment(String replyComment) {
		this.replyComment = replyComment;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public Integer getReplyCommentId() {
		return replyCommentId;
	}

	public void setReplyCommentId(Integer replyCommentId) {
		this.replyCommentId = replyCommentId;
	}

	public Double getPositiveRate() {
		return positiveRate;
	}

	public void setPositiveRate(Double positiveRate) {
		this.positiveRate = positiveRate;
	}

	public Double getNeutralRate() {
		return neutralRate;
	}

	public void setNeutralRate(Double neutralRate) {
		this.neutralRate = neutralRate;
	}

	public Double getNegativeRate() {
		return negativeRate;
	}

	public void setNegativeRate(Double negativeRate) {
		this.negativeRate = negativeRate;
	}

	public List<PictureDTO> getPicPageList() {
		return picPageList;
	}

	public void setPicPageList(List<PictureDTO> picPageList) {
		this.picPageList = picPageList;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public List<TagsDTO> getTagsList() {
		return tagsList;
	}

	public void setTagsList(List<TagsDTO> tagsList) {
		this.tagsList = tagsList;
	}



//	public ResultPage<PictureDTO> getPicPageList() {
//		return picPageList;
//	}
//
//	public void setPicPageList(ResultPage<PictureDTO> picPageList) {
//		this.picPageList = picPageList;
//	}

//	public ResultPage<Picture> getPicPageList() {
//		return picPageList;
//	}
//
//	public void setPicPageList(ResultPage<Picture> picPageList) {
//		this.picPageList = picPageList;
//	}

	

}
