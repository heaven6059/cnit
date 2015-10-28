$(function() {
	initArticleNodesGrid();
	
	$('.panel-tool-close').on('click',function(){
		$('#MODALPANEL').hide();
	});
});
function initArticleNodesGrid() {
	$('#table-article-nodes').treegrid(
			{
				url : _path + '/articleNodes/findArticleNodesList' ,
				toolbar : '#toolbar-article-nodes' ,
				idField : 'nodeId' ,
				treeField : 'nodeName' ,
				queryParams : { 'parentId' : 0 } ,
				columns : [ [
						{ field : 'nodeId' , title : '栏目id' ,align:'center' , hidden : true} ,
						{ field : 'parentId' , title : '父节点id' , align:'center',hidden : true } ,
						{ field : 'nodeName' , title : '栏目名称' , width : 400 ,halign:'center', fixed : true } ,
						{ field : 'ordernum' , width : 100 , title : '排序' ,align:'center', editor : 'numberbox' } ,
						{ field : 'editor' , title : '操作' , halign:'center', width : 350 , formatter : function(value, row) {
								var str = '';
								if(row.nodeDepth <3 ){
									str+='<a href="javascript:addNodes(' + row.nodeId + ')" style="margin-right:15px;" >添加文章子类目</a>';
								}
								var grandfatherId = $('tr[node-id='+ row.parentId +']').find('td[field="parentId"] div').text();
								if(!grandfatherId){
									grandfatherId = null;
								}
								str+='<a href="javascript:editNodes(' + row.nodeId + ','+ grandfatherId +')" style="margin-right:15px;" >编辑</a>';
								if(row.deletable == 1){
									str+='<a href="javascript:deleteNode(' + row.nodeId + ')" style="margin-right:15px;" >删除</a>';
								}
								return str;
							} 
						} 
				] ] , 
				loadFilter : function(data) {
					for (var i = 0; i < data.length; i++) {
						if (data[ i ].hasChildren > 0) {
							data[ i ].state = 'closed';
						} else {
							data[ i ].state = '';
						}
					}
					return data;
				} , 
				onBeforeExpand : function(row) {
					var url = _path + '/articleNodes/findArticleNodesList?parentId=' + row.nodeId;
					$("#table-article-nodes").treegrid("options").url = url;
					return true;
				} , 
				onLoadSuccess : function(row, data) {
					$('#table-article-nodes').treegrid('expand',1);
				}
			}
		);
	$('#table-violation-cate').datagrid({
		width : ($(window).width() * 0.98),
		height : ($(window).height() * 0.99)
	});
}

//初始化上级栏目下拉框
function initArticleNodes(grandfatherId) {
	var url = _path + '/articleNodes/findArticleNodesList?parentId=' + grandfatherId;
	$.ajax({
		  dataType: "json",
		  url: url,
		  async:false,
		  success: function(json) {
				var data="[";
				if(true){
					data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
				}
				$.each(json, function(i, n) {
					data+='{ text: "'+n.nodeName+'", id:'+n.nodeId+'},';
				});
				data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
				data+="]";
				$('#combox-parent-article-nodes').empty();
				$('#combox-parent-article-nodes').select2({data:eval(data)});
			}
	});
}


//编辑
function editNodes(nodeId, grandfatherId) {
	if(grandfatherId != null){
		initArticleNodes(grandfatherId);
		$('#tr_parent_article_nodes_id').show();
	}else{
		$('#tr_parent_article_nodes_id').hide();
	}
	_virtCatId = nodeId;
	$('#window-article-nodes-add').window('setTitle', '编辑分类');
	$('#MODALPANEL').show();
	$('#window-article-nodes-add').window('open');
	$('#combox-parent-article-nodes').removeAttr('disabled');
}

//获取指定的对象
function getArticleNodesDetail() {
	if (_virtCatId) {
		$.post(_path + '/articleNodes/getArticleNodesDetail', { 'nodeId' : _virtCatId }, function(data) {
			if (data.head.retCode == '000000') {
				var _data = data.content;
				if(_data == null){
					easyuiMsg('错误', "该栏目不存在");
					$('#MODALPANEL').hide();
					$('#window-article-nodes-add').window('close');
					initArticleNodesGrid();
					return;
				}
				_data.nodeNames=_data.nodeName;
				$('#form-article-nodes-add').form('load', _data);
				if(_data.parentId=='0'){
					$('#combox-parent-article-nodes').val(-1).trigger("change");
				}else{
					$('#combox-parent-article-nodes').val(_data.parentId).trigger("change");
				}
			} else {
				easyuiMsg('错误', data.head.retMsg);
			}
		});
	}
}

