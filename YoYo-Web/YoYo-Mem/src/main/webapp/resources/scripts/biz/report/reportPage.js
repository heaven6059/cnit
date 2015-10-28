var pics = ''; 	// 存放图片地址字符串

$(document).ready(function(){
	
	// 初始化上传图片插件
	$("#goods_picture").zyUpload({ width : "600px" , // 宽度
	height : "200px" , // 高度
	itemWidth : "100px" , // 文件项的宽度
	itemHeight : "100px" , // 文件项的高度
	url : _path + "/image/uploadImg" , // 上传文件的路径
	path:_path,
	multiple : false , // 是否可以多个文件上传
	dragDrop : false , // 是否可以拖动上传文件
	del : true , // 是否可以删除文件
	finishDel : false , // 是否在上传文件完成后删除预览
	/* 外部获得的回调接口 */
	onSuccess : function(file, data) { // 文件上传成功的回调方法
		var json = $.parseJSON(data);
		var pic = json.retMsg.split(";");
		pics =pics + pic[1] + ',' ;
		$('#imagePath').attr('value',pics);
		if (json.retCode == "000000") {
		} else if (json.retCode == "000003") {
			parent.$.messager.alert('提示', '上传失败！' + json.retMsg, 'error');
		}
	} 
	});
	
	
	//保存
	$("#save_button").click(function() {
		var params = biz.serializeObject($("#formId"));
	
		submitForm(params);
	});
	
});


function submitForm(params){
	var url=yoyo.memUrl+"/complain/saveComplain";
	var data=params;//$('#formId').serializeArray();
	 yoyo.ajaxRequest(url,data,true,"json",function(data){
		   		yoyo.loading("","");
		   		var resultCode = data.retCode;
		   		if (resultCode == yoyo.successCode) {
		   			window.location.href = yoyo.memUrl+"/skip/complain"; 
		   		} else {
		   			alert("投诉失败 ！");
		   		}
		   	});
}


