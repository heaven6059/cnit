<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>店铺信息</title>
</head>
<body>
<script type="text/javascript" src="${path}/resources/scripts/biz/shop/shopListDetail.js"></script>
	<div class="easyui-tabs" data-options="fit:true" style="border:1px solid #F2F2F2;">
		<div title="基本信息">
		 
			<table  class="shop-detail" border="1" cellspacing="1" >
			   <tr>
					<th width="23%" class="shop-detail-th" colspan="2">店铺信息</th>
					<th width="23%" class="shop-detail-th" colspan="2">店铺状态</th>
					<th width="29%" class="shop-detail-th" colspan="2">企业信息</th>
					<th width="25%" class="shop-detail-th" colspan="2">企业负责人</th>
				</tr>
				<tr><td colspan="8"><hr></td></tr>
				<tr>
					<td  width="8%" class="shop-detail-label">店主名：</td>
					<td  width="15%">${shopInfo.loginName }</td>
					<td  width="8%" class="shop-detail-label">开启状态：</td>
					<td  width="15%">
						<c:choose>
						    <c:when test="${shopInfo.shopstatus=='0'}">
						       	否
						    </c:when>
						    <c:otherwise>
						   		  是   
						    </c:otherwise>   
						  </c:choose>
					</td>
					<td  width="14%" class="shop-detail-label">企业名称：</td>
					<td  width="15%">${fn:escapeXml(shopInfo.companyName)}</td>
					<td  width="10%" class="shop-detail-label">姓名：</td>
					<td  width="15%">${fn:escapeXml(shopInfo.companyCname)}</td>
				</tr>
				<tr>
					<td class="shop-detail-label">店主实名：</td>
					<td>${fn:escapeXml(shopInfo.storeIdcardname) }</td>
					<td class="shop-detail-label">推荐状态：</td>
					<td>否</td>
					<td class="shop-detail-label">营业执照号：</td>
					<td>${shopInfo.companyNo }</td>
					<td class="shop-detail-label">身份证号：</td>
					<td>${shopInfo.companyCidcard }</td>
			    </tr>
				<tr>
					<td class="shop-detail-label">身份证号：</td>
					<td>${shopInfo.storeIdcard}</td>
					<td class="shop-detail-label">审核状态：</td>
					<td>
						<c:choose>
						    <c:when test="${shopInfo.approved=='0'}">
						       	待审核
						    </c:when>
						     <c:when test="${shopInfo.approved=='1'}">
						       	审核通过
						    </c:when>
						    <c:otherwise>
						   		 审核未通过 
						    </c:otherwise>   
						  </c:choose>
					</td>
					<td class="shop-detail-label">税务登记证号：</td>
					<td>${shopInfo.companyTaxno}</td>
					<td class="shop-detail-label">职务：</td>
					<td>${fn:escapeXml(shopInfo.companyCharge)}</td>
				</tr>
				<tr>	
					<td class="shop-detail-label">店铺名称：</td>
					<td>${fn:escapeXml(shopInfo.storeName)}</td>
					<td class="shop-detail-label">审核备注：</td>
					<td>${shopInfo.approvedremark}</td>
					<td class="shop-detail-label">组织机构代码：</td>
					<td>${shopInfo.companyCodename}</td>
					<td class="shop-detail-label">联系电话：</td>
					<td>${shopInfo.companyCtel}</td>
				</tr>
				<tr>
					<td class="shop-detail-label">所在地区：</td>
					<td>${shopInfo.companyCarea}</td>
					<td class="shop-detail-label">经营范围：</td>
					<td>${shopInfo.regionCatName}</td>
					<td class="shop-detail-label">法人：</td>
					<td>${fn:escapeXml(shopInfo.companyIdname)}</td>
					<td></td><td></td>
				</tr>
				<tr>
					<td class="shop-detail-label">详细地址：</td>
					<td>${fn:escapeXml(shopInfo.companyCarea)}</td>
					<td></td><td></td>
					<td class="shop-detail-label">法人身份证号：</td>
					<td>${shopInfo.companyIdcard}</td>
					<td></td><td></td>
				</tr>
				<tr>
					<td class="shop-detail-label">手机号码：</td>
					<td>${shopInfo.tel}</td>
					<td></td><td></td>
					<td class="shop-detail-label">注册地区：</td>
					<td>${shopInfo.companyArea}</td>
					<td></td><td></td>
				</tr>
				<tr>
					<td class="shop-detail-label">邮箱：</td>
					<td>${shopInfo.email}</td>
					<td></td><td></td>
					<td class="shop-detail-label">注册地址：</td>
					<td>${fn:escapeXml(shopInfo.companyAddr)}</td>
					<td></td><td></td>
				</tr>
				<tr>
					<td class="shop-detail-label">保证金：</td>
					<td>${shopInfo.issueMoney}</td>
					<td></td><td></td>
					<td class="shop-detail-label">注册资金：</td>
					<td>${shopInfo.companyEarnest}万元</td>
					<td></td><td></td>
				</tr>
				<tr>
					<td class="shop-detail-label">店铺备注：</td>
					<td>${shopInfo.remark}</td>
					
					<td></td><td></td>
					<td class="shop-detail-label">成立时间：</td>
					<td> <c:if test="${!empty shopInfo.companyTime }">${fn:substring(shopInfo.companyTime,0,10)}</c:if></td>
					<td></td><td></td>
				</tr>
				<tr>
					<td></td><td></td>
					<td></td><td></td>
					<td class="shop-detail-label">营业执照经营范围：</td>
					<td>${fn:escapeXml(shopInfo.companyRemark)}</td>
					<td></td><td></td>
				</tr>
				<tr>
					<td></td><td></td>
					<td></td><td></td>
					<td class="shop-detail-label">营业执照有效期：</td>
					<td> <c:if test="${!empty shopInfo.companyTimestart }">${fn:substring(shopInfo.companyTimestart,0,10)}</c:if>
					     ~<c:if test="${!empty shopInfo.companyTimeend }">${fn:substring(shopInfo.companyTimeend,0,10)}</c:if>
					</td>
					<td></td><td></td>
				</tr>
				<tr>
					
					<td></td><td></td>
					<td></td><td></td>
					<td class="shop-detail-label">企业官网地址：</td>
					<td>${shopInfo.companyUrl}</td>
					<td></td><td></td>
				</tr>
				
			</table>
		</div>
		<div title="认证信息">
			<table  class="shop-detail shoplist-confirm" border="1" cellspacing="1" >
			   <tr>
					<th class="shop-detail-th">店主证件</th>
					<th class="shop-detail-th">营业执照</th>
					<th class="shop-detail-th">组织机构代码</th>
					<th class="shop-detail-th">税务登记证</th>
				</tr>
				<tr><td colspan="8"><hr></td></tr>
				<tr>
					<td>${shopInfo.loginName }</td>
					<td>${shopInfo.companyNo }</td>
					<td>${shopInfo.companyCodename}</td>
					<td>${shopInfo.companyTaxno}</td>
				
				</tr>
				<tr>
					<td><img src="" data-img="${shopInfo.imageId}" style="width:200px;height:200px;"></td>
					<td><img src="" data-img="${shopInfo.imageCid}" style="width:200px;height:200px;"></td>
					<td><img src="" data-img="${shopInfo.imageCodeid}" style="width:200px;height:200px;"></td>
					<td><img src="" data-img="${shopInfo.imageTaxid}" style="width:200px;height:200px;"></td>
				</tr>
			</table>
		</div>
		<div title="经验值">
		
			<form id="experienceForm${shopInfo.companyId}" >
				   <table width="100%" class="shop-detail" cellspacing="0" cellpadding="0" border="0" style="margin-top:20px;">
				      <input type="hidden" name="type" value="0">
				   	  <tr >
				   	  		<td class="shop-detail-label" >当前经验值：</td>
				   	  		<td><span style="color:red;font-weight:bold;">${shopInfo.experience}</span></td>
				   	  </tr>
				   	  <tr>
				   	  		<td class="shop-detail-label">调整经验值（增/减）：</td>
				   	  		<td><input type="text" class="easyui-numberbox" name="experience" data-options="precision:0,require:true">   输入负值即可减少经验值</td>
				   	  </tr>
				   	  <tr>
				   	  		<td class="shop-detail-label">备注：</td>
				   	  		<td><input type="text" maxlength="50" name="remark"></td>
				   	  </tr>
				   	  
				      <tr>
				      		<td colspan="2" ><a  style="margin-left:30%;" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onClick="saveExperience()">保存</a></td>
				      </tr>
				   </table>
					
					<table id="experienceDataGrid${shopInfo.companyId}" class="experienceDataGrid"></table>
			</form>
		</div>
		<!-- <div title="图片空间">
			<form id="pictureForm">
			   <table width="100%" class="shop-detail" cellspacing="0" cellpadding="0" border="0" style="margin-top:20px;"> 
			   	  <tr>
			   	  		<td class="shop-detail-label" >当前图片空间：</td>
			   	  		<td></td>
			   	  </tr>
			   	  <tr>
			   	  		<td class="shop-detail-label">当前使用：</td>
			   	  		<td></td>
			   	  </tr>
			   	  <tr>
			   	  		<td class="shop-detail-label">调整空间（增/减）：</td>
			   	  		<td><input type="text" class="easyui-numberbox" name="advance" data-options="precision:2">G    输入负值即可减少空间</td>
			   	  </tr>
			   	  <tr>
			   	  		<td class="shop-detail-label">备注：</td>
			   	  		<td><input type="text" maxlength="50"></td>
			   	  </tr>
			      <tr>
			      		<td colspan="2" ><a id="picSubmit" style="margin-left:260px;" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onClick="">保存</a></td>
			      </tr>
			   </table>
				<table id="pictureDataGrid"></table>
				
			</form>
		</div> -->
		<div title="保证金">
			<form id="earnestForm${shopInfo.companyId}">
				   <table width="100%" class="shop-detail" cellspacing="0" cellpadding="0" border="0" style="margin-top:20px;">
				   	  <tr>
				   	  		<td class="shop-detail-label" >当前保证金：</td>
				   	  		<td><span style="color:red;font-weight:bold;">${shopInfo.earnest}元</span></td>
				   	  </tr>
				   	  <tr>
				   	  		<td class="shop-detail-label">保证金（增/减）：</td>
				   	  		<td><input type="text" class="easyui-numberbox" name="earnestValue" data-options="precision:2">元  输入负值即可减少保证金</td>
				   	  </tr>
				   	  <tr>
				   	  		<td class="shop-detail-label">备注：</td>
				   	  		<td><input type="text" maxlength="50" name="remark"></td>
				   	  </tr>
				      <tr>
				      		<td colspan="2" ><a  style="margin-left:30%;" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onClick="saveEarnest()">保存</a></td>
				      </tr>
				   </table>
					<table id="earnestDataGrid${shopInfo.companyId}" class="earnestDataGrid"></table>
			</form>
		</div>
		<c:if test="${shopInfo.issueType==1 }">
			<div title="分店信息">
				<table id="storeDataGrid${shopInfo.companyId}" class="storeDataGrid"></table>
			</div>
		</c:if>
	</div>
	
</body>
</html>
