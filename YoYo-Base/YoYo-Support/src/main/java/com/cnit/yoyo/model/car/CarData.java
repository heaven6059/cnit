package com.cnit.yoyo.model.car;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cnit.yoyo.model.car.dto.CarCompareDTO;

/**
 * @ClassName: CarData
 * @Description: 车型数据项
 * @author xiaox
 * @date 2015-3-31 上午10:48:44
 */
public class CarData implements Serializable {

	private static final long serialVersionUID = -3631466442236700803L;
	private Integer dataId;
	private String dataType;
	private Integer orderId;
	private String dataformat;
	private String displayName;
	private Integer listId;
	private Integer catalogId;
	private String disabled;
	private Date createtime;

	private List<CarCompareDTO> compares;

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

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public List<CarCompareDTO> getCompares() {
		return compares;
	}

	public void setCompares(List<CarCompareDTO> compares) {
		this.compares = compares;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}