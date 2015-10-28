package com.cnit.yoyo.dao.car;

import java.util.List;

import com.cnit.yoyo.model.car.CarFactoryScope;
import com.cnit.yoyo.model.car.dto.CarFactoryScopeQryDTO;

public interface CarFactoryScopeMapper {
    int deleteByPrimaryKey(Integer scopeId);

    int insert(CarFactoryScope record);

    int insertSelective(CarFactoryScope record);

    CarFactoryScope selectByPrimaryKey(Integer scopeId);

    int updateByPrimaryKeySelective(CarFactoryScope record);

    int updateByPrimaryKey(CarFactoryScope record);
    
	//查询所有的厂商区域
	List<CarFactoryScope> findList(CarFactoryScopeQryDTO dto);
	//查找同名
	int findByName(CarFactoryScope record);
	//插入
	int insertScope(CarFactoryScope record);
	//逻辑删除
	int deleteById(Integer [] id);
}