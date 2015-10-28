/**
 * 页面中需要动态添加dom元素，所以使用字符串模板。
 * 通过class属性定位标签，为了与样式进行分离，采用单独的class属性。
 * 用于js定位的class属性，会带有js_前缀。
 * */
$(function(){
	$.ajax({
		dataType:'json',
		data:{parentId:'0'},
		type:'GET',
		url:_path+'/articleMem/findNodesByParentId',
		success:function(data){
			if(data.head.retCode == '000000'){
				initArticle(data);
			}
		}
	});
	var jsNode = $('div.js_node:first');
	var dtTmpl = jsNode.find('dt:first').prop('outerHTML');
	var ddTmpl = jsNode.find('dd:first').prop('outerHTML');
	function initArticle(data){
		jsNode.find('dt').remove();
		jsNode.find('dd').remove();
		var nodeTmpl = jsNode.prop('outerHTML');
		$('div.js_node').remove();
		var content = data.content;
		var i = 0;
		var no = 1;//1：第一个dt，2：第二个dt
		var tmpl = null;
		var div = null;
		while(true){
			if(i>=content.length){
				break;
			}
			if(no == 1){
				$('.js_end_node').before(nodeTmpl);
				tmpl = new String(dtTmpl).format({nodeName:content[i].nodeName,nodeId:content[i].nodeId});
				div = $('div.js_node:last');
				div.find('dl:first').append(tmpl).find('dt').data('nodeId',content[i].nodeId);
				initChildrenNodes(div.find('dl:first'),content[i]);
				no = 2;
			}else{
				tmpl = new String(dtTmpl).format({nodeName:content[i].nodeName,nodeId:content[i].nodeId});
				div = $('div.js_node:last');
				div.find('dl:last').append(tmpl).find('dt').data('nodeId',content[i].nodeId);;
				initChildrenNodes(div.find('dl:last'),content[i]);
				no = 1;
			}
			i++;
		}
		$('.js_node dt').show();
	}
	function initChildrenNodes($dl,node){
		$.ajax({
			dataType:'json',
			data:{parentId:node.nodeId},
			type:'GET',
			url:_path+'/articleMem/findNodesByParentId',
			success:function(data){
				var tmpl = null;
				if(data.head.retCode == '000000'){
					var content = data.content;
					for(var i=0;i<content.length;i++){
						tmpl = new String(ddTmpl).format({nodeName:content[i].nodeName,nodeId:content[i].nodeId});
						$dl.append(tmpl);
					}
				}
				$dl.find('dd').show();
			}
		});
	}
});

function toArticlePage(domEle,nodeId){
	window.location.href=yoyo.portalUrl+"/article/index?nodeId="+nodeId;
}