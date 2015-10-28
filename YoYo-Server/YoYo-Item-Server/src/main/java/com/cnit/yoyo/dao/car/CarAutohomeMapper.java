package com.cnit.yoyo.dao.car;

import com.cnit.yoyo.model.car.CarAutohome;

public interface CarAutohomeMapper {
    int deleteByPrimaryKey(Integer carId);

    int insert(CarAutohome record);

    int insertSelective(CarAutohome record);

    CarAutohome selectByPrimaryKey(Integer carId);

    int updateByPrimaryKeySelective(CarAutohome record);

    int updateByPrimaryKey(CarAutohome record);

	int selectByAutohomeId(String autohomeId);
}