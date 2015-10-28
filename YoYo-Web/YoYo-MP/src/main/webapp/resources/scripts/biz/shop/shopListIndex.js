/**
 * 功能描述： 店铺列表js
 * 
 */
var $companyId=''; //当前选择行的店铺id
$(function() {
	$('#shopListDataGrid').datagrid({
		url : _path + '/shopCheck/findShopList?approved=1',
		columns : [ [ {
			field:'ck',
			checkbox:"true",
			width:'5%',
		},{
			field : 'companyName',
			sortable : true,
			halign: 'center',
			title : '公司名称',
			width:'120px',		
		}, {
			field : 'storeName',
			sortable : true,
			halign: 'center',
			title : '店铺名称',
			width:'120px',
		},{
			field : 'shopName',
			halign : 'center',
			halign: 'center',
			title : '店主姓名',
			width:"60px",
		}, {
			field : 'companyUrl',
			halign: 'center',
			title : '公司网址',
			width:"100px"
		}, {
			field : 'companyRemark',
			halign: 'center',
			width:'260px',
			sortable : true,
			title : '营业执照经营范围'
		},/* {
			field : 'regionCatName',
			halign: 'center',
			title : '店铺经营范围'
		},*/{
			field : 'lastTime',
			halign: 'center',
			sortable : true,
			title : '店铺有效期',
			width:'140px',
			formatter : function(value, row) {
				return (value!=null && value!='')?value.substring(0,value.length-2):'';
			}
		},{
			field : 'issueType',
			halign: 'center',
			title : '店铺类型',
			width:'60px',
			sortable : true,
			formatter : function(value, row) {
				if(value == "1"){ 
					return '集团';
				}else{
					return '单店';
				}
			}
		}, {
			field : 'approvedremark',
			halign: 'center',
			title : '审核备注',
			width:'100px',
		}, {
			field : 'approveTime',
			halign: 'center',
			title : '审核时间',
			width:'140px',
			sortable : true,
			formatter : function(value, row) {
				return (value!=null && value!='')?value.substring(0,value.length-2):'';
			}
		}, {
			field : 'approvedTime',
			halign: 'center',
			title : '审核通过时间',
			width:'140px',
			sortable : true,
			formatter : function(value, row) {
				return (value!=null && value!='')?value.substring(0,value.length-2):'';
			}
			
		},{
			field : 'applyTime',
			halign: 'center',
			title : '申请时间',
			width:'140px',
			sortable : true,
			formatter : function(value, row) {
				return (value!=null && value!='')?value.substring(0,value.length-2):'';
			}
		}, {
			field : 'editor',
			title : '操作',
			width : '200px',
			halign: 'center',
			formatter : function(value, row) {
				var html ="<a href='javascript:violationShop("+JSON.stringify(row)+")'>店铺违规</a>";
				html+="<a style='margin-left:2px' href='javascript:editShopGoodsCheck("+JSON.stringify(row)+")'>修改商品审核</a>";
				html+="<a style='margin-left:2px' href='javascript:editShop("+JSON.stringify(row)+")'>编辑</a>";
				return  html;
			}
		} ] ],
		toolbar : '#toolbar',
		pagination : true,
		rownumbers : true,
		//fitColumns : false,
		singleSelect : true,
		checkOnSelect:false,
		selectOnCheck:false,
		remoteSort : true,
		multiSort : true,
		nowrap : false,
		autoRowHeight:true,
	//	height:$(window).height()-2,
		width : ($(window).width() * 0.99),
		height : ($(window).height() * 0.95),
		pageSize: 25,
	    pageList: [25,50,100,200],
	    view : detailview,
		detailFormatter : function(index, row) {
			return '<div class="shopCheckDetail"  style="padding:10px 0px;margin:10px auto;"></div>';
		},
		onExpandRow : function(index, row) {
			var ddv = $(this).datagrid('getRowDetail', index).find('div.shopCheckDetail');
			$('#shop_datagrid_index').val(index);
			$('#shopCompanyId').val(row.companyId);
			ddv.panel({
				height : 450,
				border : false,
				cache : false,
				//content : shopCheckDetailContent(row),
				href:_path+'/shop/getDetail?companyId='+row.companyId,
				onLoad : function() {
					$('#shopListDataGrid').datagrid('fixDetailRowHeight', index);
				}
			});
			$('#shopListDataGrid').datagrid('fixDetailRowHeight', index);
		},
		 loadFilter: function (data) { 
		        for (var i = 0; i < data.content.rows.length; i++) { 
		               for (var att in data.content.rows[i]) { 
		                   if (typeof (data.content.rows[i][att]) == "string") {  //显示html代码为文本
		                       data.content.rows[i][att] = data.content.rows[i][att].replace(/</g, "&lt;").replace(/>/g, "&gt;"); 
		                   } 
		               } 
		        } 
		        return data.content; 
		    } 
	});
	
	
	//添加店铺
	var label = $('#addShop').menubutton({ menu: '#shopTag' }); 
	$(label.menubutton('options').menu).menu({
		onClick: function (item) {
			if(item.id == "group"){ //添加集团
				window.open(_path+"/shop/add?type=1");
			}else{
				window.open(_path+"/shop/add?type=0");
			}
		}
	});

	//加载违规类型
	findViolationCat();
	//加载分类
/*	$('#combox-cates').trapown(
			{ url : _path + '/comboBox/cateTreeCombox' , multiple : true , idField : 'catId' ,
				textField : 'catName' , parentField : 'parentCatId' , initParentId : 0 });*/
	
	$('input[name="isCheck"]').change(function(){
		if($(this).val()=='1'){//需要审核
			$('#combox-cates').combotree('setValues', []);
			getCategory($companyId);
			$('#catesTr').show();
		}else{
			$('#catesTr').hide();
		}
	});
});

