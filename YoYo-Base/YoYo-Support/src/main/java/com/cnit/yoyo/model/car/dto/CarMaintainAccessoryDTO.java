package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;

/**
 * 
 *@description 保养配件dto
 *@detail <功能详细描述>
 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 *@version 1.0.0
 */
public class CarMaintainAccessoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer categoryId;

    private Integer[] accIds;  //配件类别ids

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer[] getAccIds() {
        return accIds;
    }

    public void setAccIds(Integer[] accIds) {
        this.accIds = accIds;
    }

   
}