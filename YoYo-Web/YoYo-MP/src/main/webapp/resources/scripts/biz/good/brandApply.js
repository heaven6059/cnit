/**
 * 功能描述： 商品品牌审核的js
 * 
 */
// 扩展easyUI 中动态更新tab的title值
$.extend($.fn.tabs.methods, {
	setTabTitle:function(jq,opts){
		return jq.each(function(){
			var tab = opts.tab;
			var options = tab.panel("options");
			var tab = options.tab;
			options.title = opts.title;
			var title = tab.find("span.tabs-title");
			title.html(opts.title);
		});
	}
});

//更新tab选项卡的总数
function updateTabsTitles(len, tab){
	var updateTab = $('#brandTabs').tabs('getTab', tab);
	var updateTitle = "";
	if(tab == 0){
		updateTitle = "全部";
	}else if(tab == 1){
		updateTitle = "审核中";
	}else if(tab == 2){
		updateTitle = "审核通过";
	}else if(tab == 3){
		updateTitle = "撤回审核";
	}else{
		updateTitle = "审核不通过";
	}
	$('#brandTabs').tabs('setTabTitle',{tab:updateTab,title:updateTitle});
}

function initSpectrum(){
	$("#fontColor").spectrum({
	    color: "#000",
	    showInput: true,
	    className: "full-spectrum",
	    showInitial: true,
	    showPalette: true,
	    showSelectionPalette: true,
	    maxPaletteSize: 10,
	    preferredFormat: "hex",
	    localStorageKey: "spectrum.demo",
	    palette: [
	        ["rgb(0, 0, 0)", "rgb(67, 67, 67)", "rgb(102, 102, 102)",
	        "rgb(204, 204, 204)", "rgb(217, 217, 217)","rgb(255, 255, 255)"],
	        ["rgb(152, 0, 0)", "rgb(255, 0, 0)", "rgb(255, 153, 0)", "rgb(255, 255, 0)", "rgb(0, 255, 0)",
	        "rgb(0, 255, 255)", "rgb(74, 134, 232)", "rgb(0, 0, 255)", "rgb(153, 0, 255)", "rgb(255, 0, 255)"], 
	        ["rgb(230, 184, 175)", "rgb(244, 204, 204)", "rgb(252, 229, 205)", "rgb(255, 242, 204)", "rgb(217, 234, 211)", 
	        "rgb(208, 224, 227)", "rgb(201, 218, 248)", "rgb(207, 226, 243)", "rgb(217, 210, 233)", "rgb(234, 209, 220)", 
	        "rgb(221, 126, 107)", "rgb(234, 153, 153)", "rgb(249, 203, 156)", "rgb(255, 229, 153)", "rgb(182, 215, 168)", 
	        "rgb(162, 196, 201)", "rgb(164, 194, 244)", "rgb(159, 197, 232)", "rgb(180, 167, 214)", "rgb(213, 166, 189)", 
	        "rgb(204, 65, 37)", "rgb(224, 102, 102)", "rgb(246, 178, 107)", "rgb(255, 217, 102)", "rgb(147, 196, 125)", 
	        "rgb(118, 165, 175)", "rgb(109, 158, 235)", "rgb(111, 168, 220)", "rgb(142, 124, 195)", "rgb(194, 123, 160)",
	        "rgb(166, 28, 0)", "rgb(204, 0, 0)", "rgb(230, 145, 56)", "rgb(241, 194, 50)", "rgb(106, 168, 79)",
	        "rgb(69, 129, 142)", "rgb(60, 120, 216)", "rgb(61, 133, 198)", "rgb(103, 78, 167)", "rgb(166, 77, 121)",
	        "rgb(91, 15, 0)", "rgb(102, 0, 0)", "rgb(120, 63, 4)", "rgb(127, 96, 0)", "rgb(39, 78, 19)", 
	        "rgb(12, 52, 61)", "rgb(28, 69, 135)", "rgb(7, 55, 99)", "rgb(32, 18, 77)", "rgb(76, 17, 48)"]
	    ]
	});
	
	$("#bgColor").spectrum({
	    color: "#CCC",
	    showInput: true,
	    className: "full-spectrum",
	    showInitial: true,
	    showPalette: true,
	    showSelectionPalette: true,
	    maxPaletteSize: 10,
	    preferredFormat: "hex",
	    localStorageKey: "spectrum.demo",
	    palette: [
	        ["rgb(0, 0, 0)", "rgb(67, 67, 67)", "rgb(102, 102, 102)",
	        "rgb(204, 204, 204)", "rgb(217, 217, 217)","rgb(255, 255, 255)"],
	        ["rgb(152, 0, 0)", "rgb(255, 0, 0)", "rgb(255, 153, 0)", "rgb(255, 255, 0)", "rgb(0, 255, 0)",
	        "rgb(0, 255, 255)", "rgb(74, 134, 232)", "rgb(0, 0, 255)", "rgb(153, 0, 255)", "rgb(255, 0, 255)"], 
	        ["rgb(230, 184, 175)", "rgb(244, 204, 204)", "rgb(252, 229, 205)", "rgb(255, 242, 204)", "rgb(217, 234, 211)", 
	        "rgb(208, 224, 227)", "rgb(201, 218, 248)", "rgb(207, 226, 243)", "rgb(217, 210, 233)", "rgb(234, 209, 220)", 
	        "rgb(221, 126, 107)", "rgb(234, 153, 153)", "rgb(249, 203, 156)", "rgb(255, 229, 153)", "rgb(182, 215, 168)", 
	        "rgb(162, 196, 201)", "rgb(164, 194, 244)", "rgb(159, 197, 232)", "rgb(180, 167, 214)", "rgb(213, 166, 189)", 
	        "rgb(204, 65, 37)", "rgb(224, 102, 102)", "rgb(246, 178, 107)", "rgb(255, 217, 102)", "rgb(147, 196, 125)", 
	        "rgb(118, 165, 175)", "rgb(109, 158, 235)", "rgb(111, 168, 220)", "rgb(142, 124, 195)", "rgb(194, 123, 160)",
	        "rgb(166, 28, 0)", "rgb(204, 0, 0)", "rgb(230, 145, 56)", "rgb(241, 194, 50)", "rgb(106, 168, 79)",
	        "rgb(69, 129, 142)", "rgb(60, 120, 216)", "rgb(61, 133, 198)", "rgb(103, 78, 167)", "rgb(166, 77, 121)",
	        "rgb(91, 15, 0)", "rgb(102, 0, 0)", "rgb(120, 63, 4)", "rgb(127, 96, 0)", "rgb(39, 78, 19)", 
	        "rgb(12, 52, 61)", "rgb(28, 69, 135)", "rgb(7, 55, 99)", "rgb(32, 18, 77)", "rgb(76, 17, 48)"]
	    ]
	});
}

