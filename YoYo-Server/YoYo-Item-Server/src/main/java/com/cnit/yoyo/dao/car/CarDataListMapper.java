package com.cnit.yoyo.dao.car;

import com.cnit.yoyo.model.car.CarDataList;

public interface CarDataListMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_data_list
	 * @mbggenerated  Tue Mar 31 16:59:55 CST 2015
	 */
	int deleteByPrimaryKey(Integer listId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_data_list
	 * @mbggenerated  Tue Mar 31 16:59:55 CST 2015
	 */
	int insert(CarDataList record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_data_list
	 * @mbggenerated  Tue Mar 31 16:59:55 CST 2015
	 */
	int insertSelective(CarDataList record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_data_list
	 * @mbggenerated  Tue Mar 31 16:59:55 CST 2015
	 */
	CarDataList selectByPrimaryKey(Integer listId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_data_list
	 * @mbggenerated  Tue Mar 31 16:59:55 CST 2015
	 */
	int updateByPrimaryKeySelective(CarDataList record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_data_list
	 * @mbggenerated  Tue Mar 31 16:59:55 CST 2015
	 */
	int updateByPrimaryKey(CarDataList record);
}