/**
 * 图片上传插件
 */
;
(function($) {
	var datakey = 'data-emgaments';
	$.fn.emgament = function() {
		var options = arguments[0];
		if (options && $.type(options) === 'object') {
			settings = $.extend({}, $.fn.emgament.defaults, options);
		} else {
			$.error("参数不合法！");
			return this;
		}
		return this.each(function() {
			var $element = $(this);
		});
	};
	// 插件的defaults
	$.fn.emgament.defaults = {
		width : "700px", // 宽度
		height : "400px", // 宽度
		itemWidth : "100px", // 文件项的宽度
		itemHeight : "100px", // 文件项的高度
		url : undefined, // 上传文件的路径
		filterFile : function(files) { 
			// 提供给外部的过滤文件格式等的接口，外部需要把过滤后的文件返回
			return files;
		},
		onSelect : function(selectFile, files) { 
			// 提供给外部获取选中的文件，供外部实现预览等功能
			// selectFile:当前选中的文件
			// allFiles:还没上传的全部文件
		},
		onDelete : function(file, files) { 
			// 提供给外部获取删除的单个文件，供外部实现删除效果
			// file:当前删除的文件 files:删除之后的文件
		},
		onProgress : function(file, loaded, total) { 
			// 提供给外部获取单个文件的上传进度，供外部实现上传进度效果
		},
		onSuccess : function(file, responseInfo) { 
			// 提供给外部获取单个文件上传成功，供外部实现成功效果
		},
		onFailure : function(file, responseInfo) { 
			// 提供给外部获取单个文件上传失败，供外部实现失败效果
		},
		onComplete : function(responseInfo) { 
			// 提供给外部获取全部文件上传完成，供外部实现完成效果
		},
	};

})(jQuery);