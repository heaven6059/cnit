package com.cnit.yoyo.model.order;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class PaymentRefunds implements Serializable {
	
	private static final long serialVersionUID = 3514034431839811416L;

	private String batchNo;

    private String successNum;

    private String resultDetails;

    private String notifyTime;

    private String notifyType;

    private String notifyId;

    private String signType;

    private String sign;

    private Date createTime;
    
    
    private String batch_no;                                                                    
    private String success_num;                                                                 
    private String result_details;
    private String notify_time;                                                                    
    private String notify_type;                                                                             
    private String notify_id;                                                                              
    private String sign_type;                                               


    public String getBatchNo() {
    	if (StringUtils.isNotBlank(getBatch_no())) {
			return getBatch_no();
		}
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getSuccessNum() {
    	if (StringUtils.isNotBlank(getSuccess_num())) {
			return getSuccess_num();
		}
        return successNum;
    }

    public void setSuccessNum(String successNum) {
        this.successNum = successNum;
    }

    public String getResultDetails() {
    	if (StringUtils.isNotBlank(getResult_details())) {
			return getResult_details();
		}
        return resultDetails;
    }

    public void setResultDetails(String resultDetails) {
        this.resultDetails = resultDetails;
    }

    public String getNotifyTime() {
    	if (StringUtils.isNotBlank(getNotify_time())) {
			return getNotify_time();
		}
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getNotifyType() {
    	if (StringUtils.isNotBlank(getNotify_type())) {
			return getNotify_type();
		}
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getNotifyId() {
    	if (StringUtils.isNotBlank(getNotify_id())) {
			return getNotify_id();
		}
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getSignType() {
    	if (StringUtils.isNotBlank(getSign_type())) {
			return getSign_type();
		}
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getSuccess_num() {
		return success_num;
	}

	public void setSuccess_num(String success_num) {
		this.success_num = success_num;
	}

	public String getResult_details() {
		return result_details;
	}

	public void setResult_details(String result_details) {
		this.result_details = result_details;
	}

	public String getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}

	public String getNotify_type() {
		return notify_type;
	}

	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", batchNo=").append(batchNo);
        sb.append(", successNum=").append(successNum);
        sb.append(", resultDetails=").append(resultDetails);
        sb.append(", notifyTime=").append(notifyTime);
        sb.append(", notifyType=").append(notifyType);
        sb.append(", notifyId=").append(notifyId);
        sb.append(", signType=").append(signType);
        sb.append(", sign=").append(sign);
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
        PaymentRefunds other = (PaymentRefunds) that;
        return (this.getBatchNo() == null ? other.getBatchNo() == null : this.getBatchNo().equals(other.getBatchNo()))
            && (this.getSuccessNum() == null ? other.getSuccessNum() == null : this.getSuccessNum().equals(other.getSuccessNum()))
            && (this.getResultDetails() == null ? other.getResultDetails() == null : this.getResultDetails().equals(other.getResultDetails()))
            && (this.getNotifyTime() == null ? other.getNotifyTime() == null : this.getNotifyTime().equals(other.getNotifyTime()))
            && (this.getNotifyType() == null ? other.getNotifyType() == null : this.getNotifyType().equals(other.getNotifyType()))
            && (this.getNotifyId() == null ? other.getNotifyId() == null : this.getNotifyId().equals(other.getNotifyId()))
            && (this.getSignType() == null ? other.getSignType() == null : this.getSignType().equals(other.getSignType()))
            && (this.getSign() == null ? other.getSign() == null : this.getSign().equals(other.getSign()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBatchNo() == null) ? 0 : getBatchNo().hashCode());
        result = prime * result + ((getSuccessNum() == null) ? 0 : getSuccessNum().hashCode());
        result = prime * result + ((getResultDetails() == null) ? 0 : getResultDetails().hashCode());
        result = prime * result + ((getNotifyTime() == null) ? 0 : getNotifyTime().hashCode());
        result = prime * result + ((getNotifyType() == null) ? 0 : getNotifyType().hashCode());
        result = prime * result + ((getNotifyId() == null) ? 0 : getNotifyId().hashCode());
        result = prime * result + ((getSignType() == null) ? 0 : getSignType().hashCode());
        result = prime * result + ((getSign() == null) ? 0 : getSign().hashCode());
        return result;
    }
}