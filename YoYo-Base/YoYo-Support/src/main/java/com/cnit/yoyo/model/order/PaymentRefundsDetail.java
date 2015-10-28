package com.cnit.yoyo.model.order;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PaymentRefundsDetail implements Serializable {
    private String batchNo;

    private String tradeNo;

    private String feel;

    private String result;
    
    
    private Date createTime;

    private static final long serialVersionUID = 1L;

    @DateTimeFormat(pattern="yyyy-MM-dd")  
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", batchNo=").append(batchNo);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", feel=").append(feel);
        sb.append(", result=").append(result);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PaymentRefundsDetail other = (PaymentRefundsDetail) that;
        return (this.getBatchNo() == null ? other.getBatchNo() == null : this.getBatchNo().equals(other.getBatchNo()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getFeel() == null ? other.getFeel() == null : this.getFeel().equals(other.getFeel()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBatchNo() == null) ? 0 : getBatchNo().hashCode());
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getFeel() == null) ? 0 : getFeel().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        return result;
    }
}