/**设置有效期*/
function setShopTime(){
	$('#window_shop_date').window('open');
}

/**保存有效期*/
function saveShopTime(){
	var param_data = biz.serializeObject($("#form_shop_date"));
	var selectRows = $("#shopListDataGrid").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var ids =[];
	$.each(selectRows,function(i,v){
		ids[i]=v.companyId;
	});
	param_data.ids=ids;
	$.post(_path + '/shop/setShopTime', param_data, function(_data) {
		if (_data.head.retCode == '000000') {
			$('#shopListDataGrid').datagrid('reload', {});
			$('#window_shop_date').window('close');
		}else {
			easyuiMsg('错误', "保存失败！");
		}
	}, 'json');
}


/**删除*/
function deleteShop(){
	var param_data = {};
	var selectRows = $("#shopListDataGrid").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var ids =[];
	$.each(selectRows,function(i,v){
		ids[i]=v.companyId;
	});
	param_data.ids=ids;
	$.messager.confirm('确认','您确认想要删除店铺吗？',function(r){    
	    if (r){  
	    	$.ajax({
	    	    url:_path + '/shopCheck/delete',
	    	    type:"post",
	    	    data:param_data,
	    	    dataType:"json",
	    	    success:function(_data){
	    	    	if (_data.head.retCode == '000000') {
		    			easyuiMsg('提示', "删除成功！");
		    			$('#shopListDataGrid').datagrid('reload', {});
		    		} else {
		    			easyuiMsg('错误', "删除失败！");
		    		}
	    	    }
	    	});
	    	 
	    }
	}
	);
}

//加载违规类型
function findViolationCat(){
	var violationCat = $("#combox-violation-cat");
	var url = _path + '/storeViolationCat/findChildViolationCatList';
	$.getJSON(url, function(json) {
        for(var i = 0; i<json.length; i++){  
        	violationCat.append($("<option value='"+json[i].catId+"'/>").text(json[i].catName)); 
        }  
	});
}

//店铺违规
function violationShop(row){
	var stores = $("#combox-store");
	stores.empty();
	if(row&&row.companyId!=null&&row.companyId!=''&&row.companyId!='0'){
		var url = _path + '/shopCheck/findStoreByCompany?companyId='+row.companyId;
		$.getJSON(url, function(json) {
	        for(var i = 0; i<json.content.length; i++){  
	        	stores.append($("<option value='"+json.content[i].storeId+"'/>").text(json.content[i].storeName)); 
	        }  
	        $('#form-violation-add').form('clear');
	        $('#window-add-violation').dialog('open');
		});
	}
}

//保存店铺违规
function saveStoreViolation(){
	if ($('#form-violation-add').form('validate')) {
		var storeId = $('#combox-store').val();
		var catId = $('#combox-violation-cat').val();
		var remark = $('textarea[name="remark"]').val();
//		alert(storeId+"..."+catId+"..."+remark);
		if(!(storeId!=null&&storeId!='')){
			easyuiMsg('错误', "请选择分店");
			return;
		}
		if(!(catId!=null&&catId!='')){
			easyuiMsg('错误', "请选择违规类型");
			return;
		}
		if(!(remark!=null&&remark!='')){
			easyuiMsg('错误', "请填写备注");
			return;
		}
		
		var param_data = biz.serializeObject($("#form-violation-add"));
		$.post(_path + '/storeViolation/saveStoreViolation', param_data, function(_data) {
			if (_data.head.retCode == '000000') {
				$('#window-add-violation').window('close');
			}else if(_data.head.retCode == '000001'){
				easyuiMsg('错误', "必填项目不可为空");
			}else {
				easyuiMsg('错误', _data.head.retMsg);
			}
		}, 'json');
	}
}

