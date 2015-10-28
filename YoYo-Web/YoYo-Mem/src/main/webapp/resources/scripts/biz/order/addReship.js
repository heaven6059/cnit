var pics = ''; 	// 存放图片地址字符串

$(document).ready(function(){
	// 初始化上传图片插件
	$("#goods_picture").zyUpload({ 
		width : "500px" , // 宽度
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
			$('#memberImagePath').attr('value',pics);
			if (json.retCode == "000000") {
			} else if (json.retCode == "000003") {
				parent.$.messager.alert('提示', '上传失败！' + json.retMsg, 'error');
			}
		} 
	});
	
	
	//保存
	$("#save_button").click(function() {
		submitForm();
	});
	
	$("#appeal_save_button").click(function (){
		submitAppealForm();
	});
	
	$(".list-type-new li").click(function (){
		$(this).addClass("selected").siblings().removeClass("selected");
		$(this).parent().find("input").val($(this).attr("id"));
		if($(this).attr("id")==5){
			var p=parseFloat($("#number").val()*(parseFloat($("#number").attr("data-price")))).toFixed(2);
			$("#amount_span").html(p);
			$("#amount_tr").removeClass("hide");
		}else{
			if($("#safeguardRequire").val()!=5){
				$("#amount_tr").addClass("hide");
				$("#amount").val("");
			}
		}
	});
	
	$(".decrement").click(function (){
		if(parseInt($("#number").val())-1<1){
			return;
		}
		$("#number").val(parseInt($("#number").val())-1);
		var p=parseFloat($("#number").val()*(parseFloat($("#number").attr("data-price")))).toFixed(2);
		$("#amount_span").html(p);
	});
	
	$(".increment").click(function (){
		if(parseInt($("#number").val())+1>$("#number").attr("data-nums")){
			layer.alert("可提交数量不能超过："+$("#number").attr("data-nums"),{title:false,closeBtn:false,icon:0});
			return;
		}
		$("#number").val(parseInt($("#number").val())+1);		
		var p=parseFloat($("#number").val()*(parseFloat($("#number").attr("data-price")))).toFixed(2);
		$("#amount_span").html(p);
	});
});

function validationForm(){
	if($("#safeguardType").val().length==0){
		alert("请选择售后要求");
		return false;
	}
	if($("#safeguardRequire").val().length==0){
		alert("请选择服务类型");
		return false;
	}
	if($("#number").val().length<=0){
		alert("请输入提交数量");
		return false;
	}
	if($("#number").val()>$("#number").attr("data-opts")){
		alert("可提交数量不能超过"+$("#number").attr("data-opts"));
		return false;
	}
	if($("#memberName").val().length<=0){
		alert("请输入联系人姓名");
		return false;
	}
	var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	if(!reg.test($("#memberPhone").val())) {
		alert("请输入正确的手机号");
      	return false;
	};
	if($("#content_desc").val().length<=0){
		alert("请输入问题描述");
		return false;
	}
	return true;
}

function submitForm(){
	if(!validationForm())return;
	if($("#safeguardRequire").val()==5){
		var p=parseFloat($("#number").val()*(parseFloat($("#number").attr("data-price")))).toFixed(2);
		$("#amount").val(p);
	}
	 $.ajax({
			url:yoyo.memUrl+"/reship/saveReship",
			data:$("#reshipForm").serialize(),
			type:"post",
			dataType:"json",
			async: false,
			success:function(returnData){
				var resultCode = returnData.retCode;
		   		if (resultCode == yoyo.successCode) {
		   			window.location.href = yoyo.memUrl+"/reship/getReshipListPage"; 
		   		} else {
		   			alert("退货申请失败 ！");
		   		}
			},	
		   	error:function(){
		   		alert("退货申请失败 ！");
		   	}
	 });
}



function submitAppealForm(){
	if(!validationForm())return;
	if($("#safeguardRequire").val()==5){
		var p=parseFloat($("#number").val()*(parseFloat($("#number").attr("data-price")))).toFixed(2);
		$("#amount").val(p);
	}	
	 $.ajax({
			url:yoyo.memUrl+"/reship/saveAppleaReship",
			data:$("#reshipForm").serialize(),
			type:"post",
			dataType:"json",
			async: false,
			success:function(returnData){
				var resultCode = returnData.retCode;
		   		if (resultCode == yoyo.successCode) {
		   			window.location.href = yoyo.memUrl+"/reship/getReshipListPage"; 
		   		} else {
		   			alert("售后申诉失败 ！");
		   		}
			},	
		   	error:function(){
		   		alert("售后申诉失败 ！");
		   	}
	 });
}
