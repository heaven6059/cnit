package com.cnit.yoyo.system.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.util.CommonUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 自定义用户拦截器，主要是为了用户注销的时候把Portal门户上面的session清空
* @author ssd
* @date 2015-4-30 下午3:49:09
 */
public class LoginOutFilter extends PathMatchingFilter {

    /**
     * 把Portal门户上面的session清空
     */
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
    	HttpServletRequest req = (HttpServletRequest)request;
    	HttpSession session=req.getSession();
    	
    	/*ServletContext context = req.getSession().getServletContext();
		ServletContext contextPortal= context.getContext("/YoYoPortal");
		session = (HttpSession) contextPortal.getAttribute("session");
    	session.setAttribute("loginName", "");
    	session.setAttribute("accountId",0);
    	session.setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_OFF);
        ServletContext ContextA =session.getServletContext();
        session.setAttribute(GlobalStatic.USER_INFO,"");
        ContextA.setAttribute("session",null);*/
        return true;
    }
}
