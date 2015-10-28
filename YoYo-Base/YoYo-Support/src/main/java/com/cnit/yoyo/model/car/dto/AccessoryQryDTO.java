/**
 * 文 件 名   :  AccessoryQryDTO.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年4月3日 上午10:03:28
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cnit.yoyo.dto.BaseQryDTO;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnitcloud.com">李明</a>
 * @version 1.0.0
 */
public class AccessoryQryDTO extends BaseQryDTO implements Serializable {
    private static final long serialVersionUID = 3147643716110212911L;
    private Integer accIdQ;
    private Integer catIdQ;
    private String accNameQ;
    private String accSpecQ;
    private String accUnitQ;
    private String accForshortQ;
    private String accCodeQ;
    private String accOemQ;
    private Integer brandIdQ;
    private String brandNameQ;
    private String accMainPlantsQ;
    private BigDecimal accScPriceQ;
    private BigDecimal accPriceQ;
    private String accPackQ;
    private String accLogisticsQ;
    private String disabledQ;
    private Integer orderNumQ;
    private String remarkQ;
    private String catalogNameQ;
    
    public Integer getAccIdQ() {
        return accIdQ;
    }
    public void setAccIdQ(Integer accIdQ) {
        this.accIdQ = accIdQ;
    }
    public Integer getCatIdQ() {
        return catIdQ;
    }
    public void setCatIdQ(Integer catIdQ) {
        this.catIdQ = catIdQ;
    }
    public String getAccNameQ() {
        return accNameQ;
    }
    public void setAccNameQ(String accNameQ) {
        this.accNameQ = accNameQ;
    }
    public String getAccSpecQ() {
        return accSpecQ;
    }
    public void setAccSpecQ(String accSpecQ) {
        this.accSpecQ = accSpecQ;
    }
    public String getAccUnitQ() {
        return accUnitQ;
    }
    public void setAccUnitQ(String accUnitQ) {
        this.accUnitQ = accUnitQ;
    }
    public String getAccForshortQ() {
        return accForshortQ;
    }
    public void setAccForshortQ(String accForshortQ) {
        this.accForshortQ = accForshortQ;
    }
    public String getAccCodeQ() {
        return accCodeQ;
    }
    public void setAccCodeQ(String accCodeQ) {
        this.accCodeQ = accCodeQ;
    }
    public String getAccOemQ() {
        return accOemQ;
    }
    public void setAccOemQ(String accOemQ) {
        this.accOemQ = accOemQ;
    }
    public Integer getBrandIdQ() {
        return brandIdQ;
    }
    public void setBrandIdQ(Integer brandIdQ) {
        this.brandIdQ = brandIdQ;
    }
    public String getBrandNameQ() {
        return brandNameQ;
    }
    public void setBrandNameQ(String brandNameQ) {
        this.brandNameQ = brandNameQ;
    }
    public String getAccMainPlantsQ() {
        return accMainPlantsQ;
    }
    public void setAccMainPlantsQ(String accMainPlantsQ) {
        this.accMainPlantsQ = accMainPlantsQ;
    }
    public BigDecimal getAccScPriceQ() {
        return accScPriceQ;
    }
    public void setAccScPriceQ(BigDecimal accScPriceQ) {
        this.accScPriceQ = accScPriceQ;
    }
    public BigDecimal getAccPriceQ() {
        return accPriceQ;
    }
    public void setAccPriceQ(BigDecimal accPriceQ) {
        this.accPriceQ = accPriceQ;
    }
    public String getAccPackQ() {
        return accPackQ;
    }
    public void setAccPackQ(String accPackQ) {
        this.accPackQ = accPackQ;
    }
    public String getAccLogisticsQ() {
        return accLogisticsQ;
    }
    public void setAccLogisticsQ(String accLogisticsQ) {
        this.accLogisticsQ = accLogisticsQ;
    }
    public String getDisabledQ() {
        return disabledQ;
    }
    public void setDisabledQ(String disabledQ) {
        this.disabledQ = disabledQ;
    }
    public Integer getOrderNumQ() {
        return orderNumQ;
    }
    public void setOrderNumQ(Integer orderNumQ) {
        this.orderNumQ = orderNumQ;
    }
    public String getRemarkQ() {
        return remarkQ;
    }
    public void setRemarkQ(String remarkQ) {
        this.remarkQ = remarkQ;
    }
	public String getCatalogNameQ() {
		return catalogNameQ;
	}
	public void setCatalogNameQ(String catalogNameQ) {
		this.catalogNameQ = catalogNameQ;
	}
}
