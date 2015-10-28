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
<title>发红包</title>
</head>
<body>
<div id="main" class="content-main" style="height: 492px; width: 1677px;">
<link type="text/css" href="${path}/resources/styles/framework.css?t=${time}" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/style.css?t=${time}" rel="stylesheet" />
	<form id="groupactivity-from" name="groupactivity-from" action="${path}/redBelt/saveRedBelt" method="POST">
	<div class="goods-detail tableform">
	    <h3>基本信息</h3>
	    <div class="division">
	        <table width="100%" cellspacing="0" cellpadding="0" border="0">
	            <tbody>
		            <tr>
		                <th>发放对象：</th>
		                <td>
		                	<input type="checkbox" name="target" value="1" <c:if test="${redbelt.target eq 1 || redbelt.target eq 0}">checked="checked"</c:if> />买家
		                 	<input type="checkbox"  name="target" value="2" <c:if test="${redbelt.target eq 2 || redbelt.target eq 0}">checked="checked"</c:if>/>卖家 
		                 </td>
		            </tr>
		             <tr >
		             <input type="hidden" id="memberIds"  value="${redbelt.memberLvIds }" />
		                <th>会员等级：</th>
		               <td id="mLevList" class="mlev"></td>
		            </tr>
		             <tr>
		                <th>适用分类商品（不选通用）：</th>
		                <td>
		                	<div class="clearfix" id="ipt_75f43e">
								<div style="display:inline-block;*zoom:1;" id="choose_category" class="object-select clearfix">
									  <div rel="请选择..." class="label">请选择...</div>
									  <div class="handle">&nbsp;</div>
									  <input type="hidden" name="categorys" id="categorys" value="${redbelt.categorys}" />	
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
		            <tr>
		                <th>红包个数：</th>
		                <td><input type="text" size="30" name="nums" id="activity_name" value="${redbelt.nums}" class="x-input " autocomplete="off"><em><font color="red">*</font></em></td>
		            </tr>
		             <tr>
		                <th>红包总金额：</th>
		                <td><input type="text" size="30" name="totalAmount" id="activity_name" value="${redbelt.totalAmount}" class="x-input " autocomplete="off"><em><font color="red">*</font></em></td>
		            </tr>
		            <tr>
		                <th>发放规则：</th>
		                <td><input type="radio" name="rule" value="1" <c:if test="${redbelt.rule eq 1}">checked="checked"</c:if> />平均分配金额 
		                	<input type="radio"  name="rule" value="2" <c:if test="${redbelt.rule eq 2}">checked="checked"</c:if>/>随机分配金额 </td>
		            </tr>
		            <tr style="display:none;">
		                <th>商户经营范围（不选通用）：</th>
		                <td>
		                	<div class="clearfix" id="ipt_75f43e">
								<div style="display:inline-block;*zoom:1;" id="choose_category2" class="object-select clearfix">
									  <div rel="请选择..." class="label">请选择...</div>
									  <div class="handle">&nbsp;</div>
									  <input type="hidden" name="ranges" id="ranges" value="${redbelt.ranges}" />	
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
	    <div class="division">
	        <table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tbody>
		            <tr>
		                <th>有效期开始时间：</th>
		                <td>
			                <input id="startTime" width="200px" type="text" name="startTime" data-options="editable:true" class="easyui-datebox" value="${redbelt.startTime}"></input>
						</td>
					</tr>
		            <tr>
		                <th>有效期结束时间：</th>
						<td>
							<input id="endTime" type="text" name="endTime" data-options="editable:true" class="easyui-datebox"  value="${redbelt.endTime}"></input>
						</td>
					</tr>
		            <tr>
		                <th>是否立即发放：</th>
		                <td>
		                    <input type="radio" name="status" <c:if test="${redbelt.status eq 0}">checked="checked"</c:if> value="0">未发放
		                    <input type="radio" name="status" <c:if test="${redbelt.status eq 1}">checked="checked"</c:if> value="1">已发放
		                </td>
		            </tr>
		             <tr>
		                <th>备注：</th>
		                <td><textarea id="remark" name="remark" rows="10" style=" width:40%" type="textarea">${redbelt.remark}</textarea></td>
		            </tr>
		        </tbody>
	        </table>
	    </div>
	</div>
		<div class="table-action" style="width: 800px;">		
			<input type="hidden" name="redbeltId" value="${redbelt.redbeltId}"/>
			<c:if test="${redbelt.status != 1}">
				<button class="btn" id="btn_save_score_buy_activity" type="button"><span><span>保存</span></span></button>
			</c:if>		
			<button class="btn" id="btn_cancle" type="button"><span><span>取消</span></span></button>
		</div>
	</form>
</div>
<div id="category_dialog" class="easyui-dialog" style="width: 584px;height:500px; padding: 10px 20px" data-options="fit:true,closed:true,cache:false"></div>
</div>
<script type="text/javascript">
$(function (){
	findMemberLevel();
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
		window.location.href=yoyo.mpUrl+"/redBelt/tolist";
	});
	$("#btn_save_score_buy_activity").click(function (){
		var chk_value =[];//定义一个数组    
        $('input[name="target"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数    
        	chk_value.push($(this).val());//将选中的值添加到数组chk_value中    
        });
		if(chk_value.length<=0){
			$.messager.alert('警告','请选择发放对象');
			return;
		}
		var startTime=$("#startTime").datebox('getValue');
		var endTime=$("#endTime").datebox('getValue');
		var remark=$("#remark").val();
		
		if(remark.length>300){
			$.messager.alert('警告','描述不能超过300字符限制');
			return;
		}
		if(startTime.length<=0){
			$.messager.alert('警告','开始时间不能为空');
			return;
		}
		if(endTime.length<=0){
			$.messager.alert('警告','结束时间不能为空');
			return;
		}
		if(!compareDate(startTime,endTime)){
			$.messager.alert('警告','结束时间不能小于或等于开始时间');
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
/** 获得会员等级 */
function findMemberLevel() {
	var ids = null;
	if ($("#memberIds").val() != '' && $("#memberIds").val() != null) { // 编辑时，进行默认选择
		ids = $("#memberIds").val().split(',');
	}
	$.getJSON(biz.rootPath() + '/memberLevel/findAll', function(json) {
		var rhtml = "";
		$.each(json.content, function(i, n) {
			rhtml += '<input type="checkbox" name="memberLvIds" value="'+ n.memberLvId + '" id="level_' + i + '"';
			if (ids != null && ids.indexOf(n.memberLvId + '') != -1) {
				rhtml += 'checked';
			}
			rhtml += '><label for="level_' + i + '">' + n.name + '</label>';
		});
		$('#mLevList').html(rhtml);
	});
}

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

