package com.cnit.yoyo.dao.member;

import java.util.List;

import com.cnit.yoyo.model.member.StoreViewHistory;
import com.cnit.yoyo.model.member.dto.StoreViewHistoryDTO;
import com.cnit.yoyo.model.member.dto.StoreViewHistoryQryDTO;

public interface StoreViewHistoryMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(StoreViewHistory record);

	int insertSelective(StoreViewHistory record);

	StoreViewHistory selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(StoreViewHistory record);

	int updateByPrimaryKey(StoreViewHistory record);
	
	/**
	  * @description <b>查询店铺浏览</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-18
	  * @param @return
	  * @return List<StoreViewHistoryDTO>
	*/
	List<StoreViewHistoryDTO> selectStoreViewHistory(StoreViewHistoryQryDTO dto);
	
	/**
	  * @description <b>删除店铺浏览</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-18
	  * @param @return
	  * @return List<StoreViewHistoryDTO>
	*/
	int deleteStoreViewHistory(Integer [] ids);
	
	int insertStoreViewHistory(StoreViewHistoryDTO storeViewHistoryDTO);
	
	List<StoreViewHistory> selectStoreViewHistoryByIds(StoreViewHistoryQryDTO qryDTO);
	
	int deleteStoreViewHistoryByMember(Long memberId);
}