var pics = ''; 	// 存放图片地址字符串
var pic_size=[];
$(document).ready(function(){
	$(".member-main").find("img").each(function (x,item){
		$(item).attr("src",yoyo.imagesUrl+$(item).attr("src"));
	});
	$("form").find("input[type!='radio'][type!='checkbox'][type!='button'][type!='submit'][type!='reset']").each(function(){
		$(this).addClass("form_item_input");
	});
	// 初始化上传图片插件
	$("#goods_picture").zyUpload({ 
		width : "600px" , // 宽度
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
				pic_size.push(file.index);
				console.log(pic_size);
			} else if (json.retCode == "000003") {
				parent.$.messager.alert('提示', '上传失败！' + json.retMsg, 'error');
			}
		}, onDelete : function(file, files) { // 删除一个文件的回调方法
			$.each(pic_size,function (x,index){
				if(index==file.index){
					pic_size.splice(x, 1);		
				}
			});
		}
	});
	
	
	//保存
	$("#save_button").click(function() {
		debugger
		var params = biz.serializeObject($("#formId"));
		submitForm(params);
	});
	
	var limitNum = 300;
	var pattern = '还可以输入' + limitNum + '字符';
	$('.J_TextCount').html(pattern);
	$('#comment').keyup(function() {
		var remain = $(this).val().length;
		if (remain > 300) {
			pattern = "字数超过限制！";
		} else {
			var result = limitNum - remain;
			pattern = '还可以输入' + result + '字符';
		}
		$('.J_TextCount').html(pattern);
	});
});


function submitForm(params){
	var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	if(!reg.test($("#mobile").val())) {
      alert("请输入正确的手机号");
      return;
	};
	if($("#comment").val().length>300){
		alert("最多可输入300字符,请修改后再提交");
		return;
	}
	if($("#comment").val().length<=0){
		alert("请输入留言内容");
		return;
	}
	if(pic_size.length==0){
		//alert("请上传举报凭证");
		//return;
	}
	var url=yoyo.memUrl+"/complain/saveComplain";
	 yoyo.ajaxRequest(url,params,true,"json",function(data){
		 yoyo.loading("","");
		 var resultCode = data.retCode;
		 if (resultCode == yoyo.successCode) {
		   	window.location.href = yoyo.memUrl+"/skip/complain"; 
		 } else {
		   	alert("投诉失败 ！");
		 }
	});
}