$(function() {
/*	for(var i = 1; i < 5; i++){
		var label = $('#label_' + i).menubutton({ menu: '#labelTag' }); 
		$(label.menubutton('options').menu).menu({
			onClick: function (item) {
				if(item.id == "labeling"){
					var selectRows = getSelectedRows();
					if(selectRows.length == 0){
						$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
						return false;
					}
					$('#modelLabelWindow').window('open');
					document.getElementById("itemNum").innerHTML = selectRows.length;
					// 获取标签
					$.ajax({
			    	    url:_path + '/label/brandApplyLabels',
			    	    type:"post",
			    	    dataType:"json",
			    	    data:{
			    	    	"campanyId" : 1// 此处获取商店的id
			    	    },
			    	    dataType:"json",
			    	    success:function(_data){
			    	    	var labels = _data.content.rows;
			    	    	if(labels.length > 0){
			    	    		var ulDom = document.getElementById("labelsForSelect");
			    	    		ulDom.innerHTML = "";// 移除上次添加的子元素。
			    	    		for(var i=0,label; label = labels[i++]; ){
			    	    			var liDom = document.createElement("li");
			    	    			liDom.style.display = "inline";
			    	    			liDom.innerHTML = '<input name="labelChk" type="checkbox" value="' + label.id + '"><span style="color:' + label.fontColor + ';background-color:' + label.bgColor + '">' + label.name + '</span>&nbsp;&nbsp;';
			    	    			ulDom.appendChild(liDom);
			    	    		}
			    	    	}
			    	    	
			    	    }
			    	});
					
				}else{
					$('#labelSetWindow').window('open');
					// 获取标签
					$('#brandApplyLabels').datagrid({
						url : _path + '/label/brandApplyLabels',
						queryParams : {
							"campanyId" : 1// 根据商店名称进行查询标签
						},
						columns : [[{
							field:'ck',
							checkbox:"true"
						} , {
							field : 'name' ,
							align:'center',
							title : '标签名称'
						} , {
							field : 'type' ,
							align:'center',
							title : '标签类型',
							formatter: function(value, row, index){
								if(value == "0"){
									return "普通标签";
								}
							}
						},{
							field : 'companyName' ,
							align:'center',
							title : '店铺名称'
						} , {
							field : 'remark',
							align:'center',
							title : '标签备注'
						} , {
							field : 'fontColor',
							align:'center',
							title : '字体颜色',
							formatter: function(value, row, index){
								return "<span style='background-color:" + value + "'>&nbsp;&nbsp;&nbsp;&nbsp;</span>";
							}
						} , {
							field : 'bgColor',
							align:'center',
							title : '背景颜色',
							formatter: function(value, row, index){
								return "<span style='background-color:" + value + "'>&nbsp;&nbsp;&nbsp;&nbsp;</span>";
							}}, {
								field : 'action' ,
								title : '操作',
								align : 'center',
								width : 280,
								formatter: function(value, row, index){
									return "<a href='#' onclick='editLabel(" + JSON.stringify(row) + ")'><span style='margin-right:10px;color:blue;'>编辑</span></a>";
								}
							} 
						]] ,
						pagination : true ,
						rownumbers : true ,
						fitColumns : false ,
						singleSelect : false ,
						remoteSort : false ,
						//height:$(window).height()-30,
						width : 790,
						height : 480,
						pageSize: 20,
					    pageList: [20,50,100,200],
						multiSort : true ,
						ctrlSelect : true ,
						loadFilter : function(data){
							if (data.rows){
								return data;
							} else{
								return data.content;
							}
						},
						onLoadSuccess:function(data){
							
						}
					});
				}
			}
		});
	}*/
	
	//查看全部品牌申请记录
	$('#brandApplyAll').datagrid({
		url : _path + '/brand/brandApplyAll',
		columns : [[{
			field:'ck',
			checkbox:"true"
		}, {
			field : 'brandName' ,
			align:'center',
			title : '品牌名称',
			width : 100,
		} , {
			field : 'brandKeywords' ,
			align:'center',
			title : '品牌别名',
			width : 100,
		} , {
			field : 'companyName',
			align:'center',
			title : '店铺名称',
			width : 100,
		} , {
			field : 'catalogName',
			align:'center',
			title : '所属分类',
			width : 100,
		} , {
			field : 'brandUrl' ,
			align:'center',
			title : '品牌网址',
			width : 150,
		} , {
			field : 'status' ,
			align:'center',
			title : '审核状态',
			width : 100,
			formatter: function(value, row, index){
				if( value == "0" ){
					return "待审核";
				}else if(value == "1" ){
					return "审核通过";
				}else if(value == "3" ){
					return "撤回审核";
				}else{
					return "审核不通过";
				}
			}
		} , {
			field : 'type' ,
			align:'center',
			title : '申请类型',
			width : 100,
			formatter: function(value, row, index){
				if(value == "0"){
					return "新品牌申请";
				}else{
					return "品牌使用";
				}
			}
		}, /* {
			field : 'labelNames' ,
			align:'center',
			title : '标签',
			formatter: function(value, row, index){
				if(value.length > 0){
					var str = "";
					for(var i=0; i < value.length; i++){
						str += '<span style="color:' + row.labelFontColors[i] + ';background-color:' + row.labelBgColors[i] + '">' + row.labelNames[i] + '</span>&nbsp;&nbsp;';
					}
					return str;
				}else{
					return "";
				}
			}
		},*/{
			field : 'action' ,
			title : '操作',
			align : 'center',
			width : 280,
			formatter: function(value, row, index){
				if(row.status == "0"){
					return "<a href='#' onclick='applyBrandPass(" + JSON.stringify(row) + ")'><span style='margin-right:10px;color:blue;'>审核通过</span></a><a href='#' onclick='applyBrandRefuse(" + JSON.stringify(row) + ")'><span style='color:blue;'>审核不通过</span></a>";
				}else{
					return "--";
				}
			}
		} ]] ,
		toolbar : '#toolbar-all',
		pagination : true ,
		rownumbers : true ,
		fitColumns : false ,
		remoteSort : false ,
		multiSort : true ,
		//height:$(window).height()-30,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageSize: 25,
	    pageList: [25,50,100,200],
		singleSelect: false ,
		ctrlSelect : true ,
		nowrap: false,
		view : detailview,
			detailFormatter : function(index, row) {
				return '<div class="brandDetailAll"  style="padding:10px 0px;margin:10px auto;"></div>';
			},
			onExpandRow : function(index, row) {
				var ddv = $(this).datagrid('getRowDetail', index).find('div.brandDetailAll');
				ddv.panel({
					height : 200,
					border : false,
					cache : false,
					content : shopBrandDetailContent(row),
					onLoad : function() {
						$('#brandApplyAll').datagrid('fixDetailRowHeight', index);
					}
				});
				$('#brandApplyAll').datagrid('fixDetailRowHeight', index);
			},
		loadFilter : function(data){
			if (data.rows){
				return data;
			} else{
				return data.content;
			}
		},
		onLoadSuccess : function(data){
			var len = data.rows.length;
			updateTabsTitles(len, 0);
		}
	});
	//查看品牌申请审核中记录
	$('#brandApplying').datagrid({
		url : _path + '/brand/brandApplying',
		columns : [[{
			field:'ck',
			checkbox:"true"
		}, {
			field : 'brandName' ,
			align:'center',
			title : '品牌名称'
		} , {
			field : 'brandKeywords' ,
			align:'center',
			title : '品牌别名'
		},{
			field : 'companyName' ,
			align:'center',
			title : '店铺名称'
		} , {
			field : 'catalogName',
			align:'center',
			title : '所属分类'
		} ,{
			field : 'brandUrl' ,
			align:'center',
			title : '品牌网址'
		} , {
			field : 'status' ,
			align:'center',
			title : '审核状态',
			formatter: function(value, row, index){
				if( value == "0" ){
					return "待审核";
				}else if(value == "1" ){
					return "审核通过";
				}else if(value == "3" ){
					return "撤回审核";
				}else{
					return "审核不通过";
				}
			}
		} , {
			field : 'type' ,
			align:'center',
			title : '申请类型',
			formatter: function(value, row, index){
				if(value == "0"){
					return "新品牌申请";
				}else{
					return "品牌使用";
				}
			}
		} /*, {
			field : 'labelNames' ,
			align:'center',
			title : '标签',
			formatter: function(value, row, index){
				if(value.length > 0){
					var str = "";
					for(var i=0; i < value.length; i++){
						str += '<span style="color:' + row.labelFontColors[i] + ';background-color:' + row.labelBgColors[i] + '">' + row.labelNames[i] + '</span>&nbsp;&nbsp;';
					}
					return str;
				}else{
					return "";
				}
			}
		}*/,{
			field : 'action' ,
			title : '操作',
			align:'center',
			width : 280,
			formatter: function(value, row, index){
				return "<a href='#' onclick='applyBrandPass(" + JSON.stringify(row) + ")'><span style='margin-right:10px;color:blue;'>审核通过</span></a><a href='#' onclick='applyBrandRefuse(" + JSON.stringify(row) + ")'><span style='color:blue;'>审核不通过</span></a>";
			}
		} ]] ,
		toolbar : '#toolbar-ing',
		pagination : true ,
		rownumbers : true ,
		fitColumns : false ,
		singleSelect : false ,
		remoteSort : false ,
		multiSort : true ,
	//	height:$(window).height()-30,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageSize: 25,
	    pageList: [25,50,100,200],
		ctrlSelect : true ,
		nowrap: false,
		view : detailview,
			detailFormatter : function(index, row) {
				return '<div class="brandDetailApplying"  style="padding:10px 0px;margin:10px auto;"></div>';
			},
			onExpandRow : function(index, row) {
				var ddv = $(this).datagrid('getRowDetail', index).find('div.brandDetailApplying');
				ddv.panel({
					height : 200,
					border : false,
					cache : false,
					content : shopBrandDetailContent(row),
					onLoad : function() {
						$('#brandApplying').datagrid('fixDetailRowHeight', index);
					}
				});
				$('#brandApplying').datagrid('fixDetailRowHeight', index);
			},
		loadFilter : function(data){
			if (data.rows){
				return data;
			} else{
				return data.content;
			}
		},
		onLoadSuccess:function(data){
			var len = data.rows.length;
			updateTabsTitles(len, 1);
		}
	});
	//查看品牌申请审核通过的记录
	$('#brandApplyPass').datagrid({
		url : _path + '/brand/brandApplyPass',
		columns : [[{
			field:'ck',
			checkbox:"true"
		},{
			field : 'brandName' ,
			align:'center',
			title : '品牌名称'
		} , {
			field : 'brandKeywords' ,
			align:'center',
			title : '品牌别名'
		},{
			field : 'companyName' ,
			align:'center',
			title : '店铺名称'
		} , {
			field : 'catalogName',
			align:'center',
			title : '所属分类'
		} ,{
			field : 'brandUrl' ,
			align:'center',
			title : '品牌网址'
		} , {
			field : 'status' ,
			align:'center',
			title : '审核状态',
			formatter: function(value, row, index){
				if( value == "0" ){
					return "待审核";
				}else if(value == "1" ){
					return "审核通过";
				}else if(value == "3" ){
					return "撤回审核";
				}else{
					return "审核不通过";
				}
			}
		} , {
			field : 'type' ,
			align:'center',
			title : '申请类型',
			formatter: function(value, row, index){
				if(value == "0"){
					return "新品牌申请";
				}else{
					return "品牌使用";
				}
			}
		} /*, {
			field : 'labelNames' ,
			align:'center',
			title : '标签',
			formatter: function(value, row, index){
				if(value.length > 0){
					var str = "";
					for(var i=0; i < value.length; i++){
						str += '<span style="color:' + row.labelFontColors[i] + ';background-color:' + row.labelBgColors[i] + '">' + row.labelNames[i] + '</span>&nbsp;&nbsp;';
					}
					return str;
				}else{
					return "";
				}
			}
		}*/]] ,
		toolbar : '#toolbar-pass',
		pagination : true ,
		rownumbers : true ,
		fitColumns : false ,
		singleSelect : false ,
		remoteSort : false ,
		multiSort : true ,
		//height:$(window).height()-30,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageSize: 25,
	    pageList: [25,50,100,200],
		ctrlSelect : true ,
		nowrap: false,
		view : detailview,
		detailFormatter : function(index, row) {
			return '<div class="brandDetailPass"  style="padding:10px 0px;margin:10px auto;"></div>';
		},
		onExpandRow : function(index, row) {
			var ddv = $(this).datagrid('getRowDetail', index).find('div.brandDetailPass');
			ddv.panel({
				height : 200,
				border : false,
				cache : false,
				content : shopBrandDetailContent(row),
				onLoad : function() {
					$('#brandApplyPass').datagrid('fixDetailRowHeight', index);
				}
			});
			$('#brandApplyPass').datagrid('fixDetailRowHeight', index);
		},
		loadFilter : function(data){
			if (data.rows){
				return data;
			} else{
				return data.content;
			}
		},
		onLoadSuccess:function(data){
			var len = data.rows.length;
			updateTabsTitles(len, 2);
		}
	});
	//品牌申请审核不通过
	$('#brandApplyRefuse').datagrid({
		url : _path + '/brand/brandApplyRefuse',
		columns : [[{
			field:'ck',
			checkbox:"true"
		},{
			field : 'brandName' ,
			align:'center',
			title : '品牌名称'
		} , {
			field : 'brandKeywords' ,
			align:'center',
			title : '品牌别名'
		},{
			field : 'companyName' ,
			align:'center',
			title : '店铺名称'
		} , {
			field : 'catalogName',
			align:'center',
			title : '所属分类'
		} ,{
			field : 'brandUrl' ,
			align:'center',
			title : '品牌网址'
		} , {
			field : 'status' ,
			align:'center',
			title : '审核状态',
			formatter: function(value, row, index){
				if( value == "0" ){
					return "待审核";
				}else if(value == "1" ){
					return "审核通过";
				}else if(value == "3" ){
					return "撤回审核";
				}else{
					return "审核不通过";
				}
			}
		} ,{
			field : 'failReason' ,
			align:'center',
			title : '不通过原因'
		}, {
			field : 'type' ,
			align:'center',
			title : '申请类型',
			formatter: function(value, row, index){
				if(value == "0"){
					return "新品牌申请";
				}else{
					return "品牌使用";
				}
			}
		}/* , {
			field : 'labelNames' ,
			align:'center',
			title : '标签',
			formatter: function(value, row, index){
				if(value.length > 0){
					var str = "";
					for(var i=0; i < value.length; i++){
						str += '<span style="color:' + row.labelFontColors[i] + ';background-color:' + row.labelBgColors[i] + '">' + row.labelNames[i] + '</span>&nbsp;&nbsp;';
					}
					return str;
				}else{
					return "";
				}
			}
		}*/]] ,
		toolbar : '#toolbar-Refuse',
		pagination : true ,
		rownumbers : true ,
		fitColumns : false ,
		singleSelect : false ,
		remoteSort : false ,
		multiSort : true ,
	//	height:$(window).height()-30,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageSize: 25,
	    pageList: [25,50,100,200],
		ctrlSelect : true ,
		nowrap: false,
		view : detailview,
		detailFormatter : function(index, row) {
			return '<div class="brandDetailRefuse"  style="padding:10px 0px;margin:10px auto;"></div>';
		},
		onExpandRow : function(index, row) {
			var ddv = $(this).datagrid('getRowDetail', index).find('div.brandDetailRefuse');
			ddv.panel({
				height : 200,
				border : false,
				cache : false,
				content : shopBrandDetailContent(row),
				onLoad : function() {
					$('#brandApplyRefuse').datagrid('fixDetailRowHeight', index);
				}
			});
			$('#brandApplyRefuse').datagrid('fixDetailRowHeight', index);
		},
		loadFilter : function(data){
			if (data.rows){
				return data;
			} else{
				return data.content;
			}
		},
		onLoadSuccess:function(data){
			var len = data.rows.length;
			updateTabsTitles(len, 4);
		}
	});
	
	
	//查看撤回品牌记录
	$('#brandApplyCancel').datagrid({
		url : _path + '/brand/brandApplyCancel',
		columns : [[{
			field:'ck',
			checkbox:"true"
		},{
			field : 'brandName' ,
			align:'center',
			title : '品牌名称'
		} , {
			field : 'brandKeywords' ,
			align:'center',
			title : '品牌别名'
		},{
			field : 'companyName' ,
			align:'center',
			title : '店铺名称'
		} , {
			field : 'catalogName',
			align:'center',
			title : '所属分类'
		} ,{
			field : 'brandUrl' ,
			align:'center',
			title : '品牌网址'
		} , {
			field : 'status' ,
			align:'center',
			title : '审核状态',
			formatter: function(value, row, index){
				if( value == "0" ){
					return "待审核";
				}else if(value == "1" ){
					return "审核通过";
				}else if(value == "3" ){
					return "撤回审核";
				}else{
					return "审核不通过";
				}
			}
		} , {
			field : 'type' ,
			align:'center',
			title : '申请类型',
			formatter: function(value, row, index){
				if(value == "0"){
					return "新品牌申请";
				}else{
					return "品牌使用";
				}
			}
		} /*, {
			field : 'labelNames' ,
			align:'center',
			title : '标签',
			formatter: function(value, row, index){
				if(value.length > 0){
					var str = "";
					for(var i=0; i < value.length; i++){
						str += '<span style="color:' + row.labelFontColors[i] + ';background-color:' + row.labelBgColors[i] + '">' + row.labelNames[i] + '</span>&nbsp;&nbsp;';
					}
					return str;
				}else{
					return "";
				}
			}
		}*/]] ,
		toolbar : '#toolbar-cancel',
		pagination : true ,
		rownumbers : true ,
		fitColumns : false ,
		singleSelect : false ,
		remoteSort : false ,
		multiSort : true ,
		//height:$(window).height()-30,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageSize: 25,
	    pageList: [25,50,100,200],
		ctrlSelect : true ,
		nowrap: false,
		view : detailview,
		detailFormatter : function(index, row) {
			return '<div class="brandDetailPass"  style="padding:10px 0px;margin:10px auto;"></div>';
		},
		onExpandRow : function(index, row) {
			var ddv = $(this).datagrid('getRowDetail', index).find('div.brandDetailPass');
			ddv.panel({
				height : 200,
				border : false,
				cache : false,
				content : shopBrandDetailContent(row),
				onLoad : function() {
					$('#brandApplyPass').datagrid('fixDetailRowHeight', index);
				}
			});
			$('#brandApplyPass').datagrid('fixDetailRowHeight', index);
		},
		loadFilter : function(data){
			if (data.rows){
				return data;
			} else{
				return data.content;
			}
		},
		onLoadSuccess:function(data){
			var len = data.rows.length;
			updateTabsTitles(len, 3);
		}
	});
	
});

