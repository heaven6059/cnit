package com.cnit.yoyo.coupon.dao;

import java.util.List;

import com.cnit.yoyo.coupon.model.MemberCoupon;
import com.cnit.yoyo.coupon.model.dto.MemberCouponDTO;
import com.cnit.yoyo.coupon.model.dto.MemberCouponQryDTO;

public interface MemberCouponMapper {
    int deleteByPrimaryKey(Long key);

    int insert(MemberCoupon record);

    int insertSelective(MemberCoupon record);

    MemberCoupon selectByPrimaryKey(Long key);

    int updateByPrimaryKeySelective(MemberCoupon record);

    int updateByPrimaryKey(MemberCoupon record);
    
    List<MemberCouponDTO> selectMemberStoreCoupons(MemberCouponQryDTO couponQryDTO);
    
    MemberCouponDTO selectMemberCouponsByOrder(MemberCouponQryDTO couponQryDTO);
}