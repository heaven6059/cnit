/**
 * 功能描述：设置保养商品的js
 * 
 */
$(function() {
	var defaultGoodsSet=[{"text":"--请选择--","id": -1,selected:true},{"text":"已设置","id":1},{"text":"未设置","id":0}];
	$('#default_goods').select2({data:defaultGoodsSet});
	var op={
			refresh:true
		};
	$("#page-num").val("0");
	initPageList(op);
	
	initSeelct2(["car_factory_qry","car_dept_qry","car_type_qry"]);
	$("#car_brand_qry").change(function (){
		initSeelct2(["car_factory_qry","car_dept_qry","car_type_qry"]);
		getCarFactory($(this).select2("val"));
	});

	$("#car_factory_qry").change(function (){
		initSeelct2(["car_dept_qry","car_type_qry"]);
		getCarDept($(this).select2("val"));
	});

	$("#car_dept_qry").change(function (){
		initSeelct2(["car_type_qry"]);
		getCar($(this).select2("val"));
	});	
	getCarBrand();
	//点击设置默认商品
	$(".gridlist").on("click",".operate-btn",function (){
		$(".defaultGoodsTable tr:gt(0)").empty();
		var html='';
		var maintainItemIds=$(this).attr("maintainItemIds");
		$('#maintainId').val($(this).attr("maintainId"));  //保养周期id
		if(maintainItemIds!=''){
			$.ajax( {    
			    url:'../setMaintainGoods/getMaintainItem',// 跳转到 action    
			    data:{'maintainItemIds':maintainItemIds.split(","),"maintianId":$('#maintainId').val()},    
			    type:'post',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	if(data.retCode==0000){
			    		$.each(data.content,function (x,item){
							html+="<tr><td><input type='hidden' value='"+item.maintainItemId+"' />"+item.maintainItemName+"</td>";
							html+="<td><input type='hidden' value='"+item.goodsCateId+"' />"+item.maintainAccName+"</td>";
							if(item.defaultName!=null ){
								html+="<td><span data='"+item.defaultProductId+"'>"+ item.defaultName+"</span></td>";
							}else{
								html+="<td></td>";
							}
							html+="<td><a href='#'  onclick='selectDefaultProduct(this);' style='color:blue'>选择货品</a></td>";
							html+="</tr>";
						});
			    	}
			    	$(".defaultGoodsTable").append(html);
			    	$("#defaulGoodsWindow").window({
			    		onClose:function(){
			    			searchMaintain();
			    		}
			    	});
					$("#defaulGoodsWindow").window('open');
			     },  
			     error : function() {
			          alert("异常！");
			     }
			});
		}
	});
});


/**
 * 分页操作
 * @param option
 */
function initPageList(option){
	var regData = biz.serializeObject($("#maintain_search_form"));
	regData.page = $("#page-num").val();
	regData.rows = 10;
	$.ajax( {    
	    url:'../setMaintainGoods/getMaintainList',// 跳转到 action    
	    data:regData,    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.retCode==0000){
	    		$(".gridlist tr:gt(0)").empty();
	    		var maintainItemIds=new Array();//保养项目的id集合
	    		$.each(data.content.rows,function (x,maintain){
	    				var html='<tr>'+						
							'<td class="product_name">'+
								'<label class="col1">'+maintain.cycleCarName+'</label>'+
							'</td>'+
							'<td>'+maintain.maintainKm+'</td>'+
							'<td>'+maintain.maintainTime+'</td>'+
							'<td>';
	    				var itemTitle="";
						if(maintain.officialMaintain!=null && maintain.officialMaintain!=''){
							$.each(maintain.officialMaintain,function (x,item){
								itemTitle+=item.text+"、";
								maintainItemIds.push(item.id);
							});
						}
						html +=itemTitle;
						html +='</td>';
						itemTitle="";
						if(maintain.optionalMaintain!=null && maintain.optionalMaintain!=''){
							$.each(maintain.optionalMaintain,function (x,item){
								itemTitle+=item.text+"、";
								maintainItemIds.push(item.id);
							});
						}
						html +='<td>'+itemTitle+'</td>';
						if(maintain.num>0){
							html +='<td><div style="color:green">已设置</div></td>';
						}else{
							html +='<td><div style="color:red">未设置</div></td>';
						}
						html +=	'<td><a class="font-blue operate-btn" href="#"  maintainItemIds='+maintainItemIds+' maintainId='+maintain.maintainId+'>设置默认商品</a></td>';
						html+='</tr></tbody>';
					$(".gridlist").append(html);
	    		});
	    		if(option&&option.refresh){
	    			var opt = {
	    				items_per_page:data.content.pageSize,
	    				callback:function (){
	    					var op={
	    						refresh:false
	    					};
	    					initPageList(op);
	    				}
    				};
	    			$("#Pagination").yoyoPagination(data.content.total,opt);
	    		}
	    	}
	     },  
	     error : function() {
	          alert("异常！");
	     }
	});
}

/**清空查询条件*/
function clearFun(){	
	$("#car_brand_qry").val(-1).trigger("change");
	$("#default_goods").val(-1).trigger("change");	
	searchMaintain();
}
/**搜索*/
function searchMaintain(){
	var op={
			refresh:true
		};
		$("#page-num").val("0");
		initPageList(op);
}


