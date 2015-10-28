<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>快速发布需求</title>
<link href="${path}/resources/styles/goods/enquiry.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/select2/select2.min.css" rel="stylesheet" />
<style type="text/css">
.area{
  width: 457px;
  height: 100px;
  resize: none;
  outline: none;
  font-size: 14px;
  border: 1px solid #ccd3e4;
  padding: 0 5px;
  margin-top:1px;
}
</style>
<script type="text/javascript">
	var _path="${path}";
</script>
<script src="${path}/resources/scripts/common/yoyo.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/select2/select2.min.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/cookie/jquery.cookie.js"></script>
</head>
<body>
	<div class="content">
	    <div class="breadnav" id="breadnav">
	        <a href="${path}">首页</a>&nbsp;&gt;
	        <a href="javascript:;">快速发布需求</a>
	    </div>
	    <div class="row publicorder">
	        <div class="tabcont infotabcont">
	            <div class="tabcont-cont">
	                <div class="card current" id="tab1_select">
	                    <div class="card-title">
	                        <p class="title-text font-yh">YoYo会尽最大努力满足您的需求！</p>
	                        <p class="title-text font-yh">请填写如下信息：<span class="grey-999 fn-fontsize12">(信息保密，不会对外公开)</span></p>
	                    </div>
	                    <div class="card-cont">
	                    	<form id="requireForm">
		                        <table class="card-table" style="width:667px;">
		                            <tbody>
		                            	<tr>
		                                    <th><em class="red">*</em>类型：</th>
		                                    <td>
		                                        <div class="selmain">
		                                            <div class="select zindex-05">
		                                            	<select class="select-selected" id="requirementId" name="requirementId">
								                        	<option value="0">选择类型</option>
								                        </select>
		                                            </div>
		                                        </div>
		                                        <span class="errortip fn-hide error_type">请选择需求类型</span>
		                                    </td>
		                                </tr>
		                            	<tr>
		                                    <th><em class="red">*</em>您的姓名：</th>
		                                    <td>
		                                        <input class="inp-text" type="text" id="name" name="name">
		                                        <!-- <label><input class="radio" name="radUserSex" value="0" checked="checked" type="radio">先生</label>
		                                        <label><input class="radio" name="radUserSex" value="1" type="radio">女士</label>  -->
		                                        <span class="errortip fn-hide error_name">请填写您的姓名</span>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th><em class="red">*</em>手机号码：</th>
		                                    <td>
		                                        <input class="inp-text"  type="text" id="phone" name="phone">
		                                        <span class="errortip fn-hide error_phone">请填写正确的手机号</span>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th>邮箱：</th>
		                                    <td>
		                                        <input class="inp-text" type="text" id="email" name="email">
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th style="padding-bottom: 10px;">地址：</th>
		                                    <td>
		                                        <div class="selmain">
		                                            <div class="select zindex-05">
		                                            	<select class="select-selected" id="province">
								                        	<option value="0">选择省份</option>
								                        </select>
		                                            </div>
		                                        </div>
		                                        <div class="selmain">
		                                            <div class="select zindex-05">
		                                            	<select class="select-selected" id="city">
							                            	<option value="0">选择城市</option>
							                            </select>
		                                            </div>
		                                        </div>
		                                        <div class="selmain city-last">
		                                            <div class="select zindex-05 select-disabled">
		                                            	<select class="select-selected" id="region">
							                            	<option value="0">选择地区</option>
							                            </select>
		                                            </div>
		                                        </div>
		                                        <input id="street" class="inp-text" type="text" style="width: 457px;margin-top: 5px;margin-bottom: 5px;"/>
		                                        <!--<span class="errortip fn-hide error_site">请选择您的地址</span> -->
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th><em class="red">*</em>描述：</th>
		                                    <td>
		                                    	<textarea id="content" name="content" class="area" ></textarea>
		                                    	<p class="errortip fn-hide error_content" style="padding:0">请说明您的需求（300字以内）</p>
		                                    </td>
		                                </tr>
		                        	</tbody>
		                        </table>
	                        </form>
	                        <p class="skipcont">
	                            <a class="btn btn-orange btn-large font-yh" href="javascript:;" id="btnSubmit">发布需求</a>
	                            <a class="btn btn-large font-yh btn-disabled" href=" javascript:;" style="display: none;">正在提交...</a>
	                        </p>
	                        <p class="txt-center"><label><input class="checkbox" checked="checked" type="checkbox">我同意<a class="bluelink" target="_blank" href="javascript:;">《个人信息保护声明》</a></label></p>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<script type="text/javascript">
		accountId = "${sessionScope.accountId}";
		accountType = "${sessionScope.accountType}";
	</script>
	<script src="${path}/resources/scripts/biz/requirement/postWant.js"></script>
</body>
</html>