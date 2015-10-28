package com.cnit.yoyo.shopshiro;

import org.apache.shiro.web.filter.PathMatchingFilter;

import com.cnit.yoyo.constant.GlobalStatic;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 自定义用户拦截器
* @author ssd
* @date 2015-4-30 下午3:49:09
 */
public class LoginOutFilter extends PathMatchingFilter {

    /**
     * 将用户信息放入request中
     */
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
    	HttpServletRequest req = (HttpServletRequest)request;
    	HttpSession session=req.getSession();
    	
    	/*redisService.del(request.getSession().getId());
		request.getSession().setAttribute("loginName", "");
		request.getSession().setAttribute("accountId", 0);
		request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_OFF);
		ServletContext ContextA = request.getSession().getServletContext();
		request.getSession().setAttribute(GlobalStatic.USER_INFO, "");
		ContextA.setAttribute("session", null);*/
    	
    	/*ServletContext context = req.getSession().getServletContext();
		ServletContext contextPortal= context.getContext("/YoYoPortal");
		session = (HttpSession) contextPortal.getAttribute("session");
    	session.setAttribute("loginName", "");
    	session.setAttribute("accountId",0);
    	session.setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_OFF);
        ServletContext ContextA =session.getServletContext();
        session.setAttribute(GlobalStatic.USER_INFO,"");
        ContextA.setAttribute("session",null);*/
        //String username = (String)SecurityUtils.getSubject().getPrincipal();
        //request.setAttribute(GlobalStatic.CURRENT_USER, userService.findByUsername(username));
        return true;
    }
}
