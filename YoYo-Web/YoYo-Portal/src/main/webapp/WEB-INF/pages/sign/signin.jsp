<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.cnit.yoyo.util.Configuration" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
    String path = request.getContextPath();
	request.setAttribute("path", path);
	StringBuffer url = request.getRequestURL();  
	String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
	application.setAttribute("versionVal", Configuration.getInstance().getConfigValue("version"));
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>登录</title>
<link type="text/css" href="${path}/resources/styles/common.css?v=${versionVal}" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/base.css?v=${versionVal}" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/login.css?v=${versionVal}" rel="stylesheet" />
<script type="text/javascript">
	var _path = "${path}";
	_url ="<%=tempContextUrl%>";
</script>
<!--placeholder ie兼容 -->

</head>
<body onkeydown="bindEnter(event)">
	<div class="hide">
		<input id="ReturnURL" type="hidden" type="text" value=<c:out value="${requestScope.ReturnURL}"/> />
	</div>
	
	<!--header-->
	<div class="h_top">
		<div class="h_top_in">
	    	<div class="h_top_l fl">
	        	<h1 class="fl"><a href="${portalPath }">yoyo商城</a></h1>
	            <div class="login_txt fl">欢迎登录</div>
	        </div>
	        <div class="h_top_r fr"></div>
	    </div>
	</div>
	
	<div class="main">
		<div class="main_in">
	    	<div class="login">
	        	<div class="login_t">
	            	<h2 class="fl">YOYO会员</h2>
	                <a href="${path}/register/signup" class="fr">立即注册</a>
	            </div>
	            <form id="login-form" class="form">
		            <div class="login_b">

                        <div class="user" >
		                	<span class="fl"></span><input type="text" name="loginName" id="id_loginName" value="邮箱/用户名/已验证手机"  onblur="if(this.value==''){this.value='邮箱/用户名/已验证手机';this.style.color='#999'}" onfocus="if(this.value=='邮箱/用户名/已验证手机'){this.value='';this.style.color='#000'}" style="color:#999"/>
                        </div>
                    
		            	<div class="user pws">
		                	<span class="fl"></span>
		                    <input class="fr" type="text" name="loginPassword" id="id_loginPassword" value="密码" onblur="if(this.value==''){this.value='密码';this.style.color='#999'}" onfocus="if(this.value=='密码'){this.value='';this.style.color='#000'}" style="color:#999" />

		                </div>
		            	<div class="code">
		                	<div class="code_ipt fl"><input type="text" name="imgValidation" id="id_imgValidation" value="验证码" onblur="if(this.value==''){this.value='验证码';this.style.color='#999'}" onfocus="if(this.value=='验证码'){this.value='';this.style.color='#000'}" style="color:#999"/></div>
                            
		                    <div class="code_bg fl">
		                    	<img id="js-mail_vcode_img" style="height: 40px; width: 60px;" onclick="javascript:refresh(this);" src="${path}/verifyImage?width=60&height=30" alt="code" />
							</div>
		                    <span class="fr" ><a href="#" onclick="javascript:refreshImage();">看不清楚，换一张</a></span>
		                </div>
		                <div class="find">
		                    <label><input class="fl" type="checkbox" name="autoLogin" id="id_autoLogin" value="false" /> 记住密码</label>
		                    <a class="fr" href="${path}/password/changePwd">忘记密码？</a>
		                </div>
		                <a href="#" class="sub" id="subBtn" >登 录</a>
		            </div>
		            
		            <div class="hide">
		            	<input type="hidden" name="accountType" id="id_accountType" value="100" />
						<input type="hidden" name="loginNameType" id="id_loginNameType" class="text" />
					</div>
	            </form>
	            <div class="login_f">
	            	<p>使用第三方授权帐号登录优优：</p>
	                <ul class="clearfix">
	                	<li><a href="javascript:getQQAuthorizeURL('QQ');">QQ</a></li>
	                    <li class="login_li02"><a href="javascript:getAuthorizeURL('WX');">微信</a></li>
	                    <li class="login_li03"><a class="bor0" href="javascript:getAuthorizeURL('SN');">微博</a></li>
	                </ul>
	            </div>
	        </div>
	    </div>
	</div>
	<!--footer-->
	<div class="footer bor0">
		<div class="footer_b">
	    	<ul class="clearfix">
	        	<li><a href="javascript:;">关于我们</a></li>
	        	<li><a href="javascript:;">联系我们</a></li>
	        	<li><a href="javascript:;">商家入驻</a></li>
	        	<li><a href="javascript:;">营销中心</a></li>
	        	<li><a href="javascript:;">手机YOYO</a></li>
	        	<li><a href="javascript:;">友情链接</a></li>
	        	<li><a href="javascript:;">销售联盟</a></li>
	        	<li><a href="javascript:;">会员俱乐部</a></li>
	        	<li class="br0"><a href="javascript:;">企业频道</a></li>
	        </ul>
	   	  <p>CopyRight © 2015 ,All Rights Reserved. 版权所有 天津迪族信息技术有限公司   津B2-20070108-4号</p>
	    </div>
	</div>
	<script type="text/javascript">
		function bindEnter(obj) {
			if (obj.keyCode == 13) {
				ajaxButtonClicked();
			}
		}
	</script>
	<script type="text/javascript" src="${path}/resources/scripts/jquery/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/base64/jquery.base64.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/select2/select2.min.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
	<script type="text/javascript" src="${path}/resources/scripts/cookie/jquery.cookie.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/layer1.9.3/layer.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/validity/jquery.validity.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/biz/common.js?v=${versionVal}"></script>
	<script type="text/javascript" src="${path}/resources/scripts/biz/signin.js?v=${versionVal}"></script>
    <script type="text/javascript">
		
    </script>
</body>
</html>