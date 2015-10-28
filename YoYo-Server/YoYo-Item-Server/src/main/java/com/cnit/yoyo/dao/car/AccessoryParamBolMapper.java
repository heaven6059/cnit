package com.cnit.yoyo.dao.car;

import com.cnit.yoyo.model.car.AccessoryParamBol;
import com.cnit.yoyo.model.car.AccessoryParamBolExample;
import com.cnit.yoyo.model.car.AccessoryParamBolKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccessoryParamBolMapper {
    int countByExample(AccessoryParamBolExample example);

    int deleteByExample(AccessoryParamBolExample example);

    int deleteByPrimaryKey(AccessoryParamBolKey key);

    int insert(AccessoryParamBol record);

    int insertSelective(AccessoryParamBol record);

    List<AccessoryParamBol> selectByExample(AccessoryParamBolExample example);

    AccessoryParamBol selectByPrimaryKey(AccessoryParamBolKey key);

    int updateByExampleSelective(@Param("record") AccessoryParamBol record, @Param("example") AccessoryParamBolExample example);

    int updateByExample(@Param("record") AccessoryParamBol record, @Param("example") AccessoryParamBolExample example);

    int updateByPrimaryKeySelective(AccessoryParamBol record);

    int updateByPrimaryKey(AccessoryParamBol record);
    
    int batchInsert(List<AccessoryParamBol> list);
}