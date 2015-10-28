package com.cnit.yoyo.dao.car;

import com.cnit.yoyo.model.car.CarDeptAutohome;

public interface CarDeptAutohomeMapper {
    int deleteByPrimaryKey(Integer carDeptId);

    int insert(CarDeptAutohome record);

    int insertSelective(CarDeptAutohome record);

    CarDeptAutohome selectByPrimaryKey(Integer carDeptId);

    int updateByPrimaryKeySelective(CarDeptAutohome record);

    int updateByPrimaryKey(CarDeptAutohome record);

	int selectbyAutohomeId(String autohomeId);
}