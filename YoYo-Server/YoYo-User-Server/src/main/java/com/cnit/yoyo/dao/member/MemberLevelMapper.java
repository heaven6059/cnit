package com.cnit.yoyo.dao.member;

import java.util.List;

import com.cnit.yoyo.model.member.MemberLevel;

public interface MemberLevelMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_member_lv
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    int deleteByPrimaryKey(Integer memberLvId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_member_lv
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    int insert(MemberLevel record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_member_lv
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    int insertSelective(MemberLevel record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_member_lv
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    MemberLevel selectByPrimaryKey(Integer memberLvId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_member_lv
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    int updateByPrimaryKeySelective(MemberLevel record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_member_lv
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    int updateByPrimaryKey(MemberLevel record);

    /**
     *@description 查询某帐号类型下的默认等级id
     *@detail <方法详细描述>
     *@author <a href="liming@cnit.com">李明</a>
     *@version 1.0.0
     *@data 2015-2-4
     *@param accountType
     *@return
     */
    MemberLevel findByDefault(String _parameter);
    
    /**
     *@description 通过会员等级实例查询
     *@detail <方法详细描述>
     *@author <a href="liming@cnit.com">李明</a>
     *@version 1.0.0
     *@data 2015-3-4
     *@param record
     *@return
     */
    List<MemberLevel> selectByExample(MemberLevel record);
    
    List<MemberLevel> findAll();
}