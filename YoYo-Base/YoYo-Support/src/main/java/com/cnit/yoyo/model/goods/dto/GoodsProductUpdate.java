package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;
import java.util.List;

import com.cnit.yoyo.model.goods.AccessoryGoods;
import com.cnit.yoyo.model.goods.AttributeGoodsIndex;
import com.cnit.yoyo.model.goods.GoodsRelated;
import com.cnit.yoyo.model.goods.GoodsWithBLOBs;

/**
 * @Description: 更新商品实体
 * @author wanghb
 * @date 2015-4-20
 */
public class GoodsProductUpdate implements Serializable {
	private static final long serialVersionUID = -3819439555429029141L;
	private GoodsWithBLOBs info;// 基本信息
	private List<AttributeGoodsIndex> attrJson;// 扩展信息
	private List<GoodsRelated> relateGoodsJson; // 关联商品
	private List<AccessoryGoods> accessoryJson;// 配件
	private List<GoodsProductSpecDTO> products;//货品
	public GoodsWithBLOBs getInfo() {
		return info;
	}

	public void setInfo(GoodsWithBLOBs info) {
		this.info = info;
	}

	public List<AttributeGoodsIndex> getAttrJson() {
		return attrJson;
	}

	public void setAttrJson(List<AttributeGoodsIndex> attrJson) {
		this.attrJson = attrJson;
	}

	public List<GoodsRelated> getRelateGoodsJson() {
		return relateGoodsJson;
	}

	public void setRelateGoodsJson(List<GoodsRelated> relateGoodsJson) {
		this.relateGoodsJson = relateGoodsJson;
	}

	public List<AccessoryGoods> getAccessoryJson() {
		return accessoryJson;
	}

	public void setAccessoryJson(List<AccessoryGoods> accessoryJson) {
		this.accessoryJson = accessoryJson;
	}

	public List<GoodsProductSpecDTO> getProducts() {
		return products;
	}

	public void setProducts(List<GoodsProductSpecDTO> products) {
		this.products = products;
	}

}