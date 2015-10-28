package com.cnit.yoyo.dao.car;

import com.cnit.yoyo.model.car.CarDataInt;
import com.cnit.yoyo.model.car.CarDataListValue;
import com.cnit.yoyo.model.car.CarDataListValueKey;

public interface CarDataListValueMapper {
    int deleteByPrimaryKey(CarDataListValueKey key);

    int insert(CarDataListValue record);

    int insertSelective(CarDataListValue record);

    CarDataListValue selectByPrimaryKey(CarDataListValueKey key);

    int updateByPrimaryKeySelective(CarDataListValue record);

    int updateByPrimaryKey(CarDataListValue record);
    
    int selectCountByPrimaryKey(CarDataListValue record);
}