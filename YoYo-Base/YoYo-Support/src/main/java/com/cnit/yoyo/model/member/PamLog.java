package com.cnit.yoyo.model.member;

import java.io.Serializable;
import java.util.Date;

public class PamLog implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pam_log.event_id
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    private String eventId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pam_log.account_id
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    private String accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pam_log.event_type
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    private String eventType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pam_log.event_time
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    private Date eventTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_pam_log.event_data
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    private String eventData;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_pam_log
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pam_log.event_id
     *
     * @return the value of t_pam_log.event_id
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pam_log.event_id
     *
     * @param eventId the value for t_pam_log.event_id
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pam_log.account_id
     *
     * @return the value of t_pam_log.account_id
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pam_log.account_id
     *
     * @param accountId the value for t_pam_log.account_id
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pam_log.event_type
     *
     * @return the value of t_pam_log.event_type
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pam_log.event_type
     *
     * @param eventType the value for t_pam_log.event_type
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pam_log.event_time
     *
     * @return the value of t_pam_log.event_time
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    public Date getEventTime() {
        return eventTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pam_log.event_time
     *
     * @param eventTime the value for t_pam_log.event_time
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_pam_log.event_data
     *
     * @return the value of t_pam_log.event_data
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    public String getEventData() {
        return eventData;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_pam_log.event_data
     *
     * @param eventData the value for t_pam_log.event_data
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pam_log
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", eventId=").append(eventId);
        sb.append(", accountId=").append(accountId);
        sb.append(", eventType=").append(eventType);
        sb.append(", eventTime=").append(eventTime);
        sb.append(", eventData=").append(eventData);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pam_log
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
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
        PamLog other = (PamLog) that;
        return (this.getEventId() == null ? other.getEventId() == null : this.getEventId().equals(other.getEventId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getEventType() == null ? other.getEventType() == null : this.getEventType().equals(other.getEventType()))
            && (this.getEventTime() == null ? other.getEventTime() == null : this.getEventTime().equals(other.getEventTime()))
            && (this.getEventData() == null ? other.getEventData() == null : this.getEventData().equals(other.getEventData()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pam_log
     *
     * @mbggenerated Mon Feb 02 17:17:55 CST 2015
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEventId() == null) ? 0 : getEventId().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getEventType() == null) ? 0 : getEventType().hashCode());
        result = prime * result + ((getEventTime() == null) ? 0 : getEventTime().hashCode());
        result = prime * result + ((getEventData() == null) ? 0 : getEventData().hashCode());
        return result;
    }
}