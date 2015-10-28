package com.cnit.yoyo.model.car;

import java.io.Serializable;

/**
 * @ClassName: CarMaintainShip
 * @Description: 保养周期与车型的关系表
 * @author xiaox
 * @date 2015-3-26 下午4:11:09
 */
public class CarMaintainShip implements Serializable {
    private static final long serialVersionUID = 8001695524806897236L;
    private Integer id;
    private Integer maintainId;
    private Integer catId;
    private String disabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(Integer maintainId) {
        this.maintainId = maintainId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

}