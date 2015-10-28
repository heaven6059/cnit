var ie8SB = false;

$(function(){
	
	if(adJson.picSize){
		var arr = adJson.picSize.split(",");
		adJson.imgWidth = arr[0];
		adJson.imgHeight = arr[1];
	}
	
	//判断是否支持canvas
	var tempCanvas = document.createElement('canvas');
	if(!tempCanvas.getContext){
		ie8SB = true;
	}
	debugger
	adJson.tmpl = $(".contentBody").prop("outerHTML");
	if(adJson.adConfig && adJson.adConfig.intervalTime){
		$('#intervalTime').val(adJson.adConfig.intervalTime);
	}
	if(adJson.beginTime){
		$('#beginTime').datetimebox('setValue', new Date(adJson.beginTime.time).format('yyyy-MM-dd hh:mm:ss'));
	}
	if(adJson.endTime){
		$('#endTime').datetimebox('setValue', new Date(adJson.endTime.time).format('yyyy-MM-dd hh:mm:ss'));
	}
	
	
	//头部校验
	addValidForHead();
	debugger
	if(adJson.config){
		var configs = adJson.config.split(',');
		for ( var i = 0; i < configs.length; i++) {
			if(configs[0] == 'backgroundColor'){
				//画布
				$('.uploadImg').each(function(index,ele){
					createCanvas(this.id);
				});
			}
		}
	}
	debugger
	//初始化内容区
	initContent();
	//增加校验
	$('.contentBody').each(function(index,ele){
		addValidForContent($(this));
	});
});

/**
 * 创建画布
 */
function createCanvas(imgId){
	//生成画布
	img2base64(imgId);
	$('#'+imgId).wrap('<canvas class="canvas"  width="'+adJson.imgWidth+'" height="'+adJson.imgHeight+'">');
	var canvas = $('#'+imgId).parent().get(0);
	var context =  canvas.getContext('2d');
	context.drawImage(document.getElementById(imgId),0,0);
	var _colorInput = $('#'+imgId).closest('.contentBody').find('input.selectColor');
	_colorInput.css("background", _colorInput.val());
	_colorInput.blur(function(){
    	_colorInput.css("background", _colorInput.val());
    });
	initPickColor();
}

/**
 * 初始化选色器
 * @param event
 */
function initPickColor(){
	$('#edtiBody').on('click','.canvas',function(event){
		// 获得 canvas 位置
		var con = event.target.getContext('2d');
		var mouseX = parseInt(event.offsetX);
        var mouseY = parseInt(event.offsetY);
        
        var imagedata = con.getImageData(0, 0, adJson.imgWidth, adJson.imgHeight);
        //  get pixelArray from imagedata object
        var data = imagedata.data;  
        //  calculate offset into array for pixel at mouseX/mouseY
        var i = ((mouseY * adJson.imgWidth) + mouseX) * 4;
        //  get RGBA values
        var r = data[i];        
        var g = data[i+1];
        var b = data[i+2];
        var a = data[i+3];
        console.log('x:' + mouseX + ',y:' + mouseY);
        var rgba = 'rgba(' + r + ',' + g + ',' + b + ',' + a + ')';
        console.log(rgba);
        var _colorInput = $(event.target).closest('.contentBody').find('input.selectColor');
        _colorInput.css("background", rgba);
        _colorInput.val(rgba);
	});
}

function previewAndUpload(that,preImg,img,width,height,fileInput,imgPath){
	//由于上传文件组件的问题，必须在上传之前获取size
	previewImage(that,preImg,img,width,height);
	upLoadImg(fileInput,imgPath,img);
}

