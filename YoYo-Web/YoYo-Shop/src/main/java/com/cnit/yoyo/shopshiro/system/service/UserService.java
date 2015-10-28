package com.cnit.yoyo.shopshiro.system.service;


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
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword);

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
