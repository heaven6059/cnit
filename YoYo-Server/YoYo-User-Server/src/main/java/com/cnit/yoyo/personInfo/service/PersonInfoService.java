package com.cnit.yoyo.personInfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.member.MemberMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberWithBLOBs;

/**   
 * @Package com.cnit.yoyo.personInfo.service 
 * @Description: 修改个人信息服务类
 * @author  余平 yuping@chinacnit.com 
 * @date 2015-4-27 下午2:01:56 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
@Service("personInfoService")
public class PersonInfoService {

	@Autowired
	private MemberMapper memberMapper;
	
	/**
	 * 
	 * @Description: 获取个人信息
	 * @param paraData
	 * @return
	 */
	public Object getPersonInfo(Object  paraData){
		HeadObject head = new HeadObject();
		MemberWithBLOBs memberDTO = null;
		try{
			head.setRetCode(ErrorCode.SUCCESS);
			memberDTO = memberMapper.selectByPrimaryKey((Integer)paraData);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return  new ResultObject(head, memberDTO);
	}
	
	/**
	 * 
	 * @Description: 修改个人信息
	 * @param paraData
	 * @return
	 */
	public  Object updatePersonInfo(Object  paraData){
		HeadObject head =  new  HeadObject();
		MemberWithBLOBs record = (MemberWithBLOBs) paraData;
		int result = memberMapper.updateByPrimaryKeySelective(record);
		if(result > 0){
			head.setRetCode(ErrorCode.SUCCESS);
		}else{
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
}