//新增类型
function addNodes(nodeId) {
	$('#form-article-nodes-add').form('clear');
	if(nodeId=='0'){
		$('#combox-parent-article-nodes').val(-1).trigger("change");
	}else{
		var nodeName = $('tr[node-id='+ nodeId +']').find('span.tree-title').text();
		
		var data="[";
		data+='{ text: "'+nodeName+'", id:'+nodeId+',selected:true},';
		data+="]";
		$('#combox-parent-article-nodes').empty();
		$('#combox-parent-article-nodes').select2({data:eval(data)});
		
		$('#combox-parent-article-nodes').val(nodeId).trigger("change");
		$('#combox-parent-article-nodes').attr('disabled','true');
	}
	$('#window-article-nodes-add').window('setTitle', '添加');
	$('#MODALPANEL').show();
	$('#window-article-nodes-add').window('open');
}

var _virtCatId = null;
function clearArticleNodesDetail() {
	_virtCatId = null;
	$('#form-article-nodes-add').form('clear');
}

function saveOrUpdate() {
	var articleNodes = {};
	articleNodes.nodeId = $('input[name="nodeId"]').val();
	articleNodes.nodeName = $('input[name="nodeNames"]').val();
	if(!articleNodes.nodeName){
		easyuiMsg('错误', "请填写栏目名称");
		return;
	}
	articleNodes.parentId = $('#combox-parent-article-nodes').val();
	if(articleNodes.parentId && articleNodes.nodeId == articleNodes.parentId ){
		easyuiMsg('错误', "请重新选择上级分类");
		return;
	}
	articleNodes.ifpub = 1;
	articleNodes.ordernum = $('input[name="ordernum"]').val();
	if(!articleNodes.ordernum){
		articleNodes.ordernum = 0;
	}
	commonAjax(_path + '/articleNodes/saveOrUpdate', { 'articleNodes' : JSON.stringify(articleNodes) }, function(data) {
		$('#MODALPANEL').hide();
		$('#window-article-nodes-add').window('close');
		initArticleNodesGrid();
	}, function(data) {
		 if(data.head.retCode == '000004'){
			easyuiMsg('错误',"保存失败，栏目名称已经存在！");
			return;
		 }else if(data.head.retCode == '000007'){
			easyuiMsg('错误',"保存失败，该上级栏目不合法！");
			return;
		 }else if(data.head.retCode == '000001'){
			easyuiMsg('错误',"保存失败，该文章栏目不存在！");
			initArticleNodesGrid();
			return;
		 }else if(data.head.retCode == '000003'){
			easyuiMsg('错误',"保存失败，该上级栏目不合法！");
			return;
		 }else if(data.head.retCode == '000005'){
			easyuiMsg('错误',"最多创建4个主栏目");
			$('#window-article-nodes-add').window('close');
			$('#MODALPANEL').show();
			return;
		 }
		easyuiMsg('错误', data.head.retMsg);
	});
}

//删除
function deleteNode(nodeId) {
	if (nodeId) {
		$.messager.confirm('', '删除操作将同时删除该栏目下的所有文章，确定删除？', function(r) {
			if (r) {
				$.post(_path + '/articleNodes/deleteCate', { 'nodeId' : nodeId }, function(data) {
					if (data.head.retCode == '000000') {
						initArticleNodesGrid();
					}else if(data.head.retCode == '000007'){
						easyuiMsg('错误', "该栏目存在下级，无法删除");
					}else if(data.head.retCode == 'UNKOWN'){
						easyuiMsg('错误', "该栏目无法删除！");
					}else{
						easyuiMsg('错误', data.head.retMsg);
					}
				}, 'json');
			}
		})
	}
}

//修改发布状态
function ifPub(nodeId,ifPub) {
	if (catId) {
		$.post(_path + '/articleNodes/ifPub', { 'nodeId' : nodeId ,'ifPub' : (1-ifPub)}, function(data) {
			if (data.head.retCode == '000000') {
				initViolationCateGrid();
			}else{
				easyuiMsg('错误', data.head.retMsg);
			}
		}, 'json');
	}
}
