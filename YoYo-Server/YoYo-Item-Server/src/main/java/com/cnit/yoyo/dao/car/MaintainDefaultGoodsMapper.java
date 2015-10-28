package com.cnit.yoyo.dao.car;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cnit.yoyo.model.car.MaintainDefaultGoods;

public interface MaintainDefaultGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MaintainDefaultGoods record);

    int insertSelective(MaintainDefaultGoods record);

    MaintainDefaultGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaintainDefaultGoods record);

    int updateByPrimaryKey(MaintainDefaultGoods record);
    /**
     * 
     *@description 删除默认商品，通过店铺id，保养周期id,保养周期项目id
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-22
     *@param companyId
     *@param maintainItemId
     *@param accId
     *@return
     */
    int deleteByParam(@Param("companyId")Integer companyId,@Param("maintainId")Integer maintainId,@Param("accId")Integer accId );
    
    int findByAccIds(List<Integer> list);
}