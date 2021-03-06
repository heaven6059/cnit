package com.cnit.yoyo.model.order;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderBills extends OrderBillsKey implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_bills.payment_id
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    private String paymentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_bills.order_id
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    private Long orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_bills.pay_object
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    private String payObject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_bills.bill_id
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    private String billId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_bills.money
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    private BigDecimal money;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_bills.refund_id
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    private String refundId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_order_bills
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_bills.payment_id
     *
     * @return the value of t_order_bills.payment_id
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_bills.payment_id
     *
     * @param paymentId the value for t_order_bills.payment_id
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_bills.order_id
     *
     * @return the value of t_order_bills.order_id
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_bills.order_id
     *
     * @param orderId the value for t_order_bills.order_id
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_bills.pay_object
     *
     * @return the value of t_order_bills.pay_object
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    public String getPayObject() {
        return payObject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_bills.pay_object
     *
     * @param payObject the value for t_order_bills.pay_object
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    public void setPayObject(String payObject) {
        this.payObject = payObject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_bills.bill_id
     *
     * @return the value of t_order_bills.bill_id
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    public String getBillId() {
        return billId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_bills.bill_id
     *
     * @param billId the value for t_order_bills.bill_id
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    public void setBillId(String billId) {
        this.billId = billId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_bills.money
     *
     * @return the value of t_order_bills.money
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_bills.money
     *
     * @param money the value for t_order_bills.money
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_bills.refund_id
     *
     * @return the value of t_order_bills.refund_id
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    public String getRefundId() {
        return refundId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_bills.refund_id
     *
     * @param refundId the value for t_order_bills.refund_id
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_bills
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paymentId=").append(paymentId);
        sb.append(", orderId=").append(orderId);
        sb.append(", payObject=").append(payObject);
        sb.append(", billId=").append(billId);
        sb.append(", money=").append(money);
        sb.append(", refundId=").append(refundId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_bills
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
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
        OrderBills other = (OrderBills) that;
        return (this.getRelId() == null ? other.getRelId() == null : this.getRelId().equals(other.getRelId()))
            && (this.getBillType() == null ? other.getBillType() == null : this.getBillType().equals(other.getBillType()))
            && (this.getPaymentId() == null ? other.getPaymentId() == null : this.getPaymentId().equals(other.getPaymentId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getPayObject() == null ? other.getPayObject() == null : this.getPayObject().equals(other.getPayObject()))
            && (this.getBillId() == null ? other.getBillId() == null : this.getBillId().equals(other.getBillId()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getRefundId() == null ? other.getRefundId() == null : this.getRefundId().equals(other.getRefundId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_bills
     *
     * @mbggenerated Tue Apr 21 15:08:18 CST 2015
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRelId() == null) ? 0 : getRelId().hashCode());
        result = prime * result + ((getBillType() == null) ? 0 : getBillType().hashCode());
        result = prime * result + ((getPaymentId() == null) ? 0 : getPaymentId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getPayObject() == null) ? 0 : getPayObject().hashCode());
        result = prime * result + ((getBillId() == null) ? 0 : getBillId().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getRefundId() == null) ? 0 : getRefundId().hashCode());
        return result;
    }
}