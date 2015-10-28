function getSpecByCatId(catId,goodsId) {
	commonAjax(_path + '/spec/specAndValuesByCatId', { 'catId' : catId }, function(data) {
		renderSpecs(data.content);
		commonAjax(_path + '/product/productSpec', { 'goodsId' : goodsId }, function(data) {
			genExistProduct(data.content);
		}, null)
	},null)
}

function renderSpecs(data) {
	var root = $('<ul class="specs-ul"></ul>');
	$.each(data, function(index, spec) {
		var ele = $('<li><a id="spec_' + spec.specId + '">' + spec.specName + '</a><span>[ 0 ]</span></li>');
		renderSpecValues(spec.specId, spec.values);
		root.append(ele);
	});
	$('.specs').append(root);
	bindingEvent();
};

function renderSpecValues(specId, data) {
	var root = $('<ul class="specs-value-ul spec_' + specId + ' hidden"></ul>');
	$.each(data, function(index, specValue) {
		specValue.disabled = false;
		root.append($('<li />').append($('<input type="checkbox"/>').attr(specValue).attr('spec_id', 'spec_' + specId).attr('id', 'spec_value_' + specId + '_' + specValue.specValueId)).append(
				'<span>' + specValue.specValueName + '</span>'));
	});
	$('.spec-values-list').append(root);
}

function renderValueTable(specId) {
	$('.spec-value-table').append();
}

// 绑定事件
function bindingEvent() {
	$('.specs-ul').find('a').on('click', function() {
		$(this).parent().siblings().removeClass("c");
		$(this).parent().addClass('c');
		var specValues = $('.' + $(this).attr('id'));
		specValues.siblings().addClass("hidden");
		$('.spec-value-table table').find('tbody').addClass('hidden');
		specValues.removeClass("hidden");
	});
	$('.specs-value-ul').find('input:checkbox').on(
			'click',
			function() {
				var $this = $(this);
				var specId = $this.attr('spec_id');
				var $spec = $('#' + specId);
				var specValueId = $this.attr('id');
				if ($this.prop("checked")) {
					var $th = $('<tr><th>系统规格</th><th>规格值</th><th>操作</th></tr>');
					var $tb = $('<tr><td>' + $spec.text() + '</td><td>' + $this.attr('specvaluename')
							+ '</td><td><i class="top" onclick="up(this)"></i><i class="bottom" onclick="down(this)"></i><i class="delete" onclick="deleteSpecValue(this)"></i></td></tr>');
					var $tbody = $('<tbody></tbody>').addClass(specId).addClass(specValueId).append($th).append($tb);
					$('.spec-value-table table').append($tbody);
				} else {
					$('.spec-value-table table').find('.' + specValueId).remove();
				}
				createSKU();
				// 更新规格列表的选择数量
				var checkedNum = $this.parent().parent().find('input:checked').length;
				$spec.siblings('span').text('[ ' + checkedNum + ' ]');
			});
	$('#spec-action-gen-prod').off('click').on('click', function() {
		specActionGenProd();
	});
	$('#spec-action-cache-prod').off('click').on('click', function() {
		cacheProductData();
		cacheSpecStatistic();
		$('#spec-window').window('close');
	});
}

// 在父页面显示规格概述
function renderToParent(products) {
	var $rendEle = $('#spec-no-spec-switch').parent();
	$rendEle.find('.common-tag').remove();
	$.each(products, function(index, data) {
		$rendEle.append('<span class="common-tag">' + data.specInfo + '</span>');
	});
}

// 将货品缓存到父页面规格弹出框的ProductData字段
function cacheProductData() {
	$('#spec-window').removeData('ProductData');
	var productData = [ ];
	var staticMktprice = 0;
	var staticStore = 0;
	var staticPrice = Number.MAX_VALUE;
	var temp = 0;
	$('.product-table-body tr').each(function(index) {
		var $this = $(this);
		$('.product-table-body tr').eq(0).find('span[name="product_spec"]');
		var productDataLen = productData.length;
		productData[ productDataLen ] = {};
		productData[ productDataLen ].specInfo = $this.find('.product_spec').map(function() {
			return $(this).html();
		}).get().join(',');
		productData[ productDataLen ].specDesc = $this.find('.product_spec_desc').text();
		productData[ productDataLen ].productId = $this.find('input[name="productId"]').eq(0).val();
		productData[ productDataLen ].bn = $this.find('input[name="bn"]').eq(0).val();
		temp = $this.find('input[name="store"]').eq(0).val();
		productData[ productDataLen ].store = temp;
		if(temp){
			staticStore = staticStore+Number.parseFloat(temp); 
		}
		temp = $this.find('input[name="price"]').eq(0).val()
		productData[ productDataLen ].price = temp;
		if(temp&&temp<staticPrice){
			staticPrice = temp;
		}
		productData[ productDataLen ].cost = $this.find('input[name="cost"]').eq(0).val();
		temp = $this.find('input[name="mktprice"]').eq(0).val();
		productData[ productDataLen ].mktprice = temp;
		if(temp&&temp>staticMktprice){
			staticMktprice = temp;
		}
		productData[ productDataLen ].storePlace = $this.find('input[name="storePlace"]').eq(0).val();
		productData[ productDataLen ].marketable = $this.find('input[name="marketable"]').eq(0).prop('checked')?'1':'0';
	});
	$('#id_goods_price').val(staticPrice);
	$('#id_goods_store').val(staticStore);
	$('#id_goods_mktprice').val(staticMktprice);
	$('#spec-window').data('ProductData', productData);
	renderToParent(productData);
}

