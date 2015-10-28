var memberId,accountId,accountType;
var loginUrl = yoyo.portalUrl+'/register/login';
var indexUrl = yoyo.portalUrl+'/index';
window.onload = function(){
	if(!/10\d{1}/.test(accountType)){
		window.location.href = loginUrl;
	}
	initPicture('cell1');
	//限制100个字符
	$('#chooseForm').on('keydown','.question',function(e){
        var curLength=$(this).val().length;   
        if(curLength>=100){
        	if(e.which !=8){
        		return false;
        	}
        }  
	});

	//可选部位超链接点击事件
	$('.add').click(function(event){
		debugger
		var target = event.currentTarget;
		var partId = target.nextSibling.getAttribute('partId');
		var text = target.innerHTML;
		var cell1 = document.getElementById('cell1');
		var nums = getElesByClass(cell1, 'num');
		var form = document.getElementById('chooseForm');
		if(new RegExp('(\\s|^)' + '添加').test(text)){
			target.innerText  = '- 取消';
			//修改父元素DIV的样式
			removeClass(target.parentNode,'car_part');
			addClass(target.parentNode,'car_part2');
			//对添加至右侧表格
			if(nums[0].getAttribute('isNull') == 'true'){
				getElesByClass(cell1, 'title')[0].innerText = target.previousSibling.innerText;
				$(cell1).find('._partId').attr('_partId',partId);
				nums[0].setAttribute('isNull', 'false');
				var leaveMessage = getElesByClass(cell1, 'hide')[0];
				removeClass(leaveMessage,'hide');
				removeClass(document.getElementById('submit'), 'hide');
			}else{
				//如果已添加了部位，则新增一栏
				var num = parseInt(getElesByClass(document.getElementById('chooseForm'),'num').length);
				var newCell =document.createElement('div');
				addClass(newCell, 'cell');
				addClass(newCell, 'clearfix');
				newCell.id = "cell" + (++num);
				newCell.innerHTML = innerHtml;
				getElesByClass(newCell, 'num')[0].innerText = num;
				getElesByClass(newCell, 'title')[0].innerText = target.previousSibling.innerText;
				getElesByClass(newCell, '_partId')[0].setAttribute('_partID', partId);
			    $(newCell).find('div[node-type="data_picker"]').attr('id','filePicker_'+newCell.id);
				$(newCell).find('div[node-type="data_list"]').attr('id','fileList_'+newCell.id);
				form.insertBefore(newCell,document.getElementById('submit'));
				$(newCell).find('.num').eq(0).attr('isNUll','false');
				initPicture(newCell.id);
			}
		}else if(new RegExp('(\\s|^)' + '取消').test(text)){
			debugger
			target.innerText  = '+ 添加';
			removeClass(target.parentNode,'car_part2');
			addClass(target.parentNode,'car_part');

			var title = target.previousSibling.innerText;
			var nodes = removeBlankNodes(form.childNodes);
			var hasMove = false;
			if(nodes.length > 0){
				for(var i=0;i<nodes.length-1;i++){
					var cell = nodes[i];
					if(hasMove){
						//重新计算节点序号
						var numNode = getElesByClass(cell, 'num')[0];
						var oldNum = numNode.innerText;
						numNode.innerText = --oldNum;
						cell.id = "cell" + (oldNum);
						$(newCell).find('div[node-type="data_picker"]').attr('id','filePicker_'+cell.id);
						$(newCell).find('div[node-type="data_list"]').attr('id','fileList_'+cell.id);
						$(newCell).find('div[node-type="data_list"]').attr('id','fileList_'+cell.id);
					}
					if(title == getElesByClass(cell, 'title')[0].innerText){
						//当仅剩一个时，重置为初始状态
						if(nodes.length == 2){
							cell.innerHTML = innerHtml;
							$(cell).find('.num').eq(0).attr('isNUll','true');
							$(cell).find('.innerBox').eq(1).addClass('hide');
							$('#submit').addClass('hide');
							initPicture(cell.id);
						}else{
							form.removeChild(cell);
							//重新计算节点序号
							hasMove = true;
						}
					}
				}
			}
		}
	});
	
	//用户上传受损单
	$('#submit').click(function(){
		alert("未上传图片。");
	});
}

//初始化上传框实例
var Config = {
		// 自动上传。
		auto: true,
		// swf文件路径
		swf: _path + '/resources/styles/webUploader/Uploader.swf',
		// 文件接收服务端。
		server: _path + "/painting/uploadImg", // 上传文件的路径,
		//限制上传个数
		fileNumLimit: 3,
		// 只允许选择文件，可选。
		accept: {
		    title: 'Images',
		    extensions: 'gif,jpg,jpeg,bmp,png',
		    mimeTypes: 'image/*'
		}
}

