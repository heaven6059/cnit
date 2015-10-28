package com.cnit.yoyo.model.sales.activity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GroupBuyApply implements Serializable {
    private Long id;

    private Long goodsId;

    private String goodsName;

    private Long catId;

    private Long aid;

    private Long storeId;

    private BigDecimal price;

    private BigDecimal lastPrice;

    private Integer remainnums;

    private Integer nums;

    private Integer personlimit;

    private Integer status;

    private String remark;

    private String imageCodeid;

    private Date lastMidifity;

    private String actDesc;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    public Integer getRemainnums() {
        return remainnums;
    }

    public void setRemainnums(Integer remainnums) {
        this.remainnums = remainnums;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Integer getPersonlimit() {
        return personlimit;
    }

    public void setPersonlimit(Integer personlimit) {
        this.personlimit = personlimit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImageCodeid() {
        return imageCodeid;
    }

    public void setImageCodeid(String imageCodeid) {
        this.imageCodeid = imageCodeid;
    }

    public Date getLastMidifity() {
        return lastMidifity;
    }

    public void setLastMidifity(Date lastMidifity) {
        this.lastMidifity = lastMidifity;
    }

    public String getActDesc() {
        return actDesc;
    }

    public void setActDesc(String actDesc) {
        this.actDesc = actDesc;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", catId=").append(catId);
        sb.append(", aid=").append(aid);
        sb.append(", storeId=").append(storeId);
        sb.append(", price=").append(price);
        sb.append(", lastPrice=").append(lastPrice);
        sb.append(", remainnums=").append(remainnums);
        sb.append(", nums=").append(nums);
        sb.append(", personlimit=").append(personlimit);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", imageCodeid=").append(imageCodeid);
        sb.append(", lastMidifity=").append(lastMidifity);
        sb.append(", actDesc=").append(actDesc);
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
        GroupBuyApply other = (GroupBuyApply) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getGoodsName() == null ? other.getGoodsName() == null : this.getGoodsName().equals(other.getGoodsName()))
            && (this.getCatId() == null ? other.getCatId() == null : this.getCatId().equals(other.getCatId()))
            && (this.getAid() == null ? other.getAid() == null : this.getAid().equals(other.getAid()))
            && (this.getStoreId() == null ? other.getStoreId() == null : this.getStoreId().equals(other.getStoreId()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getLastPrice() == null ? other.getLastPrice() == null : this.getLastPrice().equals(other.getLastPrice()))
            && (this.getRemainnums() == null ? other.getRemainnums() == null : this.getRemainnums().equals(other.getRemainnums()))
            && (this.getNums() == null ? other.getNums() == null : this.getNums().equals(other.getNums()))
            && (this.getPersonlimit() == null ? other.getPersonlimit() == null : this.getPersonlimit().equals(other.getPersonlimit()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getImageCodeid() == null ? other.getImageCodeid() == null : this.getImageCodeid().equals(other.getImageCodeid()))
            && (this.getLastMidifity() == null ? other.getLastMidifity() == null : this.getLastMidifity().equals(other.getLastMidifity()))
            && (this.getActDesc() == null ? other.getActDesc() == null : this.getActDesc().equals(other.getActDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getCatId() == null) ? 0 : getCatId().hashCode());
        result = prime * result + ((getAid() == null) ? 0 : getAid().hashCode());
        result = prime * result + ((getStoreId() == null) ? 0 : getStoreId().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getLastPrice() == null) ? 0 : getLastPrice().hashCode());
        result = prime * result + ((getRemainnums() == null) ? 0 : getRemainnums().hashCode());
        result = prime * result + ((getNums() == null) ? 0 : getNums().hashCode());
        result = prime * result + ((getPersonlimit() == null) ? 0 : getPersonlimit().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getImageCodeid() == null) ? 0 : getImageCodeid().hashCode());
        result = prime * result + ((getLastMidifity() == null) ? 0 : getLastMidifity().hashCode());
        result = prime * result + ((getActDesc() == null) ? 0 : getActDesc().hashCode());
        return result;
    }
}