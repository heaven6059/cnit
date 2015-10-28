/**
 * 功能描述：
 * 作者：李明
 * 创建时间：2015-3-4
 *
 */
$(function(){
	$('#table-memberLevel').datagrid({
		url : _path + '/memberLevel/memberLevels' ,
		pageSize: 20,
	    pageList: [20,50,100,200],
//	    height:550,
		columns : [[
		   {field : 'memberLvId' ,	title : '等级编号',halign:'center'} , 
		   {field : 'name' ,title : '等级名称',halign:'center'} , 
		   {field : 'type' ,halign:'center',title : '等级类型'},
		   {field : 'isDefault' ,halign:'center',title : '是否默认'},
		   {field : 'discountRate' ,halign:'center',title : '会员折扣率'},
		   {field : 'point' ,halign:'center',title : '所需积分'},
		   {field : 'experience' ,halign:'center',title : '所需经验值'},
		   {field : 'status' ,halign:'center',title : '等级状态'}
	   ]] ,
		toolbar : '#toolbar-memberLevel' ,
		pagination : false ,
		rownumbers : true ,
		fitColumns : true ,
		singleSelect : true ,
		remoteSort : false ,
		multiSort : true ,
		loadFilter : function(data){
			if (data.rows){
				return data;
			} else{
				return data.content;
			}
		} ,
		onDblClickCell : function(index , field , value){
			var record = $(this).datagrid('getSelected');
			if (record){
				$('#dialog-memberLevel').dialog('open');
			}
		}
	});
	$('#table-memberLevel').datagrid({
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95)
	});
});

/**
 * 方法描述：新增会员等级
 * 作者：李明
 * 创建时间：2015-3-4
 */
function newMemberLevel(){
	$('#fm').form('clear');
	$('#dialog-memberLevel').dialog('open').dialog('setTitle' , '添加会员等级');
}
/**
 * 方法描述：将修改或新增信息保存到数据库
 * 作者：李明
 * 创建时间：2015-3-4
 * @param _flag
 */
function savaOrUpdateMemberLevel(){
	formSubmit('fm' , _path + '/memberLevel/newMemberLevel' , function(){
		$('#dialog-memberLevel').dialog('close'); // close the dialog
		$('#table-memberLevel').datagrid('reload'); // reload the user data
	} , null);
}
/**
 * 方法描述：逻辑删除等级
 * 作者：李明
 * 创建时间：2015-3-11
 */
function deleteMemberLevel(){
	var row = $('#table-memberLevel').datagrid('getSelected');
	if (row){
		$.messager.confirm('Confirm' , 'Are you sure you want to destroy this user?' , function(r){
			if (r){
				$.post(_path + '/memberLevel/deleteMemberLevel' , {
					memberLvId : row.memberLvId
				} , function(result){
					if (result.retCode != '000000'){
						easyuiMsg('错误',result.retMsg);
					} else{
						$('#table-memberLevel').datagrid('reload');
						easyuiMsg('成功',result.retMsg);
					}
				} , 'json');
			}
		});
	}
}