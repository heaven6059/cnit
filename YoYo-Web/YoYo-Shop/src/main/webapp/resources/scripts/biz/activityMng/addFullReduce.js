var count = 0;
var memberIds = [];
//var jsonArray = new Array();
var goodsIds=new Array();  //选择的商品id集合
var selectGoodsIds = new Array();  //已经选择的商品id集合，即：已经保存到数据中的，此次是修改
$(function() {
	var actId = $('#actId').val();
	if(null != actId && '' != actId) {
		initEdit(actId);
	}else {
		initGoods();
		findMemberLevel(memberIds);
	}
	
	//findMemberLevel(memberIds);
	
	
	
	//$('#goodsAddListTable').datagrid('loadData', { total: 0,rows: [] });
	/*$('#goodsAddListTable').datagrid({
		url : _path + '/goods/findAddActivityGoods',
		columns : [ [ {
			field : 'smallPic',
			align : 'center',
			halign: 'center',
			width:60,
			title : '商品图片',
			formatter : function(value, row) {
				if (value) {
					var imageUrl=value.split(".")[0] + ".44x44." +value.split(".")[1];
					return '<img  src="' + yoyo.imageUrl+imageUrl+ '">';
				} else {
					return '<img  src="' + yoyo.imageUrl + '/product/201505/1431434653329.64x48.jpg" width="64px" height="64px;">';
				}
			}
		},{
			field : 'name',
			align : 'center',
			width:180,
			halign: 'center',
			title : '商品名称'
		}, {
			field : 'price',
			align : 'center',
			width:160,
			halign: 'center',
			title : '商品价格'
		},{
			field : 'editor',
			halign: 'center',
			title : '操作',
			align : 'center',
			formatter : function(value, row) {
				var html =  "<a  href='javascript:updateActivityGoods("+row.goodsId+")'>删除</a>";
				return html;
			}
		}] ],
		//pagination : true,
		//pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
		selectOnCheck:false,
		checkOnSelect:false,
		remoteSort : true,
		multiSort : true,
		height:300,
		singleSelect : true,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}
	});*/
	
	//添加配件
	$('#button-rule-add').on('click', function() {
		count++;
		var html='<div class="rule-price">消费满<input type="text" name="fullPrice" style="width:50px;" maxlength="4" class="easyui-validatebox" required="required" id="fullPrice'+count+'"  >元，'+
		'减<input type="text" style="width:50px;" maxlength="4" name="reducePrice" class="easyui-validatebox" required="required" id="reducePrice'+count+'"  >元<span><a style="  margin-right: 10px;" onclick="deleteRule(this)">删除</a></span></div>';
		$('#good-rule-group').append(html);
		$.parser.parse('#good-rule-group');
	});
	
	
	
});

/*function delEditor(value, row, index) {
	var html =  "<a  href='javascript:updateActivityGoods("+row.goodsId+","+index+")'>删除</a>";
	return html;
}

function imageEditor(value, row, index) {
	if (value) {
		var imageUrl=value.split(".")[0] + ".44x44." +value.split(".")[1];
		return '<img  src="' + yoyo.imageUrl+imageUrl+ '">';
	} else {
		return '<img  src="' + yoyo.imageUrl + '/product/201505/1431434653329.64x48.jpg" width="64px" height="64px;">';
	}
}*/

function initGoods(){
	//selectGoodsIds = [];
	goodsIds=[];
	$('#goodsListTable').datagrid({
		url : _path + '/goods/findActivityGoods',
		columns : [ [ {
			field:'ck',
			checkbox:"true"
		},{
			field : 'smallPic',
			align : 'center',
			width:60,
			halign: 'center',
			title : '商品图片',
			formatter : function(value, row) {
				if (value) {
					var imageUrl=value.split(".")[0] + ".44x44." +value.split(".")[1];
					return '<img  src="' + yoyo.imageUrl+imageUrl+ '">';
				} else {
					return '<img  src="' + yoyo.imageUrl + '/product/201505/1431434653329.64x48.jpg" width="64px" height="64px;">';
				}
			}
		},{
			field : 'name',
			align : 'center',
			width:180,
			halign: 'center',
			title : '商品名称'
		},{
			field : 'price',
			align : 'center',
			width:180,
			halign: 'center',
			title : '商品价格'
		}] ],
		pagination : true,
		pagePosition : 'bottom',
		rownumbers : true,
		fitColumns : true,
		selectOnCheck:false,
		checkOnSelect:false,
		remoteSort : true,
		multiSort : true,
		height:310,
		singleSelect : true,
		loadFilter : function(data) {
			if (data.rows) {
				return data;
			} else {
				return data.content;
			}
		}, onCheck : function(rowIndex, rowData) {
			if (goodsIds.indexOf(rowData.goodsId) == -1) { // 判断是否存在，存在则不需要插入
				goodsIds.push(rowData.goodsId);
			}
			if (selectGoodsIds.indexOf(rowData.goodsId) == -1) { 
				selectGoodsIds.push(rowData.goodsId);
			}
		}, onUncheck : function(rowIndex, rowData) {
			goodsIds.remove(rowData.goodsId);
			selectGoodsIds.remove(rowData.goodsId);
		},onCheckAll:function(rows){   //点击全选按钮
			$.each(rows,function(i,rowData){
				if (goodsIds.indexOf(rowData.goodsId) == -1) { // 判断是否存在，存在则不需要插入
					goodsIds.push(rowData.goodsId);
				}
				if (selectGoodsIds.indexOf(rowData.goodsId) == -1) { 
					selectGoodsIds.push(rowData.goodsId);
				}
			});
		} ,onUncheckAll:function(rows){
			$.each(rows,function(i,rowData){
				goodsIds.remove(rowData.goodsId);
				selectGoodsIds.remove(rowData.goodsId);
			});
		},
		 onLoadSuccess : function(data) {
			if (selectGoodsIds.length > 0) {
				$.each(data.rows, function(i, v) {
					
					if (selectGoodsIds.indexOf(v.goodsId) != -1) { // 已经被选择了
						$('#goodsListTable').datagrid('checkRow', i);
					}
				});
			}
		}
	});
}


