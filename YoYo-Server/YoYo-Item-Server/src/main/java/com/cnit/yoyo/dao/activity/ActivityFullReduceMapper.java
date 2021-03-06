package com.cnit.yoyo.dao.activity;

import java.util.List;

import com.cnit.yoyo.model.activity.ActivityFullReduce;
import com.cnit.yoyo.model.activity.dto.FullReduceDTO;

public interface ActivityFullReduceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity_full_reduce
     *
     * @mbggenerated Thu Aug 27 11:35:58 CST 2015
     */
    int deleteByPrimaryKey(Long actId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity_full_reduce
     *
     * @mbggenerated Thu Aug 27 11:35:58 CST 2015
     */
    int insert(ActivityFullReduce record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity_full_reduce
     *
     * @mbggenerated Thu Aug 27 11:35:58 CST 2015
     */
    int insertSelective(ActivityFullReduce record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity_full_reduce
     *
     * @mbggenerated Thu Aug 27 11:35:58 CST 2015
     */
    ActivityFullReduce selectByPrimaryKey(Long actId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity_full_reduce
     *
     * @mbggenerated Thu Aug 27 11:35:58 CST 2015
     */
    int updateByPrimaryKeySelective(ActivityFullReduce record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_activity_full_reduce
     *
     * @mbggenerated Thu Aug 27 11:35:58 CST 2015
     */
    int updateByPrimaryKey(ActivityFullReduce record);
    
    FullReduceDTO selectFullReduceListById(Long actId);

	List<FullReduceDTO> selectFullReduceList(FullReduceDTO dto);

	List<FullReduceDTO> selectFullDiscountList();
}