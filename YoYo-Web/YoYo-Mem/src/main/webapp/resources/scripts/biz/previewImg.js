//图片上传预览    IE是用了滤镜。
var browser = navigator.appName;

var b_version = navigator.appVersion;

	function previewImage(file, preview,imghead) {
		if (!file || !file.value)
			return;
		var patn = /\.jpg$|\.png$|\.jpeg$|\.gif$|\.JPG$|\.PNG$|\.JPEG$|\.GIF$/i;
		if (patn.test(file.value)) {
			var MAXWIDTH = 448;
			var MAXHEIGHT = 248;
			var div = document.getElementById(preview);
			if (file.files && file.files[0]) {
				div.innerHTML = '<img id='+imghead+'>';
				var img = document.getElementById(imghead);
				img.onload = function() {
					var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT,
							img.offsetWidth, img.offsetHeight);
					img.width = rect.width;
					img.height = rect.height;
					//             img.style.marginLeft = rect.left+'px';
					img.style.marginTop = rect.top + 'px';
				};
				var reader = new FileReader();
				reader.onload = function(evt) {
					img.src = evt.target.result;
				};
				reader.readAsDataURL(file.files[0]);
			} else // 兼容IE
		{
			console.log(b_version);
			var version = b_version.split(";");

			var trim_Version = version[1].replace(/[ ]/g, "");
			if (browser == "Microsoft Internet Explorer"&& (trim_Version == "MSIE8.0" ||trim_Version == "MSIE9.0" || trim_Version == "MSIE6.0" || trim_Version == "MSIE7.0" ||trim_Version == "MSIE5.0" ))

			{
				var newPreview = document.getElementById(preview);
				var img=document.getElementById(imghead);
				newPreview.removeChild(img);
				file.select();
				newPreview.focus();
				var imgSrc = document.selection.createRange().text;
				var imgDiv = document.createElement("div");
				document.body.appendChild(imgDiv);
				imgDiv.id=imghead;
				imgDiv.style.width = "400px";
				imgDiv.style.height = "200px";
				imgDiv.style.margin= "auto";
				imgDiv.style.marginTop="20px";
				imgDiv.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod = scale)";
				imgDiv.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
				newPreview.appendChild(imgDiv);
				

			} else {
				var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
				file.select();
				file.blur();
				var imgSrc = document.selection.createRange().text;
				var img = document.createElement("img");
				var img1 = document.getElementById(imghead);
				img = img1;
				var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT,img.offsetWidth, img.offsetHeight);
				status = ('rect:' + rect.top + ',' + rect.left + ','+ rect.width + ',' + rect.height);
				img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod = scale;)";
				img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;
				div.innerHTML = '<img id=' + imghead + '/>';
				div.innerHTML = "<div id=" + preview + " style='width:"+ rect.width + "px;height:" + rect.height+ "px;margin-top:" + rect.top + "px;" + sFilter+ imgSrc + "\")'></div>";
				// 测试

			}

		}
	} else {
		document.getElementById(imghead).src = "";
		parent.$.messager.alert('提示', '选择图片上传!', 'error');
	}

	}
	function onPreviewLoad(sender) {
		autoSizePreview(sender, sender.offsetWidth, sender.offsetHeight);
	}
	function autoSizePreview(objPre, originalWidth, originalHeight) {
		var zoomParam = clacImgZoomParam(448, 248, originalWidth,
				originalHeight);
		objPre.style.width = zoomParam.width + 'px';
		objPre.style.height = zoomParam.height + 'px';
		objPre.style.marginTop = zoomParam.top + 'px';
		objPre.style.marginLeft = zoomParam.left + 'px';
	}
	function clacImgZoomParam(maxWidth, maxHeight, width, height) {
		var param = {
			top : 0,
			left : 0,
			width : width,
			height : height
		};
		if (width > maxWidth || height > maxHeight) {
			rateWidth = width / maxWidth;
			rateHeight = height / maxHeight;

			if (rateWidth > rateHeight) {
				param.width = maxWidth;
				param.height = Math.round(height / rateWidth);
			} else {
				param.width = Math.round(width / rateHeight);
				param.height = maxHeight;
			}
		}

		param.left = Math.round((maxWidth - param.width) / 2);
		param.top = Math.round((maxHeight - param.height) / 2);
		return param;
	}