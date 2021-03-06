package com.cnit.yoyo.dao.car;

import java.util.List;
import java.util.Map;

import com.cnit.yoyo.model.car.CarBrandGrade;

public interface CarBrandGradeMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_brand_grade
	 * @mbggenerated  Tue Mar 31 11:00:55 CST 2015
	 */
	int deleteByPrimaryKey(Integer gradeid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_brand_grade
	 * @mbggenerated  Tue Mar 31 11:00:55 CST 2015
	 */
	int insert(CarBrandGrade record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_brand_grade
	 * @mbggenerated  Tue Mar 31 11:00:55 CST 2015
	 */
	int insertSelective(CarBrandGrade record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_brand_grade
	 * @mbggenerated  Tue Mar 31 11:00:55 CST 2015
	 */
	CarBrandGrade selectByPrimaryKey(Integer gradeid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_brand_grade
	 * @mbggenerated  Tue Mar 31 11:00:55 CST 2015
	 */
	int updateByPrimaryKeySelective(CarBrandGrade record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_car_brand_grade
	 * @mbggenerated  Tue Mar 31 11:00:55 CST 2015
	 */
	int updateByPrimaryKey(CarBrandGrade record);
	
	List<CarBrandGrade> findList();

	int findByName(CarBrandGrade carBrandGrade);
	
	int updateStatusByIds(Map<String, Object> params);
}