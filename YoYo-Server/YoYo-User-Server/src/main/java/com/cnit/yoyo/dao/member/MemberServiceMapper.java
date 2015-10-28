/**
 * 文 件 名   :  MemberServiceMapper.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-27 下午4:41:56
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.dao.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cnit.yoyo.domain.member.MemberQueryDo;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.dto.MemberDTO;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public interface MemberServiceMapper {

	List<MemberListDo> findMembersInfoByParam(MemberQueryDo qureyDo);

	List<MemberListDo> findMemberByParam(MemberDTO member);

	List<MemberListDo> selectByQueryParams(@Param("whereStmt")List<String> mqls, @Param("orderStmt")String orderByCause);
	
	List<MemberListDo> checkMembersInfoByParam(MemberQueryDo qureyDo);
	
	MemberListDo selectByPrimaryKey(Integer memberId);
}
