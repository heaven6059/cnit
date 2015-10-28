package com.cnit.yoyo.dao.site;

import java.util.List;

import com.cnit.yoyo.model.site.Ad;

public interface AdMapper {
    int deleteByPrimaryKey(Integer adId);

    int insert(Ad record);

    int insertSelective(Ad record);

    Ad selectByPrimaryKey(Integer adId);

    int updateByPrimaryKeySelective(Ad record);

    int updateByPrimaryKeyWithBLOBs(Ad record);

    int updateByPrimaryKey(Ad record);

    List<Ad> selectByName(String name);
    
    List<Ad> selectAll();
    
    int deleteBatch(List<Integer> ids);
}