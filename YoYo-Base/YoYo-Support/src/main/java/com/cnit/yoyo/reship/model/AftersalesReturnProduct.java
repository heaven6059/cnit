package com.cnit.yoyo.reship.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AftersalesReturnProduct implements Serializable {

	/***/
	private static final long serialVersionUID = -1696752537308306681L;
	private Long returnId;
	private Long orderId;
	private String returnBn;
	private String title;
	private Long appealReturnId;
	private Integer status;
	private String imageFile3;
	private String imageFile;
	private String imageFile2;
	private String interevenImage;
	private Date addTime;
	private BigDecimal sellerAmount;
	private BigDecimal amount;
	private BigDecimal shippingAmount;
	private Date closeTime;
	private Long storeId;
	private Integer refundType;
	private Integer isIntervene;
	private Integer interveneReason;
	private String intervenePhone;
	private String interveneMail;
	private BigDecimal shipCost;
	private BigDecimal amountSeller;
	private Integer isSafeguard;
	private Integer safeguardType;
	private Integer safeguardRequire;
	private String refundAddress;
	private BigDecimal returnScore;
	private String imageUpload;
	private Integer isReturnMoney;
	private String returnMoneyId;
	private Integer disabled;
	private Date createTime;
	private String memberImagePath;
	private String memberName;
	private String memberPhone;
	private String content;
	private String interevenComment;
	private String productData;
	private String comment;
	private String sellerReason;
	private String sellerComment;
	private String storeName;
	private Integer nums;
	private Long itemsId;
	private Long productId;
	private Integer appeal;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" interevenComment=").append(interevenComment);
		sb.append(", storeName=").append(storeName);
		sb.append(", productData=").append(productData);
		sb.append(", sellerReason=").append(sellerReason);
		sb.append(", sellerComment=").append(sellerComment);
		sb.append(", comment=").append(comment);
		sb.append(", content=").append(content);
		sb.append(", returnId=").append(returnId);
		sb.append(", orderId=").append(orderId);
		sb.append(", returnBn=").append(returnBn);
		sb.append(", title=").append(title);
		sb.append(", appealReturnId=").append(appealReturnId);
		sb.append(", status=").append(status);
		sb.append(", imageFile3=").append(imageFile3);
		sb.append(", imageFile=").append(imageFile);
		sb.append(", imageFile2=").append(imageFile2);
		sb.append(", interevenImage=").append(interevenImage);
		sb.append(", addTime=").append(addTime);
		sb.append(", sellerAmount=").append(sellerAmount);
		sb.append(", amount=").append(amount);
		sb.append(", shippingAmount=").append(shippingAmount);
		sb.append(", closeTime=").append(closeTime);
		sb.append(", storeId=").append(storeId);
		sb.append(", refundType=").append(refundType);
		sb.append(", isIntervene=").append(isIntervene);
		sb.append(", interveneReason=").append(interveneReason);
		sb.append(", intervenePhone=").append(intervenePhone);
		sb.append(", interveneMail=").append(interveneMail);
		sb.append(", shipCost=").append(shipCost);
		sb.append(", amountSeller=").append(amountSeller);
		sb.append(", isSafeguard=").append(isSafeguard);
		sb.append(", safeguardType=").append(safeguardType);
		sb.append(", safeguardRequire=").append(safeguardRequire);
		sb.append(", refundAddress=").append(refundAddress);
		sb.append(", returnScore=").append(returnScore);
		sb.append(", imageUpload=").append(imageUpload);
		sb.append(", isReturnMoney=").append(isReturnMoney);
		sb.append(", returnMoneyId=").append(returnMoneyId);
		sb.append(", disabled=").append(disabled);
		sb.append(", createTime=").append(createTime);
		sb.append(", memberImagePath=").append(memberImagePath);
		sb.append(", memberName=").append(memberName);
		sb.append(", memberPhone=").append(memberPhone);
		return sb.toString();
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getInterevenComment() {
		return interevenComment;
	}

	public void setInterevenComment(String interevenComment) {
		this.interevenComment = interevenComment;
	}

	public String getProductData() {
		return productData;
	}

	public void setProductData(String productData) {
		this.productData = productData;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSellerReason() {
		return sellerReason;
	}

	public void setSellerReason(String sellerReason) {
		this.sellerReason = sellerReason;
	}

	public String getSellerComment() {
		return sellerComment;
	}

	public void setSellerComment(String sellerComment) {
		this.sellerComment = sellerComment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getReturnId() {
		return returnId;
	}

	public void setReturnId(Long returnId) {
		this.returnId = returnId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getReturnBn() {
		return returnBn;
	}

	public void setReturnBn(String returnBn) {
		this.returnBn = returnBn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getAppealReturnId() {
		return appealReturnId;
	}

	public void setAppealReturnId(Long appealReturnId) {
		this.appealReturnId = appealReturnId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getImageFile3() {
		return imageFile3;
	}

	public void setImageFile3(String imageFile3) {
		this.imageFile3 = imageFile3;
	}

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public String getImageFile2() {
		return imageFile2;
	}

	public void setImageFile2(String imageFile2) {
		this.imageFile2 = imageFile2;
	}

	public String getInterevenImage() {
		return interevenImage;
	}

	public void setInterevenImage(String interevenImage) {
		this.interevenImage = interevenImage;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public BigDecimal getSellerAmount() {
		return sellerAmount;
	}

	public void setSellerAmount(BigDecimal sellerAmount) {
		this.sellerAmount = sellerAmount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getShippingAmount() {
		return shippingAmount;
	}

	public void setShippingAmount(BigDecimal shippingAmount) {
		this.shippingAmount = shippingAmount;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getRefundType() {
		return refundType;
	}

	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
	}

	public Integer getIsIntervene() {
		return isIntervene;
	}

	public void setIsIntervene(Integer isIntervene) {
		this.isIntervene = isIntervene;
	}

	public Integer getInterveneReason() {
		return interveneReason;
	}

	public void setInterveneReason(Integer interveneReason) {
		this.interveneReason = interveneReason;
	}

	public String getIntervenePhone() {
		return intervenePhone;
	}

	public void setIntervenePhone(String intervenePhone) {
		this.intervenePhone = intervenePhone;
	}

	public String getInterveneMail() {
		return interveneMail;
	}

	public void setInterveneMail(String interveneMail) {
		this.interveneMail = interveneMail;
	}

	public BigDecimal getShipCost() {
		return shipCost;
	}

	public void setShipCost(BigDecimal shipCost) {
		this.shipCost = shipCost;
	}

	public BigDecimal getAmountSeller() {
		return amountSeller;
	}

	public void setAmountSeller(BigDecimal amountSeller) {
		this.amountSeller = amountSeller;
	}

	public Integer getIsSafeguard() {
		return isSafeguard;
	}

	public void setIsSafeguard(Integer isSafeguard) {
		this.isSafeguard = isSafeguard;
	}

	public Integer getSafeguardType() {
		return safeguardType;
	}

	public void setSafeguardType(Integer safeguardType) {
		this.safeguardType = safeguardType;
	}

	public Integer getSafeguardRequire() {
		return safeguardRequire;
	}

	public void setSafeguardRequire(Integer safeguardRequire) {
		this.safeguardRequire = safeguardRequire;
	}

	public String getRefundAddress() {
		return refundAddress;
	}

	public void setRefundAddress(String refundAddress) {
		this.refundAddress = refundAddress;
	}

	public BigDecimal getReturnScore() {
		return returnScore;
	}

	public void setReturnScore(BigDecimal returnScore) {
		this.returnScore = returnScore;
	}

	public String getImageUpload() {
		return imageUpload;
	}

	public void setImageUpload(String imageUpload) {
		this.imageUpload = imageUpload;
	}

	public Integer getIsReturnMoney() {
		return isReturnMoney;
	}

	public void setIsReturnMoney(Integer isReturnMoney) {
		this.isReturnMoney = isReturnMoney;
	}

	public String getReturnMoneyId() {
		return returnMoneyId;
	}

	public void setReturnMoneyId(String returnMoneyId) {
		this.returnMoneyId = returnMoneyId;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMemberImagePath() {
		return memberImagePath;
	}

	public void setMemberImagePath(String memberImagePath) {
		this.memberImagePath = memberImagePath;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public Long getItemsId() {
		return itemsId;
	}

	public void setItemsId(Long itemsId) {
		this.itemsId = itemsId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getAppeal() {
		return appeal;
	}

	public void setAppeal(Integer appeal) {
		this.appeal = appeal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}