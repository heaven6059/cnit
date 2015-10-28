package com.cnit.yoyo.goodsvirtualitems.model;
/**   
 * @Description: 购买的虚拟商品
 * @author  余平 yuping@cnit.com 
 * @date 2015-4-29 下午2:58:31 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.io.Serializable;
import java.util.Date;

public class GoodsVirtualItems implements Serializable {
    /***/
	private static final long serialVersionUID = -2231930802244581200L;
	private Long itemsId;//ID
    private Long goodsId;//商品ID
    private String goodsName;//商品名称
    private Integer productId;//货品ID
    private String cardId;//卡号
    private String cardPsw;//密码
    private Long storeId;//店铺ID
    private Long orderId;//使用订单号
    private String isUsed;//是否发放
    private String random;//卡号密码生成的key
    private Date sendTime;//发放时间
    private String random2;//卡号密码生成的 key
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" itemsId=").append(itemsId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", productId=").append(productId);
        sb.append(", cardId=").append(cardId);
        sb.append(", cardPsw=").append(cardPsw);
        sb.append(", storeId=").append(storeId);
        sb.append(", orderId=").append(orderId);
        sb.append(", isUsed=").append(isUsed);
        sb.append(", random=").append(random);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", random2=").append(random2);
        return sb.toString();
    }

	public Long getItemsId() {
		return itemsId;
	}

	public void setItemsId(Long itemsId) {
		this.itemsId = itemsId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardPsw() {
		return cardPsw;
	}

	public void setCardPsw(String cardPsw) {
		this.cardPsw = cardPsw;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getRandom2() {
		return random2;
	}

	public void setRandom2(String random2) {
		this.random2 = random2;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	
    
}