function getSelectedRows(){
	var currTab = self.$('#brandTabs').tabs('getSelected');
	var tabId = currTab.panel('options').id;
	var selectRows = null;
	if(tabId == "all"){
		selectRows = $("#brandApplyAll").datagrid('getChecked');
	}else if(tabId == "ing"){
		selectRows = $("#brandApplying").datagrid('getChecked');
	}else if(tabId == "pass"){
		selectRows = $("#brandApplyPass").datagrid('getChecked');
	}else if(tabId == "cancel"){
		selectRows = $("#brandApplyCancel").datagrid('getChecked');
	}else {
		selectRows = $("#brandApplyRefuse").datagrid('getChecked');
	}
	return selectRows;
}

//删除品牌申请记录
function deletedBrandApply(){
	var selectRows = getSelectedRows();
	if(selectRows.length == 0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var ids =[];
	$.each(selectRows, function(i,v){
		ids[i] = v.companyBrandId;
	});
	var params={};
	params.ids = ids;
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/brand/deleteBrandApply',
		    	    type:"post",
		    	    dataType:"json",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
		    	    		reloadBrandApplyData();
			    		} else {
			    			easyuiMsg('错误', "保存失败！");
			    		}
		    	    }
		    	});
		    }
		}
	);
}

//品牌申请审核通过
function applyBrandPass(data){
	$.messager.confirm('确认', '您确认审核通过吗？', function(r) {
		if (r) {
			data.status = "1";
			$.ajax({
				url : _path + '/brand/updateBrandBusiness',
				data : data,
				type : "post",
				datatype : "json",
				success : function(data) {
						if (data.head.retCode == '000000') {console.log("ddd");
							reloadBrandApplyData();
						}else {
							$.messager.alert('错误', '保存失败', 'error');
						}
					}
			 });

		}
	});
	
	
}

