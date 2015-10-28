package com.cnit.yoyo.dao.car;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cnit.yoyo.model.car.CarSpiderCompare;

public interface CarSpiderCompareMapper {

	 int deleteByPrimaryKey(Integer id);

		int insert(CarSpiderCompare record);

		int insertSelective(CarSpiderCompare record);

		CarSpiderCompare selectByPrimaryKey(Integer id);

		int updateByPrimaryKeySelective(CarSpiderCompare record);

		int updateByPrimaryKey(CarSpiderCompare record);
		
		int batchDeleteByParams(List<CarSpiderCompare> list);
		
		int batchInsertByParams(List<CarSpiderCompare> list);

		List<CarSpiderCompare> findList(@Param("spiderValId")String spiderValId, @Param("compareType")String compareType,
				@Param("orderStmt")String orderBy);
}