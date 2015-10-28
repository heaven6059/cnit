package com.cnit.yoyo.sales.activity.dao;

import java.util.List;

import com.cnit.yoyo.model.sales.activity.ScoreBuyActivity;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyActivityQryDTO;

public interface ScoreBuyActivityMapper {
    int deleteByPrimaryKey(Integer actId);
    int insert(ScoreBuyActivity record);
    int insertSelective(ScoreBuyActivity record);
    ScoreBuyActivity selectByPrimaryKey(Integer actId);
    int updateByPrimaryKeySelective(ScoreBuyActivity record);
    int updateByPrimaryKeyWithBLOBs(ScoreBuyActivity record);
    int updateByPrimaryKey(ScoreBuyActivity record);
    List<ScoreBuyActivity> selectScoreBuyActivity(ScoreBuyActivityQryDTO qryDTO);
}