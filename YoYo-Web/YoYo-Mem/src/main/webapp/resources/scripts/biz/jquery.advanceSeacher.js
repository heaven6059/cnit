/**
 * 
 */
// 创建一个闭包
;
(function($, window, document, undefined) {
	// 插件的定义
	$.fn.advanceSeacher = function(options) {
		var opts = $.extend({}, $.fn.advanceSeacher.defaults, options);
		return this.each(function() {
			init(this, opts);
		});
	};

	function init(obj, options) {
		if (options.aimDataGrid) {
			var rootEle = genElement(options.aimDataGrid);
			renderElement(options.renderTo ? options.renderTo : options.aimDataGrid, rootEle, options.aimDataGrid);
			bindEvent(options.renderTo ? options.renderTo : options.aimDataGrid, obj.id, options.aimDataGrid);
		}
	}

	function genElement(_aimDataGrid) {
		if ($('#' + _aimDataGrid)) {
			var $aimdg = $('#' + _aimDataGrid);
			var rootEle = $('<table />', { class : 'table-form' });
			$.each($aimdg.datagrid('options').columns[ 0 ], function(index, column) {
				if (!column.hidden) {
					var _label = $('<label />').text(column.title);
					if (column.editor && column.editor.type) {
						// text,textarea,checkbox,numberbox,validatebox,datebox,combobox,combotree
						var _type = column.editor.type;
						var _input = undefined;
						if (_type == 'textarea') {
							_input = $('<input />', { name : column.field , multiline : true , class : 'easyui-textbox' });
						} else if (_type == 'checkbox') {
							_input = $('<input />', { name : column.field + 'Q' , type : checkbox });
						} else if (_type == 'numberbox') {
							_input = $('<input />', { name : column.field + 'Q' , class : 'easyui-numberbox' });
						} else if (_type == 'validatebox') {
							_input = $('<input />', { name : column.field + 'Q' , class : 'easyui-validatebox' });
						} else if (_type == 'datebox') {
							_input = $('<input />', { name : column.field + 'Q' , class : 'easyui-datebox' });
						} else if (_type == 'combobox') {
							_input = $('<select />', { name : column.field + 'Q' , class : 'easyui-combobox' });
						} else if (_type == 'combotree') {
							_input = $('<select />', { name : column.field + 'Q' , class : 'easyui-combotree' });
						}
					} else {
						_input = $('<input />', { name : column.field + 'Q' , class : 'easyui-textbox' });
					}
					rootEle.append($('<tr />').append($('<td />', { class : 'table-form-label' }).append(_label)).append($('<td />').append(_input)));
				}
			});
			var advsForm = $('<form />', { id : $aimdg.datagrid('options').id + '_asf' }).append(rootEle);
			var doSearchButton = $('<a />', { class : 'easyui-linkbutton' , plain : true , id : $aimdg.datagrid('options').id + '_dosb' , iconCls : 'icon-search' }).text('查询');
			var doClearForm = $('<a />', { class : 'easyui-linkbutton' , plain : true , id : $aimdg.datagrid('options').id + '_docf' , iconCls : 'icon-clear' }).text('清除条件');
			var advsLayout = $('<div />', { class : 'easyui-layout' , fit : true }).append($('<div />', { region : 'center' , fit : true }).append(advsForm)).append(
					$('<div />', { region : 'south' , border : true , style : 'text-align: center; height: 30px; line-height: 30px;' }).append(doSearchButton).append(doClearForm));
			return advsLayout;
		}
	}

	function clearCondition(_columns) {
		var form = $('#' + $aimdg.datagrid('options').id + '_as')
		if (form) {
			form('clear');
		}
	}

	function bindEvent(_parent, _buttonId, _aimGrid) {
		$('#' + _buttonId).on('click', function() {
			var width = $('#' + _parent).parent().width();
			$('#' + _aimGrid + '_asw').window('open').window('resize', { top : 0 , left : width - $('#' + _aimGrid + '_asw').window('options').width });
		})
		$('#' + _aimGrid + '_dosb').on('click', function() {
			var params = biz.serializeObject($('#' + _aimGrid + '_asf'))
			$('#' + _aimGrid).datagrid('reload', params);
		})
		$('#' + _aimGrid + '_docf').on('click', function() {
			$('#' + _aimGrid + '_asf').form('clear');
		})
	}

	function renderElement(_parent, _rootEle, _aimDataGrid) {
		debugger;
		var advsWindow = $('<div />', { id : _aimDataGrid + '_asw' });
		$('#' + _parent).parent().append(advsWindow);
		$('#' + _aimDataGrid + '_asw').window({ title : '高级查询' , width : 400 , height : 500 , closed : true , inline : true , cache : false , resizable : false , content : _rootEle , zIndex : 9010 });
	}

	function logger() {
		console.log('今天很好笑！');
	}
	// 定义暴露format函数
	$.fn.advanceSeacher.format = function(txt) {
		return '<strong>' + txt + '</strong>';
	};
	// 插件的defaults
	$.fn.advanceSeacher.defaults = { foreground : 'red' , background : 'yellow' };
	// 闭包结束
})(jQuery, window, document);