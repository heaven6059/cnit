<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加/编辑满减促销</title>
<link type="text/css"	href="${path}/resources/styles/shopMng/shopManager.css?v=${versionVal}"	rel="stylesheet" />

	<script type="text/javascript"	src="${path}/resources/scripts/select2/select2.min.js"></script>
	<link type="text/css"	href="${path}/resources/styles/select2/select2.min.css"	rel="stylesheet" />
	<script type="text/javascript"	src="${path}/resources/scripts/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"	src="${path}/resources/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript"	src="${path}/resources/scripts/biz/activityMng/addFullReduce.js?v=${versionVal}"></script>
	<script type="text/javascript"	src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
	
	<link rel="stylesheet" href="${path}/resources/styles/jquery-ui.css"	type="text/css" />

	<script type="text/javascript"	src="${path}/resources/scripts/jquery/jquery-ui.js"></script>
</head>
<body>

	<div class="shop_manager_right ">
		<div class="title ">添加/编辑满减促销</div>
		<input type="hidden" name="actId" id="actId" value="${actId}">
		<input type="hidden" name="ruleId" id="ruleId" >
			
		<form method="post" id='form_saveRule' class="section easyui-form">
			<div id="gEditor-Body">
				<div class="title_fb ">设置规则名称</div>
				<div class="FormWrap" style="background: none">
					<label for="" class="col-sm-1 control-label">名称：</label>
					<input type="text" name="ruleName" id="ruleName" required="required" style="width: 400px;height: 30px;" maxlength="50" class="easyui-validatebox" data-validate-field="fullminus_name">
				</div>
			</div>
			<div class="FormWrap" style="background: none">
			<div class="title_fb ">设置适用商品</div>
				<table id="goodsListTable" style="margin: 10px auto;"></table>
			</div>
			
			<!-- <div style="text-align:right;line-height:60px"><input type="button" name="addGoods" style="width: 100px;" onclick="updateGoods()" class="btn openappoint" id="addGoods" value="添加"></div>
			
			<div class="FormWrap" style="background: none">
			<div class="title_fb ">已添加的商品</div>
				<table id="goodsAddListTable" style="margin: 10px auto;"></table>
				<table id="goodsAddListTable" class="easyui-datagrid" 
					data-options="collapsible:true,singleSelect:true">
				<thead>
					<tr>
						<th data-options="field:'smallPic',align:'center',formatter:imageEditor">商品图片</th>
						<th data-options="field:'name',align:'center'">商品名称</th>
						<th data-options="field:'price',align:'center'">商品价格</th>
						<th data-options="field:'editor',align:'center',formatter:delEditor">操作</th>
					</tr>
				</thead>
			</table>
			</div> -->
			
			<div class="FormWrap" style="line-height:3;background: none">
			<div class="title_fb ">设置规则</div>
				<div>
					<div>
						<label>促销规则：</label> <a id="button-rule-add" class="btn openappoint"  href="javascript:void(0)">+添加满减区间规则</a>
						<div id="good-rule-group">
								<div class="rule-price">消费满<input type="text" name="fullPrice" style="width:50px;" maxlength="4" class="easyui-validatebox" required="required" id="fullPrice"  >元，
								减<input type="text" name="reducePrice" required="required" style="width:50px;" maxlength="4" class="easyui-validatebox"  id="reducePrice"  >元</div>
						</div>
					</div>
					<div>
						<label>可参与次数：</label><input type="text" class="easyui-numberspinner" required="required" style="width:120px;" name="joinTimes"  id="joinTimes"  >
					</div>
					<div>
						<input type="hidden" id="memberIds" />
						<label>适用会员：</label>
						<div id="mLev" ></div>
						<!-- <input type="checkbox" name="memLevel" value="1" id="memLevel1" checked="checked" /><label>初级会员</label>
						<input type="checkbox" name="memLevel" value="2" id="memLevel2"  /><label>中级会员</label>
						<input type="checkbox" name="memLevel" value="3" id="memLevel3"  /><label>高级会员</label>
						<input type="checkbox" name="memLevel" value="4" id="memLevel4"  /><label>顶级会员</label>
						<input type="checkbox" name="memLevel" value="5" id="memLevel5"  /><label>骨灰初级</label>
						<input type="checkbox" name="memLevel" value="6" id="memLevel6"  /><label>骨灰高级</label>
						<input type="checkbox" name="memLevel" value="7" id="memLevel7"  /><label>骨灰顶级</label>
						<input type="checkbox" name="memLevel" value="8" id="memLevel8" /><label>菜鸟等级</label> -->
					</div>
					<div>
						<label>使用平台：</label>
						<input type="radio" name="platform" value="1" id="platform1" checked="checked" /><label>全平台</label>
						<input type="radio" name="platform" value="2" id="platform2" /><label>PC端</label>
						<input type="radio" name="platform" value="3" id="platform3" /><label>手机端</label>
					</div>
					<div>
						<label>有效期：</label>
						<input size="10" class="easyui-datebox cal" required="required" maxlength="10" data-options="editable:false,onSelect:onSelect"
									type="text" name="fromTimeStr" id="fromTime">~
						<input size="10" class="easyui-datebox cal" required="true" maxlength="10"  data-options="editable:false,onSelect:onSelect"
									type="text" name="toTimeStr" id="toTime">
					</div>
				</div>
			</div>
			<div style="text-align: center; margin-top: 20px;">
				<button class="btn btn-primary" type="button" id="saleUpSubmit"  onclick="saveActivity()">
					<span><span>保存</span></span>
				</button>
				<button class="btn btn-primary" type="button" id="saleUpSubmit"  onclick="cancle()">
					<span><span>取消</span></span>
				</button>
			</div>
		</form>

	</div>
	
	
</body>
</html>

