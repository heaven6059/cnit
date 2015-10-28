package com.cnit.yoyo.system.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

public class AuthFilter implements Filter{
	
	private String redirectUrl;
	private List<String> exceptions = new ArrayList<String>();//过滤器

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		redirectUrl = filterConfig.getInitParameter("redirectUrl");
		String[] eps = filterConfig.getInitParameter("exceptions").split(",");
		for (String ep : eps) {
			exceptions.add(ep);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;  
        HttpServletResponse resp = (HttpServletResponse) response;  
        HttpSession session = req.getSession();  
        // 如果session不为空，则可以浏览其他页面  
        String url = req.getServletPath();  
        //System.out.println(url);  
        //这里判断目录，后缀名，当然也可以写在web.xml中，用url-pattern进行拦截映射  
        if (!exceptions.contains(url)) {  
            //System.out.println(req.getServletPath()); 
        	//String accountInfo = (String) CommonUtil.getSession(req).getAttribute(GlobalStatic.USER_INFO);
        	String loginName = (String) session.getAttribute("loginName");
            if (StringUtil.isEmpty(loginName)) {  
            	//req.getRequestDispatcher("/WEB-INF/pages/shopMng/logout.jsp").forward(req, resp);
            	//return;
            	//String redirect = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort();
            	String str = req.getContextPath();
            	resp.sendRedirect(str+redirectUrl); 
            	return;
            	//chain.doFilter(request, response);
            } else {  
                chain.doFilter(request, response);  
            }  
        } else {  
            chain.doFilter(request, response);  
        } 
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
