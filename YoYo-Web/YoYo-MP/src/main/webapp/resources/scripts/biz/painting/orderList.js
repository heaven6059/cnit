;$(function() {
	var src = _path + '/painting/list';
	// 切换选项卡
	$('#tabs-order').tabs({'onSelect' : function(title, index) {
			if (title == '待付款') {
				$('#table-order-list').datagrid('options').url = src + '?payStatus=0';
			} else if (title == '待确认') {
				$('#table-order-list').datagrid('options').url = src + '?status=unconfirm';
			} else if (title == '待安装') {
				$('#table-order-list').datagrid('options').url = src + '?status=uninstall';
			} else if (title == '安装中') {
				$('#table-order-list').datagrid('options').url = src + '?status=install';
			} else if (title == '已完成') {
				$('#table-order-list').datagrid('options').url = src + '?status=finish';
			} else if (title == '已取消') {
				$('#table-order-list').datagrid('options').url = src + '?status=dead';
			} else if (title == '已删除') {
				$('#table-order-list').datagrid('options').url = src + '?disabled=del';
			} else if (title == '全部') {
				$('#table-order-list').datagrid('options').url = src;
			} 
			$('#table-order-list').datagrid('reload');
		}
	});
	var loadFilter = function(data) {
		if (data.resultObject && data.resultObject.content  && data.resultObject.content.rows) {
			return data.resultObject.content;
		} else if(data.rows){
			return data;
		}
		else{
			return {
				total : 0,
				rows : []
			};
		}
	};

	var columns = [ [ {
		field : 'chkbox',
		checkbox : true
	},{
		field : 'id',
		align : 'center',
		title : '订单号',
		width: 150,
		sortable: true
	},  {
		field : 'status',
		align : 'center',
		title : '订单状态',
		sortable: true,
		width: 80,
		formatter : function(value, row) {
			if (value == 'active') {
				return '活动订单';
			} else if (value == 'dead') {
				return '已作废';
			} else if (value == 'finish') {
				return '已完成';
			} else if (value == 'unconfirm') {
				return '待确认';
			} else if (value == 'create') {
				return '创建订单';
			} else if (value == 'uninstall') {
				return '未安装';
			} else if (value == 'install') {
				return '安装中';
			}
		}
	},  {
		field : 'payStatus',
		align : 'center',
		title : '付款状态',
		sortable: true,
		width: 80,
		formatter : function(value, row) {
			if (value == '0') {
				return '未支付';
			} else if (value == '1') {
				return '已支付';
			} else if (value == '2') {
				return '已付款至担保方';
			} else if (value == '3') {
				return '部分付款';
			} else if (value == '4') {
				return '部分退款';
			} else if (value == '5') {
				return '全额退款';
			} 
		}
	}, {
		field : 'paymentId',
		align : 'center',
		title : '支付方式',
		sortable: true,
		width: 120,
		formatter : function(value, row) {
			if (value == '0') {
				return '在线支付';
			}else if(value == '1') {
				return '到店支付';
			}
		}
//	}, {
//		field : 'refundStatus',
//		align : 'center',
//		title : '退款状态',
//		formatter : function(value, row) {
//			if (value == '0') {
//				return '未申请退款';
//			} else if (value == '1') {
//				return '退款申请中,等待卖家审核';
//			} else if (value == '2') {
//				return '卖家拒绝退款';
//			} else if (value == '3') {
//				return '卖家同意退款,等待买家退货';
//			} else if (value == '4') {
//				return '卖家已退款';
//			} else if (value == '5') {
//				return '买家已退货,等待卖家确认收货';
//			} else if (value == '6') {
//				return '卖家不同意协议,等待买家修改';
//			} else if (value == '7') {
//				return '买家已退货,卖家不同意协议,等待买家修改';
//			} else if (value == '8') {
//				return '平台介入,等待卖家举证';
//			} else if (value == '9') {
//				return '平台介入,等待平台处理';
//			} else if (value == '10') {
//				return '平台介入已处理';
//			} else if (value == '11') {
//				return '卖家同意退款，等待卖家打款至平台';
//			} else if (value == '12') {
//				return '卖家已退款，等待系统结算';
//			}
//		}
	}, {
		field : 'payed',
		align : 'center',
		title : '订单总额',
		sortable: true,
		width: 130,
		formatter : function(value, row) {
			return fmoney(value,2);
		}
	},{
		field : 'createtime',
		align : 'center',
		title : '下单时间',
		sortable: true,
		width: 130,
		formatter : function(value, row) {
			return new Date(value).format("yyyy-MM-dd hh:mm:ss");
		}
	},{
		field : 'lastModified',
		align : 'center',
		title : '最后更新时间',
		sortable: true,
		width: 130,
		formatter : function(value, row) {
			return new Date(value).format("yyyy-MM-dd hh:mm:ss");
		}
	} ] ];

	$('#table-order-list').datagrid({
		//数据网格（datagrid）面板的头部工具栏
		toolbar : '#toolbar-order-list',
		//则在数据网格（datagrid）底部显示分页工具栏
		pagination : true,
		//则显示带有行号的列。
		rownumbers : true,
		//使列自动展开/折叠以适应数据网格（datagrid）的宽度。
		fitColumns : true,
		//点击复选框将会选中该行。
		selectOnCheck : false,
		//当用户点击某一行时，则会选中/取消选中复选框。
		checkOnSelect : false,
		//定义是否从服务器排序数据。
		remoteSort : false,
		pageSize : 25,
		pageList : [ 25, 50, 100, 200 ],
		//multiSort
		multiSort : true,
		url : _path + '/painting/list',
		columns : columns,
		loadFilter : loadFilter,
		view : detailview,
		detailFormatter : function(index, row) {
			return '<div class="dropdown-order-detail" style="padding:10px 0px;margin:10px auto;"></div>';
		},
		onExpandRow : function(index, row) {
			var ddv = $(this).datagrid('getRowDetail', index).find('div.dropdown-order-detail');
			ddv.panel({
				border : false,
				height : 400,
				cache : false,
				href : _path + '/painting/orderDetailTab?orderId=' + row.id,
				onLoad : function() {
					$('#table-order-list').datagrid('fixDetailRowHeight', index);
				}
			});
			$('#table-order-list').datagrid('fixDetailRowHeight', index);
		},
		onLoadSuccess: function(date){
			paramToExcelForm($('#table-order-list').datagrid('options').url,$('#search_form').serialize());
		}
	});
	
	$('#export').click(exportSelected);
	$('#exportAll').click(exportAll);
});

