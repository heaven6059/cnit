package com.cnit.yoyo.myshiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.myshiro.system.service.UserService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义用户拦截器
* @author ssd
* @date 2015-4-30 下午3:49:09
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private UserService userService;

    /**
     * 将用户信息放入request中
     */
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(GlobalStatic.CURRENT_USER, userService.findByUsername(username));
        return true;
    }
}
