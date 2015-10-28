package com.cnit.yoyo.dao.painting;

import java.util.List;

import com.cnit.yoyo.model.painting.CarPart;

public interface CarPartMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_part
     * @mbggenerated  Tue May 05 19:10:17 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_part
     * @mbggenerated  Tue May 05 19:10:17 CST 2015
     */
    int insert(CarPart record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_part
     * @mbggenerated  Tue May 05 19:10:17 CST 2015
     */
    int insertSelective(CarPart record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_part
     * @mbggenerated  Tue May 05 19:10:17 CST 2015
     */
    CarPart selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_part
     * @mbggenerated  Tue May 05 19:10:17 CST 2015
     */
    int updateByPrimaryKeySelective(CarPart record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_part
     * @mbggenerated  Tue May 05 19:10:17 CST 2015
     */
    int updateByPrimaryKey(CarPart record);
    
    /**
     *@description 查询所有可选汽车部件
     *@detail <方法详细描述>
     *@author <a href="zjcai@chinacnit.com">蔡志杰</a>
     *@version 1.0.0
     *@data 2015-5-6
     *@return
     */
    List<CarPart> selectAllparts();
}