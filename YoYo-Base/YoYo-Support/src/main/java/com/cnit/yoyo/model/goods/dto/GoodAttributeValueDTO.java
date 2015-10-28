/**
 * 文 件 名   :  GoodAttributeValueDTO.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年4月13日 上午11:23:46
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnitcloud.com">李明</a>
 * @version 1.0.0
 */
public class GoodAttributeValueDTO implements Serializable {
	private static final long serialVersionUID = -3995234375526824913L;

	private Integer attrId;
	private String attrName;
	private Integer attrValueId;
	private Integer goodsId;
	private Integer catId;
	private String attrValues;
	private String attrInputType;
	private String attrValue;

	public Integer getAttrValueId() {
		return attrValueId;
	}

	public void setAttrValueId(Integer attrValueId) {
		this.attrValueId = attrValueId;
	}

	public Integer getAttrId() {
		return attrId;
	}

	public void setAttrId(Integer attrId) {
		this.attrId = attrId;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
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

	public String getAttrValues() {
		return attrValues;
	}

	public void setAttrValues(String attrValues) {
		this.attrValues = attrValues;
	}

	public String getAttrInputType() {
		return attrInputType;
	}

	public void setAttrInputType(String attrInputType) {
		this.attrInputType = attrInputType;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

}
