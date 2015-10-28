package com.cnit.yoyo.dao.car;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cnit.yoyo.model.car.AccessoryParamDec;
import com.cnit.yoyo.model.car.AccessoryParamDecExample;
import com.cnit.yoyo.model.car.AccessoryParamDecKey;

public interface AccessoryParamDecMapper {
    int countByExample(AccessoryParamDecExample example);

    int deleteByExample(AccessoryParamDecExample example);

    int deleteByPrimaryKey(AccessoryParamDecKey key);

    int insert(AccessoryParamDec record);

    int insertSelective(AccessoryParamDec record);

    List<AccessoryParamDec> selectByExample(AccessoryParamDecExample example);

    AccessoryParamDec selectByPrimaryKey(AccessoryParamDecKey key);

    int updateByExampleSelective(@Param("record") AccessoryParamDec record, @Param("example") AccessoryParamDecExample example);

    int updateByExample(@Param("record") AccessoryParamDec record, @Param("example") AccessoryParamDecExample example);

    int updateByPrimaryKeySelective(AccessoryParamDec record);

    int updateByPrimaryKey(AccessoryParamDec record);
    
    int batchInsert(List<AccessoryParamDec> list);
}