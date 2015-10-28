$(function(){
	// 在线html编辑器
	$('#cle-news-content').cleditor();
	debugger
	$('#type').combobox({
	    valueField:'id',
	    textField:'text',
	    data:[{
	        "id":'true',
	        "text":"公告"
	    },{
	        "id":'false',
	        "text":"特惠"
	    }]
	});
	
	$('#title').validatebox({
		required: true
	});
	if($('input[name="hasContent"]').val() == 1){
		$("#showUrl").show();
		addUrlValidate();
	}
	$('input[name="hasContent"]').change(function(){
		$('#richText').toggle();
		$('#showUrl').toggle();
		addUrlValidate();
	});
});

function addUrlValidate(){
	if($("#showUrl").is(":visible")){
		$('#url').validatebox({
		    required: true,
		    validType: 'isUrl'
		});
	}else{
		$('#url').validatebox({
			required:false,
		    validType: 'isUrl'
		});
	}
}
window.onload = function(){
	if(!hasContent){
		$('#richText').hide();
	}
};

function getValid(){
	debugger
	if(!$("#title").validatebox('isValid')){
		$.messager.alert('提示','标题不能为空','info');
		return false;
	}
	if($("#showUrl").is(":visible")){
		if(!$("#url").validatebox('isValid')){
			$.messager.alert('提示','链接不能为空','info');
			return false;
		}
	}
	return true;
}

/**
 * 保存
 */
function saveOrUpdate(){
	debugger
	var _url = ""
	if(getValid()){
		if($('#id').val()){
			_url =  _path + '/news/editNews';
		}else{
			_url =  _path + '/news/addNews';
		}
		$.ajax({
			url : _url,
			type : "post",
			data: $('#newsForm').serializeArray()
		}).done(function(data){
			if(data.retCode == yoyo.successCode){
				$.messager.alert('提示','保存成功','info');
			}else{
				$.messager.alert('提示','保存失败','info');
			}
		});
	}
}