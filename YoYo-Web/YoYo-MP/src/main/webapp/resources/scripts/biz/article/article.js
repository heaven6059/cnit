/**
 * 功能描述： 汽车车型的js
 * 
 */
$(function() {
	//查询违规处理列表
	initList();
	
	//查询文章栏目
	initArticleNodesTree('search-combox-article-nodes', false);

});
//查询违规处理列表
function initList(){
	$('#table-article').datagrid({
		url : _path + '/article/findArticleList',
		columns : [ [ {
			field:'ck',
			checkbox:"true",
			width:'2%'
		},{
			field : 'articleId',
			align : 'center',
			hidden: 'true',
			title : '文章id'			
		},{
			field : 'title',
			align : 'center',
			title : '文章标题',
			sortable: true
		}, {
			field : 'nodeName',
			align : 'center',
			title : '所属分类',
			sortable: true
		}, {
			field : 'createTime',
			align : 'center',
			title : '添加时间',
			formatter: function(value,rows,index){
				if(value){
					var time=new Date();
					time.setTime(value);	
	    			return time.format("yyyy-MM-dd hh:mm:ss");//引用YoYo-MP中common.js
				}
			}
		},{
			field: 'editor',
			align: 'center',
			title: '操作',
			width: '8%',
			formatter: function(value,rows,index){
				return "<a href='"+_path+"/article/editArticle?id="+rows.id+"' class='btn-grey'>编辑</a>&nbsp;"+
//				 	   "<a href='javascript:void(0);' onclick='deleteViolation("+rows.violationId+");' class='btn-grey'>删除</a>";
						"";
			}
		}]],
		toolbar : '#toolbar-article',
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		striped:true,
		fitColumns : true,
		pageSize : 25,
		pageList : [ 25, 50, 100, 150 ],
		singleSelect : true,
		checkOnSelect:false,
		selectOnCheck:false,
		remoteSort : false,
		selectOnCheck:false,
		multiSort : true,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		},onDblClickCell : function(index , field , value){
			openSaveDialog("edit",JSON.stringify(rows));
		}
	});
	
}

function initArticleNodesTree(obj, multiple) {
	var $obj = $('#' + obj);
	$obj.combotree({
		url : _path + '/articleNodes/findArticleNodesList',
		multiple : multiple,
		queryParams : {
			'parentId' : 0
		},
		columns : [[{
			field : 'nodeId',
			title : '栏目Id',
			fixed : true
		}, {
			field : 'nodeName',
			title : '栏目名称',
			fixed : true
		}]],
		fitColumns : true,
		loadFilter : function(data) {
			for (var i = 0; i < data.length; i++) {
				if (data[ i ].hasChildren > 0) {
					data[ i ].state = 'closed';
				} else {
					data[ i ].state = '';
				}
				data[i].id = data[i].nodeId;
				data[i].text = data[i].nodeName;
			}
			return data;
		},
		onBeforeExpand : function(row) {
			var url = _path + '/articleNodes/findArticleNodesList?parentId=' + row.nodeId;
			$obj.combotree("tree").tree("options").url = url;
			return true;
		},
		onLoadSuccess : function(node, data) {
			$obj.combotree('tree').tree("expandAll");
		}
	});
}

//删除
function deleteArticle(id){
	var ids =new Array();
	if(id){
		ids.push(id);
	}else{
		var selectRows = $("#table-article").datagrid('getChecked');
		if(selectRows.length==0){
			$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
			return false;
		}
		$.each(selectRows,function(i,v){
			ids.push(v.articleId);
		});
	}
//	alert("id....."+ids.join(","));
	if(ids.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	var params={};
	params.id=ids.join(",");
	$.messager.confirm('确认','您确认想要删除数据项吗？',function(r){    
		    if (r){  
		    	$.ajax({
		    	    url:_path + '/article/deleteArticle',
		    	    type:"post",
		    	    data:params,
		    	    dataType:"json",
		    	    success:function(_data){
//		    	    	alert(_data.head.retCode);
		    	    	if (_data.head.retCode == '000000') {
//			    			easyuiMsg('提示', "删除成功！");
			    			$('#table-article').datagrid('reload', {});
			    		} else if (_data.head.retCode == '000001'){
							easyuiMsg('错误','请选择需要操作的数据项！');
						} else {
			    			easyuiMsg('错误', "删除失败！");
			    		}
		    	    }
		    	});
		    }
		}
	);
}



/**
 * 打开高级查询
 */
function openSeachWindow() {
	var width = $(window).width(); // 屏幕的宽度
	$('#advance_search_article').dialog('open').window('resize',{ width : '380px' , height : '500px' , top : 0 , left : (width - 380) });
}
//清空高级选项条件
function clearCondition(){
	$("#advance_search_article").form('clear');
	$("#search-combox-article-nodes").val(-1).trigger("change");
	$('#table-article').datagrid('reload', {});
}