function exportSelected(){
	var objArr = $('#table-order-list').datagrid('getChecked');
	var ids = [];
	for ( var i = 0; i < objArr.length; i++) {
		ids.push(objArr[i].id);
	}
	var tempForm = document.createElement('form');
	tempForm.innerHTML ="<input name='ids' id='ids' value='"+ids.toString()+"'>";
	tempForm.action = yoyo.mpUrl+'/painting/exportOrder';
	tempForm.submit();
}

function exportAll(){
	var postForm = document.getElementById("excelForm");
	postForm.action = yoyo.mpUrl+'/painting/exportOrder';
	postForm.submit();
}
//高级查询立即筛选
function advanceSearch() {
	var url = $('#table-order-list').datagrid('options').url;
	var param = $('#search_form').serialize() ;
	if($('#payStatus').val() && url.indexOf('payStatus') >= 0){
		$('#table-order-list').datagrid('options').url = _path + '/painting/list';
		$('#table-order-list').datagrid('load', paramObj(param));
		$('#table-order-list').datagrid('options').url = _path + '/painting/list?payStatus=0';
	}else{
		$('#table-order-list').datagrid('load', paramObj(param));
	}
}

function paramToExcelForm(_param, param){
	var obj = {
			status : '' ,
			payStatus: ''
	};
	if( _param.indexOf('?') >= 0){
		var i = _param.indexOf('?')+1;
		var arr1 = _param.slice(i).split('=');
		var key = arr1[0];
		var value = arr1[1];
		obj[key] = value;
	}
	if(param){
		//["id=", "payStatus=0", "paymentId=", "storeId=", "storeName=", "name=", "minPayed=", "maxPayed=", "startDate=2015-06-04", "endDate=2015-07-29"]
		var arr2 = param.split('&');
		for ( var i = 0; i < arr2.length; i++) {
			//["id", ""]
			var arr3 = arr2[i].split('=');
			var key = arr3[0];
			var value = arr3[1];
			if( key == 'payStatus' && !value){
				continue;
			}
			obj[key] = value;
		}
	}
	for (x in obj)
	{
		$('#excelForm').find('#'+x).val(obj[x]);
	}
}

function fmoney(s, n)   
{   
   n = n > 0 && n <= 20 ? n : 2;   
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
   var l = s.split(".")[0].split("").reverse(),   
   r = s.split(".")[1];   
   t = "";   
   for(var i = 0; i < l.length; i ++ )   
   {   
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
   }   
   return t.split("").reverse().join("") + "." + r;   
} 

function searchClear(){
	$(':input','#search_form')  
	.not(':button, :submit, :reset, :hidden')  
	.val('')  
	.removeAttr('checked')  
	.removeAttr('selected');
	
	$(':input[class="textbox-value"]','#search_form').val('') ;
	
	advanceSearch();
}

function paramObj(param){
	var obj = {};
	var arr = param.split('&');
	for ( var i = 0; i < arr.length; i++) {
		var item = arr[i];
		//"2015-07-01+19%3A54%3A56"
		var _arr = item.split('=');
		if(_arr[1]){
			if(_arr[0].indexOf('Date')){
				_arr[1] = decodeURIComponent(_arr[1].replace('+',' '));
			}
			obj[_arr[0]] = _arr[1];
		}
	}
	return obj;
}
