<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
    String path = request.getContextPath();
	request.setAttribute("path", path);
	Long time=System.currentTimeMillis();
	request.setAttribute("time", time);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">   
<meta http-equiv="cache-control" content="no-cache">   
<meta http-equiv="expires" content="0">   
<title>红包发放列表</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
</head>
<body>
	<div id="toolbarDetail" style="padding-bottom:10px;">
		<table width="1200px" border="0"  >
			<tr height="40px">
				<td align="right"><span>发放对象：</span></td>
				<td>
				<input type="hidden" id="redbeltId" value="${redbelt.redbeltId }"/>
				<c:choose>
			       <c:when test="${redbelt.target==1}"><input type="text" class="easyui-textbox" data-options="disabled:true" value="买家"/> </c:when>
			       <c:when test="${redbelt.target== 0}"><input type="text" class="easyui-textbox" data-options="disabled:true" value="买家、卖家"/></c:when>
			       <c:otherwise><input type="text" class="easyui-textbox" data-options="disabled:true" value="卖家"/> </c:otherwise>
				</c:choose>
				</td>
				<td align="right"><span>红包个数：</span></td>
				<td><input type="text" class="easyui-textbox" data-options="disabled:true" value="${redbelt.nums}"/></td>
				<td align="right"><span>红包总金额：</span></td>
				<td><input type="text" class="easyui-textbox" data-options="disabled:true" value="${redbelt.totalAmount}"/></td>
				<td align="right"><span>发放规则：</span></td>
				<td>
					<c:choose>
			       <c:when test="${redbelt.rule==1}"><input type="text" class="easyui-textbox" data-options="disabled:true" value="平均分配"/> </c:when>
			       <c:otherwise><input type="text" class="easyui-textbox" data-options="disabled:true" value="随机分配"/> </c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr height="20px">
			  	<td align="right" ><span>有效期开始时间：</span></td>
				<td colspan="3"><input type="text" class="easyui-textbox" data-options="disabled:true" value="${redbelt.startTime }"/></td>
				<td align="right"><span>有效期结束时间：</span></td>
				<td colspan="3"><input type="text" class="easyui-textbox" data-options="disabled:true" value="${redbelt.endTime }"/></td>
			</tr>
		</table>
	</div>
	
	<table id="redBeltDetail_dataGrid"> </table>
	
<script type="text/javascript">
	$(function (){
		var redbeltId=$("#redbeltId").val();
		$('#redBeltDetail_dataGrid').datagrid({
			url : _path+'/redBelt/loadDetailList?redbeltId='+redbeltId+'',
			columns : [[
			{field : 'userName' ,align : 'center',halign: 'center',title : '会员姓名',width:100},
			{field : 'amount' ,align : 'center',halign: 'center',title : '红包金额',width:150},
			{field : 'status' ,align : 'center',halign: 'center',title : '状态',width:100,formatter: function(value,rows,index){
				if(value=='1'){
	    			return '已使用';
				}else if(value=='0'){
					return '未使用';
				}else{
					return '未知';
				}
			}},
			{field : 'createTime' ,align : 'center',halign: 'center',width:200,title : '发放时间',
				formatter: function(value,rows,index){
					if(value){
						var time=new Date();
						time.setTime(value);		
		    			return time.format("yyyy-MM-dd hh:mm:ss");
					}
				}
			},
			{field : 'orderId' ,align : 'center',halign: 'center',title : '关联订单号',width:150},
			{field : 'useTime' ,align : 'center',halign: 'center',width:200,title : '使用时间',
				formatter: function(value,rows,index){
					if(value){
						var time=new Date();
						time.setTime(value);		
		    			return time.format("yyyy-MM-dd hh:mm:ss");
					}
				}
			}
			]],
			toolbar : '#toolbarDetail',
			pagination : true,
			rownumbers : true,
			autoRowHeight:true,
			singleSelect : true,
			selectOnCheck : false,
			checkOnSelect : false,
			remoteSort : true,
			multiSort : true,
			nowrap : false,
			width : ($(window).width() * 0.99),
			height : ($(window).height() * 0.95),
			pageSize : 25,
			pageList : [ 25, 50, 100, 150 ],
			loadFilter : function(data){
				if (data.resultObject.content) {
					return data.resultObject.content;
				}else {
					return {
						total : 0,
						rows : []
					};
				}
			}
		});
	})
</script>
</body>
</html>