function upLoadImg(file, imgPath, preImg, cb) {
	var filev = document.getElementById(file);
	var patn = /\.jpg$|\.png$|\.jpeg$|\.gif$|\.JPG$|\.PNG$|\.JPEG$|\.GIF$/;
	if (filev.value != null && patn.test(filev.value)) {
		parent.$.messager.progress({
			title : '提示',
			text : '正在上传....'
		});
		debugger
		$.ajaxFileUpload({
			url : biz.rootPath() + '/image/uploadImgWithSize',
			fileElementId : file,
			dataType : 'json',
			data:{
					width:adJson.imgWidth,
					height:adJson.imgHeight
				},
			success : function(data, status) {
				debugger
				parent.$.messager.progress('close');
				if (data.retCode == "000000") {
					createCanvas(preImg);
					easyuiMsg('提示', '上传成功！');
					var img = document.getElementById(imgPath);
					var result = data.retMsg.split(";"); //包含id与路径
					img.value=result[1];
				} else if (data.retCode == "000003") {
					parent.$.messager.alert('提示', '上传失败！' + data.retMsg, 'error');
					var pre_img = document.getElementById(preImg);
					pre_img.src = biz.rootPath() + "/resources/images/pre_default.png";
				} else {
					parent.$.messager.alert('提示', '上传失败！'+(data.retMsg||''), 'error');
				}
				var callback = cb||{};
				typeof callback.success == "function"?callback.success(data,status):null;
			},
			error : function(data, status, e) {
				debugger
				parent.$.messager.progress('close');
				var callback = cb||{};
				typeof callback.error == "function"?callback.error(data,status,e):null;
			}
		});
	} else {
		parent.$.messager.alert('提示', '选择图片上传!', 'error');
	}
}

//提交表单验证 返回结果成功则返回data，失败则返回false
function submitCheck(){
	if($('input[name="endTime"]').val() < $('input[name="startTime"]').val()){
		parent.$.messager.alert("提示","结束时间不能早于开始时间","info");
		return false;
	}
	if(!$('form').form('validate')){
		return false;
	}
	var adConfig = {
		content:[]
	};
	if($('.contentBody').length!=adJson.adNum){
		parent.$.messager.alert("提示","必须要有"+adJson.adNum+"个广告","info");
		return false;
	}
	var nullImgList = [];
	var nullBgColorList = [];
	$('.contentBody').each(function(){
		var imgPath = $(this).find('input[name="imgPath"]').val();
		if(imgPath == ''){
			nullImgList.push($(this).find('span[title="cTitle"]').text());
		}
		if(adJson.adType == 0){
			var bgColor = $(this).find('input.selectColor').val();
			if(bgColor == ''){
				nullBgColorList.push($(this).find('span[title="cTitle"]').text());
			}
		}
		adConfig.content.push({
			bgColor:$(this).find('input.selectColor').val(),
			picUrl:$(this).find('input[name="imgPath"]').val(),
			destUrl:$(this).find('input[name="destUrl"]').val(),
			title:$(this).find('input[name="title"]').val(),
			desc:$(this).find('input[name="desc"]').val(),
			orderNo:$(this).find('input[name="orderNo"]').val()
		});
	});
	var errorMsg = '';
	if(nullImgList.length>0){
		errorMsg += nullImgList.join()+"还没有上传\n";
	}
	if(nullBgColorList.length>0){
		errorMsg += nullBgColorList.join()+"背景颜色还没选取\n";
	}
	if(errorMsg != ''){
		parent.$.messager.alert("提示",errorMsg,"info");
		return false;
	}
	adConfig.content.sort(function(a,b){
		return (a.orderNo||0) - (b.orderNo||0);
	});
	var data = {
			adId:$('#adId').val(),
			name:$('#name').val(),
			adType:$('#adType').combobox('getValue'),
			beginTime:$('#beginTime').datetimebox('getValue'),
			endTime:$('#endTime').datetimebox('getValue'),
			enabled:$('#enabled').combobox('getValue'),
			description:$('#description').val(),
			adConfig:JSON.stringify(adConfig)
	};
	if(data.beginTime>=data.endTime){
		parent.$.messager.alert('提示','结束日期必须大于开始日期','info');
		return false;
	}
	return data;
}

