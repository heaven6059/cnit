var type='';
$(function (){
	type=$("#type").val();
	$('#redBelt_list_dataGrid').datagrid({
		url : _path+'/redBelt/loadRedBeltList',
		columns : [[
		{field : 'target' ,align : 'center',halign: 'center',title : '发放对象',width:150,formatter: function(value,rows,index){
			if(value=='1'){
    			return '买家';
			}else if(value=='2'){
				return '卖家';
			}else if(value=='0'){
				return '买家、卖家';
			}else{
				return '未知';
			}
		}},
		{field : 'nums' ,align : 'center',halign: 'center',title : '红包个数',width:100},
		{field : 'totalAmount' ,align : 'center',halign: 'center',title : '红包总金额',width:150},
		{field : 'status' ,align : 'center',halign: 'center',title : '状态',width:100,formatter: function(value,rows,index){
			if(value=='1'){
    			return '已发放';
			}else if(value=='0'){
				return '未发放';
			}else{
				return '未知';
			}
		}},
		{field : 'rule' ,align : 'center',halign: 'center',title : '发放规则',width:150,formatter: function(value,rows,index){
			if(value=='1'){
    			return '平均分配';
			}else if(value=='2'){
				return '随机分配';
			}else{
				return '未知';
			}
		}},
		{field : 'startTime' ,align : 'center',halign: 'center',width:200,title : '开始时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);		
	    			return time.format("yyyy-MM-dd hh:mm:ss");
				}
			}
		},
		{field : 'endTime' ,align : 'center',halign: 'center',width:200,title : '结束时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);		
	    			return time.format("yyyy-MM-dd hh:mm:ss");
				}
			}
		},
		{field : 'opt' ,align : 'center',halign: 'center',width:200,title : '操作',
			formatter: function(value,rows,index){
				var rhtml="<a href='javascript:viewRedBelt("+JSON.stringify(rows)+")' >详情</a>";
				if(rows.status=='0'){
					rhtml+="<a href='javascript:deleteRedBelt("+rows.redbeltId+")' >删除</a>";
					rhtml+="<a  href='javascript:editRedBelt("+JSON.stringify(rows)+")'>编辑</a>";
				}
				return rhtml;
			}
		}
		]],
		toolbar : '#toolbar',
		pagination : true,
		rownumbers : true,
		autoRowHeight:true,
		singleSelect : true,
		selectOnCheck : false,
		checkOnSelect : false,
		remoteSort : true,
		multiSort : true,
		nowrap : false,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageSize : 25,
		pageList : [ 25, 50, 100, 150 ],
		loadFilter : function(data){
			if (data.content) {
				return data.content;
			} else {
				return {
					total : 0,
					rows : []
				};
			}
		}
	});
	
	$("#addScoreBuy").click(function (){
		$("#scoreBuyDialog").dialog({
		    width: 805,
		    height: 533,
		    href: _path+'/redBelt/toRedBelt',
		    modal: true,
		    closed: true,
		    title:'发红包',
		    closed:true,
		    cache:false,
		    draggable:false
		});
		$('#scoreBuyDialog').dialog('open');
		//findMemberLevel();
	});
});

function editRedBelt(row){
	$("#scoreBuyDialog").dialog({
	    width: 805,
	    height: 533,
	    href: _path+'/redBelt/toRedBelt?op=edit&id='+row.redbeltId,
	    modal: true,
	    closed: true,
	    title:'编辑红包',
	    closed:true,
	    cache:false,
	    draggable:false
	});
	$('#scoreBuyDialog').dialog('open');
	//findMemberLevel();
}
function viewRedBelt(row){
	$("#scoreBuyDialog").dialog({
	    width: 805,
	    height: 533,
	    href: _path+'/redBelt/toDetailList?id='+row.redbeltId,
	    modal: true,
	    closed: true,
	    title:'详情',
	    closed:true,
	    cache:false,
	    draggable:false
	});
	$('#scoreBuyDialog').dialog('open');
	//findMemberLevel();
}

//逻辑删除活动 
function deleteRedBelt(id){
//	var selectRows = $("#redBelt_list_dataGrid").datagrid('getChecked');
//	if(selectRows.length==0){
//		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
//		return false;
//	}
//	var ids =[];
//	$.each(selectRows,function(i,v){
//		if(v.status=='1'){
//			$.messager.alert('提示', '已发放的红包不能删除!', 'error');
//			return false;
//		}
//		ids.push(v.actId);
//	});
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/redBelt/deleteRedBelt',
		    	    type:"post",
		    	    data:{"id":id},
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			alert("删除成功！");
			    			$('#redBelt_list_dataGrid').datagrid('reload', {});
			    		}else if (_data.head.retCode == '000007'){
			    			alert("红包状态为【已发放】不能删除！");
			    		}else{
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    }
		}
	);
}
