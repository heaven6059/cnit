/**
 * 功能描述：咨询评论>咨询列表JS
 * 作者：王鹏
 * 创建时间：2015-04-22
 *
 */
$(function(){
	$('#consult_list_dataGrid').datagrid({
		url : _path+'/memberComment/getMemberAskList',
		queryParams:{objectType:$("#object_type").val()},
		columns : [[
		{
			field : 'commentId',
			checkbox:true,
			align : 'center',
			width:"30px"
		} , {
			field : 'goodName' ,
			title : '名称',
			halign : 'center',
			width:"400px"
		} , {
			field : 'time' ,
			title : '时间',
			align : 'center',
			width:"170px",
			formatter: function(value,rows,index){				
    			return new Date(value).format("yyyy-MM-dd hh:mm:ss");
			}
		} , {
			field : 'display' ,
			title : '前台是否显示',
			align : 'center',
			width:"170px",
			formatter: function(value,rows,index){
				if( value == "0" ){
					return "关闭";
				}else if(value == "1" ){
					return "显示";
				}
				return "--";
			}
			
		} , {
			field : 'author' ,
			title : '发表人' ,
			align : 'center',
			width:"170px"	
		} , {
			field : 'storeName' ,
			align : 'center',
			title : '店铺名称',
			width:"250px"	
		} , {
			field : 'editor',
			align : 'center',
			title : '操作',
			width:"45px",
			formatter : function(value, row, index) {
				return '<a href="javascript:toEdit(' + row.commentId + ')">编辑</a>'
			}
		}
		]],
		singleSelect: false,
		selectOnCheck: true,
		checkOnSelect: true,
		toolbar : '#toolbar',
		pageSize : 25,
		height:$(window).height()*0.95,
		pageList : [ 25, 50, 100, 150 ],
		pagination : true ,
		rownumbers : true ,
		//fitColumns : true ,
		nowrap : false,
		remoteSort : false ,
		multiSort : true ,
		ctrlSelect : true ,
		loadFilter : function(data){
			if (data.rows){
				return data;
			} else{
				return data.content;
			}
		}
	});

	$('#delAsk').click(function(){
		var checkedItems = $('#consult_list_dataGrid').datagrid('getChecked');
		if(checkedItems.length==0){
			$.messager.alert('警告','请选择需要删除的记录。');
			return;
		}
		var names = [];
		$.each(checkedItems, function(index, item){
			names.push(item.commentId);
		});
		var op={
			commentId:names.join(",")
		};
		delAsk(op);
	});
	
	$("#displayAsk").click(function (){
		var checkedItems = $('#consult_list_dataGrid').datagrid('getChecked');
		if(checkedItems.length==0){
			$.messager.alert('警告','请选择需要修改显示的记录。');
			return;
		}
		var names = [];
		$.each(checkedItems, function(index, item){
			names.push(item.commentId);
		});
		var op={commentId:names.join(","),display:1}
		openOrCloseAsk(op);
	});
	
	$("#closeAsk").click(function (){
		var checkedItems = $('#consult_list_dataGrid').datagrid('getChecked');
		if(checkedItems.length==0){
			$.messager.alert('警告','请选择需要修改关闭的记录。');
			return;
		}
		var names = [];
		$.each(checkedItems, function(index, item){
			names.push(item.commentId);
		});
		var op={commentId:names.join(","),display:0}
		openOrCloseAsk(op);
	});
	
});

function toEdit(commentId){
	if (commentId){
		$("#commentDetail").dialog({
		    width: 805,
		    height: 533,
		    href: _path+'/memberComment/getMemberAskDetail?commentId='+ commentId,
		    modal: true,
		    closed: true,
		    title:'商品咨询',
		    closed:true,
		    modal:true,
		    cache:false
		});
		$('#commentDetail').dialog('open');
	}
}

