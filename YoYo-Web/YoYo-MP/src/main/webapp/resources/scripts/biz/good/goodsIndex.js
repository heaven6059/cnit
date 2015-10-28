
$(function() {
	var goodsListSrc = _path + '/goods/goodsList?';
	// 切换选项卡
	$('#tabs-goods').tabs({
		'onSelect' : function(title, index) {
			if (title == '已下架/待上架商品') {
				goodsIndex.clickTabs(1);
				$('#table-goods').datagrid('options').url = goodsListSrc + 'marketableQ=0';
			}
			else if (title == '已上架商品') {
				goodsIndex.clickTabs(2);
				$('#table-goods').datagrid('options').url = goodsListSrc + 'marketableQ=1';
			} else if (title == '待审核商品') {
				goodsIndex.clickTabs(3);
				$('#table-goods').datagrid('options').url = goodsListSrc + 'marketableQ=2';
			} else if (title == '审核不通过商品') {
				goodsIndex.clickTabs(6);
				$('#table-goods').datagrid('options').url = goodsListSrc + 'marketableQ=3';
			}else {
				goodsIndex.clickTabs(5);
				$('#table-goods').datagrid('options').url = goodsListSrc;
			}
			$('#table-goods').datagrid('reload');
		}
	});

	// 绑定批量操作事件
	$('#toolbar-goods-batch-edit div').on('click', function() {
		var rows = $('#table-goods').datagrid('getChecked');
		if (rows.length < 1) {
			easyuiMsg('提示', '请选择要操作的数据项!');
		} else {
			var url = _path + '/goods/batchEdit?';
			var params = {};
			if ($(this).text() == '商品上架') {
				url = url + 'op=up'
			} else if ($(this).text() == '商品下架') {
				url = url + 'op=down'
			} else if ($(this).text() == '商品除权') {
				url = url + 'op=exdiv'
			} else if ($(this).text() == '重新生成图片') {
				url = url + 'op=regen'
			} else {
				return;
			}
			params.rows = JSON.stringify(rows);
			commonAjax(url, params, function(data) {
				$('#table-goods').datagrid('reload');
			}, function(data) {
				easyuiMsg('失败', '请选择要操作的数据项!');
			})
		}
	});

	$("#upBtn").click(function(){
		goodsIndex.onselectCheck(1);
	});
	$("#downBtn").click(function(){
		goodsIndex.onselectCheck(2);
		$("#downcause").val("");
	});
	$("#checkBtn").click(function(){
		goodsIndex.onselectCheck(3);
	});
	$("#delBtn").click(function(){
		goodsIndex.onselectCheck(4);
	});
	$("#editBtn").click(function(){
		goodsIndex.onselectCheck(5);
	});
	$("#checkok").click(function(){
		var params={};
		var rows = $('#table-goods').datagrid('getChecked');
		var url = _path + '/goods/batchEdit';
		params.rows = JSON.stringify(rows);
		params.op='check';
		params.ok=1;
		commonAjax(url, params, function(data) {
			$('#table-goods').datagrid('reload');
			$('#checkWindow').window('close');
		}, function(data) {
			easyuiMsg('失败', data.retMsg);
		})
	});
	$("#checkcancel").click(function(){
		var params={};
		var rows = $('#table-goods').datagrid('getChecked');
		var cause=$("#cause").val();
		params.op='check';
		params.ok=0;
		params.cause=cause;
		if(cause && 10<cause.length && cause.length<200){
			var url = _path + '/goods/batchEdit';
			params.rows = JSON.stringify(rows);
			commonAjax(url, params, function(data) {
				$('#table-goods').datagrid('reload');
				$('#checkWindow').window('close');
			}, function(data) {
				easyuiMsg('失败', data.retMsg);
			})
		}else{
			easyuiMsg('失败', '审核备注不能为空，并且必须大于10个字小于200个字!');
		}
		
	});
	$("#downcheckok").click(function(){
		var params={};
		var rows = $('#table-goods').datagrid('getChecked');
		var downcause=$("#downcause").val();
		params.op='down';
		params.cause=downcause;
		if(downcause && 10<downcause.length && downcause.length<200){
			var url = _path + '/goods/batchEdit';
			params.rows = JSON.stringify(rows);
			commonAjax(url, params, function(data) {
				$('#table-goods').datagrid('reload');
				$('#downWindow').window('close');
			}, function(data) {
				easyuiMsg('失败', data.retMsg);
			})
		}else{
			easyuiMsg('失败', '下架违规原因不能为空，并且必须大于10个字小于200个字!');
		}
		
	});
	// 标签
	$('#toolbar-goods-tag div').on('click', function() {
		if ($(this).text() == '为选中项打标签') {
			var rows = $('#table-goods').datagrid('getChecked');
			if (rows.length < 1) {
				easyuiMsg('提示', '请选择要操作的数据项!');
			} else {
				easyuiMsg('提示', '为选中项打标签!');
			}
		} else {
			easyuiMsg('提示', '标签设置!');
		}
	});

	var loadFilter = function(data) {
		if (data.content) {
			return data.content;
		} else {
			return {
				total : 0,
				rows : []
			};
		}
	};
	var columns = [[
	 {field : 'chkbox',checkbox : true,hidden:true},
	 {field : 'goodsId',hidden:true},
	{field : 'dbn',title : '商品编号',halign:'center',width:130,sortable : true,options : {querytype : 'like'}}, 
	{field : 'catName',title : '分类',halign:'center',width:100}, 
	{field : 'smallPic',title : '缩略图',halign:'center',width:50,
		formatter : function(value, row) {
			if (value) {
				var imageUrl=value.split(".")[0] + ".44x44." +value.split(".")[1];
				return '<img  src="' + yoyo.imageUrl+imageUrl+ '">';
			} else {
				return '<img  src="' + yoyo.imageUrl + '/product/201505/1431434653329.64x48.jpg" width="64px" height="64px;">';
			}
		}
	}, 
	{field : 'name',title : '商品名称',halign:'center',width:200,sortable : true,options : {querytype : 'like'}},
	{field : 'storeName',title : '店铺名称',halign:'center',sortable : true,options : {querytype : 'like'}}, 
	{field : 'store',title : '库存',halign:'center',width:80,sortable : true,options : {querytype : 'range'}}, 
	{field : 'price',title : '销售价',halign:'center',width:80,sortable : true,options : {querytype : 'range'}},
	{field : 'score',title : '积分',halign:'center',width:80,sortable : true,options : {querytype : 'range'},formatter : function(value) {
		if(value){
			return value.split(".")[0];
		}else{
			return 0;
		}
	}}, 
	{field : 'brandName',title : '品牌',halign:'center',width:80,sortable : true}, 
	{field : 'marketable',title : '状态',halign:'center',width:80,
		formatter : function(value) {
			if (value == '0') {
				return '<span class="common-tag-error" >已下架</span>';
			} else if (value == '1') {
				return '<span class="common-tag-pass">已上架</span>';
			} else if (value == '2') {
				return '<span class="common-tag-warn">待审核</span>';
			} else if (value == '3') {
				return '<span class="common-tag-ignore">审核不通过</span>';
			}else if (value == '4') {
				return '<span class="common-tag-warn">待上架</span>';
			}else if (value == '-1') {
				return '<span class="common-tag-ignore">暂存本地</span>';
			}
		}
	},
	{field : 'a.lastModify',title : '更新时间',width:120,halign:'center',sortable : true,options : {querytype : 'range',clazz:'easyui-datetimebox'},formatter: function(value,row,index){
		return row.lastModify;
	}}, 
	{field : 'upTime',title : '上架时间',halign:'center',width:120,sortable : true,options : {querytype : 'range',clazz:'easyui-datetimebox'},formatter: function(value,row,index){
		return row.upTime;
	}}, 
	{field : 'downTime',title : '下架时间',halign:'center',width:120,sortable : true,options : {querytype : 'range',clazz:'easyui-datetimebox'},formatter: function(value,row,index){
		return row.downTime;
	}}, 
	{field : 'brief',title : '商品简介',halign:'center',width : 300,
		formatter : function(value) {
			if(value && value.length > 100){
				return value.substring(0,100);
			}else{
				return value;
			}
		} 
	}
	]];
	var genGoodsDetailContent = function(row) {
		var cause="";
		if(row.marketable=='3'){
			cause="审核失败原因:"+row.p22;
		}
		if(row.marketable=='0' && row.p22){
			cause="违规原因:"+row.p22;
		}
		var title = $('<div />').append($('<span/>', {
			class : 'db-good-title'
		}).text(row.name)).append($('<span/>', {
			class : 'db-good-bn'
		}).text(row.dbn)).append($('<span/>', {
			class : 'db-good-st'
		})).append($('<span/>', {
			class : 'db-good-com'
		}).text('共' + row.commentsCount + '次评论'));
		var statistic = $('<div />').append($('<span/>', {
			class : 'db-good-stc'
		}).text('共被浏览' + row.viewCount + '次,被购买' + row.buyCount + '件。 '));
		var url = $('<div />').append($('<span/>', {
			class : 'db-good-stc'
		}).text('前台访问地址：')).append($('<a />', {
			class : 'db-good-url',
			href : '#'
		}).text('访问此连接').on('click', function() {
			window.open(yoyo.portalUrl + '/goodsManager/goodsIndex?goodsId=' + row.goodsId);
		}));
		return $('<div />').css({
			border : '1px solid #7b7b7b',
			padding : '10px'
		}).append(title).append(statistic).append(url).append(""+cause);
	};
	$('#table-goods').datagrid({
		toolbar : '#toolbar-goods',
		pagination : true,
		rownumbers : true,
		autoRowHeight:true,
//		fitColumns : true,
//		fit:true,
		pageSize: 25,
	    pageList: [25,50,100,150],
		singleSelect : true,
		selectOnCheck : false,
		checkOnSelect : false,
		remoteSort : true,
		multiSort : true,
		nowrap : false,
//		height:750,
		url : _path + '/goods/goodsList',
		columns : columns,
		loadFilter : loadFilter,
		view : detailview,
		detailFormatter : function(index, row) {
			return '<div class="dropdown-good-detail" style="padding:10px 0px;margin:10px auto;"></div>';
		},
		onExpandRow : function(index, row) {
			var ddv = $(this).datagrid('getRowDetail', index).find('div.dropdown-good-detail');
			ddv.panel({
				height : 120,
				border : false,
				cache : false,
				content : genGoodsDetailContent(row),
				onLoad : function() {
					$('#table-goods').datagrid('fixDetailRowHeight', index);
				}
			});
			$('#table-goods').datagrid('fixDetailRowHeight', index);
		},onDblClickRow: function (rowIndex, row) { 
			window.open(yoyo.portalUrl + '/goodsManager/goodsIndex?goodsId=' + row.goodsId);
		} 
	});
	
	$('#table-goods').datagrid({
		height : ($(window).height() * 0.95)
	});
	$('#table-goods').datagrid('reload');
	goodsIndex.clickTabs(5);
	
//	$('#acc-good-ads-button').advanceSeacher({
//		aimDataGrid : 'table-goods',
//		renderTo : 'table-good-advance-searcher',
//		exceptField : ['chkbox', 'edit', 'smallPic', 'marketable', 'catName','upTime']
//	});
	goodsIndex.adSearch(null);
	var vailidateOperate = function() {
		var rows = $('#table-goods').datagrid('getChecked');
		if (rows.length < 1) {
			easyuiMsg('提示', '请选择要操作的数据项!');
			return false;
		} else {
			return true;
		}
	};

	var methods = {
		doEdit : function(goodsId) {
			window.open(_path + '/goods/editIndex/' + goodsId);
		},
		doDelete : function(goodsId) {
			var params = {};
			params.op = 'delete';
			params.rows = JSON.stringify($('#table-goods').datagrid('getSelections'));
			commonAjax(_path + '/goods/batchEdit', params, function(data) {
				$('#table-goods').datagrid('reload');
			}, function(data) {
				easyuiMsg('失败', data.head.retMsg);
			});
		},
		doSaleUp : function(_id) {

		},
		doSaleDown : function(_id) {

		},
		doEditProduct : function(_id) {

		},
		doBatchDelete : function() {
			if (vailidateOperate) {
				var params = {};
				params.op = 'delete';
				params.rows = JSON.stringify($('#table-goods').datagrid('getChecked'));
				commonAjax(_path + '/goods/batchEdit', params, function(data) {
					$('#table-goods').datagrid('reload');
				}, function(data) {
					easyuiMsg('失败', data.head.retMsg);
				});
			}
		},
		doBatchTagging : function() {
			if (vailidateOperate) {
				// 获取标签
				commonAjax(_path + '/label/brandApplyLabels', {
					"campanyId" : 1
				}, function(data) {
					var content = $('<div />').append($('<div />').text('对选择的' + 0 + '个条目应用标签'));
					var labels = data.content.rows;
					if (labels.length > 0) {
						var ulDom = $('<ul />');
						for ( var i = 0, label; label = labels[i++];) {
							var liDom = $('<li />').css('display', 'inline');
							liDom.append($('<input />', {
								name : 'labelChk',
								type : checkbox
							}).val(label.id));
							liDom.append($('<span />').css({
								'color' : label.fontColor,
								'background-color' : label.bgColor
							}).text(label.name));
							ulDom.append(liDom);
						}
						content.append($('<div />').append(ulDom));
					}
					content.append($('<div />').css('text-align', 'center').append($('<a />', {
						onclick : 'javascritp:$(this).goodsIndex("doBatchTagging")'
					}).text('保存')));
				}, null);
				var advsWindow = $('<div />', {
					id : _aimDataGrid + '_asw'
				});
				$('#' + _parent).parent().append(advsWindow);
				$('#' + _aimDataGrid + '_asw').window({
					title : '设置标签',
					width : 400,
					height : 500,
					closed : true,
					inline : true,
					cache : false,
					resizable : false,
					content : _rootEle
				});
			}
		}
	};

	$.fn.goodsIndex = function(method) {
		if (methods[method]) {
			methods[method](arguments[1]);
		}
	}
});

