package com.cnit.yoyo.model.other.consult;

import java.io.Serializable;

/**  
* @Title: GoodsPoint.java
* @Package com.cnit.yoyo.model.other.consult
* @Description: 商品评分选项设置
* @Author 王鹏
* @date 2015-4-28 上午9:24:37
* @version V1.0  
*/ 
public class GoodsPoint implements Serializable {
    /**
     * This field corresponds to the database column t_goods_point.id
     */
    private Integer id;

    /**
     * This field corresponds to the database column t_goods_point.point_title
     */
    private String pointTitle;

    /**
     * This field corresponds to the database column t_goods_point.enabled
     */
    private Integer enabled;

    /**
     * This field corresponds to the database column t_goods_point.one_desc
     */
    private String oneDesc;

    /**
     * This field corresponds to the database column t_goods_point.tow_desc
     */
    private String towDesc;

    /**
     * This field corresponds to the database column t_goods_point.three_desc
     */
    private String threeDesc;

    /**
     * This field corresponds to the database column t_goods_point.four_desc
     */
    private String fourDesc;

    /**
     * This field corresponds to the database column t_goods_point.five_desc
     */
    private String fiveDesc;

    /**
     * This field corresponds to the database column t_goods_point.consult_comment_type
     */
    private Integer consultCommentType;

    /**
     * This field corresponds to the database table t_goods_point
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return the value of t_goods_point.id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the value for t_goods_point.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the value of t_goods_point.point_title
     */
    public String getPointTitle() {
        return pointTitle;
    }

    /**
     * @param pointTitle the value for t_goods_point.point_title
     */
    public void setPointTitle(String pointTitle) {
        this.pointTitle = pointTitle;
    }

    /**
     * @return the value of t_goods_point.enabled
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * @param enabled the value for t_goods_point.enabled
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the value of t_goods_point.one_desc
     */
    public String getOneDesc() {
        return oneDesc;
    }

    /**
     * @param oneDesc the value for t_goods_point.one_desc
     */
    public void setOneDesc(String oneDesc) {
        this.oneDesc = oneDesc;
    }

    /**
     * @return the value of t_goods_point.tow_desc
     */
    public String getTowDesc() {
        return towDesc;
    }

    /**
     * @param towDesc the value for t_goods_point.tow_desc
     */
    public void setTowDesc(String towDesc) {
        this.towDesc = towDesc;
    }

    /**
     * @return the value of t_goods_point.three_desc
     */
    public String getThreeDesc() {
        return threeDesc;
    }

    /**
     * @param threeDesc the value for t_goods_point.three_desc
     */
    public void setThreeDesc(String threeDesc) {
        this.threeDesc = threeDesc;
    }

    /**
     * @return the value of t_goods_point.four_desc
     */
    public String getFourDesc() {
        return fourDesc;
    }

    /**
     * @param fourDesc the value for t_goods_point.four_desc
     */
    public void setFourDesc(String fourDesc) {
        this.fourDesc = fourDesc;
    }

    /**
     * @return the value of t_goods_point.five_desc
     */
    public String getFiveDesc() {
        return fiveDesc;
    }

    /**
     * @param fiveDesc the value for t_goods_point.five_desc
     */
    public void setFiveDesc(String fiveDesc) {
        this.fiveDesc = fiveDesc;
    }

    /**
     * @return the value of t_goods_point.consult_comment_type
     */
    public Integer getConsultCommentType() {
        return consultCommentType;
    }

    /**
     * @param consultCommentType the value for t_goods_point.consult_comment_type
     */
    public void setConsultCommentType(Integer consultCommentType) {
        this.consultCommentType = consultCommentType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pointTitle=").append(pointTitle);
        sb.append(", enabled=").append(enabled);
        sb.append(", oneDesc=").append(oneDesc);
        sb.append(", towDesc=").append(towDesc);
        sb.append(", threeDesc=").append(threeDesc);
        sb.append(", fourDesc=").append(fourDesc);
        sb.append(", fiveDesc=").append(fiveDesc);
        sb.append(", consultCommentType=").append(consultCommentType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}