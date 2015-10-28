package com.cnit.yoyo.personInfo.dao;

import com.cnit.yoyo.member.model.dto.MemberDTO;

/**   
 * @Package com.cnit.yoyo.personInfo.dao 
 * @Description:个人信息Mapper
 * @author  余平 yuping@chinacnit.com 
 * @date 2015-4-27 下午2:05:24 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
public interface PersonInfoMapper {

	/**
	 * 
	 * @param userId 
	 * @Description: 获取个人信息
	 * @return
	 */
	public MemberDTO getPersonInfo(String userId);

	/** 
	 * @Description: 修改个人信息
	 * @param memberDTO
	 * @return			
	*/
	public int updatePersonInfo(MemberDTO memberDTO);      

}

