package com.cnit.yoyo.dao.car;

import com.cnit.yoyo.model.car.BrandAutohome;

public interface BrandAutohomeMapper {
    int deleteByPrimaryKey(Integer brandId);

    int insert(BrandAutohome record);

    int insertSelective(BrandAutohome record);

    BrandAutohome selectByPrimaryKey(Integer brandId);
    
    BrandAutohome selectByAutohomeId(String autohomeId);

    int updateByPrimaryKeySelective(BrandAutohome record);

    int updateByPrimaryKey(BrandAutohome record);

	int findBrandId(String pId);
}