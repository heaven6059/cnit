/**
 * 文 件 名   :  carDamageQueryDTO.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="zjcai@chinacnit.com">蔡志杰</a>
 * 创建时间  :  2015-5-12 下午6:37:01
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.model.painting.dto;

import java.io.Serializable;

import com.cnit.yoyo.dto.BaseQryDTO;

/**
 *@description <一句话功能简述>
 *@detail <功能详细描述>
 *@author <a href="zjcai@chinacnit.com">蔡志杰</a>
 *@version 1.0.0
 */
public class CarDamageQueryDTO extends BaseQryDTO implements Serializable {

    private static final long serialVersionUID = -5122542211794480656L;
   
    //受损单号
    private Integer carDamageId;
    //商铺ID
    private Integer companyId;
    //0:未审核，1：通过；2:未通过
    private String passStatus;
    
    private Integer memberId;
    
    //卖家课件（passStatus =1 或者3）
    private Boolean canSee;

    private String[] passStatusArr;
    
	public String[] getPassStatusArr() {
		return passStatusArr;
	}
	public void setPassStatusArr(String[] passStatusArr) {
		this.passStatusArr = passStatusArr;
	}
	public Boolean getCanSee() {
		return canSee;
	}
	public void setCanSee(Boolean canSee) {
		this.canSee = canSee;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getPassStatus() {
		return passStatus;
	}
	public void setPassStatus(String passStatus) {
		this.passStatus = passStatus;
	}
	public Integer getCarDamageId() {
        return carDamageId;
    }
    public void setCarDamageId(Integer carDamageId) {
        this.carDamageId = carDamageId;
    }
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
}
