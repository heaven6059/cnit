/**
 * 功能描述： 作者：李明 创建时间：2015-3-4
 * 
 */
$(function() {
	var itemss=[{"value":"100","text":"买家账户"},{"value":"110","text":"卖家账户"},{"value":"120","text":"店员账户"}];
	var accountStatus=[{"value":"0","text":"停用"},{"value":"1","text":"启用"}];
	var systemType=[{"value":"10","text":"WEB前端"},{"value":"11","text":"WEB管理平台"},{"value":"20","text":"Android平台"},{"value":"21","text":"IOS平台"}];
	$('#dg').datagrid({
		url : _path + '/member/findMemberList',
		pageSize: 25,
	    pageList: [25,50,100,150],
		columns : [[
		{field : 'memberId',title : '会员编号',hidden : true},
		{field : 'accountId',title : '会员帐号',hidden : true},
		{field : 'loginName',title : '用户名',halign:'center',width:80,options : {queryname : 'p.loginNameQ',querytype : 'like'}}, 
		{field : 'accountType',title : '账户类型',halign:'center',width:50,options : {queryname : 'p.accountType',querytype : 'equal',items :itemss},formatter: function(value,row,index){
				if (value && value.indexOf("10")==0){
					return '买家账户';
				}else if(value && value.indexOf("11")==0){
					return '卖家账户';
				}else if(value && value.indexOf("12")==0){
					return '店员账户';
				}else {
					return value;
				}
			}
		}, 
		{field : 'accountStatus',title : '账户状态',halign:'center',width:30,options : {querytype : 'equal',items :accountStatus},formatter : function(value, rows, index) {
				if (value &&  value == "0") {return "停用";} 
				else if (value == "1") {return "启用";}
			}
		},
		{field : 'memberLvId',	title : '会员等级',width:30,halign:'center'}, 
		{field : 'name',title : '会员姓名',halign:'center',width:80,options : {querytype : 'like'}}, 
		{field : 'nickName',	title : '昵称',halign:'center',width:50,options : {querytype : 'like'}}, 
		{field : 't.email',	title : '电子邮箱',halign:'center',width:70,options : {queryname : 't.emailQ',querytype : 'like'},formatter: function(value,row,index){
			return row.email;
		}}, 
		{field : 'mobile',title : '手机号码',halign:'center',width:50,options : {querytype : 'like'}}, 
		{field : 'idcard',title : '身份证号码',halign:'center',width:70,options : {querytype : 'like'}}, 
		{field : 'advance',	title : '预存款',width:30,halign:'center',sortable : true,options : {querytype : 'range'}}, 
		{field : 'pointSummation',	title : '积分',halign:'center',width:30,options : {querytype : 'range'}}, 
		{field : 'orderNum',	title : '订单数',halign:'center',width:30,options : {querytype : 'range'}}, 
		{field : 'p.regTime',title : '注册时间',halign:'center',width:80,options : {querytype : 'range',clazz : 'easyui-datetimebox'},formatter: function(value,row,index){
			return row.regTime;
		}},  
		{field : 'source',title : '注册渠道',halign:'center',width:50,options : {querytype : 'equal',items :systemType},formatter : function(value, rows, index) {
				if (value &&  value == "10") {
					return "WEB前端";
				} else if (value &&  value == "11") {
					return "WEB管理平台";
				} else if (value &&  value == "20") {
					return "Android平台";
				} else if (value &&  value == "21") {
					return "IOS平台";
				}
			}
		},
		{field : 'editor',title : '操作',halign:'center',width:30,formatter : function(value, row, index) {
				return '<a href="javascript:editMember(' + row.accountId + ')" mouseover="javascript:alert(1);">编辑</a>'
			}
		}]],
		toolbar : '#toolbar',
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		singleSelect : true,
		remoteSort : true,
		multiSort : true,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
	$('#dg').datagrid({
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95)
	});
	$('#member-ads-button').advanceSeacher({
		aimDataGrid : 'dg',
		renderTo : 'table-member-advance-searcher',
		exceptField : ['editor']
	});
});

function editMember(accountId) {
	// $('#dt').dialog('title', '编辑会员');
	$('#dt').dialog('refresh', _path + '/member/memberDetail?accountId=' + accountId);
	$('#dt').dialog('open');
}
function newUser() {
	$('#dlg').dialog('open').dialog('setTitle', '添加会员');
	$('#fm').form('clear');
}
function saveUser() {
	if(!$("#fm").form('validate')){
		return false;
	}
	commonAjax(yoyo.mpUrl + "/member/newMember" , $('#fm').serializeArray() , saveUserSuc , function(data){
		alert(data.retMsg);
	});
}

function saveUserSuc(){
	$('#dlg').dialog('close'); // close the dialog
	$('#dg').datagrid('reload'); // reload the user data
}

function destroyUser() {
	var row = $('#dg').datagrid('getSelections');
	if (row == null) {
		alert("请先选择行！");
		return;
	}

	$.each(row, function(i, n) {
		if (i == 0) {
			parm = n.accountId;
		} else {
			parm += ',' + n.accountId;
		}
	});
	$.ajax({
		url : _path + '/member/deleteMember',
		data : {
			'accountId' : parm
		},
		type : 'POST',
		datatype : 'json',
		success : function(data) {
			if (data.success) {
				$('#dg').datagrid('reload');
			}
		}

	});
}
// 立即筛选
function Screening() {
	$('#dg').datagrid('load', biz.serializeObject($('#member_search_form')));
}
/**
 * 切换不同渠道来源的会员列表
 */
function turnChannel(channel) {
	var memberListUrl = _path + '/member/findMemberList?';
	if (channel) {
		$('#dg').datagrid('options').url = memberListUrl + 'channel=' + channel
	}
	$('#dg').datagrid("reload");
}
function clear() {
	$('#fw').form('clear');
}
