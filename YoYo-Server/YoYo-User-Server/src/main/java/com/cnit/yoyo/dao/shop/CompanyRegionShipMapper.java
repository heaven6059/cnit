package com.cnit.yoyo.dao.shop;

import java.util.List;

import com.cnit.yoyo.model.shop.CompanyRegionShip;

public interface CompanyRegionShipMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyRegionShip record);

    int insertSelective(CompanyRegionShip record);

    CompanyRegionShip selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyRegionShip record);

    int updateByPrimaryKey(CompanyRegionShip record);
    
    int delete(Integer regionId);
    
    List<CompanyRegionShip> findRegionShip(Integer regionId);
}