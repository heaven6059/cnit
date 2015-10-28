package com.cnit.yoyo.dao.car;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cnit.yoyo.model.car.AccessoryParamInt;
import com.cnit.yoyo.model.car.AccessoryParamIntExample;
import com.cnit.yoyo.model.car.AccessoryParamIntKey;

public interface AccessoryParamIntMapper {
    int countByExample(AccessoryParamIntExample example);

    int deleteByExample(AccessoryParamIntExample example);

    int deleteByPrimaryKey(AccessoryParamIntKey key);

    int insert(AccessoryParamInt record);

    int insertSelective(AccessoryParamInt record);

    List<AccessoryParamInt> selectByExample(AccessoryParamIntExample example);

    AccessoryParamInt selectByPrimaryKey(AccessoryParamIntKey key);

    int updateByExampleSelective(@Param("record") AccessoryParamInt record, @Param("example") AccessoryParamIntExample example);

    int updateByExample(@Param("record") AccessoryParamInt record, @Param("example") AccessoryParamIntExample example);

    int updateByPrimaryKeySelective(AccessoryParamInt record);

    int updateByPrimaryKey(AccessoryParamInt record);
    
    int batchInsert(List<AccessoryParamInt> list);
}