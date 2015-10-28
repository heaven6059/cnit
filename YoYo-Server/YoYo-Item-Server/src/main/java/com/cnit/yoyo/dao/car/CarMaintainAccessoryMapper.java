package com.cnit.yoyo.dao.car;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cnit.yoyo.model.car.CarMaintainAccessory;

public interface CarMaintainAccessoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarMaintainAccessory record);

    int insertSelective(CarMaintainAccessory record);

    CarMaintainAccessory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarMaintainAccessory record);

    int updateByPrimaryKeyWithBLOBs(CarMaintainAccessory record);

    int updateByPrimaryKey(CarMaintainAccessory record);
    
    int deleteByCateId(@Param("cateId")Integer cateId);
    
    int batchInsert(@Param("list")List<CarMaintainAccessory> list);
    
    List<CarMaintainAccessory> findAccessoryByCatId(@Param("catId")Integer catId);
    
    int updateDisable(@Param("catId")Integer catId,@Param("list")List<Integer> accIds);
}