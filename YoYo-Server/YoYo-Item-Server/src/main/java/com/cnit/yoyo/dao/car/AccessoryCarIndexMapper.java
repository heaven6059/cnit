package com.cnit.yoyo.dao.car;

import com.cnit.yoyo.model.car.AccessoryCarIndex;
import com.cnit.yoyo.model.car.AccessoryCarIndexExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccessoryCarIndexMapper {
    int countByExample(AccessoryCarIndexExample example);

    int deleteByExample(AccessoryCarIndexExample example);

    int deleteByPrimaryKey(Integer relateId);

    int insert(AccessoryCarIndex record);

    int insertSelective(AccessoryCarIndex record);

    List<AccessoryCarIndex> selectByExample(AccessoryCarIndexExample example);

    AccessoryCarIndex selectByPrimaryKey(Integer relateId);

    int updateByExampleSelective(@Param("record") AccessoryCarIndex record, @Param("example") AccessoryCarIndexExample example);

    int updateByExample(@Param("record") AccessoryCarIndex record, @Param("example") AccessoryCarIndexExample example);

    int updateByPrimaryKeySelective(AccessoryCarIndex record);

    int updateByPrimaryKey(AccessoryCarIndex record);

	int batchInsert(List<AccessoryCarIndex> list);
	
	//通过车型id查找配件
    List<Integer> selectByCarId(Integer[] id);
    
    /**
     * @Title:  selectCarNameByAccId  
     * @Description:  TODO(根据配件id查询使用车型名称)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-26 下午7:30:43  
     * @version 1.0.0 
     * @param @param accId
     * @param @return
     * @return List<String>  返回类型 
     * @throws
     */
    List<String> selectCarNameByAccId(Integer accId);
}