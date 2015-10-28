<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  --%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提交询价或购买意向</title>
<script type="text/javascript">var _path="${path}";</script>
<script src="${path}/resources/scripts/common/yoyo.js"></script>
<%-- <link type="text/css" href="${path}/resources/styles/goods/jd/base.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/goods/jd/details.css" rel="stylesheet" /> --%>

<script src="${path}/resources/scripts/biz/goods/enquiry.js"></script>
<link href="${path}/resources/styles/goods/enquiry.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/select2/select2.min.js"></script>
<link type="text/css" href="${path}/resources/styles/select2/select2.min.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/cookie/jquery.cookie.js"></script>
<!--css文件-->
<%-- <link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/base.css"> --%>
<%-- <link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/common.css"> --%>
<%-- <link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/driveAndConsult.css"> --%>
<link type="text/css" href="${path}/resources/styles/base.css" rel="stylesheet" />

<%-- <link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/goods.css"> --%>
</head>
<body>


    

<div class="content">
  <input id="isBuyer" value="${isBuyer}" type="hidden"/>
    <div class="breadnav" id="breadnav">
        <a href="${path}">首页</a>&nbsp;&gt;
        <!-- <a href="javascript:;">宝马X5</a>&gt;&nbsp; -->
            <a href="javascript:;">询价试驾</a>
    </div>


    <div class="row publicorder">
        <!-- start公共提交订单 -->

    <!-- start询价预约 -->
        <div class="tabcont infotabcont">
            <div class="tabcont-title">
            	<input type="hidden" id="type" value=${type} />
                <ul class="title-ul">
                    <li class="current" id="tab1">
                        <a href="javascript:;">询问最低价</a>
                    </li>
                    <li class="last" id="tab2">
                        <a href="javascript:;">预约试驾</a>
                    </li>
                </ul>
            </div>
            <div class="tabcont-cont">
            
            	<!--询问最低价-->
                <div class="card current" id="tab1_select">
                    <div class="card-title">
                        <p class="title-text font-yh">有意向购买，想了解最低价？</p>
                        <p class="title-text font-yh">请填写如下信息给商家：<span class="grey-999 fn-fontsize12">(信息保密，不会对外公开)</span></p>
                    </div>
                    <div class="card-cont">
                        <table class="card-table" style="width:690px;">
                            <tbody>
                                <tr>
                                    <th><em class="red">*</em>意向车型：</th>
                                    <td>
                                        <div class="selmain">
                                            <div class="select zindex-06">
                                                
							                        <select id="slDept_1" class="select-selected" >
							                            <option value="0">选择车系</option>
							                            <c:if test="${carDeptList!=null&&fn:length(carDeptList)>=1}">
							                            	<c:forEach items="${carDeptList}" var="v" >
							                            		<option value="${v.carDeptId}" <c:if test="${v.carDeptId == car.carDeptId}">selected="selected"</c:if>>${v.carDeptName}</option>
							                            	</c:forEach>
							                            </c:if>
							                        </select>
							                   
                                                
                                                
                                                <!-- <div class="select-selected">
                                                    <span>宝马X5</span><i class="icon10 icon10-down1"></i>
                                                </div>
                                                <div class="select-option select-option-cx option-width-02">
                                                    <dl>
                                                        <dd><a href="javascript:void(0);" data-seid="0">选择车系</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="373">宝马1系</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="3230">宝马2系</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="3302">宝马2系Active Tourer</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="66">宝马3系</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="317">宝马3系(进口)</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="2963">宝马3系GT</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="2968">宝马4系</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="65">宝马5系</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="202">宝马5系(进口)</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="2847">宝马5系GT</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="270">宝马6系</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="153">宝马7系</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="2196">宝马M3</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="2726">宝马M5</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="2727">宝马M6</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="2561">宝马X1</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="271">宝马X3</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="3053">宝马X4</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="159">宝马X5</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="2728">宝马X5 M</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="587">宝马X6</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="2729">宝马X6 M</a></dd>
                                                        <dd><a href="javascript:void(0);" data-seid="161">宝马Z4</a></dd>
                                                    </dl>
                                                </div> -->
                                            </div>
                                        </div>
                                        <div class="selmain selmain-car" style="width:310px;">
                                            <div class="select zindex-06 select-disabled">
                                                <select id="slSpec_1" class="select-selected" style="width:308px;" >
						                            <option value="0">选择车型</option>
						                            <c:if test="${carList!=null&&fn:length(carList)>=1}">
						                            	<c:forEach items="${carList}" var="v" >
						                            		<option value="${v.carId}" <c:if test="${v.carId == car.carId}">selected="selected"</c:if>>${v.carName}<c:if test="${v.price!=null&&v.price!=''}">(指导价:${v.price})</c:if></option>
						                            	</c:forEach>
						                            </c:if>
						                        </select>
                                                
                                                <!-- <div class="select-selected">
                                                    <span>选择车型</span><i class="icon10 icon10-down1"></i>
                                                </div>
                                                <div class="select-option select-option-cx option-width-03" style="width:360px;">
                                                    <dl>
                                                        <dd><a href="javascript:;">选择车型</a></dd>
                                                    </dl>
                                                </div> -->
                                            </div>
                                        </div>
                                        <span class="errortip fn-hide error_spec_1">请选择您的车型</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th><em class="red">*</em>上牌城市：</th>
                                    <td>
                                        <div class="selmain">
                                            <div class="select zindex-05">
                                            	<select class="select-selected" id="AreaProvince_1">
						                        	<option value="0">选择省份</option>
						                        	<c:if test="${areaList!=null&&fn:length(areaList)>=1}">
						                            	<c:forEach items="${areaList}" var="v" >
						                            		<option value="${v.areaId}">${v.areaName}</option>
						                            	</c:forEach>
						                            </c:if>
						                        	<!-- <option value="340000">A 安徽</option>
						                        	<option value="820000">A 澳门</option>
						                        	<option value="110000">B 北京</option>
						                        	<option value="500000">C 重庆</option>
						                        	<option value="530000">Y 云南</option>
						                        	<option value="330000">Z 浙江</option> -->
						                        </select>
                                                <!-- <div class="select-selected">
                                                    <span>选择省份</span><i class="icon10 icon10-down1"></i>
                                                </div>
                                                <div class="select-option select-option-cx option-width-04">
                                                    <dl></dl>
                                                </div> -->
                                            </div>
                                        </div>
                                        <div class="selmain">
                                            <div class="select zindex-05">
                                            	<select class="select-selected" id="AreaCity_1">
					                            	<option value="0">选择城市</option>
					                            	<!-- <option value="445100">C 潮州</option>
					                            	<option value="441900">D 东莞</option>
					                            	<option value="440600">F 佛山</option>
					                            	<option value="441200">Z 肇庆</option>
					                            	<option value="440800">Z 湛江</option>
					                            	<option value="440400">Z 珠海</option> -->
					                            </select>
                                                <!-- <div class="select-selected">
                                                    <span>选择城市</span><i class="icon10 icon10-down1"></i>
                                                </div>
                                                <div class="select-option select-option-cx option-width-04">
                                                    <dl></dl>
                                                </div> -->
                                            </div>
                                        </div>
                                        <div class="selmain city-last">
                                            <div class="select zindex-05 select-disabled">
                                            	<select class="select-selected" id="AreaCounty_1">
					                            	<option value="0">选择地区(可不选)</option>
					                            	<!-- <option value="440306">B 宝安</option>
					                            	<option value="440304">F 福田</option>
					                            	<option value="440307">L 龙岗</option>
					                            	<option value="440303">L 罗湖</option>
					                            	<option value="440305">N 南山</option>
					                            	<option value="440308">Y 盐田</option> -->
					                            </select>
                                                <!-- <div class="select-selected">
                                                    <span>请选择区</span><i class="icon10 icon10-down1"></i>
                                                </div>
                                                <div class="select-option select-option-cx option-width-04">
                                                    <dl> </dl>
                                                </div> -->
                                            </div>
                                        </div>
                                        <span class="errortip fn-hide error_city_1">请选择您的城市</span>
                                    </td>
                                </tr>
                                <tr class="fn-hide">
                                    <td colspan="2">
                                        <span class="bluetip blue">因厂商地域政策，此经销商无法为您消费者报价，将由其它<span></span>经销商为您报价。</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th><em class="red">*</em>您的姓名：</th>
                                    <td>
                                        <input class="inp-text" type="text" id="name_1" maxlength="10">
                                        <label><input class="radio" name="radUserSex_1" value="1" checked="checked" type="radio">先生</label>
                                        <label><input class="radio" name="radUserSex_1" value="2" type="radio">女士</label>
                                        <span class="errortip fn-hide error_name_1">请填写您的姓名</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th><em class="red">*</em>手机号码：</th>
                                    <td>
                                        <input class="inp-text" value="" type="text" id="phone_1">
                                        <span class="prompt fn-hide">提交询价后会立即收到最低价短信</span>
                                        <span class="errortip fn-hide error_phone_1">请填写您的手机</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th>申请置换：</th>
                                    <td> <label> <input class="checkbox" id="trade" type="checkbox">用旧车置换新车</label>  </td>
    
                                </tr>
                        </tbody></table>
                        <div class="changecont fn-hide">
                            <p class="grey-999">想置换以上车型，还需填写如下信息：</p>
                            <table class="card-table" style="width:690px;">
                                <tbody><tr>
                                    <th><em class="red">*</em>您的车辆：</th>
                                    <td>
                                        <div class="selmain">
                                            <div class="select zindex-04">
                                                <!-- <div class="select-selected"><span>选择品牌</span><i class="icon10 icon10-down1"></i></div>
                                                <div class="select-option option-width-01">
                                                    <dl></dl>
                                                </div> -->
                                                <select id="sCar_Brand" class="select-selected">
				                            		<option value="0">选择品牌</option>
				                            		<c:if test="${brandList!=null&&fn:length(brandList)>=1}">
						                            	<c:forEach items="${brandList}" var="v" >
						                            		<option value="${v.brandId}">${v.brandName}</option>
						                            	</c:forEach>
						                            </c:if>
				                            	</select>
                                                
                                            </div>
                                        </div>
                                        <div class="selmain">
                                            <div class="select zindex-04">
                                                <!-- <div class="select-selected"><span>选择车系</span><i class="icon10 icon10-down1"></i></div>
                                                <div class="select-option option-width-02">
                                                    <dl></dl>
                                                </div> -->
                                                <select id="sCar_Series" class="select-selected">
				                            		<option value="0">选择车型</option>
				                            	</select>
                                            </div>
                                        </div>
                                        <div class="selmain selmain-last">
                                            <div class="select zindex-04 select-disabled">
                                                <!-- <div class="select-selected"><span>选择车型</span><i class="icon10 icon10-down1"></i></div>
                                                <div class="select-option select-option-cx option-width-03">
                                                    <dl> </dl>
                                                </div> -->
                                                <select id="sCar_Spec" class="select-selected">
				                            		<option value="0">选择车系</option>
				                            	</select>
                                                
                                            </div>
                                        </div>
                                        <span class="errortip fn-hide error_spec_scar">请选择您的车型</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th><em class="red">*</em>行驶里程：</th>
                                    <td>
                                        <input class="inp-text" type="text" id="txt_Scar_Miles">
                                        <span class="grey-999">万公里</span>
                                        <span class="errortip fn-hide error_miles">请填写行驶里程(0.01-99.99)</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th><em class="red">*</em>上牌日期：</th>
                                    <td>
                                        <div class="selmain">
                                            <div class="select zindex-01" data-toggle="select">
                                                <!-- <div class="select-selected"><span>选择年份</span><i class="icon10 icon10-down1"></i></div>
                                                <div class="select-option option-date">
                                                    <dl class="select-date"></dl>
                                                </div> -->
                                                <select class="select-selected" id="sCar_DatePic_Year">
				                            		<option value="0">选择年份</option>
				                            		<c:if test="${thisYear!=null&&thisYear!=''}">
					                            		<c:forEach begin="0" end="25" step="1"  var="v"  >
						                            		<option value="${thisYear - v}">${thisYear - v}</option>
						                            	</c:forEach>
				                            		</c:if>
				                            	</select>
                                            </div>
                                        </div>
    
                                        <div class="selmain">
                                            <div class="select zindex-01">
                                                <!-- <div class="select-selected"><span>选择月份</span><i class="icon10 icon10-down1"></i> </div>
                                                <div class="select-option  option-date">
                                                    <dl class="select-date"></dl>
                                                </div> -->
                                                <select id="sCar_DatePic_Month" class="select-selected">
				                            		<option value="0">选择月份</option>
				                            		<c:forEach begin="1" end="12" step="1"  var="v"  >
					                            		<option value="${v}">${v}</option>
					                            	</c:forEach>
				                            	</select>
                                            </div>
                                        </div>
                                        <span class="errortip fn-hide error_pic">请选择完整首次上牌日期</span>
                                    </td>
                                </tr>
                            </tbody></table>
                        </div>
    
                        <div class="dealercont"></div>
    
                        <p class="skipcont">
                            <a class="btn btn-orange btn-large font-yh" href="javascript:;" name="btnSubmit">获取最低价</a>
                            <a class="btn btn-large font-yh btn-disabled" href=" javascript:;" style="display: none;">正在提交...</a>
                            <a class="btn btn-large font-yh btn-disabled" href="javascript:;" style="display: none;">获取最低价</a>
                        </p>
                        <p class="txt-center"><label><input class="checkbox" checked="checked" type="checkbox" id="articleCB_1">我同意<a class="bluelink" target="_blank" href="javascript:;">《个人信息保护声明》</a></label></p>
                    </div>
                </div>
    			
                
                <!--想要试驾-->
                <div class="card " id="tab2_select">
                    <div class="card-title">
                        <p class="title-text font-yh">想到店试驾？</p>
                        <p class="title-text font-yh">请填写如下信息给商家：<span class="grey-999 fn-fontsize12">(信息保密，不会对外公开)</span></p>
                    </div>
                    <div class="card-cont">
                        <table class="card-table" style="width:690px;">
                            <tbody>
                                <tr>
                                    <th><em class="red">*</em>意向车型：</th>
                                    <td>
                                            <div class="selmain">
                                                <div class="select zindex-06">
                                                    <!-- <div class="select-selected">
                                                        <span>宝马X5</span><i class="icon10 icon10-down1"></i>
                                                    </div>
                                                    <div class="select-option select-option-cx option-width-02">
                                                        <dl>
                                                            <dd><a href="javascript:void(0);" data-seid="0">选择车系</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="373">宝马1系</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="3230">宝马2系</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="3302">宝马2系Active Tourer</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="66">宝马3系</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="317">宝马3系(进口)</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="2963">宝马3系GT</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="2968">宝马4系</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="65">宝马5系</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="202">宝马5系(进口)</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="2847">宝马5系GT</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="270">宝马6系</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="153">宝马7系</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="2196">宝马M3</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="2726">宝马M5</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="2727">宝马M6</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="2561">宝马X1</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="271">宝马X3</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="3053">宝马X4</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="159">宝马X5</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="2728">宝马X5 M</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="587">宝马X6</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="2729">宝马X6 M</a></dd>
                                                            <dd><a href="javascript:void(0);" data-seid="161">宝马Z4</a></dd>
                                                        </dl>
                                                    </div> -->
                                                    <select id="slDept_2" class="select-selected">
							                            <option value="">选择车系</option>
							                            <c:if test="${carDeptList!=null&&fn:length(carDeptList)>=1}">
							                            	<c:forEach items="${carDeptList}" var="v" >
							                            		<option value="${v.carDeptId}" <c:if test="${v.carDeptId == car.carDeptId}">selected="selected"</c:if>>${v.carDeptName}</option>
							                            	</c:forEach>
							                            </c:if>
							                        </select>
                                                </div>
                                            </div>
                                        <div class="selmain selmain-car" style="width:310px;">
                                            <div class="select zindex-06 select-disabled">
                                                <!-- <div class="select-selected"><span>选择车型</span><i class="icon10 icon10-down1"></i></div>
                                                <div class="select-option select-option-cx option-width-03" style="width:360px;">
                                                    <dl>
                                                        <dd><a href="javascript:;">选择车型</a></dd>
                                                    </dl>
                                                </div> -->
                                                <select id="slSpec_2" class="select-selected" style="width:308px;">
						                            <option value="0">选择车型</option>
						                            <c:if test="${carList!=null&&fn:length(carList)>=1}">
						                            	<c:forEach items="${carList}" var="v" >
						                            		<option value="${v.carId}" <c:if test="${v.carId == car.carId}">selected="selected"</c:if>>${v.carName}<c:if test="${v.price!=null&&v.price!=''}">(指导价:${v.price})</c:if></option>
						                            	</c:forEach>
						                            </c:if>
						                        </select>
                                            </div>
                                        </div>
                                        <span class="errortip fn-hide error_spec_2">请选择您的车型</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th><em class="red">*</em>您的城市：</th>
                                    <td>
                                        <div class="selmain">
                                            <div class="select zindex-05">
                                                <!-- <div class="select-selected"><span>选择省份</span><i class="icon10 icon10-down1"></i></div>
                                                <div class="select-option  select-option-cx option-width-04">
                                                    <dl></dl>
                                                </div> -->
                                                <select class="select-selected" id="AreaProvince_2">
						                        	<option value="0">选择省份</option>
						                        	<c:if test="${areaList!=null&&fn:length(areaList)>=1}">
						                            	<c:forEach items="${areaList}" var="v" >
						                            		<option value="${v.areaId}">${v.areaName}</option>
						                            	</c:forEach>
						                            </c:if>
						                        </select>
                                            </div>
                                        </div>
                                        <div class="selmain">
                                            <div class="select zindex-05">
                                                <!-- <div class="select-selected"><span>选择城市</span><i class="icon10 icon10-down1"></i></div>
                                                <div class="select-option select-option-cx option-width-04">
                                                    <dl></dl>
                                                </div> -->
                                                <select class="select-selected" id="AreaCity_2">
					                            	<option value="0">选择城市</option>
					                            </select>
                                            </div>
                                        </div>
                                        <div class="selmain city-last">
                                            <div class="select zindex-05 select-disabled">
                                                <!-- <div class="select-selected"><span>请选择区</span><i class="icon10 icon10-down1"></i></div>
                                                <div class="select-option select-option-cx option-width-04">
                                                    <dl> </dl>
                                                </div> -->
                                                <select class="select-selected" id="AreaCounty_2">
					                            	<option value="0">选择地区(可不选)</option>
					                            </select>
                                            </div>
                                        </div>
                                        <span class="errortip fn-hide error_city_2">请选择您的城市</span>
                                    </td>
                                </tr>
                                <tr class="fn-hide">
                                    <td colspan="2"><span class="bluetip blue">因厂商地域政策，此经销商无法您提供试驾，将由其它<span></span>经销商与您联系。</span></td>
                                </tr>
                                <tr>
                                    <th><em class="red">*</em>您的姓名：</th>
                                    <td>
                                        <input class="inp-text" type="text" id="name_2" maxlength="10">
                                        <label><input class="radio" name="radUserSex_2" value="1" checked="checked" type="radio">先生</label>
                                        <label><input class="radio" name="radUserSex_2" value="2" type="radio">女士</label>
                                        <span class="errortip fn-hide error_name_2">请填写您的姓名</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th><em class="red">*</em>手机号码：</th>
                                    <td>
                                        <input class="inp-text" type="text" id="phone_2">
                                        <span class="errortip fn-hide error_phone_2">请填写您的手机</span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <p class="skipcont">
                            <a class="btn btn-blue btn-large font-yh" href="javascript:;" name="btnSubmit">提交申请</a>
                            <a class="btn btn-large font-yh btn-disabled" href="javascript:;" style="display: none; ">正在提交...</a>
                            <a class="btn btn-large font-yh btn-disabled" href="javascript:;" style="display: none; ">提交申请</a>
                        </p>
                        <p class="txt-center"><label><input class="checkbox" checked="checked" type="checkbox" id="articleCB_2">我同意<a class="bluelink" target="_blank" href="javascript:;">《个人信息保护声明》</a></label></p>
                    </div>
                </div>
                
            </div>
        </div>
    <!-- end询价预约 -->
    
    
    
    
