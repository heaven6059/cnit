package com.cnit.yoyo.model.goods;

import java.io.Serializable;

public class CompanyWithBLOBs extends Company implements Serializable {
    private static final long serialVersionUID = 2656168530546324996L;
    private String companyRemark;
    private String companyUrl;
    private String remark;
    private String approvedremark;
    

    public String getCompanyRemark() {
        return companyRemark;
    }

    public void setCompanyRemark(String companyRemark) {
        this.companyRemark = companyRemark;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApprovedremark() {
        return approvedremark;
    }

    public void setApprovedremark(String approvedremark) {
        this.approvedremark = approvedremark;
    }


}