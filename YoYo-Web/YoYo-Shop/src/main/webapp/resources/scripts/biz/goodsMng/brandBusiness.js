//品牌管理

//初始化品牌管理列表
function initBrandListDg(){
	//分类对话框的实现
	getSelect2Data(_path+'/cate/cateTree','storeCat',true);
	$('#brandList_dg').datagrid({
		url : _path + '/brand/brandBusinessList' ,
		columns : [[{
			field : 'brandName' ,
			align:'center',
			title : '品牌名称'
		} , {
			field : 'brandKeywords' ,
			align:'center',
			title : '品牌别名'
		},{
			field : 'brandLogo' ,
			align:'center',
			title : '品牌图标',
			width : 100,
			formatter : function(value, row, index){
				if(value != ""){
					var str = yoyo.imagesUrl + value;
					//var imgTag = "<img src=" + str + ">"
					//return '<div><a href="' + str + '" target="_blank"><img src="' + str + '" onMouseOver="toolTip(' + "'" + imgTag + "'" + ')" onMouseOut="toolTip()" style="width : 100px; height : 80px;"></a></div>';
					return '<div><a href="' + str + '" target="_blank" ><img src="' + str + '" style="width : 100px; height : 80px;"></a></div>';
				}else{
					return "";
				}
			}
		} , {
			field : 'brandAptitude' ,
			align:'center',
			title : '品牌资质文件',
			width : 100,
			formatter : function(value,row,index){
				if(value != ""){
					var str = yoyo.imagesUrl  + value;
					return '<div><a href="' + str + '" target="_blank" ><img src="' + str + '" style="width : 100px; height : 80px;"></a></div>';
				}else{
					return "";
				}
			}
		} , {
			field : 'status' ,
			align:'center',
			title : '审核状态',
			formatter: function(value, row, index){
				if( value == "0" ){
					return "待审核";
				}else if(value == "1" ){
					return "审核通过";
				}else if (value=="3"){
					return "撤回审核";
				}else{
					return "审核不通过";
				}
			}
		} , {
			field : 'brandDesc' ,
			align:'center',
			width:370,
			title : '品牌介绍'
		} , {
			field : 'failReason' ,
			align:'center',
			title : '未通过原因'
		} , {
			field : 'action' ,
			title : '操作',
			align:'center',
			formatter: function(value, row, index){
				if(row.status == "3" && row.type=="0"){ 
					return "<a href='#' onclick='editBrandBusiness(" + JSON.stringify(row) + ")'><span style='margin-right:10px;color:blue;'>修改</span></a><a  onclick='deleteBrandBusiness(" + JSON.stringify(row) + ")'><span style='color:blue;'>删除</span></a>";
				}else if(row.type=="1" && row.status=="3"){
					return "<a  onclick='deleteBrandBusiness(" + JSON.stringify(row) + ")'><span style='color:blue;'>删除</span></a>";
				}
				else if(row.status == "0" ){
					return "<a href='#' onclick='cancelBrandBusiness(" + JSON.stringify(row) + ")'><span style='margin-right:10px;color:blue;'>撤回</span></a>";
				}else{
					return "--";
				}
			}
		}]] ,
		pagination : true ,
		rownumbers : true ,
		fitColumns : true ,
		remoteSort : false ,
		multiSort : true ,
		ctrlSelect : true ,
		nowrap:false,
		loadFilter : function(data){
			if (data.rows){
				return data;
			} else{
				return data.content;
			}
		}
		/*onLoadSuccess:function(data){
			
		}*/
	});
	
	//获取品牌分类的list
	//findArea(0,1,"area_1");
}

/**
 * 清空图片预览
 */
function clearImg(){
	$("#imgBrandLogo").attr('src',biz.rootPath() + "/resources/images/pre_default.png");
	$("#imgBrandAptitude").attr('src',biz.rootPath() + "/resources/images/pre_default.png");
}

//点击“添加品牌”按钮
function applyNewBrand(){
	clearImg();
	$("#addBrand_form").form("clear");
	$("#storeCat").val('-1').trigger("change");
	$('#addBrand').show();
	$('#addOldBrand').hide();
}

