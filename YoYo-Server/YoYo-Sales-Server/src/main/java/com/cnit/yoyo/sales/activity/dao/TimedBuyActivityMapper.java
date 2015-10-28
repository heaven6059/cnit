package com.cnit.yoyo.sales.activity.dao;

import com.cnit.yoyo.model.sales.activity.TimedBuyActivity;

public interface TimedBuyActivityMapper {
    int deleteByPrimaryKey(Integer actId);

    int insert(TimedBuyActivity record);

    int insertSelective(TimedBuyActivity record);

    TimedBuyActivity selectByPrimaryKey(Integer actId);

    int updateByPrimaryKeySelective(TimedBuyActivity record);

    int updateByPrimaryKeyWithBLOBs(TimedBuyActivity record);

    int updateByPrimaryKey(TimedBuyActivity record);
}