<!--额外内容        <div class="askover  fn-hide">
            <div class="askover-change">
                <div class="change-info">
                    <i class="icon icon-ok"></i>
                    <p class="name">您的申请置换信息已成功提交，我们将及时与您联系！</p>
                    <p>#家人帮家人#分享最新购车价，免费领取多种好礼&nbsp;<a href="javascript:;" class="bluelink">查看详情</a></p>
                </div>
                <div class="change-second">
                    <p id="saleScarTip">二手车之家提醒：您的旧车市场估价约为</p>
                    <p class="price"></p>
                    <p>目前有&nbsp;<span class="red">569853</span>&nbsp;人对此相关车型感兴趣</p>
                    <p>您还可以选择网站卖车，此市场估价较置换价格&nbsp;<span class="red">高出近<em class="font-bold">20%</em></span></p>
                    <p><a href="javascript:;" class="btn btn-orange btn-large" >我要卖车</a></p>
                </div>
            </div>
            <div class="dealeron"></div>
            <div class="advbox">
                <div class="advbox-secondcar">
                    <a href="javascript:;">
                        <img src="style/images/advbox-20141009.png" alt="" />
                    </a>
                </div>
            </div>
        </div>
        <div class="askover fn-hide">
            <div class="askover-cont">
                <div class="cont-card">
                    <i class="icon icon-ok"></i>
                    <p class="name"></p>
                    <p></p>
                    <p>您还可以&nbsp;<a href="javascript:" class="bluelink"></a></p>
                    <p id="commandText">#家人帮家人#分享最新购车价，免费领取多种好礼&nbsp;<a href="javascript:;" class="bluelink" target="_blank">查看详情</a></p>
                </div>
                <div class="cont-prompt"><a href="javascript:;"><img src="style/images/yuyue_img20140120.jpg" /></a></div>
                <i class="icon icon-arrow"></i>
            </div>
        </div>
        <div class="askover-notes"></div>
    
        <input type="hidden" />
        <input type="hidden" />
