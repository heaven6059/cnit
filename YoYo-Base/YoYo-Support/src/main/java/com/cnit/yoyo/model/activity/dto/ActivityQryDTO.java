/**
 * 文 件 名   :  GoodsQryDTO.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年4月3日 下午4:05:25
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.model.activity.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cnit.yoyo.dto.BaseQryDTO;

/**
 */
public class ActivityQryDTO extends BaseQryDTO implements Serializable {
    private static final long serialVersionUID = 3972668715243253431L;
    private Long companyId;
    private Long storeId;        //分店id
    
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
    
    
    

}
