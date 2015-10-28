package com.cnit.yoyo.point.dao;

import java.util.List;
import java.util.Map;

import com.cnit.yoyo.point.model.MemberPoint;
import com.cnit.yoyo.point.model.Point;

public interface MemberPointMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberPoint record);

    int insertSelective(MemberPoint record);

    MemberPoint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberPoint record);

    int updateByPrimaryKey(MemberPoint record);
    
	/** 
	 * @Description: 获取积分历史记录 
	 * @param paraData
	 * @return			
	*/
	public List<Point> getPointList(Map<String, Object> paraData);
}