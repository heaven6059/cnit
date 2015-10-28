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
			if(opts.identifier==null || (opts.identifier!=null && _data['identifier']!=null && opts.identifier.indexOf(_data['identifier'])!=-1)){
				retData.push({ id : _data[ opts.idField ] , text : _data[ opts.textField ] , state : 'closed' ,identifier:_data['identifier']});
			}
		});
		return retData;
	}

	var methods = { init : function(obj, opts) {
		var $this = $(obj);
		var queryParams={ 'parentCatId' : opts.initParentId } ;
		if(opts.brandType){
			queryParams={ 'parentCatId' : opts.initParentId,'brandType':opts.brandType } ;
		}
		if (opts.url) {
			$this.combotree({
				url : opts.url ,
				multiple : opts.multiple ,
				queryParams : queryParams ,
				columns : [
					[
							{ field : opts.idField , title : '分类Id' , fixed : true } ,
							{ field : opts.textField , title : '分类名称' , fixed : true },
							{ field : 'identifier', title : '' , fixed : true }
					]
				] , fitColumns : true , loadFilter : function(data) {
					return dataStructTransfer(data, opts);
				} , onBeforeExpand : function(row) {
					if(opts.hiddenChild){  //是否显示子类
						return false;
					}
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