/**
 * 功能描述： 汽车车系年款的js
 * 
 */
var now = new Date();  
var year = now.getFullYear(); 
var temp = [];
$(function() {
	
	$('#table-oam-goods').datagrid({
		url : _path + '/oamGoods/oamGoodsList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'spiderValId',
			align : 'center',
			halign: 'center',
			title : '爬虫数据ID',
			sortable: true
		},{
			field : 'type',
			align : 'center',
			halign: 'center',
			title : '数据类型',
			sortable: true,
			formatter : function(value, row) {
				var result = '保养';
				if(value=='CT'){
					result = '车型';
				}else if(value=='CB'){
					result = '品牌';
				}else if(value=='CS'){
					result = '车系';
				}else if(value=='CF'){
					result='汽车品牌';
				}
				return result ;
			}
		},{
			field : 'compareType',
			align : 'center',
			halign: 'center',
			title : '数据操作类型',
			sortable: true,
			formatter : function(value, row) {
				var result = '';
				if(value=='update'){
					result = '更新';
				}else if(value=='add'){
					result = '新增';
				}
				return result ;
			}
		}, {
			field : 'afterTips',
			align : 'center',
			halign: 'center',
			title : '爬虫数据'
		},{
			field : 'beforeTips',
			align : 'center',
			halign: 'center',
			title : '本地数据'
		},{
			field : 'createId',
			align : 'center',
			halign: 'center',
			title : '创建者'
		},{
			field : 'createTime',
			align : 'center',
			halign: 'center',
			title : '创建时间',
			sortable: true
		}/*,{
			field : 'editor',
			halign: 'center',
			title : '操作',
			align : 'center',
			formatter : function(value, row) {
				var html =  "<a  href='javascript:editCarYear("+JSON.stringify(row)+")'>编辑</a>";
				return html;
			}
		}*/ ] ],
		toolbar : '#toolbar-car_year',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
		pageSize : 25,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageList : [ 25, 50, 100, 150 ],
		//singleSelect : true,
		selectOnCheck:false,
		checkOnSelect:false,
		remoteSort : true,
		multiSort : true,
		singleSelect : true,
		view : detailview,
		detailFormatter : function(index, row) {
			var str = '<table><tr><td><table>';
			if(null != row.beforeValue.name) {
				str += '<tr><td>' + row.beforeValue.name + '</td><td>&nbsp;&nbsp;&nbsp;</td>' +
	            '<td>' + row.afterValue.name +'</td>' +
	            '</tr>';
			}
			if(null != row.beforeValue.imgPath) {
				str += '<tr><td>' + row.beforeValue.imgPath + '</td><td>&nbsp;&nbsp;&nbsp;</td>' +
	            '<td>' + row.afterValue.imgPath +'</td>' +
	            '</tr>' ;
			}
			if(null != row.beforeValue.autohomeUrl) {
				str += '<tr><td>' + row.beforeValue.autohomeUrl + '</td><td>&nbsp;&nbsp;&nbsp;</td>' +
	            '<td>' + row.afterValue.autohomeUrl +'</td>' +
				'</tr>' ;
			}
			str += '</table></td>';
			//alert("config="+row.beforeValue.detailConfig);
			if(null != row.beforeValue.detailConfig) {
				str += '<td><table>';
				$.each(row.beforeValue.detailConfig, function(n, value) {
					str += '<tr><td>' + value.displayName + '</td><td>&nbsp;&nbsp;&nbsp;</td>' +
		            '<td>' + value.configValue +'</td>' +
					'</tr>' ;
				});
				str += '</table></td><td><table>';
				$.each(row.afterValue.detailConfig, function(n, value) {
					str += '<tr><td>' + value.displayName + '</td><td>&nbsp;&nbsp;&nbsp;</td>' +
		            '<td>' + value.configValue +'</td>' +
					'</tr>' ;
				});
				str += '</table></td>';
			}
			
			str += '</tr></table>';
			
			
            return str;
		},
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});
	
	
	$("#checkBtn").click(function(){
		var rows = $('#table-oam-goods').datagrid('getChecked');
		if (rows.length < 1) {
			easyuiMsg('提示', '请选择要操作的数据项!');
		}else {
			$('#checkWindow').window('open');
		}
		
	});
	
	$("#checkok").click(function(){
		var params={};
		var rows = $('#table-oam-goods').datagrid('getChecked');
		var url = _path + '/oamGoods/batchCheck';
		params.rows = JSON.stringify(rows);
		params.ok=1;
		commonAjax(url, params, function(data) {
			$('#table-oam-goods').datagrid('reload');
			$('#checkWindow').window('close');
			easyuiMsg('提示', data.retMsg);
		}, function(data) {
			easyuiMsg('失败', data.retMsg);
		})
	});
	$("#checkcancel").click(function(){
		var params={};
		var rows = $('#table-oam-goods').datagrid('getChecked');
		var cause=$("#cause").val();
		params.ok=0;
		params.cause=cause;
		if(cause && 10<cause.length && cause.length<200){
			var url = _path + '/oamGoods/batchCheck';
			params.rows = JSON.stringify(rows);
			commonAjax(url, params, function(data) {
				$('#table-oam-goods').datagrid('reload');
				$('#checkWindow').window('close');
				easyuiMsg('提示', data.retMsg);
			}, function(data) {
				easyuiMsg('失败', data.retMsg);
			})
		}else{
			easyuiMsg('失败', '审核备注不能为空，并且必须大于10个字小于200个字!');
		}
		
	});
   
});


//高级查询立即筛选
function advanceSearch(){
	var param = biz.serializeObject($('#search_form'));
	param.carDeptId = $("#searchCarDeptId").val();
	param.carYearValue = $("#searchYear").val();
	$('#table-car-year').datagrid('load', param);
}

//清除高级查询
function advance_clear(){
	search_clear('search_form','table-car-year');
	$('#table-car-year').datagrid('reload', {});
	//$("#searchFactoryId").val('-1').trigger("change");
	//$("#searchCarDeptId").val('-1').trigger("change");
	$("#searchFactoryId").select2({placeholder : "--请选择--"});
	$("#searchCarDeptId").select2({placeholder : "--请选择--"});
	
	$("#searchYear").val('-1').trigger("change");
}






