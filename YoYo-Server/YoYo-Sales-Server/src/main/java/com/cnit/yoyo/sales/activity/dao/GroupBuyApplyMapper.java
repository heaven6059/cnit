package com.cnit.yoyo.sales.activity.dao;

import com.cnit.yoyo.model.sales.activity.GroupBuyApply;

public interface GroupBuyApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GroupBuyApply record);

    int insertSelective(GroupBuyApply record);

    GroupBuyApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupBuyApply record);

    int updateByPrimaryKeyWithBLOBs(GroupBuyApply record);

    int updateByPrimaryKey(GroupBuyApply record);
}