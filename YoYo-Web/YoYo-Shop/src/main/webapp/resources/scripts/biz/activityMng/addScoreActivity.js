var type=1;
$(document).ready(function(){
	type=$("#type").val();
	var limitNum=$("#limitNum").val();
	$('#goods_table').datagrid({
		url : _path + '/goods/selectGoodsForActivity?storeNum='+limitNum+'',
		columns : [ [ {
			field:'goodsId',
			title:'',
			align : 'center',
			formatter: function(value, rowData, rowIndex){
				return '<input type="radio" name="selectRadio" value="' + rowData.goodsId + '" />';
			}
		},{
			field : 'name',
			title : '商品名称',
			align : 'center',
			width : '50%'
		},{
			field : 'catName',
			title : '分类',
			align : 'center',
			width : '50%'
		}]],
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
		pageSize : 25,
		pageList : [ 25, 50, 100, 150 ],
		checkOnSelect:true,
		selectOnCheck:true,
		remoteSort : false,
		multiSort : false,
		singleSelect:true,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		},onSelect:function (rowIndex, rowData){
			$(".datagrid-btable").find("input[type=radio]").each(function (x,item){
				if($(item).val()==rowData.goodsId){
					$(item).prop("checked", true);
				}else{
					$(item).prop("checked", false);
				}
			});
		}
	});
	
	//显示分类对话框
	$('#gEditor-GCat-category').click(function() {
		$('#window_goods_select').window('open');
	});
	
	findMemberLevel();
	
});

function selectGoods() {
	var record = $('#goods_table').datagrid('getSelected');
	if(record){
		$('#labelCategory').text(record.name);
		$('#goodsId').val(record.goodsId);
		$('#catId').val(record.catId);
		$('#price').val(record.price);
		$('#remainnums').val(record.store);
		$('#window_goods_select').window('close');
	}else{
		easyuiMsg('提示', "请选择商品！");
	}
}

function closeGoods() {
	$('#window_goods_select').window('close');
}

/**获得会员等级*/
function findMemberLevel() {
	$.getJSON(biz.rootPath() + '/memberLevel/findAll', function(json) {
		var html = "";
		$.each(json.content, function(i, n) {
			html += '<label>'+n.name+':</label><label> 积分:</label><input type="text" class="easyui-validatebox" validType="integ" name="memScore" id="memScore_'+i+'"/><label> 价格:</label><input type="text" class="easyui-validatebox" validType="money" name="memPrice" id="memPrice_'+i+'"/>';
			html += '<input type="hidden" name="memberLvIds" value="'+n.memberLvId+'" id="level_'+i+'"/></br>';
		});
		$('#mLev').append(html);
	});

}

function showLev(){
	$('#lev').show();
}

function hideLev(){
	$('#lev').hide();
}

function saveActivity() {
	var params = biz.serializeObject($("#form_saveActivity"));
	
	if ($('#goodsId').val() == '' || $('#goodsId').val() == null) {
		easyuiMsg('提示', "请选择参加活动的商品！");
		return false;
	}
	
	if (type==1 && ($('#score').val() == '' || $('#score').val() == null)) {
		easyuiMsg('提示', "请填写积分！");
		return false;
	}
	
//	if ($('#imageId1').val() == '' || $('#imageId1').val() == null) {
//		easyuiMsg('提示', "请添加活动广告图！");
//		return false;
//	}
//	
	if(params.lastPrice > params.price){
		easyuiMsg('提示', "价格不能大于原始价格！");
		return false;
	}
	
	if(parseInt(params.nums) > parseInt(params.remainnums)){
		easyuiMsg('提示', "商品的数量不能大于商品库存！");
		return false;
	}
	
	if(parseInt(params.personlimit) > parseInt(params.nums)){
		easyuiMsg('提示', "限购数量不能大于商品的数量！");
		return false;
	}
	
	if($("input[name='ismemlv']:checked").val()==1 && $("input[name='memberLvIds']").length > 0) {
		var levs = $("input[name='memberLvIds']").val();
		var memJson = "[";
		$.each($("input[name='memberLvIds']"),function(index,memLvId){
			memJson += '{\"levelId\":\"' + memLvId.value + '\",\"memScore\":\"' + $("#memScore_"+index).val() + '\",\"memPrice\":\"' +  $("#memPrice_"+index).val()+'\"},';
		});
		memJson = memJson.length > 1 ? memJson.substring(0, memJson.length - 1) : memJson;
		memJson += "]";
		params.memberLvScore=memJson;
	}
	
	
	var url = _path + '/activityApply/saveScoreActivity';
	yoyo.ajaxRequest(url,params,"","json",function(data){
		if (data.head.retCode == '000000') {
			layer.msg('保存成功',{time: 3000});
			window.location.href = _path + '/activityApply/myActivityList';
		}else {
			layer.msg('保存失败',{time: 3000});
		}
	});
}
function cancle(){
	window.location.href = _path + '/activityApply/scoreActivityList';
}