/**初始化编辑*/
function initEdit(actId){
	//var actId = $('#actId').val();
	var conditionsArr = [];
	var conArr = [];
	selectGoodsIds = [];
	//if(null != actId && '' != actId) {
		commonAjax(_path + '/fullReduce/getGoodIds', { actId : actId }, function(data) {
			selectGoodsIds = data.content;
			initGoods();
		});
		
		
		commonAjax(_path + '/fullReduce/editFullReduce', { actId : actId }, function(data) {
			var dataObj = data.content;
			$('#ruleName').val(dataObj.name);
			$('#ruleId').val(dataObj.ruleId);
			conditionsArr = dataObj.conditions.split(",");
			for(var i=0;i<conditionsArr.length;i++) {
				var condition = conditionsArr[i];
				conArr = condition.split("|");
				if(i==0) {
					$('#fullPrice').val(conArr[0]);
					$('#reducePrice').val(conArr[1]);
				}else {
					var html='<div class="rule-price">消费满<input type="text" style="width:50px;" maxlength="4" name="fullPrice" value="'+conArr[0]+'" class="easyui-validatebox" required="required" id="fullPrice'+i+'"  >元，'+
					'减<input type="text" style="width:50px;" maxlength="4" name="reducePrice" value="'+conArr[1]+'" class="easyui-validatebox" required="required" id="reducePrice'+i+'"  >元<span><a style="  margin-right: 10px;" onclick="deleteRule(this)">删除</a></span></div>';
					$('#good-rule-group').append(html);
					
				}
				
			}
			$.parser.parse('#good-rule-group');
			memberIds = dataObj.memberLvIds.split(',');
			findMemberLevel(memberIds);
			if(dataObj.usePlatform == 2) {
				$("#platform2").prop("checked","checked");
			}else if(dataObj.usePlatform == 3){
				$("#platform3").prop("checked","checked");
			}else {
				$("#platform1").prop("checked","checked");
			}
			$('#joinTimes').numberspinner('setValue', dataObj.joinTimes);  
			$('#fromTime').datebox('setValue', dataObj.startTime);
			$('#toTime').datebox('setValue', dataObj.endTime);
		});
	/*}else {
		findMemberLevel(memberIds);
	}*/
}

/**获得会员等级*/
function findMemberLevel(memberIds) {
	$.getJSON(biz.rootPath() + '/memberLevel/findAll', function(json) {console.log(json);
		var html = "";
		$.each(json.content, function(i, n) {
			html += '<input type="checkbox" name="memberLvIds" value="'+n.memberLvId+'" id="level_'+i+'"';
			if(memberIds!=null && memberIds.indexOf(n.memberLvId+'')!=-1){
				html += 'checked';
			}
			html += '><label for="level_'+i+'">'+n.name+'</label>';
		});
		$('#mLev').append(html);
	});

}

/**判断时间大小*/
function onSelect(date) { 
    var issd = this.id == 'fromTime';
    var sd = issd ? date.format("yyyy-MM-dd") : new Date($('#fromTime').datebox('getValue')).format("yyyy-MM-dd");
    var ed = issd ? new Date($('#toTime').datebox('getValue')).format("yyyy-MM-dd") : date.format("yyyy-MM-dd");
    if (ed < sd) {
        $.messager.alert('错误', '结束日期小于开始日期！', 'error');
        //只要选择了日期，不管是开始或者结束都对比一下，如果结束小于开始，则清空结束日期的值并弹出日历选择框
        $('#toTime').datebox('setValue', '').datebox('showPanel');
    }
}

function deleteRule(obj){
	obj.parentNode.parentNode.remove();
}


function updateActivityGoods(goodsId,index){
	var params={};
	params.goodsId=goodsId;
	/*$.each(jsonArray,function(i,v){
		if(v.goodsId==goodsId) {
			jsonArray.remove(v);
			$('#goodsAddListTable').datagrid('deleteRow',index );
			$('#goodsAddListTable').datagrid('loadData', jsonArray);
		}
		
	});*/
	$.ajax({
	    url:_path + '/goods/updateActivityGoodsById',
	    type:"post",
	    data:params,
	    dataType:"json",
	    success:function(_data){
	    	$('#goodsListTable').datagrid('reload', {});
	    	
	    }
	});
	
}

