package com.cnit.yoyo.dao.painting;

import java.util.List;
import java.util.Map;

import com.cnit.yoyo.model.painting.PaintingOrders;
import com.cnit.yoyo.model.painting.dto.PaintingOrderDTO;

public interface PaintingOrdersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_painting_orders
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_painting_orders
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    int insert(PaintingOrders record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_painting_orders
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    int insertSelective(PaintingOrders record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_painting_orders
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    PaintingOrders selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_painting_orders
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    int updateByPrimaryKeySelective(PaintingOrders record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_painting_orders
     *
     * @mbggenerated Thu Jun 04 10:40:13 CST 2015
     */
    int updateByPrimaryKey(PaintingOrders record);
    
    PaintingOrders selectByDamageId(Integer damageId);
    
    List<String> selectList(PaintingOrderDTO dto);
    List<String> selectOrderIdsList(PaintingOrderDTO dto);
    
    int updateStatus(PaintingOrders record);
    
    int selectByOrderAndMem(Map<String, Integer> map);
    
    int selectPermission(Long companyid);
    
    List<String> selectStatus(List<Long> ids);
}