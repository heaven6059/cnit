package com.cnit.yoyo.point.service;
/**   
 * @Description: 积分
 * @author  余平 yuping@cnit.com 
 * @date 2015-5-5 下午2:13:50 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.point.dao.MemberPointMapper;
import com.cnit.yoyo.point.model.MemberPoint;
import com.cnit.yoyo.point.model.Point;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

@Service("pointService")
public class PointService {
	
	@Autowired
	private MemberPointMapper  memberPointMapper;
	
	public Object getPointList(Object object){
		HeadObject  head =  new  HeadObject();
		Map<String,Object> paraData = (Map<String,Object>)object;
		int pageNum = (Integer)paraData.get("pageNum");
		int pageSize = (Integer)paraData.get("pageSize");
		PageHelper.startPage(pageNum, pageSize);
		ResultPage<Point> dataList = new ResultPage<Point>(memberPointMapper.getPointList(paraData));
		if(dataList != null && dataList.getRows().size() > 0){
			head.setRetCode(ErrorCode.SUCCESS);
		}else{
			head.setRetCode(ErrorCode.FAILURE);
		}
		ResultObject  result = new ResultObject(head, dataList);
		return result;
	}
	
	/**
	 * @description <b>添加积分历史记录</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-8-21
	 * @param object
	 * @return
	 * Object
	*/
	public Object saveMemberPoint(Object object){
		HeadObject headObject=new HeadObject();
		try{
			memberPointMapper.insertSelective((MemberPoint) object);
			headObject.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(headObject);
	}

}

