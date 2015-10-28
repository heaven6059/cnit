package com.cnit.yoyo.model.goods.dto;

import java.util.List;

import com.cnit.yoyo.model.goods.Label;
import com.cnit.yoyo.model.goods.dto.ShopBrandBLOBsWithLabel;
import com.cnit.yoyo.model.shop.ShopBrandWithBLOBs;

/** 
* @ClassName: ShopBrandWithBLOBConvert 
* @Description: 将 ShopBrandWithBLOBs转换为ShopBrandBLOBsWithLabel，用于前段页面展示带标签的品牌
* @author Harder-Zhao 
* @date 2015-3-31 下午12:17:46 
*  
*/
public class ShopBrandWithBLOBConvert {

	public static void convertToBrandLabel(ShopBrandBLOBsWithLabel brandLabel, ShopBrandBLOBsWithLabel brand, List<Label> labels) {
		brandLabel.setBrandAptitude(brand.getBrandAptitude());
		brandLabel.setBrandDesc(brand.getBrandDesc());
		brandLabel.setBrandId(brand.getBrandId());
		brandLabel.setBrandKeywords(brand.getBrandKeywords());
		brandLabel.setBrandLogo(brand.getBrandLogo());
		brandLabel.setBrandName(brand.getBrandName());
		brandLabel.setBrandUrl(brand.getBrandUrl());
		brandLabel.setCompanyBrandId(brand.getCompanyBrandId());
		brandLabel.setCompanyId(brand.getCompanyId());
		brandLabel.setDisabled(brand.getDisabled());
		brandLabel.setFailReason(brand.getFailReason());
		brandLabel.setLabel(brand.getLabel());
		brandLabel.setStatus(brand.getStatus());
		brandLabel.setStoreCat(brand.getStoreCat());
		brandLabel.setCatalogName(brand.getCatalogName());
		brandLabel.setCompanyName(brand.getCompanyName());
		brandLabel.setType(brand.getType());
		
		if(labels != null && labels.size() > 0){
			String[] labelNames = new String[labels.size()];
			String[] labelFontColors = new String[labels.size()];
			String[] labelBgColors = new String[labels.size()];
			int i = 0;
			for(Label label : labels){
				labelNames[i] = label.getName();
				labelFontColors[i] = label.getFontColor();
				labelBgColors[i] = label.getBgColor();
				i++;
			}
			brandLabel.setLabelNames(labelNames);
			brandLabel.setLabelFontColors(labelFontColors);
			brandLabel.setLabelBgColors(labelBgColors);
		}
	}

}
