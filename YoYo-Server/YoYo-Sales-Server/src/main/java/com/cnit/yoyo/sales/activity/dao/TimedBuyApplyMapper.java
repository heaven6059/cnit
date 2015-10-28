package com.cnit.yoyo.sales.activity.dao;

import com.cnit.yoyo.model.sales.activity.TimedBuyApply;

public interface TimedBuyApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TimedBuyApply record);

    int insertSelective(TimedBuyApply record);

    TimedBuyApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TimedBuyApply record);

    int updateByPrimaryKey(TimedBuyApply record);
}