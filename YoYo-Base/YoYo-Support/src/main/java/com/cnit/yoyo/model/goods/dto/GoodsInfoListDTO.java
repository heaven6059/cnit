/**
 * 文 件 名   :  GoodsInfoListDTO.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年4月7日 上午10:09:16
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnitcloud.com">李明</a>
 * @version 1.0.0
 */
public class GoodsInfoListDTO implements Serializable {
	private static final long serialVersionUID = 878909619141132500L;
	private Integer goodsId;
	private Integer catId;// 商品分类
	private String catLogo;// 商品分类logo
	private String catName;// 商品分类名称
	private Integer companyId;
	private String thumbnailPic;// 缩略图
	private String name;// 商品名称
	private String storeName;// 店铺名称
	private BigDecimal weight;// 重量
	private BigDecimal score;// 积分
	private String lastModify;// 最近修改时间
	private String downTime;// 下架时间
	private String upTime;// 上架时间
	private Integer store;// 库存
	private BigDecimal mktPrice;// 市场价
	private String dbn;// 平台商品编号
	private String fbn;// 商家商品编号
	private String brief;// 商品简介
	private BigDecimal cost;// 成本
	private Integer brandId;// 商品品牌id
	private String brandName;// 商品品牌
	private String brandLogo;// 商品品牌logo
	private BigDecimal price;// 销售价
	private Long prices;// 销售价(分为单位)
	private Integer carId;// 车型id
	private String carName;// 车型名称
	private String iconFile;// 车型logo
	private Integer favCount;// 收藏数量
	private Integer rankCount;// 评分次数
	private Integer commentsCount;// 评论次数
	private Integer viewCount;// 浏览次数
	private Integer viewWCount;// 周浏览次数
	private Integer viewMCount;// 月浏览次数
	private Integer buyCount;// 购买次数
	private Integer buyWCount;// 周购买次数
	private Integer buyMCount;// 月购买次数
	private String unit;// 单位
	private String marketable;// 上下架状态
	private Integer accId; // 配件id
	private String smallPic;
	private String midPic;
	private String bigPic;
	private String p22;

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getCatLogo() {
		return catLogo;
	}

	public void setCatLogo(String catLogo) {
		this.catLogo = catLogo;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getThumbnailPic() {
		return thumbnailPic;
	}

	public void setThumbnailPic(String thumbnailPic) {
		this.thumbnailPic = thumbnailPic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getP22() {
		return p22;
	}

	public void setP22(String p22) {
		this.p22 = p22;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	public String getLastModify() {
		return lastModify;
	}

	public void setLastModify(String lastModify) {
		this.lastModify = lastModify;
	}

	public String getDownTime() {
		return downTime;
	}

	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}

	public String getUpTime() {
		return upTime;
	}

	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

	public BigDecimal getMktPrice() {
		return mktPrice;
	}

	public void setMktPrice(BigDecimal mktPrice) {
		this.mktPrice = mktPrice;
	}

	public String getDbn() {
		return dbn;
	}

	public void setDbn(String dbn) {
		this.dbn = dbn;
	}

	public String getFbn() {
		return fbn;
	}

	public void setFbn(String fbn) {
		this.fbn = fbn;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandLogo() {
		return brandLogo;
	}

	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getIconFile() {
		return iconFile;
	}

	public void setIconFile(String iconFile) {
		this.iconFile = iconFile;
	}

	public Integer getFavCount() {
		return favCount;
	}

	public void setFavCount(Integer favCount) {
		this.favCount = favCount;
	}

	public Integer getRankCount() {
		return rankCount;
	}

	public void setRankCount(Integer rankCount) {
		this.rankCount = rankCount;
	}

	public Integer getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(Integer commentsCount) {
		this.commentsCount = commentsCount;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getViewWCount() {
		return viewWCount;
	}

	public void setViewWCount(Integer viewWCount) {
		this.viewWCount = viewWCount;
	}

	public Integer getViewMCount() {
		return viewMCount;
	}

	public void setViewMCount(Integer viewMCount) {
		this.viewMCount = viewMCount;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public Integer getBuyWCount() {
		return buyWCount;
	}

	public void setBuyWCount(Integer buyWCount) {
		this.buyWCount = buyWCount;
	}

	public Integer getBuyMCount() {
		return buyMCount;
	}

	public void setBuyMCount(Integer buyMCount) {
		this.buyMCount = buyMCount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMarketable() {
		return marketable;
	}

	public void setMarketable(String marketable) {
		this.marketable = marketable;
	}

	public String getSmallPic() {
		return smallPic;
	}

	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}

	public String getMidPic() {
		return midPic;
	}

	public void setMidPic(String midPic) {
		this.midPic = midPic;
	}

	public String getBigPic() {
		return bigPic;
	}

	public void setBigPic(String bigPic) {
		this.bigPic = bigPic;
	}

	public Long getPrices() {
		return prices;
	}

	public void setPrices(Long prices) {
		this.prices = prices;
	}

	public String getPic180x180() {
		if (StringUtils.isNotBlank(this.getBigPic())) {
			String[] pic = this.getBigPic().split("\\.");
			return pic[0] + ".180x180." + pic[1];
		}
		return null;
	}

	public String getPic300x300() {
		if (StringUtils.isNotBlank(this.getBigPic())) {
			String[] pic = this.getBigPic().split("\\.");
			return pic[0] + ".300x300." + pic[1];
		}
		return null;
	}

	public String getPic110x110() {
		if (StringUtils.isNotBlank(this.getBigPic())) {
			String[] pic = this.getBigPic().split("\\.");
			return pic[0] + ".110x110." + pic[1];
		}
		return null;
	}

	public String getPic200x200() {
		if (StringUtils.isNotBlank(this.getBigPic())) {
			String[] pic = this.getBigPic().split("\\.");
			return pic[0] + ".200x200." + pic[1];
		}
		return null;
	}

	public String getPic220x220() {
		if (StringUtils.isNotBlank(this.getBigPic())) {
			String[] pic = this.getBigPic().split("\\.");
			return pic[0] + ".220x220." + pic[1];
		}
		return null;
	}

	public String getPic160x160() {
		if (StringUtils.isNotBlank(this.getBigPic())) {
			String[] pic = this.getBigPic().split("\\.");
			return pic[0] + ".160x160." + pic[1];
		}
		return null;
	}

	public String getPic44x44() {
		if (StringUtils.isNotBlank(this.getBigPic())) {
			String[] pic = this.getBigPic().split("\\.");
			return pic[0] + ".44x44." + pic[1];
		}
		return null;
	}
}