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
<link href="${path}/resources/styles/goods/enquiry.css?v=${versionVal}" rel="stylesheet" />
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
	                        <p class="title-text font-yh">YOYO会尽最大努力满足您的需求！</p>
	                        <p class="title-text font-yh">请填写如下信息：<span class="grey-999 fn-fontsize12">(信息保密，不会对外公开)</span></p>
	                    </div>
	                    <div class="card-cont">
	                    	<form id="requireForm">
		                        <table class="card-table" style="width:750px;">
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
		                                        <span class="errortip fn-hide error_email">请填写正确的邮箱</span>
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
		                                            <div class="select zindex-05">
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
	                        <p class="txt-center"><label><input id="iAgreed" class="checkbox" checked="checked" type="checkbox">我同意<a id="showDeclaration" class="bluelink" href="javascript:;">《个人信息保护声明》</a></label></p>
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