var goodsIndex={
	  adSearch:function(exceptField){
		  if(!exceptField){
			  exceptField=['chkbox', 'edit', 'smallPic', 'marketable', 'catName'];
		  }
		  $('#acc-good-ads-button').advanceSeacher({
				aimDataGrid : 'table-goods',
				renderTo : 'table-good-advance-searcher',
				exceptField : exceptField
		  });
	  },
	  clickTabs : function(type) {
		if (type == 1) {//已下架
			$("#upBtn").show();
			$("#downBtn").hide();
			$("#checkBtn").hide();
			$("#delBtn").show();
			$("#editBtn").show();
			$('#table-goods').datagrid('showColumn','chkbox');
			$("#table-goods").datagrid('showColumn', 'downTime');
			$("#table-goods").datagrid('hideColumn', 'upTime');
			var exceptField = ['chkbox', 'edit', 'smallPic', 'marketable', 'catName','upTime']
			goodsIndex.adSearch(exceptField);
		} else if (type == 2) {//已上架
			$("#upBtn").hide();
			$("#downBtn").show();
			$("#checkBtn").hide();
			$("#delBtn").hide();
			$("#editBtn").hide();
			$('#table-goods').datagrid('showColumn','chkbox');
			$("#table-goods").datagrid('hideColumn', 'downTime');
			$("#table-goods").datagrid('showColumn', 'upTime');
			var exceptField = ['chkbox', 'edit', 'smallPic', 'marketable', 'catName','downTime']
			goodsIndex.adSearch(exceptField);
		} else if (type == 3) {//待审核
			$("#upBtn").hide();
			$("#downBtn").hide();
			$("#checkBtn").show();
			$("#delBtn").hide();
			$("#editBtn").hide();
			$('#table-goods').datagrid('showColumn','chkbox');
			$("#table-goods").datagrid('hideColumn', 'downTime');
			$("#table-goods").datagrid('hideColumn', 'upTime');
			var exceptField = ['chkbox', 'edit', 'smallPic', 'marketable', 'catName','downTime','upTime']
			goodsIndex.adSearch(exceptField);
		} else if (type == 4) {//待上架
			$("#upBtn").hide();
			$("#downBtn").hide();
			$("#checkBtn").hide();
			$("#delBtn").hide();
			$("#editBtn").hide();
			$('#table-goods').datagrid('hideColumn','chkbox');
			$("#table-goods").datagrid('hideColumn', 'downTime');
			$("#table-goods").datagrid('hideColumn', 'upTime');
			var exceptField = ['chkbox', 'edit', 'smallPic', 'marketable', 'catName','downTime','upTime']
			goodsIndex.adSearch(exceptField);
		} else if (type == 6) {//审核不通过
			$("#upBtn").hide();
			$("#downBtn").hide();
			$("#checkBtn").hide();
			$("#delBtn").show();
			$("#editBtn").hide();
			$('#table-goods').datagrid('showColumn','chkbox');
			$("#table-goods").datagrid('hideColumn', 'downTime');
			$("#table-goods").datagrid('hideColumn', 'upTime');
			var exceptField = ['chkbox', 'edit', 'smallPic', 'marketable', 'catName','downTime','upTime']
			goodsIndex.adSearch(exceptField);
		} else {//全部
			$("#upBtn").hide();
			$("#downBtn").hide();
			$("#checkBtn").hide();
			$("#delBtn").hide();
			$("#editBtn").hide();
			$('#table-goods').datagrid('hideColumn','chkbox');
			$("#table-goods").datagrid('showColumn', 'downTime');
			$("#table-goods").datagrid('showColumn', 'upTime');
		}
  	
  },
  //type:1上架 2下架 3审核 4删除 5编辑
  onselectCheck : function(type){
	  var rows = $('#table-goods').datagrid('getChecked');
		if (rows.length < 1) {
			easyuiMsg('提示', '请选择要操作的数据项!');
		}else {
			//0-已下架 1-已上架  2-待审核  3-待上架
		   if(type=='5'){
				if (rows.length !=1) {
					easyuiMsg('提示', '请选择一项编辑!');
					return ;
				}
			}
		   if(type=='4'){
			 $.messager.confirm("确认", "删除后不可恢复，是否确认删除?", function (r) {  
			        if (r) {
			            var url = _path + '/goods/batchEdit?';
			            url = url + 'op=delete';
			            params.rows = JSON.stringify(rows);
						commonAjax(url, params, function(data) {
							$('#table-goods').datagrid('reload');
						}, function(data) {
							easyuiMsg('失败', data.head.retMsg);
						});
			        }else{
			        	return;
			        }
			    });  
			}
			for(var i=0;i<rows.length;i++){
				var status=rows[i].marketable;
				if(type=='3'){
					if(status!='2'){
						easyuiMsg('失败', '只能审核【待审核】状态商品!');
						return;
					}else{
						$('#checkWindow').window('open');
						return;
					}
				}
				if(status!='1' && type=='2'){
					easyuiMsg('失败', '只能对【上架】状态商品下架!');
					return;
				}
				if(status!='0' && type=='1'){
					easyuiMsg('失败', '只能对【下架】状态商品上架!');
					return;
				}
				if(type=='5'){
					if (rows.length !=1) {
						easyuiMsg('提示', '请选择一项编辑!');
						return;
					}
					if(status=='1'){
						easyuiMsg('提示', '【上架】状态商品不能编辑!');
						return;
					}else{
						var url = _path + '/goods/editIndex/'+rows[i].goodsId;
						$(this).goodsIndex('doEdit',rows[i].goodsId);
//						windows.location.href=url;
					}
				}
			}
			var url = _path + '/goods/batchEdit?';
			var params = {};
			if (type == '1') {//上架
				url = url + 'op=up';
			} else if (type == '2') {//下架
				$('#downWindow').window('open');
				return;
			}else if (type == '3') {//审核
				url = url + 'op=down';
			}else {
				return;
			}
			params.rows = JSON.stringify(rows);
			commonAjax(url, params, function(data) {
				$('#table-goods').datagrid('reload');
			}, function(data) {
				easyuiMsg('失败', '请选择要操作的数据项!');
			})
		}
  }
}