//保存更改
function saveEdit(){
	debugger
	var url = _path+"/siteAd/save";
	var data = submitCheck();
	if(!data){
		return;
	}
	$.messager.progress({msg:'正在保存...'});
	$.ajax({
		url:url,
		type:'POST',
		data:data,
		dataType:'json',
		success:function(data, textStatus, jqXHR){
			$.messager.progress('close');
			if(data.head.retCode == yoyo.successCode){
				parent.$.messager.alert('提示','保存成功','info');
			}else{
				parent.$.messager.alert('提示','保存失败','info');
			}
		},
		error:function (XMLHttpRequest, textStatus, errorThrown){
			$.messager.progress('close');
			parent.$.messager.alert('提示','保存失败','error');
		}
	});
}

//添加图片
var contentNo = 0;
function addContent(){
	var item = addBlankContent();
	//手动绑定
	new jscolor.color(item.find('.selectColor').get(0), {});
	addValidForContent(item);
}


/**
 * 添加广告内容区
 * @returns
 */
function addBlankContent(){
	debugger
	if($('.contentBody').length>= adJson.adNum){
		parent.$.messager.alert("提示",adJson.name+"广告不能超过"+adJson.adNum+"个","info");
		return;
	}
	var local_tmpl = new String(adJson.tmpl).format({
		contentNo:contentNo
	});
	var item = $('#addContent').before(local_tmpl).prev();
	contentNo++;
	$('span[title="cTitle"]').each(function(index,domEle){
		$(this).text("图片"+(index+1));
	});
	return item;
}

/**
 * 给通用头部加校验
 */
function addValidForHead(){
	$('#description').validatebox({
		validType:'maxLength[100]'
	});
	$('#intervalTime').validatebox({
		required: true
	});
}

/**
 * 添加校验
 * @param $content
 */
function addValidForContent($content){
	$content.find('input[name="destUrl"]').validatebox({
		required:true,
		validType:['isUrl','maxLength[300]']
	});
	$content.find('input[name="title"]').validatebox({
		validType:['maxLength[50]']
	});
	$content.find('input[name="desc"]').validatebox({
		validType:['maxLength[100]']
	});
	//排序
	$content.find('input[name="orderNo"]').validatebox({    
		//required: true,    
		validType: ['integ','range[1,'+adJson.adNum+']']  
	});
}

//删除内容
function removeContent(that){
	var tr = $(that).parents('.contentBody');
	tr.remove();
	$('span[title="cTitle"]').each(function(index,domEle){
		$(this).text("图片"+(index+1)+"：");
	});
}

function img2base64(id){
	$.ajax({
		url: _path+"/siteAd/img2base64",
		data:{
			url:$('#'+id).attr('src')
		},
		type:'post',
		dataType :'text',
		async:false,
		success:function(data){
			$('#'+id).attr('src', data); 
		}
	});
}

//初始化contentBody部位
function initContent(){
	$('.contentBody').remove();
	debugger
	var content = adJson.adConfig.content;
	for(var i=0;i<content.length;i++){
		addBlankContent();
	}
	var items = $('.contentBody');
	for(var i=0;i<content.length;i++){
		debugger
		//items.eq(i).find('input[name="picUrl"]').val(content[i].picUrl);
		var item = items.eq(i);
		item.find('input[name="imgPath"]').val(content[i].picUrl);
		item.find('input[name="destUrl"]').val(content[i].destUrl);
		item.find('input[name="title"]').val(content[i].title);
		item.find('input[name="desc"]').val(content[i].desc);
		item.find('input.selectColor').val(content[i].bgColor);
		item.find('input[name="orderNo"]').val(content[i].orderNo);
		item.find('img[id^="img"]').attr("src",yoyo.imagesUrl+content[i].picUrl);
		var imgId = item.find('img[id^="img"]').attr('id');
		if(!ie8SB){
			//生成画布
			createCanvas(imgId);
		}else{
			//生成jscolor选色器
			new jscolor.color(item.find('input.selectColor').get(0));
		}
	}
}