//品牌申请审核不通过
function applyBrandRefuse(data){
//	$("#reason").val('');
//	$("#refuseWindow").window('open');
	$.messager.prompt('审核不通过', '不通过原因', function(reason){
		if (reason!=undefined){
			if(reason!==''){
			data.status = "2";
			data.failReason = reason;
			$.ajax({
				url : _path + '/brand/updateBrandBusiness',
				data : data,
				type : "post",
				datatype : "json",
				success : function(data) {
						if (data.head.retCode == '000000') {
							reloadBrandApplyData();
						}else {
							$.messager.alert('错误', '保存失败', 'error');
						}
					}
			 });
			}else{
				easyuiMsg('提示', "请输入不通过的原因！");
			}
		}
	});
}

//不通过
//function saveRefuse(){
//	if($("#reason_textarea").val().trim()==''){
//		easyuiMsg('提示', "请输入不通过的原因！");
//		return false;
//	}
//	var data = {};
//	data.status = "2";
//	data.failReason = $("#reason_textarea").val();
//	$.ajax({
//		url : _path + '/brand/updateBrandBusiness',
//		data : data,
//		type : "post",
//		datatype : "json",
//		success : function(data) {
//				if (data.head.retCode == '000000') {
//					$("#refuseWindow").window('close');
//					reloadBrandApplyData();
//				}else {
//					easyuiMsg('错误', '保存失败', 'error');
//				}
//			}
//	 });
//}

