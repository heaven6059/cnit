/**
 * 相关商品下拉 使用select2插件
 */
;
(function($, window, document, undefined) {
	// 私有变量用来缓存数据
	var data = null;

	var dataStructTransfer = function(data, opts) {
		var retData = [ ];
		$.each(data, function(index, _data) {
			retData[ index ] = { 'id' : _data[ opts.idField ] , 'text' : _data[ opts.textField ] };
		});
		return retData;
	};

	var methods = { init : function(obj, opts) {

		var $this = $(obj);
		if (opts.data) {
			$this.select2({ data : opts.data });
		} else if (opts.url) {
			$this.select2({ multiple : opts.multiple , closeOnSelect : true ,
				ajax : { url : opts.url , cache : opts.cache , delay : 1000 , processResults : function(data) {
					return { results : dataStructTransfer(data.content.rows, opts) };
				} , data : function(params) {
					if (opts.queryParams) {
						opts.queryParams.page = params.page ? params.page : 1;
					} else {
						opts.queryParams = {};
						opts.queryParams.page = params.page ? params.page : 1;
						opts.queryParams.rows = 20;
					}
					return opts.queryParams;
				} } });
		}
		$this.data('options', opts);
		return $this;
	} };

	// 插件的定义
	$.fn.comdropdown = function(options) {
		if (typeof options == 'object') {
			// build main options before element iteration
			var opts = $.extend({}, $.fn.comdropdown.defaults, options);
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
	$.fn.comdropdown.defaults = { cache : 'true' , data : undefined };
})(jQuery, window, document);