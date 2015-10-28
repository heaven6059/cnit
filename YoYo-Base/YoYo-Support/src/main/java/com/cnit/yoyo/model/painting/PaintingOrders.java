package com.cnit.yoyo.model.painting;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cnit.yoyo.complain.model.Complain;
import com.cnit.yoyo.model.goods.Store;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.model.order.OrderLog;

public class PaintingOrders implements Serializable {
	
	private List<OrderLog> logs;
	
	public List<OrderLog> getLogs() {
		return logs;
	}

	public void setLogs(List<OrderLog> logs) {
		this.logs = logs;
	}

	private Long companyId;
	
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	private int relayCount;
	
	private String source;
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getRelayCount() {
		return relayCount;
	}

	public void setRelayCount(int relayCount) {
		this.relayCount = relayCount;
	}

	private CarDamageOffer carDamageOffer;
	
	public CarDamageOffer getCarDamageOffer() {
		return carDamageOffer;
	}

	public void setCarDamageOffer(CarDamageOffer carDamageOffer) {
		this.carDamageOffer = carDamageOffer;
	}

	private Integer complainCount;	  	
	
	public Integer getComplainCount() {
		return complainCount;
	}

	public void setComplainCount(Integer complainCount) {
		this.complainCount = complainCount;
	}

	private List<Long> ids;
	
	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	//订单关联分店
	private Store store;
	
	private List<CarDamageOfferDetail> damageOfferList;

	private Member member;
	
	private Integer accountId;
	
	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	private PamAccount pamAccount;
	
	public PamAccount getPamAccount() {
		return pamAccount;
	}

	public void setPamAccount(PamAccount pamAccount) {
		this.pamAccount = pamAccount;
	}

	private Complain complain;
	
	public Complain getComplain() {
		return complain;
	}

