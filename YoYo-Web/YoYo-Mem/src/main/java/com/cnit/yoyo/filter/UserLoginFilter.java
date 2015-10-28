/**
 * 
 */
package com.cnit.yoyo.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * @author Administrator
 * 
 */
public class UserLoginFilter implements Filter {
	private String rootPath;//WEB根目录
	private String loginPath;//登录
	private List<String> exceptions = new ArrayList<String>();//过滤器
	private RedisClientUtil redisService;//redis缓存服务
	private WebApplicationContext applicationContext;//spring容器
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (!(servletRequest instanceof HttpServletRequest)
				|| !(servletResponse instanceof HttpServletResponse)) {
			throw new ServletException(
					"OncePerRequestFilter just supports HTTP requests");
		}
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		HttpSession session = httpRequest.getSession(true);
		
		String requestType = httpRequest.getHeader("X-Requested-With"); //过滤Ajax请求
/*		if(StringUtil.isEmpty(requestType)){
			String uri = httpRequest.getRequestURI().replace(rootPath, "");
			int lastIndexOf = uri.lastIndexOf(".");
			String subfix = null;
			if (lastIndexOf != -1) {
				subfix = uri.substring(lastIndexOf);
			}
			if (!exceptions.contains(uri) && StringUtil.isEmpty(subfix)) {
				String userString = redisService.get(session.getId());//验证登录
				if (StringUtil.isEmpty(userString)) {
					httpResponse.sendRedirect(rootPath+loginPath);
					return;
				} 
			} 
		}*/
		filterChain.doFilter(servletRequest, servletResponse);
		return;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext servletContext = filterConfig.getServletContext();
		applicationContext = (WebApplicationContext) servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		redisService = (RedisClientUtil) applicationContext.getBean("redisService");
		rootPath = servletContext.getContextPath();
		loginPath = filterConfig.getInitParameter("loginPath");
		String[] eps = filterConfig.getInitParameter("exceptions").split(",");
		for (String ep : eps) {
			exceptions.add(ep);
		}
	}
}
