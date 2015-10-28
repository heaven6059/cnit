package com.cnit.yoyo.spider.common.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cnit.yoyo.spider.common.base.vo.Page;
import com.cnit.yoyo.spider.common.base.vo.Response;


public abstract class AbstractBaseController
{
    private static Logger logger = LogManager.getLogger(AbstractBaseController.class.getName());

    protected abstract Long getCurPartnerId();

    protected abstract Long getCurAccountId();

    protected HttpServletRequest getRequest()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return request;
    }

    protected void writeAjaxResult(HttpServletResponse response, String s)
    {
        try
        {
            response.setContentType("text/html;charset=UTF-8");
            response.getOutputStream().write(s.getBytes("utf-8"));
        }
        catch (Exception e)
        {
            logger.error("writeAjaxResult error", e);
        }
    }

    protected String getIpAddr()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        return ip;
    }

    protected Response<Object> buildResponse()
    {
        return this.buildResponse(null, null);
    }

    protected Response<Object> buildResponse(Object data)
    {
        return this.buildResponse(data, null);
    }

    protected Response<Object> buildResponse(Object data, Page page)
    {
        Response<Object> response = new Response<Object>();
        if (data != null)
        {
            response.setData(data);
        }
        if (page != null)
        {
            response.setPage(page);
        }
        return response;
    }
}
