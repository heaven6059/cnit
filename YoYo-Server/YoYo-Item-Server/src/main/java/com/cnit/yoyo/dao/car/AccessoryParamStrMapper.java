package com.cnit.yoyo.dao.car;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cnit.yoyo.model.car.AccessoryParamStr;
import com.cnit.yoyo.model.car.AccessoryParamStrExample;
import com.cnit.yoyo.model.car.AccessoryParamStrKey;

public interface AccessoryParamStrMapper {
    int countByExample(AccessoryParamStrExample example);

    int deleteByExample(AccessoryParamStrExample example);

    int deleteByPrimaryKey(AccessoryParamStrKey key);

    int insert(AccessoryParamStr record);

    int insertSelective(AccessoryParamStr record);

    List<AccessoryParamStr> selectByExample(AccessoryParamStrExample example);

    AccessoryParamStr selectByPrimaryKey(AccessoryParamStrKey key);

    int updateByExampleSelective(@Param("record") AccessoryParamStr record, @Param("example") AccessoryParamStrExample example);

    int updateByExample(@Param("record") AccessoryParamStr record, @Param("example") AccessoryParamStrExample example);

    int updateByPrimaryKeySelective(AccessoryParamStr record);

    int updateByPrimaryKey(AccessoryParamStr record);
    
    int batchInsert(List<AccessoryParamStr> list);
}