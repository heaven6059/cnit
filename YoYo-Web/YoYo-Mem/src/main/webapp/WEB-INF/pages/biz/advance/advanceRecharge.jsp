<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   String path =  request.getContextPath();
   request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预存款充值</title>
<%@include file="/resources/include/head.jsp" %>   
<script type="text/javascript" src="${path}/resources/scripts/jquery/jquery-1.11.2.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
</head>
<body>
	<div class="member-main">
		<div class="title title2">
			充值到预充款
		</div>
				<p class="admin-title textright">
				  <span class="flt">
					您当前的预存款：<em class="font-orange fontbold font14px" id = "totalPoint"></em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					冻结的预存款： <span class="point" id = "pointFreeze"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				          可用的预存款：<span class="point" id = "pointFreeze"></span>
				  </span>
				</p>		
		<form id="formId" class="section">
			<input type="hidden" name="memberId" value="${memberDTO.memberId}" />
			<div class="FormWrap" style="background: none; border: none; padding: 0; margin: 0;">
				<div class="division" style="border: none; margin-bottom: 0">
					<table class="forform" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<th >
								   输入充值金额：
								</th>
								<td>
								   <input autocomplete="off" class="x-input " type="text" name="email" value="${memberDTO.email}" ></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="division" style="border: none;">
					<table class="forform" width="100%" border="0" cellspacing="0"
						cellpadding="0">
					</table>
				</div>
				<div style="padding-left: 80px"  >
					<button class="btn submit-btn"  type="button" rel="_request" onclick="javascript:submitForm();">
						<span><span>支付宝</span></span>
					</button>
					<button class="btn submit-btn"  type="button" rel="_request" onclick="javascript:submitForm();">
						<span><span>微信支付</span></span>
					</button>
				</div>
				<div class="division" style="border: none;">
					<table class="forform" width="100%" border="0" cellspacing="0"
						cellpadding="0">
					</table>
				</div>								
				<div style="padding-left: 142px">
					<button class="btn submit-btn" type="button" rel="_request" onclick="javascript:submitForm();">
						<span><span>去充值</span></span>
					</button>
				</div>
			</div>
		</form>
	</div>
	
<script type="text/javascript">
	function submitForm(){
		var url=yoyo.memUrl+"/personInfo/updatePersonInfo";
		var data=$('#formId').serializeArray();
		 yoyo.ajaxRequest(url,data,true,"json",function(data){
			   		yoyo.loading("","");
			   		var resultCode = data.resultObject.retCode;
			   		if (resultCode == yoyo.successCode) {
			   			jAlert("修改成功！");
			   			window.location.href = yoyo.memUrl+"/personInfo/getPersonInfo"; 
			   		} else {
			   			jAlert("修改失败 ！");
			   		}
			   	});
	} 	
</script>
</body>
</html>