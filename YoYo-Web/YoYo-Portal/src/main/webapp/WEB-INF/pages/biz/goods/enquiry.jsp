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
<script src="${path}/resources/scripts/biz/goods/enquiry.js"></script>
<link href="${path}/resources/styles/goods/enquiry.css" rel="stylesheet" />
</head>
<body>
<div class="content">
  <input id="isBuyer" value="${isBuyer}" type="hidden"/>
  <input id="carId" value="${goodsCar.carId}" type="hidden"/>
  <input id="carDeptId" value="${goodsCar.carDeptId}" type="hidden"/>
  <input id="factoryId" value="${goodsCar.factoryId}" type="hidden"/>
  <input id="brandId" value="${goodsCar.brandId}" type="hidden"/>
  <input id="carYearId" value="${goodsCar.carYearId}" type="hidden"/>
  
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
                   		<form id="low_form">
                        <table class="card-table" style="width:980px;">
                            <tbody>
                                <tr>
                                    <th><em class="red">*</em>意向车型：</th>
                                    <td>
                                        <div class="selmain">
                                           	<select class="select-selected" id="low_Brand">
                                           		<option value="0" selected="selected">选择品牌</option>
                                           		<c:forEach var="brand" items="${brandList}">
                                           		<option value="${brand.brandId}">${brand.brandName}</option>
                                           		</c:forEach>
                                           	</select>
                                        </div>
                                        <div class="selmain">
                                           	<select class="select-selected" id="low_Series">
                                           		<option value="0">选择车系</option>
                                           	</select>
                                        </div>
                                        <div class="selmain">
                                           	<select class="select-selected" id="low_year">
                                           		<option value="0">选择年款</option>
                                           	</select>
                                        </div>
                                        <div class="selmain">
                                           	<select class="select-selected" id="low_cartype" name="carId">
                                           		<option value="0">选择车型</option>
                                           	</select>
                                        </div>
                                        <span class="errortip fn-hide error_spec_1">请选择您的车型</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th><em class="red">*</em>上牌城市：</th>
                                    <td>
                                        <div class="selmain">
                                            <div class="select zindex-05">
                                            	<select class="select-selected" id="AreaProvince_1" name="provinceId">
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
                                            	<select class="select-selected" id="AreaCity_1" name="cityId">
					                            	<option value="0">选择城市</option>
					                            </select>
                                            </div>
                                        </div>
                                        <div class="selmain city-last">
                                            <div class="select zindex-05 select-disabled">
                                            	<select class="select-selected" id="AreaCounty_1" name="countyId">
					                            	<option value="0">选择地区(可不选)</option>
					                            </select>
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
                                        <input class="inp-text" type="text" id="name_1" maxlength="10" name="userName">
                                        <label><input class="radio" name="userSex" value="1" checked="checked" type="radio">先生</label>
                                        <label><input class="radio" name="userSex" value="2" type="radio">女士</label>
                                        <span class="errortip fn-hide error_name_1">请填写您的姓名</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th><em class="red">*</em>手机号码：</th>
                                    <td>
                                        <input class="inp-text" value="" type="text" id="phone_1" name="phone">
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
                            <table class="card-table" style="width:980px;">
                                <tbody><tr>
                                    <th><em class="red">*</em>您的车辆：</th>
                                    <td>
                                         <div class="selmain">
                                           	<select class="select-selected" id="changecont_Brand" name="brandId">
                                           		<option value="0" selected="selected">选择品牌</option>
                                           		<c:forEach var="brand" items="${allBrandList}">
                                           		<option value="${brand.brandId}">${brand.brandName}</option>
                                           		</c:forEach>
                                           	</select>
                                        </div>
                                        <div class="selmain">
                                           	<select class="select-selected" id="changecont_Series" name="carDeptId">
                                           		<option value="0">选择车系</option>
                                           	</select>
                                        </div>
                                        <div class="selmain">
                                           	<select class="select-selected" id="changecont_year">
                                           		<option value="0">选择年款</option>
                                           	</select>
                                        </div>
                                        <div class="selmain">
                                           	<select class="select-selected" id="changecont_type" name="replaceCarId">
                                           		<option value="0">选择车型</option>
                                           	</select>
                                        </div>
                                        <span class="errortip fn-hide error_spec_scar">请选择您的车型</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th><em class="red">*</em>行驶里程：</th>
                                    <td>
                                        <input class="inp-text" type="text" id="txt_Scar_Miles" name="carMiles">
                                        <span class="grey-999">万公里</span>
                                        <span class="errortip fn-hide error_miles">请填写行驶里程(0.01-99.99)</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th><em class="red">*</em>上牌日期：</th>
                                    <td>
                                        <div class="selmain">
                                            <div class="select zindex-01" data-toggle="select">
                                                <select class="select-selected" id="sCar_DatePic_Year" name="cardYear">
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
                                                <select id="sCar_DatePic_Month" class="select-selected" name="cardMonth">
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
                        	<input type="hidden" id="isReplace" name="isReplace">
                        	<input type="hidden" name="type" id="low_type">
                            <a class="btn btn-orange btn-large font-yh" href="javascript:subApply('low_form');;" name="btnSubmit">获取最低价</a>
                            <a class="btn btn-large font-yh btn-disabled" href=" javascript:;" style="display: none;">正在提交...</a>
                            <a class="btn btn-large font-yh btn-disabled" href="javascript:;" style="display: none;">获取最低价</a>
                        </p>
                        <p class="txt-center">
                        	<label>
                        		<input class="checkbox" checked="checked" type="checkbox" id="articleCB_1">我同意
                        		<a class="bluelink xieyi_link" href="javascript:;">《个人信息保护声明》</a>
                        	</label>
                        </p>
                        </form>
                    </div>
                </div>
    			
                
                <!--想要试驾-->
                <form id="drive_form">
                <div class="card " id="tab2_select">
                    <div class="card-title">
                        <p class="title-text font-yh">想到店试驾？</p>
                        <p class="title-text font-yh">请填写如下信息给商家：<span class="grey-999 fn-fontsize12">(信息保密，不会对外公开)</span></p>
                    </div>
                    <div class="card-cont">
                        <table class="card-table" style="width:980px;">
                            <tbody>
                                <tr>
                                    <th><em class="red">*</em>意向车型：</th>
                                    <td>
                                        <div class="selmain">
                                           	<select class="select-selected" id="drive_Brand">
                                           		<option value="0" selected="selected">选择品牌</option>
                                           		<c:forEach var="brand" items="${brandList}">
                                           		<option value="${brand.brandId}">${brand.brandName}</option>
                                           		</c:forEach>
                                           	</select>
                                        </div>
                                        <div class="selmain">
                                           	<select class="select-selected" id="drive_Series">
                                           		<option value="0">选择车系</option>
                                           	</select>
                                        </div>
                                        <div class="selmain">
                                           	<select class="select-selected" id="drive_year">
                                           		<option value="0">选择年款</option>
                                           	</select>
                                        </div>
                                        <div class="selmain">
                                           	<select class="select-selected" id="drive_carType" name="carId">
                                           		<option value="0">选择车型</option>
                                           	</select>
                                        </div>
                                        <span class="errortip fn-hide error_spec_2">请选择您的车型</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th><em class="red">*</em>您的城市：</th>
                                    <td>
                                        <div class="selmain">
                                            <div class="select zindex-05">
                                                <select class="select-selected" id="AreaProvince_2" name="provinceId">
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
                                                <select class="select-selected" id="AreaCity_2" name="cityId">
					                            	<option value="0">选择城市</option>
					                            </select>
                                            </div>
                                        </div>
                                        <div class="selmain city-last">
                                            <div class="select zindex-05 select-disabled">
                                                <select class="select-selected" id="AreaCounty_2" name="countyId">
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
                                        <input class="inp-text" type="text" id="name_2" maxlength="10" name="userName">
                                        <label><input class="radio" name="userSex" value="1" checked="checked" type="radio">先生</label>
                                        <label><input class="radio" name="userSex" value="2" type="radio">女士</label>
                                        <span class="errortip fn-hide error_name_2">请填写您的姓名</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th><em class="red">*</em>手机号码：</th>
                                    <td>
                                        <input class="inp-text" type="text" id="phone_2" name="phone" />
                                        <span class="errortip fn-hide error_phone_2">请填写您的手机</span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <p class="skipcont">
                       	 	<input type="hidden" name="type" id="drive_type">
                            <a class="btn btn-blue btn-large font-yh" href="javascript:subApply('drive_form');" name="btnSubmit">提交申请</a>
                            <a class="btn btn-large font-yh btn-disabled" href="javascript:;" style="display: none; ">正在提交...</a>
                            <a class="btn btn-large font-yh btn-disabled" href="javascript:;" style="display: none; ">提交申请</a>
                        </p>
                        <p class="txt-center">
                        	<label>
                        	<input class="checkbox" checked="checked" type="checkbox" id="articleCB_2">我同意
                        	<a class="bluelink xieyi_link" href="javascript:;">《个人信息保护声明》</a>
                        	</label>
                        </p>
                    </div>
                </div>
                </form>
            </div>
        </div>
    <!-- end询价预约 -->
    <!--询价列表-->
    <div class="askover-notes">
    	<ul class="notes-ul">
        </ul>
    </div>
