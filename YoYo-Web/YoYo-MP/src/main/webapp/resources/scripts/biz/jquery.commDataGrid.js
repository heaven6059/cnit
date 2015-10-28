// 创建一个闭包
;
(function($) {
	// 插件的定义
	$.fn.advanceSeacher = function(options) {
		// build main options before element iteration
		var opts = $.extend({}, $.fn.advanceSeacher.defaults, options);
		// iterate and reformat each matched element
		return this.each(function() {
			init(opts);
		});
	};

	function init(options) {
		if (options.aimDataGrid) {
			var rootEle = genElement(options.aimDataGrid);
			renderElement(options.renderTo ? options.renderTo : options.aimDataGrid, rootEle);
			bindEvent(options.advanceSearchButton, options.aimDataGrid + 'asw');
		}
	}

	function genElement(_aimDataGrid) {
		if ($('#' + _aimDataGrid)) {
			var columns = $aimdg.datagrid('options').columns;
			var rootEle = $('<table />');
			$.each(columns, function(index, column) {
				if (!column.hidden) {
					var _label = $('<label />').text(column.title);
					if (column.editor.type) {
						// text,textarea,checkbox,numberbox,validatebox,datebox,combobox,combotree
						var _type = column.editor.type;
						var _input = undefined;
						if (_type == 'textarea') {
							_input = $('<input />', { name : column.field + '_as' , multiline : true , class : 'easyui-textbox' });
						} else if (_type == 'checkbox') {
							_input = $('<input />', { name : column.field + '_as' , type : checkbox });
						} else if (_type == 'numberbox') {
							_input = $('<input />', { name : column.field + '_as' , class : 'easyui-numberbox' });
						} else if (_type == 'validatebox') {
							_input = $('<input />', { name : column.field + '_as' , class : 'easyui-validatebox' });
						} else if (_type == 'datebox') {
							_input = $('<input />', { name : column.field + '_as' , class : 'easyui-datebox' });
						} else if (_type == 'combobox') {
							_input = $('<select />', { name : column.field + '_as' , class : 'easyui-combobox' });
						} else if (_type == 'combotree') {
							_input = $('<select />', { name : column.field + '_as' , class : 'easyui-combotree' });
						}
					} else {
						_input = $('<input />', { name : column.field + '_as' , class : 'easyui-textbox' });
					}
					rootEle.append($('<tr />').append($('<td />').append(_label)).append($('<td />').append(_input)));
				}
			});
			rootEle = $('<div />', { class : easyui - window , closed : true , id : $aimdg.datagrid('options').id + '_asw' }).attr('data-options',
					'inline:true,cache:false,onBeforeClose:clearCondition').append($('<form />', { id : $aimdg.datagrid('options').id + '_asf' })).append(rootEle);
			return rootEle;
		}
	}

	// 插件的defaults
	$.fn.advanceSeacher.defaults = { tableType : 'datagrid' , advanceSearchOn : false , methods : { loadFilter : function() {} , bindingEvent : function() {} } };
	// 闭包结束
})(jQuery);