var selectDefaultProductIds = new Array();   //分页选择的默认商品id
var selectDefaultProductObj = {};   //分页选择的默认商品对象
var $that = {};  //当前点击的配件货品按钮对象
/**弹出默认货品的对话框*/
function selectDefaultProduct(that){
	 var selectProduct = new Array();   //已经选择的商品
	 var cateId = $($(that).parent().parent().find('td:eq(1) input')).val();
	 $('#maintainItemId').val($($(that).parent().parent().find('td:eq(0) input')).val()); //设置保养项的id
	 $that = $(that).parent().parent().find('td:eq(2)');
	 var selectId=$($that.find('span')).attr('data');
	 selectProduct.push(selectId);
	 selectDefaultProductIds.push(selectId);
	 selectDefaultProductObj[selectId]=$that.find('span').html();
		$('#productDefaultTable').datagrid({
			url : _path + '/product/productList?cateId='+cateId,
			columns : [ [ {
				field:'ck',
				checkbox:"true"
			},{
				field : 'name',
				align : 'center',
				title : '货品名称'			
			}, {
				field : 'productId',
				align : 'center',
				title : '货品ID'
			},  {
				field : 'price',
				align : 'center',
				title : '价格'
			},{
				field : 'specInfo',
				align : 'center',
				title : '货品描述'					
			} ] ],
			pagination : true,
			pagePosition : 'bottom',
			rownumbers : true,
			fitColumns : true,
			pageSize : 20,
			height:540,
			pageList : [ 20, 100, 150, 500 ],
			singleSelect : true,
			checkOnSelect:true,
			selectOnCheck:true,
			remoteSort : false,
			multiSort : true,
			loadFilter : function(data) {
				if (data.rows) {
					return data;
				} else {
					return data.content;
				}
			},
			onLoadSuccess:function(data){
				if(selectProduct.length>0){
					$.each(data.rows,function(i,v){
						if(selectProduct.indexOf(v.productId+'')!=-1){  //已经被选择了
							$('#productDefaultTable').datagrid('checkRow',i);
						}
					});
				}
			},
			onBeforeClose:function(){
				selectDefaultProductIds=[];
				selectDefaultProductObj = {}; 
			},
			onSelect:function(rowIndex,rowData){
				selectDefaultProductIds=[];
				selectDefaultProductObj = {}; 
				selectDefaultProductIds.push(rowData.productId);
				selectDefaultProductObj[rowData.productId]=rowData.name;
			}
		});
		$("#accessoryDefaultWindow").window({
			modal:false,
			resizable:false,
			draggable:false,
			collapsible:false,
			closed:true,
	    	maximized:false,
	    	minimizable:false,
	    	maximizable:false,
	    	onClose:function(){
				selectDefaultProductIds=[];
				selectDefaultProductObj = {}; 
			}
		});
		$("#accessoryDefaultWindow").window("move",{top:$(document).scrollTop() + ($(window).height()-650) * 0.5});
		$('#accessoryDefaultWindow').window('open');
		
	}
	
	
	/**确定选择的默认货品*/
	function confirmAccessoryGoods(){
		if(selectDefaultProductIds.length==0){
			$.messager.show({ title : '提示' , msg : '请选择默认货品!' });
			return false;
		}
		//插入到“默认商品”表格列中
		$that.children().remove();
		var text = '';
		$.each(selectDefaultProductIds,function(i,option){//只有一个货品
			text +="<span data='"+option+"'>"+ selectDefaultProductObj[option]+"</span>";
			$('#productId').val(option);
		});
		$that.append(text);
		//提交到后台
		var param = biz.serializeObject($("#submintform"));
		$.ajax( {    
		    url:'../setMaintainGoods/saveDefaultGoods',// 跳转到 action    
		    data: param,    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	if(data.retCode==0000){
		    		selectDefaultProductIds=[];
		    		selectDefaultProductObj = {};  
		    		$('#accessoryDefaultWindow').window('close');
		    		easyuiMsg('提示', "保存成功！");
		    	} else {
					$.messager.alert('错误', '保存失败', 'error');
				}
		     },  
		     error : function() {
		          alert("异常！");
		     }
		});
		
		
	}




function initSeelct2(target){
    $.each(target,function (x,t){    	
    	$("#"+t).val(null);
    	$("#"+t).empty();
    	var data=[{"text":"--请选择--","id": -1,selected:true}];
    	$("#"+t).select2({data:eval(data)});
	});
}

function getCar(deptId){
	$.post("../car/findCar",{deptId:deptId}, function(brand) {
		var data="[";
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		$.each(brand, function(i, n){
			
			data+='{ text: "'+n.carName+'", id:'+n.carId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		$("#car_type_qry").select2({data:eval(data)});
	});
}


function getCarDept(factoryId){
	$.post("../carDept/findSelect",{factoryId:factoryId}, function(brand) {
		var data="[";
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		$.each(brand.content, function(i, n){
			data+='{ text: "'+n.carDeptName+'", id:'+n.carDeptId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		$("#car_dept_qry").select2({data:eval(data)});
	});
}

function getCarFactory(brandId){	
	$.post("../carFactory/findSelect",{brandId:brandId}, function(brand) {
		var data="[";
		data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
		$.each(brand.content, function(i, n){
			data+='{ text: "'+n.factoryName+'", id:'+n.factoryId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		$("#car_factory_qry").select2({data:eval(data)});
	});	
}

function getCarBrand(){
	var identifier= yoyo.car;			
	$.post("../brand/findCarBrand",{identifier:identifier}, function(brand) {
		var data="[";
			data+='{ text: "--请选择--", id: "-1" ,selected:true  },';		
		$.each(brand, function(i, n){
			data+='{ text: "'+n.brandName+'", id:'+n.brandId+'},';
		});
		data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
		data+="]";
		$("#car_brand_qry").select2({data:eval(data)});
	});
}

