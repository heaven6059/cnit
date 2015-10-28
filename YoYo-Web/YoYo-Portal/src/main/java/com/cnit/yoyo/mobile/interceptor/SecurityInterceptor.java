package com.cnit.yoyo.mobile.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cnit.yoyo.article.controller.ArticleController;
import com.cnit.yoyo.weixin.tencent.WeixinUtil;


public class SecurityInterceptor implements HandlerInterceptor{
	private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);
		
	// 不需要拦截的资源
    private List<String> excludeUrls;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean returnFlag = true;
		//如果要访问的资源是需要验证的
		if(!isSafeUrl(request)){
			returnFlag = weixinSessionMake(request, response, handler);
		}
		return returnFlag;
	}

	private boolean weixinSessionMake(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		logger.debug("-----weixinSessionMake获取code之前的session openid为---------："+session.getAttribute(WeixinUtil.SESSION_TP_OPEN_ID));
		/**
		 * request中获取code变量，若存在code表示，新的微信用户请求
		 */
		if(session.getAttribute(WeixinUtil.SESSION_TP_OPEN_ID) == null){
			session.setAttribute(WeixinUtil.SESSION_TP_OPEN_ID, request.getParameter("openid"));//test
		}
		String session_openid = (String) session.getAttribute(WeixinUtil.SESSION_TP_OPEN_ID);
		if(null != session_openid){
			return true;
		}else{
			if(null != request.getParameter("code")){
				String code = request.getParameter("code");
				WeixinUtil.logger.debug("code不为空，在执行getAccessTokenOpenIdByCode方法前获取到的微信code值为==============："+code);
				JSONObject resultObj = WeixinUtil.getAccessTokenOpenIdByCode(code);
				WeixinUtil.logger.debug("weixinSessionMake获取code后请求微信的返回值为----："+resultObj.toString());
				/**
				 * 判断访问微信服务器是否成功 若失败返回错误报文
				 * 如：{"errcode":40029,"errmsg":"invalid code"}
				 */
				if(resultObj.get("errcode") != null){
					System.out.println(" SecurityInterceptor errcode---" + resultObj.get("errcode"));
					response.setCharacterEncoding("UTF-8");
					response.sendError(HttpStatus.BAD_GATEWAY.value(), "No effective micro user faith " + resultObj.toString());
					return false;
				}else{
					String openid = resultObj.getString("openid");
					System.out.println(" SecurityInterceptor openid------:" + openid);
					if(StringUtils.isEmpty(openid)){
						response.setCharacterEncoding("UTF-8");
						response.sendError(HttpStatus.BAD_GATEWAY.value(), "No effective micro user faith");
						return false;
					}
					session.setAttribute(WeixinUtil.SESSION_TP_OPEN_ID, openid);
					WeixinUtil.logger.debug("-----weixinSessionMake获取code后的session openid为---------："+session.getAttribute(WeixinUtil.SESSION_TP_OPEN_ID));
				}
			}
			// 判断session里是否有用户信息
			System.out.println("SecurityInterceptor session:openid-----:" + session.getAttribute(WeixinUtil.SESSION_TP_OPEN_ID));
			if(session.getAttribute(WeixinUtil.SESSION_TP_OPEN_ID) == null){
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/xml;charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.sendError(HttpStatus.BAD_GATEWAY.value(), "No effective micro user faith");
				return false;
			}else{
				return true;
			}
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	/**
	 * 判断是否要跳过的地址
	 * @param request
	 * @return
	 */
	private boolean isSafeUrl(HttpServletRequest request){
		String url = request.getServletPath();
		if(excludeUrls != null && excludeUrls.size() > 0){
			for(String safeUrl : excludeUrls){
				if(url.startsWith(safeUrl)){
					// 当前被请求的地址　包含filter里配置的安全地址,说明该地址是安全的
					return true;
				}
			}
		}
		return false;
	}
}
