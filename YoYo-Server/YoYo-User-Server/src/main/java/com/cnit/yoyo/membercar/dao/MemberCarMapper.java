package com.cnit.yoyo.membercar.dao;

import java.util.List;

import com.cnit.yoyo.membercar.model.MemberCar;
import com.cnit.yoyo.membercar.model.MemberCarDTO;
import com.cnit.yoyo.membercar.model.MemberCarQryDTO;

public interface MemberCarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberCar record);

    int insertSelective(MemberCar record);

    MemberCar selectByPrimaryKey(Integer id);
    
    int updateDefaultCarStatus(MemberCar memberCar);

    int updateByPrimaryKeySelective(MemberCar record);

    int updateByPrimaryKey(MemberCar record);
    
    List<MemberCar> selectMemberCarList(MemberCarQryDTO qryDTO);
    
    MemberCarDTO selectMemberCar(MemberCarQryDTO qryDTO);
    
    int deleteMemberCar(MemberCarQryDTO qryDTO);
    
    MemberCarDTO selectMemberDefaultCar(MemberCarQryDTO memberCar);
    
    int selectMemberHasAddCar(MemberCarQryDTO qryDTO);
    
    int selectMemberCarCount(MemberCar memberCar);
    
    int updateLastCarToDefault(MemberCarQryDTO car);
}