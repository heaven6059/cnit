$(function (){
	var type=$("#type").val();
	var goodsListSrc = _path + '/scoreBuyApply/loadScoreBuyApplyList?type='+type+'&';
	// 切换选项卡
	$('#tabs-goods').tabs({
		'onSelect' : function(title, index) {
			if (title == '待审核') {
				$('#score_list_dataGrid').datagrid('options').url = goodsListSrc + 'status=1';
			} else if (title == '审核通过') {
				$('#score_list_dataGrid').datagrid('options').url = goodsListSrc + 'status=2';
			}else if (title == '审核未通过') {
				$('#score_list_dataGrid').datagrid('options').url = goodsListSrc + 'status=3';
			}else if (title == '全部') {
				$('#score_list_dataGrid').datagrid('options').url = _path + '/scoreBuyApply/loadScoreBuyApplyList?type='+type+'';
			}
			$('#score_list_dataGrid').datagrid('reload');
		}
	});
	
	$('#score_list_dataGrid').datagrid({
		url : _path+'/scoreBuyApply/loadScoreBuyApplyList?type='+type+'',
		queryParams:{objectType:$("#object_type").val()},
		columns : [[
			{field:'ck',checkbox:"true"},
			{field : 'actName' ,align : 'center',halign: 'center',title : '所属活动',width:200}, 
			{field : 'goodsName' ,align : 'center',halign: 'center',title : '商品名称',width:250},
			{field : 'catName',align : 'center',halign: 'center',title : '商品分类',width:150} ,
			{field : 'storeName' ,align : 'center',halign: 'center',title : '店铺名称',width:100} , 
			{field : 'ismemlv' ,align : 'center',halign: 'center',title : '是否开启会员等级积分',width:100},
			{field : 'status' ,align : 'center',halign: 'center',title : '审核状态',width:80,formatter : function(value) {
				//状态:1=>待审核,2=>审核通过,3=>审核不通过
				if(value=='1'){
					return '待审核';
				}else if(value=='2'){
					return '审核通过';
				}else if(value=='3'){
					return '审核未通过';
				}
			}} , 
			{field : 'remainnums' ,align : 'center',halign: 'center',title : '剩余库存',width:100} ,
			{field : 'nums' ,align : 'center',halign: 'center',title : '参与库存' ,width:80}, 
			{field : 'price' ,align : 'center',halign: 'center',title : '参与价格' ,width:80}, 
			{field : 'memName' ,align : 'center',halign: 'center',title : '申请商家' ,width:100}, 
			{field : 'score' ,align : 'center',halign: 'center',title : '积分',width:80 }, 
			{field : 'lastPrice' ,align : 'center',align: 'center',title : '最终活动价格',width:80},
			{field : 'personlimit' ,align : 'center',halign: 'center',title : '每人限购' ,width:80},
			{field : 'remark' ,align : 'center',halign: 'center',title : '备注',width:200 }
		]],
		toolbar : '#toolbar',
		pagination : true ,
		pagePosition : 'bottom',
		pagination : true,
		rownumbers : true,
		autoRowHeight:true,
		pageSize: 25,
	    pageList: [25,50,100,150],
		singleSelect : true,
		selectOnCheck : false,
		checkOnSelect : false,
		remoteSort : true,
		multiSort : true,
		nowrap : false,
		loadFilter : function(data){
			if (data.rows){
				return data;
			} else{
				return data.content;
			}
		},
		view: detailview,
		detailFormatter:function(index,row){
			return '<div class="ddv" ></div>';
		},
		onExpandRow: function(index,row){
			var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
			ddv.panel({
				border:false,
				cache:false,
				content : detailContent(row),
				onLoad:function(){
					$("#score_list_dataGrid").datagrid('fixDetailRowHeight',index);
				}
			});
			$("#score_list_dataGrid").datagrid('fixDetailRowHeight',index);
		}
	});
	$('#score_list_dataGrid').datagrid({
		height : ($(window).height() * 0.95)
	});
	
	$("#addScoreBuy").click(function (){
		$("#scoreBuyDialog").dialog({
		    width: 805,
		    height: 533,
		    href: _path+'/scoreBuyActivity/toScoreBuyActivity',
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

function detailContent(row){
	var html=[];
	html.push('<div style="border: 1px solid #d9d9d9;padding:1px;"><h5>活动信息</h5>');
	html.push('<table width="100%" border="0" cellspacing="0" cellpadding="0" class="gridlist">');
	html.push('<thead class="table-thead"><tr><th style="width:100px;">活动名称</th><th style="width:100px;">开始时间</th><th style="width:100px;">结束时间</th><th style="width:100px;">开启状态</th></tr></thead>');
	var actOpen="";
	if(row.actOpen=='0'){
		actOpen='开启';
	}else{
		actOpen='关闭';
	}
	html.push('<tbody class="table-tbody"><tr><td>'+row.actName+'</td><td class="textleft">'+new Date(row.startTime).format("yyyy-MM-dd hh:mm:ss")+'</td><td class="textleft">'+new Date(row.endTime).format("yyyy-MM-dd hh:mm:ss")+'</td><td>'+actOpen+'</td></tr></tbody>');
	html.push('</table></div>');
	
	var status='';
	if(row.status=='1'){
		status= '待审核';
	}else if(row.status=='2'){
		status= '审核通过';
	}else if(row.status=='3'){
		status= '审核未通过';
	}
	html.push('<div style="border: 1px solid #d9d9d9;"><h5>申请信息</h5>');
	html.push('<table width="100%" border="0" cellspacing="0" cellpadding="0" class="gridlist">');
	html.push('<thead class="table-thead"><tr><th style="width:100px;">申请商品</th><th style="width:100px;">参加活动商品数量</th><th style="width:100px;">每人限购</th><th style="width:100px;">申请状态</th></tr></thead>');
	html.push('<tbody class="table-tbody"><tr><td>'+row.goodsName+'</td><td class="textleft">'+row.nums+'</td><td class="textleft">'+row.personlimit+'</td><td>'+status+'</td></tr></tbody>');
	html.push('</table></div>');
	if(row.status=='1'){
		html.push('<div class="table-action"><button class="btn" id="btn_check" type="button" onclick="check(1,'+row.id+');"><span><span>审核通过</span></span></button> &nbsp;&nbsp;&nbsp;&nbsp;');
		html.push('<button class="btn" id="btn_nocheck" type="button" onclick="check(2,'+row.id+');"><span><span>审核不通过</span></span></button></div>');
	}
	return html.join("");
}
function editActivity(row){
	$("#scoreBuyDialog").dialog({
	    width: 805,
	    height: 533,
	    href: _path+'/scoreBuyActivity/toScoreBuyActivity?op=edit&id='+row.actId,
	    modal: true,
	    closed: true,
	    title:'积分换购',
	    closed:true,
	    cache:false,
	    draggable:false
	});
	$('#scoreBuyDialog').dialog('open');
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
		ids[i]=v.id;
	});
	var params={};
	params.ids=ids;
	
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/scoreBuyActivity/deleteBuyActivity',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
			    			easyuiMsg('提示', "删除成功！");
			    			$('#table-car-year').datagrid('reload', {});
			    		} else if (_data.head.retCode == 'PDE001'){
			    			easyuiMsg('错误', "页面输入数据错误！");
			    		}else if (_data.head.retCode == '000007'){
			    			easyuiMsg('错误', "存在级联数据！");
			    			$('#table-car-year').datagrid('reload', {});
			    		}else{
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
}

function check(type,id){
	$.ajax({
	    url:_path + '/scoreBuyApply/updateScoreBuyApply',
	    type:"post",
	    data:{"type":type,"id":id},
	    dataType:"json",
	    success:function(_data){
	    	if (_data.head.retCode == '000000') {
    			easyuiMsg('提示', "操作成功！");
    			$('#score_list_dataGrid').datagrid('reload', {});
    		} else if (_data.head.retCode == 'PDE001'){
    			easyuiMsg('错误', "页面输入数据错误！");
    		}else{
    			easyuiMsg('错误', "操作失败！");
    		}
	    }
	});
}