/**修改店铺商品审核设置*/
function editShopGoodsCheck(row){
	$('#companyId').val(row.companyId);
	$companyId = row.companyId;
	if(row.isCheck=='1'){ //需要进行商品审核，则查找已经选择的分类
		$('input[name="isCheck"]:eq(0)').prop('checked', true);
		var param_data={};
		param_data.companyId=row.companyId;
		//先获取经营范围关联的分类
		$.ajax({
			url:_path+'/companyRegionCat/findGoodsCat',
			type:'post',
			dataType:'json',
			data:{'companyId':row.companyId},
			success:function(data){
				console.log(data);
				var identifier = [];
				if(data.head.retCode == '000000'){
					if(data.content!=null){
						$.each(data.content,function(k,v){
							if(v!=null){
								identifier.push(v.identifier);
							}
						});
					}
					
					$('#combox-cates').trapown(
							{ url : _path + '/comboBox/cateTreeCombox' , multiple : true , idField : 'catId' ,
								textField : 'catName' , parentField : 'parentCatId' , initParentId : 0,identifier:identifier });
					$.post(_path + '/shopCheck/getShopGoodsCat', param_data, function(data) {
						if (data.head.retCode == '000000') {
			 	    	    var ct = [];
			    			$.each(data.content,function(i,v){
			    				ct.push(v.catId);
			    			});
			    			$('#combox-cates').combotree('setValues', ct);
			    			$('#catesTr').show();
			    			$('#window_shop_good_list').window('open');
			    		} else {
			    			easyuiMsg('错误', "获取分类信息失败！");
			    		}
					}, 'json');
				}
				
			   }
		  });
		}else{
			$('input[name="isCheck"]:eq(1)').prop('checked', true);
			$('#catesTr').hide();
			$('#window_shop_good_list').window('open');
		}
	
}

//切换商品审核，由不需要审核改为需要审核，需要重新获取经营范围与商品分类的数据
function getCategory(companyId){
	$.ajax({
		url:_path+'/companyRegionCat/findGoodsCat',
		type:'post',
		dataType:'json',
		data:{'companyId':companyId},
		success:function(data){
			console.log(data);
			var identifier = [];
			if(data.head.retCode == '000000'){
				if(data.content!=null){
					$.each(data.content,function(k,v){
						if(v!=null){
							identifier.push(v.identifier);
						}
					});
				}
				
				$('#combox-cates').trapown(
						{ url : _path + '/comboBox/cateTreeCombox' , multiple : true , idField : 'catId' ,
							textField : 'catName' , parentField : 'parentCatId' , initParentId : 0,identifier:identifier });
			
			}
			
		   }
	  });
}

/**保存商品审核设置*/
function saveShopGoodsCheck(){
	var param_data = biz.serializeObject($("#form_shop_good_list"));
	if($('input[name="isCheck"]:checked ').val()=='1'){  //需要商品审核
		var category = $('#combox-cates').combotree('tree').tree('getChecked');
		var categoryIds = [];
		$.each(category,function(i,v){
			if(v.children.length==0){ //保存的是叶子节点
				categoryIds.push(v.id);
			}
		});
		if(categoryIds.length==0){
			easyuiMsg('错误',"请选择分类！");
			return false;
		}
		param_data.goodCategory = categoryIds;
	}
	
	$.post(_path + '/shopCheck/updateShopGoodCheck', param_data, function(_data) {
		if (_data.head.retCode == '000000') {
			$('#window_shop_good_list').window('close');
			$('#shopListDataGrid').datagrid('reload', {});
			easyuiMsg('提示', '保存成功！');
		}else {
			easyuiMsg('错误', '保存失败！');
		}
	}, 'json');
	
}


/**编辑店铺*/
function editShop(row){
	if(row.issueType == "1"){ //添加集团
		window.open(_path+"/shop/add?type=1&companyId="+row.companyId);
	}else{
		window.open(_path+"/shop/add?type=0&companyId="+row.companyId);
	}
}
