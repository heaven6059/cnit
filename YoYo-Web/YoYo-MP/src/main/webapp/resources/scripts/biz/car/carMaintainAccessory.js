/**
 * 功能描述： 保养配件的js
 * 
 */

var accIds=new Array();  //选择的配件类别id集合
var selectAccessory = new Array();  //已经选择的配件类别id集合，即：已经保存到数据中的，此次是修改
$(function() {
	initCateGrid();
});
function initCateGrid() {
	$('#table_maintian_acc').treegrid({
						url : _path + '/cate/findCategoryTree?identifier='+ yoyo.maintain,
						rownumbers : true,
						toolbar : '#toolbar-cate',
						idField : 'catId',
						treeField : 'catName',
						fitColumns : true,
						singleSelect : true,
						columns : [ [
								{
									field : 'catId',
									title : '节点id',
									hidden : true
								},
								{
									field : 'parentCatId',
									title : '父节点id',
									hidden : true
								},
								{
									field : 'catName',
									title : '保养项目',
									width : 400,
									fixed : true
								},
								{
									field : 'accessoryName',
									title : '保养配件',
									fixed : true
								},
								{
									field : 'isSet',
									title : '是否已设置配件',
									fixed : true,
									align : 'center',
									formatter : function(value, row) {
										if (row.childCount == 0) {
											if(row.accessoryName!=null && row.accessoryName!=''){
												return "<lable style='color:green'>已设置</lable>";
											}else{
												return "<lable style='color:red'>未设置</lable>";
											}
										}
									}
								},
								{
									field : 'editor',
									title : '操作',
									width : 200,
									formatter : function(value, row) {
										// 只有叶子节点才可以设置配件
										if (row.childCount == 0) {
											return '<a href="javascript:setMaintainAcc(' + row.catId+ ',\''+row.accessoryIds+'\')">设置配件</a>';
										}
									}
								} ] ],
						loadFilter : function(data) {
							for ( var i = 0; i < data.length; i++) {
								if (data[i].childCount > 0) {
									data[i].state = 'closed';
								} else {
									data[i].state = '';
								}
							}
							return data;
						},
						onBeforeExpand : function(row) {
							var url = _path + '/cate/getChildTree?parentCatId='	+ row.catId;
							$("#table_maintian_acc").treegrid("options").url = url;
							return true;
						},
						onDblClickCell : function(index, field, value) {

						},
						onLoadSuccess : function(row, data) {
							expandAll();
						}
					});
}

// 设置保养配件对话框
function setMaintainAcc(catId,ids) {
	selectAccessory = [];
	if(ids!=''||ids==null){
		selectAccessory = ids.split("|"); // 已经选择的配件
	}
	accIds=[];  //选择的配件类别id集合
	$('#car_accessory_tables').datagrid({
						url : _path + '/accessory/maintainAccessory',
						columns : [ [
								{
									field : 'catalogId',
									title : '类型编号',
									hidden : true,
									width : 100
								},
								{
									field:'ck',
									checkbox:"true"
								},
								{
									field : 'catalogName',
									title : '配件类型名称',
									width : 200,
									sortable : true,
									options : {
										querytype : 'like'
									}
								},
								{
									field : 'catId',
									title : '是否已绑定分类',
									width : 100,
									formatter : function(value, row) {
										if (value != null && value != '') {
											return '是';
										} else {
											return '否';
										}
									}
								}
								 ] ],
						pagination : true,
						rownumbers : true,
						singleSelect : false,
						remoteSort : true,
						multiSort : true,
						checkOnSelect:false,
						selectOnCheck:false,
						loadFilter : function(data) {
							if (data.rows) {
								return data;
							} else {
								return data.content;
							}
						}, onCheck : function(rowIndex, rowData) {
							if (accIds.indexOf(rowData.catalogId) == -1) { // 判断是否存在，存在则不需要插入
								accIds.push(rowData.catalogId);
							}
							if (selectAccessory.indexOf(rowData.catalogId) == -1) { 
								selectAccessory.push(rowData.catalogId);
							}
						}, onUncheck : function(rowIndex, rowData) {
							accIds.remove(rowData.catalogId);
							selectAccessory.remove(rowData.catalogId);
						},onCheckAll:function(rows){   //点击全选按钮
							$.each(rows,function(i,rowData){
								if (accIds.indexOf(rowData.catalogId) == -1) { // 判断是否存在，存在则不需要插入
									accIds.push(rowData.catalogId);
								}
								if (selectAccessory.indexOf(rowData.catalogId) == -1) { 
									selectAccessory.push(rowData.catalogId);
								}
							});
						} ,onUncheckAll:function(rows){
							$.each(rows,function(i,rowData){
								accIds.remove(rowData.catalogId);
								selectAccessory.remove(rowData.catalogId);
							});
						},
						 onLoadSuccess : function(data) {
							if (selectAccessory.length > 0) {
								$.each(data.rows, function(i, v) {
									if (selectAccessory.indexOf(v.catalogId) != -1) { // 已经被选择了
										$('#car_accessory_tables').datagrid('checkRow', i);
									}
								});
							}
						}
					});
	$("#catId").val(catId);
	$('#maintain_acc_window').window('open');
}


//取消设置配件对话框
function closeMaintainDialog(){
	$('#maintain_acc_window').window('close');
}

//选择配件类别
function getAccessoryId(){
	
	var params = {};
	params.categoryId=$("#catId").val(); //获取当前选择行的id
	if(accIds.length!=0){
		params.accIds = accIds.join(",");
	}else{
		params.accIds=[];
	}
	$.post(_path + '/carMaintainAccessory/saveMaintainAccessory', params, function(_data) {
		if (_data.head.retCode == '000000') {
			$('#table_maintian_acc').treegrid("options").url =  _path + '/cate/findCategoryTree?identifier='+ yoyo.maintain;
			$('#table_maintian_acc').treegrid('reload');
			$('#car_data_add').window('close');
		}else if (_data.head.retCode == '000007'){
			easyuiMsg('错误', "存在级联数据，不能取消原有配件类别！");
		}else{
			easyuiMsg('错误', "保存失败！");
		}
	}, 'json');
	
	$('#maintain_acc_window').window('close');
}

function expandAll() {
	$('#table_maintian_acc').treegrid('expandAll');
}
function collapseAll() {
	$('#table_maintian_acc').treegrid('collapseAll');
}

function onSelect(index, row) {
	if (editIndex != index) {
		if (endEditing()) {
			return true;
		} else {
			$('#table-attr-add').datagrid('selectRow', editIndex);
			return false;
		}
	}
}