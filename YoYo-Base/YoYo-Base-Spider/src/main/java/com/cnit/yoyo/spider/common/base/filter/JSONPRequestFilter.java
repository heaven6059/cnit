package com.cnit.yoyo.spider.common.base.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Jsonp过滤器
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
@SuppressWarnings({"unchecked", "unused"})
public class JSONPRequestFilter implements Filter {
    private String callbackParameter;

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException("This filter can " +
                    " only process HttpServletRequest requests");
        }

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (isJSONPRequest(httpRequest)) {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(httpResponse) {

                @Override
                public ServletOutputStream getOutputStream() throws IOException {
                    return new ServletOutputStream() {
                        @Override
                        public void write(int b) throws IOException {
                            byteArrayOutputStream.write(b);
                        }
                    };
                }

                @Override
                public PrintWriter getWriter() throws IOException {
                    return new PrintWriter(byteArrayOutputStream);
                }

                public String getData() {
                    return byteArrayOutputStream.toString();
                }
            };

            chain.doFilter(httpRequest, responseWrapper);
            response.getOutputStream().write((getCallbackParameter(httpRequest) + "(").getBytes());
            response.getOutputStream().write(byteArrayOutputStream.toByteArray());
            response.getOutputStream().write(");".getBytes());

            response.setContentType("text/javascript");
        } else {
            chain.doFilter(request, response);
        }
    }

    private String getCallbackMethod(HttpServletRequest httpRequest) {
        return httpRequest.getParameter(callbackParameter);
    }

    private boolean isJSONPRequest(HttpServletRequest httpRequest) {
        String callbackMethod = getCallbackMethod(httpRequest);
        return (callbackMethod != null && callbackMethod.length() > 0);
    }

    private String getCallbackParameter(HttpServletRequest request) {
        return request.getParameter(callbackParameter);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        callbackParameter = filterConfig.getInitParameter("callbackParameter");
    }

    public void destroy() {
    }
}