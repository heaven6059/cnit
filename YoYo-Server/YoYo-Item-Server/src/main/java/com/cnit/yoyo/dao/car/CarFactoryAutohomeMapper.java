package com.cnit.yoyo.dao.car;

import com.cnit.yoyo.model.car.CarFactoryAutohome;

public interface CarFactoryAutohomeMapper {
    int deleteByPrimaryKey(Integer factoryId);

    int insert(CarFactoryAutohome record);

    int insertSelective(CarFactoryAutohome record);

    CarFactoryAutohome selectByPrimaryKey(Integer factoryId);

    int updateByPrimaryKeySelective(CarFactoryAutohome record);

    int updateByPrimaryKey(CarFactoryAutohome record);

	CarFactoryAutohome selectByAutohomeId(String autohomeId);
}