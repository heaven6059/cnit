package com.cnit.yoyo.dao.car;

import com.cnit.yoyo.model.car.CarCompareType;

public interface CarCompareTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarCompareType record);

    int insertSelective(CarCompareType record);

    CarCompareType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarCompareType record);

    int updateByPrimaryKey(CarCompareType record);
}