package com.cnit.yoyo.dao.painting;

import java.util.List;

import com.cnit.yoyo.model.painting.CarDamageDetail;

public interface CarDamageDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car_damage_detail
     *
     * @mbggenerated Mon May 11 18:58:49 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car_damage_detail
     *
     * @mbggenerated Mon May 11 18:58:49 CST 2015
     */
    int insert(CarDamageDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car_damage_detail
     *
     * @mbggenerated Mon May 11 18:58:49 CST 2015
     */
    int insertSelective(CarDamageDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car_damage_detail
     *
     * @mbggenerated Mon May 11 18:58:49 CST 2015
     */
    CarDamageDetail selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car_damage_detail
     *
     * @mbggenerated Mon May 11 18:58:49 CST 2015
     */
    int updateByPrimaryKeySelective(CarDamageDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car_damage_detail
     *
     * @mbggenerated Mon May 11 18:58:49 CST 2015
     */
    int updateByPrimaryKeyWithBLOBs(CarDamageDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car_damage_detail
     *
     * @mbggenerated Mon May 11 18:58:49 CST 2015
     */
    int updateByPrimaryKey(CarDamageDetail record);
    
    int batchInsert(List<CarDamageDetail> list);
    
    List<CarDamageDetail> selectByDamageId(int damageId);
}