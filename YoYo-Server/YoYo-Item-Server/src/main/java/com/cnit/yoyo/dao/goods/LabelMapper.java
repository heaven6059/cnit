package com.cnit.yoyo.dao.goods;

import com.cnit.yoyo.model.goods.Label;
import java.util.List;

public interface LabelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_label
     *
     * @mbggenerated Mon Mar 30 11:06:41 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_label
     *
     * @mbggenerated Mon Mar 30 11:06:41 CST 2015
     */
    int insert(Label record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_label
     *
     * @mbggenerated Mon Mar 30 11:06:41 CST 2015
     */
    int insertSelective(Label record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_label
     *
     * @mbggenerated Mon Mar 30 11:06:41 CST 2015
     */
    Label selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_label
     *
     * @mbggenerated Mon Mar 30 11:06:41 CST 2015
     */
    int updateByPrimaryKeySelective(Label record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_label
     *
     * @mbggenerated Mon Mar 30 11:06:41 CST 2015
     */
    int updateByPrimaryKey(Label record);
    
    List<Label> findLabelsByCamId();
    
    int deleteByPrimaryKeys(Long[] id);
    List<Label> selectByPrimaryKeys(String[] id);
    
    
    int findBrandByName(Label label);
    
}