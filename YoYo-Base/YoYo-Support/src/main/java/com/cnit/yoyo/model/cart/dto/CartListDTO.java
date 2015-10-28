package com.cnit.yoyo.model.cart.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cnit.yoyo.model.cart.Cart;

/**
 * 
 * @ClassName: CartListDTO
 * @Description: 购物车列表传输对象
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
 * @date 2015-4-9 下午6:57:02
 * 
 */
public class CartListDTO extends Cart implements Serializable {

	// 店铺名称 t_company
	private String companyName;
	// 店铺名称 t_store
	private String storeName;
	// 商品名称 t_product
	private String productName;
	// 商品单价 t_goods
	private Double goodsPrice;
	// 商品默认图片路径 t_goods
	private String goodsImage;
	// 商品库存
	private Integer store;
	// //是否销售
	// private String marketable;
	// 商品单价 t_goods
	private Double goodsSumPrice;

	private String appointment;

	private String bn;
	// 成本
	private BigDecimal cost;
	// 重量
	private BigDecimal weight;
	// 积分
	private BigDecimal score;
	// 货品规格信息
	private String specInfo;
	// 货品类型
	private String goodsType;
	// 商品类型编号
	private String identifier;

	// 货品规格详细信息
	private String specDesc;
	// 商品是否失效 t_goods.disabled
	private String gDisabled;
	// 商品是否上架 t_goods.marketable
	private String gMarketable;
	// 货品是否失效 t_product.disabled
	private String pDisabled;
	// 货品是否上架 t_product.marketable
	private String pMarketable;

	private String limitGoodsdown;// 下架所有商品

	private String limitStore;// 店铺屏蔽

	private String limitStoredown;// 关闭店铺

	private String shopStatus;// 店铺开业状态

	private String status;// 店铺开启状态

	private String disabled;// 店铺是否失效

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

	// public String getMarketable() {
	// return marketable;
	// }
	//
	// public void setMarketable(String marketable) {
	// this.marketable = marketable;
	// }

	public Double getGoodsSumPrice() {
		return goodsSumPrice;
	}

	public void setGoodsSumPrice(Double goodsSumPrice) {
		this.goodsSumPrice = goodsSumPrice;
	}

	public String getBn() {
		return bn;
	}

	public void setBn(String bn) {
		this.bn = bn;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getSpecInfo() {
		return specInfo;
	}

	public void setSpecInfo(String specInfo) {
		this.specInfo = specInfo;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSpecDesc() {
		return specDesc;
	}

	public void setSpecDesc(String specDesc) {
		this.specDesc = specDesc;
	}

	public String getgDisabled() {
		return gDisabled;
	}

	public void setgDisabled(String gDisabled) {
		this.gDisabled = gDisabled;
	}

	public String getgMarketable() {
		return gMarketable;
	}

	public void setgMarketable(String gMarketable) {
		this.gMarketable = gMarketable;
	}

	public String getpDisabled() {
		return pDisabled;
	}

	public void setpDisabled(String pDisabled) {
		this.pDisabled = pDisabled;
	}

	public String getpMarketable() {
		return pMarketable;
	}

	public void setpMarketable(String pMarketable) {
		this.pMarketable = pMarketable;
	}

	public String getLimitGoodsdown() {
		return limitGoodsdown;
	}

	public void setLimitGoodsdown(String limitGoodsdown) {
		this.limitGoodsdown = limitGoodsdown;
	}

	public String getLimitStore() {
		return limitStore;
	}

	public void setLimitStore(String limitStore) {
		this.limitStore = limitStore;
	}

	public String getLimitStoredown() {
		return limitStoredown;
	}

	public void setLimitStoredown(String limitStoredown) {
		this.limitStoredown = limitStoredown;
	}

	public String getShopStatus() {
		return shopStatus;
	}

	public void setShopStatus(String shopStatus) {
		this.shopStatus = shopStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getAppointment() {
		return appointment;
	}

	public void setAppointment(String appointment) {
		this.appointment = appointment;
	}

}