	public void setComplain(Complain complain) {
		this.complain = complain;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	private Integer memberId;
	
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	//支付方式
	private String payStatus;
	
	//买家姓名和手机号
	private String name;
	private String phone;
	
	//选择消费时间
	private Date consumptionTime;
	
	private String consumptionTimeStr;
	
	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.id
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.createtime
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.car_damage_id
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private Integer carDamageId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.car_damage_offer_id
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private Integer carDamageOfferId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.store_id
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private Integer storeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.store_name
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private String storeName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.last_modified
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private Date lastModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.payment
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private String payment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.payment_id
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private Integer paymentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.status
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.confirm
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private String confirm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.tostr
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private String tostr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.score_u
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private String scoreU;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.score_g
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private String scoreG;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.payed
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private BigDecimal payed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.memo
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.disabled
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private String disabled;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.mark_type
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private String markType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.mark_text
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private String markText;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.confirm_time
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private Date confirmTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.comments_count
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private Integer commentsCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_painting_orders.refund_status
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private String refundStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_painting_orders
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    private static final long serialVersionUID = 1L;
    
	public List<CarDamageOfferDetail> getDamageOfferList() {
		return damageOfferList;
	}

	public void setDamageOfferList(List<CarDamageOfferDetail> damageOfferList) {
		this.damageOfferList = damageOfferList;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

    public Date getConsumptionTime() {
		return consumptionTime;
	}

	public void setConsumptionTime(Date consumptionTime) {
		this.consumptionTime = consumptionTime;
	}

    public String getConsumptionTimeStr() {
		return consumptionTimeStr;
	}

	public void setConsumptionTimeStr(String consumptionTimeStr) {
		this.consumptionTimeStr = consumptionTimeStr;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.id
     *
     * @return the value of t_painting_orders.id
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.id
     *
     * @param id the value for t_painting_orders.id
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.createtime
     *
     * @return the value of t_painting_orders.createtime
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.createtime
     *
     * @param createtime the value for t_painting_orders.createtime
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.car_damage_id
     *
     * @return the value of t_painting_orders.car_damage_id
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public Integer getCarDamageId() {
        return carDamageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.car_damage_id
     *
     * @param carDamageId the value for t_painting_orders.car_damage_id
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setCarDamageId(Integer carDamageId) {
        this.carDamageId = carDamageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.car_damage_offer_id
     *
     * @return the value of t_painting_orders.car_damage_offer_id
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public Integer getCarDamageOfferId() {
        return carDamageOfferId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.car_damage_offer_id
     *
     * @param carDamageOfferId the value for t_painting_orders.car_damage_offer_id
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setCarDamageOfferId(Integer carDamageOfferId) {
        this.carDamageOfferId = carDamageOfferId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.store_id
     *
     * @return the value of t_painting_orders.store_id
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.store_id
     *
     * @param storeId the value for t_painting_orders.store_id
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.store_name
     *
     * @return the value of t_painting_orders.store_name
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.store_name
     *
     * @param storeName the value for t_painting_orders.store_name
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.last_modified
     *
     * @return the value of t_painting_orders.last_modified
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public Date getLastModified() {
        return lastModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.last_modified
     *
     * @param lastModified the value for t_painting_orders.last_modified
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.payment
     *
     * @return the value of t_painting_orders.payment
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public String getPayment() {
        return payment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.payment
     *
     * @param payment the value for t_painting_orders.payment
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setPayment(String payment) {
        this.payment = payment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.payment_id
     *
     * @return the value of t_painting_orders.payment_id
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public Integer getPaymentId() {
        return paymentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.payment_id
     *
     * @param paymentId the value for t_painting_orders.payment_id
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.status
     *
     * @return the value of t_painting_orders.status
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.status
     *
     * @param status the value for t_painting_orders.status
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.confirm
     *
     * @return the value of t_painting_orders.confirm
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public String getConfirm() {
        return confirm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.confirm
     *
     * @param confirm the value for t_painting_orders.confirm
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.tostr
     *
     * @return the value of t_painting_orders.tostr
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public String getTostr() {
        return tostr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.tostr
     *
     * @param tostr the value for t_painting_orders.tostr
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setTostr(String tostr) {
        this.tostr = tostr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.score_u
     *
     * @return the value of t_painting_orders.score_u
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public String getScoreU() {
        return scoreU;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.score_u
     *
     * @param scoreU the value for t_painting_orders.score_u
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setScoreU(String scoreU) {
        this.scoreU = scoreU;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.score_g
     *
     * @return the value of t_painting_orders.score_g
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public String getScoreG() {
        return scoreG;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.score_g
     *
     * @param scoreG the value for t_painting_orders.score_g
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setScoreG(String scoreG) {
        this.scoreG = scoreG;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.payed
     *
     * @return the value of t_painting_orders.payed
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public BigDecimal getPayed() {
        return payed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.payed
     *
     * @param payed the value for t_painting_orders.payed
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setPayed(BigDecimal payed) {
        this.payed = payed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.memo
     *
     * @return the value of t_painting_orders.memo
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.memo
     *
     * @param memo the value for t_painting_orders.memo
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.disabled
     *
     * @return the value of t_painting_orders.disabled
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public String getDisabled() {
        return disabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.disabled
     *
     * @param disabled the value for t_painting_orders.disabled
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.mark_type
     *
     * @return the value of t_painting_orders.mark_type
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public String getMarkType() {
        return markType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.mark_type
     *
     * @param markType the value for t_painting_orders.mark_type
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setMarkType(String markType) {
        this.markType = markType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.mark_text
     *
     * @return the value of t_painting_orders.mark_text
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public String getMarkText() {
        return markText;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.mark_text
     *
     * @param markText the value for t_painting_orders.mark_text
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setMarkText(String markText) {
        this.markText = markText;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.confirm_time
     *
     * @return the value of t_painting_orders.confirm_time
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public Date getConfirmTime() {
        return confirmTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.confirm_time
     *
     * @param confirmTime the value for t_painting_orders.confirm_time
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.comments_count
     *
     * @return the value of t_painting_orders.comments_count
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public Integer getCommentsCount() {
        return commentsCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.comments_count
     *
     * @param commentsCount the value for t_painting_orders.comments_count
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_painting_orders.refund_status
     *
     * @return the value of t_painting_orders.refund_status
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public String getRefundStatus() {
        return refundStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_painting_orders.refund_status
     *
     * @param refundStatus the value for t_painting_orders.refund_status
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_painting_orders
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createtime=").append(createtime);
        sb.append(", carDamageId=").append(carDamageId);
        sb.append(", carDamageOfferId=").append(carDamageOfferId);
        sb.append(", storeId=").append(storeId);
        sb.append(", storeName=").append(storeName);
        sb.append(", lastModified=").append(lastModified);
        sb.append(", payment=").append(payment);
        sb.append(", paymentId=").append(paymentId);
        sb.append(", status=").append(status);
        sb.append(", confirm=").append(confirm);
        sb.append(", tostr=").append(tostr);
        sb.append(", scoreU=").append(scoreU);
        sb.append(", scoreG=").append(scoreG);
        sb.append(", payed=").append(payed);
        sb.append(", memo=").append(memo);
        sb.append(", disabled=").append(disabled);
        sb.append(", markType=").append(markType);
        sb.append(", markText=").append(markText);
        sb.append(", confirmTime=").append(confirmTime);
        sb.append(", commentsCount=").append(commentsCount);
        sb.append(", refundStatus=").append(refundStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_painting_orders
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
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
        PaintingOrders other = (PaintingOrders) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getCarDamageId() == null ? other.getCarDamageId() == null : this.getCarDamageId().equals(other.getCarDamageId()))
            && (this.getCarDamageOfferId() == null ? other.getCarDamageOfferId() == null : this.getCarDamageOfferId().equals(other.getCarDamageOfferId()))
            && (this.getStoreId() == null ? other.getStoreId() == null : this.getStoreId().equals(other.getStoreId()))
            && (this.getStoreName() == null ? other.getStoreName() == null : this.getStoreName().equals(other.getStoreName()))
            && (this.getLastModified() == null ? other.getLastModified() == null : this.getLastModified().equals(other.getLastModified()))
            && (this.getPayment() == null ? other.getPayment() == null : this.getPayment().equals(other.getPayment()))
            && (this.getPaymentId() == null ? other.getPaymentId() == null : this.getPaymentId().equals(other.getPaymentId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getConfirm() == null ? other.getConfirm() == null : this.getConfirm().equals(other.getConfirm()))
            && (this.getTostr() == null ? other.getTostr() == null : this.getTostr().equals(other.getTostr()))
            && (this.getScoreU() == null ? other.getScoreU() == null : this.getScoreU().equals(other.getScoreU()))
            && (this.getScoreG() == null ? other.getScoreG() == null : this.getScoreG().equals(other.getScoreG()))
            && (this.getPayed() == null ? other.getPayed() == null : this.getPayed().equals(other.getPayed()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getDisabled() == null ? other.getDisabled() == null : this.getDisabled().equals(other.getDisabled()))
            && (this.getMarkType() == null ? other.getMarkType() == null : this.getMarkType().equals(other.getMarkType()))
            && (this.getMarkText() == null ? other.getMarkText() == null : this.getMarkText().equals(other.getMarkText()))
            && (this.getConfirmTime() == null ? other.getConfirmTime() == null : this.getConfirmTime().equals(other.getConfirmTime()))
            && (this.getCommentsCount() == null ? other.getCommentsCount() == null : this.getCommentsCount().equals(other.getCommentsCount()))
            && (this.getRefundStatus() == null ? other.getRefundStatus() == null : this.getRefundStatus().equals(other.getRefundStatus()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_painting_orders
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getCarDamageId() == null) ? 0 : getCarDamageId().hashCode());
        result = prime * result + ((getCarDamageOfferId() == null) ? 0 : getCarDamageOfferId().hashCode());
        result = prime * result + ((getStoreId() == null) ? 0 : getStoreId().hashCode());
        result = prime * result + ((getStoreName() == null) ? 0 : getStoreName().hashCode());
        result = prime * result + ((getLastModified() == null) ? 0 : getLastModified().hashCode());
        result = prime * result + ((getPayment() == null) ? 0 : getPayment().hashCode());
        result = prime * result + ((getPaymentId() == null) ? 0 : getPaymentId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getConfirm() == null) ? 0 : getConfirm().hashCode());
        result = prime * result + ((getTostr() == null) ? 0 : getTostr().hashCode());
        result = prime * result + ((getScoreU() == null) ? 0 : getScoreU().hashCode());
        result = prime * result + ((getScoreG() == null) ? 0 : getScoreG().hashCode());
        result = prime * result + ((getPayed() == null) ? 0 : getPayed().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getDisabled() == null) ? 0 : getDisabled().hashCode());
        result = prime * result + ((getMarkType() == null) ? 0 : getMarkType().hashCode());
        result = prime * result + ((getMarkText() == null) ? 0 : getMarkText().hashCode());
        result = prime * result + ((getConfirmTime() == null) ? 0 : getConfirmTime().hashCode());
        result = prime * result + ((getCommentsCount() == null) ? 0 : getCommentsCount().hashCode());
        result = prime * result + ((getRefundStatus() == null) ? 0 : getRefundStatus().hashCode());
        return result;
    }
}