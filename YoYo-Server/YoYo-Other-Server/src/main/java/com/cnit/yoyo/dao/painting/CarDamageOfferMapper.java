package com.cnit.yoyo.dao.painting;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cnit.yoyo.model.painting.CarDamageOffer;
import com.cnit.yoyo.model.painting.dto.CarDamageQueryDTO;

public interface CarDamageOfferMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car_damage_offer
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car_damage_offer
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    int insert(CarDamageOffer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car_damage_offer
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    int insertSelective(CarDamageOffer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car_damage_offer
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    CarDamageOffer selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car_damage_offer
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    int updateByPrimaryKeySelective(CarDamageOffer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car_damage_offer
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    int updateByPrimaryKey(CarDamageOffer record);
    
    List<Integer> selectByCompanyId(Integer companyId);
    
    int selectCount(Integer cardamageID); 
    
    CarDamageOffer selectOffer(@Param("cardamageID") Integer cardamageID,@Param("companyId")Integer companyId);
    
    List<CarDamageOffer> selectOffers(CarDamageQueryDTO dto);
}