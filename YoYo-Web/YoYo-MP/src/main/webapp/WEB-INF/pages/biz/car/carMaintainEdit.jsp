<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
    request.setAttribute("time", System.currentTimeMillis());
%>
<!-- 新增品牌-->
<form id="form-car-maintain-add" class="easyui-form" >
		<table class="add_brand_table">
			<tr>
				<td align="right"><span style="color: red;">*</span>选择车型：</td>
				<td>
					<select id="car_brand_sel" name="carBrand" style="width: 160px;">
					</select>
					<select id="car_factory_sel" name="carFactory" style="width: 160px;">
					</select>
					<select id="car_dept_sel" name="carDept" style="width: 160px;">
					</select>
					<select id="car_type_sel" name="carId" style="width: 160px;">
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">官方保养：</td>
				<td>
					<select class="js-example-basic-multiple" multiple="multiple" id="official_maintain_sel" style="width: 360px">					 
					</select>
				</td>
			</tr>
			<tr>
			<td align="right">自选保养：</td>
				<td>
					<input id="citySel" onclick="showMenu();" type='text' class="tab_input easyui-validatebox" />
				</td>
			</tr>
			<tr>
				<td align="right">保养里程：</td>
				<td><input type='text' name="maintainKm" class="tab_input easyui-validatebox" value="${carMaintain.maintainKm}" data-options="required:true" /></td>
			</tr>
			<tr>
				<td align="right">保养周期：</td>
				<td>
					<input type='text' name="maintainTime" class="tab_input easyui-numberbox easyui-validatebox" value="${carMaintain.maintainTime}" data-options="required:true" style="width: 90px;" /> 个月
				</td>
			</tr>
			<tr>
				<td align="right">首保时长：</td>
				<td><input type='text' name="firstMaintainTime"
					class="tab_input easyui-numberbox easyui-validatebox"
					data-options="required:true" style="width: 90px;" value="${carMaintain.firstMaintainTime}"/> 个月</td>
			</tr>
			<tr>
				<td align="right">首保公里数：</td>
				<td><input type='text' name="firstMaintainKm"
					style="width: 90px;"
					class="tab_input easyui-numberbox easyui-validatebox"
					data-options="required:true" value="${carMaintain.firstMaintainKm}"/>KM</td>
			</tr>
			<tr>
				<td align="right">购车时长：</td>
				<td><input type='text' name="purchaseTime"
					class="tab_input easyui-numberbox easyui-validatebox"
					style="width: 90px;" data-options="required:true" value="${carMaintain.purchaseTime}"/> 个月</td>
			</tr>
			<tr>
				<td align="right">行驶里程：</td>
				<td><input type='text' name="carTripRange"
					class="tab_input easyui-numberbox easyui-validatebox"
					style="width: 90px;" data-options="required:true" value="${carMaintain.carTripRange}" />KM</td>
			</tr>
			<tr>
				<td align="right">排序：</td>
				<td><input type='text' name="sort"
					class="tab_input easyui-numberbox" style="width: 90px;" value="${carMaintain.sort}" /></td>
			</tr>
			<tr>
				<td align="right">保养周期描述：</td>
				<td><textarea cols="50" rows="6" name="remark">${carMaintain.remark}</textarea></td>
			</tr>
	
		</table>
	
	<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
		<ul id="treeDemo" class="ztree tab_input easyui-validatebox validatebox-text validatebox-invalid" style="margin-top:0; width:360px; height: 300px;"></ul>
	</div>
	<div region="south" border="false" style="text-align: center; height: 30px; line-height: 30px; margin-bottom: 10px;">
		<input type="hidden" name="maintainId"  value="${carMaintain.maintainId}">
		<input type="hidden" id="optional_maintain" name="optionalMaintain" value="<c:out value="${carMaintain.optionalMaintain}"/>" />
		<input type="hidden" id="official_Maintain_input" name="officialMaintain" value="<c:out value="${carMaintain.officialMaintain}"/>"/>
		<input type="hidden" name="crawlerId" value="${carMaintain.crawlerId}"/>
		<input type="hidden" id="carBrandIdInput" value="${carMaintain.carBrand}"/>
		<input type="hidden" id="carFactoryIdInput" value="${carMaintain.carFactory}"/>
		<input type="hidden" id="carDeptIdInput" value="${carMaintain.carDept}"/>
		<input type="hidden" id="carIdInput" value="${carMaintain.carId}"/>
		<a class="easyui-linkbutton" id="add-car-maintain-btn" icon="icon-save" onclick="saveCarMaintain()">保存</a>			
		<a class="easyui-linkbutton" id="update-car_maintain-btn" icon="icon-cancel" onclick="javascript:closeDailog('window-edit-car-maintain');" style="margin-left: 15px;">取消</a>
	</div>
	</form>
