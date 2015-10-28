/**
 * 文 件 名   :  CookieUtil.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-12 下午6:28:25
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.util.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class CookieUtil {

    /**
     * 设置cookie（接口方法）
     * 
     * @author 刘鹏
     * @param response
     * @param name
     *        cookie名字
     * @param value
     *        cookie值
     * @param maxAge
     *        cookie生命周期 以秒为单位
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge,String path) {
        Cookie cookie = new Cookie(name, value);
        if(path == null || "".equalsIgnoreCase(path)){
        	cookie.setPath("/");
        }else{
        	cookie.setPath(path);
        }
        if (maxAge >= 0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    /**
     * 根据名字获取cookie（接口方法）
     * 
     * @author 刘鹏
     * @param request
     * @param name
     *        cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面（非接口方法）
     * 
     * @author 刘鹏
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

}