</div>

<!-- 个人信息保护声明start -->
<div class="xieyi" style="display: none;" id="xieyi1">
     <h1>个人信息保护声明</h1>
     <div class="tx-area">
         <h3>1、用户须知</h3>
         <p>（1）天津迪族信息技术有限公司是处理通过本网站（简称“网站”）收集的所有个人信息的管理者。天津迪族信息技术有限公司尊重您的隐私，遵照中华人民共和国相关处理网络个人信息的规定来处理您的信息。</p>
         <p>（2）本声明将介绍我们如何处理通过网站收集的所有个人信息，以及访问和更正这些个人信息的权利。</p>
         <p>（3）天津迪族信息技术有限公司享有变更本声明的权利，这些变更信息在更改的声明发布时立即生效。建议您定期阅读声明，了解声明变更的情况。</p>

         <h3>2、个人信息的范围和收集</h3>
         <p>（1）我们收集您的个人信息的最终目的是为了向您提供更好的产品、服务，优化并丰富您的用户体验，这些个人信息系能够单独或者与其他信息结合识别您的个人身份的信息，包括：</p>
         <p>&nbsp;①姓名</p>
         <p>&nbsp;②移动电话</p>
         <p>&nbsp;③您在网站的表格上输入的其他信息（电子邮箱、车牌号、住址等）</p>
         <p>&nbsp;④在您上载到网站的内容中包含的任何个人信息</p>
         <p>（2）以上个人信息均是您自愿提供。您有权拒绝提供，但如果您拒绝提供某些个人信息，您将可能无法使用我们提供的产品、服务，或者可能对您使用产品或服务造成一定的影响。</p>
         <p>（3）对于不满 18 岁的用户，须在其法定监护人已经阅读本声明并且许可的情况下，通过网站提交个人信息。</p>

         <h3>3、个人信息的使用和披露</h3>
         <p>（1）您同意，天津迪族信息技术有限公司可以通过以下方式对个人信息进行使用和披露（包含对于个人信息的存储和处理）：</p>
         <p>&nbsp;①我们（含分支机构）自行使用；</p>
         <p> &nbsp;②我们向关联公司（包括但不限于天津迪族信息技术有限公司的附属公司、控股公司、联营公司等）披露并由其使用；</p>
         <p>&nbsp;③我们向相关汽车经销商及厂商披露并由其使用；</p>
         <p>&nbsp;④我们及关联公司及相关汽车经销商、厂商为满足您的需求，通过您提供的信息与您联系；</p>
         <p>&nbsp;⑤我们及关联公司及相关汽车经销商、厂商会定期或不定期向您发送有关产品、服务或相关活动的信息，您同意接收上述信息。</p>
         <p>（2）您同意免除上述个人信息的接收和/或使用方在按照本法律声明所述情形下进行信息披露和使用而导致的或可能导致的所有索赔、责任和损失。</p>
         <h3>4、更正或投诉</h3>
         <p>如果您需要查询、修改或更正您的个人信息，或对个人信息保护问题有任何疑问或投诉，您可以拨打电话<span class="number-phone">4001-022-772</span>联系我们。</p>
     </div>
 </div>
  <!-- 个人信息保护声明end -->
</body>
</html>