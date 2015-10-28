package com.cnit.yoyo.model.goods;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 图片
 * @detail <功能详细描述>
 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
 * @version 1.0.0
 */
public class Picture implements Serializable {
    private static final long serialVersionUID = 8392012755855723060L;
    private Long pictureId;
    private String picturePath;
    private Long companyId;
    private Date modifyTime;

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}