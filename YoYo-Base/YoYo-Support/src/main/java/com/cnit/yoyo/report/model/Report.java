package com.cnit.yoyo.report.model;

import java.io.Serializable;
import java.util.Date;

public class Report implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8453459777134854647L;
	private Integer productId;//商品编号
	private Long reportId;//举报编号
    private Integer catId;// 举报类型id
    private Integer fromMemberId;//举报方
    private String fromUname;//举报人
    private Integer toMemberId;//被举报方
    private String toUname;//店主名
    private String source;//举报方来源 default 'buyer' buyer => (''买家''),''seller'' => (''卖家'')'
    private String mobile;//手机
    private Long storeId;//店铺Id
    private String storeName;//店铺名
    private String status;//举报状态  default 'intervene' comment '''intervene'' => (''平台介入''),''voucher'' => (''取证中''),
  //''success'' => (''举报成立''),''error'' => (''举报不成立''),''cancel'' => (''举报撤销''),''finish'' => (''完成''),',
    private Date createtime;//申请时间
    private Date lastModified;//更新时间
    private String disabled;//失效  default 'false' comment '（true：是；false：否）'
    private String reason;//举报原因 false 虚假信息 unconformity 图片不符
    private String memo;//备注
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" reportId=").append(reportId);
        sb.append(" productId=").append(productId);
        sb.append(", catId=").append(catId);
        sb.append(", fromMemberId=").append(fromMemberId);
        sb.append(", fromUname=").append(fromUname);
        sb.append(", toMemberId=").append(toMemberId);
        sb.append(", toUname=").append(toUname);
        sb.append(", source=").append(source);
        sb.append(", mobile=").append(mobile);
        sb.append(", storeId=").append(storeId);
        sb.append(", storeName=").append(storeName);
        sb.append(", status=").append(status);
        sb.append(", createtime=").append(createtime);
        sb.append(", lastModified=").append(lastModified);
        sb.append(", disabled=").append(disabled);
        sb.append(", reason=").append(reason);
        sb.append(", memo=").append(memo);
        return sb.toString();
    }
    
    
	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public Long getReportId() {
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	public Integer getCatId() {
		return catId;
	}
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	public Integer getFromMemberId() {
		return fromMemberId;
	}
	public void setFromMemberId(Integer fromMemberId) {
		this.fromMemberId = fromMemberId;
	}
	public String getFromUname() {
		return fromUname;
	}
	public void setFromUname(String fromUname) {
		this.fromUname = fromUname;
	}
	public Integer getToMemberId() {
		return toMemberId;
	}
	public void setToMemberId(Integer toMemberId) {
		this.toMemberId = toMemberId;
	}
	public String getToUname() {
		return toUname;
	}
	public void setToUname(String toUname) {
		this.toUname = toUname;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	public Date getCreatetime() {
		return createtime;
	}


	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}


	public Date getLastModified() {
		return lastModified;
	}


	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}


	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}