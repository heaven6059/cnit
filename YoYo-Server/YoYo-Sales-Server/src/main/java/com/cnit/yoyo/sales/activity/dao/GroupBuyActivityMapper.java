package com.cnit.yoyo.sales.activity.dao;

import com.cnit.yoyo.model.sales.activity.GroupBuyActivity;

public interface GroupBuyActivityMapper {
    int deleteByPrimaryKey(Long actId);

    int insert(GroupBuyActivity record);

    int insertSelective(GroupBuyActivity record);

    GroupBuyActivity selectByPrimaryKey(Long actId);

    int updateByPrimaryKeySelective(GroupBuyActivity record);

    int updateByPrimaryKeyWithBLOBs(GroupBuyActivity record);

    int updateByPrimaryKey(GroupBuyActivity record);
}