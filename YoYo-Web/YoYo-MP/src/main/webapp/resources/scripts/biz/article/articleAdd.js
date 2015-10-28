/**
 * 功能描述： 汽车车型的js
 * 
 */
$(function() {
	// 在线html编辑器
	$('#cle-article-content').cleditor();
	
	//查询文章栏目
	initArticleNodesTree('combox-article-node', false);
	
	//判断当前操作是新增还是编辑
	addOrEdit();
});

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

//判断当前操作是新增还是编辑
function addOrEdit(){
	var id = $('#id').val();
	var nodeId = $('#hideNodeId').val();
	if(id!=null&&id!=''&&parseInt(id)>=1){
		//当前操作为编辑
		if(nodeId!=null&&nodeId!=''&&parseInt(nodeId)>=1){
			$('#combox-article-node').combotree('setValue',nodeId);
		}
	}
}

//保存
function saveOrUpdateArticle(){
	var id = $('#id').val();
	var title = $('#article_title').val();
	var nodeId = $('#combox-article-node').combotree('getValue');
	if(nodeId==null||nodeId==''||parseInt(nodeId)<1){
		easyuiMsg('错误', "请选择所属栏目");
		return;
	}
	var author = $('#article_author').val();
	var ifpub = $('input[name="ifpub"]:checked').val();
	var content = $('#cle-article-content').val();
	var param_data = biz.serializeObject($("#form-article-add"));
	$.post(_path + '/article/saveOrUpdateArticle', param_data, function(_data) {
		if (_data.head.retCode == '000000') {
			alert("保存成功");
			window.location.href = _path+"/article/index";
		}else {
			if(_data.head.retCode == '000001'){
				easyuiMsg('错误', "所属栏目不存在");
			}else if(_data.head.retCode == '000004'){
				easyuiMsg('错误', "该文章标题已存在");
			}else if(_data.head.retCode == 'SPE002'){
				easyuiMsg('错误', "该文章不存在");
				window.location.href = _path+"/article/index";
			}
		}
	}, 'json');
}
