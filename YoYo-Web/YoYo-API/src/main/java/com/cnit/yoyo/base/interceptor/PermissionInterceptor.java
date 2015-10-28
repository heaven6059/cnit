package com.cnit.yoyo.base.interceptor;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cnit.yoyo.api.vo.SmsSendVO;
import com.cnit.yoyo.util.ApplicationContextUtil;
import com.cnit.yoyo.util.Base64;


/**
 * 用户认证拦截
 * 
 * @author yangyi
 * @date 2015年5月22
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    /**
     * 日志
     */
    private static Logger log = LoggerFactory.getLogger(PermissionInterceptor.class);

    /**
     * 认证接口
     */
//    @Resource
//    private IAuthenticationService authenticationService;

    /**
     * token获取的参数长度
     */
    private static final int TOKEN_PARAM_LENGTH = 4;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("image handler class: " + handler.getClass().toString());
        
//		resultJson = (String) mth.invoke(this, data);
//        WebRequestInterceptor
        String data=request.getParameter("data");
        String json = new String(Base64.decode(data),"utf-8");
        json = json.replace("&quot;", "'");
		JSONObject jsonObject = JSONObject.fromObject(json);
		Object objarr=jsonObject.get("data");
		String method=(String) jsonObject.get("method");
		request.setAttribute("mobile", "18664975981");
		Enumeration<String> keys = request.getParameterNames(); 
        while(keys.hasMoreElements()) { 
            String k = keys.nextElement(); 
            System.out.println(k + " = " + request.getParameter(k) ); 
        } 
//		response.sendRedirect(request.getContextPath() +"/oauth/sendSmsCode");  
		
//		// 从spring容器中获得服务提供者
        Object provider = ApplicationContextUtil.getBeanByName("oauthController");
        // 根据方法名及参数类型获得方法对象
//        Method methoda = provider.getClass().getMethod("sendSmsCode", Object.class);
//        // 调用方法
//        methoda.invoke(provider, request);
//        SmsSendVO sendSms,BindingResult bindingResult,HttpServletRequest request
        
        Method mth = provider.getClass().getMethod(method, String.class);
        mth.invoke(provider, data);
        return false;
//		return "/oauth/"+method;
//		response.sendRedirect("sendSmsCode");
//		request.getContextPath() + 
		/*
        handler.getClass().getAnnotations();
        HandlerMethod handlerMethod = (HandlerMethod) handler;  
        System.out.println(handlerMethod.getMethod());
        Map<String, String[]> pramMap = request.getParameterMap(); 
        
        Enumeration<String> keys = request.getParameterNames(); 
        while(keys.hasMoreElements()) { 
            String k = keys.nextElement(); 
            System.out.println(k + " = " + request.getParameter(k) ); 
        } 
        
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
            for(MethodParameter methodParameter : methodParameters){
            System.out.println(methodParameter.getParameterName());
            }
        
        
        Class handlerClass = ClassUtils.getUserClass(handler);  
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
            // 没有声明需要权限,或者声明不验证权限
            if (authPassport == null || authPassport.validate() == false) {
                return true;
            } else {
                // 获取token
                String token = request.getParameter("token");
//                String[] tokenParam = TokenUtils.getTokenParams(token);
//                if (tokenParam.length == TOKEN_PARAM_LENGTH) {
//                    String platform = tokenParam[0];
//                    String companyCode = tokenParam[1];
//                    String customerCode = tokenParam[2];
//                    String uuid = tokenParam[3];
//                    // 先从session中查询
//                    HttpSession session = request.getSession();
//                    AuthenticationInfoVo vo = (AuthenticationInfoVo) session.getAttribute(uuid);
//                    // session中没有则调用认证接口
//                    if (null == vo) {
//                        AuthenticationInfoVo authenticationInfoVo = authenticationService.verify(platform, companyCode,
//                                customerCode, uuid);
//                        // 认证通过存入session
//                        if (null != authenticationInfoVo) {
//                            session.setAttribute(uuid, authenticationInfoVo);
//                            return true;
//                        } else {
//                            throw new ImageAuthRuntimeException("1002", "用户认证信息失败");
//                        }
//                    } else {
//                        return true;
//                    }
//
//                } else {
//                    throw new ImageAuthRuntimeException("1002", "用户认证信息失败");
//                }
            }
        }
        return true;
        */
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        log.info("image postHandle !!!");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (ex != null) {
            log.error(ex.getMessage(), ex);
        }
    }
}