//将货品规格汇总值商品主表的spec_desc字段用于商品详情页规格显示
function cacheSpecStatistic(){
	var specs = $('.specs-ul').find('a');
	var specArray = [];
	$.each(specs,function(index,spec){
		var specStr = [$(spec).attr('id').split('_')[1],$(spec).text()].join('|');
		var specValue = [];
		$(".spec-values-list  input[spec_id=" + $(spec).attr('id') + "]:checked").each(function() {
			specValue.push($(this).attr('specvalueid')+'|'+$(this).attr('specvaluename'));
		});
		specValue = '{'+specValue.join(';')+'}';
		specArray.push(specStr+':'+specValue);
	});
	$('#spec-window').data('specDesc', specArray.join(','));
}

var GOODS_ID = null;
var CAT_ID = null;
$(function() {
	GOODS_ID = $('#spec-window').attr('goodsid');
	CAT_ID = $('#spec-window').attr('catid');
	getSpecByCatId(CAT_ID,GOODS_ID);
});
// 依据选定的规格和规格值生成货品
function specActionGenProd() {
	var html = '';
	// 获得选择的规格的值
	var productArr = doExchange(arrayInfor); // 货品规格
	if (productArr !== undefined && productArr.length > 0) {
		$.each(productArr, function(i, item) {
			var td_array = item.split(",");
			var productEleId = ['product'];
			$.each(td_array, function(index, val) { // 遍历多个规格
				html += '<span class="product_spec">' + val + '</span>';
				productEleId.push(val.split(':')[0].split('|')[0]);
				productEleId.push(val.split(':')[1].split('|')[0]);
			});
			productEleId = productEleId.join("_");
			if($('#'+productEleId).length==0){
				html = '<tr id="'+productEleId+'" status="new">';
				html += '<td><input type="checkbox" name="marketable" class="pro-marketable-check" checked="checked"></td>';
				html += '<td>';
				$.each(kreateSpecInfo(item), function(i, obj) { // 遍历多个规格
					html += '<span class="product_spec">' + obj + '</span>';
				});
				html +='<div class="hide product_spec_desc">'+item+'</div>';
				html += '</td>';
				html += '<td><div name="pic"><div></td>';
				html += '<td><input type="hidden" name="productId"><input type="text" name="bn"  size="12"></td>';
				html += '<td><input type="text" name="store" size="10"></td>';
				html += '<td><input type="text"	 name="price" size="8"></td>';
				html += '<td><input type="text" name="cost" size="8"></td>';
				html += '<td><input type="text"	 name="mktprice" size="8"></td>';
				html += '<td><input type="text" name="storePlace" size="4"></td>';
				html += '<td><img class="operater"  style="cursor: pointer;" alt="删除" src=""></td>';
				html += '</tr>';
				$('.product-table-body').append(html);
			}
		});
		// 绑定删除货品按钮事件
		$('.product-table-body .operater').on('click', function() {
			var $this = $(this).parent().parent();
			productArr.splice($this.index(), 1);// 当前行的id
			$this.remove();
		});
	} else {}
}

function kreateSpecInfo(specDesc){
	var specInfo = [];
	$.each(specDesc.split(','),function(index,spec){
		specInfo.push([spec.split(':')[0].split('|')[1],spec.split(':')[1].split('|')[1]].join(':'));
	});
	return specInfo;
}