//保存新添加的品牌
function saveBrandBusiness(){
	 var reg_data = biz.serializeObject($("#addBrand_form"));
	 reg_data.storeCat = $("#storeCat").val();
	 if(!$("#addBrand_form").form('validate')){
		   return false;
	 }
	 
	 if( reg_data.storeCat == null || reg_data.storeCat == ""){
		$.messager.alert('提示', '请选择所属分类', 'info');
		return false;
	 }
	 if(reg_data.brandId == ""){
		 reg_data.brandId = null;
	 }
	 if(reg_data.type == ""){
		 reg_data.type = "0";
	 }
	 if(reg_data.status == "" || reg_data.status=="3"){
		 reg_data.status = "0";
	 }
	 if(reg_data.disabled == ""){
		 reg_data.disabled = "0";
	 }
	 
	 var url = biz.rootPath();
	 if(reg_data.companyBrandId == ""){
		 url = url + "/brand/insertBrandBusiness";
	 }else{
		 url = url + "/brand/updateBrandBusiness";
	 }
	 yoyo.ajaxRequest(url,reg_data,"","json",function(data){
			if (data.head.retCode == '000000') {
				$.messager.alert('提示', '保存成功', 'info', function(){
					$("#addBrand").hide();
					$("#brandList_dg").datagrid('reload');
				});
			}else if(data.head.retCode == '000012') {
				$.messager.alert('提示', '该品牌名称已经存在，请去"品牌使用申请"进行申请', 'info');
			}else if(data.head.retCode == '000004') {
				$.messager.alert('提示', '不能重复申请该品牌', 'info');
			}else {
				$.messager.alert('错误', '保存失败', 'error');
			}
	 });
}

//修改品牌信息
function editBrandBusiness(data){
	clearImg();
	$('#form-brand-add').form('clear');
	$("#addBrand_form").form("load", data);
	$("#storeCat").val(data.storeCat).trigger("change");
	if(data.brandLogo != ''){
		$("#imgBrandLogo").attr('src', yoyo.imagesUrl+ data.brandLogo);
	}
	if(data.brandAptitude != ''){
		$("#imgBrandAptitude").attr('src', yoyo.imagesUrl + data.brandAptitude);
	}
	$('#addBrand').show();
	$('#addOldBrand').hide();
}

//撤回品牌审核
function cancelBrandBusiness(data){
	$.messager.confirm('提示', '确定撤回该品牌审核吗？',function(r){
		if(r){
			 $.ajax({
					url : biz.rootPath() + "/brand/cancelBrandBusiness",
					data : data,
					type : "post",
					datatype : "json",
					success : function(data) {
							if (data.head.retCode == '000000') {
								$.messager.alert('提示', '撤回成功', 'info',function(){
									$("#brandList_dg").datagrid('reload');
								});
							}else {
								$.messager.alert('错误', '撤回失败', 'error');
							}
						}
				 });
		}
	});
}

