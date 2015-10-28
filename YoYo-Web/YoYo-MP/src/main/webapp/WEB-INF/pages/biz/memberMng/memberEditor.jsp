<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>编辑会员信息</title>
</head>
<body>
	<div class="easyui-tabs" data-options="fit:true">
		<div title="信息详细">
			<table  class="table-form">
				<tr>
					<td class="table-form-label">登录名：</td>
					<td><c:out value='${content.pamAccount.loginName}'/></td>
					<td class="table-form-label">用户姓名：</td>
					<td><c:out value="${content.member.name}"></c:out></td>
					<td class="table-form-label">账号类型 ：</td>
					<td>
						<c:set var="accountType" value="${content.pamAccount.accountType}"/>
					    <c:if  test="${fn:indexOf(accountType,'10')==0 }" >买家账户</c:if>
						<c:if  test="${fn:indexOf(accountType,'11')==0 }" >卖家账户</c:if>
						<c:if  test="${fn:indexOf(accountType,'12')==0 }" >店员账户</c:if>
					</td>
				</tr>
				<tr>
					<td class="table-form-label">用户昵称：</td>
					<td><c:out value="${content.member.nickName}"></c:out></td>
					<td class="table-form-label">Email：</td>
					<td><c:out value="${content.member.email}"></c:out></td>
					<td class="table-form-label">会员等级：</td>
					<td>
						<c:set var="memberLvId" value="${content.member.memberLvId}"/>
					 	<c:if  test="${memberLvId==1 }" >铜牌会员</c:if>
						<c:if  test="${memberLvId==2 }" >银牌会员</c:if>
						<c:if  test="${memberLvId==3 }" >金牌会员</c:if>
						<c:if  test="${memberLvId==4 }" >砖石会员</c:if>
					</td>
				</tr>
				<tr>
					<td class="table-form-label">手机：</td>
					<td><c:out value="${content.member.mobile}"></c:out></td>
				    <td class="table-form-label">地区：</td>
					<td><c:out value="${content.member.area}"></c:out></td>
					<td class="table-form-label">固定电话：</td>
					<td><c:out value="${content.member.tel}"></c:out></td>
				</tr>
				<tr>
					<td class="table-form-label">性别：</td>
					<td>
					 	<c:if  test="${content.member.sex==1 }" >男</c:if>
						<c:if  test="${content.member.sex==2 }" >女</c:if>
					</td>
					<td class="table-form-label">注册渠道：</td>
					<td>
					 	<c:if  test="${content.member.channel==10 }" >web前端</c:if>
						<c:if  test="${content.member.channel==11 }" >web管理平台</c:if>
						<c:if  test="${content.member.channel==20 }" >appAndroid</c:if>
						<c:if  test="${content.member.channel==21 }" >appIOS</c:if>
					</td>
					<td class="table-form-label">注册IP：</td>
					<td><c:out value="${content.pamAccount.regIp}"></c:out></td>
				</tr>
				<tr>
					<td class="table-form-label">会员积分：</td>
					<td><c:out value="${content.member.pointSummation}"></c:out></td>
					<td class="table-form-label">出生日期：</td>
					<td><c:out value="${content.member.bYear}-${content.member.bMonth}-${content.member.bDay}"></c:out></td>
				</tr>
				<tr>
					<td class="table-form-label">联系地址：</td>
					<td colSpan="5"><c:out value="${content.member.addr}"></c:out></td>
				</tr>
				<tr>
					<td class="table-form-label">备注：</td>
					<td colSpan="5"><c:out value="${content.member.remark}"></c:out></td>
				</tr>
			</table>
		</div>
		<div title="修改信息">
			<form id="memberForm">
				<input type="hidden"  name="memberLvId"  value="<c:out value='${content.member.memberLvId}'></c:out>" />
				<table  class="table-form">
					<tr>
						<td class="table-form-label">会员编号：</td>
						<td><input type="text" name="memberId" value="<c:out value='${content.member.memberId}'/>" readonly="true"></td>
						<td class="table-form-label">用户名：</td>
						<td><input type="text" class="easyui-textbox" name="loginName" value="<c:out value='${content.pamAccount.loginName}'/>" readonly="true"></td>
					</tr>
					<tr>
						<td class="table-form-label">姓名：</td>
						<td><input type="text" class="easyui-textbox" name="name" value="<c:out value='${content.member.name}'/>"></td>
						<td class="table-form-label">昵称：</td>
						<td><input type="text" class="easyui-textbox" name="nickName" value="<c:out value='${content.member.nickName}'/>"></td>
					</tr>
					<tr>
						<td class="table-form-label">邮箱：</td>
						<td><input type="text" class="easyui-validatebox" name="email" validType="email" value="<c:out value='${content.member.email}'/>"></td>
						<td class="table-form-label">联系地址：</td>
						<td><input type="text" class="easyui-textbox" name="addr" value="<c:out value='${content.member.addr}'/>"></td>
					</tr>
					<tr>
						<td class="table-form-label">身份证号码：</td>
						<td><input type="text" class="easyui-validatebox" validType="idCode" name="idcard" value="<c:out value='${content.member.idcard}'/>"></td>
							<td class="table-form-label">地区：</td>
						<td><input type="text" class="easyui-textbox" name="area" value="<c:out value='${content.member.area}'/>"></td>
					</tr>
					<tr>
						<td class="table-form-label">性别：</td>
						<td>
						<input class="easyui-combobox" name="sex"  data-options="valueField: 'value',textField: 'label',data: [{label: '男',value: '1' <c:if  test="${content.member.sex==1 }" >,selected:true</c:if> },{label: '女',value: '2' <c:if  test="${content.member.sex==2 }" >,selected:true</c:if>},]" /> 
						</td>
						<td class="table-form-label">出生日期：</td>
						<td><input type="text" class="easyui-datebox" name="birth"
							value="<c:out value='${content.member.bYear}-${content.member.bMonth}-${content.member.bDay}'/>"></td>
					</tr>
					<tr>
						<td class="table-form-label">移动电话：</td>
						<td><input type="text" class="easyui-validatebox" name="mobile" validType="mobile" value="<c:out value='${content.member.mobile}'/>"></td>
						<td class="table-form-label">固定电话：</td>
						<td><input type="text" class="easyui-validatebox" name="tel" validType="tel" value="<c:out value='${content.member.tel}'/>"></td>
					</tr>
					<tr>
						<td class="table-form-label">邮编：</td>
						<td><input type="text" class="easyui-validatebox" validType="ZIP" name="zip" value="<c:out value='${content.member.zip}'/>"></td>
					</tr>
					
				</table>
				<div style="padding-left:350px;padding-top:20px;">
					<a id="memberSubmit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onClick="javascript:memberSubmit()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="easyui-linkbutton" icon="icon-cancel" onclick="javascript:$('#dt').dialog('close');">取消</a>
				</div>
			</form>
			<script type="text/javascript">
				function memberSubmit() {
					if(!$("#memberForm").form('validate')){
						return false;
					}
					formSubmit('memberForm', yoyo.mpUrl+'/member/modifyMember', function() {
						window.location.reload('true');
					}, null);
				}
			</script>
		</div>
		<%-- <div title="预存款">
			<form id="advanceForm">
				<div>
					<label>当前预存款：<c:out value="${content.member.advance}" /></label>
				</div>
				<div>
					<label for="">充值金额：</label><input type="text" class="easyui-numberbox" name="advance"
						data-options="min:0,precision:2">
				</div>
				<div>
					<label for="">备注：</label><input type="text" class="easyui-textbox" name="remark" data-options="multiline:true">
				</div>
				<div>
					<a id="advanceSubmit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onClick="">保存</a>
				</div>
			</form>
		</div> --%>
	<%-- 	<div title="经验值">
			<form id="experienceForm">
				<div>
					<label>当前经验值：<c:out value="${content.member.advance}" /></label>
				</div>
				<div>
					<label for="">调整经验值（增/减）：</label><input type="text" class="easyui-numberbox" name="advance"
						data-options="precision:2">
				</div>
				<div>
					<a id="experienceSubmit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onClick="">保存</a>
				</div>
			</form>
		</div> --%>
		<%-- <div title="积分">
			<form id="pointForm">
				<div>
					<label>当前积分：<c:out value="${content.member.pointSummation}" /></label>
				</div>
				<div>
					<label for="">调整积分(增/减)：</label><input type="text" class="easyui-numberbox" name="advance"
						data-options="precision:2">
				</div>
				<div>
					<label for="">备注：</label><input type="text" class="easyui-textbox" name="remark" data-options="multiline:true">
				</div>
				<div>
					<a id="pointSubmit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onClick="">保存</a>
				</div>
			</form>
		</div> --%>
		<!-- <div title="订单">
			<table id="orderTable"></table>
		</div>
		<div title="站内信">
			<table id="innerMsgTable"></table>
		</div>
		<div title="会员备注"></div> -->
	</div>
	<script type="text/javascript">
		
	</script>
</body>
</html>