//导出数据----POI
function exportDataWithExcel(){
	alert("POI导出");
}

// 操作完之后需要重载数据
function reloadBrandApplyData(){
	window.location.href = _path+"/brand/apply_index";
	var currTab =  self.parent.$('#brandTabs').tabs('getSelected'); //获得当前品牌审核tab
	var url = $(currTab.panel('options').content).attr('src');
	self.parent.$('#brandTabs').tabs('update', {
		tab : currTab,
		options : {
			content:'<iframe name="indextab" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>'
		}
	});
}

//初始化添加新标签
function addNewLabel(){
	initSpectrum();
	$('#dataForm').form('clear');
	$('#modelWindow').window('open');
}

// 编辑标签
function editLabel(data){
	initSpectrum();
	$('#modelWindow').window('open');
	$('#dataForm').form('clear');
	$("#dataForm").form("load", data);
}

//删除标签
function deleteLabel(){
	var selectRows = $("#brandApplyLabels").datagrid('getChecked');
	
	if(selectRows.length == 0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var ids =[];
	$.each(selectRows, function(i,v){
		ids[i] = v.id;
	});
	var params={};
	params.ids = ids;
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/label/deleteLabels',
		    	    type:"post",
		    	    dataType:"json",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
		    	    	if (_data.head.retCode == '000000') {
		    	    		$('#modelWindow').window('close');
							$("#brandApplyLabels").datagrid('reload');
			    		} else {
			    			easyuiMsg('错误', "保存失败！");
			    		}
		    	    }
		    	});
		    	 
		    }
		}
	);
}

