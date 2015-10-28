package com.cnit.yoyo.sales.activity.dao;

import java.util.List;

import com.cnit.yoyo.model.sales.activity.ScoreBuyApply;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyApplyDTO;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyApplyQryDTO;

public interface ScoreBuyApplyMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(ScoreBuyApply record);

	int insertSelective(ScoreBuyApply record);

	ScoreBuyApplyDTO selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ScoreBuyApply record);

	int updateByPrimaryKey(ScoreBuyApply record);

	List<ScoreBuyApplyDTO> selectList(ScoreBuyApplyQryDTO record);
	
	ScoreBuyApplyDTO selectGoodsScoreActivity(Integer goodsId);
}