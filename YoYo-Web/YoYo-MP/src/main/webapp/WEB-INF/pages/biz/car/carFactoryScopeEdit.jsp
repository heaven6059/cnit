<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <!-- 添加厂商区域 -->
<form id="add_scopes_form" class="easyui-form" method="post" action="">
	<input type="hidden" name="scopeId" id="scopeId"/>
	<table  style="border-collapse: separate; border-spacing: 15px;">
		<tr > 
			<td align="right">品牌区域名称：</td>
			<td><input type="text" value="${CarFactoryScope.carType}" name="carType" id="carType" class="easyui-textbox easyui-validatebox" data-options="required:true"></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;">
				<input value="${CarFactoryScope.scopeId}" type="hidden" id="scopeId" name="scopeId"/>
				<a id="btn" href="javascript:saveScope(${CarFactoryScope.scopeId});" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
				<a id="btn" href="javascript:closeCarFactoryScope();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>  
			</td>
		</tr>
	</table>
</form>