function genExistProduct(data){
	var specObj = new Object();
	$.each(data, function(i, item) {
		var td_array = item.specDesc.split(",");
		var productEleId = ['product'];
		var spec = '';
		$.each(td_array, function(index, val) { // 遍历多个规格
			spec += '<span class="product_spec">' + val + '</span>';
			var valsplit = val.split(':');
			$.each(valsplit,function(index,temp){
				productEleId.push(temp.split('|')[0]);
			});
			if(!specObj[valsplit[0]]){
				var valObject = new Object();
				valObject[valsplit[1]]=valsplit[1];
				specObj[valsplit[0]]=valObject;
			}else{
				var valObject = specObj[valsplit[0]];
				valObject[valsplit[1]]=valsplit[1];
				specObj[valsplit[0]]=valObject;
			}
		});
		productEleId = productEleId.join("_");
		var html = '<tr id="'+productEleId+'" status="exist">';
		if(item.marketable=='0'){
			html += '<td><input type="checkbox" name="marketable" class="pro-marketable-check"></td>';
		}else{
			html += '<td><input type="checkbox" name="marketable" class="pro-marketable-check" checked="checked"></td>';
		}
		html += '<td>';
		$.each(kreateSpecInfo(item.specDesc), function(i, obj) { // 遍历多个规格
			html += '<span class="product_spec">' + obj + '</span>';
		});
		html +='<div class="hide product_spec_desc">'+item.specDesc+'</div>';
		html += '</td>';
		html += '<td><div name="pic"><div></td>';
		html += '<td><input type="hidden" name="productId" value='+item.productId+'><input type="text" name="bn"  size="12" value='+item.bn+'></td>';
		html += '<td><input type="text" name="store" size="10" value='+item.store+'></td>';
		html += '<td><input type="text"	 name="price" size="8" value='+item.price+'></td>';
		html += '<td><input type="text" name="cost" size="8" value='+item.cost+'></td>';
		html += '<td><input type="text"	 name="mktprice" size="8" value='+item.mktprice+'></td>';
		html += '<td><input type="text" name="storePlace" size="4" value='+item.storePlace+'></td>';
		html += '<td><img class="operater"  style="cursor: pointer;" alt="删除" src=""></td>';
		html += '</tr>';
		$('.product-table-body').append(html);
	});
	//勾选规格值 更新规格数字
	$.each(specObj,function(index,obj){
		var specid = index.split('|')[0];//specId
		var spec = $('#spec_'+specid);
		var checkedNum = 0;//规格数字
		$.each(obj,function(index2){
			var specvalueid=index2.split('|')[0];
			var specValue = $('#spec_value_'+specid+'_'+specvalueid);
			specValue.prop('checked','checked');
			var $th = $('<tr><th>系统规格</th><th>规格值</th><th>操作</th></tr>');
			var $tb = $('<tr><td>' + spec.text() + '</td><td>' + specValue.attr('specvaluename')
					+ '</td><td><i class="top" onclick="up(this)"></i><i class="bottom" onclick="down(this)"></i><i class="delete" onclick="deleteSpecValue(this)"></i></td></tr>');
			var $tbody = $('<tbody></tbody>').addClass('spec_'+specid).addClass('spec_value_'+specid+'_'+specvalueid).append($th).append($tb);
			$('.spec-value-table table').append($tbody);
			checkedNum += 1;
		});
		spec.siblings('span').text('[ ' + checkedNum + ' ]');
		$('.specs ul li:eq(0) a').click();
	});
	//缓存至父页面
	cacheProductData();
}

// 组合数组: 组合算法 获得所有组合
function doExchange(doubleArrays) {
	var len = doubleArrays.length;
	if (len >= 2) {
		var arr1 = doubleArrays[ 0 ];
		var arr2 = doubleArrays[ 1 ];
		var len1 = doubleArrays[ 0 ].length;
		var len2 = doubleArrays[ 1 ].length;
		var newlen = len1 * len2;
		var temp = new Array(newlen);
		var index = 0;
		for (var i = 0; i < len1; i++) {
			for (var j = 0; j < len2; j++) {
				temp[ index ] = arr1[ i ] + "," + arr2[ j ];
				index++;
			}
		}
		var newArray = new Array(len - 1);
		newArray[ 0 ] = temp;
		if (len > 2) {
			var _count = 1;
			for (var i = 2; i < len; i++) {
				newArray[ _count ] = doubleArrays[ i ];
				_count++;
			}
		}
		return doExchange(newArray);
	} else {
		return doubleArrays[ 0 ];
	}
}

// SKU信息组合
var arrayInfor = new Array();// 存放每组选中的CheckBox值的对象
var arrayTile = new Array();// 规格标题组数
function createSKU() {
	var SKUObj = $(".specs-ul").find('a');
	arrayTile = new Array();// 规格标题组数
	arrayInfor = new Array();// 存放每组选中的CheckBox值的对象
	$.each(SKUObj, function(i, item) {
		var specId = $(item).attr('id');
		arrayTile.push($(item).text());
		// 选中的CHeckBox取值
		var order = new Array();
		$(".spec-values-list  input[spec_id=" + specId + "]:checked").each(function() {
			var $this = $(this);
			var specEleId = $this.attr('spec_id'); 
			var specItem = $this.attr('specid')+'|'+$('#'+specEleId).text()+':'+$this.attr('specvalueid')+'|'+$(this).attr('specvaluename');
			order.push(specItem);
		});
		arrayInfor.push(order);
	});
}