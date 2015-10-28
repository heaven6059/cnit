package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @ClassName: CarData 
 * @Description: 车型数据项DTO
 * @author xiaox
 * @date 2015-3-31 上午11:18:44
 */
public class CarDataDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private Integer dataId;

    private String dataType;

    private Integer orderId;

    private String dataformat;

    private String displayName;

    private Integer listId;

    private Integer catalogId;
    
    private String disabled;
    
    private Date createtime;
    
    //以下为新增
    private String catalogName; //类别名称
    
    private String listValue;  //列表数据项
    
    private String listName;
    
    private String dataValue;//数据项的值
    
    private String orderStmt;  //排序

    public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getDataformat() {
		return dataformat;
	}

	public void setDataformat(String dataformat) {
		this.dataformat = dataformat;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getListId() {
		return listId;
	}

	public void setListId(Integer listId) {
		this.listId = listId;
	}

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getListValue() {
		return listValue;
	}

	public void setListValue(String listValue) {
		this.listValue = listValue;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

    public String getOrderStmt() {
        return orderStmt;
    }

    public void setOrderStmt(String orderStmt) {
        this.orderStmt = orderStmt;
    }

   
}