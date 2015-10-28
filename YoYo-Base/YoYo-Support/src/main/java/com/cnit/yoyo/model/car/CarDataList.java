package com.cnit.yoyo.model.car;

import java.io.Serializable;

/**
 * @ClassName: CarDataList
 * @Description: 多个选项的数据类型
 * @author xiaox
 * @date 2015-3-31 下午5:00:12
 */
public class CarDataList implements Serializable {
    private static final long serialVersionUID = -8447559395700638438L;
    private Integer listId;
    private String listName;
    private String data;

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}