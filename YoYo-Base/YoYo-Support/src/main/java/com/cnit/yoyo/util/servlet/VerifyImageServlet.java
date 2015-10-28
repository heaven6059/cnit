package com.cnit.yoyo.util.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.util.VerifyCodeUtils;

/**
 * @description 图片验证码生成
 * @detail 随机生成图片验证码 客户端可传入图片宽度与高度
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class VerifyImageServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("22222222--------"+request.getRequestURI());
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 存入会话session
        HttpSession session = request.getSession(true);
        session.setAttribute(GlobalStatic.VALIDATE_CODE, verifyCode.toLowerCase());
        String width = request.getParameter("width");// 图片宽
        String height = request.getParameter("height");// 图片高
        // 生成图片
        VerifyCodeUtils.outputImage((width == null || "".equalsIgnoreCase(width) ? 200 : Integer.parseInt(width)),
                (height == null || "".equalsIgnoreCase(height) ? 200 : Integer.parseInt(height)), response.getOutputStream(), verifyCode);
    }
}