//删除品牌
function deleteBrandBusiness(data){
	$.messager.confirm('提示', '确定删除该品牌吗？',function(r){
		if(r){
			 $.ajax({
					url : biz.rootPath() + "/brand/deleteBrandBusiness",
					data : data,
					type : "post",
					datatype : "json",
					success : function(data) {
							if (data.head.retCode == '000000') {
								$.messager.alert('提示', '删除成功', 'info',function(){
									$("#brandList_dg").datagrid('reload');
								});
							}else {
								$.messager.alert('错误', '删除失败', 'error');
							}
						}
				 });
		}
	});
}
var selectBrandIds = new Array();   //分页选择的已有品牌id
var selectBrandObj = {};   //分页选择的已有品牌对象
/**添加已有品牌申请*/
function applyOldBrand(){
	$('#brand-edit-relate').empty();
	$('#addOldBrand').show();
	$('#addBrand').hide();
}
/**选择已有品牌*/
function selectOldBrand(){
	 var selectBrand = new Array();   //已经选择的商品
	 $.each($('#brand-edit-relate').find('li'), function(index, liele) {
			var $liele = $(this);
			selectBrand.push($liele.attr('id'));
			selectBrandIds.push($liele.attr('id'));
			selectBrandObj[$liele.attr('id')]=$(this).find('span:first').html();
		});
		$('#brandTable').datagrid({
			url : _path + '/brand/brandList?brandType=-1',
			columns : [ [ {
				field:'ck',
				checkbox:"true"
			},{
				field : 'brandName',
				align : 'center',
				title : '品牌名称'			
			}, {
				field : 'brandKeywords',
				align : 'center',
				title : '品牌别名'
			},  {
				field : 'brandUrl',
				align : 'center',
				title : '品牌网址'
			} ] ],
			pagination : true,
			pagePosition : 'bottom',
			rownumbers : true,
			fitColumns : true,
			pageSize : 20,
			pageList : [ 20, 50, 100, 200 ],
			singleSelect : false,
			checkOnSelect:false,
			selectOnCheck:false,
			remoteSort : false,
			multiSort : true,
			loadFilter : function(data) {
				if (data.rows) {
					return data;
				} else {
					return data.content;
				}
			},
			onLoadSuccess:function(data){
				if(selectBrand.length>0){
					$.each(data.rows,function(i,v){
						if(selectBrand.indexOf(v.brandId+'')!=-1){  //已经被选择了
							$('#brandTable').datagrid('checkRow',i);
						}
					});
				}
			},
			onCheck:function(rowIndex,rowData){
				if(selectBrandIds.indexOf(rowData.brandId)==-1){ //判断是否存在，存在则不需要插入
					selectBrandIds.push(rowData.brandId);
					selectBrandObj[rowData.brandId]=rowData.brandName;
				}
			},
			onUncheck:function(rowIndex,rowData){
				selectBrandIds.remove(rowData.brandId);
			},onCheckAll:function(rows){   //点击全选按钮
				$.each(rows,function(i,rowData){
					if (selectBrandIds.indexOf(rowData.brandId) == -1) { // 判断是否存在，存在则不需要插入
						selectBrandIds.push(rowData.brandId);
						selectBrandObj[rowData.brandId]=rowData.brandName;
					}
				});
			} ,onUncheckAll:function(rows){
				$.each(rows,function(i,rowData){
					selectBrandIds.remove(rowData.brandId);
				});
			}
		});
		$("#oldBrandWindow").window({
			modal:false,
			resizable:false,
			draggable:false,
			collapsible:false,
			closed:true,
	    	maximized:false,
	    	minimizable:false,
	    	maximizable:false,
			onClose:function(){
				selectBrandIds=[];
				selectBrandObj = {}; 
			}
		});
		
		$('#oldBrandWindow').window('open');
		$("#oldBrandWindow").window("move",{top:$(document).scrollTop() + ($(window).height()-650) * 0.5});
		
	}

/**确定选择的已有品牌*/
function confirmOldBrand(){
	if(selectBrandIds.length==0){
		$.messager.show({ title : '提示' , msg : '请选择品牌!' });
		return false;
	}
	var oldBrand = $('#brand-edit-relate');
	oldBrand.empty();
	$.each(selectBrandIds,function(i,option){
		oldBrand.append($('<li id="' +option + '" ><a style="  margin-right: 10px;" onclick="deleteOldBrand(this)">删除</a><span style="  margin-right: 10px;">' + selectBrandObj[option] + '</span></li>'));
	});
	selectBrandIds=[];
	selectBrandObj = {};  
	$('#oldBrandWindow').window('close');
}

function deleteOldBrand(obj){
    $(obj).parent().remove();
}

/**保存已有品牌申请*/
function saveOldBrand(){
	var params = {};
	var oldBrandIds=[];
	$.each($('#brand-edit-relate').find('li'), function(index, liele) {
		oldBrandIds.push($(this).attr('id'));
	});
	if(oldBrandIds.length>0){
		 params.brandIds=oldBrandIds;
		 yoyo.ajaxRequest(_path+'/brand/insertOldBrandBusiness',params,"","json",function(data){
			if (data.head.retCode == '000000') {
				$.messager.alert('提示', '保存成功', 'info', function(){
					$("#addOldBrand").hide();
					$("#brandList_dg").datagrid('reload');
				});
			}else if(data.head.retCode == '000004') {
				$.messager.alert('提示', '不能重复申请该品牌', 'info');
			}else if (data.head.retCode == '000007') {
				easyuiMsg('提示', "申请成功！但是已去掉重复申请的品牌");
			}else {
				$.messager.alert('错误', '保存失败', 'error');
			}
	 });
	}
}