package com.cnit.yoyo.commons.page;

import javax.servlet.http.HttpServletRequest;



/**
 * 获取分页器对象
 * @author wanghb
 * @date 2015-04-22
 * @version 1.0.0
 */
public class PageUtil {
	  public static Page getPaginator(HttpServletRequest request)
	    {
	        Page paginator = new Page();
	        String pageSize = getParameterWithDefault(request, "pageSize", "20");   //每页十条
	        paginator.setPageSize(Integer.parseInt(pageSize));
	        String currentPage = getParameterWithDefault(request, "currentPage", "1");
	        paginator.setCurrentPage(Integer.parseInt(currentPage));
	        return paginator;
	    }

	    public static String getParameterWithDefault(HttpServletRequest request, String name, String defaultValue)
	    {
	        String paramValue = request.getParameter(name);
	        return paramValue != null ? paramValue.trim() : defaultValue;
	    }

}
