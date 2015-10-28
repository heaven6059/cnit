<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>店铺等级</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<style type="text/css">
.search_input{width:80px;}
</style>
</head>
<body>
    <script type="text/javascript"  src="${path}/resources/scripts/biz/shop/shopGradeIndex.js"></script>
    
	<div id="toolbar-shopGrade">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newshopGradeAndValue()"> 添加等级</a> <a class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="deleteShopGrade()"> 删除 </a>
			<a href="#"   style="margin-left: 50px;" class="easyui-linkbutton"
            iconCls="icon-search" onclick="openAdvaceQuery('advance_search_shopGrade')">高级筛选</a>
	</div>
	<table id="table-shopGrade" ></table>


	<!-- 新增店铺等级-->
	<div id="window-add-shopGrade" class="easyui-window" title="添加等级"
		closed="true" style="height: 700px; width: 800px;"
		data-options="cache:false,top:50,minimizable:false,iconCls:'icon-save',modal:true">

		<form id="form-shopGrade-add" >
			<input type="hidden" name="gradeId">
			<table class="add_brand_table">
				<tr>
					<td align="right"><span style="color: red;">*</span>店铺类型：</td>
					<td><select name="issueType" style="width: 90px;" class="tab_input "   id="issueType">
						<option value="1">集团</option>
						<option value="0">单店</option>
					</select></td>
				</tr>
				<tr>
					<td align="right"><span style="color: red;">*</span>等级名称：</td>
					<td><input type='text' name="gradeName"
						class="tab_input easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td align="right"><span style="color: red;">*</span>允许开店个数：</td>
					<td><input type='text' name="shopNums" style="width: 90px;" id="shopNums"
						class="tab_input easyui-numberbox" data-options="required:true"/></td>
				</tr>
				<tr>
					<td align="right">允许发布商品数：</td>
					<td><input type='text' name="goodsNum" style="width: 90px;"
						class="tab_input easyui-numberbox" /></td>
				</tr>
				<tr>
					<td align="right">允许发行优惠券数：</td>
					<td><input type='text' name="couponsNum" style="width: 90px;" class="tab_input easyui-numberbox" /></td>
				</tr>
				<tr>
					<td align="right">可选模板套数：</td>
					<td><input type='text' name="themeNum" style="width: 90px;"
						class="tab_input easyui-numberbox" data-options="required:false"/></td>
				</tr>
			
				<tr>
					<td align="right">收费标准：</td>
					<td><input type='text' name="gradeMoney" style="width: 90px;" class="tab_input easyui-numberbox" /></td>
				</tr>
				<tr>
					<td align="right"><span style="color: red;">*</span>保证金标准：</td>
					<td><input type='text' name="issueMoney" style="width: 90px;" class="tab_input  easyui-validatebox " data-options="required:true" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
				</tr>
				
				<tr>
					<td align="right"><span style="color: red;">*</span>所需经验值：</td>
					<td><input type='text' name="experience" style="width: 90px;" class="tab_input easyui-validatebox" data-options="required:true" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
				</tr>
				<tr>
					<td align="right">是否为该店铺类型默认等级：</td>
					<td>
						<input type='radio' name="defaultLv" id="defaultLv" value="1" checked="checked">
						<label for="defaultLv">是</label>
					<input type='radio' name="defaultLv" id="l2" value="0" style="margin-left:10px;"><label for="l2">否</label> 如果选择“是”，注册商店成功时，初始等级为当前等级</td>
				</tr>
               
               
				<tr>
					<td align="right">店铺备注：</td>
					<td><textarea cols="50" rows="6" name="remark"></textarea></td>
				</tr>


			</table>
		</form>

		<div region="south" border="false"
			style="text-align: center; height: 30px; line-height: 30px; margin-bottom: 10px;">
			<a class="easyui-linkbutton" id="add-shopGrade-btn" icon="icon-save"
				onclick="saveShopGrade()" style="display: none;">保存</a> <a
				class="easyui-linkbutton" id="update-shopGrade-btn" icon="icon-save"
				onclick="updateShopGrade()" style="display: none;">保存</a> <a
				class="easyui-linkbutton" id="update-shopGrade-btn" icon="icon-cancel"
				onclick="javascript:closeDailog('window-add-shopGrade');" style="margin-left: 15px;">取消</a>
		</div>

	</div>

   <div id="advance_search_shopGrade" class="easyui-dialog advance_search" title="高级筛选"
        style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
        data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="shopGrade_search_form" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tr>
					<td align="right"><span>等级名称: </span></td>
					<td><input id="gradeName" name="gradeName"
						class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
					<td align="right"><span>开店个数: </span></td>
					<td><input  name="shopNums" class="easyui-numberbox search_class search_input" />到
					<input  name="maxShopNums" class="easyui-numberbox search_class search_input" />
					</td>
				</tr>
				<tr>
					<td align="right"><span>所需经验值: </span></td>
					<td><input  name="experience" class="easyui-numberbox search_class search_input" />到
					<input  name="maxEexperience" class="easyui-numberbox search_class search_input" />
					</td>
				</tr>
				<tr>
					<td align="right"><span>是否默认: </span></td>
					<td><input type='radio' name="defaultLv" id="ll1" value="1" checked="checked">
						<label for="l11">是</label>
					<input type='radio' name="defaultLv" id="l21" value="0" style="margin-left:10px;"><label for="l21">否</label></td>
				</tr>
				<tr>
					<td align="right"><span>保证金额: </span></td>
					<td><input name="issueMoney" class="easyui-numberbox search_class search_input" />
					到
					<input  name="maxIssueMoney" class="easyui-numberbox search_class search_input" />
					</td>
				</tr>
				<tr>
					<td align="right"><span>允许发布商品数: </span></td>
					<td><input name="goodsNum"	class="easyui-numberbox search_class search_input" />
						到
					<input  name="maxGoodsNum" class="easyui-numberbox search_class search_input" /></td>
				</tr>
				<tr>
					<td align="right"><span>允许发行优惠数: </span></td>
					<td><input  name="couponsNum" class="easyui-numberbox search_class search_input" />到
					<input  name="maxCouponsNum" class="easyui-numberbox search_class search_input" /></td>
				</tr>
				<tr>
					<td align="right"><span>可选模板套数: </span></td>
					<td><input  name="themeNum" class="easyui-numberbox search_class search_input" />到
					<input  name="maxThemeNum" class="easyui-numberbox search_class search_input" /></td>
				</tr>
				<tr>
					<td align="right"><span>收费标准: </span></td>
					<td><input name="gradeMoney"	class="easyui-numberbox search_class search_input" />到
					<input  name="maxGradeMoney" class="easyui-numberbox search_class search_input" /></td>
				</tr>
				<tr>
					<td align="right"><span>店铺类型: </span></td>
					<td><select name="issueType" style="width: 90px;" class="tab_input easyui-combobox" >
						<option value="1">集团</option>
						<option value="0">单店</option>
					</select></td>
				</tr>
			</table>
		</form>
	</div>
    <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6"
            iconCls="icon-ok" onclick="advanceQuery('table-shopGrade','shopGrade_search_form')" style="width: 90px">立即筛选</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"
            iconCls="icon-cancel" onclick="search_clear('shopGrade_search_form','table-shopGrade')" style="width: 90px">清除条件</a>
    </div>
	
</body>
</html>
