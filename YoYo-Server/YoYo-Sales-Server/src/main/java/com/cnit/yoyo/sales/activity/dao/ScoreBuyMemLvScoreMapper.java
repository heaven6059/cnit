package com.cnit.yoyo.sales.activity.dao;

import com.cnit.yoyo.model.sales.activity.ScoreBuyMemLvScore;

public interface ScoreBuyMemLvScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScoreBuyMemLvScore record);

    int insertSelective(ScoreBuyMemLvScore record);

    ScoreBuyMemLvScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScoreBuyMemLvScore record);

    int updateByPrimaryKey(ScoreBuyMemLvScore record);
}