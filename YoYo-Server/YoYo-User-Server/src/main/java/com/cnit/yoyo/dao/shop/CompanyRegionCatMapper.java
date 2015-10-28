package com.cnit.yoyo.dao.shop;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cnit.yoyo.model.shop.CompanyRegionCat;
import com.cnit.yoyo.model.shop.dto.CompanyRegionCatDTO;

public interface CompanyRegionCatMapper {
    int deleteByPrimaryKey(Integer regionId);

    int insert(CompanyRegionCat record);

    int insertSelective(CompanyRegionCat record);

    CompanyRegionCat selectByPrimaryKey(Integer regionId);

    int updateByPrimaryKeySelective(CompanyRegionCat record);

    int updateByPrimaryKey(CompanyRegionCat record);
    
    List<CompanyRegionCatDTO> getRegionByPid(@Param("pid")Integer pid);
    
    int findByRegionName(CompanyRegionCat record);
    //通过父级id修改父级子元素个数
    int updateParentById(CompanyRegionCatDTO record); 
    int deleteById(Integer id);
}