function initPicture(cellId) {
	//优化retina, 在retina下这个值是2
	var ratio = window.devicePixelRatio || 1;
	// 缩略图大小
	var thumbnailWidth = 110 * ratio;
	var thumbnailHeight = 110 * ratio;
	
	var pickId = '#filePicker_'+cellId;
	Config.pick = pickId;
	var uploader = 'uploader'+ cellId;
	uploader = WebUploader.create(Config);
	uploader.on('fileQueued', function( file ) {
        var $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<img>' +
                    '<div class="info">' + file.name + '</div>' +
                '</div>'
                ),
                
        $btns = $('<div class="file-panel" style="height: 0px;">'+
					 		'<span class="cancel">删除</span>'+
					    '</div>').appendTo( $li ),
					    
         $img = $li.find('img');
        // $list为容器jQuery实例
		if($('#fileList_'+cellId).find("div[class='file-item thumbnail']").length>2){
			$('#fileList_'+cellId).find('div[title=uploaderDiv]').hide();
		}
        $li.insertBefore($('#fileList_'+cellId).find('div[title=uploaderDiv]').eq(0));

        $li.on( 'mouseenter', function() {
            $btns.stop().animate({height: 30});
        });

        $li.on( 'mouseleave', function() {
            $btns.stop().animate({height: 0});
        });
        
        $li.on('click', '.cancel', function() {
       	 	 var item = this.parentNode.parentNode;
        	 var lenth = $(item).siblings().length;
        	 var $uploaderDiv = $(item).parent().find('div[title=uploaderDiv]');
    	     uploader.removeFile( item.id, true );
    	     $(item).remove();
    	     if(lenth > 2){
    	    	 $uploaderDiv.show();
    	     }
        })
        
        // 创建缩略图
        // 如果为非图片文件，可以不用调用此方法。
        // thumbnailWidth x thumbnailHeight 为 100 x 100
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }
            $img.attr( 'src', src );
        }, thumbnailWidth, thumbnailHeight );
        
    });
	
	uploader.on( 'beforeFileQueued', function( ) {
		$('#submit').off('click');
		$('#submit').click(function(){
			alert("图片正在上传中！");
		});
	});
	uploader.on( 'uploadSuccess', function( file,response ) {
		var fileId = file.id;
		var $file = $('#'+fileId);
		//exchange(fileId);
		if(response.head.retCode == "000000"){
			//返回的图片地址
			var href = response.content.fileName;
			$file.append('<input type="hidden" picaddress="'+href+'">');
			
			$('#submit').off('click');
			//用户上传受损单
			$('#submit').click(function(){
				if(accountId ==0){
					window.location.href = loginUrl;
				}
				//获得所有的部件图片地址与说明
				var picArr = getPictures();
				if(!picArr){
					$.messager.alert("操作提示", "请给每个部位最少一张图片(图片若在上传中，请稍等片刻)", "info");
					return false;
				}
				debugger
				var catParts = [];
				for(var i in picArr){
					catParts.push('{"car_part_id":"'+ $('._partId').eq(i).attr('_partId') +'","pic":"'+picArr[i].join(";")+'","remark":"'+ $('.question').eq(i).val() +'"}'); 
				}
				var params = {};
				params.memberId = memberId;
				params.catParts = JSON.stringify(catParts);
				commonAjax(_path + '/painting/save', params, function(data){
					if(data.retCode =="000000"){
						 window.location.href = yoyo.portalUrl;
					}else if(data.retCode == "000002"){
						 $.messager.alert("操作提示", "报价失败，请检查上传的数据是否有误！", "info");
					}
				});
			});
		}
	});
}

var exchange = function(id){
	var $obj = $('#'+id);
	var preNode = $obj.prev();
	var clone = preNode.clone();
	preNode.remove();
	clone.insertAfter($obj);
}; 
    
function getPictures(){
	var picArr = [];
	$('.uploader-list').each(function(){
		var pic = [];
		$(this).find('input:hidden').each(function(){
			pic.push(this.getAttribute('picaddress'));
		});
		if(pic.length <= 0){
			picArr = null;
			return false;
		}
		picArr.push(pic);
	});
	return picArr;
}

var innerHtml = '<div class="left"><span class="num" isNull="false">1</span>、</div>'+
'<div class="right">'+
    '<div class="innerBox">'+
        '<div class="title">点击左侧受损部位添加</div>'+
        '<div class="picture">'+
		    '<div id="fileList_cell1" node-type="data_list"class="uploader-list">'+
			    ' <div title="uploaderDiv" class="file-item thumbnail">'+
		    		'<a href="javascript:void(0)" onClick="uploadFile(this)">'+
			    		'<div class="pickImg">'+
							'<img src="'+_path+'/resources/images/add_img.png">'+
						'</div>'+
					'</a>'+
				'</div>'+
        	'</div>'+
		    '<div id="filePicker_cell1" node-type="data_picker">选择图片</div>'+
		'</div>'+
    '</div>'+
    '<div class="innerBox">'+
        '<div class="title2">请简单描述您的问题</div>'+
        '<textarea  name="textarea" class="question"></textarea>'+
    '</div>'+
'</div>'+
'<input _partid="" type="hidden" class="_partId">';

function uploadFile(obj){
	$(obj).parents('.picture').find('input[type="file"]').trigger('click');
}

//限制textarea的输入字符数
function limiter(e) {
	 var curLength=$(this).val().length;   
     if(curLength>=5){
     	if(e.which !=8){
     		return false;
     	}
     }  
}
