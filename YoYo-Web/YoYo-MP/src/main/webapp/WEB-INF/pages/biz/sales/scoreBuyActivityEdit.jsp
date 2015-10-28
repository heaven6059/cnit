<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    String path = request.getContextPath();
	request.setAttribute("path", path);
	Long time=System.currentTimeMillis();
	request.setAttribute("time", time);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">   
<meta http-equiv="cache-control" content="no-cache">   
<meta http-equiv="expires" content="0">   
<title>活动管理</title>
</head>
<body>
<div id="main" class="content-main" style="height: 492px; width: 1677px;">
<link type="text/css" href="${path}/resources/styles/framework.css?t=${time}" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/style.css?t=${time}" rel="stylesheet" />
	<form id="groupactivity-from" name="groupactivity-from" action="../scoreBuyActivity/saveScoreBuyActivity" method="POST">
	<input type="hidden" id="dom_el_0e155b0" name="act_id" value="" class="x-input " autocomplete="off">
	<input type="hidden" id="type" name="type" value="${type }"/>
	<div class="goods-detail tableform">
	    <h3>基本信息</h3>
	    <div class="division">
	        <table width="100%" cellspacing="0" cellpadding="0" border="0">
	            <tbody>
		            <tr>
		                <th>活动名称：</th>
		                <td><input type="text" size="60" name="name" id="activity_name" value="${ScoreBuyActivity.name}" class="x-input " autocomplete="off"><em><font color="red">*</font></em></td>
		            </tr>
		            <tr>
		                <th>活动描述：</th>
		                <td><textarea id="activity_desc" name="description" rows="10" style=" width:60%" type="textarea">${ScoreBuyActivity.description}</textarea></td>
		            </tr>
		         <c:if test="${type==2}">
		            <tr>
		            	<th>商家库存不得少于</th>
		            	<td><input type="text" value="${ScoreBuyActivity.limitNum}" name="limitNum" size="10"  id="limitNum" precision="0" class="easyui-numberbox">&nbsp;&nbsp;件(填“0”时表示无限制最少库存)</td>
		            </tr>
		          </c:if>
				</tbody>
	        </table>
	    </div>
	    <div class="division">
	        <table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tbody>
				<c:if test="${type!=4 && type!=5 }">
		            <tr>
		                <th>申请开始时间：</th>
		                <td>
			                <input id="apply_start_date" width="200px" type="text" name="apply_start_time" data-options="editable:false" class="easyui-datetimebox" value="<fmt:formatDate value="${ScoreBuyActivity.applyStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"></input>
						</td>
					</tr>
		            <tr>
		                <th>申请结束时间：</th>
						<td>
							<input id="apply_end_date" type="text" name="apply_end_time" data-options="editable:false" class="easyui-datetimebox" value="<fmt:formatDate value="${ScoreBuyActivity.applyEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"></input>
						</td>
					</tr>
				</c:if>
		            <tr>
		                <th>活动开始时间：</th>
		                <td>
							<input id="activity_start_date" type="text" name="start_time" data-options="editable:false" class="easyui-datetimebox" value="<fmt:formatDate value="${ScoreBuyActivity.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"></input>
						</td>
		            </tr>
		            <tr>
		                <th>活动结束时间：</th>
						<td>
							<input id="activity_end_date" type="text" name="end_time" data-options="editable:false" class="easyui-datetimebox" value="<fmt:formatDate value="${ScoreBuyActivity.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"></input>
						</td>
		            </tr>
		            <tr>
		                <th>活动是否开启：</th>
		                <td>
		                    <input type="radio" name="actOpen" <c:if test="${ScoreBuyActivity.actOpen eq 1}">checked="checked"</c:if> value="1">开启
		                    <input type="radio" name="actOpen" <c:if test="${ScoreBuyActivity.actOpen eq 0}">checked="checked"</c:if> value="0">关闭
		                </td>
		            </tr>
		             <tr>
		                <th>商户经营范围：</th>
		                <td>
		                	<div class="clearfix" id="ipt_75f43e">
								<div style="display:inline-block;*zoom:1;" id="choose_category" class="object-select clearfix">
									  <div rel="请选择..." class="label">请选择...</div>
									  <div class="handle">&nbsp;</div>
									  <input type="hidden" value="" vtype="" id="list_datas" name="business_type">
								</div>
								<div style="display:block;" id="choose_cateogry_items" class="gridlist rows-body">
									<c:forEach items="${categorys}" var="category">
									<div class="row">
										<div style="float: left;">${category.catName}</div>
										<div class="opt" style="float: left;"><img app="desktop" src="../resources/images/delecate.gif" data="${category.catId}"></div>
									</div>
									</c:forEach>
								</div>
							</div>
						</td>
		            </tr>
		        </tbody>
	        </table>
	    </div>
	</div>
		<div class="table-action">		
			<input type="hidden" name="actId" value="${ScoreBuyActivity.actId}"/>
			<input type="hidden" name="businessType" id="categorys" value="${ScoreBuyActivity.businessType}" />	
			<c:if test="${ScoreBuyActivity.actOpen != 1}">
				<button class="btn" id="btn_save_score_buy_activity" type="button"><span><span>保存</span></span></button>
			</c:if>		
			<button class="btn" id="btn_cancle" type="button"><span><span>取消</span></span></button>
		</div>
	</form>
</div>
<div id="category_dialog" class="easyui-dialog" style="width: 884px;height:500px; padding: 10px 20px" data-options="fit:true,closed:true,cache:false"></div>
</div>
<script type="text/javascript">
$(function (){
	$("#choose_category").click(function (){
		$("#category_dialog").dialog({
		    width: 805,
		    height: 533,
		    href: _path+'/scoreBuyActivity/toMainCategoryList',
		    modal: true,
		    closed: true,
		    title:'',
		    closed:true,
		    cache:false,
		    draggable:false
		});
		$('#category_dialog').dialog('open');
	});
	
	$("#btn_cancle").click(function (){
		var type=$("#type").val();
		window.location.href=yoyo.mpUrl+"/scoreBuyActivity/toScoreBuyActivityList?t="+type+"";
	});
	$("#btn_save_score_buy_activity").click(function (){
		var type=$("#type").val();
		var activity_name=$.trim($("#activity_name").val());
		var activity_desc=$.trim($("#activity_desc").val());
		var apply_start_date=$("#apply_start_date").datebox('getValue');
		var apply_end_date=$("#apply_end_date").datebox('getValue');
		var activity_start_date=$("#activity_start_date").datebox('getValue');
		var activity_end_date=$("#activity_end_date").datebox('getValue');
		if(activity_name.length<=0){
			$.messager.alert('警告','活动名称不能为空');
			return;
		}
		if(activity_desc.length>300){
			$.messager.alert('警告','活动描述不能超过300字符限制');
			return;
		}
		if(type!=4 && type!=5){
			if(apply_start_date.length<=0){
				$.messager.alert('警告','申请开始时间不能为空');
				return;
			}
			if(apply_end_date.length<=0){
				$.messager.alert('警告','申请结束时间不能为空');
				return;
			}
			if(!compareDate(apply_start_date,apply_end_date)){
				$.messager.alert('警告','申请结束时间不能小于或等于申请开始时间');
				return;
			}
		}
		if(activity_start_date.length<=0){
			$.messager.alert('警告','活动开始时间不能为空');
			return;
		}
		if(activity_end_date.length<=0){
			$.messager.alert('警告','活动结束时间不能为空');
			return;
		}
		if(!compareDate(activity_start_date,activity_end_date)){
			$.messager.alert('警告','活动结束不能小于或等于活动开始时间');
			return;
		}
		$(this).closest("form[name='groupactivity-from']").submit();  //查找$(this)最近的form
	});
	
	$("select[name='activity_hour']").each(function (x,y){
		for(var t=0;t<24;t++){
			$(this).append("<option>"+pad(t,2)+"</option>");
		}
	});
	
	$("select[name='activity_minute']").each(function (x,y){
		for(var t=0;t<60;t++){
			$(this).append("<option>"+pad(t,2)+"</option>");
		}
	});
	
	$("#choose_cateogry_items").on("click","img",function (){
		var categorysArray= new Array();
		categorysArray=$("#categorys").val().split(",");
		var index=categorysArray.arryIndexOf($(this).attr("data"));
		if(index>=0){
			categorysArray.elremove(index);
			$("#categorys").val(categorysArray.join(","));
		}
		$(this).parent().parent().remove();
	});
});


function closeCategoryDialog(){
	$('#category_dialog').dialog('close');
}

function pad(num, n) {
    var len = num.toString().length;
    while(len < n) {
        num = "0" + num;
        len++;
    }
    return num;
}
</script>
</body>
</html>