// 新增一个标签到数据库
function submitLabelForm(){
	var reg_data = biz.serializeObject($("#dataForm"));
	
	 if(!$("#dataForm").form('validate')){
		   return false;
	 }

	 var fontColor = document.getElementById("fontColor").value;
	 var bgColor = document.getElementById("bgColor").value;
	 if(fontColor == ""){
		 reg_data.fontColor = "#000000";
	 }else{
		 reg_data.fontColor = fontColor;
	 }
	 if(bgColor == ""){
		 reg_data.bgColor = "#cccccc";
	 }else{
		 reg_data.bgColor = bgColor;
	 }
	 var url = biz.rootPath();
	 if(reg_data.campanyId == ""){
		 url = url + "/label/insertLabel";
	 }else{
		 url = url + "/label/updateLabel";
	 }
	 $.ajax({
		url : url,
		data : reg_data,
		type : "post",
		datatype : "json",
		success : function(data) {
				if (data.head.retCode == '000000') {
					$.messager.alert('提示', '保存成功', 'info', function(){
						$('#modelWindow').window('close');
						$("#brandApplyLabels").datagrid('reload');
					});
				} else if (data.head.retCode == '000004'){
					easyuiMsg('错误',"该标签名称已经存在！");
				}else {
					easyuiMsg('错误', '保存失败');
				}
			}
	 });
}

