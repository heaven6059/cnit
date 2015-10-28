var type='';
$(function (){
	type=$("#type").val();
	$('#score_list_dataGrid').datagrid({
		url : _path+'/scoreBuyActivity/loadScoreBuyActivity?type='+type+'',
		queryParams:{objectType:$("#object_type").val()},
		columns : [[
		{field:'ck',checkbox:"true"},
		{field : 'name' ,align : 'center',halign: 'center',title : '活动名称',width:200}, 
		{field : 'description' ,align : 'center',halign: 'center',title : '活动描述',width:300},
		{field : 'businessType',align : 'center',halign: 'center',title : '经营范围',width:300,formatter: function(value,rows,index){
		if(value){
			var typeArry = [];
			typeArry = value.split(",");
			var data = '';
			$.each(typeArry, function(i, n) {
				if(n==25){ //选择指定值
					data += '整车,';
				}else if(n==28){
					data += '保养,';
				}else if(n==81){
					data += '配件,';
				}else if(n==88917){
					data += '精品,';
				}
			});
			return data;
		 }
		}
		} ,
		{field : 'startTime' ,align : 'center',width:300,halign: 'center',title : '活动开始时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);		
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		} , 
		{field : 'endTime' ,align : 'center',width:300,halign: 'center',title : '活动结束时间',
			formatter: function(value,rows,index){
				if(value){
					var createDate=new Date();					
	    			createDate.setTime(value);	
	    			return createDate.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		} , 
		{field : 'applyStartTime' ,align : 'center',halign: 'center',width:300,title : '申请开始时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);		
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		} ,
		{field : 'applyEndTime' ,align : 'center',width:300,halign: 'center',title : '申请结束时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);		
	    			return time.format("yyyy-MM-dd hh:mm");//引用YoYo-MP中common.js
				}
			}
		} , 
		{field : 'limitNum' ,align : 'center',width:100,halign: 'center',title : '最低库存限制',hidden:"true"} , 
		{field : 'actOpen' ,align : 'center',width:100,halign: 'center',title : '是否开启' ,
			formatter: function(value,rows,index){
				if(value==1){
					return "开启";
				}
				return "关闭";
			}
		},
		{field : 'editor',halign: 'center',width:300,title : '操作',align : 'center',
			formatter : function(value, row) {
				var html = '';
				if(row.actOpen!=1) {
					html =  "<a  href='javascript:editActivity("+JSON.stringify(row)+")'>编辑</a>";
					html += "<a  href='javascript:optActivity("+row.actId+",1)'>开启</a>";
				}else{
					html =  "<a  href='javascript:editActivity("+JSON.stringify(row)+")'>查看详情</a>";
					html += "<a  href='javascript:optActivity("+row.actId+",0)'>关闭</a>";
				}
				return html;
			}
		}]],
		toolbar : '#toolbar',
		pagination : true ,
		pagePosition : 'bottom',
		fitColumns : true ,
		rownumbers : true ,
		pageSize : 25,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageList : [ 25, 50, 100, 150 ],
		singleSelect: false,
		selectOnCheck:false,
		checkOnSelect: false,
		remoteSort : false ,
		multiSort : true ,
		ctrlSelect : true ,
		loadFilter : function(data){
			if(type==4 || type==5){
				$('#score_list_dataGrid').datagrid('hideColumn', 'applyStartTime'); 
				$('#score_list_dataGrid').datagrid('hideColumn', 'applyEndTime'); 
			}
			if(type==2){
				$('#score_list_dataGrid').datagrid('showColumn', 'limitNum'); 
			}
			if (data.rows){
				return data;
			} else{
				return data.content;
			}
		}
		
	});

	
	$("#addScoreBuy").click(function (){
		$("#scoreBuyDialog").dialog({
		    width: 805,
		    height: 533,
		    href: _path+'/scoreBuyActivity/toScoreBuyActivity?type='+type+'',
		    modal: true,
		    closed: true,
		    title:'积分换购',
		    closed:true,
		    cache:false,
		    draggable:false
		});
		$('#scoreBuyDialog').dialog('open');
	});
});


function editActivity(row){
	$("#scoreBuyDialog").dialog({
	    width: 805,
	    height: 533,
	    href: _path+'/scoreBuyActivity/toScoreBuyActivity?type='+type+'&op=edit&id='+row.actId,
	    modal: true,
	    closed: true,
	    title:'积分换购',
	    closed:true,
	    cache:false,
	    draggable:false
	});
	$('#scoreBuyDialog').dialog('open');
}

function optActivity(actId,opt){
	$.ajax({
	    url:_path + '/scoreBuyActivity/optActivity',
	    type:"post",
	    data:{"actId":actId,"actOpen":opt},
	    dataType:"json",
	    success:function(_data){
	    	if (_data.head.retCode == '000000') {
    			alert("操作成功！");
    			$('#score_list_dataGrid').datagrid('reload', {});
    		}else{
    			alert("操作失败！");
    		}
	    }
	});
}

//逻辑删除活动 
function deleteActivity(){
	var selectRows = $("#score_list_dataGrid").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var ids =[];
	$.each(selectRows,function(i,v){
		if(v.actOpen=='1'){
			$.messager.alert('提示', '活动状态为【开启】不能删除!', 'error');
			return false;
		}
		ids.push(v.actId);
	});
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/scoreBuyActivity/deleteBuyActivity',
		    	    type:"post",
		    	    data:{"ids":ids.join(",")},
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			alert("删除成功！");
			    			$('#score_list_dataGrid').datagrid('reload', {});
			    		}else if (_data.head.retCode == '000007'){
			    			alert("活动状态为【开启】不能删除！");
			    		}else{
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
}