function delAsk(option){
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){ 
	    if (r){
	    	$.ajax( {
	    	    url:'../memberComment/doDelAsk',// 跳转到 action    
	    	    data:{commentId:option.commentId},    
	    	    type:'post',    
	    	    cache:false,    
	    	    dataType:'json',    
	    	    success:function(data) {
	    	    	if(data.headObject.retCode==0000){
	    	    		$.messager.alert('信息','删除成功');
	    	    		refresh();
	    	    		if(option.callback){
			    			option.callback();
			    		}
	    	    	}else{
	    	    		$.messager.alert('信息','删除失败');
	    	    	}
	    	     },    
	    	     error : function() {    
	    	          alert("异常！");
	    	     }
	    	});
	    }
	});	
}

function clearConsultSearch(){
	$("#consult_search_form").form('clear');
	consultSearch();
}

function consultSearch(){
	var startTime=$("#startTime").datebox('getValue');;
	var endTime=$("#endTime").datebox('getValue');;
	
	if(!compareDate(startTime,endTime)){
		$.messager.alert('信息','开始时间不能大于结束时间！');
		return;
	}
	
	var param={
		productName:$("#productName").val(),
		storeName:$("#storeName").val(),
		memberName:$("#memberName").val(),
		startTime:startTime,
		endTime:endTime,
		disPlay:$("#consult_search_form input[name='disPlay']:checked").val()
	};
	$('#consult_list_dataGrid').datagrid('load', param);
}

function refresh(){
	$('#consult_list_dataGrid').datagrid('reload');
}

function openOrCloseAsk(option){
	$.messager.confirm('确认','您确认想要修改显示标志方式吗？',function(r){
		if(r){
			$.ajax({
			    url:'../memberComment/openOrCloseAsk',// 跳转到 action    
			    data:{commentId:option.commentId,display:option.display},    
			    type:'post',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	if(data.headObject.retCode==0000){
			    		$.messager.alert('信息','修改成功');
			    		refresh();
			    		if(option.callback){
			    			option.callback();
			    		}
			    	}else{
			    		$.messager.alert('信息','修改失败');
			    	}
			    }
			});
		}
	});
}


function saveCommentAsk(){
	if($.trim($("#reply_text").val())==""){
		alert("请输入回复内容");
		return;
	}
	$.ajax( {    
	    url:'../memberComment/doReplyAsk',// 跳转到 action    
	    data:$("#reply_ask").serialize(),    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.resultObject.retCode==0000){
	    		alert("提交成功.");
	    	}else{
	    		alert("解释信息提交失败");
	    	}
	     },    
	     error : function() {    
	          alert("异常！");    
	     }
	});		
	$('#commentDetail').dialog('close');
}


function closeCommentAsk(){
	 $("#commentDetail").dialog('close');
}

function newUser(){
	$('#dlg').dialog('open').dialog('setTitle' , '添加会员');
	$('#fm').form('clear');
}
function saveUser(){
	formSubmit('fm' , _path+'/member/newMember' , function(){
		$('#dlg').dialog('close'); // close the dialog
		$('#dg').datagrid('reload'); // reload the user data
	} , null);
}

function destroyUser() {	
	var row = $('#dg').datagrid('getSelections');
	if (row == null) {
		alert("请先选择行！");
		return;
	}
	 
	$.each(row, function (i, n) {
	if (i == 0) {
		parm =  n.accountId;
	} else {
		parm +=',' + n.accountId;
	}
	}); 
	$.ajax({
		url : _path+'/member/deleteMember',
		data : {'accountId':parm},	
		type : 'POST',
		datatype:'json',
		success : function(data) {
			if (data.success) {
				$('#dg').datagrid('reload');
			}
		}

	});
}




//立即筛选
function Screening(){
	$('#dg').datagrid('load', biz.serializeObject($('#member_search_form')));
}

/**
 * 切换不同渠道来源的会员列表
 */
function turnChannel(channel){
	$('#dg').datagrid("reload",{channel:channel});
}


function clear(){
	$('#fw').form('clear');
}

