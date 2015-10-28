package com.cnit.yoyo.model.shop.dto;

import java.io.Serializable;
import java.util.List;

import com.cnit.yoyo.dto.BaseQryDTO;

/**
 * 商家详情
 * @author wangz
 *
 */
public class ShopDetailInfoDTO extends BaseQryDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long companyId;

    private String shopName; 
   
    private Integer accountId;

    private String storeName;  //店铺名称
   
    private String storeAddr;
    
    private String storeTel; 
    
    private String storeBrief;
    
    private String image;
    
    private List<String> images;
    
    private Integer couponNum;   //优惠券数量  
    
    private Integer isFocus;  //是否关注 0:否,1:是
    
    private Double goodsPoints; //商品评分
    
    private Double servicePoints; //服务评分
    
    private Double timePoints; //时效评分

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddr() {
		return storeAddr;
	}

	public void setStoreAddr(String storeAddr) {
		this.storeAddr = storeAddr;
	}

	public String getStoreTel() {
		return storeTel;
	}

	public void setStoreTel(String storeTel) {
		this.storeTel = storeTel;
	}

	public String getStoreBrief() {
		return storeBrief;
	}

	public void setStoreBrief(String storeBrief) {
		this.storeBrief = storeBrief;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public Integer getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(Integer couponNum) {
		this.couponNum = couponNum;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getIsFocus() {
		return isFocus;
	}

	public void setIsFocus(Integer isFocus) {
		this.isFocus = isFocus;
	}

	public Double getGoodsPoints() {
		return goodsPoints;
	}

	public void setGoodsPoints(Double goodsPoints) {
		this.goodsPoints = goodsPoints;
	}

	public Double getServicePoints() {
		return servicePoints;
	}

	public void setServicePoints(Double servicePoints) {
		this.servicePoints = servicePoints;
	}

	public Double getTimePoints() {
		return timePoints;
	}

	public void setTimePoints(Double timePoints) {
		this.timePoints = timePoints;
	}

    
}
