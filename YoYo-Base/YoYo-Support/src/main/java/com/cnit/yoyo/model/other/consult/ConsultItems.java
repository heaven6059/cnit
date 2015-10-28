package com.cnit.yoyo.model.other.consult;

import java.io.Serializable;

/**  
* @Title: ConsultItems.java
* @Package com.cnit.yoyo.model.other.consult
* @Description: 咨询项设置
* @Author 王鹏
* @date 2015-4-28 上午9:26:29
* @version V1.0  
*/ 
public class ConsultItems implements Serializable {
    /**
     * This field corresponds to the database column t_consult_items.id
     */
    private Integer id;

    /**
     * This field corresponds to the database column t_consult_items.consult_title
     */
    private String consultTitle;

    /**
     * This field corresponds to the database column t_consult_items.enabled
     */
    private Integer enabled;

    private static final long serialVersionUID = 1L;

    /**
     * @return the value of t_consult_items.id
     *
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the value for t_consult_items.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the value of t_consult_items.consult_title
     */
    public String getConsultTitle() {
        return consultTitle;
    }

    /**
     * @param consultTitle the value for t_consult_items.consult_title
     */
    public void setConsultTitle(String consultTitle) {
        this.consultTitle = consultTitle;
    }

    /**
     * @return the value of t_consult_items.enabled
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * @param enabled the value for t_consult_items.enabled
     *
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", consultTitle=").append(consultTitle);
        sb.append(", enabled=").append(enabled);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}