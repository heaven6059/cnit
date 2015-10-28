package com.cnit.yoyo.dao.car;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cnit.yoyo.model.car.Accessory;
import com.cnit.yoyo.model.car.AccessoryAndBindingCars;
import com.cnit.yoyo.model.car.AccessoryCarIndex;
import com.cnit.yoyo.model.car.AccessoryCatalog;
import com.cnit.yoyo.model.car.AccessoryExample;
import com.cnit.yoyo.model.car.AccessoryParamValue;
import com.cnit.yoyo.model.car.dto.AccessoryDTO;

public interface AccessoryMapper {
	int countByExample(AccessoryExample example);

	int deleteByExample(AccessoryExample example);

	int deleteByPrimaryKey(Integer accId);

	int insert(Accessory record);

	int insertSelective(Accessory record);

	List<Accessory> selectByExampleWithBLOBs(AccessoryExample example);

	List<Accessory> selectByExample(AccessoryExample example);

	Accessory selectByPrimaryKey(Integer accId);

	int updateByExampleSelective(@Param("record") Accessory record,
			@Param("example") AccessoryExample example);

	int updateByExampleWithBLOBs(@Param("record") Accessory record,
			@Param("example") AccessoryExample example);

	int updateByExample(@Param("record") Accessory record,
			@Param("example") AccessoryExample example);

	int updateByPrimaryKeySelective(Accessory record);

	int updateByPrimaryKeyWithBLOBs(Accessory record);

	int updateByPrimaryKey(Accessory record);

	List<AccessoryParamValue> selectAccessoryParamValue(Map map);

	AccessoryAndBindingCars selectAccInfoAndBindingCars(Integer accId);

	List<Accessory> selectByQueryParams(@Param("whereStmt")List<String> list,@Param("orderStmt")String order);
    
    AccessoryCatalog findAccessoryTypeInfo(Integer accId);
    
    /**
     * @Title:  selectAccessoryById  
     * @Description:  TODO(根据主键查询对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-25 上午11:05:18  
     * @version 1.0.0 
     * @param @param accId
     * @param @return
     * @return AccessoryDTO  返回类型 
     * @throws
     */
    AccessoryDTO selectAccessoryById(Integer accId);
    
    List<AccessoryCarIndex> selecBindingCarsByAccId(Integer accId);
    
}