<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<title>违规处理</title>
	<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
	<script type="text/javascript" src="${path}/resources/scripts/biz/storeViolation/violation.js"></script>    
</head>
<body>
    <div id="toolbar-violation">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openSaveDialog('add')"> 添加</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteViolation()"> 删除 </a>
        <a href="javascript:;" style="margin-left: 50px;" class="easyui-linkbutton" iconCls="icon-search" onclick="openSeachWindow()">高级筛选</a>
    </div>
    <table id="table-violation"></table>
	
	<!-- <div id="window-add-violation" class="easyui-dialog"  style="width:400px;height:200px;max-width:800px;padding:10px" data-options="
            iconCls:'icon-save',closed:true,
            onResize:function(){
                $(this).dialog('center');
            }">        
    </div> -->
	
	
	
	<!-- 新增汽车厂商-->
    <div id="window-add-violation" class="easyui-window" title="添加"
        closed="true" style="height: 550px; width: 800px;"
        data-options="cache:false,top:50,minimizable:false,iconCls:'icon-save'">

        <form id="form-violation-add" class="easyui-form">
            <table class="add_brand_table">
            	<tr class="hide">
					<td><label>违规处理ID：</label></td>
					<td><input type="text" name="violationId"  /></td>
				</tr>
				<tr>
                    <td align="right"><span style="color: red;">*</span>违规类型：</td>
                    <td>
                    	<select id="combox-violation-cat" style="width: 200px;" name="catId" data-options="required:true"></select>
                    </td>
                </tr>
				<!-- <tr>
					<td align="right"><label><span style="color: red;">*</span>扣分节点：</label></td>
					<td align="left"><input type="text"  name="scoreValue" style="width: 200px;" class="easyui-textbox" data-options="required:true,precision:0,min:0"  />&nbsp;&nbsp;分</td>
				</tr> -->
				<!-- <tr id="tr_parent_violation_cat_id">
					<td align="right"><label>上级分类：</label></td>
					<td align="left"><select id="combox-parent-violation-cat"  name="parentId" style="width: 300px;padding: 3px 5px;height: 28px;">
					</select></td>
				</tr> 
				<tr>
					<td align="right"><label><span style="color: red;">*</span>扣除分数：</label></td>
					<td><input type="text" name="score" style="width: 300px;" class="easyui-numberbox"  data-options="precision:0,max:99999,required:true" /></td>
				</tr> -->
				<tr>
					<td align="right"><label>限制发布商品天数：</label></td>
					<td><input type="text" name="goodsDays" style="width: 200px;" class="easyui-numberbox" data-options="precision:0,min:0" />&nbsp;&nbsp;天</td>
				</tr>
				<tr>
					<td align="right"><label>下架店铺内所有商品天数：</label></td>
					<td><input type="text" name="goodsdownDays" style="width: 200px;" class="easyui-numberbox" data-options="precision:0,min:0" />&nbsp;&nbsp;天</td>
				</tr>
				<!-- ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓暂时隐藏不用,默认值0↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ -->
				<tr style="display:none;">
					<td align="right"><label>商品降权天数：</label></td>
					<td><input type="text" name="newsDays" style="width: 200px;" class="easyui-numberbox" data-options="precision:0,min:0" value="0" />&nbsp;&nbsp;天</td>
				</tr>
				<tr style="display:none;">
					<td align="right"><label>商品降权值：</label></td>
					<td><input type="text" name="newsDaysValue" style="width: 200px;" class="easyui-numberbox" data-options="precision:0,max:100,min:0" value="0" />&nbsp;&nbsp;%</td>
				</tr>
				<!-- ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑暂时隐藏不用↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ -->
				<tr>
					<td align="right"><label>店铺屏蔽天数：</label></td>
					<td><input type="text" name="storeDays" style="width: 200px;" class="easyui-numberbox" data-options="precision:0,min:0" />&nbsp;&nbsp;天</td>
				</tr>
				<tr>
					<td align="right"><label>关闭店铺天数：</label></td>
					<td><input type="text" name="storedownDays" style="width: 200px;" class="easyui-numberbox" data-options="precision:0,min:0" />&nbsp;&nbsp;天</td>
				</tr>
				<!-- ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓暂时隐藏不用,默认值0↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ -->
				<tr style="display:none;">
					<td align="right"><label>限制参加营销活动天数：</label></td>
					<td><input type="text" name="salesDays" style="width: 200px;" class="easyui-numberbox" data-options="precision:0,min:0" value="0" />&nbsp;&nbsp;天</td>
				</tr>
				<!-- ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑暂时隐藏不用↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ -->
				<tr>
					<td align="right"><label>支付违约金：</label></td>
					<td><input type="text" name="earnestMoney" style="width: 200px;" class="easyui-numberbox" data-options="precision:2,min:0" />&nbsp;&nbsp;万元</td>
				</tr>
				<tr>
					<td align="right"><label>备注：</label></td>
					<td><textarea rows="4" cols="26" name="remark"></textarea> </td>
				</tr>
            </table>
        </form>

        <div region="south" border="false" style="text-align: center; height: 30px; line-height: 30px; margin-bottom: 10px;">
            <a class="easyui-linkbutton" id="add-car-factory-btn" icon="icon-save" onclick="saveOrUpdateViolation()">保存</a>
            <a class="easyui-linkbutton" id="update-car_factory-btn" icon="icon-cancel"
                onclick="javascript:closeDailog('window-add-violation');$('#MODALPANEL').hide();" style="margin-left: 15px;">取消</a>
        </div>
    </div>
	
	
	<div id="advance_search_violation" class="easyui-dialog advance_search" title="高级筛选"
        style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
        data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="violation_search_form" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tbody>
		            <tr>
	                    <td align="right"><span style="color: red;">*</span>违规类型：</td>
	                    <td>
	                    	<select id="search-combox-violation-cat" style="width: 120px;" name="catId" data-options="required:true"></select>
	                    </td>
	                </tr>
					<!-- <tr>
						<td align="right"><label><span style="color: red;">*</span>扣分节点：</label></td>
						<td align="left"><input type="text"  name="scoreValue" style="width: 120px;" class="easyui-textbox" data-options="precision:0,min:0" />&nbsp;&nbsp;分</td>
					</tr> -->
					<tr>
						<td align="right"><label>限制发布商品天数：</label></td>
						<td><input type="text" name="goodsDays" style="width: 120px;" class="easyui-numberbox" data-options="precision:0,min:0" />&nbsp;&nbsp;天</td>
					</tr>
					<tr>
						<td align="right"><label>下架店铺内所有商品天数：</label></td>
						<td><input type="text" name="goodsdownDays" style="width: 120px;" class="easyui-numberbox" data-options="precision:0,min:0" />&nbsp;&nbsp;天</td>
					</tr>
					<!-- <tr>
						<td align="right"><label>商品降权天数：</label></td>
						<td><input type="text" name="newsDays" style="width: 120px;" class="easyui-numberbox" data-options="precision:0,min:0" />&nbsp;&nbsp;天</td>
					</tr>
					<tr>
						<td align="right"><label>商品降权值：</label></td>
						<td><input type="text" name="newsDaysValue" style="width: 120px;" class="easyui-numberbox" data-options="precision:0,max:100,min:0" />&nbsp;&nbsp;%</td>
					</tr> -->
					<tr>
						<td align="right"><label>店铺屏蔽天数：</label></td>
						<td><input type="text" name="storeDays" style="width: 120px;" class="easyui-numberbox" data-options="precision:0,min:0" />&nbsp;&nbsp;天</td>
					</tr>
					<tr>
						<td align="right"><label>关闭店铺天数：</label></td>
						<td><input type="text" name="storedownDays" style="width: 120px;" class="easyui-numberbox" data-options="precision:0,min:0" />&nbsp;&nbsp;天</td>
					</tr>
					<!-- <tr>
						<td align="right"><label>限制参加营销活动天数：</label></td>
						<td><input type="text" name="salesDays" style="width: 120px;" class="easyui-numberbox" data-options="precision:0,min:0" />&nbsp;&nbsp;天</td>
					</tr> -->
					<tr>
						<td align="right"><label><span>支付违约金：</span><select name="earnestMoneySearchType"><option value="gt">大于</option><option value="ge">大于等于</option><option value="eq">等于</option><option value="le">小于等于</option><option value="lt">小于</option><option value="bt">介于</option></select></label></td>
						<td>
							<div class="nbt"><input type="text" name="earnestMoney" style="width: 120px;" class="easyui-numberbox"  data-options="precision:2,min:0" />&nbsp;&nbsp;万元</div>
							<div class="bt" style="display:none;">
								大于等于：<input type="text" name="earnestMoney1" style="width: 60px;" class="easyui-numberbox"  data-options="precision:2,min:0" />&nbsp;&nbsp;万元
								<br/>
								小于等于：<input type="text" name="earnestMoney2" style="width: 60px;" class="easyui-numberbox"  data-options="precision:2,min:0" />&nbsp;&nbsp;万元
							</div>
						</td>
					</tr>
		            
		            
		            
			</table>
		</form>
	</div>
    <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6"
            iconCls="icon-ok" onclick="advanceQuery('table-violation','violation_search_form')" style="width: 90px">立即筛选</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"
            iconCls="icon-cancel" onclick="clearCondition('violation_search_form')" style="width: 90px">清除条件</a>
    </div>
	<div id="MODALPANEL" style="position: absolute; width: 100%; height: 100%; top: 0px; left: 0px; z-index: 6000; opacity: 0.4; background: rgb(51, 51, 51);display:none;"></div>
</body>
</html>
