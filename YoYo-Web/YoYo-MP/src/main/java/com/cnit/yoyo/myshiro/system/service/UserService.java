package com.cnit.yoyo.myshiro.system.service;


import java.util.List;
import java.util.Set;

import com.cnit.yoyo.model.member.PamAccount;

/**
 * 账号管理接口
* @author ssd
* @date 2015-4-30 下午4:07:13
 */
public interface UserService {

    /**
     * 创建用户
     * @param user
     */
    public int createUser(PamAccount user);

    /**
     * 
    *
    * @Description: 更新用户
    * @param @param user
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:07:39 
    * @return int    返回类型 
    * @throws
     */
    public int updateUser(PamAccount user);

    /**
     * 
    *
    * @Description: 删除用户
    * @param @param userId    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:08:13 
    * @return void    返回类型 
    * @throws
     */
    public void deleteUser(Long userId);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword);


    /**
     * 
    *
    * @Description:查找用户
    * @param @param userId
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:08:30 
    * @return PamAccount    返回类型 
    * @throws
     */
    PamAccount findOne(Long userId);

    /**
     * 
    *
    * @Description: 查找所有用户 
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:08:42 
    * @return List<PamAccount>    返回类型 
    * @throws
     */
    List<PamAccount> findAll();

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     * @throws Exception 
     */
    public PamAccount findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     * @throws Exception 
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     * @throws Exception 
     */
    public Set<String> findPermissions(String username);

}