<link type="text/css" href="${path}/resources/styles/css/zTreeStyle.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/zTree_v3/jquery.ztree.core-3.5.min.js?t=${time}"></script>
<script type="text/javascript" src="${path}/resources/scripts/zTree_v3/jquery.ztree.excheck-3.5.min.js?t=${time}"></script>
<SCRIPT type="text/javascript">
	$(function() {
		findOfficialMaintainCategory();
		carSelectTrigger();		
		initSeelct2(["car_factory_sel","car_dept_sel","car_type_sel"]);
		$("#car_brand_sel").change(function (){			
			initSeelct2(["car_factory_sel","car_dept_sel","car_type_sel"]);
			getCarFactory($(this).select2("val"));
		});
	
		$("#car_factory_sel").change(function (){
			initSeelct2(["car_dept_sel","car_type_sel"]);
			getCarDept($(this).select2("val"));
		});
	
		$("#car_dept_sel").change(function (){
			initSeelct2(["car_type_sel"]);
			getCar($(this).select2("val"));
		});
		var setting = {
				async:{
					contentType:"application/json",  
					dataType:"json",
					enable:true,
					type:"post",
					url:'../carMaintain/findMaintainCategoryTree?identifier='+yoyo.maintain  
				},
				view: {
					dblClickExpand: false
				},
				check: {
					enable: true,
					chkboxType:{"Y" : "ps", "N" : "ps" }
				},
				callback: {
					onCheck:onCheck				
				}
			}
		$.fn.zTree.init($("#treeDemo"), setting);
		var v=$("#optional_maintain").val();
		if(v){
			var title=new Array();
			$.each(JSON.parse(v),function(x,checkJSON){		
				title.push(checkJSON.text);
			});
			$("#citySel").val(title.join(","));
		}
		
	});
		//var zNodes =;
		function beforeClick(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}
		
		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getCheckedNodes(true),			
			v = "";			
			var offMaintainArray=new Array();			
			$.each(nodes,function(x,node){
				if(!node.isParent){	
					offMaintainArray.push("{\"id\":\"" + node.id + "\",\"text\":\"" + node.name+ "\"}");
					v+=node.name+","
				}
			});
			$("#citySel").val(v.substring(0, v.length-1));
			if(offMaintainArray.length>0){
				$("#optional_maintain").val("["+offMaintainArray.join(",")+"]");
			}else{
				$("#optional_maintain").val("");
			}
		}
		
		function nodeIsChecked(node,nodeArry){	
			//主节点选中,且有子节点			
			if(node.checked&&node.isParent){
				$.each(node.children,function(x,node){
					nodeIsChecked(node,nodeArry);
				});
			}
			
			//非主节点选中,且无子节点,获取同级节点
			
			if(node.checked&&(!node.isParent)){
				var nodeJSON="";
				var firstNode=findFirstNode(node);	
				$.each(siblingsNextNode(firstNode),function (x,node){
				});
			}
		}
		function findFirstNode(node){
			if(node.isFirstNode){
				return node;				
			}
			return findFirstNode(node.getPreNode());			
		}
	
	
		function siblingsNextNode(node,nodeArray){			
			if(!nodeArray){
				nodeArray=new Array();
			}			
			if(node.isLastNode){
				nodeArray.push(node);				
			}else if(!node.isLastNode){
				nodeArray.push(node);
				return siblingsNextNode(node.getNextNode(),nodeArray);
			}
			return nodeArray;
		}
		
		function showMenu(){
			initNode();
			var cityObj = $("#citySel");
			var cityOffset = $("#citySel").position();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
			$("body").bind("mousedown", onBodyDown);
		}
		
		function initNode(){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var v=$("#optional_maintain").val();
			if(v){
				$.each(JSON.parse(v),function(x,checkJSON){					
					var curObj=treeObj.getNodeByParam("id", checkJSON.id, null);
					if(curObj){
						treeObj.checkNode(curObj, true, true);
						treeObj.expandNode(curObj, true, true, true);
					}
				});
			}
		}
		
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
		
		function carSelectTrigger(){
			getCarBrand();
		}
		
		function initSeelct2(target){
		    $.each(target,function (x,t){
		    	$("#"+t).val(null);
		    	$("#"+t).children().remove();
			});
		}

		//获得车型
		function getCarData(url,obj,isSelect){
			$.getJSON(url, function(json) {
				var data="[";
				if(isSelect){
					data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
				}
				$.each(json.content, function(i, n){
					data+='{ text: "'+n.carName+'", id:'+n.carId+'},';
				});
				data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
				data+="]";
				
			});
		}

		function getCar(deptId){
			$.post("../carMaintain/findCar",{deptId:deptId}, function(brand) {
				var data="[";
					data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
				$.each(brand, function(i, n){
					
					data+='{ text: "'+n.carName+'", id:'+n.carId+'},';
				});
				data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
				data+="]";
				$("#car_type_sel").select2({data:eval(data)});
				$("#car_type_sel").val($("#carIdInput").val()).trigger("change");
				
			});
		}


		function getCarDept(factoryId){
			$.post("../carMaintain/findCarDept",{factoryId:factoryId}, function(brand) {
				var data="[";
					data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
				$.each(brand, function(i, n){
					data+='{ text: "'+n.carDeptName+'", id:'+n.carDeptId+'},';
				});
				data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
				data+="]";
				$("#car_dept_sel").select2({data:eval(data)});
				$("#car_dept_sel").val($("#carDeptIdInput").val()).trigger("change");
			});
		}

		function getCarFactory(brandId){	
			$.post("../carMaintain/findCarFactory",{brandId:brandId}, function(brand) {
				var data="[";
				data+='{ text: "--请选择--", id: "-1" ,selected:true  },';
				$.each(brand, function(i, n){
					data+='{ text: "'+n.factoryName+'", id:'+n.factoryId+'},';
				});
				data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
				data+="]";
				$("#car_factory_sel").select2({data:eval(data)});
				$("#car_factory_sel").val($("#carFactoryIdInput").val()).trigger("change");
			});	
		}

		function getCarBrand(){
			var identifier= yoyo.car;			
			$.post("../carMaintain/findCarBrand",{identifier:identifier}, function(brand) {
				var data="[" 
					data+='{ text: "--请选择--", id: "-1" ,selected:true  },';		
				$.each(brand, function(i, n){
					data+='{ text: "'+n.brandName+'", id:'+n.brandId+'},';
				});
				data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
				data+="]";
				$("#car_brand_sel").select2({data:eval(data)});
				$("#car_brand_sel").val($("#carBrandIdInput").val()).trigger("change");
			});
		}

		function findOfficialMaintainCategory(){
			$.post("../carMaintain/findOfficialMaintainCategory",{identifier:yoyo.official_maintain}, function(brand) {
				var data="[" 			
				$.each(brand, function(i, n){
					data+='{ text: "'+n.catName+'", id:'+n.catId+',selected:false},';
				});
				data=data.length>1?data.substring(0, data.length-1):data; //除开没有数据的情况
				data+="]";
				
				$("#official_maintain_sel").select2({data:eval(data)});
				var items=new Array();
				if($("#official_Maintain_input").val()){
					$.each(JSON.parse($("#official_Maintain_input").val()),function (x,item){
						items.push(item.id);
					});
				}
				$("#official_maintain_sel").val(items).trigger("change");
			});
		}

		/**
		 * 方法描述：新增周期
		 */
		function saveCarMaintain() {
			var offMaintainArray=new Array();
			$.each($("#official_maintain_sel").select2("data"), function(x, d) {
				offMaintainArray.push("{\"id\":\"" + d.id + "\",\"text\":\"" + d.text+ "\"}");
			});
			$("#official_Maintain_input").val("["+offMaintainArray.join(",")+"]");
			if ($('#form-car-maintain-add').form('validate')) {
				var param_data = biz.serializeObject($("#form-car-maintain-add"));
				if ($("#car_type_sel").val() == '' || $("#car_type_sel").val() == null|| $("#car_type_sel").val() == '-1') {
					easyuiMsg('错误', "请选择车型！");
					return false;
				}			
				$.ajax({
					url : _path + '/carMaintain/insertCarMaintain',
					data : $("#form-car-maintain-add").serialize(),
					type : 'post',
					cache : false,
					dataType : 'json',
					success : function(data) {
						if (data.head.retCode == '000000') {
							easyuiMsg('信息', "保存成功", function() {
								$('#window-edit-car-maintain').window('close');
								$('#table-car-maintain').datagrid('reload', {});
							});
						} else if (data.head.retCode == '000004') {
							easyuiMsg('错误', "该车型周期名称已经存在！");
						} else {
							easyuiMsg('错误', "保存失败！");
						}
					},
					error : function() {
						alert("异常！");
					}
				});
			}
	}

	function closeCategory() {
		$("#window-maintain-category").window('close');
	}
</SCRIPT>
