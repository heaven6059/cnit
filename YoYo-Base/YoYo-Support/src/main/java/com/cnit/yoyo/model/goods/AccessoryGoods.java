package com.cnit.yoyo.model.goods;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName: AccessoryGoods
 * @Description: 商品配件
 * @author xiaox
 * @date 2015-4-14 下午2:41:41
 */
public class AccessoryGoods implements Serializable {

	private static final long serialVersionUID = -7094675850654165772L;
	private Long id;
	private String accGroupName;
	private Integer minBuy;
	private Integer maxBuy;
	private String discType;
	private BigDecimal credit;
	private String accessoryGoods;
	private String actype;
	private String searchKeywords;
	private String catIds;
	private String brandIds;
	private String tags;
	private BigDecimal priceform;
	private BigDecimal priceto;
	private Long goodsId;
	private BigDecimal discountPrice;
	private List<ProductWithBLOBs> products;
	private Double sumPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccGroupName() {
		return accGroupName;
	}

	public void setAccGroupName(String accGroupName) {
		this.accGroupName = accGroupName;
	}

	public Integer getMinBuy() {
		return minBuy;
	}

	public void setMinBuy(Integer minBuy) {
		this.minBuy = minBuy;
	}

	public Integer getMaxBuy() {
		return maxBuy;
	}

	public void setMaxBuy(Integer maxBuy) {
		this.maxBuy = maxBuy;
	}

	public String getDiscType() {
		return discType;
	}

	public void setDiscType(String discType) {
		this.discType = discType;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public String getAccessoryGoods() {
		return accessoryGoods;
	}

	public void setAccessoryGoods(String accessoryGoods) {
		this.accessoryGoods = accessoryGoods;
	}

	public String getActype() {
		return actype;
	}

	public void setActype(String actype) {
		this.actype = actype;
	}

	public String getSearchKeywords() {
		return searchKeywords;
	}

	public void setSearchKeywords(String searchKeywords) {
		this.searchKeywords = searchKeywords;
	}

	public String getCatIds() {
		return catIds;
	}

	public void setCatIds(String catIds) {
		this.catIds = catIds;
	}

	public String getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(String brandIds) {
		this.brandIds = brandIds;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public BigDecimal getPriceform() {
		return priceform;
	}

	public void setPriceform(BigDecimal priceform) {
		this.priceform = priceform;
	}

	public BigDecimal getPriceto() {
		return priceto;
	}

	public void setPriceto(BigDecimal priceto) {
		this.priceto = priceto;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public BigDecimal getDiscountPrice() {
		if(null==sumPrice)return new BigDecimal(0);
		if (discType.equals("minus")) {
			discountPrice = BigDecimal.valueOf(sumPrice).subtract(credit);
		} else if (discType.equals("discount")) {
			discountPrice = BigDecimal.valueOf(sumPrice).multiply(credit);
		} else {
			discountPrice = BigDecimal.valueOf(sumPrice);
		}
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public List<ProductWithBLOBs> getProducts() {
		return products;
	}

	public void setProducts(List<ProductWithBLOBs> products) {
		this.products = products;
	}

}