package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;
/**
 * 
 * @ClassName: StoreDTO 
 * @Description: 分店数据传输对象
 * @author xiaox
 * @date 2015-3-18 下午2:16:44
 */
public class StoreDTO implements Serializable {
	
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -24729625091537558L;
	private Long storeId;
	private String storeName;
	private String addr;
	private String area;
	private String createtime;
	private Long companyId;
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	
}
