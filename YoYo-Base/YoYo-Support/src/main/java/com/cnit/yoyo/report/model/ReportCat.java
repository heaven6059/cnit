package com.cnit.yoyo.report.model;

import java.io.Serializable;

public class ReportCat implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 399338467171448218L;
	private Integer catId;
    private String catName;
    private Integer parentId;
    private String catPath;
    private String isLeaf;
    private Integer pOrder;
    private Integer childCount;
    private String disabled;
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" catId=").append(catId);
        sb.append(", catName=").append(catName);
        sb.append(", parentId=").append(parentId);
        sb.append(", catPath=").append(catPath);
        sb.append(", isLeaf=").append(isLeaf);
        sb.append(", pOrder=").append(pOrder);
        sb.append(", childCount=").append(childCount);
        sb.append(", disabled=").append(disabled);
        return sb.toString();
    }
	public Integer getCatId() {
		return catId;
	}
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getCatPath() {
		return catPath;
	}
	public void setCatPath(String catPath) {
		this.catPath = catPath;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Integer getpOrder() {
		return pOrder;
	}
	public void setpOrder(Integer pOrder) {
		this.pOrder = pOrder;
	}
	public Integer getChildCount() {
		return childCount;
	}
	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
    
    
}