function saveBrandLabel(){
	var selectRows = getSelectedRows();
	var chk_value = [];    
	$('input[name="labelChk"]:checked').each(function(){    
		chk_value.push($(this).val());    
	});
	
	var ids = [];
	$.each(selectRows, function(i,v){
		ids[i] = v.companyBrandId;
	});
	$.ajax({
		url : _path + '/brand/markLabelForBrand',
		data : {
			"brandIds" : ids,
			"labelIds" : chk_value
		},
		type : "post",
		datatype : "json",
		success : function(data) {
				if (data.head.retCode == '000000') {
					$('#modelLabelWindow').window('close');
					reloadBrandApplyData();
				}else {
					$.messager.alert('错误', '保存失败', 'error');
				}
			}
	 });
}

//显示品牌详情
function shopBrandDetailContent(row){
	var html ='<table width="100%" border="1" cellspacing="1" cellpadding="0" class="gridlist">'+
	'<colgroup><col style="width:8%">'+
	'<col style="width:8%">'+
    '<col style="width:15%">'+
	'<col style="width:10%">'+
	'<col style="width:10%">'+
    '<col style="width:10%">'+
	'<col style="width:10%">'+
	'<col style="width:30%">'+
      '</colgroup><thead>'+
	  '<tr>'+
       ' <th>品牌名称</th>'+
       ' <th>品牌别名</th>'+
       ' <th>店铺名称</th>'+
		'<th>品牌图标</th>'+
        '<th>品牌资质文件</th>'+
        '<th>申请类型</th>'+
        '<th>审核状态</th>'+
        '<th>网址</th>'+
      '</tr>'+
      '</thead>'+
       ' <tbody><tr>'+
        '  <td width="8%">'+row.brandName+'</td>'+
         ' <td width="8%">'+row.brandKeywords+'</td>'+
         ' <td class="textleft">'+row.companyName+'</td>'+
          '<td width="10%"><img src="'+yoyo.imagesUrl+row.brandLogo+'"   width="60px"   height="60px" ></td>'+
           '<td width="10%"><img src="'+yoyo.imagesUrl+row.brandAptitude+'" width="60px"   height="60px"></td>';
			if(row.type=='0'){
				html += '<td >新品牌申请</td>';
			}else{
				html += '<td >品牌使用</td>';
			}
			if(row.status=='0'){
				html += '<td >待审核</td>';
			}else if(row.status=='1'){
				html += '<td >审核通过</td>';
			}else if(row.status=='3'){
				html += '<td >撤回审核</td>';
			}else{
				html += '<td >审核不通过</td>';
			}
			
          html+='<td  width="15%">'+row.brandUrl+'</td>'+
        '</tr>'+
    '</tbody></table>';
	
	html+= '<table width="100%" border="0" cellspacing="0" cellpadding="0" class="gridlist2">'+
	    '<thead>'+
	     '<tr>'+
	             '<th>详细说明</th>'+
	     ' </tr>'+
	   ' </thead>'+
	     ' <tbody><tr>'+
	              '<td >'+row.brandDesc+'</td>'+
	      '</tr>'+
	  '</tbody></table>';
	return html;
}