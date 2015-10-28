package com.cnit.yoyo.membermsg.dao;

/**   
 * @Description: 买家中心 站内信
 * @author  余平 yuping@cnit.com 
 * @date 2015-5-5 下午2:13:50 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.util.List;
import java.util.Map;

import com.cnit.yoyo.membermsg.model.MemberMsg;

public interface MemberMsgMapper {

	/**
	 * 
	 * @Description:获取会员站内消息列表
	 * @param paraData
	 * @return
	 */
	public List<MemberMsg> getMemberMsgList(Map<String, Object> paraData);

	/**
	 * 
	 * @Description:获取 站内消息详细信息
	 * @param map
	 * @return
	 */
	public MemberMsg getMemberMsgDetailById(Map<String, Object> map);

	/**
	 * 
	 * @Description: 改变站内信 读的状态
	 * @param map
	 * @return
	 */
	public int updateMemberMsgReadStatus(Map<String, Object> map);

	void insert(MemberMsg memberMsg);

}