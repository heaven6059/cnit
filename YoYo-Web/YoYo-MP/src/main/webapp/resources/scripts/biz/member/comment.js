/**
 * 功能描述：咨询评论>咨询列表JS
 * 作者：王鹏
 * 创建时间：2015-04-22
 *
 */
$(function(){
	$('#consult_list_dataGrid').datagrid({
		url : _path+'/memberComment/getMemberComment',
		columns : [[
		{
			field : 'commentId',
			checkbox:true,
			align : 'center',
			width:"30px"
		} ,{
			field : 'loginName' ,
			title : '评论人',
			halign : 'center',
			width:"200px"
		},
		{
			field : 'storeName' ,
			title : '所属店铺',
			halign : 'center',
			width:"200px",
			sortable : true,
			sortName:"store_name"
		},{
			field : 'title' ,
			title : '评论标题',
			halign : 'center',
			width:"200px",
			sortable : true
		},{
			field : 'productName' ,
			title : '评论商品',
			halign : 'center',
			width:"280px",
			sortable : true
		},{
			field : 'createTime' ,
			title : '评论时间',
			halign : 'center',
			width:"160px",
			formatter: function(value,rows,index){
				if(value){
					var createDate=new Date(value);
    				return createDate.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
				return "";
			},sortable : true
		},{
			field : 'commentStar' ,
			title : '评分',
			align : 'center',
			width:"150px",
			formatter: function(value,rows,index){
				if(value){
					return value+"分";
				}
				return "";
			},sortable : true
		},{
			field : 'display' ,
			title : '是否显示',
			align : 'center',
			width:"150px",
			formatter: function(value,rows,index){
				if(value==1){
					return "是";
				}
				return "否";
			}
		},{
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
		pageSize : 20,
		height:$(window).height()*0.95,
		pageList : [ 20, 50, 100, 200 ],
		pagination : true ,
		rownumbers : true ,
		//fitColumns : true ,
		nowrap : false,
		remoteSort : true ,
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
	
	
	$(".comment-display").on("click",function (){
		var display=$(this).attr("data-display");
		var commentId=$(this).attr("data-commentId");
		var reloadMain=$(this).attr("reload-main");
		var op={
				curObj:$(this),
				commentId:commentId,
				display:display,
				callback:function (){
					if(display==1){
						curObj.html("[关闭显示]");
						curObj.attr("data-display","0");
					}else{
						curObj.html("[显示到商品]");
						curObj.attr("data-display","1");
					}
					if(reloadMain){
						refreshComment();
					}
				}
			}
		openOrCloseAsk(op);
	});
	
	$(".comment-del").click(function (){
		var commentId=$(this).attr("data-commentId");
		var close=$(this).attr("close");
		var curObj=$(this);
		var op={
			commentId:commentId,
			callback:function (){
				curObj.parent().parent().parent().remove();
				if(close){
					$("#commentDetail").dialog('close');
				}
			}
		}
		delAsk(op);
	});
});

function toEdit(commentId){
	if (commentId){
		$("#commentDetail").dialog({
		    width: 805,
		    height: 533,
		    href: _path+'/memberComment/getMemberCommentDetail?commentId='+ commentId,
		    modal: true,
		    closed: true,
		    title:'商品评论',
		    closed:true,
		    modal:true,
		    cache:false
		});
		$('#commentDetail').dialog('open');
	}
}
function clearCommentsSearch(){
	$("#comments_search_form").form('clear');
	commentsSearch();
}

function commentsSearch(){
	var startTime=$("#startTime").datebox('getValue');;
	var endTime=$("#endTime").datebox('getValue');;
	
	if(!compareDate(startTime,endTime)){
		$.messager.alert('信息','开始时间不能大于结束时间！');
		return;
	}
	var param={
		memberName:$("#memberName").val(),
		storeName:$("#storeName").val(),
		title:$("#title").val(),
		productName:$("#productName").val(),
		startTime:startTime,
		endTime:endTime,
		disPlay:$("#comments_search_form input[name='disPlay']:checked").val()
	};
	$('#consult_list_dataGrid').datagrid('load', param);
}

function delAsk(option){
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){ 
	    if (r){
	    	$.ajax( {
	    	    url:'../memberComment/doDelCommentReply',// 跳转到 action    
	    	    data:{commentId:option.commentId},    
	    	    type:'post',    
	    	    cache:false,    
	    	    dataType:'json',    
	    	    success:function(data) {
	    	    	if(data.retCode==000000){
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

function refresh(){
	$('#consult_list_dataGrid').datagrid('reload');
}

function openOrCloseAsk(option){
	$.messager.confirm('确认','您确认想要修改显示标志方式吗？',function(r){
		if(!r)return;
		$.ajax({
		    url:'../memberComment/openOrCloseComment',// 跳转到 action    
		    data:{
		    	commentId:option.commentId,
		    	display:option.display
		    },    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	if(data.retCode==0000){
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
	});
}


function saveComment(){
	if($.trim($("#reply_text").val())==""){
		alert("请输入回复内容");
		return;
	}
	$.ajax({
	    url:'../memberComment/doCommentReply',// 跳转到 action    
	    data:$("#reply_comment").serialize(),    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.retCode==0000){
	    		alert("提交成功.");
	    	}else{
	    		alert("解释信息提交失败");
	    	}
	    	$('#commentDetail').dialog('close');
	     }
	});		
}


function closeComment(){
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

