package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.cnit.yoyo.dto.BaseQryDTO;
import com.cnit.yoyo.model.goods.Picture;

public class GoodsProductSpecDTO implements Serializable {
	private static final long serialVersionUID = -7223105370827288266L;
	private Integer productId;
	private String bn;
	private BigDecimal price;
	private BigDecimal cost;
	private BigDecimal mktprice;
	private BigDecimal timeprice;
	private Integer store;
	private String storePlace;
	private String disabled;
	private String marketable;
	private String specInfo;
	private String specDesc;
	private List<Picture> pictures;  //货品图片
	private String picIds;  //货品图片ids xiaox 2015.05.11
	private String name;    //货品名称
	private String picturePath;  //货品默认图片，订单列表可用于展示
	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getBn() {
		return bn;
	}

	public void setBn(String bn) {
		this.bn = bn;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getMktprice() {
		return mktprice;
	}

	public void setMktprice(BigDecimal mktprice) {
		this.mktprice = mktprice;
	}
	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

	public String getStorePlace() {
		return storePlace;
	}

	public void setStorePlace(String storePlace) {
		this.storePlace = storePlace;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getMarketable() {
		return marketable;
	}

	public void setMarketable(String marketable) {
		this.marketable = marketable;
	}

	public String getSpecInfo() {
		return specInfo;
	}

	public void setSpecInfo(String specInfo) {
		this.specInfo = specInfo;
	}

	public String getSpecDesc() {
		return specDesc;
	}

	public void setSpecDesc(String specDesc) {
		this.specDesc = specDesc;
	}

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public String getPicIds() {
        return picIds;
    }

    public void setPicIds(String picIds) {
        this.picIds = picIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

	public BigDecimal getTimeprice() {
		return timeprice;
	}

	public void setTimeprice(BigDecimal timeprice) {
		this.timeprice = timeprice;
	}
	
	
}