-->
    </div>
    
    
    
    <!--询价列表-->
    <div class="askover-notes">
    	<ul class="notes-ul">
        	<!-- <li class="bg-grey">
                <p class="red">2015-05-13&nbsp;&nbsp;&nbsp;15:42&nbsp;&nbsp;&nbsp;深圳&nbsp;&nbsp;苏先生询问最低价成功</p>            
                <p>2015-05-13&nbsp;&nbsp;&nbsp;15:42&nbsp;&nbsp;&nbsp;深圳&nbsp;&nbsp;苏先生询问最低价成功</p>            
			</li>
        	<li>
                <p>2015-05-13&nbsp;&nbsp;&nbsp;15:42&nbsp;&nbsp;&nbsp;深圳&nbsp;&nbsp;苏先生询问最低价成功</p>            
                <p>2015-05-13&nbsp;&nbsp;&nbsp;15:42&nbsp;&nbsp;&nbsp;深圳&nbsp;&nbsp;苏先生询问最低价成功</p>            
			</li>
        	<li class="bg-grey">
                <p>2015-05-13&nbsp;&nbsp;&nbsp;15:42&nbsp;&nbsp;&nbsp;深圳&nbsp;&nbsp;苏先生询问最低价成功</p>            
                <p>2015-05-13&nbsp;&nbsp;&nbsp;15:42&nbsp;&nbsp;&nbsp;深圳&nbsp;&nbsp;苏先生询问最低价成功</p>            
			</li>
        	<li>
                <p>2015-05-13&nbsp;&nbsp;&nbsp;15:42&nbsp;&nbsp;&nbsp;深圳&nbsp;&nbsp;苏先生询问最低价成功</p>            
                <p>2015-05-13&nbsp;&nbsp;&nbsp;15:42&nbsp;&nbsp;&nbsp;深圳&nbsp;&nbsp;苏先生询问最低价成功</p>            
			</li>
        	<li class="bg-grey">
                <p>2015-05-13&nbsp;&nbsp;&nbsp;15:42&nbsp;&nbsp;&nbsp;深圳&nbsp;&nbsp;苏先生询问最低价成功</p>            
                <p>2015-05-13&nbsp;&nbsp;&nbsp;15:42&nbsp;&nbsp;&nbsp;深圳&nbsp;&nbsp;苏先生询问最低价成功</p>            
			</li> -->
        </ul>
    </div>
