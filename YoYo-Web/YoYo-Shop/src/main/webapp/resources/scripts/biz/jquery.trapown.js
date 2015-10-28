/**
 * 商品下拉 使用select2插件
 */
;
(function($, window, document, undefined) {
	// 私有变量用来缓存数据
	var data = null;

	var dataStructTransfer = function(data, opts) {
		var retData = [ ];
		$.each(data, function(index, _data) {
			retData[ index ] = { 'id' : _data[ opts.idField ] , 'text' : _data[ opts.textField ] , state : 'closed' };
		});
		return retData;
	}

	var methods = { init : function(obj, opts) {
		var $this = $(obj);
		if (opts.url) {
			$this.combotree({
				url : opts.url ,
				multiple : opts.multiple ,
				queryParams : { 'parentCatId' : opts.initParentId } ,
				columns : [
					[
							{ field : opts.idField , title : '分类Id' , fixed : true } ,
							{ field : opts.textField , title : '分类名称' , fixed : true }
					]
				] , fitColumns : true , loadFilter : function(data) {
					return dataStructTransfer(data, opts);
				} , onBeforeExpand : function(row) {
					var url = opts.url + '?' + opts.parentField + '=' + row.id;
					$this.combotree("tree").tree("options").url = url;
					return true;
				} , onLoadSuccess : function(node, data) {
					$this.combotree('tree').tree("expandAll");
					if(opts.selected!=null){ //设置默认值
						$this.combotree('setValues',opts.selected);
					}
				} });
		}
		$this.data('options', opts);
		return $this;
	} };

	// 插件的定义
	$.fn.trapown = function(options) {
		if (typeof options == 'object') {
			// build main options before element iteration
			var opts = $.extend({}, $.fn.trapown.defaults, options);
			// iterate and reformat each matched element
			return this.each(function() {
				methods[ 'init' ](this, opts);
			});
		} else if (typeof options == 'string') {
			if (methods[ options ]) {
				methods[ options ](arguments[ 1 ]);
			}
		}
	};
	// 插件的defaults
	$.fn.trapown.defaults = { cache : 'true' , data : undefined };
})(jQuery, window, document);