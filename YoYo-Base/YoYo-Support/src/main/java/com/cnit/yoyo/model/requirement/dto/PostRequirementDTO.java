package com.cnit.yoyo.model.requirement.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.cnit.yoyo.dto.BaseQryDTO;

public class PostRequirementDTO extends BaseQryDTO  implements Serializable {

	private static final long serialVersionUID = 1888862927988366728L;
	
    private String passStatus;
    
    private Integer accountId;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    private String name;
    
	private List<Object> ids;

    private String loginName;

    public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Object> getIds() {
		return ids;
	}

	public void setIds(List<Object> ids) {
		this.ids = ids;
	}

	public String getPassStatus() {
		return passStatus;
	}

	public void setPassStatus(String passStatus) {
		this.passStatus = passStatus;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
