/**
 * 功能描述：咨询列表的相关函数
 * 作者：xiaox
 * 创建时间：2015-3-4
 *
 */
function initDataGrid(){
	$('#consult_list_dataGrid').datagrid({
		url : _path+'/member/getMemberList' ,
		columns : [[{
			field : 'check' ,
			title : '查看',
			formatter: function(value,rows,index){
				return '<span color="blue">查看</span>';
			}
		} , {
			field : 'goodName' ,
			title : '名称'
		} , {
			field : 'replyName' ,
			title : '最后回复人'
		} , {
			field : 'IP' ,
			title : 'IP'
		} , {
			field : 'lastreplyTime' ,
			title : '时间'
		} , {
			field : 'display' ,
			title : '前台是否显示' ,
		} , {
			field : 'author' ,
			title : '发表人'
		} , {
			field : 'contact' ,
			title : '联系方式'
		} , {
			field : 'comment' ,
			title : '内容'
		} , {
			field : 'storeName' ,
			title : '店铺名称' ,
			sortable : true
		} ]] ,
		toolbar : '#toolbar' ,
		pagination : true ,
		rownumbers : true ,
		fitColumns : true ,
		singleSelect : false ,
		remoteSort : false ,
		multiSort : true ,
		ctrlSelect : true ,
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
				$('#dt').dialog('refresh' , _path+'/member/memberDetail?accountId=' + record.accountId);
				$('#dt').dialog('open');
			}
		}
	});
}

