/**
 * 功能描述： 店铺审核js
 * 
 */
$(function() {
	$('#shopCheckDataGrid').datagrid({
		url : _path + '/shopCheck/findShopList',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'companyName',
			sortable : true,
			halign: 'center',
			title : '公司名称'			
		}, {
			field : 'storeName',
			sortable : true,
			halign: 'center',
			title : '店铺名称'
		},{
			field : 'shopName',
			halign : 'center',
			halign: 'center',
			title : '店主姓名'
			
		}, {
			field : 'companyUrl',
			halign: 'center',
			title : '公司网址'
		}, {
			field : 'companyRemark',
			halign: 'center',
			width:160,
			sortable : true,
			title : '营业执照经营范围'
		}, {
			field : 'approved',
			halign: 'center',
			title : '审核状态',
			sortable : true,
			formatter : function(value, row) {
				if(value == "0"){
					return "待审核";
				}else if(value == "1"){
					return "审核通过";
				}else if(value == "2"){
					return "审核未通过";
				}
			}
		}, {
			field : 'approvedremark',
			halign: 'center',
			title : '审核备注'
		}, {
			field : 'approveTime',
			halign: 'center',
			sortable : true,
			title : '审核时间',
			formatter : function(value, row) {
				return (value!=null && value!='')?value.substring(0,value.length-2):'';
			}
		}, {
			field : 'approvedTime',
			halign: 'center',
			sortable : true,
			title : '审核通过时间',
			formatter : function(value, row) {
				return (value!=null && value!='')?value.substring(0,value.length-2):'';
			}
			
		},{
			field : 'applyTime',
			halign: 'center',
			sortable : true,
			title : '申请时间',
			formatter : function(value, row) {
				return (value!=null && value!='')?value.substring(0,value.length-2):'';
			}
		}, {
			field : 'editor',
			title : '操作',
			halign: 'center',
			formatter : function(value, row,index) {
				return  "<a href='javascript:shopCheck("+JSON.stringify(row)+","+index+")'>店铺审核</a>";
			}
		} ] ],
		toolbar : '#toolbar',
		pagination : true,
		rownumbers : true,
		fitColumns : false,
		singleSelect : false,
		checkOnSelect:false,
		selectOnCheck:false,
		remoteSort : true,
		multiSort : true,
		nowrap : false,
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
			$('#datagrid_index').val(index);
			$('#companyId').val(row.companyId);
			ddv.panel({
				height : 450,
				border : false,
				cache : false,
				//content : shopCheckDetailContent(row),
				href:_path+'/shopCheck/getDetail?companyId='+row.companyId,
				onLoad : function() {
					$('#shopCheckDataGrid').datagrid('fixDetailRowHeight', index);
				}
			});
			$('#shopCheckDataGrid').datagrid('fixDetailRowHeight', index);
		},
	/*	loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}*/
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

	
	
});



function shopCheck(row,index){
	$('#approvedremark').val('');
	$("#shopCheckDataGrid").datagrid('checkRow',index);
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
				
				$('#combox-cate').trapown(
						{ url : _path + '/comboBox/cateTreeCombox' , multiple : true , idField : 'catId' ,
							textField : 'catName' , parentField : 'parentCatId' , initParentId : 0,identifier:identifier });
				$('#approved').change(function(){ 
					if($(this).val()=='1'){	//审核通过
						$('#checkTr').show();
					}else{
						$('#checkTr').hide();
						$('#cateTr').hide();
					}
				});
				
				$('input[name="isCheck"]').change(function(){
					if($(this).val()=='1'){//需要审核
						$('#combox-cate').combotree('setValues', []);
						$('#cateTr').show();
					}else{
						$('#cateTr').hide();
					}
				});
				$('input[name="isCheck"]:eq(1)').prop('checked', true);
				$('#approved').val('0');
				$('#cateTr').hide();
				$('#checkTr').hide();
				$('#window_shop_check').window('open');
				
				
			}else{
				easyuiMsg('错误', "获取商品分类失败！");
			}
		}
		
		
	});

}

function saveShopCheck(){
	var param_data = biz.serializeObject($("#form_shop_check"));
	var selectRows = $("#shopCheckDataGrid").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	
	if($("#approved").val()!='1'){
		if($('#approvedremark').val().trim()=='' || $('#approvedremark').val()=='最多输入1000个字符'){
			easyuiMsg('提示', "请填写备注！");
			return false;
		}
		
	}else{
		if($('input[name="isCheck"]:checked ').val()=='1'){  //需要商品审核
			var category = $('#combox-cate').combotree('tree').tree('getChecked');
			var categoryIds = [];
			$.each(category,function(i,v){
				categoryIds.push(v.id);
			});
			if(categoryIds.length==0){
				easyuiMsg('错误',"请选择分类！");
				return false;
			}
			param_data.goodCategory = categoryIds;
		}
	}
	
	if($('#approvedremark').val().length>1000){
		param_data.approvedremark=$('#approvedremark').val().substring(0,1000);
	}
	
	
	var ids =[];
	var accountIds=[];
	var issueTypes = [];
	$.each(selectRows,function(i,v){
		ids[i]=v.companyId;
		accountIds[i]=v.accountId;
		issueTypes[i]=v.issueType;
	});
	param_data.ids=ids;
	param_data.accountIds=accountIds;
	param_data.issueTypes=issueTypes;
	$.post(_path + '/shopCheck/checking', param_data, function(_data) {
		if (_data.head.retCode == '000000') {
			$('#shopCheckDataGrid').datagrid('reload', {});
			$('#window_shop_check').window('close');
		}else {
			easyuiMsg('错误', "保存失败！");
		}
	}, 'json');
}


function deleteCheck(){
	var param_data = {};
	var selectRows = $("#shopCheckDataGrid").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var ids =[];
	$.each(selectRows,function(i,v){
		ids[i]=v.companyId;
	});
	param_data.ids=ids;
	$.messager.confirm('确认','您确认想要删除店铺申请吗？',function(r){    
	    if (r){  
	    	$.ajax({
	    	    url:_path + '/shopCheck/delete',
	    	    type:"post",
	    	    data:param_data,
	    	    dataType:"json",
	    	    success:function(_data){
	    	    	if (_data.head.retCode == '000000') {
		    			easyuiMsg('提示', "删除成功！");
		    			$('#shopCheckDataGrid').datagrid('reload', {});
		    		} else {
		    			easyuiMsg('错误', "删除失败！");
		    		}
	    	    }
	    	});
	    	 
	    }
	}
	);
}