</div>







<%--
<div class="contentxj">
<div class="tittop">

            <h1 class="titzx">
                询价试驾置换</h1>
        
</div>

<div id="divInfo" class="conxj">


            <div class="cxjl">
                <!--start询问最低价-->
                <div class="cxjlt curactive" id="divOrder">
                    <div class="cxtl">
                        <p class="cxtit cf36614">
                            询问最低价<span class="cxsp"></span></p>
                        <div class="cxtxt">
                            <span class="cf36614">想了解最低价？</span>请填写右侧信息：您提供的信息将按照信息保护的规定予以保留。</div>
                    </div>
                    <div class="cxtr">
                    </div>
                </div>
                <!--end询问最低价-->
                <!--start申请试驾-->
                <div class="cxjlt cxactive" id="divDrive">
                    <div class="cxtl">
                        <p class="cxtit c0282cd">
                            预约试驾<span class="cxbsp"></span></p>
                        <div class="cxtxt">
                            <span class="c0282cd">想到店试驾？</span>请填写右侧信息：您提供的信息将按照信息保护的规定予以保留。</div>
                    </div>
                    <div class="cxtr">
                    </div>
                </div>
                <!--end申请试驾-->
            </div>
            <div class="cxjr">
                <!--start询问最低价-->
                <ul id="Cbo_00" class="cxrul" style="display: block;">
                    <li><font id="spanshowmessage1">想申请</font> <strong>
                        宝马3系</strong> <font id="spanshowmessage2">试驾</font>？请填写如下信息给商家：（信息保密，不对外公开）</li>
                    <li><span class="w30">*</span> <span class="w60">意向车型：</span>
                    	<span>
                        <select id="slDept" class="width-01">
                            <option value="">==选择意向车型==</option>
                            <c:if test="${carDeptList!=null&&fn:length(carDeptList)>=1}">
                            	<c:forEach items="${carDeptList}" var="v" >
                            		<option value="${v.carDeptId}" <c:if test="${v.carDeptId == car.carDeptId}">selected="selected"</c:if>>${v.carDeptName}</option>
                            	</c:forEach>
                            </c:if>
                        </select></span>
                    	<span>
                        <select id="slSpec" class="width-01">
                            <option value="">==选择意向车型==</option>
                            <c:if test="${carList!=null&&fn:length(carList)>=1}">
                            	<c:forEach items="${carList}" var="v" >
                            		<option value="${v.carId}" <c:if test="${v.carId == car.carId}">selected="selected"</c:if>>${v.carName}<c:if test="${v.price!=null&&v.price!=''}">(指导价:${v.price})</c:if></option>
                            	</c:forEach>
                            </c:if>
                            <!-- <option value="20220">2015款 316i 进取型 (指导价:28.3万)</option>
                            <option value="20155">2015款 316Li 手动型 (指导价:29.68万)</option>
                            <option value="20221">2015款 316i 运动设计套装 (指导价:30.1万)</option>
                            <option value="20156">2015款 316Li 时尚型 (指导价:32.18万)</option>
                            <option value="21618">2015款 320i 超悦版时尚型 (指导价:32.8万)</option>
                            <option value="20223">2015款 320i 时尚型 (指导价:33.3万)</option> 
                            <option value="20219">2015款 335Li 风尚设计套装 (指导价:60.78万)</option>-->
                        </select></span> <span class="ml12 cfc0301" id="confimMsg4" style="display: none">请选择意向车型</span>
                    </li>
                    <li><span class="w30">*</span> <span class="w60" id="cardCity">您的城市：</span> 
                    	<span>
	                        <select class="w102" id="AreaProvince_1">
	                        	<option value="0">选择省份</option>
	                        	<c:if test="${areaList!=null&&fn:length(areaList)>=1}">
	                            	<c:forEach items="${areaList}" var="v" >
	                            		<option value="${v.areaId}">${v.areaName}</option>
	                            	</c:forEach>
	                            </c:if>
	                        	<!-- <option value="340000">A 安徽</option>
	                        	<option value="820000">A 澳门</option>
	                        	<option value="110000">B 北京</option>
	                        	<option value="500000">C 重庆</option>
	                        	<option value="530000">Y 云南</option>
	                        	<option value="330000">Z 浙江</option> -->
	                        </select>
                      	</span> 
                      	<span class="ml12">
                            <select class="w102" id="AreaCity_1">
                            	<option value="0">选择城市</option>
                            	<!-- <option value="445100">C 潮州</option>
                            	<option value="441900">D 东莞</option>
                            	<option value="440600">F 佛山</option>
                            	<option value="441200">Z 肇庆</option>
                            	<option value="440800">Z 湛江</option>
                            	<option value="440400">Z 珠海</option> -->
                            </select>
                        </span> 
                        <span class="ml12">
                            <select class="w128" id="AreaCounty_1">
                            	<option value="0">选择地区(可不选)</option>
                            	<!-- <option value="440306">B 宝安</option>
                            	<option value="440304">F 福田</option>
                            	<option value="440307">L 龙岗</option>
                            	<option value="440303">L 罗湖</option>
                            	<option value="440305">N 南山</option>
                            	<option value="440308">Y 盐田</option> -->
                            </select>
                        </span> 
                        <span class="ml12 cfc0301" style="display: none;" id="confimMsg0">请选择您的城市</span>
                     </li>
                    <li class="cxts" id="confimRow" style="display: none;">您所选择的城市没有商家能为您报价，可以选择其他城市经销商为您报价。</li>
                    <li><span class="w30">*</span> <span class="w60">您的姓名：</span> <span>
                        <input value="" class="w102" id="name" tabindex="1" maxlength="10" type="text"></span>
                        <span class="ml12">
                            <input class="radmt" id="radUserSex0" name="radUserSex" value="1" checked="checked" type="radio"><label for="radUserSex0">先生</label></span> <span class="ml12">
                                    <input class="radmt" id="radUserSex1" name="radUserSex" value="2" type="radio"><label for="radUserSex1">女士</label></span> <span class="ml12 cfc0301" id="confimMsg2" style="display: none">
                                            请填写您的姓名</span> </li>
                    <li><span class="w30">*</span> <span class="w60">手机号码：</span> <span>
                        <input value="" class="w213" id="phone" tabindex="2" maxlength="20" type="text"><input id="haslowprice" value="no" type="hidden"></span> <span class="ml12 cfc0301" id="confimMsg3" style="display: none">请填写您的手机号码</span> </li>
                    <li style="display: none;" id="trZHTitle">
                        <span class="w30">&nbsp;</span> <span class="w60">申请置换：</span>
                        <span><label><input class="checkbox" id="chkZH" type="checkbox" checked="checked">询价同时提交置换</label></span>
                    </li>
                    <li class="change" id="trZHContent" style="display:none;">
                        <p class="message">想置换以上车型，还需填写如下信息：</p>
                        <div class="listcont">
                            <span class="w30">*</span> <span class="w60">您的车辆：</span>
                            <span>
                            	<select id="sCar_Brand" class="w102">
                            		<option value="0">选择品牌</option>
                            		<c:if test="${brandList!=null&&fn:length(brandList)>=1}">
		                            	<c:forEach items="${brandList}" var="v" >
		                            		<option value="${v.brandId}">${v.brandName}</option>
		                            	</c:forEach>
		                            </c:if>
                            	</select>
                            </span>
                            <span class="ml12">
                            	<select id="sCar_Series" class="w102">
                            		<option value="0">选择车系</option>
                            	</select>
                            </span>
                            <span class="ml12">
                            	<select id="sCar_Spec" class="w141">
                            		<option value="0">选择车型</option>
                            	</select>
                            </span>
                            <span class="ml12 cfc0301" style="display: none;" id="sCar_Select_Tip">请选择您的车辆</span>
                        </div>
                        <div class="listcont">
                            <span class="w30">*</span> <span class="w60">行驶里程：</span><span><input class="w102" onkeyup="if(isNaN(this.value)) this.value=''" id="txt_Scar_Miles" value="" type="text"></span><span class="ml12">万公里</span><span class="ml12 cfc0301" style="display: none;" id="sCar_Miles_Tip">请填写行驶里程(0.01-99.99)</span>
                        </div>
                        <div class="listcont">
                            <span class="w30">*</span> <span class="w60">上牌日期：</span>
                            <span>
                            	<select class="w102" id="sCar_DatePic_Year">
                            		<option value="0">选择年份</option>
                            		<!-- <option value="2015">2015</option>
                            		<option value="2014">2014</option>
                            		<option value="2013">2013</option>
                            		<option value="2012">2012</option>
                            		<option value="2011">2011</option>
                            		<option value="2010">2010</option>
                            		<option value="2009">2009</option><option value="2008">2008</option><option value="2007">2007</option><option value="2006">2006</option>
                            		<option value="2005">2005</option><option value="2004">2004</option><option value="2003">2003</option><option value="2002">2002</option>
                            		<option value="2001">2001</option><option value="2000">2000</option><option value="1999">1999</option><option value="1998">1998</option>
                            		<option value="1997">1997</option><option value="1996">1996</option><option value="1995">1995</option><option value="1994">1994</option>
                            		<option value="1993">1993</option><option value="1992">1992</option><option value="1991">1991</option> -->
                            		<c:choose>
	                            		<c:when test="${thisYear!=null&&thisYear!=''}">
		                            		<c:forEach begin="0" end="25" step="1"  var="v"  >
			                            		<option value="${thisYear - v}">${thisYear - v}</option>
			                            	</c:forEach>
	                            		</c:when>
	                            		<c:otherwise>
		                            		<option value="2015">2015</option>
		                            		<option value="2014">2014</option>
		                            		<option value="2013">2013</option>
		                            		<option value="2012">2012</option>
		                            		<option value="2011">2011</option>
		                            		<option value="2010">2010</option>
		                            		<option value="2009">2009</option><option value="2008">2008</option><option value="2007">2007</option><option value="2006">2006</option>
		                            		<option value="2005">2005</option><option value="2004">2004</option><option value="2003">2003</option><option value="2002">2002</option>
		                            		<option value="2001">2001</option><option value="2000">2000</option><option value="1999">1999</option><option value="1998">1998</option>
		                            		<option value="1997">1997</option><option value="1996">1996</option><option value="1995">1995</option><option value="1994">1994</option>
		                            		<option value="1993">1993</option><option value="1992">1992</option><option value="1991">1991</option>
	                            		</c:otherwise>
                            		</c:choose>
                            	</select>
                            </span>
                            <span class="ml12">
                            	<select id="sCar_DatePic_Month" class="w102">
                            		<option value="0">选择月份</option>
                            		<c:forEach begin="1" end="12" step="1"  var="v"  >
	                            		<option value="${v}">${v}</option>
	                            	</c:forEach>
                            	</select>
                            </span>
                            <span class="ml12 cfc0301" id="sCar_DatePic_Tip" style="display: none;">请选择完整首次上牌日期</span>
                        </div>
                    </li>
                    <li style="display:none" class="text-con"><span class="w30">&nbsp;</span> <span class="w60">备&nbsp;&nbsp;&nbsp;&nbsp;注：</span>
                        <span>
                            <textarea class="w367 cbdbebe" id="content" tabindex="3" alt="选填，可填写您希望试驾的时间">选填，可填写您希望的优惠幅度、喜欢的颜色及预计提车时间</textarea></span>
                    </li>
                    <li class="cfc0301 mb0" id="spContent" style="display: none;"><span class="w30">&nbsp;</span>
                        <span class="w60">&nbsp;</span> <span>备注最多可输入200个字</span> </li>
                    <li style="display: none;">
                        <div style="display: none;" class="dealercont" id="recommendDealer"><div class="dealercont-title"><span class="title-name">推荐询价经销商</span><span class="message">(可多选，最少选<span class="font-12-b red">1</span>个、最多选<span class="font-12-b red">3</span>个)</span></div><div class="dealercont-cont"><table class="tablecont"><tbody><tr><td class="text-center" width="10%"><input checked="checked" name="dealerList" data-dealerid="99835" data-dealerorder="0" type="checkbox"></td><td width="90%"><p class="name"><a href="#">深圳市宝昌汽车</a></p><p><span class="grey-999">地址：</span>广东省深圳市龙华新区油松社区中裕冠大道1号</p></td></tr><tr><td class="text-center" width="10%"><input name="dealerList" data-dealerid="63715" data-dealerorder="1" type="checkbox"></td><td width="90%"><p class="name"><a href="#">深圳创丰宝宝马</a></p><p><span class="grey-999">地址：</span>广东省深圳市龙岗区中心城长兴北路6号</p></td></tr><tr><td class="text-center" width="10%"><input name="dealerList" data-dealerid="11758" data-dealerorder="2" type="checkbox"></td><td width="90%"><p class="name"><a href="#">深圳宝源行</a></p><p><span class="grey-999">地址：</span>深圳市福田区福田汽车站福安大厦南楼侧一楼、二楼</p></td></tr><tr><td colspan="2"><div class="btncont"><a href="#" class="btn-more">展开更多推荐商家<i class="icon-jt"></i></a></div></td></tr><tr style="display:none;"><td class="text-center" width="10%"><input name="dealerList" data-dealerid="1847" data-dealerorder="3" type="checkbox"></td><td width="90%"><p class="name"><a href="#">深圳宝创宝马</a></p><p><span class="grey-999">地址：</span>广东省深圳市深南中路国际文化大厦一楼B2</p></td></tr><tr style="display:none;"><td class="text-center" width="10%"><input name="dealerList" data-dealerid="79130" data-dealerorder="4" type="checkbox"></td><td width="90%"><p class="name"><a href="#">宝安宝骏汽车</a></p><p><span class="grey-999">地址：</span>深圳市宝安区福永街道新田工业厂房A栋首层101号</p></td></tr><tr style="display:none;"><td class="text-center" width="10%"><input name="dealerList" data-dealerid="11830" data-dealerorder="5" type="checkbox"></td><td width="90%"><p class="name"><a href="#">深圳市宝骏</a></p><p><span class="grey-999">地址：</span>广东省深圳市罗湖区罗沙路5071</p></td></tr></tbody></table><div class="tipcont" id="popup_info" style="display:none;"></div></div></div> 
                    </li>
                    <li class="txtcent btn-cont">
                        <input class="inpsq" id="btnSubmit" type="button">
                        <input class="inptj" id="btnDoing" style="display: none;" type="button">
                        <input class="refer-disabled" id="btnUnable" style="display: none;" type="button">
                    </li>
                    <li class="txtcent"><input checked="checked" id="chkFLSM" class="checkbox" type="checkbox"><label for="chkFLSM">我同意</label> <a href="../agreement.html" target="_blank">《个人信息保护声明》</a></li>
                </ul>
                <!--end询问最低价start申请试驾-->
                <!--end申请试驾-->
            </div>
        

</div>
</div>
 --%>
</body>
</html>