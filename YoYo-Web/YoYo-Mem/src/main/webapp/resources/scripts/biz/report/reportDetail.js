var pics = ''; 	// 存放图片地址字符串
$(document).ready(function(){
	// 初始化上传图片插件
	$("#goods_picture").zyUpload({ width : "630px" , // 宽度
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
		if(($(".msg-textarea").val().length<10)||($(".msg-textarea").val().length>300)){
			$(".text-count").removeClass("hide");
			return;
		}
		submitForm(params);
	});
	
	$(".J_Revoke").click(function (){
		var url=yoyo.memUrl+"/report/updateReportStatus";
		 yoyo.ajaxRequest(url,{reportId:$("#reportId").val(),status:'cancel'},true,"json",function(data){
			   	var resultCode = data.retCode;
			   	if (resultCode == yoyo.successCode) {
			   		layer.msg('撤销成功!', {icon: 1,shade: [0.8, '#393D49'],end:function (){
			   			window.location.href = yoyo.memUrl+"/report/getReportDetailById/?reportId="+$("#reportId").val(); 
			   		}});
			   	} else {
			   		layer.msg('撤销失败!', {icon: 0,shade: [0.8, '#393D49'],end:function (){
			   			window.location.href = yoyo.memUrl+"/report/getReportDetailById/?reportId="+$("#reportId").val(); 
			   		}});
			   	}
		});
	});
	
});


function cancelReport(reportId){
	layer_utils.confirm("是否确定撤销?", function (){
	    var url=yoyo.memUrl+"/report/updateReportStatus";
		yoyo.ajaxRequest(url,{reportId:reportId,status:"cancel"},true,"json",function(data){		   	
		   	var resultCode = data.retCode;
		   	if (resultCode == yoyo.successCode) {
		   		window.location.href = yoyo.memUrl+"/report/reportList"; 
		   	} else {
		   		layer.alert("撤销投诉失败！",{title:false,closeBtn:false,icon:0});
		   	}
		});
	});
}

function submitForm(params){
	var url=yoyo.memUrl+"/report/saveReportComment";
	data = params;
	reportId = params.reportId;
	 yoyo.ajaxRequest(url,data,true,"json",function(data){
		   	var resultCode = data.retCode;
		   	if (resultCode == yoyo.successCode) {
		   			window.location.href = yoyo.memUrl+"/report/getReportDetailById/?reportId="+reportId; 
		   	} else {
		   		alert("举报失败 ！");
		   	}
	});
}



