package com.cnit.yoyo.model.member.dto;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cnit.yoyo.annotation.CompositeSearch;
import com.cnit.yoyo.dto.BaseQryDTO;
import com.cnit.yoyo.util.StringUtil;

/**
 * @Title: MemberCommentQryDTO.java
 * @Package com.cnit.yoyo.model.member.dto
 * @Description: 会员咨询评论查询DTO
 * @Author 王鹏
 * @date 2015-5-5 上午9:52:09
 * @version V1.0
 */
public class MemberCommentQryDTO extends BaseQryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7791009690645143949L;
	private String objectType;
	private Integer storeId;
	private Long companyId;
	private Long orderId;
	private Integer commentId;
	private String commentsType;
	private Integer memberId;
	private Integer filterReply;
	private Long goodsId;
	private String keyWords;
	private String memberName;
	@CompositeSearch(fieldName = "store.store_name")
	private String storeName;
	@CompositeSearch(fieldName = "comments.title")
	private String title;
	@CompositeSearch(fieldName = "product.name")
	private String productName;
	private String disPlay;
	private String startTime;
	private String endTime;
	@CompositeSearch(fieldName = "comments.create_time")
	private String createTime;
	@CompositeSearch(fieldName = "comments.comment_star")
	private Integer commentStar;
	private String orderStmt;
	private List<Object> commentIds;

	public List<Object> getCommentIds() {
		return commentIds;
	}

	public void setCommentIds(List<Object> commentIds) {
		this.commentIds = commentIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getFilterReply() {
		return filterReply;
	}

	public void setFilterReply(Integer filterReply) {
		this.filterReply = filterReply;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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

	public String getCommentsType() {
		return commentsType;
	}

	public void setCommentsType(String commentsType) {
		this.commentsType = commentsType;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDisPlay() {
		return disPlay;
	}

	public void setDisPlay(String disPlay) {
		this.disPlay = disPlay;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getCommentStar() {
		return commentStar;
	}

	public void setCommentStar(Integer commentStar) {
		this.commentStar = commentStar;
	}

	public String getOrderStmt() {
		return orderStmt;
	}

	public void setOrderStmt(String orderStmt) {
		this.orderStmt = orderStmt;
	}

	/**
	 * @description <b>构建排序字段</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-8-31
	 * @return String
	 */
	public void buildOrderByField() {
		if (StringUtils.isNotBlank(super.getSort())&& StringUtils.isNotBlank(super.getSort())) {
			Class cls = this.getClass();
			Field[] fd = cls.getDeclaredFields();
			StringBuffer stringBuffer = new StringBuffer();
			for (Field field : fd) {
				if (!field.isAnnotationPresent(CompositeSearch.class))
					continue;
				if (!field.getName().equals(super.getSort()))
					continue;
				stringBuffer.append(field.getAnnotation(CompositeSearch.class).fieldName()+ " " + super.getOrder());
			}
			if (StringUtil.isNotEmpty(stringBuffer)) {
				this.setOrderStmt(stringBuffer.toString());
			}
		}
	}
}
