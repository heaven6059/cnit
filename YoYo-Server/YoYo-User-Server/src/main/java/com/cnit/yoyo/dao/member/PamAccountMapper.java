package com.cnit.yoyo.dao.member;

import java.util.List;

import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.model.system.RoleUserLink;
import com.cnit.yoyo.model.system.dto.UserListDTO;

public interface PamAccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pam_account
     *
     * @mbggenerated Mon Mar 30 10:44:31 CST 2015
     */
    int deleteByPrimaryKey(Integer accountId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pam_account
     *
     * @mbggenerated Mon Mar 30 10:44:31 CST 2015
     */
    int insert(PamAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pam_account
     *
     * @mbggenerated Mon Mar 30 10:44:31 CST 2015
     */
    int insertSelective(PamAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pam_account
     *
     * @mbggenerated Mon Mar 30 10:44:31 CST 2015
     */
    PamAccount selectByPrimaryKey(Integer accountId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pam_account
     *
     * @mbggenerated Mon Mar 30 10:44:31 CST 2015
     */
    int updateByPrimaryKeySelective(PamAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pam_account
     *
     * @mbggenerated Mon Mar 30 10:44:31 CST 2015
     */
    int updateByPrimaryKey(PamAccount record);
    
    /**
     *@description 通过登录名称查询账户列表
     *@detail <方法详细描述>
     *@author <a href="liming@cnit.com">李明</a>
     *@version 1.0.0
     *@data 2015-2-3
     *@param loginName
     *@return
     */
    List<PamAccount> findByLoginName(String loginName);
    
    List<PamAccount> selectByPrimaryKeySelective(PamAccount record);
    
    List<PamAccount> selectUnionBySelective(PamAccount record);
    
    
    Long selectLoginname(String loginName);
    
    List<PamAccount> selectAllUser();
    
    List<PamAccount> selectAllShop();
    
    PamAccount selectUserRole(Integer accountId);
    
    PamAccount selectUserRoleByUsername(String username);
    
    int saveUserRoleLink(RoleUserLink rul);
    
    int deleteUserRoleLink(RoleUserLink rul);

    PamAccount findByUsername(String loginName);
    
    PamAccount findMemByUsername(String loginName);
    
    PamAccount findShopByUsername(String loginName);
    
    PamAccount findMpByUsername(String loginName);
    
    PamAccount findPortalByUsername(String loginName);

	PamAccount selectUser(int userId);
	
	List<UserListDTO> selectAllUserRole();
	
	List<UserListDTO> selectAllSiteUserRole();

	PamAccount findByUsernameOrType(PamAccount pa);
}