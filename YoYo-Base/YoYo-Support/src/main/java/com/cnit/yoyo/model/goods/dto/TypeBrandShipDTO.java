package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;
/**
 * 
 * @ClassName: TypeBrandShip 
 * @Description: 商品分类与品牌的关系对象
 * @author xiaox
 * @date 2015-3-25 下午7:15:29
 */
public class TypeBrandShipDTO  implements Serializable {
  
    private Integer id;

    private Integer brandId;   
    
    private Integer catId;

    private Integer brandOrder;
    
    private String categoryName;
    
    private String brandName;
    
    private String logo;

    private static final long serialVersionUID = 1L;

	public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public Integer getBrandOrder() {
		return brandOrder;
	}

	public void setBrandOrder(Integer brandOrder) {
		this.brandOrder = brandOrder;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

   
}