package com.cnit.yoyo.api.base.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.annotation.JSONField;

// json的时候不输出null值
@JsonSerialize(include = Inclusion.NON_DEFAULT)
@XmlRootElement
public class Parameter implements Serializable {

	/**
     * 序列化UID
     */
    private static final long serialVersionUID = -5903110698377968413L;
    @NotBlank(message="{平台标识不能为空}")
	private String fphoneostype;
    
    /** 状态：失败 */
	public static final Integer STATUS_FAIL = 0;
	/** 状态：成功 */
	public static final Integer STATUS_SUCCESS = 1;

	/** 最小分页尺寸 */
	private static final Integer MIN_PAGE_SIZE = 5;

	/** 默认分页尺寸 */
	private static final Integer DEFAULT_PAGE_SIZE = 20;

	/** 最大分页尺寸 */
	private static final Integer MAX_PAGE_SIZE = 100;


	/** 签名 */
	private String sign;
	
	/** 时间戳 */
	private Long timestamp;
	
	/** 版本号 */
	private String v;

	/** 状态 （ 0：失败，1：成功， ） */
	private Integer status;

	/** 分页展示记录数 */
	private Integer pageSize;
	/** 当前页码 */
	private Integer currentPage;

	/** 总记录数量 */
	private Integer totalCount;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}
	
	

	/** 获得分页尺寸（参数是安全级别的） */
	@JsonIgnore
	@JSONField(serialize=false) // FastJson序列化的时候忽略掉
	public Integer getPageSizeSafety() {
		if (pageSize == null) {
			return DEFAULT_PAGE_SIZE;
		}
		if (pageSize < MIN_PAGE_SIZE || pageSize > MAX_PAGE_SIZE) {
			return DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/** 获得当前分页（参数是安全级别的） */
	@JsonIgnore
	@JSONField(serialize=false)  // FastJson序列化的时候忽略掉
	public Integer getCurrentPageSafety() {
		if (currentPage == null || currentPage <= 0) {
			return 1;
		}
		return currentPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getV() {
		return v;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public void setV(String v) {
		this.v = v;
	}

	public String getFphoneostype() {
		return fphoneostype;
	}

	public void setFphoneostype(String fphoneostype) {
		this.fphoneostype = fphoneostype;
	}

}
