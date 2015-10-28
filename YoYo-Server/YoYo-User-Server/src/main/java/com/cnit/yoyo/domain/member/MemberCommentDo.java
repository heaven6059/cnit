package com.cnit.yoyo.domain.member;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: MemberCommentListDo
 * @Description: 会员评论，咨询，短消息
 * @author xiaox
 * @date 2015-3-5 上午10:00:23
 */
public class MemberCommentDo implements Serializable {

	private static final long serialVersionUID = -9147861003019955784L;
	private String goodName;// 商品名称
	private String picturePath;// 商品图片
	private Integer commentId;
	private Integer memberId;// 会员ID
	private Integer authorId;// 作者ID
	private int forCommentId; // 对哪条评论的回复的评论id
	private int productId;// 商品Id
	private String IP;
	private String replyName; // 最后回复人
	private String lastreplyTime; // 最后回复时间
	private Date time;// 发表时间
	private String display; // 前台是否显示
	private String author; // 发表人
	private String contact;// 联系方式
	private String comment;// 内容
	private String title;// 标题
	private String storeName; // 店铺名称
	private int storeId;// 店铺Id
	private Long companyId;
	private String objectType;// 类型

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getReplyName() {
		return replyName;
	}

	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}

	public String getLastreplyTime() {
		return lastreplyTime;
	}

	public void setLastreplyTime(String lastreplyTime) {
		this.lastreplyTime = lastreplyTime;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public int getForCommentId() {
		return forCommentId;
	}

	public void setForCommentId(int forCommentId) {
		this.forCommentId = forCommentId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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

}