/*function updateGoods(){
	var selectRows = $("#goodsListTable").datagrid('getChecked');
	if(selectRows.length==0){
		$.messager.alert('提示', '请选择需要操作的数据项！', 'error');
		return false;
	}
	//console.log("selectRows="+selectRows);
	var ids =[];
	$.each(selectRows,function(i,v){
		ids[i]=v.goodsId;
		//jsonArray.push(v);
	});
	var params={};
	params.goodsId=ids;
	//jsonArray.push(selectRows);
	console.log("jsonArray="+jsonArray);
	$('#goodsAddListTable').datagrid('appendRow', {});
	$('#goodsAddListTable').datagrid({
		rownumbers : true,
		fitColumns : true,
		selectOnCheck:false,
		checkOnSelect:false,
		remoteSort : false,
		multiSort : true,
		height:300,
		nowrap : false,
		data:jsonArray
	});
	
	$.ajax({
	    url:_path + '/goods/updateActivityGoods',
	    type:"post",
	    data:params,
	    dataType:"json",
	    success:function(_data){
	    	$('#goodsListTable').datagrid('reload', {});
	    	//$('#goodsAddListTable').datagrid('reload', {});
	    }
	});
}*/

function cancle(){
	window.location.href = _path + '/fullReduce/fullReduceList';
}


function saveActivity() {
	var params = biz.serializeObject($("#form_saveRule"));
	params.name=$("#ruleName").val();
	if(params.name == null || params.name == '') {
		$.messager.alert('错误', '规则名称不能为空！', 'error');
		return;
	}
	
	if(goodsIds.length!=0){
		params.goodsId = goodsIds.join(",");
	}else{
		params.goodsId=[];
	}
	/*var rows = $("#goodsAddListTable").datagrid("getRows");
	  var ids =[];
	$.each(rows,function(i,v){
		ids[i]=v.goodsId;
	});
	params.goodsId=ids.join(",");*/
	if(params.goodsId == null || params.goodsId.length <= 0) {
		$.messager.alert('错误', '请添加相关商品！', 'error');
		return;
	}
	var rulePrice = "";
	$('.rule-price').each(function(index) {
		var $this = $(this);
		var fullPrice = $this.find('input[name="fullPrice"]').eq(0).val();
		var reducePrice = $this.find('input[name="reducePrice"]').eq(0).val();
		rulePrice += fullPrice+"|"+reducePrice+",";
	});
	
	rulePrice = rulePrice.length>1 ? rulePrice.substring(0, rulePrice.length-1):rulePrice;
	params.conditions=rulePrice;
	if(params.conditions == null || params.conditions == '' || params.conditions == '|') {
		$.messager.alert('错误', '请设置促销规则！', 'error');
		return;
	}
	params.joinTimes=$("#joinTimes").val();
	if(params.joinTimes == null || params.joinTimes == '') {
		$.messager.alert('错误', '请设置可参与次数！', 'error');
		return;
	}else {
		if(params.joinTimes <= 0) {
			$.messager.alert('错误', '可参与次数必须大于0！', 'error');
			return;
		}
	}
	var platform = $("input[name='platform']:checked").val();
	params.usePlatform=platform;
	
	var memLevel = "";
	$('input[name="memberLvIds"]:checked').each(function(){
		memLevel += $(this).val() + ",";
	});
	
	memLevel = memLevel.length>1 ? memLevel.substring(0, memLevel.length-1):memLevel;
	params.memberLvIds=memLevel;
	if(params.memberLvIds == null || params.memberLvIds == '') {
		$.messager.alert('错误', '请设置适用会员！', 'error');
		return;
	}
	
	if(params.fromTimeStr == null || params.fromTimeStr == '' || params.toTimeStr == null || params.toTimeStr == '') {
		$.messager.alert('错误', '请设置有效期！', 'error');
		return;
	}
	//var fromDate = $("#fromTime").val();
	//var toDate =  $("#toTime").val();
	
	//params.fromTimeStr=fromDate;
	//params.toTimeStr=toDate;
	var actId = $('#actId').val();
	var url = "";
	if(null != actId && '' != actId) {
		params.actId = actId;
		params.ruleId=$('#ruleId').val();
		url = _path + '/fullReduce/updateFullReduce';
	}else {
		url = _path + '/fullReduce/saveFullReduce';
	}
	
	$.ajax({ url :  url, data : params , type : "post" , datatype : "json" , 
		success : function(data) {
		if (data.head.retCode == '000000') {
			layer.msg('保存成功',{time: 3000});
		} else if (data.head.retCode == '000004') {
			easyuiMsg('错误', "该商品编号已经存在！");
		} else if (data.head.retCode == '000008') {
			easyuiMsg('错误', "货品编号已经存在！");
		} else if (data.head.retCode == '000011') {
			easyuiMsg('错误', "店铺违规，限制发布商品或开启规格选择未违规的分店进行发布商品！");
		}else {
			layer.msg('保存失败',{time: 3000});
		}
	} });
}