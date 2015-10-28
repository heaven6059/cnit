package com.cnit.yoyo.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 敏感词过滤器
 * @author wanghb
 * @date 2015-08-28
 * @version 1.1.0
 */
public class WordValidator implements Filter {

	public static final Logger logger = LoggerFactory.getLogger(WordValidator.class);

	private WordValidatorUtil wordValidatorUtil = new WordValidatorUtil();

	@Override
	public void destroy() {
		wordValidatorUtil = null;
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String requestType = request.getHeader("X-Requested-With");
		boolean async = false;
		if (StringUtils.isNotBlank(requestType) && requestType.equals("XMLHttpRequest")) {
			logger.debug("异步请求");
			async = true;
		}
		Map<String, String[]> params = request.getParameterMap();
		if (null != params && params.size() > 0) {
			for (String key : params.keySet()) {
				String[] values = params.get(key);
				for (int i = 0; i < values.length; i++) {
					String value = values[i];
					if (value != null) {
						Boolean bool = wordValidatorUtil.isContaintSensitiveWord(value, 1);
						if (bool == true) {
							logger.info("请求的数据包含敏感词！");
							response.setCharacterEncoding("GBK");
							if (!async) {
								PrintWriter out = response.getWriter();
								out.print("<script>");
								out.print("alert('您输入的内容有敏感词!');history.back();");
								out.print("</script>");
								out.close();
								return;
							}
						}
					}
				}
			}
		}
		chain.doFilter(arg0, arg1);
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
